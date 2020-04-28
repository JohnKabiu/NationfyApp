package com.onetouch.tenant.emanyatta.data.local.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.model.BillHistory;
import com.onetouch.tenant.emanyatta.data.local.model.Month;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillHistoryMonthAdapter extends RecyclerView.Adapter<BillHistoryMonthAdapter.ViewHolder> {

    private FragmentActivity context;
    private List<Month> months;
    private BillHistoryAdapter mAdapter;
    private List<BillHistory> billHistories;

    public BillHistoryMonthAdapter(FragmentActivity context, List<Month> months, List<BillHistory> billHistories) {
        this.context = context;
        this.months = months;
        this.billHistories = billHistories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_month, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Month current = months.get(position);

        holder.month.setText(current.getName());

        List<BillHistory> billSort = new ArrayList<>();

        for (BillHistory bill : billHistories) {
            String date = bill.getDatePaid();
            String[] separate = date.split(" ");

            if (separate[0].equals(current.getName())) {
                billSort.add(bill);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        layoutManager.setReverseLayout(false);
        layoutManager.setStackFromEnd(false);
        holder.monthRecords.setLayoutManager(layoutManager);
        mAdapter = new BillHistoryAdapter(context, billSort);
        holder.monthRecords.setAdapter(mAdapter);

        holder.expandMore.setOnClickListener(v -> {
            holder.expandMore.setVisibility(View.GONE);
            holder.expandLess.setVisibility(View.VISIBLE);
            holder.monthRecords.setVisibility(View.VISIBLE);
        });

        holder.expandLess.setOnClickListener(v -> {
            holder.expandLess.setVisibility(View.GONE);
            holder.expandMore.setVisibility(View.VISIBLE);
            holder.monthRecords.setVisibility(View.GONE);
        });

    }

    @Override
    public int getItemCount() {
        return months.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.month)
        TextView month;
        @BindView(R.id.monthRecords)
        RecyclerView monthRecords;
        @BindView(R.id.expandMore)
        ImageView expandMore;
        @BindView(R.id.expandLess)
        ImageView expandLess;
        @BindView(R.id.progress_circular)
        ProgressBar progressCircular;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
