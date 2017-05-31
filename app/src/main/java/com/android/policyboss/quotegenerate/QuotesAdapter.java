package com.android.policyboss.quotegenerate;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.policyboss.R;

/**
 * Created by IN-RB on 30-05-2017.
 */

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesItem>{

    @Override
    public QuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quotes_item,parent,false);
        return  new QuotesItem(view);
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
