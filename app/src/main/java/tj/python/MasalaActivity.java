package tj.python;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class MasalaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.masala_hal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle(getIntent().getExtras().get("title").toString());
        String shart = getIntent().getExtras().get("shart").toString();
        String kod = getIntent().getExtras().get("kod").toString();
        String img = getIntent().getExtras().get("img").toString();

        TextView sh = (TextView) findViewById(R.id.shart);
        TextView ko = (TextView) findViewById(R.id.kod);
        TextView im = (TextView) findViewById(R.id.natija);

        sh.setText(shart);
        ko.setText(kod);
        im.setText(img);
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

}
