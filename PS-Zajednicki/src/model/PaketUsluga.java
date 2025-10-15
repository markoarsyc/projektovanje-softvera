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
public class PaketUsluga implements Serializable, DomainObject<PaketUsluga> {

    private int idPaketUsluga;
    private TipPaketaUsluga tip;
    private String nazivPaketa;
    private double cenaMesec;

    public PaketUsluga() {
    }

    public PaketUsluga(TipPaketaUsluga tip, String nazivPaketa, double cenaMesec) {
        this.tip = tip;
        this.nazivPaketa = nazivPaketa;
        this.cenaMesec = cenaMesec;
    }

    public PaketUsluga(int idPaketUsluga, TipPaketaUsluga tip, String nazivPaketa, double cenaMesec) {
        this.idPaketUsluga = idPaketUsluga;
        this.tip = tip;
        this.nazivPaketa = nazivPaketa;
        this.cenaMesec = cenaMesec;
    }

    public int getIdPaketUsluga() {
        return idPaketUsluga;
    }

    public void setIdPaketUsluga(int idPaketUsluga) {
        this.idPaketUsluga = idPaketUsluga;
    }

    public TipPaketaUsluga getTip() {
        return tip;
    }

    public void setTip(TipPaketaUsluga tip) {
        this.tip = tip;
    }

    public String getNazivPaketa() {
        return nazivPaketa;
    }

    public void setNazivPaketa(String nazivPaketa) {
        this.nazivPaketa = nazivPaketa;
    }

    public double getCenaMesec() {
        return cenaMesec;
    }

    public void setCenaMesec(double cenaMesec) {
        this.cenaMesec = cenaMesec;
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
        final PaketUsluga other = (PaketUsluga) obj;
        return Objects.equals(this.nazivPaketa, other.nazivPaketa);
    }

    @Override
    public String toString() {
        return nazivPaketa;
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO paket_usluga (tip, nazivPaketa, cenaMesec) VALUES (?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, tip.name());
        ps.setString(2, nazivPaketa);
        ps.setDouble(3, cenaMesec);
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE paket_usluga SET tip = ?, nazivPaketa = ?, cenaMesec = ? WHERE idPaketUsluga = ?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setString(1, tip.name());
        ps.setString(2, nazivPaketa);
        ps.setDouble(3, cenaMesec);
        ps.setInt(4, idPaketUsluga);
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM paket_usluga WHERE idPaketUsluga = ?";
    }

    @Override
    public void fillDeleteStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idPaketUsluga);
    }

    @Override
    public String getSelectQuery() {
        return "SELECT idPaketUsluga, tip, nazivPaketa, cenaMesec FROM paket_usluga WHERE idPaketUsluga = ?";
    }

    @Override
    public void fillSelectStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, idPaketUsluga);
    }

    @Override
    public PaketUsluga createFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            int idPaketUsluga = rs.getInt("idPaketUsluga");
            String tipStr = rs.getString("tip");
            TipPaketaUsluga tip = TipPaketaUsluga.valueOf(tipStr);
            String nazivPaketa = rs.getString("nazivPaketa");
            double cenaMesec = rs.getDouble("cenaMesec");

            return new PaketUsluga(idPaketUsluga, tip, nazivPaketa, cenaMesec);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return "SELECT * FROM paket_usluga";
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        //Nema parametara
    }

    @Override
    public List<PaketUsluga> createListFromResultSet(ResultSet rs) throws SQLException {
        List<PaketUsluga> lista = new ArrayList<>();
        while (rs.next()) {
            int idPaketUsluga = rs.getInt("idPaketUsluga");
            String tipStr = rs.getString("tip");
            TipPaketaUsluga tip = TipPaketaUsluga.valueOf(tipStr);
            String nazivPaketa = rs.getString("nazivPaketa");
            double cenaMesec = rs.getDouble("cenaMesec");

            lista.add(new PaketUsluga(idPaketUsluga, tip, nazivPaketa, cenaMesec));
        }
        return lista;
    }

}
