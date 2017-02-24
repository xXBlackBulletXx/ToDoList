package antoniorusso.todolist.activities.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.view.ActionMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

import antoniorusso.todolist.R;
import antoniorusso.todolist.activities.activities.MainActivity;
import antoniorusso.todolist.activities.models.Nota;

/**
 * Created by Antonio Russo on 21/02/2017.
 */

public class NotaAdapter extends RecyclerView.Adapter<NotaAdapter.NotaVH> {
    ArrayList<Nota> noteList = new ArrayList<>();
    public static int POSITION = -1;

    public NotaAdapter() {}

    public void removeNota(int pos){
        noteList.remove(pos);
    }

    public void addNote(Nota e){
        noteList.add(e);
        notifyDataSetChanged();
    }

    public Nota getNota(int pos){
        return noteList.get(pos);
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


    public OnItemLongClickListener getOnItemLongClickListener() {
        return onItemLongClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    OnItemLongClickListener onItemLongClickListener;

    public interface OnItemLongClickListener{

        void onLongClick(int position);
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
            onItemLongClickListener.onLongClick(getAdapterPosition());
            /*ColorDrawable mycolor = (ColorDrawable) view.getBackground();
            int mc = mycolor.getColor();
            if (mc == Color.LTGRAY){
                view.setBackgroundColor(Color.WHITE);
            }else {
                view.setBackgroundColor(Color.LTGRAY);
            }*/
            return false;
        }
    }
}
