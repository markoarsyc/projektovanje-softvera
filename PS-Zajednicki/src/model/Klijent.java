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
public class Klijent implements Serializable, DomainObject<Klijent> {

    private int idKlijent;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private String telefon;
    private KategorijaKlijenta kategorija;

    public Klijent() {
    }

    public Klijent(int idKlijent, String ime, String prezime, String email, String lozinka, String telefon, KategorijaKlijenta kategorija) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.telefon = telefon;
        this.kategorija = kategorija;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public KategorijaKlijenta getKategorija() {
        return kategorija;
    }

    public void setKategorija(KategorijaKlijenta kategorija) {
        this.kategorija = kategorija;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Klijent other = (Klijent) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO klijent (ime, prezime, email, lozinka, telefon, kategorija) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, email);
        ps.setString(4, lozinka);
        ps.setString(5, telefon);
        ps.setInt(6, kategorija.getIdKategorijaKlijenta());
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE klijent SET ime = ?, prezime = ?, email = ?, lozinka = ?, telefon = ?, kategorija=? WHERE idKlijent = ?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, email);
        ps.setString(4, lozinka);
        ps.setString(5, telefon);
        ps.setInt(6, kategorija.getIdKategorijaKlijenta());
        ps.setInt(7, idKlijent);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM klijent WHERE idKlijent = ?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idKlijent);
    }

    @Override
    public String getSelectQuery() {
        if (idKlijent == 0) {
            return "SELECT * FROM klijent k LEFT JOIN kategorija_klijenta kk ON k.kategorija = kk.idKategorijaKlijenta WHERE k.email = ? AND k.lozinka = ?";
        } else {
            return "SELECT * FROM klijent k LEFT JOIN kategorija_klijenta kk ON k.kategorija = kk.idKategorijaKlijenta WHERE k.idKlijent = ?";
        }
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        if (idKlijent == 0) {
            ps.setString(1, email);
            ps.setString(2, lozinka);
        } else {
            ps.setInt(1, idKlijent);
        }
    }

    @Override
    public Klijent createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            //Klijent
            int idKlijent = rs.getInt("k.idKlijent");
            String ime = rs.getString("k.ime");
            String prezime = rs.getString("k.prezime");
            String email = rs.getString("k.email");
            String lozinka = rs.getString("k.lozinka");
            String telefon = rs.getString("k.telefon");
            
            //Kategorija
            int idKategorijaKlijenta = rs.getInt("kk.idKategorijaKlijenta");
            String naziv = rs.getString("kk.naziv");
            int procenatPopusta = rs.getInt("kk.procenatPopusta");
            KategorijaKlijenta kategorija = new KategorijaKlijenta(idKategorijaKlijenta, naziv, procenatPopusta);

            return new Klijent(idKlijent, ime, prezime, email, lozinka, telefon, kategorija);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM klijent k LEFT JOIN kategorija_klijenta kk ON k.kategorija = kk.idKategorijaKlijenta";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<Klijent> createListFromResultSet(ResultSet rs) throws SQLException {
        List<Klijent> lista = new ArrayList<>();
        while (rs.next()) {
            int idKlijent = rs.getInt("k.idKlijent");
            String ime = rs.getString("k.ime");
            String prezime = rs.getString("k.prezime");
            String email = rs.getString("k.email");
            String lozinka = rs.getString("k.lozinka");
            String telefon = rs.getString("k.telefon");
            
            //Kategorija
            int idKategorijaKlijenta = rs.getInt("kk.idKategorijaKlijenta");
            String naziv = rs.getString("kk.naziv");
            int procenatPopusta = rs.getInt("kk.procenatPopusta");
            KategorijaKlijenta kategorija = new KategorijaKlijenta(idKategorijaKlijenta, naziv, procenatPopusta);

            lista.add(new Klijent(idKlijent, ime, prezime, email, lozinka, telefon, kategorija));
        }
        return lista;
    }

}
