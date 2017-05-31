package com.android.policyboss.carinsurance;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.policyboss.R;

/**
 * Created by IN-RB on 31-05-2017.
 */

public class CarQuotesAdapter  extends RecyclerView.Adapter<CarQuotesAdapter.QuotesItem> {

    @Override
    public QuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quotes_item,parent,false);
        return  new CarQuotesAdapter.QuotesItem(view);
    }

    @Override
    public void onBindViewHolder(QuotesItem holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class QuotesItem extends RecyclerView.ViewHolder{

        public QuotesItem(View itemView) {
            super(itemView);
        }
    }
}
