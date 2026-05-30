/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kolaci;

import model.Kolac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class IzmeniKolacSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Kolac)){
            throw new Exception("Sistem ne može da doda kolač.");
        }
        Kolac k = (Kolac) objekat;
        if(k.getNaziv()==null || k.getNaziv().isEmpty()){
            throw new Exception("Greska naziv kolaca!");
        }else if(k.getOpis()==null || k.getOpis().isEmpty()){
            throw new Exception("Greska opis kolaca!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Kolac) objekat);
    }
    
}
