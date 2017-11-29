package com.android.policyboss.bikeinsurance;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.android.policyboss.R;
import com.android.policyboss.core.models.AppliedAddonsPremiumBreakup;
import com.android.policyboss.core.models.MobileAddOn;

import java.util.List;

/**
 * Created by IN-RB on 17-11-2017.
 */

public class GridAddonAdapter extends RecyclerView.Adapter<GridAddonAdapter.AddonItem> {

    Activity context;

    List<AppliedAddonsPremiumBreakup> listAppliedAddon;

    public GridAddonAdapter(Activity context, List<AppliedAddonsPremiumBreakup> list) {
        this.context = context;
        this.listAppliedAddon = list;
    }

    public class AddonItem extends RecyclerView.ViewHolder {

        public TextView addonName;

        public AddonItem(View itemView) {
            super(itemView);
            addonName = (TextView) itemView.findViewById(R.id.addonName);
        }
    }


    @Override
    public AddonItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_grid_addon, parent, false);
        return new GridAddonAdapter.AddonItem(itemView);
    }

    @Override
    public void onBindViewHolder(final AddonItem holder, final int position) {

        if (holder instanceof AddonItem) {
            holder.addonName.setText("" + listAppliedAddon.get(position).getAddonName()
                    + " ( " + "\u20B9" + String.valueOf(Math.round(listAppliedAddon.get(position).getPriceAddon()) + ")"));
        }

    }

    @Override
    public int getItemCount() {
        return listAppliedAddon.size();
    }
}
