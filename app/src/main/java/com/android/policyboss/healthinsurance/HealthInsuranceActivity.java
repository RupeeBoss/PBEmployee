package com.android.policyboss.healthinsurance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.controller.database.DatabaseController;
import com.android.policyboss.core.models.CoverModelInfo;
import com.android.policyboss.core.requestEntity.HealthRequestEntity;
import com.android.policyboss.notification.NotificationActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class HealthInsuranceActivity extends BaseActivity implements View.OnClickListener {

    public final static String AGE_DETAIL_INFO = "coverinfo";
    public final static String HEALTH_QUOTE_REQUEST = "healthquote_request";

    HealthRequestEntity healthRequestEntity;

    Spinner spSumAssured, spCover, spCoverFor, spCoverForKids;
    ArrayAdapter<String> listsumAssured, listCover, listCoverFor, listCoverForKids;
    ArrayAdapter<String> cityAdapter;
    AutoCompleteTextView autoCity;
    List<String> cityList;
    DatabaseController databaseController;
    Button btnContinue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_insurance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init_widgets();

        setListeners();
        spCoverFor.setVisibility(View.GONE);
        spCoverForKids.setVisibility(View.GONE);
        realm = Realm.getDefaultInstance();
        databaseController = new DatabaseController(this, realm);
        cityList = new ArrayList<>();
        cityList = databaseController.getHealthCity();

        prepareSpinnersData();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.notification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {

            case R.id.action_notification:
                intent = new Intent(HealthInsuranceActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setListeners() {
        listCoverFor = null;
        spCover.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spCover.getSelectedItem().toString().equals("Self")) {
                    //self
                    listCoverFor = new
                            ArrayAdapter<String>(HealthInsuranceActivity.this, android.R.layout.simple_list_item_1,
                            getResources().getStringArray(R.array.health_self_type));
                    spCoverForKids.setVisibility(View.GONE);
                    spCoverFor.setVisibility(View.VISIBLE);

                } else if (spCover.getSelectedItem().toString().equals("Family")) {
                    //family
                    listCoverFor = new
                            ArrayAdapter<String>(HealthInsuranceActivity.this, android.R.layout.simple_list_item_1,
                            getResources().getStringArray(R.array.health_family_type));
                    spCoverFor.setVisibility(View.VISIBLE);
                    spCoverForKids.setVisibility(View.VISIBLE);
                } else if (spCover.getSelectedItem().toString().equals("Parents")) {
                    //parents
                    listCoverFor = new
                            ArrayAdapter<String>(HealthInsuranceActivity.this, android.R.layout.simple_list_item_1,
                            getResources().getStringArray(R.array.health_parent_type));
                    spCoverFor.setVisibility(View.VISIBLE);
                    spCoverForKids.setVisibility(View.GONE);
                }

                spCoverFor.setAdapter(listCoverFor);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void prepareSpinnersData() {

        listsumAssured = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.health_sum_assured)) {
                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
                    }
                };
        spSumAssured.setAdapter(listsumAssured);


        listCover = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.health_cover)) {
                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);

                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
                    }
                };
        spCover.setAdapter(listCover);

        listCoverForKids = new
                ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.health_family_kids)) {
                    @Override
                    public boolean isEnabled(int position) {
                        if (position == 0) {
                            // Disable the first item from Spinner
                            // First item will be use for hint
                            return false;
                        } else {
                            return true;
                        }
                    }

                    @Override
                    public View getDropDownView(int position, View convertView,
                                                ViewGroup parent) {
                        View view = super.getDropDownView(position, convertView, parent);
                        TextView tv = (TextView) view;
                        if (position == 0) {
                            // Set the hint text color gray
                            tv.setTextColor(Color.GRAY);
                        } else {
                            tv.setTextColor(Color.BLACK);
                        }
                        return view;
                    }
                };
        spCoverForKids.setAdapter(listCoverForKids);


        cityAdapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, cityList);
        autoCity.setAdapter(cityAdapter);
        autoCity.setThreshold(1);
    }


    private void init_widgets() {
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(this);
        spSumAssured = (Spinner) findViewById(R.id.spSumAssured);
        spCover = (Spinner) findViewById(R.id.spCover);
        spCoverFor = (Spinner) findViewById(R.id.spCoverFor);
        spCoverForKids = (Spinner) findViewById(R.id.spCoverForKids);
        autoCity = (AutoCompleteTextView) findViewById(R.id.autoCity);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnContinue) {

            healthRequestEntity = new HealthRequestEntity();

            if (spSumAssured.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Select insured amount.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (spCover.getSelectedItemPosition() == 0) {
                Toast.makeText(this, "Select insurence cover for.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (autoCity.getText().toString().equals("")) {
                Toast.makeText(this, "Enter city name.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (databaseController.getHealthCityID(autoCity.getText().toString()) == 0) {
                Toast.makeText(this, "Invalid city.", Toast.LENGTH_SHORT).show();
                return;
            }
            CoverModelInfo info = new CoverModelInfo();
            info.setCover(spCover.getSelectedItemPosition());

            if (spCover.getSelectedItem().toString().equals("Self")) {
                if (spCoverFor.getSelectedItem().toString().equals("UnMarried")) {
                    healthRequestEntity.setMaritalStatusID(1);
                } else {
                    healthRequestEntity.setMaritalStatusID(2);
                }
            } else {
                healthRequestEntity.setMaritalStatusID(2);
            }

            //family
            if (spCover.getSelectedItemPosition() == 2) {
                if (spCoverForKids.getSelectedItemPosition() == 0) {
                    Toast.makeText(this, "Select number of kids", Toast.LENGTH_SHORT).show();
                    return;
                }
                //0 self 1 spouse 2 both
                info.setCoverForFamily(spCoverFor.getSelectedItemPosition());
                info.setNoofKids(Integer.parseInt(spCoverForKids.getSelectedItem().toString()));
            } else if (spCover.getSelectedItemPosition() == 3) { //parents
                // 0 father 1 mother 3 both
                info.setCoverForParents(spCoverFor.getSelectedItemPosition());
            }


            healthRequestEntity.setSumInsured(spSumAssured.getSelectedItem().toString());
            healthRequestEntity.setCityID(databaseController.getHealthCityID(autoCity.getText().toString()));
            healthRequestEntity.setPolicyFor(spCover.getSelectedItem().toString());


            startActivity(new Intent(this, HealthInsuranceAgeDetailActivity.class)
                    .putExtra(AGE_DETAIL_INFO, info)
                    .putExtra(HEALTH_QUOTE_REQUEST, healthRequestEntity));

        }
    }
}
