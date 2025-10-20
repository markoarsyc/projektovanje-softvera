/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.kategorija_klijenta;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.KategorijaKlijenta;

/**
 *
 * @author Marko
 */
public class TableModelKategorijaKlijenta extends AbstractTableModel {

    private String[] kolone = {"id", "Naziv", "Popust (%)"};
    private List<KategorijaKlijenta> kategorijeKlijenta;

    public TableModelKategorijaKlijenta(List<KategorijaKlijenta> kategorijeKlijenta) {
        this.kategorijeKlijenta = kategorijeKlijenta;
    }

    public List<KategorijaKlijenta> getKategorijeKlijenta() {
        return kategorijeKlijenta;
    }

    @Override
    public int getRowCount() {
        return kategorijeKlijenta.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        KategorijaKlijenta kategorija = kategorijeKlijenta.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return kategorija.getIdKategorijaKlijenta();
            case 1:
                return kategorija.getNaziv();
            case 2:
                return kategorija.getProcenatPopusta();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
