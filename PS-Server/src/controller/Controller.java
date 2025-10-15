/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import domain.DomainObject;
import operacije.VratiSO;

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
}
