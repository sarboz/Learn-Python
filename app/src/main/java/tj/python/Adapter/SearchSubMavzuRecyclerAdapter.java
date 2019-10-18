package tj.python.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tj.python.DataServer.DataServer;
import tj.python.Models.SubMavzu;
import tj.python.R;

public class SearchSubMavzuRecyclerAdapter extends RecyclerView.Adapter<SearchSubMavzuRecyclerAdapter.Holder> {
    List<SubMavzu> list=new ArrayList<>();
String ch;
    Context mctx;

    public SearchSubMavzuRecyclerAdapter(Context mctx, List<SubMavzu> list,String ch) {
        this.list = list;
        this.mctx = mctx;
        this.ch=ch;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_test, viewGroup, false);
        return new Holder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        holder.name.setText(Html.fromHtml(list.get(i).getName().replace(ch,"<i><b><font color='red'>"+ch+"</font></b></i>")));
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
        public Holder(@NonNull View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            img = (ImageView) v.findViewById(R.id.image);

        }
    }
}
