/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

import forme.DodajKolacForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.*;
import forme.PrikazKolacaForma;
import forme.PrikazKupacaForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.*;
import model.Poslasticar;
import model.Racun;
import forme.IzmenaPoslasticaraForma;

/**
 *
 * @author HP
 */
public class Cordinator {
    private static Cordinator instance;
    private Poslasticar ulogovani;
    private LogInController logInController;
    private GlavnaFormaController glavnaFormaController;
    private PrikazKolacaController prikazKolacaController;
    private DodajKolacController dkController;
    private DodajKupcaController dkuController;
    private DodajMestoController dMController;
    private PrikazSmenaController psController;
    private DodajSmenuController dsController;
    private PrikazRacunaController prController;
    private PrikazStavkiRacunaController psrController;
    private DodajRacunController drController;
    private IzmenaPoslasticaraController ipController;
    private Map<String, Object> parametri;
    
    private PrikazKupacaController prikazKupacaController;

    private Cordinator() {
        parametri = new HashMap<>();
    }

    public static Cordinator getInstance() {
        if(instance == null)
            instance = new Cordinator();
        return instance;
    }

    public Poslasticar getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Poslasticar ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriLogInFormu() {
        logInController = new LogInController(new LogInForma());
        logInController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController = new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }
    
    public void otvoriDodajKolacFormu() {
        dkController = new DodajKolacController(new DodajKolacForma());
        dkController.otvoriFormu(FormaMod.DODAJ);
    }
    
    public void otvoriPrikazKolacaFormu() {
        prikazKolacaController = new PrikazKolacaController(new PrikazKolacaForma());
        prikazKolacaController.otvoriFormu();
    }

    public void otvoriIzmeniKolacFormu() {
        dkController = new DodajKolacController(new DodajKolacForma());
        dkController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        prikazKolacaController.osveziFormu();
    }
    
    public void otvoriPrikazKupacaFormu() {
        prikazKupacaController = new PrikazKupacaController(new PrikazKupacaForma());
        prikazKupacaController.otvoriFormu();
    }

    public void otvoriDodajKupcaFormu() {
        dkuController = new DodajKupcaController(new DodajKupcaForma());
        dkuController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriDodajMestoFormu() {
        dMController = new DodajMestoController(new DodajMestoForma(), dkuController);
        dMController.otvoriFormu();
    }

    public void otvoriIzmeniKupacFormu() {
        dkuController = new DodajKupcaController(new DodajKupcaForma());
        dkuController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuKupac() {
        prikazKupacaController.osveziFormu();
    }

    public void otvoriPrikaziSmeneFormu() {
        psController = new PrikazSmenaController(new PrikazSmenaForma());
        psController.otvoriFormu();
    }

    public void otvoriDodajSmenaFormu() {
        dsController = new DodajSmenuController(new DodajSmenuForma());
        dsController.otvoriFormu();
    }
    
    public void otvoriPrikazRacunaFormu() {
        prController = new PrikazRacunaController(new PrikazRacunaForma());
        prController.otvoriFormu();
    }

    public void otvoriPrikazStavkiRFormu(Racun odabraniRacun) {
        psrController = new PrikazStavkiRacunaController(new PrikazStavkiRacunaForma(), odabraniRacun);
        psrController.otvoriFormu();
    }

    public void otvoriDodajracunFormu() throws Exception {
        drController = new DodajRacunController(new DodajRacunForma(), FormaMod.DODAJ, null);
        drController.otvoriFormu();
    }
    
    public void otvoriIzmeniRacunFormu(Racun odabraniRacun) throws Exception {
        drController = new DodajRacunController(new DodajRacunForma(), FormaMod.IZMENI, odabraniRacun);
        drController.otvoriFormu();
    }
    
    public void vratiSeNaGlavnuFormu() {
        if (prController != null) {
            prController.osveziTabelu();
        }
    }
    
    public void otvoriIzmeniPoslasticaraFormu(Poslasticar poslasticarZaIzmenu) {
        IzmenaPoslasticaraForma ipf = new IzmenaPoslasticaraForma();
        ipController = new IzmenaPoslasticaraController(ipf, poslasticarZaIzmenu);
        ipController.otvoriFormu();
    }
    
    public void osveziPrikazPoslasticara() {
        if (glavnaFormaController != null) {
            glavnaFormaController.osveziPrikazUlogovanogPoslasticara();
        }
    }
}
