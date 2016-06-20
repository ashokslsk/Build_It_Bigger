package com.ashokslsk.joketeller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JokeDisplayingActivity extends AppCompatActivity {

    public static String EXTRA_JOKE = "extra_joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_displaying);

        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString(EXTRA_JOKE, getIntent().getStringExtra(EXTRA_JOKE));

            JokeDisplayFragment frag = new JokeDisplayFragment();
            frag.setArguments(bundle);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, frag)
                    .commit();
        }

    }
}
