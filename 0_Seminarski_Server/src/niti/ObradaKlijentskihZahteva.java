/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.net.*;
import model.*;
import komunikacija.*;
import domen.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static komunikacija.Operacija.DODAJ_KOLAC;
import static komunikacija.Operacija.UCITAJ_RACUNE;
import operacija.racun.UcitajRacunSO;
import operacija.StavkaRacuna.*;
import repository.controller.Controller;

/**
 *
 * @author HP
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket s;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
        posiljalac = new Posiljalac(s);
        primalac = new Primalac(s);
    }

    @Override
    public void run() {
        try{
            while(!kraj){
                Zahtev zahtev = (Zahtev)primalac.primi();
                Odgovor odgovor = new Odgovor();

                switch (zahtev.getOperacija()) {
                    case LOGIN:
                        try {
                            Poslasticar p = (Poslasticar) zahtev.getParametar();
                            p = Controller.getInstance().login(p);
                            odgovor.setOdgovor(p);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UCITAJ_KOLACE:
                        try {
                            List<Kolac> kolaci = Controller.getInstance().ucitajKolace();
                            odgovor.setOdgovor(kolaci);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case OBRISI_KOLAC:
                        try{
                            Kolac k = (Kolac) zahtev.getParametar();
                            Controller.getInstance().obrisiKolac(k);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_KOLAC:
                        try {
                            Kolac noviKolac = (Kolac) zahtev.getParametar();
                            Controller.getInstance().dodajKolac(noviKolac);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case IZMENI_KOLAC:
                        try {
                            Kolac kolacZaIzmenu = (Kolac) zahtev.getParametar();
                            Controller.getInstance().izmeniKolac(kolacZaIzmenu);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case UCITAJ_KUPCE:
                        try {
                            List<Kupac> kupci = Controller.getInstance().ucitajKupce();
                            odgovor.setOdgovor(kupci);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case OBRISI_KUPAC:
                        try{
                            Kupac ku = (Kupac) zahtev.getParametar();
                            Controller.getInstance().obrisiKupac(ku);
                            odgovor.setOdgovor(null);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case UCITAJ_MESTA:
                        try {
                            List<Mesto> mesta = Controller.getInstance().ucitajMesta();
                            odgovor.setOdgovor(mesta);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_KUPCA:
                        try{
                            Kupac noviKupac = (Kupac) zahtev.getParametar();
                            Controller.getInstance().dodajKupca(noviKupac);
                            System.out.println(noviKupac);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                            System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                        }
                        break;
                    case DODAJ_MESTO:
                    try {
                        Mesto novoMesto = (Mesto) zahtev.getParametar();
                        Controller.getInstance().dodajMesto(novoMesto);
                        odgovor.setOdgovor(null);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                    }
                    break;
                    case IZMENI_KUPAC:
                    try {
                        Kupac kupacZaIzmenu = (Kupac) zahtev.getParametar();
                        Controller.getInstance().izmeniKupac(kupacZaIzmenu);
                        odgovor.setOdgovor(null); // USPEH! Postavljamo null kao "dobar" odgovor
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                    }
                    break;
                    case UCITAJ_SMENE:
                        try {
                            List<Smena> smene = Controller.getInstance().ucitajSmene();
                            odgovor.setOdgovor(smene);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                        }
                        break;
                    case DODAJ_SMENU:
                        try {
                            Smena novaSmena = (Smena) zahtev.getParametar();
                            Controller.getInstance().dodajSmena(novaSmena);
                            odgovor.setOdgovor(null);
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex);
                            System.out.println("Greska, klasa ObradaKZ: " + ex.getMessage());
                        }
                        break;
                case UCITAJ_RACUNE:
                    try {
                        List<Racun> racuni = Controller.getInstance().ucitajRacune();
                        odgovor.setOdgovor(racuni);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                case UCITAJ_STAVKE_RACUNA:
                    try {
                        List<StavkaRacuna> stavke = Controller.getInstance().ucitajStavkeRacuna((Racun)zahtev.getParametar());
                        odgovor.setOdgovor(stavke);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                case UCITAJ_POSLASTICARE:
                    try {
                        List<Poslasticar> poslasticari = Controller.getInstance().ucitajPoslasticare();
                        odgovor.setOdgovor(poslasticari);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                    }
                    break;
                    case KREIRAJ_RACUN: 
                        try {
                            Racun noviRacun = (Racun) zahtev.getParametar();
                            Controller.getInstance().dodajRacun(noviRacun);
                            odgovor.setOdgovor(null); 
                        } catch (Exception ex) {
                            odgovor.setOdgovor(ex); 
                            System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                        }
                        break;
                case PRETRAZI_RACUNE:
                    try {
                        Racun racunZaPretragu = (Racun) zahtev.getParametar();
                        List<Racun> filtriraniRacuni = Controller.getInstance().pretraziRacune(racunZaPretragu);
                        odgovor.setOdgovor(filtriraniRacuni);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                    }
                    break;
                case IZMENI_RACUN:
                    try {
                        Racun racunZaIzmenu = (Racun) zahtev.getParametar();
                        Controller.getInstance().izmeniRacun(racunZaIzmenu);
                        odgovor.setOdgovor(null); 
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex); 
                        System.out.println("Greska, klasa ObradaKlijentskihZahteva: " + ex.getMessage());
                    }
                    break;
                case IZMENI_POSLASTICAR:
                    try {
                        Poslasticar poslasticarZaIzmenu = (Poslasticar) zahtev.getParametar();
                        Controller.getInstance().izmeniPoslasticara(poslasticarZaIzmenu);
                        odgovor.setOdgovor(null);
                    } catch (Exception ex) {
                        odgovor.setOdgovor(ex);
                        System.out.println("Greška prilikom izmene poslastičara: " + ex.getMessage());
                    }
                    break;
                    default:
                        System.out.println("Greska! Operacija ne postoji.");
                }
                posiljalac.posalji(odgovor);
            }
        }catch(Exception e){
//            e.printStackTrace();
            System.out.println("Klijent je prekinuo konekciju: "+e.getMessage());
        }
    }
    
    public void prekini(){
        kraj = true;
        try {
            s.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        interrupt();
    }
  
}
