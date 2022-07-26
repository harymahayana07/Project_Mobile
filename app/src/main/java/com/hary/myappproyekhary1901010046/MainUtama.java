package com.hary.myappproyekhary1901010046;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainUtama extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_utama);

        Button btnBiodata = findViewById(R.id.btn_biodata);
        btnBiodata.setOnClickListener(this);

        Button btnVolBalok = findViewById(R.id.btn_balok);
        btnVolBalok.setOnClickListener(this);

        Button btnVolBola = findViewById(R.id.btn_bola);
        btnVolBola.setOnClickListener(this);

        Button btnKalkulator = findViewById(R.id.btn_kalkulator);
        btnKalkulator.setOnClickListener(this);

        Button btnContact = findViewById(R.id.btn_contact);
        btnContact.setOnClickListener(this);

        Button btnLogout = findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_biodata:
                Intent biodataIntent = new Intent(MainUtama.this, MainActivity.class);
                startActivity(biodataIntent);
                break;
            case R.id.btn_balok:
                Intent balokIntent = new Intent(MainUtama.this, VolumeBalok.class);
                startActivity(balokIntent);
                break;
            case R.id.btn_bola:
                Intent bolaIntent = new Intent(MainUtama.this, VolumeBola.class);
                startActivity(bolaIntent);
                break;
            case R.id.btn_kalkulator:
                Intent kalkulatorIntent = new Intent(MainUtama.this, Kalkulator.class);
                startActivity(kalkulatorIntent);
                break;
            case R.id.btn_contact:
                Intent contactIntent = new Intent(MainUtama.this, ContactMe.class);
                startActivity(contactIntent);
                break;
            case R.id.btn_logout:
                Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                Intent logoutIntent = new Intent(MainUtama.this, SignUpActivity.class);
                startActivity(logoutIntent);
                finish();

        }
    }
}