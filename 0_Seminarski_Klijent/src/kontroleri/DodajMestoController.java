/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Mesto;

/**
 *
 * @author HP
 */
public class DodajMestoController {
    
    private final DodajMestoForma forma;
    private final DodajKupcaController roditeljskiKontroler; // Referenca na formu za dodavanje kupca

    public DodajMestoController(DodajMestoForma forma, DodajKupcaController roditeljskiKontroler) {
        this.forma = forma;
        this.roditeljskiKontroler = roditeljskiKontroler;
        forma.setTitle("Dodavanje novog mesta");
        forma.setLocationRelativeTo(null);
        forma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }
    
    private void addActionListener() {
        forma.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajMesto();
            }

            private void sacuvajMesto() {
                try {
                    String nazivMesta = forma.getjTextFieldNazivMesta().getText().trim();
                    
                    if (nazivMesta.isEmpty()) {
                        JOptionPane.showMessageDialog(forma, "Naziv mesta ne sme biti prazan!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    Mesto novoMesto = new Mesto(-1, nazivMesta);
                    

                    Komunikacija.getInstance().dodajMesto(novoMesto);
                    JOptionPane.showMessageDialog(forma, "Sistem je zapamtio mesto.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Osvežavanje JComboBox-a u roditeljskoj formi
                    roditeljskiKontroler.ucitajMesta();
                    
                    forma.dispose();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(forma, "Greška prilikom dodavanja mesta: " + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        forma.setVisible(true);
    }
}
