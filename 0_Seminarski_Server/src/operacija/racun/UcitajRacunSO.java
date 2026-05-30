/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.racun;

import java.util.List;
import model.Kupac;
import model.Racun;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author HP
 */
public class UcitajRacunSO extends ApstraktnaGenerickaOperacija {

    private List<Racun> listaRacuna;

    @Override
    protected void preduslovi(Object param) throws Exception {
        // Nema preduslova za ovu operaciju
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        
        String uslov = " JOIN kupac ON racun.kupac = kupac.idKupca JOIN poslasticar ON racun.poslasticar = poslasticar.idPoslasticar";
        
        try{
            listaRacuna = broker.getAll(new Racun(), uslov);
        }catch(Exception e){
            System.out.println("UcitajKupceSO: " +e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Racun> getListaRacuna() {
        return listaRacuna;
    }

}
