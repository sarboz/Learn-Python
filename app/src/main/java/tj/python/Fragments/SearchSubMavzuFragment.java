package tj.python.Fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tj.python.Adapter.RecyclerTouchListener;
import tj.python.Adapter.SearchSubMavzuRecyclerAdapter;
import tj.python.DataServer.DataServer;
import tj.python.ISearchClick;
import tj.python.Models.SubMavzu;
import tj.python.R;
import tj.python.WelcomeActivity;


public class SearchSubMavzuFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener, WelcomeActivity.ISearch {


    RecyclerView rv_list;
    SwipeRefreshLayout loading;
    static ISearchClick iseach;
    List<SubMavzu> list;
    SearchSubMavzuRecyclerAdapter adapter;

    public static SearchSubMavzuFragment newInstance(ISearchClick i) {
        SearchSubMavzuFragment fragment = new SearchSubMavzuFragment();
        iseach = i;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        loading = (SwipeRefreshLayout) rootView.findViewById(R.id.main_swipe);
        rv_list = (RecyclerView) rootView.findViewById(R.id.lv_news);

        list = DataServer.getAllSubMavzu(getActivity());

        rv_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv_list.setItemAnimator(new DefaultItemAnimator());
        adapter = new SearchSubMavzuRecyclerAdapter(getActivity(), list, "");
        rv_list.setAdapter(adapter);
        loading.setOnRefreshListener(this);
        onRefresh();

        rv_list.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_list, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                iseach.OnSearch(list.get(position).getId());
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

    List<SubMavzu> sub = new ArrayList<>();

    @Override
    public void search(String str) {

        sub = DataServer.getAllSubMavzu(getActivity(), str);
        if (!str.isEmpty())
            rv_list.setAdapter(new SearchSubMavzuRecyclerAdapter(getActivity(), sub, str));
        else
            rv_list.setAdapter(new SearchSubMavzuRecyclerAdapter(getActivity(), list, ""));
    }


}


