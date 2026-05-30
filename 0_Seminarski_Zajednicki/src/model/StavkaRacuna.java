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
public class StavkaRacuna implements ApstraktniDomenskiObjekat {
    private int rbSR;
    private int kolicina;
    private double cenaSR;
    private Kolac kolac;
    private Racun racun;
    
    public StavkaRacuna() {}

    public StavkaRacuna(int rbSR, int kolicina, double cenaSR, Kolac kolac, Racun racun) {
        this.rbSR = rbSR;
        this.kolicina = kolicina;
        this.cenaSR = cenaSR;
        this.kolac = kolac;
        this.racun = racun;
    }

    public int getRbSR() {
        return rbSR;
    }

    public void setRbSR(int rbSR) {
        this.rbSR = rbSR;
    }

    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }

    public double getCenaSR() { return cenaSR; }
    public void setCenaSR(double cenaSR) { this.cenaSR = cenaSR; }

    public Kolac getKolac() { return kolac; }
    public void setKolac(Kolac kolac) { this.kolac = kolac; }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }
    
    @Override
    public String toString() {
        return kolicina + " x " + kolac.getNaziv() + " - " + cenaSR + " RSD";
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaracuna";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("stavkaracuna.rbSR");
            int kolicina = rs.getInt("stavkaracuna.kolicina");
            double cena = rs.getDouble("stavkaracuna.cenaSR");
            int sifraKolaca = rs.getInt("kolac.sifraKolaca");
            String nazivKolaca = rs.getString("kolac.naziv");
            String opisKolaca = rs.getString("kolac.opis");
            double cenaKolaca = rs.getDouble("kolac.cena");
            Kolac kolac = new Kolac(sifraKolaca, nazivKolaca, opisKolaca, cenaKolaca);
            StavkaRacuna stavka = new StavkaRacuna(rb, kolicina, cena, kolac, null);
            lista.add(stavka);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "kolicina, cenaSR, racun, kolac";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return kolicina + "," + cenaSR + "," + racun.getIdRacuna() + ", " + kolac.getSifraKolaca();
    }
    
    @Override
    public String vratiPrimarniKljuc() {
        return "rbSR=" + rbSR + " AND racun=" + racun.getIdRacuna();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
            int rb = rs.getInt("rbSR");
            int kolicina = rs.getInt("kolicina");
            double cena = rs.getDouble("cenaSR");
            int sifraKolaca = rs.getInt("kolac");
            Kolac k = new Kolac();
            k.setSifraKolaca(sifraKolaca);
            int idRacuna = rs.getInt("racun");
            Racun r = new Racun();
            r.setIdRacuna(idRacuna);
            return new StavkaRacuna(rb, kolicina, cena, k, r);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "kolicina=" + kolicina + ", cenaSR=" + cenaSR +
                 ", kolac=" + kolac.getSifraKolaca();
    }
}


