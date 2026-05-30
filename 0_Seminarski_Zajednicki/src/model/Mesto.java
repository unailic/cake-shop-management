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
public class Mesto implements ApstraktniDomenskiObjekat {
    private int idMesta;
    private String nazivMesta;

    public Mesto() {}

    public Mesto(int idMesta, String nazivMesta) {
        this.idMesta = idMesta;
        this.nazivMesta = nazivMesta;
    }

    public int getIdMesta() { return idMesta; }
    public void setIdMesta(int idMesta) { this.idMesta = idMesta; }

    public String getNazivMesta() { return nazivMesta; }
    public void setNazivMesta(String nazivMesta) { this.nazivMesta = nazivMesta; }

    @Override
    public String toString() {
        return nazivMesta;
    }

    @Override
    public String vratiNazivTabele() {
        return "mesto";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("mesto.idMesta");
            String naziv = rs.getString("mesto.nazivMesta");
            Mesto m = new Mesto(id, naziv);
            lista.add(m);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "nazivMesta";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+nazivMesta+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "mesto.idMesta="+idMesta;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "nazivMesta='" + nazivMesta + "'";
    }

}
    

