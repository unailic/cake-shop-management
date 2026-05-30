/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Smena;

/**
 *
 * @author HP
 */
public class ModelTabeleSmene extends AbstractTableModel {
    List<Smena> lista;
    String[] kolone = {"Naziv", "Pocetak smene", "Kraj smene"};

    public ModelTabeleSmene(List<Smena> lista) {
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
            Smena smena = lista.get(rowIndex);
    
            switch (columnIndex) {
                case 0:
                    return smena.getNazivSmene();
                case 1:
                    return smena.getPocetakSmene().toString();
                case 2:
                    return smena.getKrajSmene().toString();
                default:
                    return null;
            }
    }
    
}
