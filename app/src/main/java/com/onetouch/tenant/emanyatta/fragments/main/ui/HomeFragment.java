package com.onetouch.tenant.emanyatta.fragments.main.ui;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.Constants.BaseApp;
import com.apps.nationfy.R;
import com.github.clans.fab.FloatingActionButton;
import com.lamudi.phonefield.PhoneInputLayout;
import com.onetouch.tenant.emanyatta.BuyAirtimeActivity;
import com.onetouch.tenant.emanyatta.data.local.AppDatabase;
import com.onetouch.tenant.emanyatta.data.local.adapter.BillAdapter;
import com.onetouch.tenant.emanyatta.data.local.adapter.PaymentOptionAdapter;
import com.onetouch.tenant.emanyatta.data.local.model.Average;
import com.onetouch.tenant.emanyatta.data.local.model.MpesaResponse;
import com.onetouch.tenant.emanyatta.data.local.model.PaymentOption;
import com.onetouch.tenant.emanyatta.data.local.model.User;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.AverageViewModel;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.BillViewModel;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.UserViewModel;
import com.onetouch.tenant.emanyatta.data.network.APIClient;
import com.onetouch.tenant.emanyatta.data.network.RequestInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static String token;
    RecyclerView rvBills;
    private BillAdapter mAdapter;
    private PaymentOptionAdapter adapter;
    private FloatingActionButton btnBuyAirtime;
    private TextView paid;
    private TextView pending;
    private TextView complain;
    private AppDatabase appDatabase;
    private TextView v;

    private RequestInterface requestInterface;
    private BaseApp app;
    private LinearLayout payAll;
    private int pendingAmount;
    private ProgressDialog dialog;
    private Dialog myDialog;
    private List<PaymentOption> paymentOptionList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_emanyatta, container, false);

        app = (BaseApp) Objects.requireNonNull(getActivity()).getApplicationContext();
        requestInterface = APIClient.getClient(app.settings.getBearerToken()).create(RequestInterface.class);

        paid = (TextView) view.findViewById(R.id.paidAmount);
        pending = (TextView) view.findViewById(R.id.pendingAmount);
//        complain = (TextView) view.findViewById(R.id.complains);

        payAll = (LinearLayout) view.findViewById(R.id.payAll);

        appDatabase = AppDatabase.getDatabase(getContext());

        v = (TextView) view.findViewById(R.id.txtWelcomeUser);

        btnBuyAirtime = (FloatingActionButton) view.findViewById(R.id.btnBuyAirtime);

        btnBuyAirtime.setOnClickListener(v -> {
            Intent intent = new Intent(view.getContext(), BuyAirtimeActivity.class);
            startActivity(intent);
        });

        payAll.setOnClickListener(v1 -> {
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

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            scrollView.setLayoutManager(layoutManager);
            adapter = new PaymentOptionAdapter(paymentOptionList);
            scrollView.setAdapter(adapter);
            layoutManager.setReverseLayout(false);
            layoutManager.setStackFromEnd(false);

            phoneInputLayout.setHint(R.string.number_hint_others);
            phoneInputLayout.setDefaultCountry("KE");

            e.setText(String.valueOf(pendingAmount));

            AppDatabase appDatabase = AppDatabase.getDatabase(getContext());
            User user = appDatabase.userDao().getUser();
            s.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {

                    phoneInputLayout.setVisibility(View.VISIBLE);
                    btnFollow.setOnClickListener(v -> {
                        dialog = ProgressDialog.show(getContext(), "",
                                "Pushing To M-Pesa...", true);
                        dialog.show();
                        String phoneNumber1 = phoneInputLayout.getPhoneNumber();
                        String nPN = phoneNumber1.replace("+", "");
                        stkPush(nPN, Integer.parseInt(e.getText().toString()));
                    });

                } else {

                    phoneInputLayout.setVisibility(View.GONE);
                    btnFollow.setOnClickListener(v -> {
                        dialog = ProgressDialog.show(getContext(), "",
                                "Pushing To M-Pesa...", true);
                        dialog.show();

                        stkPush(user.getMobile(), Integer.parseInt(e.getText().toString()));
                    });

                }
            });

            amount.setText("Ksh. " + pendingAmount);
            billname.setText("Overal Payment");

            c.setOnClickListener(v2 -> {
                myDialog.cancel();
            });

            Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            myDialog.show();
        });

        getAverages();

        setAverages();

        getUser(getContext());

        fixAdapter();

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void getUser(Context context) {
        if (isNetworkConnected()) {
            Call<User> call = requestInterface.getUser();
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                    assert response.body() != null;

                    AppDatabase appDatabase = AppDatabase.getDatabase(context);
                    appDatabase.userDao().insertUser(response.body());
                }

                @Override
                public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

                    Toast.makeText(context, "Failed to insert user", Toast.LENGTH_SHORT).show();
                }
            });

            UserViewModel u = ViewModelProviders.of(this).get(UserViewModel.class);
            if (u.getUser(getContext(), token) != null) {
                v.setText("Welcome, " + u.getUser(getContext(), token).getUsername());
            }
        } else {
            Toast.makeText(context, "Please Connect your Internet or Wifi", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAverages() {
        if (isNetworkConnected()) {
            AverageViewModel a = ViewModelProviders.of(this).get(AverageViewModel.class);
            if (a.getAverage(getContext()) != null) {
                if (a.getAverage(getContext()).getPaid() == null) {
                    paid.setText("Ksh. " + 0);
                } else {
                    paid.setText("Ksh. " + a.getAverage(getContext()).getPaid());
                }
                if (a.getAverage(getContext()).getPending() == null) {
                    pending.setText("Ksh. " + 0);
                } else {
                    pendingAmount = Integer.parseInt(a.getAverage(getContext()).getPending());
                    pending.setText("Ksh. " + a.getAverage(getContext()).getPending());
                }
//            complain.setText(a.getAverage(getContext()).getComplain());
            }
        } else {
            Toast.makeText(getContext(), "Please Connect your Internet or Wifi", Toast.LENGTH_SHORT).show();
        }
    }

    private void fixAdapter() {
        if (isNetworkConnected()) {
            BillViewModel model = ViewModelProviders.of(this).get(BillViewModel.class);
            model.getBillList(getContext(), token).observe(this, billList -> {
                rvBills = (RecyclerView) Objects.requireNonNull(getView()).findViewById(R.id.rvBills);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                rvBills.setLayoutManager(layoutManager);
                mAdapter = new BillAdapter(getActivity(), billList, token);
                rvBills.setAdapter(mAdapter);
                layoutManager.setReverseLayout(true);
                layoutManager.setStackFromEnd(true);
            });
        } else {
            Toast.makeText(getContext(), "Please Connect your Internet or Wifi", Toast.LENGTH_SHORT).show();

        }
    }

    private void getAverages() {
        if (isNetworkConnected()) {
            requestInterface.getAverage().enqueue(new Callback<Average>() {
                @Override
                public void onResponse(@NonNull Call<Average> call, @NonNull Response<Average> response) {
                    Average average = response.body();
                    assert average != null;
                    appDatabase.averageDao().insertAverages(average);
                }

                @Override
                public void onFailure(@NonNull Call<Average> call, @NonNull Throwable t) {
                    Toast.makeText(getContext(), "Failed To Get Averages", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getContext(), "Please Connect your Internet or Wifi", Toast.LENGTH_SHORT).show();
        }

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

    private void stkPush(String phone, int amount) {

        if (isNetworkConnected()) {
            BaseApp app = (BaseApp) getActivity().getApplicationContext();
            RequestInterface requestInterface = APIClient.getClient(app.settings.getBearerToken()).create(RequestInterface.class);
            requestInterface.stkPush(phone, "ALLPAYMENT", amount).enqueue(new Callback<MpesaResponse>() {
                @Override
                public void onResponse(@NonNull Call<MpesaResponse> call, @NonNull Response<MpesaResponse> response) {
                    Toast.makeText(getContext(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    myDialog.cancel();
                    dialog.cancel();
                }

                @Override
                public void onFailure(@NonNull Call<MpesaResponse> call, @NonNull Throwable t) {
                    Toast.makeText(getContext(), "Failed Try Again" + t, Toast.LENGTH_LONG).show();
                    myDialog.cancel();
                    dialog.cancel();
                }
            });
        } else {
            Toast.makeText(getContext(), "Please Connect your Internet or Wifi", Toast.LENGTH_SHORT).show();
        }
    }


    private boolean isNetworkConnected() {
        try {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
            assert mConnectivityManager != null;
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            return mNetworkInfo != null;

        } catch (NullPointerException e) {
            return false;

        }
    }

}
