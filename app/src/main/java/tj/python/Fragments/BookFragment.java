package tj.python.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tj.python.Adapter.BookRecyclerAdapter;
import tj.python.Adapter.RecyclerTouchListener;
import tj.python.BookReadActivity;
import tj.python.DataServer.DataServer;
import tj.python.Models.Book;
import tj.python.R;


public class BookFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView rv_list;
    SwipeRefreshLayout loading;
    List<Book> list;

    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            loading = (SwipeRefreshLayout) rootView.findViewById(R.id.main_swipe);
            rv_list = (RecyclerView) rootView.findViewById(R.id.lv_news);

            list = DataServer.getBooks(getActivity());
            rv_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            rv_list.setItemAnimator(new DefaultItemAnimator());
            rv_list.setAdapter(new BookRecyclerAdapter(getActivity(), list));
            loading.setOnRefreshListener(this);
            onRefresh();

            rv_list.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_list, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    startActivity(new Intent(getActivity(), BookReadActivity.class).putExtra("file", list.get(position).getFile_name()));

                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));


        return rootView;
    }


    @Override
    public void onRefresh() {
        loading.setRefreshing(false);

    }

    public interface ISelectMavzu {
        void onSelectMavzu(String id);
    }
}

