/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import komunikacija.Posiljalac;
import model.*;
import cordinator.Cordinator;

/**
 *
 * @author HP
 */
public class LogInController {
    private final LogInForma lf;

    public LogInController(LogInForma lf) {
        this.lf = lf;
        lf.setTitle("Prijava poslastičara");
        lf.setLocationRelativeTo(null);
        addActionListeners();
    }

    private void addActionListeners() {
        lf.loginAddActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String ki = lf.getjTextFieldUsername().getText().trim();
                String pass = String.valueOf(lf.getjPasswordField().getPassword()).trim();
                
                Komunikacija.getInstance().konekcija();
                Poslasticar ulogovani = Komunikacija.getInstance().logIn(ki, pass);
                
                if(ulogovani == null){
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra nisu ispravni.", "Greska!", JOptionPane.ERROR_MESSAGE);
                }else{
                    Cordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisnicko ime i sifra su ispravni.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                    try{
                        Cordinator.getInstance().otvoriGlavnuFormu();
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(lf, "Ne moze da se otvori glavna forma i meni.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    }
                    lf.dispose();
                }
            }
            
        });
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }

}
