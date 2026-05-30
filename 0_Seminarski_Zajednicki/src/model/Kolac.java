/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Objects;
import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Kolac implements ApstraktniDomenskiObjekat {
    private int sifraKolaca;
    private String naziv;
    private String opis;
    private double cena;

    public Kolac() {}

    public Kolac(int sifraKolaca, String naziv, String opis, double cena) {
        this.sifraKolaca = sifraKolaca;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }
    
    public Kolac(String naziv, String opis, double cena) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
    }

    public int getSifraKolaca() {
        return sifraKolaca;
    }

    public void setSifraKolaca(int sifraKolaca) {
        this.sifraKolaca = sifraKolaca;
    }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public double getCena() { return cena; }
    public void setCena(double cena) { this.cena = cena; }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String vratiNazivTabele() {
        return "kolac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int sifraKolaca = rs.getInt("kolac.sifraKolaca");
            String naziv = rs.getString("kolac.naziv");
            String opis = rs.getString("kolac.opis");
            double cena = rs.getDouble("kolac.cena");
            
            Kolac k = new Kolac(sifraKolaca, naziv, opis, cena);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, opis, cena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "','" + opis + "'," + cena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kolac.sifraKolaca=" + sifraKolaca;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
            int sifraKolaca = rs.getInt("kolac.sifraKolaca");
            String naziv = rs.getString("kolac.naziv");
            String opis = rs.getString("kolac.opis");
            double cena = rs.getDouble("kolac.cena");
            return new Kolac(sifraKolaca, naziv, opis, cena);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', opis='" + opis + "', cena=" + cena;
    }
}