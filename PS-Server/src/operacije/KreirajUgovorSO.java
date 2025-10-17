/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije;

import java.util.List;
import model.StavkaUgovora;
import model.Ugovor;

/**
 *
 * @author Marko
 */
public class KreirajUgovorSO extends AbstractSystemOperation<Ugovor>{

    private List<StavkaUgovora> stavkeUgovora;
    
    public KreirajUgovorSO(List<StavkaUgovora> stavkeUgovora) {
        this.stavkeUgovora = stavkeUgovora;
    }
    
    @Override
    protected void validate(Ugovor ugovor) throws Exception {
        if (ugovor == null) {
            throw new IllegalArgumentException("Ugovor ne sme biti null");
        }
        if (stavkeUgovora == null || stavkeUgovora.isEmpty()) {
            throw new IllegalArgumentException("Ugovor mora imati barem jednu stavku");
        }
    }

    @Override
    protected Object executeOperation(Ugovor ugovor) throws Exception {
        int rb = 1;
        int ugovorId = (int) dbb.insert(ugovor);
        ugovor.setIdUgovor(ugovorId);
        for (StavkaUgovora stavka : stavkeUgovora) {
            stavka.setRb(rb);
            stavka.setUgovor(ugovor);
            dbb.insert(stavka);
            rb++;
        }
        return ugovor.getIdUgovor();
    }
    
}
