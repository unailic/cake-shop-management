/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import java.util.List;
import model.Racun;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class PretraziRacunSO extends ApstraktnaGenerickaOperacija {

    private List<Racun> racuni;

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (!(objekat instanceof Racun)) {
            throw new Exception("Greška, prosleđeni parametar mora biti objekat klase Racun.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Racun racunZaPretragu = (Racun) objekat;
        
        StringBuilder whereKlauzula = new StringBuilder();
        
        // Uvek dodajem JOIN-ove, jer uvek ucitavam kupca i poslasticara
        whereKlauzula.append(" JOIN kupac ON racun.kupac = kupac.idKupca JOIN poslasticar ON racun.poslasticar = poslasticar.idPoslasticar");
        
        // Dodajem WHERE klauzulu, počinjem sa 1=1 da bih lakše dodavala uslove sa AND
        whereKlauzula.append(" WHERE 1=1");
        
        // Dodajem uslove na osnovu popunjenih polja
        if (racunZaPretragu.getIdRacuna() != 0) {
            whereKlauzula.append(" AND racun.idRacuna = ").append(racunZaPretragu.getIdRacuna());
        }
        
        if (racunZaPretragu.getKupac() != null) {
            whereKlauzula.append(" AND racun.kupac = ").append(racunZaPretragu.getKupac().getIdKupca());
        }
        
        if (racunZaPretragu.getPoslasticar() != null) {
            whereKlauzula.append(" AND racun.poslasticar = ").append(racunZaPretragu.getPoslasticar().getIdPoslasticar());
        }

        
        whereKlauzula.append(" AND racun.obradjen = ").append(racunZaPretragu.isObradjen() ? 1 : 0);

        try {
            racuni = broker.getAll(new Racun(), whereKlauzula.toString());
        } catch (Exception e) {
            System.out.println("Greska u PretraziRacuneSO: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }
}
