/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package form;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Zaposleni;
import model.helper.UlogovaniZaposleni;

/**
 *
 * @author Marko
 */
public class TableModelZaposleni extends AbstractTableModel{
    private String[] kolone = {"id","Ime","Prezime","Email","Status"};
    private List<UlogovaniZaposleni> zaposleniLista;

    public TableModelZaposleni(List<UlogovaniZaposleni> zaposleniLista) {
        this.zaposleniLista = zaposleniLista;
    }
    
    
    
    @Override
    public int getRowCount() {
        return zaposleniLista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        UlogovaniZaposleni ulogovaniZaposleni = zaposleniLista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ulogovaniZaposleni.getZaposleni().getIdZaposleni();
            case 1:
                return ulogovaniZaposleni.getZaposleni().getIme();
            case 2:
                return ulogovaniZaposleni.getZaposleni().getPrezime();
            case 3:
                return ulogovaniZaposleni.getZaposleni().getEmail();
            case 4:
                return ulogovaniZaposleni.getStatus();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<UlogovaniZaposleni> getZaposleniLista() {
        return zaposleniLista;
    }
    
    
    
    
    
}
