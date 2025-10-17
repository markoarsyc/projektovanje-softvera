/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.ugovor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Ugovor;

/**
 *
 * @author Marko
 */
public class TableModelUgovor extends AbstractTableModel {
    private String[] kolone = {"Id","Pocetak","Kraj","Trajanje (meseci)", "Cena (RSD/mes)", "Status"};
    private List<Ugovor> ugovori;
    public TableModelUgovor(List<Ugovor> ugovori) {
        this.ugovori = ugovori;
    }
    @Override
    public int getRowCount() {
        return ugovori.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Ugovor ugovor = ugovori.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ugovor.getIdUgovor();
            case 1:
                return ugovor.getDatumPocetka().format(formatter);
            case 2:
                return ugovor.getDatumIsteka().format(formatter);
            case 3:
                return ugovor.getTrajanjeMeseci();
            case 4:
                return ugovor.getUkupnaCena();
            case 5:
                return ugovor.getStatus().toString();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Ugovor> getUgovori() {
        return ugovori;
    }
    
    
    
    
}
