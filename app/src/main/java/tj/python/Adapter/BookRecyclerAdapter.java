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
import tj.python.Models.Mavzu;
import tj.python.R;

public class BookRecyclerAdapter extends RecyclerView.Adapter<BookRecyclerAdapter.Holder> {
    List<Book> list;
    Context mctx;

    public BookRecyclerAdapter(Context mctx, List<Book> list) {
        this.list = list;
        this.mctx = mctx;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_book, viewGroup, false);
        return new Holder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.name.setText(list.get(i).getTitle());
        holder.author.setText(list.get(i).getAuthor());
        int id=mctx.getResources().getIdentifier(list.get(i).getImg(),"drawable",mctx.getPackageName());
        holder.img.setImageResource(id);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        TextView name,author;
        ImageView img;

        public Holder(@NonNull View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.name);
            author = (TextView) v.findViewById(R.id.author);
            img = (ImageView) v.findViewById(R.id.image);
        }
    }
}
