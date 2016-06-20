package com.ashokslsk.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ashokslsk.JokeProvider;
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
//        Log.d(TAG, "getNewJoke() called with: " + "");
//        tvPrintJoke.setText(JokeProvider.getNewJoke());

        Intent i = new Intent(this, JokeDisplayingActivity.class);
        i.putExtra(JokeDisplayingActivity.EXTRA_JOKE, JokeProvider.getNewJoke());
        startActivity(i);
    }
}
