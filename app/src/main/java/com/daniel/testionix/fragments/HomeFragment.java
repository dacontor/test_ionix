package com.daniel.testionix.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.testionix.R;
import com.daniel.testionix.adapters.HomeAdapter;
import com.daniel.testionix.core.models.ItemsModel;
import com.daniel.testionix.core.models.LoginObject;
import com.daniel.testionix.utils.SystemUtils;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private LoginObject mObject;
    private RecyclerView listHome;

    public static HomeFragment newInstance(LoginObject data) {
        HomeFragment fragment = new HomeFragment();
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

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        listHome = view.findViewById(R.id.listHome);

        listHome.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        listHome.setLayoutManager(linearLayoutManager);

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadAdapter();

    }

    private void loadAdapter() {

        HomeAdapter adapter = new HomeAdapter(mObject.getResult().getItems(), listener);
        listHome.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private HomeAdapter.OnItemClickListener listener = new HomeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(ItemsModel data, View view) {
            if (data != null)
                initFragment(DetailFragment.newInstance(data));
        }
    };

    private void initFragment(Fragment fragment) {

        SystemUtils.replaceFragment(Objects.requireNonNull(getActivity()),
                R.id.container,
                "DetailFragment",
                true,
                null,
                fragment);

    }

}
