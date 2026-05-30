/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.*;
import model.*;
import komunikacija.Komunikacija;
import cordinator.Cordinator;

/**
 *
 * @author HP
 */
public class Komunikacija {
    private Socket socket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    private Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if(instance == null)
            instance = new Komunikacija();
        return instance;
    }
    
    public void konekcija(){
        try {
            socket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(socket);
            primalac = new Primalac(socket);
        } catch (IOException ex) {
            System.out.println("Greska pri povezivanju sa serverom!");
        }
    }

    public Poslasticar logIn(String ki, String pass) {
        Poslasticar p = new Poslasticar();
        p.setKorisnickoIme(ki);
        p.setSifra(pass);
        
        Zahtev zahtev = new Zahtev(Operacija.LOGIN, p);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        p = (Poslasticar) odgovor.getOdgovor();
        return p;
    }

    public List<Kolac> ucitajKolace() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KOLACE, null);
        List<Kolac> kolaci = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        kolaci = (List<Kolac>) odgovor.getOdgovor();
        return kolaci;
    }

    public void obrisiKolac(Kolac k) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KOLAC, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Sistem je obrisao kolac.");
        }else{
            System.out.println("Greska!");
            ((Exception)odgovor.getOdgovor()).getMessage();
            throw new Exception("Greska!");
        }
    }

    public void dodajKolac(Kolac noviKolac) {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KOLAC, noviKolac);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }else{
            System.out.println("Greska!");
        }
    }

    public void izmeniKolac(Kolac noviKolac) {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KOLAC, noviKolac);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
            Cordinator.getInstance().osveziFormu();
        }else{
            System.out.println("Greska!");
        }
    }

    public List<Kupac> ucitajKupce() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_KUPCE, null);
        List<Kupac> kupci = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        kupci = (List<Kupac>) odgovor.getOdgovor();
        return kupci;
    }

    public void obrisiKupca(Kupac ku) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.OBRISI_KUPAC, ku);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        if(odgovor.getOdgovor() == null){
            System.out.println("Sistem je obrisao kupca.");
        }else{
            System.out.println("Greska!");
            ((Exception)odgovor.getOdgovor()).printStackTrace();
            throw new Exception("Greska!");
        }
    }

    public List<Mesto> ucitajMesta() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_MESTA, null);
        List<Mesto> mesta = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        mesta = (List<Mesto>) odgovor.getOdgovor();
        return mesta;
    }

    public void dodajKupca(Kupac noviKupac) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_KUPCA, noviKupac);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }
    }

    public void dodajMesto(Mesto novoMesto) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_MESTO, novoMesto);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }
    }

    public void izmeniKupca(Kupac noviKupac) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_KUPAC, noviKupac);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
            Cordinator.getInstance().osveziFormuKupac();
        }
    }

    public List<Smena> ucitajSmene() {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_SMENE, null);
        List<Smena> smene = new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        Odgovor odgovor = (Odgovor) primalac.primi();
        smene = (List<Smena>) odgovor.getOdgovor();
        return smene;
    }
    
    public void dodajSmenu(Smena smena) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.DODAJ_SMENU, smena);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }
    }

    public List<Racun> ucitajListuRacuna() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_RACUNE, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<Racun>) odgovor.getOdgovor();
    }

    public List<StavkaRacuna> ucitajStavkeRacuna(Racun racun) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_STAVKE_RACUNA, racun);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<StavkaRacuna>) odgovor.getOdgovor();
    }

    public List<Poslasticar> ucitajPoslasticare() throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.UCITAJ_POSLASTICARE, null);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<Poslasticar>) odgovor.getOdgovor();
    }
    
    
    public void kreirajRacun(Racun racun) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.KREIRAJ_RACUN, racun);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        // Provera da li je serverska strana vratila izuzetak
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        // Ako nije, sve je u redu
        if (odgovor.getOdgovor() == null) {
            System.out.println("Sistem je uspešno kreirao račun.");
        }
    }
    
    public List<Racun> pretraziRacune(Racun racunZaPretragu) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.PRETRAZI_RACUNE, racunZaPretragu);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        return (List<Racun>) odgovor.getOdgovor();
    }
    
    public void izmeniRacun(Racun racun) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_RACUN, racun);
        posiljalac.posalji(zahtev);
        
        Odgovor odgovor = (Odgovor) primalac.primi();
        
        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }

        if(odgovor.getOdgovor() == null){
            System.out.println("Uspeh!");
        }
    }
    
    public void izmeniPoslasticara(Poslasticar poslasticar) throws Exception {
        Zahtev zahtev = new Zahtev(Operacija.IZMENI_POSLASTICAR, poslasticar);
        posiljalac.posalji(zahtev);

        Odgovor odgovor = (Odgovor) primalac.primi();

        if (odgovor.getOdgovor() instanceof Exception) {
            throw (Exception) odgovor.getOdgovor();
        }
    }
    
}