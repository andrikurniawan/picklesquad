package com.pplnostra.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class login_user extends AppCompatActivity implements View.OnClickListener{


    TextView tvSignUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        tvSignUpLink = (TextView) findViewById(R.id.tvSignUpLink);

        tvSignUpLink.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tvSignUpLink:
                startActivity(new Intent(this, com.pplnostra.login.CompletingForm.class));
                break;
        }
    }
}
