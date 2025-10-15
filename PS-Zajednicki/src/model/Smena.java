/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.DomainObject;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Marko
 */
public class Smena implements Serializable, DomainObject<Smena> {

    private int idSmena;
    private String naziv;
    private LocalTime pocetak;
    private LocalTime kraj;

    public Smena() {
    }

    public Smena(int idSmena, String naziv, LocalTime pocetak, LocalTime kraj) {
        this.idSmena = idSmena;
        this.naziv = naziv;
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalTime kraj) {
        this.kraj = kraj;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Smena other = (Smena) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO smena VALUES (?,?,?) WHERE idSmena=?";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, naziv);
        ps.setTime(2, Time.valueOf(pocetak));
        ps.setTime(3, Time.valueOf(kraj));
        ps.setInt(4, idSmena);
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE smena SET naziv=?, pocetak=?, kraj=? WHERE idSmena=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, naziv);
        ps.setTime(2, Time.valueOf(pocetak));
        ps.setTime(3, Time.valueOf(kraj));
        ps.setInt(4, idSmena);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM smena WHERE idSmena=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idSmena);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM smena WHERE id=?";
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idSmena);
    }

    @Override
    public Smena createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int idSmena = rs.getInt("idSmena");
            String naziv = rs.getString("naziv");
            LocalTime pocetak = rs.getTime("pocetak").toLocalTime();
            LocalTime kraj = rs.getTime("kraj").toLocalTime();
            return new Smena(idSmena, naziv, pocetak, kraj);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM smena";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<Smena> createListFromResultSet(ResultSet rs) throws SQLException {
        List<Smena> lista = new ArrayList<>();
        while(rs.next()) {
            int idSmena = rs.getInt("idSmene");
            String naziv = rs.getString("naziv");
            LocalTime pocetak = rs.getTime("pocetak").toLocalTime();
            LocalTime kraj = rs.getTime("kraj").toLocalTime();
            lista.add(new Smena(idSmena, naziv, pocetak, kraj));
        }
        return lista;
    }

}
