/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import domain.DomainObject;
import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Marko
 */
public class Zaposleni implements Serializable, DomainObject<Zaposleni> {

    private int idZaposleni;
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;
    private LocalDate datumZaposlenja;

    public Zaposleni() {
    }

    public Zaposleni(int idZaposleni, String ime, String prezime, String email, String lozinka, LocalDate datumZaposlenja) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.datumZaposlenja = datumZaposlenja;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
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

    public LocalDate getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(LocalDate datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
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
        final Zaposleni other = (Zaposleni) obj;
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
        return "INSERT INTO zaposleni (ime, prezime, email, lozinka, datumZaposlenja) VALUES (?, ?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, email);
        ps.setString(4, lozinka);
        ps.setDate(5, Date.valueOf(datumZaposlenja));
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE zaposleni SET ime = ?, prezime = ?, email = ?, lozinka = ?, datumZaposlenja = ? WHERE idZaposleni = ?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, ime);
        ps.setString(2, prezime);
        ps.setString(3, email);
        ps.setString(4, lozinka);
        ps.setDate(5, Date.valueOf(datumZaposlenja));
        ps.setInt(6, idZaposleni);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM zaposleni WHERE idZaposleni = ?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idZaposleni);
    }

    @Override
    public String getSelectQuery() {
        if (idZaposleni == 0) {
            return "SELECT id, ime, prezime, email, lozinka, datumZaposlenja FROM zaposleni WHERE email = ? AND lozinka = ?";
        } else {
            return "SELECT id, ime, prezime, email, lozinka, datumZaposlenja FROM zaposleni WHERE idZapoleni = ?";
        }
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        if (idZaposleni == 0) {
            ps.setString(1, email);
            ps.setString(2, lozinka);
        } else {
            ps.setInt(1, idZaposleni);
        }
    }

    @Override
    public Zaposleni createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int idZaposleni = rs.getInt("idZaposleni");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String lozinka = rs.getString("lozinka");
            LocalDate datumZaposlenja = rs.getDate("datumZaposlenja").toLocalDate();

            return new Zaposleni(idZaposleni, ime, prezime, email, lozinka, datumZaposlenja);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM zaposleni";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<Zaposleni> createListFromResultSet(ResultSet rs) throws SQLException {
        List<Zaposleni> lista = new ArrayList<>();
        while (rs.next()) {
            int idZaposleni = rs.getInt("idZaposleni");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String email = rs.getString("email");
            String lozinka = rs.getString("lozinka");
            LocalDate datumZaposlenja = rs.getDate("datumZaposlenja").toLocalDate();

            lista.add(new Zaposleni(idZaposleni, ime, prezime, email, lozinka, datumZaposlenja));
        }
        return lista;
    }

}
