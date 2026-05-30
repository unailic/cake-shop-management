/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.DodajRacunForma;
import forme.FormaMod;
import static forme.FormaMod.DODAJ;
import static forme.FormaMod.IZMENI;
import forme.model.ModelTabeleStavkaRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Kolac;
import model.Kupac;
import model.Poslasticar;
import model.Racun;
import model.StavkaRacuna;

public class DodajRacunController {
    
    private final DodajRacunForma drf;
    private Racun racun;
    private ModelTabeleStavkaRacuna modelStavke;
    Poslasticar poslasticar = Cordinator.getInstance().getUlogovani();
    
    public DodajRacunController(DodajRacunForma drf, FormaMod mod, Racun racun) throws Exception {
        this.drf = drf;
        this.racun = racun;
        
        drf.setTitle("Kreiranje/Izmena racuna");
        drf.setLocationRelativeTo(null);
        drf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        popuniComboBoxeve();
        dodajActionListenere();
        pripremiFormu(mod);
    }
    
    public void otvoriFormu(){
        drf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                drf.getjButtonIzmeniRacun().setEnabled(false);
                drf.getjButtonKreirajRacun().setVisible(true);
                drf.getjTextFieldID().setEnabled(false);
                drf.getjTextFieldCenaStavke().setEditable(false);
                drf.getjTextFieldPoslasticar().setText(poslasticar.getImePrezime());
                drf.getjTextFieldPoslasticar().setEditable(false);
                modelStavke = new ModelTabeleStavkaRacuna(new ArrayList<>());
                break;
            case IZMENI:
                drf.getjButtonKreirajRacun().setEnabled(false);
                drf.getjButtonIzmeniRacun().setVisible(true);
                drf.getjTextFieldID().setEditable(false);
                drf.getjTextFieldCenaStavke().setEditable(false);
                drf.getjTextFieldPoslasticar().setText(poslasticar.getImePrezime());
                drf.getjTextFieldPoslasticar().setEditable(false);
                popuniFormuZaIzmenu(racun);
                break;
        }
        drf.getjTableStavke().setModel(modelStavke);
    }
    
    private void popuniFormuZaIzmenu(Racun racun) {
        drf.getjTextFieldID().setText(String.valueOf(racun.getIdRacuna()));
        
        for (int i = 0; i < drf.getjComboBoxKupci().getItemCount(); i++) {
            Kupac k = (Kupac) drf.getjComboBoxKupci().getItemAt(i);
            if (k != null && k.getIdKupca() == racun.getKupac().getIdKupca()) {
                drf.getjComboBoxKupci().setSelectedItem(k);
                break;
            }
        }
        
        if (racun.isObradjen()) {
            drf.getjComboBoxObradjen().setSelectedItem("Da");
        } else {
            drf.getjComboBoxObradjen().setSelectedItem("Ne");
        }
        
        try {
            List<StavkaRacuna> stavke = Komunikacija.getInstance().ucitajStavkeRacuna(racun);
            modelStavke = new ModelTabeleStavkaRacuna(stavke);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(drf, "Sistem ne može da učita stavke računa.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void dodajActionListenere() {
        drf.getjComboBoxKolaci().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (drf.getjComboBoxKolaci().getSelectedItem() instanceof Kolac) {
                    Kolac odabraniKolac = (Kolac) drf.getjComboBoxKolaci().getSelectedItem();
                    drf.getjTextFieldCenaStavke().setText(String.valueOf(odabraniKolac.getCena()));
                }
            }
        });
        
        drf.getjButtonDodajStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajStavku();
            }
        });
        
        drf.getjButtonObrisiStavku().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obrisiStavku();
            }
        });
        
        drf.getjButtonKreirajRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kreirajRacun();
            }
        });
        
        drf.getjButtonIzmeniRacun().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeniRacun();
            }
        });
    }

    private void dodajStavku() {
        if (drf.getjComboBoxKolaci().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(drf, "Morate izabrati kolač.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int kolicina = 0;
        try {
            kolicina = Integer.parseInt(drf.getjTextFieldKolicinaStavke().getText().trim());
            if (kolicina <= 0) {
                JOptionPane.showMessageDialog(drf, "Količina mora biti veća od nule.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(drf, "Količina mora biti broj.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Kolac kolac = (Kolac) drf.getjComboBoxKolaci().getSelectedItem();
        
        boolean stavkaPostoji = false;
        // Provera da li stavka sa istim kolacem postoji
        for (StavkaRacuna stavka : modelStavke.getListaStavki()) {
            if (stavka.getKolac().equals(kolac)) {
                // Ako postoji, povecavam kolicinu i cenu
                stavka.setKolicina(stavka.getKolicina() + kolicina);
                stavka.setCenaSR(stavka.getCenaSR() + (kolac.getCena() * kolicina));
                stavkaPostoji = true;
                break;
            }
        }
        
        if (!stavkaPostoji) {
            // Ako ne postoji, dodajem novu stavku
            StavkaRacuna novaStavka = new StavkaRacuna();
            novaStavka.setKolac(kolac);
            novaStavka.setKolicina(kolicina);
            novaStavka.setCenaSR(kolac.getCena() * kolicina);
            modelStavke.dodajStavku(novaStavka);
        }

        modelStavke.fireTableDataChanged();

        drf.getjComboBoxKolaci().setSelectedIndex(-1);
        drf.getjTextFieldCenaStavke().setText("");
        drf.getjTextFieldKolicinaStavke().setText("");
    }
    
    private void obrisiStavku() {
        int red = drf.getjTableStavke().getSelectedRow();
        if (red == -1) {
            JOptionPane.showMessageDialog(drf, "Niste izabrali nijednu stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }
        modelStavke.obrisiStavku(red);
    }
    
    private void kreirajRacun() {
        try {
            Kupac kupac = (Kupac) drf.getjComboBoxKupci().getSelectedItem();
            if(kupac == null) {
                JOptionPane.showMessageDialog(drf, "Morate izabrati kupca.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<StavkaRacuna> stavkeRacuna = modelStavke.getListaStavki();
            if(stavkeRacuna.isEmpty()){
                JOptionPane.showMessageDialog(drf, "Račun mora imati barem jednu stavku.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean obradjen = false;
            if (drf.getjComboBoxObradjen().getSelectedItem() != null && drf.getjComboBoxObradjen().getSelectedItem().equals("Da")) {
                obradjen = true;
            }

            Racun noviRacun = new Racun();
            noviRacun.setKupac(kupac);
            noviRacun.setPoslasticar(poslasticar);
            noviRacun.setStavke(stavkeRacuna);
            noviRacun.setObradjen(obradjen);

            double ukupnaCena = 0.0;
            for (StavkaRacuna stavka : stavkeRacuna) {
                ukupnaCena += stavka.getCenaSR();
            }
            noviRacun.setCena(ukupnaCena);

            Komunikacija.getInstance().kreirajRacun(noviRacun);

            JOptionPane.showMessageDialog(drf, "Sistem je zapamtio račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            prikaziPodatkeORacunuKreiranje(noviRacun);

            drf.dispose();
            Cordinator.getInstance().vratiSeNaGlavnuFormu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(drf, "Sistem ne može da zapamti račun. Moguća greška na serveru.", "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void izmeniRacun() {
        try {
            if (this.racun.isObradjen()) {
                JOptionPane.showMessageDialog(drf, "Sistem ne može da izmeni obrađeni račun.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Kupac kupac = (Kupac) drf.getjComboBoxKupci().getSelectedItem();
            
            if (kupac == null || poslasticar == null) {
                JOptionPane.showMessageDialog(drf, "Morate izabrati kupca.", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            this.racun.setKupac(kupac);
            this.racun.setPoslasticar(poslasticar);
            
            boolean obradjen = false;
            if (drf.getjComboBoxObradjen().getSelectedItem() != null && drf.getjComboBoxObradjen().getSelectedItem().equals("Da")) {
                obradjen = true;
            }
            this.racun.setObradjen(obradjen);
            
            List<StavkaRacuna> stavkeRacuna = modelStavke.getListaStavki();
            double ukupnaCena = 0.0;
            for (StavkaRacuna stavka : stavkeRacuna) {
                ukupnaCena += stavka.getCenaSR();
            }
            this.racun.setCena(ukupnaCena);
            
            this.racun.setStavke(stavkeRacuna); 
            
            Komunikacija.getInstance().izmeniRacun(this.racun);
            JOptionPane.showMessageDialog(drf, "Sistem je zapamtio račun.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            prikaziPodatkeORacunuIzmena(this.racun);
            drf.dispose();
            Cordinator.getInstance().vratiSeNaGlavnuFormu();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(drf, "Sistem ne može da zapamti račun. Moguća greška na serveru.", "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    private void popuniComboBoxeve() throws Exception {
        List<Kupac> kupci = Komunikacija.getInstance().ucitajKupce();
        List<Kolac> kolaci = Komunikacija.getInstance().ucitajKolace();
        drf.getjComboBoxKupci().removeAllItems();
        drf.getjComboBoxKolaci().removeAllItems();
        
        for (Kolac kolac : kolaci) {
            drf.getjComboBoxKolaci().addItem(kolac);
        }
        drf.getjComboBoxKolaci().setSelectedIndex(-1);
        
        for (Kupac k : kupci) {
            drf.getjComboBoxKupci().addItem(k);
        }
        drf.getjComboBoxKupci().setSelectedIndex(-1);
        

        drf.getjComboBoxObradjen().setSelectedIndex(-1);
    }
    
    private void prikaziPodatkeORacunuIzmena(Racun racun) {
        String poruka =
                "ID računa: " + racun.getIdRacuna() + "\n"
                + "Ukupna vrednost: " + racun.getCena() + "\n"
                + "Status obrade: " + (racun.isObradjen() ? "Obrađen" : "Neobrađen") + "\n"
                + "Poslastičar: " + racun.getPoslasticar().toString() + "\n"
                + "Kupac: " + racun.getKupac().toString();
        
        JOptionPane.showMessageDialog(drf, poruka, "Podaci o računu", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void prikaziPodatkeORacunuKreiranje(Racun racun) {
        String poruka =
                "Ukupna vrednost: " + racun.getCena() + "\n"
                + "Status obrade: " + (racun.isObradjen() ? "Obrađen" : "Neobrađen") + "\n"
                + "Poslastičar: " + racun.getPoslasticar().toString() + "\n"
                + "Kupac: " + racun.getKupac().toString();
        
        JOptionPane.showMessageDialog(drf, poruka, "Podaci o računu", JOptionPane.INFORMATION_MESSAGE);
    }
}