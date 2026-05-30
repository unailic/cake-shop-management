/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import java.sql.Time;
import java.time.LocalTime;

/**
 *
 * @author HP
 */
public class PoslasticarSmena implements ApstraktniDomenskiObjekat {
    private LocalDateTime datumPoslasticarSmena;
    private Poslasticar poslasticar;
    private Smena smena;

    public PoslasticarSmena() {}

    public PoslasticarSmena(LocalDateTime datumPoslasticarSmena, Poslasticar poslasticar, Smena smena) {
        this.datumPoslasticarSmena = datumPoslasticarSmena;
        this.poslasticar = poslasticar;
        this.smena = smena;
    }

    public LocalDateTime getDatumPoslasticarSmena() {
        return datumPoslasticarSmena;
    }

    public void setDatumPoslasticarSmena(LocalDateTime datumPoslasticarSmena) {
        this.datumPoslasticarSmena = datumPoslasticarSmena;
    }

    public Poslasticar getPoslasticar() { return poslasticar; }
    public void setPoslasticar(Poslasticar poslasticar) { this.poslasticar = poslasticar; }

    public Smena getSmena() { return smena; }
    public void setSmena(Smena smena) { this.smena = smena; }

    @Override
    public String vratiNazivTabele() {
        return "poslasticarsmena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            LocalDateTime datum = rs.getTimestamp("poslasticarSmena.datumPoslasticarSmena").toLocalDateTime();

            int idPoslasticar = rs.getInt("poslasticar.idPoslasticar");
            String imePrezime = rs.getString("poslasticar.imePrezime");
            LocalDateTime datumRodjenja = null;
            if (rs.getTimestamp("poslasticar.datumRodjenja") != null) {
                datumRodjenja = rs.getTimestamp("poslasticar.datumRodjenja").toLocalDateTime();
            }
            String korisnickoIme = rs.getString("poslasticar.korisnickoIme");
            String sifra = rs.getString("poslasticar.sifra");
            Poslasticar poslasticar = new Poslasticar(idPoslasticar, imePrezime, datumRodjenja, korisnickoIme, sifra);

            int rbSmene = rs.getInt("smena.rbSmene");
            String nazivSmene = rs.getString("smena.nazivSmene");
            Time tPocetak = rs.getTime("smena.pocetakSmene");
            Time tKraj = rs.getTime("smena.krajSmene");
            LocalTime pocetakSmene = tPocetak != null ? tPocetak.toLocalTime() : null;
            LocalTime krajSmene = tKraj != null ? tKraj.toLocalTime() : null;
            Smena smena = new Smena(rbSmene, nazivSmene, pocetakSmene, krajSmene);

            PoslasticarSmena ps = new PoslasticarSmena(datum, poslasticar, smena);
            lista.add(ps);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "poslasticar, smena, datumPoslasticarSmena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return poslasticar.getIdPoslasticar() + "," + smena.getRbSmene() +",'"+datumPoslasticarSmena+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "poslasticar="+poslasticar.getIdPoslasticar()+" AND smena="+smena.getRbSmene();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "datumPoslasticarSmena='" + datumPoslasticarSmena.toString() + "'";
    }
}
