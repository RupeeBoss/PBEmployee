package com.android.policyboss.carinsurance;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.policyboss.R;
import com.android.policyboss.core.models.MototrQuotesEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.policyboss.core.controller.database.DatabaseController.getProfessionalID1;

/**
 * Created by IN-RB on 31-05-2017.
 */

public class CarQuotesAdapter  extends RecyclerView.Adapter<CarQuotesAdapter.QuotesItem> {
    Activity mContext;
    List<MototrQuotesEntity> quoteEntities;

    public CarQuotesAdapter(Activity mContext, List<MototrQuotesEntity> quoteEntities) {
        this.mContext = mContext;
        this.quoteEntities = quoteEntities;
    }

    public class QuotesItem extends RecyclerView.ViewHolder{
        TextView tvcomprehensiveName, tvidvAmt, tvpremium, tvpolicyterm,  btnBuyNoew;
        LinearLayout lyPremiumBreakup ,lymoredetail;
        ImageView ivBankLogo;

        public QuotesItem(View itemView) {
            super(itemView);
            tvcomprehensiveName = (TextView) itemView.findViewById(R.id.tvcomprehensiveName);
            tvidvAmt = (TextView) itemView.findViewById(R.id.tvidvAmt);
            tvpremium = (TextView) itemView.findViewById(R.id.tvpremium);
            tvpolicyterm = (TextView) itemView.findViewById(R.id.tvpolicyterm);

            btnBuyNoew = (TextView) itemView.findViewById(R.id.btnBuyNoew);
            ivBankLogo = (ImageView) itemView.findViewById(R.id.ivBankLogo);

            lyPremiumBreakup = (LinearLayout)itemView.findViewById(R.id.lyPremiumBreakup);
            lymoredetail = (LinearLayout)itemView.findViewById(R.id.lymoredetail);
           // ivInfo = (ImageView) itemView.findViewById(R.id.ivInfo);

        }
    }
    @Override
    public QuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quotes_item,parent,false);
        return  new CarQuotesAdapter.QuotesItem(view);
    }

    @Override
    public void onBindViewHolder(QuotesItem holder, int position) {
        final MototrQuotesEntity quoteEntity = quoteEntities.get(position);
        String comprehen = "Comprehensive";
        if(quoteEntity.getAddOn_Name()!=null)
        {
            if(quoteEntity.getAddOn_Name().isEmpty()) {
              //  comprehen = "";
            }else {
                String[] txt1 = quoteEntity.getAddOn_Name().split("'+'");
                for (int i = 0; i < txt1.length; i++) {
                    comprehen = "Comprehensive +" + txt1[i];
                }
            }
        }
        else {
            //comprehen = "Comprehensive";
        }
        holder.tvcomprehensiveName.setText(comprehen);
        holder.tvidvAmt.setText("" + quoteEntity.getIDV());
        holder.tvpremium.setText("" + quoteEntity.getNetPayablePayablePremium());
        holder.tvpolicyterm.setText("1 Year");
//        holder.tvLoanTenure.setText("" + quoteEntity.getLoanTenure());
//        holder.tvProcessingFee.setText("" + quoteEntity.getProcessingfee());
//        holder.tvEligibleLoan.setText("" + BigDecimal.valueOf(quoteEntity.getLoan_eligible()).toPlainString());
        Glide.with(mContext)
                .load(getProfessionalID1(quoteEntity.getInsurerId()))
                .into(holder.ivBankLogo);
        //change Fresco


        holder.btnBuyNoew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((CarQuoteGenerate) mContext).redirectToApplyLoan(quoteEntity);

            }
        });

        holder.lyPremiumBreakup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((CarQuoteGenerate) mContext).redirectToPopUpPremium(quoteEntity);

            }
        });

        holder.lymoredetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CarQuoteGenerate) mContext).redirectToPopUpCard(quoteEntity);
            }
        });


    }

    @Override
    public int getItemCount() {
        return quoteEntities.size();
    }
}
