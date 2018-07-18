package com.daniel.testionix.adapters.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.testionix.R;
import com.daniel.testionix.adapters.HomeAdapter;
import com.daniel.testionix.core.models.ItemsModel;

public class HomeViewHolder extends RecyclerView.ViewHolder {

    private TextView txtTitle;
    private LinearLayout lyCard;

    private HomeViewHolder(View parent, TextView txtTitle, LinearLayout lyCard) {
        super(parent);
        this.txtTitle = txtTitle;
        this.lyCard = lyCard;
    }

    public static HomeViewHolder newInstance(View parent) {
        TextView txtTitle = parent.findViewById(R.id.txtTitle);
        LinearLayout lyCard = parent.findViewById(R.id.lyCard);

        return new HomeViewHolder(parent, txtTitle, lyCard);
    }


    public void createViewAdapter(HomeViewHolder mHolder, final ItemsModel item, final HomeAdapter.OnItemClickListener listener) {

        mHolder.txtTitle.setText(item.getName());

        mHolder.lyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item, v);
            }
        });

    }
}
