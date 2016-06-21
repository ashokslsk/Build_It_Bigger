package com.ashokslsk.builditbigger.paid.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ashokslsk.builditbigger.R;
import com.ashokslsk.builditbigger.activity.MainActivity;
import com.ashokslsk.builditbigger.data.EndpointsAsyncTask;
import com.ashokslsk.joketeller.JokeDisplayingActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;



/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    FragmentActivity con;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        con = getActivity();
        ButterKnife.bind(this, view);

        ((MainActivity) con).setSupportActionBar(toolbar);

        return view;
    }

    @OnClick(R.id.main_b_show_joke)
    void showNewJoke() {
        final AsyncTask<EndpointsAsyncTask.GotJokeCallback, Void, String> processGetJoke = new EndpointsAsyncTask();

        ProgressBar pb = new ProgressBar(con);
        pb.setIndeterminate(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(con);
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
                    Toast.makeText(con, result, Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(con, JokeDisplayingActivity.class);
                    i.putExtra(JokeDisplayingActivity.EXTRA_JOKE, result);
                    startActivity(i);
                }
            }
        });
    }

}
