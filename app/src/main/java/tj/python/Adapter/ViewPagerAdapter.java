package tj.python.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import tj.python.Fragments.MavzuFragment;
import tj.python.Models.Mavzu;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    List<Mavzu> list = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm, List<Mavzu> l) {
        super(fm);
        list = l;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getName();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public MavzuFragment getItem(int i) {
        return new MavzuFragment();
    }
}

