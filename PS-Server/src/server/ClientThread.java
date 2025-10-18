/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import controller.Controller;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import model.KategorijaKlijenta;
import model.Klijent;
import model.PaketUsluga;
import model.RadnoVreme;
import model.Smena;
import model.StavkaUgovora;
import model.Ugovor;
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
public class ClientThread extends Thread {

    private final Socket socket;
    private final Sender sender;
    private final Receiver receiver;
    private Controller controller = Controller.getInstance();

    public ClientThread(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (socket != null && !socket.isClosed()) {
            Request request = (Request) receiver.receive();
            Response response = new Response();
            if (request == null) {
                break;
            }
            switch (request.getOperacija()) {
                case Operacija.LOGIN_ZAPOSLENI:
                    response.setParams(controller.vrati((Zaposleni) request.getParams()));
                    break;
                case Operacija.VRATI_SVE_PAKETE_USLUGA:
                    response.setParams(controller.vratiSve(new PaketUsluga()));
                    break;
                case Operacija.DODAJ_PAKET_USLUGA:
                    response.setParams(controller.dodaj((PaketUsluga) request.getParams()));
                    break;
                case Operacija.IZMENI_PAKET_USLUGA:
                    response.setParams(controller.izmeni((PaketUsluga) request.getParams()));
                    break;
                case Operacija.OBRISI_PAKET_USLUGA:
                    response.setParams(controller.obrisi((PaketUsluga) request.getParams()));
                    break;
                case Operacija.VRATI_SVE_KATEGORIJE_KLIJENTA:
                    response.setParams(controller.vratiSve(new KategorijaKlijenta()));
                    break;
                case Operacija.DODAJ_KATEGORIJU_KLIJENTA:
                    response.setParams(controller.dodaj((KategorijaKlijenta) request.getParams()));
                    break;
                case Operacija.IZMENI_KATEGORIJU_KLIJENTA:
                    response.setParams(controller.izmeni((KategorijaKlijenta) request.getParams()));
                    break;
                case Operacija.OBRISI_KATEGORIJU_KLIJENTA:
                    response.setParams(controller.obrisi((KategorijaKlijenta) request.getParams()));
                    break;
                case Operacija.VRATI_SVE_KLIJENTE:
                    response.setParams(controller.vratiSve(new Klijent()));
                    break;
                case Operacija.DODAJ_KLIJENTA:
                    response.setParams(controller.dodaj((Klijent) request.getParams()));
                    break;
                case Operacija.IZMENI_KLIJENTA:
                    response.setParams(controller.izmeni((Klijent) request.getParams()));
                    break;
                case Operacija.OBRISI_KLIJENTA:
                    response.setParams(controller.obrisi((Klijent) request.getParams()));
                    break;
                case Operacija.DODAJ_UGOVOR:
                    UgovorSaStavkama ugovorSaStavkama = (UgovorSaStavkama) request.getParams();
                    response.setParams(controller.kreirajUgovor(ugovorSaStavkama.getUgovor(), ugovorSaStavkama.getStavke()));
                    break;
                case Operacija.VRATI_SVE_UGOVORE:
                    response.setParams(controller.vratiSve(new Ugovor()));
                    break;
                case Operacija.VRATI_SVE_STAVKE:
                    response.setParams(controller.vratiSve(new StavkaUgovora()));
                    break;
                case Operacija.IZMENI_UGOVOR:
                    response.setParams(controller.izmeni((Ugovor) request.getParams()));
                    break;
                case Operacija.VRATI_SVE_RADNO_VREME:
                    response.setParams(controller.vratiSve(new RadnoVreme()));
                    break;
                case Operacija.VRATI_SVE_SMENE:
                    response.setParams(controller.vratiSve(new Smena()));
                    break;
                case Operacija.DODAJ_RADNO_VREME:
                    response.setParams(controller.dodaj((RadnoVreme) request.getParams()));
                    break;
                case Operacija.IZMENI_RADNO_VREME:
                    response.setParams(controller.izmeni((RadnoVreme) request.getParams()));
                    break;
                case Operacija.OBRISI_RADNO_VREME:
                    response.setParams(controller.obrisi((RadnoVreme) request.getParams()));
                    break;
                case Operacija.PRIJAVI_KLIJENT:
                    response.setParams(controller.vrati((Klijent) request.getParams()));
                    break;
                default:
                    throw new AssertionError();
            }

            sender.send(response);
        }
    }

}
