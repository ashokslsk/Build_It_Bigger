package com.ashokslsk.builditbigger.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.ashokslsk.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by ashok.kumar on 20/06/16.
 */
public class EndpointsAsyncTask extends AsyncTask<Pair<Context, EndpointsAsyncTask.GotJokeCallback>, Void, String> {
    private MyApi myApiService = null;
    private Context context;
    private GotJokeCallback callback;

    @Override
    protected String doInBackground(Pair<Context, GotJokeCallback>... params) {
        if (myApiService == null) {  // Only do this once


            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://android-app-backend.appspot.com/_ah/api/");


            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0].first;
        callback = params[0].second;

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(callback!=null){
            callback.done();
        }
    }

    public interface GotJokeCallback {
        void done();
    }
}