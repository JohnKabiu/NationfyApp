package com.apps.nationfy.Item;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.Activity.BeritaDetailActivity;
import com.apps.nationfy.Models.BeritaModels;
import com.apps.nationfy.R;
import com.apps.nationfy.Utils.DatabaseHelper;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by apps on 3/24/2019.
 */

public class BeritaItem extends RecyclerView.Adapter<BeritaItem.ItemRowHolder> {

    private ArrayList<BeritaModels> dataList;
    private Context mContext;
    private int rowLayout;
    private DatabaseHelper databaseHelper;

    public BeritaItem(Context context, ArrayList<BeritaModels> dataList, int rowLayout) {
        this.dataList = dataList;
        this.mContext = context;
        this.rowLayout = rowLayout;
        databaseHelper = new DatabaseHelper(mContext);

    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemRowHolder holder, final int position) {
        final BeritaModels singleItem = dataList.get(position);
        holder.text.setText(singleItem.getJudul());
        holder.purpose.setText(singleItem.getTipe());
        Picasso.with(mContext)
                .load(singleItem.getFoto())
                .resize(150,150)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(R.drawable.image_placeholder)
                .into(holder.images);



        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(mContext, BeritaDetailActivity.class);
                    intent.putExtra("Id", singleItem.getId());
                    mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    class ItemRowHolder extends RecyclerView.ViewHolder {
        TextView text, purpose;
        ImageView images;
        LinearLayout lyt_parent;

        ItemRowHolder(View itemView) {
            super(itemView);
            lyt_parent = itemView.findViewById(R.id.rootLayout);
            images = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            purpose = itemView.findViewById(R.id.textPurpose);
        }
    }
}
