/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import model.KategorijaKlijenta;
import model.Klijent;
import model.PaketUsluga;
import model.Zaposleni;
import model.helper.UgovorSaStavkama;
import operacije.Operacija;
import transfer.Receiver;
import transfer.Request;
import transfer.Response;
import transfer.Sender;

/**
 *
 * @author Marko
 */
public class Communication {

    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    private static Communication instance;

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    private Communication() {
        try {
            socket = new Socket("localhost", 9000);
            receiver = new Receiver(socket);
            sender = new Sender(socket);
        } catch (IOException ex) {
            System.out.println("Greska prilikom povezivanja na server: " + ex.getMessage());
        }
    }

    //Operacije
    //Zaposleni
    public Zaposleni login(Zaposleni zaposleni) {
        Request request = new Request(zaposleni, Operacija.LOGIN_ZAPOSLENI);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (Zaposleni) response.getParams();
    }

    //Paket usluga
    public List<PaketUsluga> vratiSvePakete() {
        Request request = new Request(null, Operacija.VRATI_SVE_PAKETE_USLUGA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (List<PaketUsluga>) response.getParams();
    }

    public int dodajPaketUsluga(PaketUsluga paket) {
        Request request = new Request(paket, Operacija.DODAJ_PAKET_USLUGA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }

    public int izmeniPaketUsluga(PaketUsluga paket) {
        Request request = new Request(paket, Operacija.IZMENI_PAKET_USLUGA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }

    public int obrisiPaketUsluga(PaketUsluga paket) {
        Request request = new Request(paket, Operacija.OBRISI_PAKET_USLUGA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }

    //Kategorija klijenta
    public List<KategorijaKlijenta> vratiSveKategorijeKlijenta() {
        Request request = new Request(null, Operacija.VRATI_SVE_KATEGORIJE_KLIJENTA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (List<KategorijaKlijenta>) response.getParams();
    }

    public int dodajKategorijuKlijenta(KategorijaKlijenta kategorija) {
        Request request = new Request(kategorija, Operacija.DODAJ_KATEGORIJU_KLIJENTA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }

    public int izmeniKategorijuKlijenta(KategorijaKlijenta kategorija) {
        Request request = new Request(kategorija, Operacija.IZMENI_KATEGORIJU_KLIJENTA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }

    public int obrisiKategorijuKlijenta(KategorijaKlijenta kategorija) {
        Request request = new Request(kategorija, Operacija.OBRISI_KATEGORIJU_KLIJENTA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }

    //Klijent
    public List<Klijent> vratiSveKlijente() {
        Request request = new Request(null, Operacija.VRATI_SVE_KLIJENTE);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (List<Klijent>) response.getParams();
    }
    
    public int dodajKlijenta(Klijent klijent) {
        Request request = new Request(klijent, Operacija.DODAJ_KLIJENTA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }

    public int izmeniKlijenta(Klijent klijent) {
        Request request = new Request(klijent, Operacija.IZMENI_KLIJENTA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }

    public int obrisiKlijenta(Klijent klijent) {
        Request request = new Request(klijent, Operacija.OBRISI_KLIJENTA);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }
    
    //Ugovor
    public int dodajUgovorSaStavkama(UgovorSaStavkama ugovorSaStavkama) {
        Request request = new Request(ugovorSaStavkama, Operacija.DODAJ_UGOVOR);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (int) response.getParams();
    }
}
