/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import model.Kolac;
import model.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class ObrisiKupacSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat == null || !(objekat instanceof Kupac)){
            throw new Exception("Sistem ne može da obriše kupca.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Kupac) objekat);
    }
    
}
