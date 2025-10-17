/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.helper;

import java.io.Serializable;
import java.util.List;
import model.StavkaUgovora;
import model.Ugovor;

/**
 *
 * @author Marko
 */
public class UgovorSaStavkama implements Serializable{
    private Ugovor ugovor;
    private List<StavkaUgovora> stavke;

    public UgovorSaStavkama(Ugovor ugovor, List<StavkaUgovora> stavke) {
        this.ugovor = ugovor;
        this.stavke = stavke;
    }

    public Ugovor getUgovor() {
        return ugovor;
    }

    public void setUgovor(Ugovor ugovor) {
        this.ugovor = ugovor;
    }

    public List<StavkaUgovora> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaUgovora> stavke) {
        this.stavke = stavke;
    }
}
