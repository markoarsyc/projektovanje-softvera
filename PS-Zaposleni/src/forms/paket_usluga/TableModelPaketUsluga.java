/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.paket_usluga;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.PaketUsluga;

/**
 *
 * @author Marko
 */
public class TableModelPaketUsluga extends AbstractTableModel {
    private String[] kolone = {"id", "Tip", "Naziv", "Mesecna cena"};
    private List<PaketUsluga> paketi;
    
    public TableModelPaketUsluga(List<PaketUsluga> paketi) {
        this.paketi = paketi;
    }
    
    @Override
    public int getRowCount() {
        return paketi.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PaketUsluga paket = paketi.get(rowIndex);
        switch (columnIndex) {
            case 0:
               return paket.getIdPaketUsluga();
            case 1:
                return paket.getTip();
            case 2:
                return paket.getNazivPaketa();
            case 3:
                return paket.getCenaMesec();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<PaketUsluga> getPaketi() {
        return paketi;
    }
    
    
    
}
