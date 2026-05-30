/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.smene;

import model.Smena;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class KreirajSmenaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Smena)) {
            throw new Exception("Nisu poslati ispravni podaci za kreiranje smene.");
        }
        // Ovde možete dodati dodatnu validaciju ako je potrebno, npr. da li su popunjeni svi atributi.
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Smena)objekat);
    }
}
