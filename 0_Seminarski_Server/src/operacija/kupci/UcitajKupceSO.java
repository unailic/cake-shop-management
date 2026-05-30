/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kupci;

import java.util.List;
import model.Kupac;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class UcitajKupceSO extends ApstraktnaGenerickaOperacija{
    
    List<Kupac> kupci;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov = " JOIN mesto ON kupac.mesto = mesto.idMesta";
        
        try{
            kupci = broker.getAll(new Kupac(), uslov);
        }catch(Exception e){
            System.out.println("UcitajKupceSO: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(List<Kupac> kupci) {
        this.kupci = kupci;
    }
    
}
