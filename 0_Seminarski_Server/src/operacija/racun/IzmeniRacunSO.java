/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

/**
 *
 * @author HP
 */
import model.Racun;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

public class IzmeniRacunSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Racun)) {
            throw new Exception("Sistem ne može da zapamti račun.");
        }
        Racun racun = (Racun) objekat;
        if (racun.getKupac() == null || racun.getPoslasticar() == null) {
            throw new Exception("Morate odabrati kupca i poslastičara za izmenu računa.");
        }
        if (racun.getStavke() == null || racun.getStavke().isEmpty()) {
            throw new Exception("Račun mora da sadrži barem jednu stavku.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun racun = (Racun) objekat;

        // 1. Izmeni glavni objekat (Racun)
        broker.edit(racun);
        
        broker.deleteWhere(new StavkaRacuna(), " WHERE racun=" + racun.getIdRacuna());
        
        // 2. Dodaj sve nove stavke
        for (StavkaRacuna stavka : racun.getStavke()) {
            stavka.setRacun(racun);
            broker.add(stavka);
        }
    }
}
