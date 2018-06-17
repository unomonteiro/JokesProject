package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import io.monteirodev.jokedisplay.JokeActivity;

import static io.monteirodev.jokedisplay.JokeActivity.EXTRA_JOKE;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private InterstitialAd interstitialAd;
    private ProgressBar mProgressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mProgressBar = root.findViewById(R.id.progressBar);
        Button jokeButton = root.findViewById(R.id.joke_button);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInterstitial();
            }
        });

        // github.com/googleads/googleads-mobile-android-examples/ ... /InterstitialExample
        MobileAds.initialize(getContext(), getString(R.string.ca_app_pub));
        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(getContext());
        // Defined in res/values/strings.xml
        interstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestCloudJoke();
            }
        });

        return root;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and restart the game.
        if (interstitialAd != null && interstitialAd.isLoaded()) {
            interstitialAd.show();
        } else {
            requestCloudJoke();
        }
    }

    /** https://stackoverflow.com/a/29103920/6997703 */
    @SuppressLint("StaticFieldLeak")
    private void requestCloudJoke() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded()) {
            AdRequest adRequest = new AdRequest.Builder().build();
            interstitialAd.loadAd(adRequest);
        }

        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(){
            @Override
            protected void onPostExecute(String result) {
                mProgressBar.setVisibility(View.GONE);
                launchJokeDisplayIntent(result);
            }
        }.execute();
    }

    private void launchJokeDisplayIntent(String joke) {
        Intent jokeDisplayIntent = new Intent(getContext(), JokeActivity.class);
        jokeDisplayIntent.putExtra(EXTRA_JOKE, joke);
        startActivity(jokeDisplayIntent);
    }
}
