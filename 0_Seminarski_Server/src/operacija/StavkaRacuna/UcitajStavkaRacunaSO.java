/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.StavkaRacuna;

import java.util.List;
import model.Racun;
import model.StavkaRacuna;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class UcitajStavkaRacunaSO extends ApstraktnaGenerickaOperacija {

    private List<StavkaRacuna> listaStavki;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (!(param instanceof Racun)) {
            throw new Exception("Nije poslat ispravan objekat za pretragu stavki računa.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        Racun racun = (Racun) param;
        String uslov = " JOIN kolac ON stavkaracuna.kolac = kolac.sifraKolaca WHERE stavkaracuna.racun = " + racun.getIdRacuna();
        this.listaStavki = broker.getAll(new StavkaRacuna(), uslov);
    }

    public List<StavkaRacuna> getListaStavki() {
        return listaStavki;
    }

}
