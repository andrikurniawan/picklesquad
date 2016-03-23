package com.pplnostra.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CompletingForm extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etEmail, etPhoneNumber, etBirthday, etGender;
    Button bNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completing_form);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etBirthday = (EditText) findViewById(R.id.etBirthday);
        etGender = (EditText) findViewById(R.id.etGender);
        bNext = (Button) findViewById(R.id.bNext);

        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shared = getSharedPreferences(getResources().getString(R.string.KEY_SHARED_PREF), MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("phoneNumber", etPhoneNumber.getText().toString());
                editor.putString("birthday", etBirthday.getText().toString());
                editor.putString("gender", etGender.getText().toString());
                editor.commit();

                Intent in = new Intent(CompletingForm.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        });
    }

    public void onStart() {
        super.onStart();
        display();
    }

    @Override
    public void onClick(View v) {

    }

    public void display() {
        SharedPreferences shared = getSharedPreferences(getResources().getString(R.string.KEY_SHARED_PREF), MODE_PRIVATE);
        String userName = shared.getString("name", "");
        etName.setText(userName);

        String email = shared.getString("email", "");
        etEmail.setText(email);

        String bod = shared.getString("birthday","");
        etBirthday.setText(bod);
    }
}
