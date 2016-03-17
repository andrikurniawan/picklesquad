package com.pplnostra.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CompletingForm extends AppCompatActivity implements View.OnClickListener {

    Button bNext;
    EditText etName, etEmail, etPhoneNumber, etBirthday , etGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completing_form);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
        etBirthday = (EditText) findViewById(R.id.etBirthday);
        etGender = (EditText) findViewById(R.id.etGender);
        bNext = (Button)findViewById(R.id.bNext);

        bNext.setOnClickListener(this);

    }

    @Override
    public void onStart(){
        super.onStart();
        display();
    }
    public void display(){
        //ambil data masukan kesini menggunakan etName.setText(blabla);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bNext:
                startActivity(new Intent(this, com.pplnostra.login.MainActivity.class));
                break;

        }
    }


}
