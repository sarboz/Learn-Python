package tj.python;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tj.python.DataServer.DataServer;
import tj.python.Models.Test;
import tj.python.Utils.Speech;

public class TestActivity extends AppCompatActivity {
    List<Test> list = new ArrayList<>();

    RadioButton r1, r2, r3, r4, rbSelected;
    RadioGroup rd;
    TextView savol, textTimer;
    Button btn;
    ProgressBar pbar;
    int result = 0;
    int pos = 0;
    int time = 30;
    CountDownTimer c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        list = DataServer.getTests(this);
        c = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                textTimer.setText("0:" + checkDigit(time));
                time--;
            }

            public void onFinish() {
                pos++;
                setData(pos);
                pbar.setProgress(pbar.getProgress() + 1);
            }
        };
        initView();
        pbar.setMax(list.size());
        setData(0);
    }

    private CountDownTimer timer() {
        time = 30;
        return c;
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

    Speech s = new Speech(this);
    private void initView() {
        rd = (RadioGroup) findViewById(R.id.rd);
        r1 = (RadioButton) findViewById(R.id.test1);
        r2 = (RadioButton) findViewById(R.id.test2);
        r3 = (RadioButton) findViewById(R.id.test3);
        r4 = (RadioButton) findViewById(R.id.test4);
        savol = (TextView) findViewById(R.id.savol);
        textTimer = (TextView) findViewById(R.id.timer);
        btn = (Button) findViewById(R.id.button);
        pbar = (ProgressBar) findViewById(R.id.pBar);
    }

    private void showdialog(int result) {
        AlertDialog.Builder alert;
        alert = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Шумо аз " + list.size() + " - то савол " + result + " ҷавоби дуруст додед.")
                .setTitle("Натиҷа")
                .setPositiveButton("Фаҳмидам", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        alert.create().show();
    }


    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    private void setData(int pos) {
        if (pos == list.size()) {
            showdialog(result);
            btn.setText("finish");
            btn.setEnabled(false);
            return;
        }
        savol.setText(list.get(pos).getSavol());
        r1.setText(list.get(pos).getVariants().get(0));
        r2.setText(list.get(pos).getVariants().get(1));
        r3.setText(list.get(pos).getVariants().get(2));
        r4.setText(list.get(pos).getVariants().get(3));
        timer().start();
    }


    public void next(View view) {
        int selectedId = rd.getCheckedRadioButtonId();
        if (selectedId > 0 && pos < list.size()) {
            timer().cancel();
            check(pos, selectedId);
            pos++;
            setData(pos);
            pbar.setProgress(pbar.getProgress() + 1);
            rd.clearCheck();

        } else {
            Toast.makeText(this, "Select radio", Toast.LENGTH_SHORT).show();
        }
    }


    public void check(int pos, int idSelectes) {
        rbSelected = (RadioButton) findViewById(idSelectes);
        if (list.get(pos).getJavob().equals(rbSelected.getText().toString())) {
            result++;
            s.speechMedia("er");
            return;
        }
        s.speechMedia("er2");
    }
}
