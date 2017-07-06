package com.android.policyboss.salessupport;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.bike.BikeController;
import com.android.policyboss.core.models.ResponseEntity;
import com.android.policyboss.core.response.BikePremiumResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalesSupportFragment extends BaseFragment implements IResponseSubcriber {

    List<ResponseEntity> mListBikeQuotes;

    public SalesSupportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sales_support, container, false);
        mListBikeQuotes = new ArrayList<>();
        new BikeController(getActivity()).getBikePremium(this);

        return view;
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        if (response instanceof BikePremiumResponse) {

            Log.d("SALES", "" + ((BikePremiumResponse) response).getResponse().size());
            List<ResponseEntity> list = ((BikePremiumResponse) response).getResponse();
            mListBikeQuotes = list;

        }
    }

    @Override
    public void OnFailure(Throwable t) {

    }
}
