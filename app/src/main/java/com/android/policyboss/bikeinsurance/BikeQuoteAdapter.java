package com.android.policyboss.bikeinsurance;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.policyboss.R;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.policyboss.core.controller.database.DatabaseController.getProfessionalID1;

/**
 * Created by IN-RB on 06-07-2017.
 */

public class BikeQuoteAdapter extends RecyclerView.Adapter<BikeQuoteAdapter.BikeQuoteItem> {


    Activity mContext;
    BikePremiumResponse response;
    List<ResponseEntity> listQuotes;

    public BikeQuoteAdapter(Activity mContext, BikePremiumResponse response) {
        this.mContext = mContext;
        this.response = response;
        this.listQuotes = response.getResponse();
    }

    public class BikeQuoteItem extends RecyclerView.ViewHolder {
        public TextView txtInsurerName, txtIDV, txtFinalPremium;
        ImageView imgInsurerLogo;

        public BikeQuoteItem(View itemView) {
            super(itemView);
            txtInsurerName = (TextView) itemView.findViewById(R.id.txtInsurerName);
            txtIDV = (TextView) itemView.findViewById(R.id.txtIDV);
            txtFinalPremium = (TextView) itemView.findViewById(R.id.txtFinalPremium);
            imgInsurerLogo = (ImageView) itemView.findViewById(R.id.imgInsurerLogo);
        }
    }

    @Override
    public BikeQuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_bikequote_item, parent, false);
        return new BikeQuoteAdapter.BikeQuoteItem(itemView);
    }

    @Override
    public void onBindViewHolder(BikeQuoteItem holder, int position) {

        final ResponseEntity responseEntity = listQuotes.get(position);

        holder.txtInsurerName.setText(responseEntity.getInsurer().getInsurer_Name());
        holder.txtIDV.setText(response.getSummary().getRequest_Product().getVehicle_expected_idv());
        if (responseEntity.getPremium_Breakup().getFinal_premium() != null) {
            holder.txtFinalPremium.setText("Rs. " +Math.round( responseEntity.getPremium_Breakup().getFinal_premium()));
        } else {
            holder.txtFinalPremium.setText("");
        }

        Glide.with(mContext)
                .load(getProfessionalID1(responseEntity.getInsurer().getInsurer_ID()))
                .into(holder.imgInsurerLogo);
    }


    @Override
    public int getItemCount() {
        return listQuotes.size();
    }
}
