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

import tj.python.Adapter.MasalaRecyclerAdapter;
import tj.python.Adapter.RecyclerTouchListener;
import tj.python.DataServer.DataServer;
import tj.python.MasalaActivity;
import tj.python.Models.Masala;
import tj.python.R;


public class MasalaFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView rv_list;
    SwipeRefreshLayout loading;
    List<Masala> list;

    public static MasalaFragment newInstance() {
        MasalaFragment fragment = new MasalaFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        loading = (SwipeRefreshLayout) rootView.findViewById(R.id.main_swipe);
        rv_list = (RecyclerView) rootView.findViewById(R.id.lv_news);

        list = DataServer.getMasala(getActivity());
        rv_list.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(new MasalaRecyclerAdapter(getActivity(), list));
        loading.setOnRefreshListener(this);
        onRefresh();

        rv_list.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_list, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getActivity(), MasalaActivity.class);
                i.putExtra("shart", list.get(position).getShart());
                i.putExtra("title", list.get(position).getTitle());
                i.putExtra("kod", list.get(position).getKod());
                i.putExtra("img", list.get(position).getNatijaImg());

                startActivity(i);
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

}

