package com.android.policyboss.healthinsurance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.models.CoverModelInfo;
import com.android.policyboss.core.requestEntity.HealthRequestEntity;
import com.android.policyboss.core.requestEntity.MemberListEntity;
import com.android.policyboss.personaldetail.CustomerDetailsActivity;
import com.android.policyboss.utility.DateTimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HealthInsuranceAgeDetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String HEALTH_QUOTE = "HealthInsuranceAgeDetailActivity.class";

    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

    CardView cvSelf, cvFamily, cvParents;
    LinearLayout llSelf, llSpouse, llKid1, llKid2, llKid3, llKid4;
    LinearLayout llFather, llMother;

    //self
    EditText etSelfDOB;
    RadioButton rbSelfMale;

    //family
    EditText etFamilySelfDOB, etFamilySpouseDOB;
    RadioButton rbFamilySelfMale, rbFamilySpouseMale;

    EditText etFamilyKid1DOB, etFamilyKid2DOB, etFamilyKid3DOB, etFamilyKid4DOB;
    RadioButton rbFamilyKid1MaleDOB, rbFamilySelfFemale, rbFamilySpouseFemale, rbFamilyKid2Male, rbFamilyKid3Male, rbFamilyKid4Male;

    //parents
    EditText etParentFatherDOB, etParentMotherDOB;

    Button btnContinue;

    HealthRequestEntity healthRequestEntity;
    List<MemberListEntity> listHealthMember;
    CoverModelInfo modelInfo;


    @Override
    protected void onResume() {
        super.onResume();
        listHealthMember = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_insurance_age_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init_widgets();
        setListener();


        if (getIntent().getParcelableExtra(HealthInsuranceActivity.AGE_DETAIL_INFO) != null) {
            modelInfo = getIntent().getParcelableExtra(HealthInsuranceActivity.AGE_DETAIL_INFO);
            HideandShow(modelInfo);
        }
        if (getIntent().getParcelableExtra(HealthInsuranceActivity.HEALTH_QUOTE_REQUEST) != null) {
            healthRequestEntity = getIntent().getParcelableExtra(HealthInsuranceActivity.HEALTH_QUOTE_REQUEST);
        }
    }

    private void setListener() {
        btnContinue.setOnClickListener(this);

        etSelfDOB.setOnClickListener(this);

        etFamilySelfDOB.setOnClickListener(this);
        etFamilySpouseDOB.setOnClickListener(this);
        etFamilyKid1DOB.setOnClickListener(this);
        etFamilyKid2DOB.setOnClickListener(this);
        etFamilyKid3DOB.setOnClickListener(this);
        etFamilyKid4DOB.setOnClickListener(this);

        etParentFatherDOB.setOnClickListener(this);
        etParentMotherDOB.setOnClickListener(this);
    }

    private void init_widgets() {

        btnContinue = (Button) findViewById(R.id.btnContinue);


        cvSelf = (CardView) findViewById(R.id.cvSelf);
        cvFamily = (CardView) findViewById(R.id.cvFamily);
        cvParents = (CardView) findViewById(R.id.cvParents);

        llSelf = (LinearLayout) findViewById(R.id.llSelf);
        llSpouse = (LinearLayout) findViewById(R.id.llSpouse);
        llKid1 = (LinearLayout) findViewById(R.id.llKid1);
        llKid2 = (LinearLayout) findViewById(R.id.llKid2);
        llKid3 = (LinearLayout) findViewById(R.id.llKid3);
        llKid4 = (LinearLayout) findViewById(R.id.llKid4);

        llFather = (LinearLayout) findViewById(R.id.llFather);
        llMother = (LinearLayout) findViewById(R.id.llMother);


        //self
        etSelfDOB = (EditText) findViewById(R.id.etSelfDOB);

        //family
        etFamilySelfDOB = (EditText) findViewById(R.id.etFamilySelfDOB);
        etFamilySpouseDOB = (EditText) findViewById(R.id.etFamilySpouseDOB);
        etFamilyKid1DOB = (EditText) findViewById(R.id.etFamilyKid1DOB);
        etFamilyKid2DOB = (EditText) findViewById(R.id.etFamilyKid2DOB);
        etFamilyKid3DOB = (EditText) findViewById(R.id.etFamilyKid3DOB);
        etFamilyKid4DOB = (EditText) findViewById(R.id.etFamilyKid4DOB);

        //parents
        etParentFatherDOB = (EditText) findViewById(R.id.etParentFatherDOB);
        etParentMotherDOB = (EditText) findViewById(R.id.etParentMotherDOB);


        //self
        rbSelfMale = (RadioButton) findViewById(R.id.rbSelfMale);

        //family
        rbFamilySelfMale = (RadioButton) findViewById(R.id.rbFamilySelfMale);
        rbFamilySpouseMale = (RadioButton) findViewById(R.id.rbFamilySpouseMale);
        rbFamilyKid1MaleDOB = (RadioButton) findViewById(R.id.rbFamilyKid1MaleDOB);
        rbFamilyKid2Male = (RadioButton) findViewById(R.id.rbFamilyKid2Male);
        rbFamilyKid3Male = (RadioButton) findViewById(R.id.rbFamilyKid3Male);
        rbFamilyKid4Male = (RadioButton) findViewById(R.id.rbFamilyKid4Male);
        rbFamilySpouseFemale = (RadioButton) findViewById(R.id.rbFamilySpouseFemale);
        rbFamilySelfFemale = (RadioButton) findViewById(R.id.rbFamilySelfFemale);


//        rbFamilySelfMale.setOnCheckedChangeListener(onCheckedChangeListener);
//        rbFamilySelfFemale.setOnCheckedChangeListener(onCheckedChangeListener);
//
//        rbFamilySpouseMale.setOnCheckedChangeListener(onCheckedChangeListener);
//        rbFamilySpouseFemale.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.rbFamilySelfMale:
                    if (!rbFamilySpouseFemale.isChecked())
                        rbFamilySpouseFemale.setChecked(true);
                    else
                        rbFamilySpouseFemale.setChecked(false);

                    break;
                case R.id.rbFamilySelfFemale:
                    if (!rbFamilySpouseMale.isChecked())
                        rbFamilySpouseMale.setChecked(true);
                    else
                        rbFamilySpouseMale.setChecked(false);
                    break;

//                case R.id.rbFamilySpouseMale:
//                    if (!rbFamilySelfFemale.isChecked())
//                        rbFamilySelfFemale.setChecked(true);
//                    break;
//                case R.id.rbFamilySpouseFemale:
//                    if (!rbFamilySelfMale.isChecked())
//                        rbFamilySelfMale.setChecked(true);
//                    break;
            }
        }
    };

    private void HideandShow(CoverModelInfo modelInfo) {

        /*
        * -cover
        * 1 self
        * 2 family
        * 3 parents
        *
        * -coverForFamily
        * 0 self
        * 1 spouse
        * 2 both
        *
        * -kids
        * no of kids
        *
        * - coverForParents
        *  0 father
        *  1 Mother
        *  2 both*/


        //self
        if (modelInfo.getCover() == 1) {
            cvSelf.setVisibility(View.VISIBLE);
            cvFamily.setVisibility(View.GONE);
            cvParents.setVisibility(View.GONE);
        } else
            //family
            if (modelInfo.getCover() == 2) {
                cvSelf.setVisibility(View.GONE);
                cvParents.setVisibility(View.GONE);
                cvFamily.setVisibility(View.VISIBLE);

                if (modelInfo.getCoverForFamily() == 0) {
                    llSelf.setVisibility(View.VISIBLE);
                    llSpouse.setVisibility(View.GONE);
                } else if (modelInfo.getCoverForFamily() == 1) {
                    llSelf.setVisibility(View.GONE);
                    llSpouse.setVisibility(View.VISIBLE);
                } else if (modelInfo.getCoverForFamily() == 2) {
                    llSelf.setVisibility(View.VISIBLE);
                    llSpouse.setVisibility(View.VISIBLE);
                }

                if (modelInfo.getNoofKids() == 0) {
                    llKid4.setVisibility(View.GONE);
                    llKid3.setVisibility(View.GONE);
                    llKid2.setVisibility(View.GONE);
                    llKid1.setVisibility(View.GONE);
                } else if (modelInfo.getNoofKids() == 1) {
                    llKid4.setVisibility(View.GONE);
                    llKid3.setVisibility(View.GONE);
                    llKid2.setVisibility(View.GONE);
                    llKid1.setVisibility(View.VISIBLE);
                } else if (modelInfo.getNoofKids() == 2) {
                    llKid4.setVisibility(View.GONE);
                    llKid3.setVisibility(View.GONE);
                    llKid2.setVisibility(View.VISIBLE);
                    llKid1.setVisibility(View.VISIBLE);
                } else if (modelInfo.getNoofKids() == 3) {
                    llKid4.setVisibility(View.GONE);
                    llKid3.setVisibility(View.VISIBLE);
                    llKid2.setVisibility(View.VISIBLE);
                    llKid1.setVisibility(View.VISIBLE);
                } else if (modelInfo.getNoofKids() == 4) {
                    llKid4.setVisibility(View.VISIBLE);
                    llKid3.setVisibility(View.VISIBLE);
                    llKid2.setVisibility(View.VISIBLE);
                    llKid1.setVisibility(View.VISIBLE);
                }

            } else
                //parents
                if (modelInfo.getCover() == 3) {
                    cvParents.setVisibility(View.VISIBLE);
                    cvSelf.setVisibility(View.GONE);
                    cvFamily.setVisibility(View.GONE);

                    if (modelInfo.getCoverForParents() == 0) {
                        llMother.setVisibility(View.GONE);
                        llFather.setVisibility(View.VISIBLE);
                    } else if (modelInfo.getCoverForParents() == 1) {
                        llMother.setVisibility(View.VISIBLE);
                        llFather.setVisibility(View.GONE);
                    } else if (modelInfo.getCoverForParents() == 2) {
                        llMother.setVisibility(View.VISIBLE);
                        llFather.setVisibility(View.VISIBLE);
                    }
                }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.etSelfDOB:
                DateTimePicker.showHealthAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etSelfDOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });
                break;

            case R.id.etFamilySelfDOB:
                DateTimePicker.showHealthAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etFamilySelfDOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });
                break;
            case R.id.etFamilySpouseDOB:
                DateTimePicker.showHealthAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etFamilySpouseDOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });
                break;
            case R.id.etParentFatherDOB:
                DateTimePicker.showHealthParentAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etParentFatherDOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });
                break;
            case R.id.etParentMotherDOB:
                DateTimePicker.showHealthParentAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etParentMotherDOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });

                break;

            case R.id.etFamilyKid1DOB:
                DateTimePicker.showHealthKidsAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etFamilyKid1DOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });
                break;
            case R.id.etFamilyKid2DOB:
                DateTimePicker.showHealthKidsAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etFamilyKid2DOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });
                break;
            case R.id.etFamilyKid3DOB:
                DateTimePicker.showHealthKidsAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etFamilyKid3DOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });
                break;
            case R.id.etFamilyKid4DOB:
                DateTimePicker.showHealthKidsAgeDatePicker(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (view.isShown()) {

                            Calendar calendar = Calendar.getInstance();
                            calendar.set(year, month, dayOfMonth);
                            String currentDay = simpleDateFormat.format(calendar.getTime());
                            etFamilyKid4DOB.setText(currentDay);
                            //startActivity(new Intent(CarDetailsActivity.this, CarDetailsActivity.class).putExtra(Constants.QUOTE, quoteRequestEntity));
                            //etDate.setTag(R.id.et_date, calendar.getTime());
                        }
                    }
                });
                break;
            case R.id.btnContinue:


                //self
                if (modelInfo.getCover() == 1) {
                    if (etSelfDOB.getText().toString().equals("")) {
                        Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    MemberListEntity entity = new MemberListEntity();
                    entity.setMemberDOB(etSelfDOB.getText().toString());
                    if (rbSelfMale.isChecked()) {
                        entity.setMemberGender("M");

                    } else {
                        entity.setMemberGender("F");
                    }
                    entity.setMemberNumber("1");
                    entity.setMemberType("Adult");
                    entity.setMemberTypeID("1");
                    listHealthMember.add(entity);

                } else
                    //family
                    if (modelInfo.getCover() == 2) {
                        if (modelInfo.getCoverForFamily() == 0) {

                            if (etFamilySelfDOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            MemberListEntity entity = new MemberListEntity();
                            entity.setMemberDOB(etFamilySelfDOB.getText().toString());
                            if (rbFamilySelfMale.isChecked()) {
                                entity.setMemberGender("M");
                            } else {
                                entity.setMemberGender("F");
                            }
                            entity.setMemberNumber("1");
                            entity.setMemberType("Adult");
                            entity.setMemberTypeID("1");
                            listHealthMember.add(entity);

                        } else if (modelInfo.getCoverForFamily() == 1) {

                            if (etFamilySpouseDOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            MemberListEntity entity = new MemberListEntity();
                            entity.setMemberDOB(etFamilySpouseDOB.getText().toString());
                            if (rbFamilySpouseMale.isChecked()) {
                                entity.setMemberGender("M");
                            } else {
                                entity.setMemberGender("F");
                            }
                            entity.setMemberNumber("2");
                            entity.setMemberType("Adult");
                            entity.setMemberTypeID("2");
                            listHealthMember.add(entity);

                        } else if (modelInfo.getCoverForFamily() == 2) {

                            if (etFamilySelfDOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (etFamilySpouseDOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            MemberListEntity entitySelf = new MemberListEntity();
                            entitySelf.setMemberDOB(etFamilySelfDOB.getText().toString());
                            if (rbFamilySelfMale.isChecked()) {
                                entitySelf.setMemberGender("M");
                            } else {
                                entitySelf.setMemberGender("F");
                            }
                            entitySelf.setMemberNumber("1");
                            entitySelf.setMemberType("Adult");
                            entitySelf.setMemberTypeID("1");
                            listHealthMember.add(entitySelf);

                            MemberListEntity entitySpouse = new MemberListEntity();
                            entitySpouse.setMemberDOB(etFamilySpouseDOB.getText().toString());
                            if (rbFamilySpouseMale.isChecked()) {
                                entitySpouse.setMemberGender("M");
                            } else {
                                entitySpouse.setMemberGender("F");
                            }
                            entitySpouse.setMemberNumber("2");
                            entitySpouse.setMemberType("Adult");
                            entitySpouse.setMemberTypeID("2");
                            listHealthMember.add(entitySpouse);
                        }

                        if (modelInfo.getNoofKids() == 1) {

                            if (etFamilyKid1DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            MemberListEntity entity = new MemberListEntity();
                            entity.setMemberDOB(etFamilyKid1DOB.getText().toString());
                            if (rbFamilyKid1MaleDOB.isChecked()) {
                                entity.setMemberGender("M");
                            } else {
                                entity.setMemberGender("F");
                            }
                            entity.setMemberNumber("5");
                            entity.setMemberType("Child");
                            entity.setMemberTypeID("3");
                            listHealthMember.add(entity);

                        } else if (modelInfo.getNoofKids() == 2) {

                            if (etFamilyKid1DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (etFamilyKid2DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            MemberListEntity entityKid1 = new MemberListEntity();
                            entityKid1.setMemberDOB(etFamilyKid1DOB.getText().toString());
                            if (rbFamilyKid1MaleDOB.isChecked()) {
                                entityKid1.setMemberGender("M");
                            } else {
                                entityKid1.setMemberGender("F");
                            }
                            entityKid1.setMemberNumber("5");
                            entityKid1.setMemberType("Child");
                            entityKid1.setMemberTypeID("3");
                            listHealthMember.add(entityKid1);

                            MemberListEntity entityKid2 = new MemberListEntity();
                            entityKid2.setMemberDOB(etFamilyKid2DOB.getText().toString());
                            if (rbFamilyKid2Male.isChecked()) {
                                entityKid2.setMemberGender("M");
                            } else {
                                entityKid2.setMemberGender("F");
                            }
                            entityKid2.setMemberNumber("5");
                            entityKid2.setMemberType("Child");
                            entityKid2.setMemberTypeID("3");
                            listHealthMember.add(entityKid2);


                        } else if (modelInfo.getNoofKids() == 3) {

                            if (etFamilyKid1DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (etFamilyKid2DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (etFamilyKid3DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }


                            MemberListEntity entityKid1 = new MemberListEntity();
                            entityKid1.setMemberDOB(etFamilyKid1DOB.getText().toString());
                            if (rbFamilyKid1MaleDOB.isChecked()) {
                                entityKid1.setMemberGender("M");
                            } else {
                                entityKid1.setMemberGender("F");
                            }
                            entityKid1.setMemberNumber("5");
                            entityKid1.setMemberType("Child");
                            entityKid1.setMemberTypeID("3");
                            listHealthMember.add(entityKid1);

                            MemberListEntity entityKid2 = new MemberListEntity();
                            entityKid2.setMemberDOB(etFamilyKid2DOB.getText().toString());
                            if (rbFamilyKid2Male.isChecked()) {
                                entityKid2.setMemberGender("M");
                            } else {
                                entityKid2.setMemberGender("F");
                            }
                            entityKid2.setMemberNumber("5");
                            entityKid2.setMemberType("Child");
                            entityKid2.setMemberTypeID("3");
                            listHealthMember.add(entityKid2);

                            MemberListEntity entityKid3 = new MemberListEntity();
                            entityKid3.setMemberDOB(etFamilyKid3DOB.getText().toString());
                            if (rbFamilyKid2Male.isChecked()) {
                                entityKid3.setMemberGender("M");
                            } else {
                                entityKid3.setMemberGender("F");
                            }
                            entityKid3.setMemberNumber("5");
                            entityKid3.setMemberType("Child");
                            entityKid3.setMemberTypeID("3");
                            listHealthMember.add(entityKid3);


                        } else if (modelInfo.getNoofKids() == 4) {

                            if (etFamilyKid1DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (etFamilyKid2DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (etFamilyKid3DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            if (etFamilyKid4DOB.getText().toString().equals("")) {
                                Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            MemberListEntity entityKid1 = new MemberListEntity();
                            entityKid1.setMemberDOB(etFamilyKid1DOB.getText().toString());
                            if (rbFamilyKid1MaleDOB.isChecked()) {
                                entityKid1.setMemberGender("M");
                            } else {
                                entityKid1.setMemberGender("F");
                            }
                            entityKid1.setMemberNumber("5");
                            entityKid1.setMemberType("Child");
                            entityKid1.setMemberTypeID("3");
                            listHealthMember.add(entityKid1);

                            MemberListEntity entityKid2 = new MemberListEntity();
                            entityKid2.setMemberDOB(etFamilyKid2DOB.getText().toString());
                            if (rbFamilyKid2Male.isChecked()) {
                                entityKid2.setMemberGender("M");
                            } else {
                                entityKid2.setMemberGender("F");
                            }
                            entityKid2.setMemberNumber("5");
                            entityKid2.setMemberType("Child");
                            entityKid2.setMemberTypeID("3");
                            listHealthMember.add(entityKid2);

                            MemberListEntity entityKid3 = new MemberListEntity();
                            entityKid3.setMemberDOB(etFamilyKid3DOB.getText().toString());
                            if (rbFamilyKid2Male.isChecked()) {
                                entityKid3.setMemberGender("M");
                            } else {
                                entityKid3.setMemberGender("F");
                            }
                            entityKid3.setMemberNumber("5");
                            entityKid3.setMemberType("Child");
                            entityKid3.setMemberTypeID("3");
                            listHealthMember.add(entityKid3);

                            MemberListEntity entityKid4 = new MemberListEntity();
                            entityKid4.setMemberDOB(etFamilyKid4DOB.getText().toString());
                            if (rbFamilyKid4Male.isChecked()) {
                                entityKid4.setMemberGender("M");
                            } else {
                                entityKid4.setMemberGender("F");
                            }
                            entityKid4.setMemberNumber("5");
                            entityKid4.setMemberType("Child");
                            entityKid4.setMemberTypeID("3");
                            listHealthMember.add(entityKid3);
                        }

                    } else
                        //parents
                        if (modelInfo.getCover() == 3) {


                            if (modelInfo.getCoverForParents() == 0) {

                                if (etParentFatherDOB.getText().toString().equals("")) {
                                    Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                MemberListEntity entityFather = new MemberListEntity();
                                entityFather.setMemberDOB(etParentFatherDOB.getText().toString());
                                entityFather.setMemberGender("M");
                                entityFather.setMemberNumber("3");
                                entityFather.setMemberType("Adult");
                                entityFather.setMemberTypeID("5");
                                listHealthMember.add(entityFather);

                            } else if (modelInfo.getCoverForParents() == 1) {

                                if (etParentMotherDOB.getText().toString().equals("")) {
                                    Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                MemberListEntity entityMother = new MemberListEntity();
                                entityMother.setMemberDOB(etParentMotherDOB.getText().toString());
                                entityMother.setMemberGender("F");
                                entityMother.setMemberNumber("4");
                                entityMother.setMemberType("Adult");
                                entityMother.setMemberTypeID("4");
                                listHealthMember.add(entityMother);
                            } else if (modelInfo.getCoverForParents() == 2) {

                                if (etParentFatherDOB.getText().toString().equals("")) {
                                    Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if (etParentMotherDOB.getText().toString().equals("")) {
                                    Toast.makeText(this, "Invalid date", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                MemberListEntity entityFather = new MemberListEntity();
                                entityFather.setMemberDOB(etParentFatherDOB.getText().toString());
                                entityFather.setMemberGender("M");
                                entityFather.setMemberNumber("3");
                                entityFather.setMemberType("Adult");
                                entityFather.setMemberTypeID("5");
                                listHealthMember.add(entityFather);

                                MemberListEntity entityMother = new MemberListEntity();
                                entityMother.setMemberDOB(etParentMotherDOB.getText().toString());
                                entityMother.setMemberGender("F");
                                entityMother.setMemberNumber("4");
                                entityMother.setMemberType("Adult");
                                entityMother.setMemberTypeID("4");
                                listHealthMember.add(entityMother);

                            }
                        }

                healthRequestEntity.setMemberList(listHealthMember);
                //healthRequestEntity.setSupportsAgentID(new LoginFacade(this).getUser().getEmp_Id());

                startActivity(new Intent(this, CustomerDetailsActivity.class)
                        .putExtra(HEALTH_QUOTE, healthRequestEntity));


                //listHealthMember.clear();
                break;
        }
    }

    @Override
    protected void onPause() {

        super.onPause();
        //  healthRequestEntity = null;
    }

    /*@Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof HealthQuoteResponse) {

            if (((HealthQuoteResponse) response).getHealthQuotes() != null) {
                startActivity(new Intent(this, HealthQuotes.class)
                        .putExtra(HEALTH_QUOTE, (HealthQuoteResponse) response));
            }
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }*/
}
