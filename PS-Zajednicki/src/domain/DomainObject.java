/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;
import java.sql.*;
import java.util.List;
/**
 *
 * @author Marko
 * @param <T>
 */
public interface DomainObject<T> {
    // Vraća SQL upit za INSERT operaciju
    String getInsertQuery();
    // Popunjava PreparedStatement za INSERT
    void fillInsertStatement(PreparedStatement ps) throws SQLException;
    
    // Vraća SQL upit za UPDATE operaciju
    String getUpdateQuery();
    // Popunjava PreparedStatement za UPDATE
    void fillUpdateStatement(PreparedStatement ps) throws SQLException;
    
    // Vraća SQL upit za DELETE operaciju
    String getDeleteQuery();
    // Popunjava PreparedStatement za DELETE
    void fillDeleteStatement(PreparedStatement ps) throws SQLException;
    
    // Vraća SQL upit za SELECT operaciju za jedan objekat
    String getSelectQuery();
    // Popunjava PreparedStatement za SELECT
    void fillSelectStatement(PreparedStatement ps) throws SQLException;
    // Kreira objekat iz ResultSet-a
    T createFromResultSet(ResultSet rs) throws SQLException;
    
    // Vraća SQL upit za SELECT operaciju za sve redove
    String getSelectAllQuery();
    // Popunjava PreparedStatement za SELECT
    void fillSelectAllStatement(PreparedStatement ps) throws SQLException;
    //Kreira listu objekata iz ResultSet-a
    List<T> createListFromResultSet(ResultSet rs) throws SQLException;
}
