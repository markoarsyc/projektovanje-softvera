package server;

import config.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private int port;
    private ServerSocket serverSocket;
    private boolean running = false;

    @Override
    public void run() {
        
        try {
            FileReader fileReader = new FileReader();
            fileReader.readFile("server.config");
            port = fileReader.getPort();
            serverSocket = new ServerSocket(port);
            running = true;
            System.out.println("Server je pokrenut na portu " + port);

            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Klijent povezan");
                    ClientThread clientThread = new ClientThread(clientSocket);
                    clientThread.start();
                } catch (IOException e) {
                    if (running) {
                        System.out.println("Greska prilikom prihvatanja klijenta: " + e.getMessage());
                    } else {
                        System.out.println("Server je zaustavljen.");
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Greska prilikom pokretanja server soketa: " + ex.getMessage());
        }
    }

    public void stopServer() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            System.out.println("Server je uspesno zaustavljen.");
        } catch (IOException e) {
            System.out.println("Greska prilikom zaustavljanja servera: " + e.getMessage());
        }
    }
}
