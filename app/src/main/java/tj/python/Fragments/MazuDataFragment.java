package tj.python.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import tj.python.DataServer.DataServer;
import tj.python.R;
import tj.python.TestSubMavzuActivity;

public class MazuDataFragment extends Fragment implements View.OnClickListener {
    String id;

    public static MazuDataFragment newInstance(String idSubMavzu) {
        MazuDataFragment fragment = new MazuDataFragment();
        Bundle args = new Bundle();
        args.putString("id", idSubMavzu);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mazu_data, container, false);
        id = getArguments().getString("id");

        TextView data = (TextView) v.findViewById(R.id.data);
        data.setText(DataServer.getDataSubMavzu(getActivity(), id).get(0).getText());
        Button btn = v.findViewById(R.id.btn);
        btn.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getContext(), TestSubMavzuActivity.class);
        i.putExtra("id", id);
        startActivity(i);
    }
}
