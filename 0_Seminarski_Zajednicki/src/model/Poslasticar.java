/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Poslasticar implements ApstraktniDomenskiObjekat {
    private int idPoslasticar;
    private String imePrezime;
    private LocalDateTime datumRodjenja;
    private String korisnickoIme;
    private String sifra;
    
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Poslasticar() {}

    public Poslasticar(int idPoslasticar, String imePrezime, LocalDateTime datumRodjenja, String korisnickoIme, String sifra) {
        this.idPoslasticar = idPoslasticar;
        this.imePrezime = imePrezime;
        this.datumRodjenja = datumRodjenja;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdPoslasticar() { return idPoslasticar; }
    public void setIdPoslasticar(int idPoslasticar) { this.idPoslasticar = idPoslasticar; }

    public String getImePrezime() { return imePrezime; }
    public void setImePrezime(String imePrezime) { this.imePrezime = imePrezime; }

    public LocalDateTime getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDateTime datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getKorisnickoIme() { return korisnickoIme; }
    public void setKorisnickoIme(String korisnickoIme) { this.korisnickoIme = korisnickoIme; }

    public String getSifra() { return sifra; }
    public void setSifra(String sifra) { this.sifra = sifra; }

    @Override
    public String toString() {
        return imePrezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.korisnickoIme);
        hash = 29 * hash + Objects.hashCode(this.sifra);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Poslasticar other = (Poslasticar) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    @Override
    public String vratiNazivTabele() {
        return "poslasticar";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("poslasticar.idPoslasticar");
            String ime = rs.getString("poslasticar.imePrezime");
            LocalDateTime datum = rs.getTimestamp("poslasticar.datumRodjenja").toLocalDateTime();
            String korisnickoIme = rs.getString("poslasticar.korisnickoIme");
            String sifra = rs.getString("poslasticar.sifra");
            Poslasticar p = new Poslasticar(id, ime, datum, korisnickoIme, sifra);
            lista.add(p);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, datumRodjenja, korisnickoIme, sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+imePrezime+"','"+datumRodjenja.format(formatter)+"','"+korisnickoIme+"','"+sifra+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "poslasticar.idPoslasticar="+idPoslasticar;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime + 
               "', datumRodjenja='" + datumRodjenja.format(formatter) + 
               "', korisnickoIme='" + korisnickoIme + 
               "', sifra='" + sifra + "'";
    }

}

