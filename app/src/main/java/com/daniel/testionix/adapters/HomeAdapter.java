package com.daniel.testionix.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.testionix.R;
import com.daniel.testionix.adapters.viewholder.HomeViewHolder;
import com.daniel.testionix.core.models.ItemsModel;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ItemsModel> mItems;
    private OnItemClickListener listener;

    public HomeAdapter(List<ItemsModel> list, OnItemClickListener listener) {
        mItems = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_home, viewGroup, false);
        return HomeViewHolder.newInstance(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position) {

        ItemsModel object = getItem(position);
        HomeViewHolder holder = (HomeViewHolder) viewHolder;

        createViewAdapter(holder, object);

    }

    private void createViewAdapter(HomeViewHolder holder, ItemsModel item) {
        holder.createViewAdapter(holder, item, listener);
    }

    private ItemsModel getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(ItemsModel item, View view);
    }

}
