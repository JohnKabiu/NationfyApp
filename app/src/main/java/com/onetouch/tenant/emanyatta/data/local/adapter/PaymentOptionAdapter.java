package com.onetouch.tenant.emanyatta.data.local.adapter;

import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.bumptech.glide.Glide;
import com.onetouch.tenant.emanyatta.CardPaymentActivity;
import com.onetouch.tenant.emanyatta.data.local.model.PaymentOption;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentOptionAdapter extends RecyclerView.Adapter<PaymentOptionAdapter.ViewHolder> {

    private List<PaymentOption> data;

    public PaymentOptionAdapter(List<PaymentOption> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_option, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaymentOption current = data.get(position);

        Glide.with(holder.itemView.getContext())
                .load(data.get(position).getPicture())
                .into(holder.imageOption);

        setBW(holder.imageOption);
        if (current.getId() == 0) {
            setNormal(holder.imageOption);
        }
        holder.cardView.setOnClickListener(v -> {
            if (current.getId() == 0) {
                setNormal(holder.imageOption);
            } else if (current.getId() == 1) {
                Intent intent = new Intent(v.getContext(), CardPaymentActivity.class);
                v.getContext().startActivity(intent);
            } else if (current.getId() == 2) {

            } else if (current.getId() == 3) {

            }

        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private void setBW(ImageView iv) {

        float brightness = 10; // change values to suite your need

        float[] colorMatrix = {
                0.33f, 0.33f, 0.33f, 0, brightness,
                0.33f, 0.33f, 0.33f, 0, brightness,
                0.33f, 0.33f, 0.33f, 0, brightness,
                0, 0, 0, 1, 0
        };

        ColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        iv.setColorFilter(colorFilter);
    }

    private void setNormal(ImageView i) {
        i.setColorFilter(null);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageOption)
        ImageView imageOption;
        @BindView(R.id.cardView)
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
