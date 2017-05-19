package com.android.policyboss.carinsurance;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarInsuranceFragment extends BaseFragment {


    public CarInsuranceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewCar = inflater.inflate(R.layout.fragment_carinsurance, container, false);
        return viewCar;
    }

}
