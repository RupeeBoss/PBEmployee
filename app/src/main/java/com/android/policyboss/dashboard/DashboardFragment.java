package com.android.policyboss.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;
import com.android.policyboss.bikeinsurance.BikeInsuranceActivity;
import com.android.policyboss.carinsurance.CarInsuranceActivity;
import com.android.policyboss.healthinsurance.HealthInsuranceActivity;

import java.util.ArrayList;
import java.util.List;

import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements View.OnClickListener {

    LinearLayout txtCarInsurance, txtHealthInsurance, txtBikeInsurance;


    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initview(view);
        initBanner(view);
        setListener();
        return view;
    }

    private void initBanner(View view) {
        BannerSlider bannerSlider = (BannerSlider) view.findViewById(R.id.banner_slider1);
        List<Banner> banners = new ArrayList<>();
        //add banner using image url
        //banners.add(new RemoteBanner("Put banner image url here ..."));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.policy_banner_1));
        banners.add(new DrawableBanner(R.drawable.policy_banner_1));
        banners.add(new DrawableBanner(R.drawable.policy_banner_1));
        bannerSlider.setBanners(banners);
    }

    private void setListener() {
        txtCarInsurance.setOnClickListener(this);
        txtHealthInsurance.setOnClickListener(this);
        txtBikeInsurance.setOnClickListener(this);
    }

    private void initview(View view) {
        txtCarInsurance = (LinearLayout) view.findViewById(R.id.txtCarInsurance);
        txtHealthInsurance = (LinearLayout) view.findViewById(R.id.txtHealthInsurance);
        txtBikeInsurance = (LinearLayout) view.findViewById(R.id.txtBikeInsurance);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtCarInsurance:
                startActivity(new Intent(getActivity(), CarInsuranceActivity.class));
                break;
            case R.id.txtHealthInsurance:
                startActivity(new Intent(getActivity(), HealthInsuranceActivity.class));
                break;
            case R.id.txtBikeInsurance:
                startActivity(new Intent(getActivity(), BikeInsuranceActivity.class));
                break;
        }
    }
}
