/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.kolaci;

import model.Kolac;
import java.util.*;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class UcitajKolaceSO extends ApstraktnaGenerickaOperacija{
    List<Kolac> kolaci;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        kolaci = broker.getAll(new Kolac(), "");
    }

    public List<Kolac> getKolaci() {
        return kolaci;
    }

    public void setKolaci(List<Kolac> kolaci) {
        this.kolaci = kolaci;
    }
    
}
