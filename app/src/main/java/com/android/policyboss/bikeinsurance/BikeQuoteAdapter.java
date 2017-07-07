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
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.policyboss.core.controller.database.DatabaseController.getProfessionalID1;

/**
 * Created by IN-RB on 06-07-2017.
 */

public class BikeQuoteAdapter extends RecyclerView.Adapter<BikeQuoteAdapter.BikeQuoteItem> {


    Activity mContext;
    List<ResponseEntity> bikeRespEntitytList;

    public BikeQuoteAdapter(Activity mContext ,List<ResponseEntity> bikeRespEntitytList) {
        this.mContext = mContext;
        this.bikeRespEntitytList = bikeRespEntitytList;
    }

    public class BikeQuoteItem extends RecyclerView.ViewHolder{
        public TextView txtInsurerName ,txtNetPremium, txtServtax, txtFinalPremium;
        ImageView ivBankLogo;
        public BikeQuoteItem(View itemView) {
            super(itemView);
            txtInsurerName = (TextView)itemView.findViewById(R.id.txtInsurerName);
            txtNetPremium = (TextView)itemView.findViewById(R.id.txtNetPremium);
            txtServtax = (TextView)itemView.findViewById(R.id.txtServtax);
            txtFinalPremium = (TextView)itemView.findViewById(R.id.txtFinalPremium);
            ivBankLogo = (ImageView)itemView.findViewById(R.id.ivBankLogo);
        }
    }

    @Override
    public BikeQuoteItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_bikequote_item,parent,false);
        return new BikeQuoteAdapter.BikeQuoteItem(itemView);
    }

    @Override
    public void onBindViewHolder(BikeQuoteItem holder, int position) {

        final  ResponseEntity responseEntity = bikeRespEntitytList.get(position);
        holder.txtInsurerName.setText(responseEntity.getInsurer().getInsurer_Name());

//        try{
//            holder.txtNetPremium.setText("" + responseEntity.getPremium_Breakup().getNet_premium());
//        }catch (Exception ex)
//        {
//            holder.txtNetPremium.setText("");
//        }
//
//        try{
//            holder.txtServtax.setText("" + responseEntity.getPremium_Breakup().getService_tax());
//        }catch (Exception ex)
//        {
//            holder.txtServtax.setText("");
//        }
//
//
//
//        try{
//            holder.txtFinalPremium.setText("" + responseEntity.getPremium_Breakup().getFinal_premium());
//        }catch (Exception ex)
//        {
//            holder.txtFinalPremium.setText("");
//        }

        ///////////////////////////////////////////////
        if( responseEntity.getPremium_Breakup().getNet_premium() != null) {
            holder.txtNetPremium.setText("" + responseEntity.getPremium_Breakup().getNet_premium());
        }else{
            holder.txtNetPremium.setText("");
        }

        if( responseEntity.getPremium_Breakup().getService_tax() != null) {
            holder.txtServtax.setText("" + responseEntity.getPremium_Breakup().getService_tax());
        }else{
            holder.txtServtax.setText("");
        }

        if( responseEntity.getPremium_Breakup().getFinal_premium() != null) {
            holder.txtFinalPremium.setText("" + responseEntity.getPremium_Breakup().getFinal_premium());
        }else {
            holder.txtFinalPremium.setText("");
        }

        Glide.with(mContext)
                .load(getProfessionalID1(responseEntity.getInsurer().getInsurer_ID()))
                .into(holder.ivBankLogo);
    }


    @Override
    public int getItemCount() {
        return bikeRespEntitytList.size();
    }
}
