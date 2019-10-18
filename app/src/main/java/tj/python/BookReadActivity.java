package tj.python;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.joanzapata.pdfview.PDFView;

public class BookReadActivity extends AppCompatActivity {

    PDFView pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_read);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pdf = (PDFView) findViewById(R.id.pdfview);

        String file_name = getIntent().getExtras().getString("file");

        pdf.fromAsset(file_name + ".pdf")
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .swipeVertical(true)
                .load();
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
