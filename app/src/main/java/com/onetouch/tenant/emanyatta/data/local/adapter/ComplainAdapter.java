package com.onetouch.tenant.emanyatta.data.local.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.model.Complain;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplainAdapter extends RecyclerView.Adapter<ComplainAdapter.ViewHolder> {

    private FragmentActivity context;
    private List<Complain> complains;

    public ComplainAdapter(FragmentActivity context, List<Complain> complains) {
        this.context = context;
        this.complains = complains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complain, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Complain current = complains.get(position);
        holder.apartmentName.setText(current.getApartmentName());
        holder.complainName.setText(current.getComplainName());
        holder.complainStatus.setText(current.getComplainStatus());
        holder.dateReported.setText(current.getDateRecorded());
        holder.houseNo.setText("House Number :" + current.getHouseNumber());
        holder.complainDescription.setText(current.getComplainDescription());
    }

    @Override
    public int getItemCount() {
        return complains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.apartmentName)
        TextView apartmentName;
        @BindView(R.id.complainName)
        TextView complainName;
        @BindView(R.id.complainStatus)
        TextView complainStatus;
        @BindView(R.id.houseNo)
        TextView houseNo;
        @BindView(R.id.dateReported)
        TextView dateReported;
        @BindView(R.id.complainDescription)
        TextView complainDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
