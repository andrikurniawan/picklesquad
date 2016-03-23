package com.pplnostra.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Syukri Mullia Adil P on 3/11/2016.
 */
public class UserSessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Context mContext;

    private static final String KEY_SHARED_PREF = "PICKLEUSER";
    private static final int PRIVATE_MODE = 0;
    private static final String IS_USER_LOGIN = "ISUSERLOGIN";

    public UserSessionManager(Context context){
        this.mContext = context;
        pref = mContext.getSharedPreferences(KEY_SHARED_PREF, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createUserLogin(String email, String password){
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString("EMAIL", email);
        editor.putString("TOKEN", password);
        editor.commit();
    }


    public boolean checkLogin(){
        if(this.isUserLogin()){
            return true;
        }
        else{
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            return false;
        }
    }


    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent intent = new Intent(mContext, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public boolean isUserLogin(){
        Log.e("cek login1 : ", "" + pref.contains(IS_USER_LOGIN));
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();

        user.put("EMAIL", pref.getString("EMAIL", null));
        user.put("TOKEN", pref.getString("TOKEN", null));
        return user;
    }

}


