package com.pplnostra.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    TextView mTextDetails;
    UserSessionManager session;

    String name, email, birthday, token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();
        mTextDetails = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        session = new UserSessionManager(getApplicationContext());

        loginButton.setReadPermissions(Arrays.asList("public_profile","email"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                final AccessToken accessToken = loginResult.getAccessToken();

                GraphRequest request = GraphRequest.newMeRequest(
                        accessToken,
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(
                                    JSONObject object,
                                    GraphResponse response) {
                                // Application code
                                name = object.optString("name").toString();
                                email = object.optString("email").toString();
                                birthday = object.optString("birthday").toString();
                                token = AccessToken.getCurrentAccessToken().toString();

                                SharedPreferences shared = getSharedPreferences(getResources().getString(R.string.KEY_SHARED_PREF), MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putString("name", name);
                                editor.putString("email", email);
                                editor.putString("birthday", birthday);

                                Log.e("Cek nama", name + "");
                                Log.e("Cek email", email + "");
                                Log.e("Cek birthday", birthday + "");

                                Log.e("Token: ", token + "");
                                editor.commit();

                                session.createUserLogin(email, token);
                                Intent in = new Intent(LoginActivity.this, CompletingForm.class);
                                startActivity(in);
                                finish();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,link,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                mTextDetails.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                mTextDetails.setText("Login attempt failed.");
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

class FBUser {
    private String email;
    private String name;
    private String id;

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setEmail(String e) {
        email = e;
    }

    public void setName(String n) {
        name = n;
    }

    public void setId(String i) {
        id = i;
    }
}
