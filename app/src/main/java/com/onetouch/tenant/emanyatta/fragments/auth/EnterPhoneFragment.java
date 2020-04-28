package com.onetouch.tenant.emanyatta.fragments.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.apps.nationfy.Constants.BaseApp;
import com.apps.nationfy.R;
import com.google.android.material.textfield.TextInputEditText;
import com.lamudi.phonefield.PhoneInputLayout;
import com.onetouch.tenant.emanyatta.data.local.model.LogIn;
import com.onetouch.tenant.emanyatta.data.network.APIClient;
import com.onetouch.tenant.emanyatta.data.network.RequestInterface;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnterPhoneFragment extends Fragment {
    public static String nPN;
    public String n;
    @BindView(R.id.btnContinue)
    Button btnContinue;
    @BindView(R.id.txtPassword)
    TextInputEditText txtPassword;
    @BindView(R.id.edit_text)
    PhoneInputLayout phoneInputLayout;
    private RequestInterface requestInterface;
    private ProgressDialog dialog;
    private String TAG = "EPA";
    private String p;
    private String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_enter_phone, container, false);
        ButterKnife.bind(this, view);

        BaseApp app = (BaseApp) Objects.requireNonNull(getActivity()).getApplicationContext();

        requestInterface = APIClient.getClient(app.settings.getBearerToken()).create(RequestInterface.class);

        btnContinue.setOnClickListener(v -> {
            dialog = ProgressDialog.show(getContext(), "",
                    "Loading. Please wait...", true);
            dialog.show();
            String phoneNumber = phoneInputLayout.getPhoneNumber();
            nPN = phoneNumber.replace("+", "");
            password = txtPassword.getText().toString();
            n = nPN;
            p = password;
            enterPhoneNumber();
        });

        phoneInputLayout.setHint(R.string.number_hint);
        phoneInputLayout.setDefaultCountry("KE");

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

    private void enterPhoneNumber() {

        boolean valid = true;
        if (phoneInputLayout.isValid()) {
            phoneInputLayout.setError(null);
        } else {
            dialog.cancel();
            phoneInputLayout.setError("Invalid Phone Number");
            valid = false;
        }

        if (valid) {
            if (isNetworkConnected()) {
                Call<LogIn> call = requestInterface.getLoginResponse(n, p, 1);
                call.enqueue(new Callback<LogIn>() {
                    @Override
                    public void onResponse(Call<LogIn> call, Response<LogIn> response) {

                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: Ikosawa" + response); //ToDo:: Replace with Timber.
                            dialog.cancel();
                            if (response.body().getMessage().contains("Success OTP sent")) {

                                EnterOtpFragment fragment = new EnterOtpFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("phone_number", n);
                                fragment.setArguments(bundle);
                                FragmentManager fm = getFragmentManager();
                                assert fm != null;
                                fm.beginTransaction().replace(R.id.flMain, fragment).commit();
                            } else {

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LogIn> call, Throwable t) {
                        dialog.cancel();
                    }
                });
            } else {
                Toast.makeText(getContext(), "Please Connect your Internet or Wifi", Toast.LENGTH_SHORT).show();
            }
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
