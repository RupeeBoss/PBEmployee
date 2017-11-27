package com.android.policyboss.notification;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.android.policyboss.R;
import com.android.policyboss.core.models.NotificationMasterEntity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class NotificationActivity extends AppCompatActivity {

    public Realm realm;
    RecyclerView rvNotification;
    List<NotificationMasterEntity> NotificationLst;
    NotificationAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        realm = Realm.getDefaultInstance();
        initialize();
    }

    private void initialize() {

        rvNotification = (RecyclerView) findViewById(R.id.rvNotification);
        rvNotification.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NotificationActivity.this);
        rvNotification.setLayoutManager(layoutManager);
        NotificationLst = realm.where(NotificationMasterEntity.class).findAll();
        if(NotificationLst.size() >0)
        {
            mAdapter = new NotificationAdapter(NotificationActivity.this, NotificationLst);
            rvNotification.setAdapter(mAdapter);
        }
        else{
            rvNotification.setAdapter(null);
            Snackbar.make(rvNotification, "No Data Available", Snackbar.LENGTH_SHORT).show();
        }


    }



}
