/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domen.ApstraktniDomenskiObjekat;
import java.io.Serializable;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Time;

/**
 *
 * @author HP
 */
public class Smena implements ApstraktniDomenskiObjekat {
    private int rbSmene;
    private String nazivSmene;
    private LocalTime pocetakSmene;
    private LocalTime krajSmene;

    public Smena() {}

    public Smena(int rbSmene, String nazivSmene, LocalTime pocetakSmene, LocalTime krajSmene) {
        this.rbSmene = rbSmene;
        this.nazivSmene = nazivSmene;
        this.pocetakSmene = pocetakSmene;
        this.krajSmene = krajSmene;
    }

    public int getRbSmene() { return rbSmene; }
    public void setRbSmene(int rbSmene) { this.rbSmene = rbSmene; }

    public String getNazivSmene() { return nazivSmene; }
    public void setNazivSmene(String nazivSmene) { this.nazivSmene = nazivSmene; }

    public LocalTime getPocetakSmene() {
        return pocetakSmene;
    }

    public void setPocetakSmene(LocalTime pocetakSmene) {
        this.pocetakSmene = pocetakSmene;
    }

    public LocalTime getKrajSmene() {
        return krajSmene;
    }

    public void setKrajSmene(LocalTime krajSmene) {
        this.krajSmene = krajSmene;
    }


    @Override
    public String toString() {
        return nazivSmene;
    }

    @Override
    public String vratiNazivTabele() {
        return "smena";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("smena.rbSmene");
            String naziv = rs.getString("smena.nazivSmene");
            Time tPocetak = rs.getTime("smena.pocetakSmene");
            Time tKraj = rs.getTime("smena.krajSmene");
            LocalTime pocetak = (tPocetak != null) ? tPocetak.toLocalTime() : null;
            LocalTime kraj = (tKraj != null) ? tKraj.toLocalTime() : null;

            Smena s = new Smena(rb, naziv, pocetak, kraj);
            lista.add(s);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivSmene, pocetakSmene, krajSmene";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivSmene+"','"+pocetakSmene.toString()+"','"+krajSmene.toString()+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "smena.rbSmene="+rbSmene;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivSmene='" + nazivSmene + 
               "', pocetakSmene='" + pocetakSmene.toString() + 
               "', krajSmene='" + krajSmene.toString() + "'";
    }

}
