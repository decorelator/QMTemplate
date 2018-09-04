package com.techtify.qualimoments.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.techtify.qualimoments.R;

public class QLoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private View loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.techtify.qualimoments",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                //DUaJbvifllUONLCxF1DiGxbEMbQ=

            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
*/
       // FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_qlogin);
        loading = findViewById(R.id.loading);

        fbInit();
    }

    public void onLogin(View view) {
        startActivity(new Intent(this, QMainActivity.class));
        finish();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        loading.setVisibility(View.VISIBLE);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void fbInit() {
        boolean loggedIn = Profile.getCurrentProfile() != null;

        if (loggedIn){
            loading.setVisibility(View.VISIBLE);
            onLogin(null);
            return;
        }
        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
       // loginButton.setText("login");





        loading.setVisibility(View.GONE);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loading.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        onLogin(null);
                        loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

    }

    public void LILofgin(){
        String url = "https://api.linkedin.com/v1/people/~:(id,first-name,last-name)";

        APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
        apiHelper.getRequest(this, url, new ApiListener() {
            @Override
            public void onApiSuccess(ApiResponse apiResponse) {
                // Success!
                Log.e("tag","OK");
            }

            @Override
            public void onApiError(LIApiError liApiError) {
                Log.e("tag","OK");
            }
        });
    }

    public void onLoginLi(View view) {
        LILofgin();
    }

    public void onFBClick(View view) {
        loginButton.performClick();
    }
}
