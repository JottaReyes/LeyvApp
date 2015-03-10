package com.layarapp.leyvapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jorge.reyes on 06/03/2015.
 */
public class NetworkUtility {

    public boolean isConnected(Context ctx){

        boolean flag = false;
        ConnectivityManager connectivityManager =
                (ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            flag = true;
        }
        return flag;
    }
}
