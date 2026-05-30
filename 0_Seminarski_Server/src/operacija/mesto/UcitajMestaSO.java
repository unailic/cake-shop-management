/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import java.util.List;
import model.Kolac;
import model.Mesto;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class UcitajMestaSO extends ApstraktnaGenerickaOperacija{
    List<Mesto> mesta;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        mesta = broker.getAll(new Mesto(), "");
    }

    public List<Mesto> getMesta() {
        return mesta;
    }

    public void setMesta(List<Mesto> mesta) {
        this.mesta = mesta;
    }

}
