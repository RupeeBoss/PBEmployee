package com.android.policyboss.healthinsurance;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.policyboss.R;
import com.android.policyboss.carinsurance.CarQuoteGenerate;
import com.android.policyboss.core.models.HealthQuotesEntity;
import com.android.policyboss.core.models.MototrQuotesEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import static com.android.policyboss.core.controller.database.DatabaseController.getProfessionalID1;

/**
 * Created by IN-RB on 31-05-2017.
 */

public class HealthQuotesAdapter extends RecyclerView.Adapter<HealthQuotesAdapter.QuotesItem> {
    Activity mContext;
    List<HealthQuotesEntity> listHealthQuotes;

    public HealthQuotesAdapter(Activity mContext, List<HealthQuotesEntity> quotes) {
        this.mContext = mContext;
        this.listHealthQuotes = quotes;
    }

    public class QuotesItem extends RecyclerView.ViewHolder {

        ImageView imgInsuranceCompLogo;
        TextView txtInsuranceCompName, txtTotalPremium, txtPolicyTerm, txtSumAssured;
        Button btnBuyNow;

        public QuotesItem(View itemView) {
            super(itemView);
            imgInsuranceCompLogo = (ImageView) itemView.findViewById(R.id.imgInsuranceCompLogo);
            txtInsuranceCompName = (TextView) itemView.findViewById(R.id.txtInsuranceCompName);
            txtTotalPremium = (TextView) itemView.findViewById(R.id.txtTotalPremium);
            txtPolicyTerm = (TextView) itemView.findViewById(R.id.txtPolicyTerm);
            txtSumAssured = (TextView) itemView.findViewById(R.id.txtSumAssured);
            btnBuyNow = (Button) itemView.findViewById(R.id.btnBuyNow);

        }
    }

    @Override
    public QuotesItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.health_quote_item, parent, false);
        return new HealthQuotesAdapter.QuotesItem(view);
    }

    @Override
    public void onBindViewHolder(QuotesItem holder, int position) {

        if (holder instanceof QuotesItem) {
            final HealthQuotesEntity entity = listHealthQuotes.get(position);
            holder.txtInsuranceCompName.setText(entity.getInsurerName());
            holder.txtTotalPremium.setText("Premium amount Rs." + entity.getNetPremium());
            holder.txtSumAssured.setText("Sum insured " + entity.getSumInsured());
            holder.txtPolicyTerm.setText("Policy term " + entity.getPolicyTermYear());

            /*Glide.with(mContext)
                    .load(getProfessionalID1(entity.getInsurerId()))
                    .into(holder.imgInsuranceCompLogo);*/

            holder.btnBuyNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((HealthQuoteActivity) mContext).BuyHealth(entity);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listHealthQuotes.size();
    }
}
