
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.poslasticar;

import java.util.List;
import model.Poslasticar;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class UcitajPoslasticarSO extends ApstraktnaGenerickaOperacija{
    
    List<Poslasticar> poslasticari;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        //
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        poslasticari = broker.getAll(new Poslasticar(), "");
    }

    public List<Poslasticar> getPoslasticari() {
        return poslasticari;
    }

    public void setPoslasticari(List<Poslasticar> poslasticari) {
        this.poslasticari = poslasticari;
    }
    
}
