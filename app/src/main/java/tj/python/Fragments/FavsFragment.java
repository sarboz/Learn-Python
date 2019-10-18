package tj.python.Fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tj.python.Adapter.RecyclerTouchListener;
import tj.python.Adapter.SubMavzuRecyclerAdapter;
import tj.python.DataServer.DataServer;
import tj.python.Models.SubMavzu;
import tj.python.R;


public class FavsFragment extends android.support.v4.app.Fragment implements SwipeRefreshLayout.OnRefreshListener {


    RecyclerView rv_list;
    SwipeRefreshLayout loading;
    List<SubMavzu> list;
    static ISelectFavs iSelectFavs;

    public static FavsFragment newInstance(ISelectFavs i) {
        FavsFragment fragment = new FavsFragment();
        iSelectFavs = i;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        loading = (SwipeRefreshLayout) rootView.findViewById(R.id.main_swipe);
        rv_list = (RecyclerView) rootView.findViewById(R.id.lv_news);

        list = DataServer.getSubFavs(getActivity());
        rv_list.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv_list.setItemAnimator(new DefaultItemAnimator());
        rv_list.setAdapter(new SubMavzuRecyclerAdapter(getActivity(), list));
        loading.setOnRefreshListener(this);
        onRefresh();

        rv_list.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rv_list, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                iSelectFavs.onSelectfav(list.get(position).getId());
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

    public interface ISelectFavs {
        void onSelectfav(String id);
    }
}

