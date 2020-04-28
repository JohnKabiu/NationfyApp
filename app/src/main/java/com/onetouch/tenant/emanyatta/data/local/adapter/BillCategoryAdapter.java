package com.onetouch.tenant.emanyatta.data.local.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.bumptech.glide.Glide;
import com.onetouch.tenant.emanyatta.data.local.model.BillCategory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillCategoryAdapter extends RecyclerView.Adapter<BillCategoryAdapter.ViewHolder> {

    private Activity context;
    private List<BillCategory> billCategories;

    public BillCategoryAdapter(Activity context, List<BillCategory> billCategories) {
        this.context = context;
        this.billCategories = billCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_category, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillCategory current = billCategories.get(position);
        holder.name.setText(current.getCategoryName());
        Glide.with(context).load(current.getCategoryPicture()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return billCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
