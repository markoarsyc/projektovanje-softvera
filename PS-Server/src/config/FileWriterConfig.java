/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Marko
 */
public class FileWriterConfig {

    private int port;
    private String connectionUrl;
    private String user;
    private String password;

    public FileWriterConfig() {}

    public FileWriterConfig(int port, String connectionUrl, String user, String password) {
        this.port = port;
        this.connectionUrl = connectionUrl;
        this.user = user;
        this.password = password;
    }

    /**
     * Upisuje sve parametre u konfiguracioni fajl u formatu:
     * port=9000
     * connectionUrl=jdbc:mysql://localhost:3306/mojabaza
     * user=root
     * password=1234
     */
    public void writeFile(String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write("#Konfiguracioni fajl servera\n");
            bw.write("port=" + port + "\n");
            bw.write("connectionUrl=" + connectionUrl + "\n");
            bw.write("user=" + user + "\n");
            bw.write("password=" + password + "\n");
        }
    }

    // Getteri i setteri
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

