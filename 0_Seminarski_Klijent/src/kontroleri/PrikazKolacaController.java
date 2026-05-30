/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import forme.*;
import model.Kolac;
import java.util.*;
import komunikacija.Komunikacija;
import forme.model.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import cordinator.Cordinator;
import javax.swing.JFrame;

/**
 *
 * @author HP
 */
public class PrikazKolacaController {
    private final PrikazKolacaForma pkf;

    public PrikazKolacaController(PrikazKolacaForma pkf) {
        this.pkf = pkf;
        pkf.setLocationRelativeTo(null);
        pkf.setTitle("Prikaz kolača");
        pkf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Kolac> kolaci = Komunikacija.getInstance().ucitajKolace();
        ModelTabeleKolaci mtk = new ModelTabeleKolaci(kolaci);
        pkf.getjTablePrikazKolaca().setModel(mtk);
    }

    private void addActionListener() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTablePrikazKolaca().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pkf, "Nije odabran ni jedan kolač za brisanje!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    ModelTabeleKolaci mtk = (ModelTabeleKolaci) pkf.getjTablePrikazKolaca().getModel();
                    Kolac k = mtk.getLista().get(red);
                    try{
                        Komunikacija.getInstance().obrisiKolac(k);
                        JOptionPane.showMessageDialog(pkf, "Sistem je obrisao kolac.", "Uspeh!", JOptionPane.INFORMATION_MESSAGE);
                        pripremiFormu();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise kolac.", "Greska!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        pkf.addBtnIzmeniActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = pkf.getjTablePrikazKolaca().getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(pkf, "Nije odabran ni jedan kolač za izmenu!", "Upozorenje!", JOptionPane.WARNING_MESSAGE);
                }else{
                    ModelTabeleKolaci mtk = (ModelTabeleKolaci) pkf.getjTablePrikazKolaca().getModel();
                    Kolac k = mtk.getLista().get(red);
                    Cordinator.getInstance().dodajParam("kolac", k);
                    Cordinator.getInstance().otvoriIzmeniKolacFormu();
                }
            }
        });
        
        pkf.addBtnPretraziActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String naziv = pkf.getjTextFieldPretragaNaziv().getText().trim();
                String opis = pkf.getjTextFieldPretragaOpis().getText().trim();
                
                ModelTabeleKolaci mtk = (ModelTabeleKolaci) pkf.getjTablePrikazKolaca().getModel();
                mtk.pretrazi(naziv, opis);
            }
        });
        
        pkf.addBtnResetujActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pkf.getjTextFieldPretragaNaziv().setText("");
                pkf.getjTextFieldPretragaOpis().setText("");
                pripremiFormu();
            }
        });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
}
