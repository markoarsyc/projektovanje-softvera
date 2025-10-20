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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marko
 */
public class Ugovor implements Serializable, DomainObject<Ugovor> {

    private int idUgovor;
    private Zaposleni zaposleni;
    private Klijent klijent;
    private LocalDate datumPocetka;
    private LocalDate datumIsteka;
    private int trajanjeMeseci;
    private double ukupnaCena;
    private StatusUgovora status;

    public Ugovor() {
    }

    public Ugovor(Zaposleni zaposleni, Klijent klijent, LocalDate datumPocetka, LocalDate datumIsteka, int trajanjeMeseci, double ukupnaCena, StatusUgovora status) {
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.datumPocetka = datumPocetka;
        this.datumIsteka = datumIsteka;
        this.trajanjeMeseci = trajanjeMeseci;
        this.ukupnaCena = ukupnaCena;
        this.status = status;
    }

    public Ugovor(int idUgovor, Zaposleni zaposleni, Klijent klijent, LocalDate datumPocetka, LocalDate datumIsteka, int trajanjeMeseci, double ukupnaCena, StatusUgovora status) {
        this.idUgovor = idUgovor;
        this.zaposleni = zaposleni;
        this.klijent = klijent;
        this.datumPocetka = datumPocetka;
        this.datumIsteka = datumIsteka;
        this.trajanjeMeseci = trajanjeMeseci;
        this.ukupnaCena = ukupnaCena;
        this.status = status;
    }

    public int getIdUgovor() {
        return idUgovor;
    }

    public void setIdUgovor(int idUgovor) {
        this.idUgovor = idUgovor;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public LocalDate getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(LocalDate datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public LocalDate getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(LocalDate datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public int getTrajanjeMeseci() {
        return trajanjeMeseci;
    }

    public void setTrajanjeMeseci(int trajanjeMeseci) {
        this.trajanjeMeseci = trajanjeMeseci;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public StatusUgovora getStatus() {
        return status;
    }

    public void setStatus(StatusUgovora status) {
        this.status = status;
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
        final Ugovor other = (Ugovor) obj;
        return this.idUgovor == other.idUgovor;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String datumPocetkaStr = (datumPocetka != null) ? datumPocetka.format(formatter) : "";
        String datumIstekaStr = (datumIsteka != null) ? datumIsteka.format(formatter) : "";
        return "BROJ UGOVORA: " + idUgovor + " / (" + datumPocetkaStr + " - " + datumIstekaStr + ") / " + status;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO ugovor (zaposleni, klijent, datumPocetka, datumIsteka, trajanjeMeseci, ukupnaCena, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, zaposleni.getIdZaposleni());
        ps.setInt(2, klijent.getIdKlijent());
        ps.setDate(3, Date.valueOf(datumPocetka));
        ps.setDate(4, Date.valueOf(datumIsteka));
        ps.setInt(5, trajanjeMeseci);
        ps.setDouble(6, ukupnaCena);
        ps.setString(7, status.name());
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE ugovor SET zaposleni=?, klijent=?, datumPocetka=?, datumIsteka=?, trajanjeMeseci=?, ukupnaCena=?, status=? WHERE idUgovor=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, zaposleni.getIdZaposleni());
        ps.setInt(2, klijent.getIdKlijent());
        ps.setDate(3, Date.valueOf(datumPocetka));
        ps.setDate(4, Date.valueOf(datumIsteka));
        ps.setInt(5, trajanjeMeseci);
        ps.setDouble(6, ukupnaCena);
        ps.setString(7, status.name());
        ps.setInt(8, idUgovor);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM ugovor WHERE idUgovor=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idUgovor);
    }

    @Override
    public String getSelectQuery() {
        return """
               SELECT *
               FROM ugovor u
               JOIN zaposleni z ON u.zaposleni = z.idZaposleni
               JOIN klijent k ON u.klijent = k.idKlijent
               LEFT JOIN kategorija_klijenta kk ON k.kategorija = kk.idKategorijaKlijenta
               WHERE u.idUgovor = ?""";
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idUgovor);
    }

    @Override
    public Ugovor createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            //Ugovor
            int idUgovor = rs.getInt("u.idUgovor");
            LocalDate datumPocetka = rs.getDate("u.datumPocetka").toLocalDate();
            LocalDate datumIsteka = rs.getDate("u.datumIsteka").toLocalDate();
            int trajanjeMeseci = rs.getInt("u.trajanjeMeseci");
            double ukupnaCena = rs.getDouble("u.ukupnaCena");
            StatusUgovora status = StatusUgovora.valueOf(rs.getString("u.status"));

            //Zaposleni
            int idZaposleni = rs.getInt("z.idZaposleni");
            String imeZ = rs.getString("z.ime");
            String prezimeZ = rs.getString("z.prezime");
            String emailZ = rs.getString("z.email");
            String lozinkaZ = rs.getString("z.lozinka");
            LocalDate datumZaposlenja = rs.getDate("z.datumZaposlenja").toLocalDate();

            Zaposleni zaposleni = new Zaposleni(idZaposleni, imeZ, prezimeZ, emailZ, lozinkaZ, datumZaposlenja);

            //Klijent
            int idKlijent = rs.getInt("k.idKlijent");
            String imeK = rs.getString("k.ime");
            String prezimeK = rs.getString("k.prezime");
            String emailK = rs.getString("k.email");
            String lozinkaK = rs.getString("k.lozinka");
            String telefon = rs.getString("k.telefon");

            //Kategorija
            int idKategorijaKlijenta = rs.getInt("kk.idKategorijaKlijenta");
            String naziv = rs.getString("kk.naziv");
            int procenatPopusta = rs.getInt("kk.procenatPopusta");
            KategorijaKlijenta kategorija = new KategorijaKlijenta(idKategorijaKlijenta, naziv, procenatPopusta);

            Klijent klijent = new Klijent(idKlijent, imeK, prezimeK, emailK, lozinkaK, telefon, kategorija);

            return new Ugovor(idUgovor, zaposleni, klijent, datumPocetka, datumIsteka, trajanjeMeseci, ukupnaCena, status);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return """
               SELECT *
               FROM ugovor u
               JOIN zaposleni z ON u.zaposleni = z.idZaposleni
               JOIN klijent k ON u.klijent = k.idKlijent
               LEFT JOIN kategorija_klijenta kk ON k.kategorija = kk.idKategorijaKlijenta""";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Nema parametara za ovu operaciju
    }

    @Override
    public List<Ugovor> createListFromResultSet(ResultSet rs) throws SQLException {
        List<Ugovor> lista = new ArrayList<>();
        while (rs.next()) {
            //Ugovor
            int idUgovor = rs.getInt("u.idUgovor");
            LocalDate datumPocetka = rs.getDate("u.datumPocetka").toLocalDate();
            LocalDate datumIsteka = rs.getDate("u.datumIsteka").toLocalDate();
            int trajanjeMeseci = rs.getInt("u.trajanjeMeseci");
            double ukupnaCena = rs.getDouble("u.ukupnaCena");
            StatusUgovora status = StatusUgovora.valueOf(rs.getString("u.status"));

            //Zaposleni
            int idZaposleni = rs.getInt("z.idZaposleni");
            String imeZ = rs.getString("z.ime");
            String prezimeZ = rs.getString("z.prezime");
            String emailZ = rs.getString("z.email");
            String lozinkaZ = rs.getString("z.lozinka");
            LocalDate datumZaposlenja = rs.getDate("z.datumZaposlenja").toLocalDate();

            Zaposleni zaposleni = new Zaposleni(idZaposleni, imeZ, prezimeZ, emailZ, lozinkaZ, datumZaposlenja);

            //Klijent
            int idKlijent = rs.getInt("k.idKlijent");
            String imeK = rs.getString("k.ime");
            String prezimeK = rs.getString("k.prezime");
            String emailK = rs.getString("k.email");
            String lozinkaK = rs.getString("k.lozinka");
            String telefon = rs.getString("k.telefon");

            //Kategorija
            int idKategorijaKlijenta = rs.getInt("kk.idKategorijaKlijenta");
            String naziv = rs.getString("kk.naziv");
            int procenatPopusta = rs.getInt("kk.procenatPopusta");
            KategorijaKlijenta kategorija = new KategorijaKlijenta(idKategorijaKlijenta, naziv, procenatPopusta);

            Klijent klijent = new Klijent(idKlijent, imeK, prezimeK, emailK, lozinkaK, telefon, kategorija);

            lista.add(new Ugovor(idUgovor, zaposleni, klijent, datumPocetka, datumIsteka, trajanjeMeseci, ukupnaCena, status));
        }
        return lista;
    }

}
