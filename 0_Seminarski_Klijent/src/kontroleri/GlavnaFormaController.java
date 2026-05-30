/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import cordinator.Cordinator;
import forme.GlavnaForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.Poslasticar;

/**
 *
 * @author HP
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();
        pripremi();
    }

    private void addActionListeners() {
        gf.addBtnIzmeniProfilActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Poslasticar ulogovaniPoslasticar = Cordinator.getInstance().getUlogovani();
                
                if (ulogovaniPoslasticar != null) {
                    Cordinator.getInstance().otvoriIzmeniPoslasticaraFormu(ulogovaniPoslasticar);
                } else {
                    JOptionPane.showMessageDialog(gf, "Nije pronađen ulogovani poslastičar.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void otvoriFormu() {
        Poslasticar ulogovani = Cordinator.getInstance().getUlogovani();
        String imePrezime = ulogovani.getImePrezime();
        gf.setVisible(true);
        gf.getjLabelDobrodosao().setText("Dobrodošli!");
    }
    
    private void pripremi() {
        gf.setLocationRelativeTo(null);
        gf.setTitle("Sistem za poslovanje poslastičarnice");
        
        Poslasticar ulogovani = Cordinator.getInstance().getUlogovani();
        
        if (ulogovani != null) {
            // Prikaz imena i prezimena
            gf.getjLabelImePrezime().setText(" " + ulogovani.getImePrezime());

            // Prikaz datuma rođenja sa formatiranjem
            if (ulogovani.getDatumRodjenja() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                String formatiranDatum = ulogovani.getDatumRodjenja().format(formatter);
                gf.getjLabelDatumRodjenja().setText(" " + formatiranDatum);
            }

            // Prikaz korisničkog imena
            gf.getjLabelKorisnickoIme().setText(" " + ulogovani.getKorisnickoIme());
        }
        
        // 2. Prikaz datuma i vremena
        new Thread(() -> {
            while (true) {
                try {
                    // Uzimam trenutni datum i vreme
                    LocalDateTime sada = LocalDateTime.now();
                    DateTimeFormatter formatiranDatum = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
                    DateTimeFormatter formatiranoVreme = DateTimeFormatter.ofPattern("HH:mm:ss");

                    String datum = "Datum: " + sada.format(formatiranDatum);
                    String vreme = "Vreme: " + sada.format(formatiranoVreme);

                    SwingUtilities.invokeLater(() -> {
                        gf.getjLabelDatum().setText(datum);
                        gf.getjLabelVreme().setText(vreme);
                    });

                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    // Ako je nit prekinuta, izlazim iz petlje
                    break;
                }
            }
        }).start();
    }

    public void osveziPrikazUlogovanogPoslasticara() {
        pripremi();
    }
}
