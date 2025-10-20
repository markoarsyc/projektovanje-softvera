package operacije;

import java.util.List;
import model.StavkaUgovora;
import model.Ugovor;

/**
 *
 * @author Marko
 */
public class IzmeniUgovorSO extends AbstractSystemOperation<Ugovor> {

    private List<StavkaUgovora> noveStavkeUgovora;

    public IzmeniUgovorSO(List<StavkaUgovora> noveStavkeUgovora) {
        this.noveStavkeUgovora = noveStavkeUgovora;
    }

    @Override
    protected void validate(Ugovor ugovor) throws Exception {
        if (ugovor == null) {
            throw new IllegalArgumentException("Ugovor ne sme biti null.");
        }
        if (noveStavkeUgovora == null) {
            throw new IllegalArgumentException("Lista stavki ne sme biti null.");
        }
    }

    @Override
    protected Object executeOperation(Ugovor ugovor) throws Exception {
        //Trenutne stavke za prosledjeni ugovor
        StavkaUgovora kriterijum = new StavkaUgovora();
        kriterijum.setUgovor(ugovor);
        List<StavkaUgovora> postojeceStavke = (List<StavkaUgovora>) dbb.selectAll(kriterijum);
        
        int rb = postojeceStavke.getLast().getRb() + 1;

        //Izmena podataka nevezanih za stavke - status
        dbb.update(ugovor);

        //Brisanje onih koje nisu u izmenjenom ugovoru
        for (StavkaUgovora postojeca : postojeceStavke) {
            boolean pronadjena = false;
            for (StavkaUgovora nova : noveStavkeUgovora) {
                if (nova.getRb() == postojeca.getRb()) {
                    pronadjena = true;
                    break;
                }
            }
            if (!pronadjena) {
                dbb.delete(postojeca);
            }
        }


        //Dodajemo nove stavke na kraj
        for (StavkaUgovora nova : noveStavkeUgovora) {
            if (nova.getRb() == 0) {
                nova.setUgovor(ugovor);
                nova.setRb(rb);
                dbb.insert(nova);
                rb++;
            }
        }

        return ugovor.getIdUgovor();
    }
}
