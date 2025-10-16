/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import model.PaketUsluga;
import model.Zaposleni;
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
    public Zaposleni login(Zaposleni zaposleni) {
        Request request = new Request(zaposleni, Operacija.LOGIN_ZAPOSLENI);
        sender.send(request);
        Response response = (Response) receiver.receive();
        return (Zaposleni) response.getParams();
    }

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
}
