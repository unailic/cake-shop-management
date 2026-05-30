/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smene;

import model.Smena;
import operacija.ApstraktnaGenerickaOperacija;
import java.util.*;

/**
 *
 * @author HP
 */
public class UcitajSmenaSO extends ApstraktnaGenerickaOperacija{
    
    List<Smena> smene;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        smene = broker.getAll(new Smena(), null);
    }

    public List<Smena> getSmene() {
        return smene;
    }

    public void setSmene(List<Smena> smene) {
        this.smene = smene;
    }
    
}
