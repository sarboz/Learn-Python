package tj.python;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.File;

import tj.python.Fragments.SearchSubMavzuFragment;

import static tj.python.Fragments.SearchSubMavzuFragment.newInstance;


public class WelcomeActivity extends AppCompatActivity implements ISearchClick {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.nav_bottom);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.share:
                                try {
                                    ApplicationInfo app = getApplicationContext().getApplicationInfo();
                                    String filePath = app.sourceDir;
                                    Intent intent = new Intent(Intent.ACTION_SEND);
                                    intent.setType("*/*");
                                    intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
                                    startActivity(Intent.createChooser(intent, "Share app via"));
                                    return true;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.like:
                                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                                try {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                                }
                                break;
                            case R.id.exit:
                                System.exit(0);
                                finish();
                                break;
                        }
                        return false;
                    }
                });
    }

    public void quicStart(View view) {
        int id = view.getId();
        startActivity(new Intent(this, MainActivity.class).putExtra("id", id));
    }

    FragmentManager ft = getSupportFragmentManager();


    ISearch search;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = newInstance(WelcomeActivity.this);
                search = ((SearchSubMavzuFragment) newFragment);
                ft.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .setCustomAnimations(R.animator.side_in, R.animator.side_out)
                        .addToBackStack("SubMavzu")
                        .replace(R.id.frame, newFragment).commit();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search.search(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void OnSearch(String id) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("idSub", id);
        startActivity(i);
    }

    public void about(View view) {
        startActivity(new Intent(this,AboutActivity.class));

    }

    public interface ISearch {
        void search(String str);
    }

}