package antoniorusso.todolist.activities.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Antonio Russo on 21/02/2017.
 */

public class Nota {
    private String title, descrizione, dataCreazione, dataScadenza;
    Stato status;

    public Nota(String Title, String descrizione, String dataScadenza, Stato status){
        this.title = Title;
        this.descrizione = descrizione;
        this.dataScadenza = dataScadenza;
        SimpleDateFormat d = new SimpleDateFormat("dd MMMMM yyyy");
        this.dataCreazione = d.format(new Date());
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(String dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public String getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(String dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Stato getStatus() {
        return status;
    }

    public void setStatus(Stato status) {
        this.status = status;
    }
}
