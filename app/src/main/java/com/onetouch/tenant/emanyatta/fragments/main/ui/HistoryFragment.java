package com.onetouch.tenant.emanyatta.fragments.main.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.adapter.BillHistoryMonthAdapter;
import com.onetouch.tenant.emanyatta.data.local.model.Month;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.BillHistoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HistoryFragment extends Fragment {

    private BillHistoryMonthAdapter mAdapter;
    private RecyclerView rvBills;
    private List<Month> months;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        loadMonths();
        fixAdapter();
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

    private void fixAdapter() {
        if (isNetworkConnected()) {
            BillHistoryViewModel m = ViewModelProviders.of(this).get(BillHistoryViewModel.class);
            m.getBillList(getContext()).observe(this, billHistories -> {
                rvBills = (RecyclerView) Objects.requireNonNull(getView()).findViewById(R.id.rvBills);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(RecyclerView.VERTICAL);
                rvBills.setLayoutManager(layoutManager);
                mAdapter = new BillHistoryMonthAdapter(getActivity(), months, billHistories);
                rvBills.setAdapter(mAdapter);
                layoutManager.setReverseLayout(false);
                layoutManager.setStackFromEnd(false);
            });
        } else {
            Toast.makeText(getContext(), "Please Connect your Internet or Wifi", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadMonths() {
        months = new ArrayList<>();

        Month a = new Month();
        a.setId(0);
        a.setName("January");

        Month b = new Month();
        b.setId(1);
        b.setName("February");

        Month c = new Month();
        c.setId(2);
        c.setName("March");

        Month d = new Month();
        d.setId(3);
        d.setName("April");

        Month e = new Month();
        e.setId(4);
        e.setName("May");

        Month f = new Month();
        f.setId(5);
        f.setName("June");

        Month g = new Month();
        g.setId(6);
        g.setName("July");

        Month h = new Month();
        h.setId(7);
        h.setName("August");

        Month i = new Month();
        i.setId(8);
        i.setName("September");

        Month j = new Month();
        j.setId(9);
        j.setName("October");

        Month k = new Month();
        k.setId(10);
        k.setName("November");

        Month l = new Month();
        l.setId(11);
        l.setName("December");

        months.add(a);
        months.add(b);
        months.add(c);
        months.add(d);
        months.add(e);
        months.add(f);
        months.add(g);
        months.add(h);
        months.add(i);
        months.add(j);
        months.add(k);
        months.add(l);

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
