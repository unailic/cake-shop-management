/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.util.List;
import model.Poslasticar;

/**
 *
 * @author HP
 */
public class LogInOperacija extends ApstraktnaGenerickaOperacija{
    Poslasticar poslasticar;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Poslasticar)){
            throw new Exception("Korisničko ime i šifra nisu ispravni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<Poslasticar> sviPoslasticari = broker.getAll((Poslasticar) objekat, null);
        System.out.println("Klasa LogInOperacija SO: "+sviPoslasticari);
        
        if(sviPoslasticari.contains((Poslasticar) objekat)){
            for (Poslasticar p : sviPoslasticari) {
                if(p.equals((Poslasticar) objekat)){
                    poslasticar = p;
                    return;
                }
            }
        }else{
            poslasticar = null;
        }
        
    }

    public Poslasticar getPoslasticar() {
        return poslasticar;
    }

    public void setPoslasticar(Poslasticar poslasticar) {
        this.poslasticar = poslasticar;
    }
    
}
