package com.onetouch.tenant.emanyatta;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.apps.nationfy.R;
import com.google.android.material.textfield.TextInputEditText;
import com.lamudi.phonefield.PhoneInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyAirtimeActivity extends AppCompatActivity {

    @BindView(R.id.edit_text)
    PhoneInputLayout editText;
    @BindView(R.id.airtimeAmount)
    TextInputEditText airtimeAmount;
    @BindView(R.id.btnFinish)
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_airtime);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editText.setHint(R.string.number_hint);
        editText.setDefaultCountry("KE");

    }

    public void Back(View view) {
        Intent intent = new Intent(getApplicationContext(), com.onetouch.tenant.emanyatta.MainActivity.class);
        startActivity(intent);
        finish();
    }
}
