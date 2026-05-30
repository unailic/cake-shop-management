/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazRacunaForma;
import forme.PrikazStavkiRacunaForma;
import forme.model.ModelTabeleRacuni;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import komunikacija.Komunikacija;
import model.Kupac;
import model.Poslasticar;
import model.Racun;

/**
 *
 * @author HP
 */
public class PrikazRacunaController {

    private PrikazRacunaForma forma;
    private boolean isInitialLoad = true;

    public PrikazRacunaController(PrikazRacunaForma forma) {
        this.forma = forma;
        otvoriFormu();
        addActionListener();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        forma.setLocationRelativeTo(null);
        forma.setTitle("Prikaz racuna");
        forma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        forma.setVisible(true);

    }

    private void pripremiFormu() {
        try {
            // Učitavam listu računa sa servera
            List<Racun> listaRacuna = Komunikacija.getInstance().ucitajListuRacuna();
            
            // Kreiram i postavljam model tabele
            ModelTabeleRacuni model = new ModelTabeleRacuni(listaRacuna);
            forma.getjTableRacuni().setModel(model);
            
            TableRowSorter<ModelTabeleRacuni> sorter = new TableRowSorter<>(model);
            forma.getjTableRacuni().setRowSorter(sorter);
            
            popuniComboBoxevePretraga();

            if (isInitialLoad) {
                JOptionPane.showMessageDialog(forma, "Sistem je učitao listu računa.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                isInitialLoad = false;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da učita listu računa. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addActionListener() {
        dodajActionListenerNaDugmePrikaziStavke();
        dodajActionListenerNaDugmePretraziRacun();
        dodajActionListenerNaDugmeResetuj();
        dodajActionListenerNaDugmeIzmeni();
        dodajActionListenerNaTabelu();
    }
    
        private void dodajActionListenerNaDugmePrikaziStavke() {
        forma.addBtnPrikaziSRActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = forma.getjTableRacuni().getSelectedRow();
                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Morate odabrati račun.", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                } else {
                    int modelRow = forma.getjTableRacuni().convertRowIndexToModel(red);
                    ModelTabeleRacuni model = (ModelTabeleRacuni) forma.getjTableRacuni().getModel();
                    Racun odabraniRacun = model.getListaRacuna().get(modelRow);
                    Cordinator.getInstance().otvoriPrikazStavkiRFormu(odabraniRacun);
                }
            }
        });
    }
        
    private void dodajActionListenerNaDugmePretraziRacun() {
        forma.addBtnPretraziRacunActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pretraziRacune();
            }
        });
    }

    private void dodajActionListenerNaDugmeResetuj() {
        forma.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Resetovanje polja za pretragu na default vrednosti
                forma.getjTextFieldID().setText("");
                forma.getjComboBoxPretragaPoslasticar().setSelectedIndex(-1);
                forma.getjComboBoxPretragaKupac().setSelectedIndex(-1);
                forma.getjComboBoxStatus().setSelectedIndex(-1);
                
                // Osvežavanje tabele sa svim računima
                osveziTabelu();
            }
        });
    }
    
    private void dodajActionListenerNaDugmeIzmeni() {
        forma.addBtnIzmeniRacunActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniOdabraniRacun();
            }
        });
    }
    
    private void dodajActionListenerNaTabelu() {
        forma.getjTableRacuni().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int red = forma.getjTableRacuni().getSelectedRow();
                
                if (red == -1) {
                    JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (e.getClickCount() == 1) { 
                    try {
                        int modelRow = forma.getjTableRacuni().convertRowIndexToModel(red);
                        ModelTabeleRacuni model = (ModelTabeleRacuni) forma.getjTableRacuni().getModel();
                        Racun odabraniRacun = model.getListaRacuna().get(modelRow);
                        
                        // Prvi JOptionPane
                        JOptionPane.showMessageDialog(forma, "Sistem je našao račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                        
                        // Drugi JOptionPane sa detaljima o računu
                        String poruka = "ID računa: " + odabraniRacun.getIdRacuna() + "\n"
                                      + "Ukupna vrednost: " + odabraniRacun.getCena() + "\n"
                                      + "Status obrade: " + (odabraniRacun.isObradjen() ? "Obrađen" : "Neobrađen") + "\n"
                                      + "Poslastičar: " + odabraniRacun.getPoslasticar().toString() + "\n"
                                      + "Kupac: " + odabraniRacun.getKupac().toString();
                        
                        JOptionPane.showMessageDialog(forma, poruka, "Podaci o računu", JOptionPane.INFORMATION_MESSAGE);
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void pretraziRacune() {
        int idRacuna = 0;
        if (!forma.getjTextFieldID().getText().isEmpty()) {
            try {
                idRacuna = Integer.parseInt(forma.getjTextFieldID().getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(forma, "ID računa mora biti broj!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        Poslasticar poslasticar = (Poslasticar) forma.getjComboBoxPretragaPoslasticar().getSelectedItem();
        Kupac kupac = (Kupac) forma.getjComboBoxPretragaKupac().getSelectedItem();

        // Inicijalizujem listu van try-catch bloka da bude vidljiva svuda
        List<Racun> filtriraniRacuni = new ArrayList<>();
        String status = (String) forma.getjComboBoxStatus().getSelectedItem();

        try {
            if (status == null) {
                // SCENARIO 1: Nije izabran status
                // Prvi zahtev: pretraga za OBRADJENE racune
                Racun racunZaPretraguTrue = new Racun(idRacuna, 0.0, true, poslasticar, kupac);
                List<Racun> obradjeniRacuni = Komunikacija.getInstance().pretraziRacune(racunZaPretraguTrue);
                filtriraniRacuni.addAll(obradjeniRacuni);

                // Drugi zahtev: pretraga za NEOBRADJENE racune
                Racun racunZaPretraguFalse = new Racun(idRacuna, 0.0, false, poslasticar, kupac);
                List<Racun> neobradjeniRacuni = Komunikacija.getInstance().pretraziRacune(racunZaPretraguFalse);
                filtriraniRacuni.addAll(neobradjeniRacuni);

            } else {
                // SCENARIO 2: Izabran je status (Obrađeni ili Neobrađeni)
                boolean obradjen = status.equals("Obrađeni");
                Racun racunZaPretragu = new Racun(idRacuna, 0.0, obradjen, poslasticar, kupac);
                filtriraniRacuni = Komunikacija.getInstance().pretraziRacune(racunZaPretragu);
            }

            // Ažuriranje i osvežavanje tabele
            ModelTabeleRacuni model = (ModelTabeleRacuni) forma.getjTableRacuni().getModel();
            model.setListaRacuna(filtriraniRacuni);
            model.fireTableDataChanged();

            // Prikaz poruke korisniku
            if (filtriraniRacuni.isEmpty()) {
                JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe račune po zadatim kriterijumima.", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(forma, "Sistem je našao račune po zadatim kriterijumima.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da nađe račune po zadatim kriterijumima. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void izmeniOdabraniRacun() {
        int red = forma.getjTableRacuni().getSelectedRow();

        if (red == -1) {
            JOptionPane.showMessageDialog(forma, "Morate izabrati račun za izmenu.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int modelRow = forma.getjTableRacuni().convertRowIndexToModel(red);
            ModelTabeleRacuni model = (ModelTabeleRacuni) forma.getjTableRacuni().getModel();
            Racun odabraniRacun = model.getListaRacuna().get(modelRow);

            Cordinator.getInstance().otvoriIzmeniRacunFormu(odabraniRacun);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da učita odabrani račun za izmenu. Detalji: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void popuniComboBoxevePretraga() {
        try {
            List<Kupac> kupci = Komunikacija.getInstance().ucitajKupce();
            List<Poslasticar> poslasticari = Komunikacija.getInstance().ucitajPoslasticare();

            forma.getjComboBoxPretragaKupac().removeAllItems();
            forma.getjComboBoxPretragaKupac().addItem(null);
            for (Kupac kupac : kupci) {
                forma.getjComboBoxPretragaKupac().addItem(kupac);
            }
            forma.getjComboBoxPretragaKupac().setSelectedIndex(-1);

            forma.getjComboBoxPretragaPoslasticar().removeAllItems();
            forma.getjComboBoxPretragaPoslasticar().addItem(null);
            for (Poslasticar poslasticar : poslasticari) {
                forma.getjComboBoxPretragaPoslasticar().addItem(poslasticar);
            }
            forma.getjComboBoxPretragaPoslasticar().setSelectedIndex(-1);

            forma.getjComboBoxStatus().removeAllItems();
            forma.getjComboBoxStatus().addItem(null);
            forma.getjComboBoxStatus().addItem("Obrađeni");
            forma.getjComboBoxStatus().addItem("Neobrađeni");
            forma.getjComboBoxStatus().setSelectedIndex(-1);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Greška prilikom popunjavanja polja za pretragu.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void osveziTabelu() {
        pripremiFormu();
    }
}
