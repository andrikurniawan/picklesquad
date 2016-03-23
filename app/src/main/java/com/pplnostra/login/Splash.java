package com.pplnostra.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

public class Splash extends Activity {

    private final int DELAY_SPLASHSCREEN = 3000;
    UserSessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        session = new UserSessionManager(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;

                if (!session.isUserLogin()) {
                    intent = new Intent(Splash.this, LoginActivity.class);
                } else {
                    intent = new Intent(Splash.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }

                startActivity(intent);
                finish();
            }
        }, DELAY_SPLASHSCREEN);
    }
}
