/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package thread;

import form.ServerForm;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author Marko
 */
public class StatusThread extends Thread{
    private ServerForm serverForm;

    public StatusThread(ServerForm serverForm) {
        this.serverForm = serverForm;
    }
    
    @Override
    public void run() {
        while (true) {
            SwingUtilities.invokeLater(() -> serverForm.popuniTabeluUlogovaniZaposleni());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Doslo je do greske u statusnoj niti: " + ex.getMessage());
            }
        }
    }
    
}
