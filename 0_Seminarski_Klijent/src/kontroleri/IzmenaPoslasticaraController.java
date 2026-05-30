/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

/**
 *
 * @author HP
 */

import cordinator.Cordinator;
import forme.FormaMod;
import forme.IzmenaPoslasticaraForma;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import model.Poslasticar;

public class IzmenaPoslasticaraController {
    
    private final IzmenaPoslasticaraForma ipf;
    private Poslasticar poslasticar;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

    public IzmenaPoslasticaraController(IzmenaPoslasticaraForma ipf, Poslasticar poslasticar) {
        this.ipf = ipf;
        this.poslasticar = poslasticar;
        
        ipf.setTitle("Izmena poslastičara");
        ipf.setLocationRelativeTo(null);
        ipf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        pripremiFormu();
        dodajActionListenere();
    }
    
    public void otvoriFormu() {
        ipf.setVisible(true);
    }

    private void pripremiFormu() {
        // Popunjavam formu podacima iz objekta poslasticar
        if (poslasticar != null) {
            ipf.getjTextFieldID().setText(String.valueOf(poslasticar.getIdPoslasticar()));
            ipf.getjTextFieldID().setEditable(false); // ID se ne menja
            ipf.getjTextFieldImePrezime().setText(poslasticar.getImePrezime());
            
            // Postavljam datum rođenja iz LocalDateTime u JTextField koristeći novi format
            if (poslasticar.getDatumRodjenja() != null) {
                ipf.getjTextFieldDatumRodj().setText(poslasticar.getDatumRodjenja().format(formatter));
            }
            
            ipf.getjTextFieldKorisnickoIme().setText(poslasticar.getKorisnickoIme());
            ipf.getjTextFieldSifra().setText(poslasticar.getSifra());
        }
    }
    
    private void dodajActionListenere() {
        ipf.addBtnSacuvajIzmeneActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sacuvajIzmene();
            }
        });
    }

    private void sacuvajIzmene() {
        try {
            // Validacija unosa
            String imePrezime = ipf.getjTextFieldImePrezime().getText().trim();
            String datumRodjenjaStr = ipf.getjTextFieldDatumRodj().getText().trim();
            String korisnickoIme = ipf.getjTextFieldKorisnickoIme().getText().trim();
            String sifra = ipf.getjTextFieldSifra().getText().trim();

            if (imePrezime.isEmpty() || datumRodjenjaStr.isEmpty() || korisnickoIme.isEmpty() || sifra.isEmpty()) {
                JOptionPane.showMessageDialog(ipf, "Sva polja moraju biti popunjena!", "Greška", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            //Parsiram string u LocalDate, jer ne unosim vreme
            LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaStr, formatter);
            
            //Pretvaram LocalDate u LocalDateTime sa početkom dana (00:00:00)
            LocalDateTime datumRodjenjaSaVremenom = datumRodjenja.atStartOfDay();
            
            //Popunjavam objekat poslasticar novim podacima
            this.poslasticar.setImePrezime(imePrezime);
            this.poslasticar.setDatumRodjenja(datumRodjenjaSaVremenom);
            this.poslasticar.setKorisnickoIme(korisnickoIme);
            this.poslasticar.setSifra(sifra);

            //Pozivam metodu za izmenu u Komunikacija klasi
            Komunikacija.getInstance().izmeniPoslasticara(this.poslasticar);
            
            // Ažuriram Cordinator objekat kako bi se promene odmah videle na glavnoj formi
            Cordinator.getInstance().setUlogovani(this.poslasticar);

            JOptionPane.showMessageDialog(ipf, "Sistem je zapamtio izmene poslastičara.", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
            Cordinator.getInstance().osveziPrikazPoslasticara();
            ipf.dispose(); // Zatvaranje forme
            
        } catch (DateTimeParseException ex) {
             JOptionPane.showMessageDialog(ipf, "Datum rođenja nije u ispravnom formatu (dd.MM.yyyy.).", "Greška pri unosu datuma", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(ipf, "Sistem ne može da zapamti izmene poslastičara.", "Greška", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
