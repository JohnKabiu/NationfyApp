package com.onetouch.tenant.emanyatta.data.local.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.model.BillHistory;
import com.shuhart.stickyheader.StickyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillHistoryAdapter extends StickyAdapter<BillHistoryAdapter.ViewHolder, BillHistoryAdapter.ViewHolder> {


    private FragmentActivity context;
    private List<BillHistory> bills;

    public BillHistoryAdapter(FragmentActivity context, List<BillHistory> bills) {
        this.context = context;
        this.bills = bills;
    }

    @Override
    public int getHeaderPositionForItem(int itemPosition) {
        return 1;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder viewHolder, int i) {
    }

    @Override
    public ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_year, parent, false);
        return new ViewHolder(itemView);
    }

    @NonNull
    @Override
    public BillHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill_history, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BillHistory current = bills.get(position);
        holder.accountNumber.setText("Current Balance : Ksh. " + current.getCurrentBalance());
        holder.billName.setText(current.getPaymentName());
        holder.billValue.setText("Ksh. " + current.getPaidAmount());
        holder.paidDate.setText("Paid on : " + current.getDatePaid());

//        TextDrawable drawable1 = TextDrawable.builder()
//                .buildRoundRect(String.valueOf(current.getPaymentName().toUpperCase().trim().charAt(0)), R.color.colorProjectGreen, 50);
//        holder.imageView.setImageDrawable(drawable1);
    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.paidDate)
        TextView paidDate;
        @BindView(R.id.billName)
        TextView billName;
        @BindView(R.id.billValue)
        TextView billValue;
        @BindView(R.id.accountNumber)
        TextView accountNumber;
//        @BindView(R.id.image_view)
//        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
