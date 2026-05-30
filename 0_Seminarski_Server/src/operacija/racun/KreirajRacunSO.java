/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

/**
 *
 * @author HP
 */
import domen.ApstraktniDomenskiObjekat;
import model.Racun;
import model.StavkaRacuna;
import java.util.List;
import model.Racun;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

public class KreirajRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Racun)) {
            throw new Exception("Poslati objekat nije tipa Racun!");
        }

        Racun racun = (Racun) objekat;
        if (racun.getStavke() == null || racun.getStavke().isEmpty()) {
            throw new Exception("Račun mora da sadrži barem jednu stavku.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun racun = (Racun) objekat;
        
        // 1. Sačuvaj glavni objekat Racun i dobij automatski generisani ID
        int idRacuna = broker.addReturnKey(racun);
        racun.setIdRacuna(idRacuna);
        
        System.out.println("Novi ID računa je: " + racun.getIdRacuna());
        
        // 2. Postavi ID računa na svaku stavku i sačuvaj je
        for (StavkaRacuna stavka : racun.getStavke()) {
            stavka.setRacun(racun);
            broker.add(stavka);
        }
    }
    
}
