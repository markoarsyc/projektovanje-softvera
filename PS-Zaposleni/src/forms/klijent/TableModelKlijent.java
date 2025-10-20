/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.klijent;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Klijent;

/**
 *
 * @author Marko
 */
public class TableModelKlijent extends AbstractTableModel {

    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Kategorija"};
    private List<Klijent> klijenti;

    public TableModelKlijent(List<Klijent> klijenti) {
        this.klijenti = klijenti;
    }

    @Override
    public int getRowCount() {
        return klijenti == null ? 0 : klijenti.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = klijenti.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return k.getIdKlijent();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getEmail();
            case 4:
                return k.getTelefon();
            case 5:
                return (k.getKategorija() != null) ? k.getKategorija().getNaziv() : "N/A";
            default:
                throw new AssertionError("Nepostojeca kolona!");
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Klijent> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<Klijent> klijenti) {
        this.klijenti = klijenti;
        fireTableDataChanged();
    }

    public Klijent getKlijentAt(int row) {
        return klijenti.get(row);
    }
}

