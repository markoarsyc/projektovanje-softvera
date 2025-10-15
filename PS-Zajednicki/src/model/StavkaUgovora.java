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
public class StavkaUgovora implements Serializable, DomainObject<StavkaUgovora> {

    private int rb;
    private String status;
    private double cenaMesec;
    private int procenatPopusta;
    private Ugovor ugovor;
    private PaketUsluga paketUsluga;

    public StavkaUgovora() {
    }

    public StavkaUgovora(int rb, String status, double cenaMesec, int procenatPopusta, Ugovor ugovor, PaketUsluga paketUsluga) {
        this.rb = rb;
        this.status = status;
        this.cenaMesec = cenaMesec;
        this.procenatPopusta = procenatPopusta;
        this.ugovor = ugovor;
        this.paketUsluga = paketUsluga;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCenaMesec() {
        return cenaMesec;
    }

    public void setCenaMesec(double cenaMesec) {
        this.cenaMesec = cenaMesec;
    }

    public int getProcenatPopusta() {
        return procenatPopusta;
    }

    public void setProcenatPopusta(int procenatPopusta) {
        this.procenatPopusta = procenatPopusta;
    }

    public Ugovor getUgovor() {
        return ugovor;
    }

    public void setUgovor(Ugovor ugovor) {
        this.ugovor = ugovor;
    }

    public PaketUsluga getPaketUsluga() {
        return paketUsluga;
    }

    public void setPaketUsluga(PaketUsluga paketUsluga) {
        this.paketUsluga = paketUsluga;
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
        final StavkaUgovora other = (StavkaUgovora) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.ugovor, other.ugovor);
    }

    @Override
    public String toString() {
        return "StavkaUgovora{" + "rb=" + rb + ", status=" + status + ", cenaMesec=" + cenaMesec + ", procenatPopusta=" + procenatPopusta + ", ugovor=" + ugovor + ", paketUsluga=" + paketUsluga + '}';
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO stavka_ugovora (rb, status, cenaMesec, procenatPopusta, ugovor, paketUsluga) VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, rb);
        ps.setString(2, status);
        ps.setDouble(3, cenaMesec);
        ps.setInt(4, procenatPopusta);
        ps.setInt(5, ugovor.getIdUgovor());
        ps.setInt(6, paketUsluga.getIdPaketUsluga());
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE stavka_ugovora SET status=?, cenaMesec=?, procenatPopusta=?, paketUsluga=? WHERE ugovor=? AND rb=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, status);
        ps.setDouble(2, cenaMesec);
        ps.setInt(3, procenatPopusta);
        ps.setInt(4, paketUsluga.getIdPaketUsluga());
        ps.setInt(5, ugovor.getIdUgovor());
        ps.setInt(6, rb);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM stavka_ugovora WHERE ugovor=? AND rb=?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, ugovor.getIdUgovor());
        ps.setInt(2, rb);
    }

    @Override
    public String getSelectQuery() {
        return """
               SELECT su.rb, su.status, su.cenaMesec, su.procenatPopusta,
                      u.idUgovor, pu.idPaketUsluga, pu.tip, pu.nazivPaketa, pu.cenaMesec
               FROM stavka_ugovora su
               JOIN ugovor u ON su.ugovor = u.idUgovor
               JOIN paket_usluga pu ON su.paketUsluga = pu.idPaketUsluga
               WHERE su.ugovor = ? AND su.rb = ?
               """;
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, ugovor.getIdUgovor());
        ps.setInt(2, rb);
    }

    @Override
    public StavkaUgovora createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int rb = rs.getInt("su.rb");
            String status = rs.getString("su.status");
            double cenaMesec = rs.getDouble("su.cenaMesec");
            int procenatPopusta = rs.getInt("su.procenatPopusta");

            // Ugovor (samo ID)
            int idUgovor = rs.getInt("u.idUgovor");
            Ugovor ugovor = new Ugovor();
            ugovor.setIdUgovor(idUgovor);

            // Paket
            int idPaket = rs.getInt("pu.idPaketUsluga");
            String tipStr = rs.getString("pu.tip");
            TipPaketaUsluga tip = TipPaketaUsluga.valueOf(tipStr);
            String nazivPaketa = rs.getString("pu.nazivPaketa");
            double cenaPaket = rs.getDouble("pu.cenaMesec");
            PaketUsluga paketUsluga = new PaketUsluga(idPaket, tip, nazivPaketa, cenaPaket);

            return new StavkaUgovora(rb, status, cenaMesec, procenatPopusta, ugovor, paketUsluga);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return """
               SELECT su.rb, su.status, su.cenaMesec, su.procenatPopusta,
                      u.idUgovor, pu.idPaketUsluga, pu.tip, pu.nazivPaketa, pu.cenaMesec
               FROM stavka_ugovora su
               JOIN ugovor u ON su.ugovor = u.idUgovor
               JOIN paket_usluga pu ON su.paketUsluga = pu.idPaketUsluga
               """;
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Ova operacija nema parametre
    }

    @Override
    public List<StavkaUgovora> createListFromResultSet(ResultSet rs) throws SQLException {
        List<StavkaUgovora> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("su.rb");
            String status = rs.getString("su.status");
            double cenaMesec = rs.getDouble("su.cenaMesec");
            int procenatPopusta = rs.getInt("su.procenatPopusta");

            int idUgovor = rs.getInt("u.idUgovor");
            Ugovor ugovor = new Ugovor();
            ugovor.setIdUgovor(idUgovor);

            int idPaket = rs.getInt("pu.idPaketUsluga");
            String tipStr = rs.getString("pu.tip");
            TipPaketaUsluga tip = TipPaketaUsluga.valueOf(tipStr);
            String nazivPaketa = rs.getString("pu.nazivPaketa");
            double cenaPaket = rs.getDouble("pu.cenaMesec");

            PaketUsluga paketUsluga = new PaketUsluga(idPaket, tip, nazivPaketa, cenaPaket);

            lista.add(new StavkaUgovora(rb, status, cenaMesec, procenatPopusta, ugovor, paketUsluga));
        }
        return lista;
    }

}
