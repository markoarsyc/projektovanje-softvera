package thread;

import javax.swing.JTextField;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class TimeThread extends Thread {

    private final JLabel lblTime;
    private final JLabel lblDate;
    private final JTextField txtSmena;

    public TimeThread(JLabel lblTime, JLabel lblDate, JTextField txtSmena) {
        this.lblTime = lblTime;
        this.lblDate = lblDate;
        this.txtSmena = txtSmena;
    }

    @Override
    public void run() {
        while (true) {
            Date date = new Date();

            // format za vreme i datum
            DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            // prikaz vremena i datuma
            String vremeStr = timeFormat.format(date);
            String datumStr = dateFormat.format(date);

            lblTime.setText(vremeStr);
            lblDate.setText(datumStr);

            // izdvajanje sati radi određivanja smene
            int sati = Integer.parseInt(vremeStr.substring(0, 2));

            if (sati >= 10 && sati < 16) {
                txtSmena.setText("PRVA");
            } else if (sati >= 16 && sati < 22) {
                txtSmena.setText("DRUGA");
            } else {
                txtSmena.setText("PREKOVREMENO");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TimeThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}