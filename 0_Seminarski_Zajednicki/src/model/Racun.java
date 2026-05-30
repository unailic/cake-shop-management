/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */

public class Racun implements ApstraktniDomenskiObjekat {
    private int idRacuna;
    private double cena;
    private boolean obradjen;
    private Poslasticar poslasticar;
    private Kupac kupac;
    private List<StavkaRacuna> stavke;

    public Racun() {}

    public Racun(int idRacuna, double cena, boolean obradjen, Poslasticar poslasticar, Kupac kupac) {
        this.idRacuna = idRacuna;
        this.cena = cena;
        this.obradjen = obradjen;
        this.poslasticar = poslasticar;
        this.kupac = kupac;
        stavke = new ArrayList<>();
    }

    public int getIdRacuna() { return idRacuna; }
    public void setIdRacuna(int idRacuna) { this.idRacuna = idRacuna; }

    public double getCena() { return cena; }
    public void setCena(double cena) { this.cena = cena; }

    public boolean isObradjen() { return obradjen; }
    public void setObradjen(boolean obradjen) { this.obradjen = obradjen; }

    public Poslasticar getPoslasticar() {
        return poslasticar;
    }

    public void setPoslasticar(Poslasticar poslasticar) {
        this.poslasticar = poslasticar;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public List<StavkaRacuna> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaRacuna> stavke) {
        this.stavke = stavke;
    }
    
    @Override
    public String toString() {
        return "Račun #" + idRacuna + " - " + cena + " RSD";
    }

    @Override
    public String vratiNazivTabele() {
        return "racun";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idR = rs.getInt("racun.idRacuna");
            double cena = rs.getDouble("racun.cena");
            boolean obradjen = rs.getBoolean("racun.obradjen");

            int idPoslasticar = rs.getInt("poslasticar.idPoslasticar");
            String imePoslasticara = rs.getString("poslasticar.imePrezime");
            String korisnickoImePoslasticara = rs.getString("poslasticar.korisnickoIme");
            Poslasticar poslasticar = new Poslasticar(idPoslasticar, imePoslasticara, null, korisnickoImePoslasticara, null);

            int idKupac = rs.getInt("kupac.idKupca");
            String imeKupca = rs.getString("kupac.imePrezime");

            Mesto mestoKupca = null;
            Kupac kupac = new Kupac(idKupac, imeKupca, mestoKupca);

            Racun r = new Racun(idR, cena, obradjen, poslasticar, kupac);
            lista.add(r);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "cena, obradjen, poslasticar, kupac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return cena+","+obradjen+","+poslasticar.getIdPoslasticar()+","+kupac.getIdKupca();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "racun.idRacuna="+idRacuna;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "cena=" + cena + ", obradjen=" + obradjen + 
               ", poslasticar=" + poslasticar.getIdPoslasticar() + 
               ", kupac=" + kupac.getIdKupca();
    }

}
