/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import static forme.FormaMod.*;
import forme.model.ModelTabeleKolaci;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Kolac;
import cordinator.Cordinator;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class DodajKolacController {
    private final DodajKolacForma dkf;

    public DodajKolacController(DodajKolacForma dkf) {
        this.dkf = dkf;
        dkf.setTitle("Dodavanje/Izmena kolača");
        dkf.setLocationRelativeTo(null);
        dkf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        dkf.getjTextAreaNoviOpis().setLineWrap(true);
        dkf.getjTextAreaNoviOpis().setWrapStyleWord(true);
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod){
        pripremiFormu(mod);
        dkf.setVisible(true);
    }

    private void addActionListener() {
            dkf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }
            private void dodaj(ActionEvent e){
                String noviNaziv = dkf.getjTextFieldNoviNaziv().getText().trim();
                String noviOpis = dkf.getjTextAreaNoviOpis().getText();
                double cena;
                
                try {
                    cena = Double.parseDouble(dkf.getjTextFieldCena().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dkf, "Cena mora biti broj.", "Greška!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Kolac noviKolac = new Kolac(-1, noviNaziv, noviOpis, cena);
                
                try{
                    Komunikacija.getInstance().dodajKolac(noviKolac);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kolac.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti kolac.", "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
            
            dkf.izmeniAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }
            private void izmeni(ActionEvent e){
                int sifra = Integer.parseInt(dkf.getjTextFieldSifraKolaca().getText());
                String noviNaziv = dkf.getjTextFieldNoviNaziv().getText().trim();
                String noviOpis = dkf.getjTextAreaNoviOpis().getText();
                double novaCena;

                try {
                    novaCena = Double.parseDouble(dkf.getjTextFieldCena().getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dkf, "Cena mora biti broj.", "Greška!", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Kolac noviKolac = new Kolac(sifra, noviNaziv, noviOpis, novaCena);
                
                try{
                    Komunikacija.getInstance().izmeniKolac(noviKolac);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio kolac.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    dkf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne moze da zapamti kolac.", "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            }
            
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch (mod) {
            case DODAJ:
                dkf.getjTextFieldSifraKolaca().setEnabled(false);
                dkf.getjButtonIzmeni().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonDodaj().setEnabled(true);
                break;
            case IZMENI:
                dkf.getjButtonIzmeni().setVisible(true);
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonIzmeni().setEnabled(true);
                
                Kolac k = (Kolac) Cordinator.getInstance().vratiParam("kolac");
                dkf.getjTextFieldSifraKolaca().setText(k.getSifraKolaca()+"");
                dkf.getjTextFieldNoviNaziv().setText(k.getNaziv());
                dkf.getjTextAreaNoviOpis().setText(k.getOpis());
                dkf.getjTextFieldCena().setText(String.valueOf(k.getCena()));

                break;
            default:
                throw new AssertionError();
        }
    }
    
    
}
