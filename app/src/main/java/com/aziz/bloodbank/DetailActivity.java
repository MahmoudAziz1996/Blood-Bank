package com.aziz.bloodbank;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    DonerData data;
    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra("Id", 0);
        data = dbHelper.getDonerDetail(this, id);

        TextView name = (TextView) findViewById(R.id.name);
        TextView bloodgrp = (TextView) findViewById(R.id.bloodgrp);
        TextView phone = (TextView) findViewById(R.id.phone);
        TextView email = (TextView) findViewById(R.id.email);
        TextView city = (TextView) findViewById(R.id.city);
        TextView addr = (TextView) findViewById(R.id.addr);

        name.setText(data.full_name);
        bloodgrp.setText(bloodgrp.getText() + data.bloodgrp);
        phone.setText(phone.getText() + data.phone);
        email.setText(email.getText() + data.email);
        city.setText(city.getText() + data.city);
        addr.setText(addr.getText() + data.addr);

    }

    public void call() {

        String number = data.phone;
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(DetailActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(DetailActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(DetailActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                call();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void callDonate(View view) {
        call();
    }
}

