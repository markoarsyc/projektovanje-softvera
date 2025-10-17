/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.ugovor;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaUgovora;

/**
 *
 * @author Marko
 */
public class TableModelStavkaUgovora extends AbstractTableModel {
    private String[] kolone = {"Paket","Tip paketa","Cena sa popustom"};
    private List<StavkaUgovora> stavke;

    public TableModelStavkaUgovora(List<StavkaUgovora> stavke) {
        this.stavke = stavke;
    }
    
    public List<StavkaUgovora> getStavke() {
        return stavke;
    }
    
    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       StavkaUgovora stavkaUgovora = stavke.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavkaUgovora.getPaketUsluga().toString();
            case 1:
                return stavkaUgovora.getPaketUsluga().getTip();
            case 2:
                return stavkaUgovora.getFinalnaCena();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    
    
}
