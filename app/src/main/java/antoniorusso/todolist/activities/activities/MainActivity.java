package antoniorusso.todolist.activities.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import antoniorusso.todolist.R;
import antoniorusso.todolist.activities.adapters.NotaAdapter;
import antoniorusso.todolist.activities.models.Nota;
import antoniorusso.todolist.activities.models.Stato;

/**
 * Created by Antonio Russo on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FloatingActionButton addBtn;
    RecyclerView NoteRV;
    LinearLayoutManager NoteLLM;
    NotaAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NoteRV = (RecyclerView) findViewById(R.id.note_rv);
        adapter = new NotaAdapter();
        NoteLLM = new LinearLayoutManager(this);
        NoteRV.setAdapter(adapter);
        NoteRV.setLayoutManager(NoteLLM);
        addBtn = (FloatingActionButton) findViewById(R.id.Add_Button);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Add_Button:
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("CODICI", resultCode + " - " + resultCode);
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 100:
                Nota e = new Nota(data.getStringExtra("TITOLO"), data.getStringExtra("DESCRIZIONE"), data.getStringExtra("DATASCADENZA"), Stato.DA_FARE);
                adapter.addNote(e);
                break;
        }
    }
}
