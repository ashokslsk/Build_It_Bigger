package com.ashokslsk.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.ashokslsk.builditbigger.data.EndpointsAsyncTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by ashok.kumar on 21/06/16.
 */
public class JokesTest extends ApplicationTestCase<Application> implements EndpointsAsyncTask.GotJokeCallback {

    CountDownLatch signal;
    String joke;

    public JokesTest() {
        super(Application.class);
    }

    @SmallTest
    public void testJoke() {
        try {
            signal = new CountDownLatch(1);
            new EndpointsAsyncTask().execute(this);
            signal.await(10, TimeUnit.SECONDS);
            assertNotNull("joke is null", joke);
            assertFalse("joke is empty", joke.isEmpty());
        } catch (Exception ex) {
            fail();
        }
    }

    @Override
    public void done(String result, boolean error) {
        this.joke = result;
        signal.countDown();
    }
}