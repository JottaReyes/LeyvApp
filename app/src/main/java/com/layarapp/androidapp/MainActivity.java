package com.layarapp.androidapp;


import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.layar.sdk.LayarSDK;
import com.layarapp.androidapp.utils.Constants;
import com.layarapp.androidapp.utils.NetworkUtility;
import com.shamanland.fab.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.twitter.sdk.android.tweetui.TweetUi;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetui.TweetUtils;


import java.util.Random;

import io.fabric.sdk.android.Fabric;

/**
 * Clase Controladora Principal
 */
public class MainActivity extends Activity {


    public final static String TAG = "MainActivity";

    private FloatingActionButton fltBtnLaunchLayar;
    private TwitterLoginButton loginButton;
    private Button tweetButton;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(Constants.TWITTER_KEY, Constants.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig), new TweetUi());
        setContentView(R.layout.activity_main);

        Fabric.with(this, new TweetComposer(), new Twitter(authConfig));

        setupUI();

        Picasso.with(this)
                .load(Constants.getUrl(randInt(1,7)))
                .centerCrop()
                .fit()
                .into(imageView);

        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {

                loginButton.setVisibility(View.GONE);
                tweetButton.setVisibility(View.VISIBLE);

                Picasso.with(getApplicationContext())
                        .load(Constants.getUrl(randInt(1,7)))
                        .centerCrop()
                        .fit()
                        .into(imageView);

                tweetButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tweet();
                    }
                });

            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(),"Intenta de nuevo",
                        Toast.LENGTH_SHORT).show();
            }
        });

        fltBtnLaunchLayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch(v);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }

    private void tweet(){

        if(NetworkUtility.isConnected(getApplicationContext())){
            TweetComposer.Builder builder = new TweetComposer.Builder(this)
                    .text("Descubrí un lugar genial gracias a #Leyvapp");

            builder.show();
        }else{
            Toast.makeText(getApplicationContext(),"por favor verifique su conexión a la red",
                    Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * Binding de los elementos de la UI
     */
    private void setupUI(){

        fltBtnLaunchLayar = (FloatingActionButton) findViewById(R.id.launch_layar_btn_flt);
        fltBtnLaunchLayar.setColor(getResources().getColor(R.color.tw__solid_white));
        fltBtnLaunchLayar.setSize(FloatingActionButton.SIZE_NORMAL);
        fltBtnLaunchLayar.initBackground();
        loginButton = (TwitterLoginButton)findViewById(R.id.login_button);
        tweetButton = (Button)findViewById(R.id.tweet_btn);
        imageView = (ImageView)findViewById(R.id.imageView);

    }

    /**
     * Lanza el LayarScanner
     * @param view
     */
    public void launch(View view){

        LayarSDK.initialize(this, Constants.OAUTH_KEY, Constants.OAUTH_SECRET);
        String layerName = "labellavillata3g";
        LayarSDK.startLayarActivity(this, layerName);
    }

    /**
     * Genera un numero aleatorio para obtener una URL de imágen
     * @param min
     * @param max
     * @return
     */
    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

}
