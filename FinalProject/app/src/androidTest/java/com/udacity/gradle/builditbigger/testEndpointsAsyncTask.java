package com.udacity.gradle.builditbigger;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class testEndpointsAsyncTask extends ActivityInstrumentationTestCase2<MainActivity> {


    public testEndpointsAsyncTask() {
        super(MainActivity.class);
    }


    public void testtheEndpointsAsyncTask() throws Throwable {
        final String[] testedResult = new String[1];
       final CountDownLatch signal=new CountDownLatch(1);

        final EndpointsAsyncTask task=new EndpointsAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
//                super.onPostExecute(result);

                testedResult[0] =result;
                signal.countDown();

            }
        };

        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
//                task.execute(getActivity());
            }
        });
        task.execute(getActivity());
        signal.await(30, TimeUnit.SECONDS);
        assertFalse(testedResult[0].equals(""));



    }


}
