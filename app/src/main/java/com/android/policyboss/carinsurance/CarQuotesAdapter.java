package com.android.policyboss.carinsurance;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.policyboss.R;
import com.android.policyboss.core.models.MototrQuotesEntity;
import com.bumptech.glide.Glide;
import java.math.BigDecimal;
import java.util.List;

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
        ImageView ivBankLogo;

        public QuotesItem(View itemView) {
            super(itemView);
            tvcomprehensiveName = (TextView) itemView.findViewById(R.id.tvcomprehensiveName);
            tvidvAmt = (TextView) itemView.findViewById(R.id.tvidvAmt);
            tvpremium = (TextView) itemView.findViewById(R.id.tvpremium);
            tvpolicyterm = (TextView) itemView.findViewById(R.id.tvpolicyterm);

            btnBuyNoew = (TextView) itemView.findViewById(R.id.btnBuyNoew);
            ivBankLogo = (ImageView) itemView.findViewById(R.id.ivBankLogo);
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
        holder.tvcomprehensiveName.setText("Comprehensive  + " + quoteEntity.getAddOn_Name());
        holder.tvidvAmt.setText("" + quoteEntity.getIDV());
        holder.tvpremium.setText("" + quoteEntity.getAddOn_Premium());
        holder.tvpolicyterm.setText("1 Year");
//        holder.tvLoanTenure.setText("" + quoteEntity.getLoanTenure());
//        holder.tvProcessingFee.setText("" + quoteEntity.getProcessingfee());
//        holder.tvEligibleLoan.setText("" + BigDecimal.valueOf(quoteEntity.getLoan_eligible()).toPlainString());
//        Glide.with(mContext)
//                .load(quoteEntity.get())
//                .into(holder.ivBankLogo);
        //change Fresco


        holder.btnBuyNoew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((CarQuoteGenerate) mContext).redirectToApplyLoan(quoteEntity);

            }
        });
//        holder.ivInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mContext.startActivity(new Intent(mContext, QuoteInfoActivity.class).putExtra("QUOTEINFO", quoteEntity));
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return quoteEntities.size();
    }
}
