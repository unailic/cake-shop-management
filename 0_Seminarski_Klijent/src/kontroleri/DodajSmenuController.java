/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Smena;
import komunikacija.Komunikacija;

/**
 *
 * @author HP
 */
public class DodajSmenuController {
    
    private DodajSmenuForma dsf;

    public DodajSmenuController(DodajSmenuForma dsf) {
        this.dsf = dsf;
        pripremiFormu();

        dsf.setTitle("Dodavanje smene");
        dsf.setLocationRelativeTo(null);
        dsf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        addActionListener();
    }

    private void pripremiFormu() {
    }
    
    private void addActionListener() {
        dsf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Uzimam podataka iz forme
                    String nazivSmene = dsf.getjTextFieldNazivSmene().getText().trim();
                    String pocetakString = dsf.getjTextFieldPocetak().getText().trim();
                    String krajString = dsf.getjTextFieldKraj().getText().trim();
                    
                    // Validacija unosa
                    if (nazivSmene.isEmpty() || pocetakString.isEmpty() || krajString.isEmpty()) {
                        throw new Exception("Morate uneti sve podatke o smeni.");
                    }
                    
                    // Parsiranje vremena
                    LocalTime pocetakSmene = LocalTime.parse(pocetakString);
                    LocalTime krajSmene = LocalTime.parse(krajString);
                    
                    if (krajSmene.isBefore(pocetakSmene)) {
                        throw new Exception("Vreme kraja smene ne sme biti pre vremena početka smene.");
                    }
                    
                    // Kreiram objekat Smena
                    Smena novaSmena = new Smena(-1, nazivSmene, pocetakSmene, krajSmene);
                    
                    // Pozivam server
                    Komunikacija.getInstance().dodajSmenu(novaSmena);

                    JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio smenu.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    
                    dsf.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dsf, "Sistem ne moze da zapamti smenu.", "Greška!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        dsf.setVisible(true);
    }
}
