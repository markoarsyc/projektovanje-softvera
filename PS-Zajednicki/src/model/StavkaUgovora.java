package model;

import domain.DomainObject;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class StavkaUgovora implements Serializable, DomainObject<StavkaUgovora> {

    private int rb;
    private Ugovor ugovor;
    private PaketUsluga paketUsluga;
    private double finalnaCena;

    public StavkaUgovora() {
    }

    public StavkaUgovora(int rb, double finalnaCena, Ugovor ugovor, PaketUsluga paketUsluga) {
        this.rb = rb;
        this.finalnaCena = finalnaCena;
        this.ugovor = ugovor;
        this.paketUsluga = paketUsluga;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public double getFinalnaCena() {
        return finalnaCena;
    }

    public void setFinalnaCena(double finalnaCena) {
        this.finalnaCena = finalnaCena;
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
        return Objects.hash(rb, ugovor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StavkaUgovora other = (StavkaUgovora) obj;
        return rb == other.rb && Objects.equals(ugovor, other.ugovor);
    }

    @Override
    public String toString() {
        return "StavkaUgovora{" + "rb=" + rb + ", finalnaCena=" + finalnaCena +
                ", ugovor=" + ugovor + ", paketUsluga=" + paketUsluga + '}';
    }

    @Override
    public String getInsertQuery() {
        return "INSERT INTO stavka_ugovora (rb, ugovor, paketUsluga, finalnaCena) VALUES (?, ?, ?, ?)";
    }

    @Override
    public void fillInsertStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, rb);
        ps.setInt(2, ugovor.getIdUgovor());
        ps.setInt(3, paketUsluga.getIdPaketUsluga());
        ps.setDouble(4, finalnaCena);
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE stavka_ugovora SET paketUsluga=?, finalnaCena=? WHERE ugovor=? AND rb=?";
    }

    @Override
    public void fillUpdateStatement(PreparedStatement ps) throws SQLException {
        ps.setInt(1, paketUsluga.getIdPaketUsluga());
        ps.setDouble(2, finalnaCena);
        ps.setInt(3, ugovor.getIdUgovor());
        ps.setInt(4, rb);
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
               SELECT su.rb, su.finalnaCena,
                      u.idUgovor,
                      pu.idPaketUsluga, pu.tip, pu.nazivPaketa, pu.cenaMesec
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
            double finalnaCena = rs.getDouble("su.finalnaCena");

            int idUgovor = rs.getInt("u.idUgovor");
            Ugovor ugovor = new Ugovor();
            ugovor.setIdUgovor(idUgovor);

            int idPaket = rs.getInt("pu.idPaketUsluga");
            String tipStr = rs.getString("pu.tip");
            TipPaketaUsluga tip = TipPaketaUsluga.valueOf(tipStr);
            String nazivPaketa = rs.getString("pu.nazivPaketa");
            double cenaPaket = rs.getDouble("pu.cenaMesec");

            PaketUsluga paketUsluga = new PaketUsluga(idPaket, tip, nazivPaketa, cenaPaket);

            return new StavkaUgovora(rb, finalnaCena, ugovor, paketUsluga);
        }
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return """
               SELECT su.rb, su.finalnaCena,
                      u.idUgovor,
                      pu.idPaketUsluga, pu.tip, pu.nazivPaketa, pu.cenaMesec
               FROM stavka_ugovora su
               JOIN ugovor u ON su.ugovor = u.idUgovor
               JOIN paket_usluga pu ON su.paketUsluga = pu.idPaketUsluga
               """;
    }

    @Override
    public void fillSelectAllStatement(PreparedStatement ps) throws SQLException {
        // nema parametara
    }

    @Override
    public List<StavkaUgovora> createListFromResultSet(ResultSet rs) throws SQLException {
        List<StavkaUgovora> lista = new ArrayList<>();
        while (rs.next()) {
            int rb = rs.getInt("su.rb");
            double finalnaCena = rs.getDouble("su.finalnaCena");

            int idUgovor = rs.getInt("u.idUgovor");
            Ugovor ugovor = new Ugovor();
            ugovor.setIdUgovor(idUgovor);

            int idPaket = rs.getInt("pu.idPaketUsluga");
            String tipStr = rs.getString("pu.tip");
            TipPaketaUsluga tip = TipPaketaUsluga.valueOf(tipStr);
            String nazivPaketa = rs.getString("pu.nazivPaketa");
            double cenaPaket = rs.getDouble("pu.cenaMesec");

            PaketUsluga paketUsluga = new PaketUsluga(idPaket, tip, nazivPaketa, cenaPaket);

            lista.add(new StavkaUgovora(rb, finalnaCena, ugovor, paketUsluga));
        }
        return lista;
    }
}
