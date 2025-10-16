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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Marko
 */
public class KategorijaKlijenta implements Serializable, DomainObject<KategorijaKlijenta> {

    private int idKategorijaKlijenta;
    private String naziv;
    private int procenatPopusta;

    public KategorijaKlijenta() {
    }
    
    public KategorijaKlijenta(String naziv, int procenatPopusta) {
        this.naziv = naziv;
        this.procenatPopusta = procenatPopusta;
    }

    public KategorijaKlijenta(int idKategorijaKlijenta, String naziv, int procenatPopusta) {
        this.idKategorijaKlijenta = idKategorijaKlijenta;
        this.naziv = naziv;
        this.procenatPopusta = procenatPopusta;
    }

    public int getIdKategorijaKlijenta() {
        return idKategorijaKlijenta;
    }

    public void setIdKategorijaKlijenta(int idKategorijaKlijenta) {
        this.idKategorijaKlijenta = idKategorijaKlijenta;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getProcenatPopusta() {
        return procenatPopusta;
    }

    public void setProcenatPopusta(int procenatPopusta) {
        this.procenatPopusta = procenatPopusta;
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
        final KategorijaKlijenta other = (KategorijaKlijenta) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO kategorija_klijenta (naziv, procenatPopusta) VALUES (?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, naziv);
        ps.setInt(2, procenatPopusta);
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE kategorija_klijenta SET naziv = ?, procenatPopusta = ? WHERE idKategorijaKlijenta = ?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, naziv);
        ps.setInt(2, procenatPopusta);
        ps.setInt(3, idKategorijaKlijenta);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM kategorija_klijenta WHERE idKategorijaKlijenta = ?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idKategorijaKlijenta);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM kategorija_klijenta WHERE idKategorijaKlijenta = ?";
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idKategorijaKlijenta);
    }

    @Override
    public KategorijaKlijenta createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int idKategorijaKlijenta = rs.getInt("idKategorijaKlijenta");
            String naziv = rs.getString("naziv");
            int procenatPopusta = rs.getInt("procenatPopusta");
            return new KategorijaKlijenta(idKategorijaKlijenta, naziv, procenatPopusta);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM kategorija_klijenta";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri    
    }

    @Override
    public List<KategorijaKlijenta> createListFromResultSet(ResultSet rs) throws SQLException {
        List<KategorijaKlijenta> lista = new ArrayList<>();
        while (rs.next()) {
            int idKategorijaKlijenta = rs.getInt("idKategorijaKlijenta");
            String naziv = rs.getString("naziv");
            int procenatPopusta = rs.getInt("procenatPopusta");
            lista.add(new KategorijaKlijenta(idKategorijaKlijenta, naziv, procenatPopusta));
        }
        return lista;
    }

}
