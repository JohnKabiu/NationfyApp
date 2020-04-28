package com.onetouch.tenant.emanyatta.fragments.main.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.nationfy.R;
import com.onetouch.tenant.emanyatta.data.local.adapter.ComplainAdapter;
import com.onetouch.tenant.emanyatta.data.local.viewmodel.ComplainViewModel;

import java.util.Objects;

public class ComplainFragment extends Fragment {

    ComplainAdapter mAdapter;
    RecyclerView rvComplains;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_complains, container, false);
        fixAdapter();
        return v;
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
        ComplainViewModel model = ViewModelProviders.of(this).get(ComplainViewModel.class);
        model.getComplainList().observe(this, complainList -> {
            rvComplains = (RecyclerView) Objects.requireNonNull(getView()).findViewById(R.id.rvComplains);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            rvComplains.setLayoutManager(layoutManager);
            mAdapter = new ComplainAdapter(getActivity(), complainList);
            rvComplains.setAdapter(mAdapter);
            layoutManager.setReverseLayout(true);
            layoutManager.setStackFromEnd(true);
        });
    }
}
