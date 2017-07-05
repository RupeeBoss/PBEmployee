package com.android.policyboss.salessupport;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.utility.QuotePuller;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesSupportFragment extends BaseFragment {


    public SalesSupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales_support, container, false);

        new BikeController(getActivity()).getBikePremium();

        return view;
    }

}
