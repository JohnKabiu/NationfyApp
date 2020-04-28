package com.onetouch.tenant.emanyatta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.apps.nationfy.R;
import com.bumptech.glide.Glide;
import com.onetouch.tenant.emanyatta.data.local.model.House;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.HouseViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseDetailsActivity extends AppCompatActivity {

    @BindView(R.id.imageViewHouse)
    ImageView imageViewHouse;
    @BindView(R.id.textViewHouseName)
    TextView textViewHouseName;
    @BindView(R.id.textViewAppLocation)
    TextView textViewAppLocation;
    @BindView(R.id.textViewApartmentDescription)
    TextView textViewApartmentDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        assert b != null;
        int houseId = b.getInt("house_id");

        HouseViewModel model = ViewModelProviders.of(this).get(HouseViewModel.class);
        House house;
        house = model.getHouseLiveData(this, houseId);
        Glide.with(this).load(house.getImageUrl()).into(imageViewHouse);
        textViewHouseName.setText(house.getHouseName());
        textViewApartmentDescription.setText(house.getHouseDescription());
        textViewAppLocation.setText(house.getHouseLocation());
    }
}
