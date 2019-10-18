package tj.python.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tj.python.Models.Book;
import tj.python.Models.Masala;
import tj.python.R;

public class MasalaRecyclerAdapter extends RecyclerView.Adapter<MasalaRecyclerAdapter.Holder> {
    List<Masala> list;
    Context mctx;

    public MasalaRecyclerAdapter(Context mctx, List<Masala> list) {
        this.list = list;
        this.mctx = mctx;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new Holder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.title.setText(list.get(i).getTitle());
        holder.text.setText(list.get(i).getShart());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView text,title;

        public Holder(@NonNull View v) {
            super(v);
            title = (TextView) v.findViewById(R.id.title);
            text = (TextView) v.findViewById(R.id.text);
        }
    }
}
