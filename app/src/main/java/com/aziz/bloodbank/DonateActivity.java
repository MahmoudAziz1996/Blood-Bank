package com.aziz.bloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class DonateActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String TAG = "DonateActivity";
    ArrayAdapter<CharSequence> Blood_List;
    ArrayAdapter<CharSequence> City_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);


        Blood_List = ArrayAdapter.createFromResource(this,R.array.bg_arrays, android.R.layout.select_dialog_item);
        City_List = ArrayAdapter.createFromResource(this,R.array.city_arrays, android.R.layout.select_dialog_item);

        Spinner spinner_blood = (Spinner)findViewById(R.id.spinner_bloodgrp);
        Spinner spinner_city = (Spinner)findViewById(R.id.spinner_city);

        spinner_city.setAdapter(City_List);
        spinner_city.setOnItemSelectedListener(this);
        City_List.getItem(0).toString();

        spinner_blood.setAdapter(Blood_List);
        Blood_List.getItem(0).toString();



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String city = City_List.getItem(position).toString();
    }



    public void register(View view)
    {
        String msg = "";

        DonerData donerData = new DonerData();

        donerData.full_name = ((EditText) findViewById(R.id.editText_name)).getText().toString();
        donerData.phone = ((EditText) findViewById(R.id.editText_phone)).getText().toString();
        donerData.email = ((EditText) findViewById(R.id.editText_email)).getText().toString();
        donerData.addr = ((EditText) findViewById(R.id.editText_address)).getText().toString();

        donerData.bloodgrp = ((Spinner)findViewById(R.id.spinner_bloodgrp)).getSelectedItem().toString();
        donerData.city = ((Spinner)findViewById(R.id.spinner_city)).getSelectedItem().toString();

        // Validation of input data

        if(donerData.full_name.isEmpty() || donerData.phone.isEmpty() || donerData.email.isEmpty() ||  donerData.addr.isEmpty())
        {
            msg = "One or Multiple fields are Empty.\nCheck all Fields and try Again.";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher( donerData.email).matches())
        {
            msg = "Invalid E-mail address.";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        else if( donerData.phone.length()<10)
        {
            msg = "Invalid Phone number.";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
        }
        else
        {

            dbHelper.insert(donerData, this);
            msg = "Successfully, registered as Doner.";
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            super.onBackPressed();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
