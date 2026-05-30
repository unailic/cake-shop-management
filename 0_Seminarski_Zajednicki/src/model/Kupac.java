/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Kupac implements ApstraktniDomenskiObjekat {
    private int idKupca;
    private String imePrezime;
    private Mesto mesto;

    public Kupac() {}

    public Kupac(int idKupca, String imePrezime, Mesto mesto) {
        this.idKupca = idKupca;
        this.imePrezime = imePrezime;
        this.mesto = mesto;
    }

    public int getIdKupca() { return idKupca; }
    public void setIdKupca(int idKupca) { this.idKupca = idKupca; }

    public String getImePrezime() { return imePrezime; }
    public void setImePrezime(String imePrezime) { this.imePrezime = imePrezime; }

    public Mesto getMesto() { return mesto; }
    public void setMesto(Mesto mesto) { this.mesto = mesto; }

    @Override
    public String toString() {
        return imePrezime;
    }
    
    @Override
    public String vratiNazivTabele() {
        return "kupac";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("kupac.idKupca");
            String ime = rs.getString("kupac.imePrezime");
            
            int idMesta = rs.getInt("mesto.idMesta");
            String nazivMesta = rs.getString("mesto.nazivMesta");
            Mesto mesto = new Mesto(idMesta, nazivMesta);
            
            Kupac k = new Kupac(id, ime, mesto);
            lista.add(k);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "imePrezime, mesto";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+imePrezime+"',"+mesto.getIdMesta();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kupac.idKupca="+idKupca;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "imePrezime='" + imePrezime + "', mesto=" + mesto.getIdMesta();
    }

}
