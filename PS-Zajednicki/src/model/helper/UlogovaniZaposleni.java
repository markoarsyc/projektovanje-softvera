/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.helper;

import java.io.Serializable;
import java.util.Objects;
import model.Zaposleni;

/**
 *
 * @author Marko
 */
public class UlogovaniZaposleni implements Serializable{
    private Zaposleni zaposleni;
    private String status = "ODJAVLJEN";

    public UlogovaniZaposleni() {
    }

    public UlogovaniZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        final UlogovaniZaposleni other = (UlogovaniZaposleni) obj;
        return Objects.equals(this.zaposleni, other.zaposleni);
    }
    
    
    
}
