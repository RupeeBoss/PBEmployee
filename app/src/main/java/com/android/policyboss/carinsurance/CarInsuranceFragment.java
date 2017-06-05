package com.android.policyboss.carinsurance;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarInsuranceFragment extends BaseFragment implements View.OnClickListener {

    Button btnNewCar, btnRenewal;

    public CarInsuranceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewCar = inflater.inflate(R.layout.fragment_carinsurance, container, false);
        init(viewCar);
        setListener();
        startAnimation();
        return viewCar;
    }

    private void startAnimation() {
       /* ObjectAnimator animX = ObjectAnimator.ofFloat(btnNewCar, "y", 500f);
        ObjectAnimator animY = ObjectAnimator.ofFloat(btnRenewal, "y", 100f);
        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(animX, animY);
        animSetXY.start();*/

        btnRenewal.animate().x(00f).y(500f).setDuration(1000);
        btnNewCar.animate().x(00f).y(370f).setDuration(800);
    }

    private void setListener() {
        btnNewCar.setOnClickListener(this);
        btnRenewal.setOnClickListener(this);
    }

    private void init(View viewCar) {
        btnNewCar = (Button) viewCar.findViewById(R.id.btnNewCar);
        btnRenewal = (Button) viewCar.findViewById(R.id.btnRenewal);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnNewCar:
                break;
            case R.id.btnRenewal:
                startActivity(new Intent(getActivity(), RenewalCarActivity.class));
                break;
        }
    }
}
