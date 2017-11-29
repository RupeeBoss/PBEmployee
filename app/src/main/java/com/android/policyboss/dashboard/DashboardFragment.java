package com.android.policyboss.dashboard;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;
import com.android.policyboss.bikeinsurance.BikeInsuranceActivity;
import com.android.policyboss.carinsurance.CarInsuranceActivity;
import com.android.policyboss.core.models.NotificationMasterEntity;
import com.android.policyboss.healthinsurance.HealthInsuranceActivity;
import com.android.policyboss.navigationview.HomeActivity;
import com.android.policyboss.notification.NotificationActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements View.OnClickListener {

    LinearLayout txtCarInsurance, txtHealthInsurance, txtBikeInsurance, llTravelIns;
    public Realm realm;
    TextView textNotifyItemCount;
    int mNotifyItemCount = 0;

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
        realm = Realm.getDefaultInstance();
        setListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void initBanner(View view) {
        BannerSlider bannerSlider = (BannerSlider) view.findViewById(R.id.banner_slider1);
        List<Banner> banners = new ArrayList<>();
        //add banner using image url
        //banners.add(new RemoteBanner("Put banner image url here ..."));
        //add banner using resource drawable
        banners.add(new DrawableBanner(R.drawable.policyboss_banner_1));
        banners.add(new DrawableBanner(R.drawable.policyboss_banner_2));
        banners.add(new DrawableBanner(R.drawable.policyboss_banner_1));
        bannerSlider.setBanners(banners);
    }

    private void setListener() {
        txtCarInsurance.setOnClickListener(this);
        txtHealthInsurance.setOnClickListener(this);
        txtBikeInsurance.setOnClickListener(this);
        llTravelIns.setOnClickListener(this);
    }

    private void initview(View view) {
        llTravelIns = (LinearLayout) view.findViewById(R.id.llTravelIns);
        txtCarInsurance = (LinearLayout) view.findViewById(R.id.txtCarInsurance);
        txtHealthInsurance = (LinearLayout) view.findViewById(R.id.txtHealthInsurance);
        txtBikeInsurance = (LinearLayout) view.findViewById(R.id.txtBikeInsurance);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtCarInsurance:
                txtCarInsurance.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.image_click));
                startActivity(new Intent(getActivity(), CarInsuranceActivity.class));
                break;
            case R.id.txtHealthInsurance:
                txtHealthInsurance.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.image_click));
                startActivity(new Intent(getActivity(), HealthInsuranceActivity.class));
                break;
            case R.id.txtBikeInsurance:
                txtBikeInsurance.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.image_click));
                startActivity(new Intent(getActivity(), BikeInsuranceActivity.class));
                break;
            case R.id.llTravelIns:
                Toast.makeText(getActivity(), "Coming Soon...", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.notification_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_notification);

        final List<NotificationMasterEntity>   NotificationLst = realm.where(NotificationMasterEntity.class).findAll();

            List<NotificationMasterEntity> NotifyLstCount = realm.where(NotificationMasterEntity.class).equalTo("isread", false).findAll();
            mNotifyItemCount = NotifyLstCount.size();

            View actionView = MenuItemCompat.getActionView(menuItem);
            textNotifyItemCount = (TextView) actionView.findViewById(R.id.notify_badge);
            textNotifyItemCount.setText( ""+ mNotifyItemCount);

            actionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onOptionsItemSelected(menuItem);


                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for (NotificationMasterEntity notificationMaster : NotificationLst) {
                                notificationMaster.setIsread(true);
                                realm.copyToRealmOrUpdate(notificationMaster);
                            }
                        }
                    });

                }
            });


        setupBadge();
        return;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {

            case R.id.action_notification:



                intent = new Intent(getActivity(), NotificationActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupBadge() {

        if (textNotifyItemCount != null) {
            if (mNotifyItemCount == 0) {
                if (textNotifyItemCount.getVisibility() != View.GONE) {
                    textNotifyItemCount.setVisibility(View.GONE);
                }
            } else {
                //textNotifyItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textNotifyItemCount.getVisibility() != View.VISIBLE) {
                    textNotifyItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }
}
