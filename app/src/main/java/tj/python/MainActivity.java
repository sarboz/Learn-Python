package tj.python;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import tj.python.Fragments.BookFragment;
import tj.python.Fragments.FavsFragment;
import tj.python.Fragments.MasalaFragment;
import tj.python.Fragments.MavzuFragment;
import tj.python.Fragments.MazuDataFragment;
import tj.python.Fragments.SubMavzuFragment;
import tj.python.Fragments.TestViewFragment;

public class MainActivity extends AppCompatActivity implements MavzuFragment.ISelectMavzu,
        SubMavzuFragment.IselectSubMavzu, FavsFragment.ISelectFavs {

    FragmentManager ft = getSupportFragmentManager();
    int id;
    String id_sub = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        id = getIntent().getExtras().getInt("id");
        id_sub = getIntent().getExtras().getString("idSub", "0").toString();

        if (!id_sub.equals("0"))
            onSelectSubMavzu(id_sub);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (id) {
            case R.id.item_1:
                Fragment newFragment = MavzuFragment.newInstance(this);
                ft.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                        .replace(R.id.frame, newFragment).commit();
                break;
            case R.id.item_2:
                Fragment masalaFragment = MasalaFragment.newInstance();
                ft.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                        .replace(R.id.frame, masalaFragment).commit();
                break;
            case R.id.item_3:
                Fragment bookFragment = BookFragment.newInstance();
                ft.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                        .replace(R.id.frame, bookFragment).commit();
                break;
            case R.id.item_4:
                Fragment testFragment = TestViewFragment.newInstance();
                ft.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                        .replace(R.id.frame, testFragment).commit();
                break;
            case R.id.item_5:

                Fragment favFragment = FavsFragment.newInstance(this);
                ft.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                        .replace(R.id.frame, favFragment).commit();
                break;
            case R.id.item_6:
                break;
        }
    }


    @Override
    public void onSelectMavzu(String id) {
        Fragment newFragment = SubMavzuFragment.newInstance(id, this);
        ft.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                .addToBackStack("SubMavzu")
                .replace(R.id.frame, newFragment).commit();
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onSelectSubMavzu(String id) {
        Fragment newFragment = MazuDataFragment.newInstance(id);
        ft.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                .addToBackStack("MazuData")
                .replace(R.id.frame, newFragment).commit();
    }

    @Override
    public void onSelectfav(String id) {
        Fragment newFragment = MazuDataFragment.newInstance(id);
        ft.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                .addToBackStack("MazuData")
                .replace(R.id.frame, newFragment).commit();
    }


}
