package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import pom2.poly.com.jokedisplay.JokeDisplayActivityMainActivity;


public class MainActivity extends ActionBarActivity  {

    private Button btTellJoke;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btTellJoke = (Button) findViewById(R.id.btTellJoke);
        btTellJoke.setVisibility(View.VISIBLE);

        //prepare the Interstitial  AD
        // Create the InterstitialAd and set the adUnitId.
        mInterstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));//ca-app-pub-3940256099942544/1033173712
//set the action after close,we want to open a new activity to show the joke
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                //do something here
                showJokeDisplayAvtivity();

            }
        });

        //load the InterstitialAd
        if (!mInterstitialAd.isLoading() && !mInterstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(adRequest);
        }

    }
private void showJokeDisplayAvtivity(){
    EndpointsAsyncTask task = new EndpointsAsyncTask();
    task.setBw(new EndpointsAsyncTask.BackWard() {
        @Override
        public void taskReturn(String taskresult) {
            Intent displayIntent = new Intent(getApplicationContext(), JokeDisplayActivityMainActivity.class);
            displayIntent.putExtra(JokeDisplayActivityMainActivity.GET_JOKE_KEY, taskresult);
            startActivity(displayIntent);
        }
    });
    task.execute(getApplicationContext());
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        /*String joke=new JokeTeller().getJoke();
        Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();*/
        //when click the button ,if ad load,show the AD,if not yet,go to the joke show activity
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else{
            showJokeDisplayAvtivity();
        }

    }



}
