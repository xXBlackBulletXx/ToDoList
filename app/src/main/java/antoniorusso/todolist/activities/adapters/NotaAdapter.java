package antoniorusso.todolist.activities.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import antoniorusso.todolist.R;
import antoniorusso.todolist.activities.models.Nota;

/**
 * Created by Antonio Russo on 21/02/2017.
 */

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaVH> {
    ArrayList<Nota> noteList = new ArrayList<>();

    public NotaAdapter() {}

    public void addNote(Nota e){
        noteList.add(e);
        notifyDataSetChanged();
    }

    @Override
    public NotaVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, null);

        return new NotaVH(v);
    }

    @Override
    public void onBindViewHolder(NotaVH holder, int position) {
        holder.titleTv.setText(noteList.get(position).getTitle());
        holder.dateTv.setText(noteList.get(position).getDataScadenza());
        holder.descriptionTv.setText(noteList.get(position).getDescrizione());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class NotaVH extends RecyclerView.ViewHolder implements View.OnLongClickListener{

        TextView titleTv, descriptionTv, dateTv;

        public NotaVH(View itemView) {
            super(itemView);
            titleTv = (TextView) itemView.findViewById(R.id.item_note_title);
            descriptionTv = (TextView) itemView.findViewById(R.id.item_note_description);
            dateTv = (TextView) itemView.findViewById(R.id.item_note_data);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
}
