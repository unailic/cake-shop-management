/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import javax.swing.table.AbstractTableModel;
import model.Kolac;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author HP
 */
public class ModelTabeleKolaci extends AbstractTableModel {
    
    List<Kolac> lista;
    String[] kolone = {"Šifra", "Naziv", "Opis", "Cena"};

    public ModelTabeleKolaci(List<Kolac> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kolac k = lista.get(rowIndex);
        switch (columnIndex) {
            case 0: return k.getSifraKolaca();
            case 1: return k.getNaziv();
            case 2: return k.getOpis();
            case 3: return k.getCena();
            default: return "N/A";
        }
    }

    public List<Kolac> getLista() {
        return lista;
    }

    public void pretrazi(String naziv, String opis) {
        List<Kolac> filteredList = lista.stream()
                .filter(k -> (naziv == null || naziv.isEmpty() || k.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .filter(k -> (opis == null || opis.isEmpty() || k.getOpis().toLowerCase().contains(opis.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }
    
}
