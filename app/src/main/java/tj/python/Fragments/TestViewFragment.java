package tj.python.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tj.python.R;
import tj.python.TestActivity;


public class TestViewFragment extends android.support.v4.app.Fragment implements View.OnClickListener {



    public static TestViewFragment newInstance() {
        TestViewFragment fragment = new TestViewFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View r = inflater.inflate(R.layout.start_test, container, false);

        Button button = (Button) r.findViewById(R.id.btnstart);
        button.setOnClickListener(this);


        return r;
    }

    @Override
    public void onClick(View v) {

        startActivity(new Intent(getActivity(), TestActivity.class));
        getActivity().getFragmentManager().popBackStack();
    }
}

