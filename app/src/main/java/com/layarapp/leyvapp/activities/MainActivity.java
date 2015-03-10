package com.layarapp.leyvapp.activities;


import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.layar.sdk.LayarSDK;
import com.layarapp.leyvapp.R;
import com.layarapp.leyvapp.callbacks.Callback;
import com.layarapp.leyvapp.utils.Constants;
import com.shamanland.fab.FloatingActionButton;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CompactTweetView;
import com.twitter.sdk.android.tweetui.LoadCallback;
import com.twitter.sdk.android.tweetui.TweetUi;
import com.twitter.sdk.android.tweetui.TweetViewFetchAdapter;

import java.util.Arrays;
import java.util.List;

import io.fabric.sdk.android.Fabric;


public class MainActivity extends ListActivity {

    public final static String TAG = "MainActivity";

    LinearLayout tweetsContainer;
    private FloatingActionButton fltBtnLaunchLayar;
    private ListView tweetList;



    List<Long> tweetIds = Arrays.asList(503435417459249153L,
            510908133917487104L,
            473514864153870337L,
            510908133917487104L,
            473514864153870337L,
            510908133917487104L,
            473514864153870337L,
            510908133917487104L,
            473514864153870337L,
            510908133917487104L,
            473514864153870337L,
            477788140900347904L);
    final TweetViewFetchAdapter adapter =
            new TweetViewFetchAdapter<CompactTweetView>(
                    MainActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new TweetUi());
        setContentView(R.layout.activity_main);

        setupUI();

        setListAdapter(adapter);
        adapter.setTweetIds(tweetIds,
                new LoadCallback<List<Tweet>>() {
                    @Override
                    public void success(List<Tweet> tweets) {
                        Toast.makeText(getApplicationContext(), "I found some tweets", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        // Toast.makeText(...).show();
                    }
                });

    }

    private void setupUI(){

        tweetsContainer = (LinearLayout) findViewById(R.id.tweets_container);

        fltBtnLaunchLayar = (FloatingActionButton) findViewById(R.id.launch_layar_btn_flt);
        fltBtnLaunchLayar.setColor(getResources().getColor(R.color.tw__solid_white));
        fltBtnLaunchLayar.setSize(FloatingActionButton.SIZE_NORMAL);
        fltBtnLaunchLayar.initBackground();

        fltBtnLaunchLayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch(v);
            }
        });

        tweetList = (ListView) findViewById(android.R.id.list);
        //tweetList.setOnTouchListener(new ShowHideOnScroll(fltBtnLaunchLayar));

    }


    public void launch(View view){

        String oauthKey = "LOngRbjGIkDiaQAe";
        String oauthSecret = "nxqMjShYyZFadPIvsgTpLNWUlKukbECO";
        LayarSDK.initialize(this, oauthKey, oauthSecret);
        LayarSDK.startLayarActivity(this, new Callback());
    }
}
