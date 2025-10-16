/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import domain.DomainObject;
import java.util.List;
import operacije.IzmeniSO;
import operacije.KreirajSO;
import operacije.ObrisiSO;
import operacije.VratiSO;
import operacije.VratiSveSO;

/**
 *
 * @author Marko
 */
public class Controller {
    private static Controller instance;
    private DBBroker dbb;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    private Controller() {
        dbb = new DBBroker();
    }
    
    public <T extends DomainObject<T>> T vrati(T object) {
        try {
            return (T) new VratiSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("Greska prilikom izvrsavanja vratiSO: " + ex.getMessage());
        }
        return null;
    }
    
    public <T extends DomainObject<T>> List<T> vratiSve(T object) {
        try {
            return (List<T>) new VratiSveSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("Greska prilikom izvrsavanja vratiSveSO: " + ex.getMessage());
        }
        return null;
    }
    
    public <T extends DomainObject<T>> int dodaj(T object) {
        try {
            return (int) new KreirajSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("Greska prilikom izvrsavanja dodajSO: " + ex.getMessage());
        }
        return 0;
    }
    
    public <T extends DomainObject<T>> int izmeni(T object) {
        try {
            return (int) new IzmeniSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("Greska prilikom izvrsavanja izmeniSO: " + ex.getMessage());
        }
        return 0;
    }
    public <T extends DomainObject<T>> int obrisi(T object) {
        try {
            return (int) new ObrisiSO<T>().execute(object);
        } catch (Exception ex) {
            System.out.println("Greska prilikom izvrsavanja obrisiSO: " + ex.getMessage());
        }
        return 0;
    }
}
