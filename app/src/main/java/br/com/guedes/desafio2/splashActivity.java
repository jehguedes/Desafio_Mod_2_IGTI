package br.com.guedes.desafio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final SharedPreferences sharedPref = getSharedPreferences("config", Context.MODE_PRIVATE);
        boolean isAlredyLogged = sharedPref.getBoolean("login", false);

        if(isAlredyLogged){
            goToMain();
        }else{
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sharedPref.edit().putBoolean("login", true).apply();
                    goToMain();
                }
            }, 3000);
        }


    }

    private void goToMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

}