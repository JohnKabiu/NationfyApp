package com.onetouch.tenant.emanyatta.data.local.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.bumptech.glide.Glide;
import com.onetouch.tenant.emanyatta.HouseDetailsActivity;
import com.onetouch.tenant.emanyatta.data.local.model.House;
import com.onetouch.tenant.emanyatta.data.local.model.HousePictures;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.ViewHolder> {

    private Activity context;
    private List<House> houses;
    private List<HousePictures> pics;

    public HouseAdapter(Activity context, List<House> houses) {
        this.context = context;
        this.houses = houses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_house, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        House current = houses.get(position);
        holder.textViewApartmentName.setText(current.getHouseName());
        holder.textViewHouseLocation.setText(current.getHouseLocation());
        Glide.with(context).load(Objects.requireNonNull(current.getImageUrl())).into(holder.imageViewHouse);

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), HouseDetailsActivity.class);
            Bundle b = new Bundle();
            b.putInt("house_id", position + 1);
            intent.putExtras(b);
            v.getContext().startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return houses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewHouse)
        ImageView imageViewHouse;
        @BindView(R.id.textViewApartmentName)
        TextView textViewApartmentName;
        @BindView(R.id.textViewHouseLocation)
        TextView textViewHouseLocation;
        @BindView(R.id.cardView)
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
