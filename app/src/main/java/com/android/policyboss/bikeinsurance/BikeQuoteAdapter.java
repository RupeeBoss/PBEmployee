package com.android.policyboss.bikeinsurance;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.R;
import com.android.policyboss.carinsurance.CarQuoteGenerate;
import com.android.policyboss.carinsurance.PremiumPopUpActivity;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.response.BikePremiumResponse;
import com.android.policyboss.utility.Constants;
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
        if (response.getResponse() != null)
            this.listQuotes = response.getResponse();
        else
            this.listQuotes = null;
    }

    public class BikeQuoteItem extends RecyclerView.ViewHolder {
        public TextView txtInsurerName, txtIDV, txtFinalPremium, txtPremiumBreakUp;
        ImageView imgInsurerLogo;

        public BikeQuoteItem(View itemView) {
            super(itemView);
            txtInsurerName = (TextView) itemView.findViewById(R.id.txtInsuranceCompName);
            txtIDV = (TextView) itemView.findViewById(R.id.txtIDV);
            txtFinalPremium = (TextView) itemView.findViewById(R.id.txtFinalPremium);
            imgInsurerLogo = (ImageView) itemView.findViewById(R.id.imgInsurerLogo);
            txtPremiumBreakUp = (TextView) itemView.findViewById(R.id.txtPremiumBreakUp);
        }
    }

    @Override
    public BikeQuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_bikequote_item_new, parent, false);
        return new BikeQuoteAdapter.BikeQuoteItem(itemView);
    }

    @Override
    public void onBindViewHolder(BikeQuoteItem holder, int position) {

        final ResponseEntity responseEntity = listQuotes.get(position);

        holder.txtInsurerName.setText(responseEntity.getInsurer().getInsurer_Name());
        // holder.txtIDV.setText(responseEntity);
        if (responseEntity.getPremium_Breakup() != null) {
            holder.txtFinalPremium.setText("\u20B9 " + Math.round(Double.parseDouble(responseEntity.getPremium_Breakup().getFinal_premium())));
        } else {
            holder.txtFinalPremium.setText("");
        }

        holder.txtIDV.setText("\u20B9 " + String.valueOf(responseEntity.getLM_Custom_Request().getVehicle_expected_idv()));
        Glide.with(mContext)
                .load(getProfessionalID1(Integer.parseInt(responseEntity.getInsurer().getInsurer_ID())))
                .into(holder.imgInsurerLogo);

        holder.txtFinalPremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BikeQuoteActivity) mContext).redirectToBuy(responseEntity.getService_Log_Unique_Id());
            }
        });
        holder.txtPremiumBreakUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BikeQuoteActivity) mContext).redirectToPopUpPremium(responseEntity, response.getSummary(), responseEntity.getLM_Custom_Request().getVehicle_expected_idv());
            }
        });

        if (responseEntity.getPremium_Breakup().getListAppliedAddons() != null) {
            if (responseEntity.getPremium_Breakup().getListAppliedAddons().size() != 0) {
                Toast.makeText(mContext, "Applied " + position, Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public int getItemCount() {
        if (listQuotes != null) {
            return listQuotes.size();
        } else {
            return 0;
        }
    }

    public void setQuoteResponse(BikePremiumResponse response) {
        this.response = response;
        if (response.getResponse() != null)
            this.listQuotes = response.getResponse();
    }
}
