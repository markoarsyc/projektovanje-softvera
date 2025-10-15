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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Marko
 */
public class RadnoVreme implements Serializable, DomainObject<RadnoVreme> {

    private Zaposleni zaposleni;
    private Smena smena;
    private LocalDate datum;

    public RadnoVreme() {
    }

    public RadnoVreme(Zaposleni zaposleni, Smena smena, LocalDate datum) {
        this.zaposleni = zaposleni;
        this.smena = smena;
        this.datum = datum;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Smena getSmena() {
        return smena;
    }

    public void setSmena(Smena smena) {
        this.smena = smena;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
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
        final RadnoVreme other = (RadnoVreme) obj;
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        if (!Objects.equals(this.smena, other.smena)) {
            return false;
        }
        return Objects.equals(this.datum, other.datum);
    }

    @Override
    public String toString() {
        return "RadnoVreme{" + "zaposleni=" + zaposleni + ", smena=" + smena + ", datum=" + datum + '}';
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO radno_vreme (zaposleni, smena, datum) VALUES (?,?,?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, zaposleni.getIdZaposleni());
        ps.setInt(2, smena.getIdSmena());
        ps.setDate(3, Date.valueOf(datum));
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE radno_vreme SET smena=? WHERE zaposleni=? AND datum=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, smena.getIdSmena());
        ps.setInt(2, zaposleni.getIdZaposleni());
        ps.setDate(3, Date.valueOf(datum));
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM radno_vreme WHERE zaposleni=? AND smena=? AND datum=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, zaposleni.getIdZaposleni());
        ps.setInt(2, smena.getIdSmena());
        ps.setDate(3, Date.valueOf(datum));
    }

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM radno_vreme r JOIN smena s ON r.smena=s.idSmena JOIN zaposleni z ON r.zaposleni=z.idZaposleni WHERE s.zaposleni=? AND s.datum=?";
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, zaposleni.getIdZaposleni());
        ps.setDate(2, Date.valueOf(datum));
    }

    @Override
    public RadnoVreme createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            //Zaposleni
            int idZaposleni = rs.getInt("z.idZaposleni");
            String ime = rs.getString("z.ime");
            String prezime = rs.getString("z.prezime");
            String email = rs.getString("z.email");
            String lozinka = rs.getString("z.lozinka");
            LocalDate datumZaposlenja = rs.getDate("z.datumZaposlenja").toLocalDate();
            Zaposleni zaposleni = new Zaposleni(idZaposleni, ime, prezime, email, lozinka, datumZaposlenja);

            //Smena
            int idSmena = rs.getInt("s.idSmene");
            String naziv = rs.getString("s.naziv");
            LocalTime pocetak = rs.getTime("s.pocetak").toLocalTime();
            LocalTime kraj = rs.getTime("s.kraj").toLocalTime();
            Smena smena = new Smena(idSmena, naziv, pocetak, kraj);

            //Datum
            LocalDate datum = rs.getDate("r.datum").toLocalDate();

            return new RadnoVreme(zaposleni, smena, datum);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM radno_vreme r JOIN smena s ON r.smena=s.idSmena JOIN zaposleni z ON r.zaposleni=z.idZaposleni";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Za ovu operaciju ne postoje parametri
    }

    @Override
    public List<RadnoVreme> createListFromResultSet(ResultSet rs) throws SQLException {
        List<RadnoVreme> lista = new ArrayList<>();
        while (rs.next()) {
            //Zaposleni
            int idZaposleni = rs.getInt("z.idZaposleni");
            String ime = rs.getString("z.ime");
            String prezime = rs.getString("z.prezime");
            String email = rs.getString("z.email");
            String lozinka = rs.getString("z.lozinka");
            LocalDate datumZaposlenja = rs.getDate("z.datumZaposlenja").toLocalDate();
            Zaposleni zaposleni = new Zaposleni(idZaposleni, ime, prezime, email, lozinka, datumZaposlenja);

            //Smena
            int idSmena = rs.getInt("s.idSmene");
            String naziv = rs.getString("s.naziv");
            LocalTime pocetak = rs.getTime("s.pocetak").toLocalTime();
            LocalTime kraj = rs.getTime("s.kraj").toLocalTime();
            Smena smena = new Smena(idSmena, naziv, pocetak, kraj);

            //Datum
            LocalDate datum = rs.getDate("r.datum").toLocalDate();
            
            lista.add(new RadnoVreme(zaposleni, smena, datum));
        }
        return lista;
    }

}
