/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.poslasticar;

/**
 *
 * @author HP
 */

import model.Poslasticar;
import operacija.ApstraktnaGenerickaOperacija;

public class IzmeniPoslasticarSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (objekat == null || !(objekat instanceof Poslasticar)) {
            throw new Exception("Sistem ne može da izmeni poslastičara. Greška u prosleđenom parametru.");
        }
        Poslasticar p = (Poslasticar) objekat;
        
        if (p.getImePrezime() == null || p.getImePrezime().isEmpty()) {
            throw new Exception("Ime i prezime poslastičara ne sme biti prazno!");
        }
        if (p.getKorisnickoIme() == null || p.getKorisnickoIme().isEmpty()) {
            throw new Exception("Korisničko ime poslastičara ne sme biti prazno!");
        }
        if (p.getSifra() == null || p.getSifra().isEmpty()) {
            throw new Exception("Šifra poslastičara ne sme biti prazna!");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit((Poslasticar) objekat);
    }
}
