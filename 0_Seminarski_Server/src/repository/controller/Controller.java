/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.controller;

import operacija.kolaci.UcitajKolaceSO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Kolac;
import model.Kupac;
import model.Mesto;
import model.Poslasticar;
import model.Racun;
import model.Smena;
import model.StavkaRacuna;
import operacija.*;
import operacija.kolaci.*;
import operacija.kupci.*;
import operacija.mesto.*;
import operacija.smene.*;
import operacija.racun.*;
import operacija.StavkaRacuna.*;
import operacija.poslasticar.IzmeniPoslasticarSO;
import operacija.poslasticar.UcitajPoslasticarSO;


//import rs.ac.bg.fon.ps.repository.Repository;


/**
 *
 * @author Milos
 */
public class Controller {

    private static Controller instance;


    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Poslasticar login(Poslasticar p) throws Exception {
        LogInOperacija operacija = new LogInOperacija();
        operacija.izvrsi(p, null);
        System.out.println("Klasa controller: " + operacija.getPoslasticar());
        return operacija.getPoslasticar();
    }

    public List<Kolac> ucitajKolace() throws Exception {
        UcitajKolaceSO operacija = new UcitajKolaceSO();
        operacija.izvrsi(null, null);
        return operacija.getKolaci();
    }

    public void obrisiKolac(Kolac k) throws Exception {
        ObrisiKolacSO operacija = new ObrisiKolacSO();
        operacija.izvrsi(k, null);
    }

    public void dodajKolac(Kolac noviKolac) throws Exception {
        KreirajKolacSO operacija = new KreirajKolacSO();
        operacija.izvrsi(noviKolac, null);
    }

    public void izmeniKolac(Kolac kolacZaIzmenu) throws Exception {
        IzmeniKolacSO operacija = new IzmeniKolacSO();
        operacija.izvrsi(kolacZaIzmenu, null);
    }

    public List<Kupac> ucitajKupce() throws Exception {
        UcitajKupceSO operacija = new UcitajKupceSO();
        operacija.izvrsi(null, null);
        return operacija.getKupci();
    }

    public void obrisiKupac(Kupac ku) throws Exception {
        ObrisiKupacSO operacija = new ObrisiKupacSO();
        operacija.izvrsi(ku, null);
    }

    public List<Mesto> ucitajMesta() throws Exception {
        UcitajMestaSO operacija = new UcitajMestaSO();
        operacija.izvrsi(null, null);
        return operacija.getMesta();
    }

    public void dodajKupca(Kupac noviKupac) throws Exception {
        KreirajKupacSO operacija = new KreirajKupacSO();
        operacija.izvrsi(noviKupac, null);
    }

    public void dodajMesto(Mesto novoMesto) throws Exception {
        KreirajMestoSO operacija = new KreirajMestoSO();
        operacija.izvrsi(novoMesto, null);
    }

    public void izmeniKupac(Kupac kupacZaIzmenu) throws Exception {
        IzmeniKupacSO operacija = new IzmeniKupacSO();
        operacija.izvrsi(kupacZaIzmenu, null); 
    }

    public List<Smena> ucitajSmene() throws Exception {
        UcitajSmenaSO operacija = new UcitajSmenaSO();
        operacija.izvrsi(null, null);
        return operacija.getSmene();
    }

    public void dodajSmena(Smena novaSmena) throws Exception {
        KreirajSmenaSO operacija = new KreirajSmenaSO();
        operacija.izvrsi(novaSmena, null);
    }

    public List<Racun> ucitajRacune() throws Exception {
        UcitajRacunSO operacija = new UcitajRacunSO();
        operacija.izvrsi(null, null);
        return operacija.getListaRacuna();
    }

    public List<StavkaRacuna> ucitajStavkeRacuna(Racun racun) throws Exception {
        UcitajStavkaRacunaSO operacija = new UcitajStavkaRacunaSO();
        operacija.izvrsi(racun, null);
        return operacija.getListaStavki();
    }

    public List<Poslasticar> ucitajPoslasticare() throws Exception {
        UcitajPoslasticarSO operacija = new UcitajPoslasticarSO();
        operacija.izvrsi(null, null);
        return operacija.getPoslasticari();
    }
    
    public void dodajRacun(Racun noviRacun) throws Exception {
        KreirajRacunSO operacija = new KreirajRacunSO();
        operacija.izvrsi(noviRacun, null);
    }

    public List<Racun> pretraziRacune(Racun racunZaPretragu) throws Exception {
        PretraziRacunSO operacija = new PretraziRacunSO();
        operacija.izvrsi(racunZaPretragu, null);
        return operacija.getRacuni();
    }
    
    public void izmeniRacun(Racun racunZaIzmenu) throws Exception {
        IzmeniRacunSO operacija = new IzmeniRacunSO();
        operacija.izvrsi(racunZaIzmenu, null);
    }

    public void izmeniPoslasticara(Poslasticar poslasticarZaIzmenu) throws Exception {
        IzmeniPoslasticarSO operacija = new IzmeniPoslasticarSO();
        operacija.izvrsi(poslasticarZaIzmenu, null);
    }
    

}
