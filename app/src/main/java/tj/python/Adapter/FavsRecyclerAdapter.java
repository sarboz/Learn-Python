package tj.python.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import tj.python.DataServer.DataServer;
import tj.python.Models.SubMavzu;
import tj.python.R;

public class FavsRecyclerAdapter extends RecyclerView.Adapter<FavsRecyclerAdapter.Holder> {
    List<SubMavzu> list;
    Context mctx;

    public FavsRecyclerAdapter(Context mctx, List<SubMavzu> list) {
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
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.name.setText(list.get(i).getName());



        holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    DataServer.setFav(mctx, 1, list.get(i).getId());
                else DataServer.setFav(mctx, 0, list.get(i).getId());
            }
        });
        int id = mctx.getResources().getIdentifier(list.get(i).getImg(), "drawable", mctx.getPackageName());
        holder.img.setImageResource(id);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView img;
        CheckBox fav;

        public Holder(@NonNull View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            img = (ImageView) v.findViewById(R.id.image);
            fav = (CheckBox) v.findViewById(R.id.fav);
        }
    }
}
