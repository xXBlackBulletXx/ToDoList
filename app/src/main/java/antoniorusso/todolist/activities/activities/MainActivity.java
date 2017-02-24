package antoniorusso.todolist.activities.activities;

import android.content.Context;
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
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    ActionMode mActionMode;
    int Pos = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NoteRV = (RecyclerView) findViewById(R.id.note_rv);
        adapter = new NotaAdapter();
        NoteLLM = new LinearLayoutManager(this);
        NoteRV.setAdapter(adapter);
        NoteRV.setLayoutManager(NoteLLM);
        adapter.setOnItemLongClickListener(new NotaAdapter.OnItemLongClickListener() {
            @Override
            public void onLongClick(int position) {
                Pos = position;
                startActionMode(mActionModeCallback);
            }
        });
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
            case 200:
                adapter.getNota(Pos).setTitle(data.getStringExtra("TITOLO"));
                adapter.getNota(Pos).setDescrizione(data.getStringExtra("DESCRIZIONE"));
                adapter.getNota(Pos).setDataScadenza(data.getStringExtra("DATASCADENZA"));
                adapter.notifyItemChanged(Pos);
                break;
        }
    }

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menunote, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.elimina_item:
                    adapter.removeNota(Pos);
                    adapter.notifyItemChanged(Pos);
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.modifica_toolbar_item:
                    Intent i = new Intent(MainActivity.this, FormActivity.class);
                    i.putExtra("Title", adapter.getNota(Pos).getTitle());
                    i.putExtra("Description", adapter.getNota(Pos).getDescrizione());
                    i.putExtra("DataScadenza", adapter.getNota(Pos).getDataScadenza());
                    i.putExtra("POSITION", Pos);
                    startActivityForResult(i, 200);
                    mode.finish();
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };
}
