package com.ashokslsk.builditbigger;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokslsk.builditbigger.data.EndpointsAsyncTask;
import com.ashokslsk.joketeller.JokeDisplayingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //Views
    @Bind(R.id.main_tv_print_joke)
    TextView tvPrintJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.main_b_show_joke)
    void getNewJoke() {

        //Java Library
//        Log.d(TAG, "getNewJoke() called with: " + "");
//        tvPrintJoke.setText(JokeProvider.getNewJoke());

        //Using Android Library
//        Intent i = new Intent(this, JokeDisplayingActivity.class);
//        i.putExtra(JokeDisplayingActivity.EXTRA_JOKE, JokeProvider.getNewJoke());
//        startActivity(i);


        final AsyncTask<EndpointsAsyncTask.GotJokeCallback, Void, String> processGetJoke = new EndpointsAsyncTask();

        ProgressBar pb = new ProgressBar(this);
        pb.setIndeterminate(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Loading joke. Please wait...")
                .setView(pb)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        processGetJoke.cancel(true);
                        dialog.dismiss();
                    }
                })
                .setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        processGetJoke.execute(new EndpointsAsyncTask.GotJokeCallback() {
            @Override
            public void done(String result, boolean error) {
                dialog.dismiss();
                if (error) {
                    Log.e("error text", result);
                    Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(MainActivity.this, JokeDisplayingActivity.class);
                    i.putExtra(JokeDisplayingActivity.EXTRA_JOKE, result);
                    startActivity(i);
                }
            }
        });
    }

}
