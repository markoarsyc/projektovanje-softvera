/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Marko
 */
public class FileReader {
    private int port;
    private String connectionUrl;
    private String user;
    private String password;

    public void readFile(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) {
                    continue; // preskace prazne linije i komentare
                }

                String[] parts = line.split("=");
                if (parts.length != 2) continue;

                String key = parts[0].trim();
                String value = parts[1].trim();

                switch (key) {
                    case "port":
                        port = Integer.parseInt(value);
                        break;
                    case "connectionUrl":
                        connectionUrl = value;
                        break;
                    case "user":
                        user = value;
                        break;
                    case "password":
                        password = value;
                        break;
                }
            }
        }
    }

    public int getPort() {
        return port;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "FileReader{" + 
                "port=" + port + 
                ", connectionUrl='" + connectionUrl + '\'' + 
                ", user='" + user + '\'' + 
                ", password='" + password + '\'' + 
                '}';
    }
}

