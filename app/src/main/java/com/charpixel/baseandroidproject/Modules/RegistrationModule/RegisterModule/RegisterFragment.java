package com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.charpixel.baseandroidproject.Application;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Facebook;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Google;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.ApiModels.Login;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.BLoginView;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.LoginContract;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.LoginModule.LoginPresenter;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ApiModels.Register;
import com.charpixel.baseandroidproject.Modules.RegistrationModule.RegisterModule.ViewModels.RegisterInfo;
import com.charpixel.baseandroidproject.R;
import com.charpixel.baseandroidproject.Utilities.PrefUtils;
import com.charpixel.baseandroidproject.common.BaseFragment;
import com.charpixel.baseandroidproject.common.Provider;
import com.charpixel.baseandroidproject.dataBindingUtils.TextWatcherAdapter;
import com.charpixel.baseandroidproject.databinding.FragmentRegisterBinding;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import org.parceler.Parcels;

import javax.inject.Inject;

import retrofit2.Call;


/**
 * A placeholder fragment containing a simple View.
 */
public class RegisterFragment extends BaseFragment implements LoginContract.View,
        GoogleApiClient.OnConnectionFailedListener , View.OnClickListener  {

    private static final String TAG = "LoginActivityFragment";
    private static final int RC_SIGN_IN = 9001;
    private static final int FB_SIGN_IN = 9002;
    private static final int GOOGLE_SIGN_IN = 9003;

    public BroadcastReceiver receiver;

    @Inject
    SharedPreferences sharedPreferences;

    @Inject
    PrefUtils utils;

    @Inject
    LoginPresenter loginPresenter;

    BLoginView bLoginView;
    public static final String LOGIN_INFO = "registerInfo";
    @Inject
    Login.Request loginRequest;
    private TwitterLoginButton loginButton;
//    @Inject
//    ForgotPasswordRequest forgotPasswordRequest;

    @Inject
    Google.Request googleRequest;

    @Inject
    Facebook.Request facebookRequest;

    private RegisterInfo registerInfo;
    GoogleApiClient mGoogleApiClient;
//    private CallbackManager callbackManager;
//
//    LoginButton facebookLoginButton;
    FragmentRegisterBinding binding;
    TwitterSession session;



    public RegisterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);



        //  getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (savedInstanceState == null) {
            registerInfo = new RegisterInfo();
//            registerInfo.email.set("ashu.saini1111+2@gmail.com");
//            registerInfo.password.set("ashuashu");
        } else {
            registerInfo = Parcels.unwrap(savedInstanceState.getParcelable(LOGIN_INFO));
        }

        binding.setRegisterInfo(registerInfo);

        TextWatcherAdapter watcher = new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                registerInfo.validate(getResources());
            }
        };

        binding.email.addTextChangedListener(watcher);
        binding.password.addTextChangedListener(watcher);
        binding.confirmPassword.addTextChangedListener(watcher);

        binding.registerButton.setOnClickListener(this);

        binding.signInButton.setOnClickListener(this);

        //binding.twitterLoginButton.setOnClickListener(this);

       // initialiseFacebook();
        initialiseGoogle();

        binding.customTwitterLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialiseTwitter();
                binding.twitterLoginButton.performClick();
            }
        });



        return binding.getRoot();
        //return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
//        loginButton = (TwitterLoginButton) getView().findViewById(R.id.twitter_login_button);

        loginPresenter.attachView(this);

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void onButtonClick(View view) {
        Toast.makeText(getActivity(), "CLICKED", Toast.LENGTH_LONG).show();
        bLoginView.setEmail("ashu.saini1111+1@gmail.com");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.registerButton:


                String error = registerInfo.validate(getResources());

                Log.e(TAG, "onClick: "+error );
                if ( error == null) {
                    Snackbar.make(binding.getRoot(), registerInfo.email.get() + " - " + registerInfo.password.get(), Snackbar.LENGTH_LONG).show();
                    registerUser();
                }else {

                    Snackbar.make(binding.getRoot(),error,Snackbar.LENGTH_LONG).show();

                }


                break;
            case R.id.facebook_login_button:
             //   LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"));
                break;
            case R.id.forgotPassword:
                navigateToForgotPasswordActivity();
                break;
            case R.id.twitter_login_button:

                break;
            case R.id.login:
                registerInfo.loginExecuted = true;
                requestLogin();


                break;



        }
    }


    private void initialiseTwitter()
    {
        binding.twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                //If login succeeds passing the Calling the login method and passing Result object
                binding.twitterLoginButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        login(result);
                    }
                });

            }

            @Override
            public void failure(TwitterException exception) {
                //If failure occurs while login handle it here
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });
    }

    public void login(Result<TwitterSession> result) {
        //Creating a twitter session with result's data
        TwitterSession session = result.data;

        //Getting the username from session
        final String userName = session.getUserName();


        //Getting the account service of the user logged in
        Call<User> call = Twitter.getApiClient(session).getAccountService()
                .verifyCredentials(true, false);
        call.enqueue(new Callback<User>() {
            @Override
            public void failure(TwitterException e) {
                //If any error occurs handle it here
            }
            @Override
            public void success(Result<User> userResult) {
                //If it succeeds creating a User object from userResult.data
                User user = userResult.data;

            }
        });
    }


//    private  void initialiseFacebook(){
//        FacebookSdk.sdkInitialize(getActivity());
//
//        callbackManager = CallbackManager.Factory.create();
//        // Callback registration
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//              //  Log.v("facebook", "llllll"+loginResult.getAccessToken().getToken());
//               // Log.v("facebook", "llllll"+loginResult.getAccessToken().toString());
//                Log.v("facebook", "llllll"+loginResult.getAccessToken().getApplicationId());
//
//                // App code
//                GraphRequest request = GraphRequest.newMeRequest(
//                        loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @Override
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                Log.v("LoginActivity", response.toString());
//                                Log.v("json " , object.toString());
//
//
//
//                                // Application code
//                                try {
//                                    String email = object.getString("email");
//                                    facebookRequest.setFbId(loginResult.getAccessToken().getApplicationId());
//                                    facebookRequest.setFbToken(loginResult.getAccessToken().getToken());
//                                    facebookRequest.setDeviceType("ANDROID");
//                                    facebookRequest.setDeviceToken(utils.getDeviceToken(getActivity()));
//                                    facebookRequest.setEmail(object.getString("email"));
//                                    facebookRequest.setFirstName(object.getString("name").split(" ")[0]);
//                                    facebookRequest.setLastName(object.getString("name").split(" ")[1]);
//                                    facebookRequest.setProfilePic(object.getJSONObject("picture").getJSONObject("data").getString("url"));
//
//
//                                    loginPresenter.doFacebookLogin();
//
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//                            }
//
//                        });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name,email,gender,picture.type(large)");
//                request.setParameters(parameters);
//                request.executeAsync();
//
//                showSnackbar(loginResult.getAccessToken().toString());
//                // App code
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                Log.v("facebook error", "llllll"+exception);
//
//                // App code
//            }
//        });
//        binding.facebookLoginButton.setOnClickListener(this);
//
//    }



    public void registerUser(){

        Register.Request request = new Register.Request();

            request.setEmail(registerInfo.email.get());
            request.setFullName(registerInfo.name.get());
            request.setPassword(registerInfo.password.get());
            //request.setConfirm_password(registerInfo.confirm_password.get());
            loginPresenter.requestForRegister(request);



    }


    @Override
    public void navigateToOtpVerification() {
//        Intent i = new Intent(getActivity(), OtpVerificationActivity.class);
//        startActivity(i);

    }

    @Override
    public void navigateToFacebookDetails() {
//        Intent i = new Intent(getActivity(), ExtraDetailActivity.class);
//        i.putExtra("firstName",facebookRequest.getFirstName());
//        i.putExtra("email",facebookRequest.getEmail());
//        startActivityForResult(i , FB_SIGN_IN);
    }

    @Override
    public void navigateToGoogleDetails() {
//        Intent i = new Intent(getActivity(), ExtraDetailActivity.class);
//
//        i.putExtra("firstName",googleRequest.getFirstName());
//        i.putExtra("email",googleRequest.getEmail());
//        startActivityForResult(i , GOOGLE_SIGN_IN);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    public void requestLogin(){
        Log.v("login Request",registerInfo.email.get()+","+registerInfo.password.get());
        loginRequest.setEmail(registerInfo.email.get());
        //loginRequest.setDeviceToken(utils.getDeviceToken(getContext()));
        loginRequest.setPassword(registerInfo.password.get());
        loginPresenter.requestForLogin();


    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((Application) getActivity().getApplication()).getNetComponent().inject(this);


    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }


    private void  initialiseGoogle(){

        // Configure sign-in to Request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//
//                .requestIdToken("220895903359-4cha8gk95rc2h17ss5i5qes8iv0dsc70.apps.googleusercontent.com")
//              // .requestIdToken(getString(R.string.server_client_id))
//               // .requestServerAuthCode(getString(R.string.server_client_id))
//                .build();
//        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
//                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();

        mGoogleApiClient = Provider.getInstance().getGoogleApiClient(getActivity(),this);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }else if(requestCode == FB_SIGN_IN) {


            Log.e(TAG,data.getStringExtra("email"));

            facebookRequest.setFirstName(data.getStringExtra("firstName"));

            facebookRequest.setEmail(data.getStringExtra("email"));


            loginPresenter.doFacebookLogin();


          //  Log.v(TAG,data.getStringExtra());

        }else if(requestCode == GOOGLE_SIGN_IN) {


            Log.e(TAG,data.getStringExtra("email"));
            googleRequest.setFirstName(data.getStringExtra("firstName"));
            googleRequest.setEmail(data.getStringExtra("email"));

            googleRequest.setCountryCode(data.getStringExtra("countryCode"));


//            googleRequest.setPhoneNo(data.getStringExtra("phoneNo"));
//            googleRequest.setApiTime(2);

            loginPresenter.doGoogleLogin();
            //  Log.v(TAG,data.getStringExtra());

        } else {
         //   callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e(TAG,acct.getIdToken());

            googleRequest.setGoogleId(acct.getId());
            googleRequest.setGoogleCode(acct.getIdToken());
            googleRequest.setProfilePic(acct.getPhotoUrl().toString());


        //   Log.v(TAG,acct.getServerAuthCode().toString());
           // acct.getServerAuthCode();

            googleRequest.setDeviceToken(utils.getDeviceToken(getContext()));

            googleRequest.setEmail(acct.getEmail());
            googleRequest.setFirstName(acct.getDisplayName().split(" ")[0]);
            googleRequest.setLastName(acct.getDisplayName().split(" ")[1]);
           // googleRequest.setApiTime(1);

            loginPresenter.doGoogleLogin();
            showSnackbar(acct.getEmail());

            //   goToDashboard();
//            mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()+ acct.getId() +acct.getEmail()+acct.getIdToken()));
//            updateUI(true);

//            Intent intent = new Intent(this, RequestRideScreen.class);
//            startActivity(intent);

        } else {
            // Signed out, show unauthenticated UI.
//            updateUI(false);
        }
    }






    @Override
    public void navigateToForgotPasswordActivity() {
//        Intent i = new Intent(getActivity(), ForgotpasswordActivity.class);
//        startActivity(i);

    }

    @Override
    public void navigateToDashboard() {
//        Intent i = new Intent(getActivity(), RequestRideScreen.class);
//        startActivity(i);
//        getActivity().finish();
    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void showSnackbar(String s) {

        Snackbar.make(getView(), s, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

}
