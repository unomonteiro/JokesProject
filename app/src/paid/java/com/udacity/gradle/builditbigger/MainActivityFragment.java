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

import io.monteirodev.jokedisplay.JokeActivity;

import static io.monteirodev.jokedisplay.JokeActivity.EXTRA_JOKE;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

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
                requestCloudJoke();
            }
        });
        return root;
    }

    @SuppressLint("StaticFieldLeak")
    private void requestCloudJoke() {
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
