/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.PrikazStavkiRacunaForma;
import forme.model.ModelTabeleKupci;
import forme.model.ModelTabeleStavkaRacuna;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Racun;
import model.StavkaRacuna;
import komunikacija.Komunikacija;
import model.Kupac;
import model.Mesto;

/**
 *
 * @author HP
 */
public class PrikazStavkiRacunaController {
    private PrikazStavkiRacunaForma forma;
    private Racun racun;
    private boolean isInitialLoad = true;

    public PrikazStavkiRacunaController(PrikazStavkiRacunaForma forma, Racun racun) {
        this.forma = forma;
        this.racun = racun;
        otvoriFormu();
        addActionListener();
    }
    
    public void otvoriFormu(){
        pripremiFormu();
        forma.setLocationRelativeTo(null);
        forma.setTitle("Prikaz stavki odabranog racuna");
        forma.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        forma.setVisible(true);
        forma.getjLabelBroj().setText("Stavke racuna broj "+racun.getIdRacuna());
    }

    private void pripremiFormu() {
        try {
            // Učitavam listu stavki računa sa servera
            List<StavkaRacuna> listaStavki = Komunikacija.getInstance().ucitajStavkeRacuna(racun);
            
            // Kreiram i postavljamo model tabele
            ModelTabeleStavkaRacuna model = new ModelTabeleStavkaRacuna(listaStavki);
            forma.getjTableStavkeRacuna().setModel(model);

            if (isInitialLoad) {
                JOptionPane.showMessageDialog(forma, "Sistem je učitao stavke računa.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                isInitialLoad = false;
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(forma, "Sistem ne može da učita stavke računa.", "Greška", JOptionPane.ERROR_MESSAGE);
            System.out.println(ex.getMessage());
        }
    }
    

    private void addActionListener() {

    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
}
