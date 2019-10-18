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

import tj.python.Models.Mavzu;
import tj.python.R;

public class MavzuRecyclerAdapter extends RecyclerView.Adapter<MavzuRecyclerAdapter.Holder> {
    List<Mavzu> list;
    Context mctx;

    public MavzuRecyclerAdapter(Context mctx, List<Mavzu> list) {
        this.list = list;
        this.mctx = mctx;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test, viewGroup, false);
        return new Holder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.name.setText(list.get(i).getName());
        int id=mctx.getResources().getIdentifier(list.get(i).getImg(),"drawable",mctx.getPackageName());
        holder.img.setImageResource(id);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;

        public Holder(@NonNull View v) {
            super(v);


            name = (TextView) v.findViewById(R.id.name);
            img = (ImageView) v.findViewById(R.id.image);
        }
    }
}
