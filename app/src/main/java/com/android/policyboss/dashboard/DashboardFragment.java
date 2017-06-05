package com.android.policyboss.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;
import com.android.policyboss.carinsurance.CarInsuranceActivity;
import com.android.policyboss.healthinsurance.HealthInsuranceActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements View.OnClickListener {

    TextView txtCarInsurance, txtHealthInsurance;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initview(view);
        setListener();
        return view;
    }

    private void setListener() {
        txtCarInsurance.setOnClickListener(this);
        txtHealthInsurance.setOnClickListener(this);
    }

    private void initview(View view) {
        txtCarInsurance = (TextView) view.findViewById(R.id.txtCarInsurance);
        txtHealthInsurance = (TextView) view.findViewById(R.id.txtHealthInsurance);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtCarInsurance:
                startActivity(new Intent(getActivity(), CarInsuranceActivity.class));
                //Toast.makeText(getActivity(), "Car insurance", Toast.LENGTH_SHORT).show();
                break;
            case R.id.txtHealthInsurance:
                startActivity(new Intent(getActivity(), HealthInsuranceActivity.class));
                break;
        }
    }
}
