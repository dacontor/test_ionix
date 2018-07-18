package com.daniel.testionix.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniel.testionix.R;
import com.daniel.testionix.core.models.ItemsModel;

public class DetailFragment extends Fragment {

    private ItemsModel mObject;

    private TextView email;
    private TextView number;

    public static DetailFragment newInstance(ItemsModel data) {
        DetailFragment fragment = new DetailFragment();
        Bundle b = new Bundle();
        b.putParcelable("data", data);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle b = getArguments();

        assert b != null;
        mObject = b.getParcelable("data");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        email = view.findViewById(R.id.email);
        number = view.findViewById(R.id.number);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadData();

    }

    private void loadData() {

        email.setText(mObject.getDetail().getEmail());
        number.setText(mObject.getDetail().getPhone_number());

    }
}
