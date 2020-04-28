package com.onetouch.tenant.emanyatta.fragments.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.apps.nationfy.Constants.BaseApp;
import com.apps.nationfy.R;
import com.chaos.view.PinView;
import com.onetouch.tenant.emanyatta.MainActivity;
import com.onetouch.tenant.emanyatta.data.local.AppDatabase;
import com.onetouch.tenant.emanyatta.data.local.model.OtpCheck;
import com.onetouch.tenant.emanyatta.data.network.APIClient;
import com.onetouch.tenant.emanyatta.data.network.RequestInterface;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterOtpFragment extends Fragment {

    private static String otp;
    @BindView(R.id.btnFinish)
    Button btnFinish;
    @BindView(R.id.firstPinView)
    PinView firstPinView;
    @BindView(R.id.txtPhoneNumer)
    TextView txtPhoneNumer;
    private String myNo;
    private String TAG = "";
    private ProgressDialog dialog;

    private RequestInterface requestInterface;
    private BaseApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enter_otp, container, false);
        ButterKnife.bind(this, view);

        app = (BaseApp) Objects.requireNonNull(getActivity()).getApplicationContext();
        requestInterface = APIClient.getClient(app.settings.getBearerToken()).create(RequestInterface.class);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myNo = bundle.getString("phone_number", "");
        }

        txtPhoneNumer.setText(myNo);

        btnFinish.setOnClickListener(v -> {
            dialog = ProgressDialog.show(getContext(), "",
                    "Loading. Please wait...", true);
            dialog.show();
            otp = Objects.requireNonNull(firstPinView.getText()).toString();
            verifyOtp();
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void verifyOtp() {

        if (isNetworkConnected()) {
            Call<OtpCheck> call = requestInterface.verifyOtp(otp, myNo);
            call.enqueue(new Callback<OtpCheck>() {
                @Override
                public void onResponse(@NonNull Call<OtpCheck> call, @NonNull Response<OtpCheck> response) {

                    Log.d(TAG, "onResponse: imepita" + response);

                    assert response.body() != null;
                    if (response.body().getMessage().contains("Correct OTP")) {

                        String token = response.headers().get("authorization");

                        dialog.cancel();

                        Intent launchNextActivity;
                        launchNextActivity = new Intent(getContext(), MainActivity.class);
                        Bundle b = new Bundle();
                        b.putString("token", token);
                        launchNextActivity.putExtras(b);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        launchNextActivity.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(launchNextActivity);
                        app.settings.setBearerToken(token);
                        app.settings.setPhoneNumber(myNo);
                        AppDatabase appDatabase = AppDatabase.getDatabase(getContext());
                        int uId = appDatabase.userDao().getUserId(myNo);
                        app.settings.setUserId(String.valueOf(uId));
                        app.settings.SetIsloggedIn(true);

                    } else {
                        dialog.cancel();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<OtpCheck> call, @NonNull Throwable t) {
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
