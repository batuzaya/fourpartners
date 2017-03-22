package com.charpixel.baseandroidproject.common;

import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by ashu on 27-07-2016.
 */
public class Provider {

   private GoogleApiClient mGoogleApiClient;
    private static Provider p ;




   private GoogleApiClient createGoogleApiClint(FragmentActivity c , GoogleApiClient.OnConnectionFailedListener l){

       GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
               .requestEmail()
               .requestProfile()
               .requestIdToken("1061772343519-poficbehgvldvhvutsaibi3vb7738adn.apps.googleusercontent.com")
               // .requestIdToken(getString(R.string.server_client_id))
               //.requestServerAuthCode(getString(R.string.server_client_id))
               .build();
       mGoogleApiClient = new GoogleApiClient.Builder(c)
               .enableAutoManage(c /* FragmentActivity */, l /* OnConnectionFailedListener */)
               .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
               .build();

       return mGoogleApiClient;


   }

    public GoogleApiClient getGoogleApiClient(FragmentActivity c , GoogleApiClient.OnConnectionFailedListener l){
        if(mGoogleApiClient != null){
            return  mGoogleApiClient;
        }else{
            return createGoogleApiClint(c,l);
        }
    }



    public static Provider getInstance(){

        if(p == null)
        {
            p = new Provider();
            return p;
        }else{
            return p;
        }

    }

}
