package antoniorusso.todolist.activities.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toolbar;

import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import antoniorusso.todolist.R;

/**
 * Created by Antonio Russo on 22/02/2017.
 */

public class FormActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener{
    EditText titleTV, descriptionTV;
    CalendarView calendar;
    static  String GIORNO, MESE, ANNO;
    static int POS = -1;
    Intent i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ativity_add_modify);
        titleTV = (EditText) findViewById(R.id.title_ET);
        descriptionTV = (EditText) findViewById(R.id.description_ET);
        calendar = (CalendarView) findViewById(R.id.data_CV);
        calendar.setOnDateChangeListener(this);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        i = getIntent();
        if(i.getStringExtra("Title")!= null){
            titleTV.setText(i.getStringExtra("Title"));
            descriptionTV.setText(i.getStringExtra("Description"));
            try {
                String dateString = i.getStringExtra("DataScadenza");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(dateString);

                long startDate = date.getTime();

                calendar.setDate(startDate);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            POS = i.getIntExtra("POSITION", -1);
            Log.i("POSITIONSETTED", String.valueOf(POS));
        }
    }

    @Override
    public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
        GIORNO = String.valueOf(i2);
        MESE = String.valueOf(i1);
        ANNO = String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menuform, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.conferma_item:
                if(GIORNO != null && !titleTV.getText().toString().isEmpty() && !descriptionTV.getText().toString().isEmpty()){

                    Intent intent = new Intent();
                    intent.putExtra("TITOLO", titleTV.getText().toString());
                    intent.putExtra("DESCRIZIONE", descriptionTV.getText().toString());
                    intent.putExtra("DATASCADENZA", GIORNO + "/" + (Integer.parseInt(MESE)+1) + "/" + ANNO);
                    if(POS != -1){
                        Log.i("INTENT", "200");
                        setResult(200, intent);
                        POS = -1;
                    }else{
                        Log.i("INTENT", "100");
                        setResult(100, intent);
                    }

                    finish();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);

                    builder.setMessage("Dati non inseriti")
                            .setTitle("Errore")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User clicked OK button
                                }
                            });

                    builder.create().show();
                }
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
