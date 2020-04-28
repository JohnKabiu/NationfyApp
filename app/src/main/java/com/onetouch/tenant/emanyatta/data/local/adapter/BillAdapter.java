package com.onetouch.tenant.emanyatta.data.local.adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.apps.nationfy.R;
import com.lamudi.phonefield.PhoneInputLayout;
import com.onetouch.tenant.emanyatta.Emanyatta;
import com.onetouch.tenant.emanyatta.data.local.AppDatabase;
import com.onetouch.tenant.emanyatta.data.local.model.Bill;
import com.onetouch.tenant.emanyatta.data.local.model.MpesaResponse;
import com.onetouch.tenant.emanyatta.data.local.model.PaymentOption;
import com.onetouch.tenant.emanyatta.data.local.model.User;
import com.onetouch.tenant.emanyatta.data.network.APIClient;
import com.onetouch.tenant.emanyatta.data.network.RequestInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private Dialog myDialog;
    private FragmentActivity context;
    private List<Bill> bills;
    private List<PaymentOption> paymentOptionList;
    private String token;
    private String TAG = "";
    private ProgressDialog dialog;
    private PaymentOptionAdapter mAdapter;

    public BillAdapter(FragmentActivity context, List<Bill> bills, String token) {
        this.context = context;
        this.bills = bills;
        this.token = token;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bills, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bill current = bills.get(position);
        holder.accountNumber.setText(current.getAccountNo());
        holder.billName.setText(current.getBillName());
        holder.billValue.setText("Ksh. " + current.getAmountValue());
        holder.dueDate.setText("Due on : " + current.getDueDate());
        holder.apartmentName.setText(current.getApartmentName());

        TextDrawable drawable1 = TextDrawable.builder()
                .buildRoundRect(String.valueOf(current.getBillName().toUpperCase().charAt(0)), R.color.colorProjectGreen, 50);
        holder.imageView.setImageDrawable(drawable1);


        holder.btnPayBill.setOnClickListener(v -> {
            myDialog = new Dialog(v.getContext());
            Button btnFollow;
            TextView amount, duedate, billname;
            myDialog.setContentView(R.layout.payment_popup);
            btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
            amount = (TextView) myDialog.findViewById(R.id.txtAmount);
            duedate = (TextView) myDialog.findViewById(R.id.txtDueDate);
            billname = (TextView) myDialog.findViewById(R.id.txtBillName);
            RecyclerView scrollView = myDialog.findViewById(R.id.picker);
            PhoneInputLayout phoneInputLayout = myDialog.findViewById(R.id.edit_text);
            EditText e = myDialog.findViewById(R.id.amountValue);
            Switch s = myDialog.findViewById(R.id.switch1);
            TextView c = myDialog.findViewById(R.id.btnCancel);

            loadPaymentOptions();

            e.setText(current.getAmountValue());

            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            scrollView.setLayoutManager(layoutManager);
            mAdapter = new PaymentOptionAdapter(paymentOptionList);
            scrollView.setAdapter(mAdapter);
            layoutManager.setReverseLayout(false);
            layoutManager.setStackFromEnd(false);

            phoneInputLayout.setHint(R.string.number_hint_others);
            phoneInputLayout.setDefaultCountry("KE");

            AppDatabase appDatabase = AppDatabase.getDatabase(holder.itemView.getContext());
            User user = appDatabase.userDao().getUser();
            s.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {

                    phoneInputLayout.setVisibility(View.VISIBLE);
                    btnFollow.setOnClickListener(v1 -> {
                        dialog = ProgressDialog.show(context, "",
                                "Pushing To M-Pesa...", true);
                        dialog.show();
                        String phoneNumber1 = phoneInputLayout.getPhoneNumber();
                        String nPN = phoneNumber1.replace("+", "");
                        stkPush(nPN, Integer.parseInt(e.getText().toString()), current.getPayAccount(), v);
                    });

                } else {

                    phoneInputLayout.setVisibility(View.GONE);
                    btnFollow.setOnClickListener(v1 -> {
                        dialog = ProgressDialog.show(context, "",
                                "Pushing To M-Pesa...", true);
                        dialog.show();

                        stkPush(user.getMobile(), Integer.parseInt(e.getText().toString()), current.getPayAccount(), v);
                    });

                }
            });

            amount.setText("Ksh. " + current.getAmountValue());
            duedate.setText("Due On: " + current.getDueDate());
            billname.setText(current.getBillName());

            c.setOnClickListener(v2 -> {
                myDialog.cancel();
            });

            Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        });

    }

    @Override
    public int getItemCount() {
        return bills.size();
    }

    private void loadPaymentOptions() {
        paymentOptionList = new ArrayList<>();

        PaymentOption mpesa = new PaymentOption();
        mpesa.setId(0);
        mpesa.setName("mPesa");
        mpesa.setPicture(R.drawable.ic_mpesa);

        PaymentOption card = new PaymentOption();
        card.setId(1);
        card.setName("Card");
        card.setPicture(R.drawable.ic_visa);

        PaymentOption equitel = new PaymentOption();
        equitel.setId(2);
        equitel.setName("Equitel");
        equitel.setPicture(R.drawable.ic_equitel);

        PaymentOption airtel = new PaymentOption();
        airtel.setId(3);
        airtel.setName("Airtel");
        airtel.setPicture(R.drawable.ic_airtell);

        paymentOptionList.add(mpesa);
        paymentOptionList.add(card);
        paymentOptionList.add(equitel);
        paymentOptionList.add(airtel);
    }

    private void stkPush(String phone, int amount, String account, View view) {

        if (isNetworkConnected()) {
            Emanyatta app = (Emanyatta) context.getApplicationContext();
            RequestInterface requestInterface = APIClient.getClient(app.settings.getBearerToken()).create(RequestInterface.class);
            requestInterface.stkPush(phone, account, amount).enqueue(new Callback<MpesaResponse>() {
                @Override
                public void onResponse(@NonNull Call<MpesaResponse> call, @NonNull Response<MpesaResponse> response) {
                    Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    myDialog.cancel();
                    dialog.cancel();
                }

                @Override
                public void onFailure(@NonNull Call<MpesaResponse> call, @NonNull Throwable t) {
                    Log.e(TAG, "onFailure: " + t);
                    Toast.makeText(context, "Failed Try Again" + t, Toast.LENGTH_LONG).show();
                    myDialog.cancel();
                    dialog.cancel();
                }
            });
        } else {
            Toast.makeText(context, "Please Connect your Internet or Wifi", Toast.LENGTH_SHORT).show();

        }
    }

    private boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) Objects.requireNonNull(context).getSystemService(Context.CONNECTIVITY_SERVICE);
            assert mConnectivityManager != null;
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return mNetworkInfo != null;

        } catch (NullPointerException e) {
            return false;

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.dueDate)
        TextView dueDate;
        @BindView(R.id.billName)
        TextView billName;
        @BindView(R.id.billValue)
        TextView billValue;
        @BindView(R.id.accountNumber)
        TextView accountNumber;
        @BindView(R.id.apartmentName)
        TextView apartmentName;
        @BindView(R.id.btnPayBill)
        Button btnPayBill;
        @BindView(R.id.image_view)
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
