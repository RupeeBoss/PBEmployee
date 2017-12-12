package com.android.policyboss.createlead;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.createlead.CreateLeadController;
import com.android.policyboss.core.models.LeadDetailRequest;
import com.android.policyboss.core.response.LeadCreateResponse;
import com.android.policyboss.utility.DateTimePicker;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Rajeev Ranjan on 12/06/2017.
 */

public class CreateLeadFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriber {
    final int INSURANCE = 1001;
    final int DL = 1002;
    final int RC = 1003;
    EditText etPolicyExpire, etName, etMobile, etEmail;
    protected View.OnClickListener datePickerDialog = new View.OnClickListener() {

        private EditText editText;

        @Override
        public void onClick(View view) {

            if (view instanceof EditText) {
                editText = (EditText) view;
            }


            DateTimePicker.showDataPickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    if (editText != null) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(year, monthOfYear, dayOfMonth);

                        if (editText.getId() == etPolicyExpire.getId()) {

                            try {
                                java.util.Date _startdate = new java.util.Date(calendar.getTimeInMillis());
                                etPolicyExpire.setText(new SimpleDateFormat("yyyy-MM-dd").format(_startdate));

                            } catch (Exception io) {
                                io.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    };
    TextView txtDrivingLicence, txtInsurance, txtRCbook;
    Spinner spinnerCollection;
    Button btnSubmit;
    LeadDetailRequest leadDetailRequest;
    EditText etState, etRTO, etSeries, etVehicleNumber;
    int EmployeeID;
    SharedPreferences sharedPreferences;
    ProgressDialog dialog;
    RadioButton rbPrivateCar, rb3WheelerCar, rbLCV, rb2Wheeler;


    public CreateLeadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_lead, container, false);
        init(view);
        setlistner();
        leadDetailRequest = new LeadDetailRequest();
        //sharedPreferences = Constants.initializeSharedpreference(this);
        //EmployeeID = sharedPreferences.getInt(Constants.USERID, 0);
        return view;
    }

    private void setlistner() {
        etPolicyExpire.setOnClickListener(datePickerDialog);
        txtDrivingLicence.setOnClickListener(this);
        txtInsurance.setOnClickListener(this);
        txtRCbook.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    private void init(View view) {

        rbPrivateCar = (RadioButton) view.findViewById(R.id.rbPrivateCar);
        rb3WheelerCar = (RadioButton) view.findViewById(R.id.rb3WheelerCar);
        rbLCV = (RadioButton) view.findViewById(R.id.rbLCV);
        rb2Wheeler = (RadioButton) view.findViewById(R.id.rb2Wheeler);

        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etName = (EditText) view.findViewById(R.id.etName);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etPolicyExpire = (EditText) view.findViewById(R.id.etPolicyExpire);
        etState = (EditText) view.findViewById(R.id.etState);
        etRTO = (EditText) view.findViewById(R.id.etRTO);
        etSeries = (EditText) view.findViewById(R.id.etSeries);
        etVehicleNumber = (EditText) view.findViewById(R.id.etVehicleNumber);

        txtDrivingLicence = (TextView) view.findViewById(R.id.txtDrivingLicence);
        txtInsurance = (TextView) view.findViewById(R.id.txtInsurance);
        txtRCbook = (TextView) view.findViewById(R.id.txtRCbook);

        //spinnerCollection = (Spinner) findViewById(R.id.spinnerCollection);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.btnSubmit) {

//            if (etState.getText().toString() == null
//                    && etRTO.getText().toString() == null
//                    && etSeries.getText().toString() == null
//                    && etVehicleNumber.getText().toString() == null) {
//                Toast.makeText(this, "Invalid Vehicle number", Toast.LENGTH_SHORT).show();
//
//                return;
//            }
//
//
//            if (etName.getText().toString() == null) {
//                Toast.makeText(this, "Invalid name", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//           /* if (validateEmail(etEmail)) {
//
//                Toast.makeText(this, "Invalid Email address", Toast.LENGTH_SHORT).show();
//                return;
//            }*/
//
//            if (etMobile.getText().toString().length() != 10) {
//                Toast.makeText(this, "Invalid mobile number", Toast.LENGTH_SHORT).show();
//
//                return;
//            }
//
//            if (etPolicyExpire.getText().toString().length() == 0) {
//                Toast.makeText(this, "Select Policy expiry date", Toast.LENGTH_SHORT).show();
//
//                return;
//            }
//            if (leadDetailRequest.getPolicyUploadImage() == null) {
//                Toast.makeText(this, "Insurance copy not attached.", Toast.LENGTH_SHORT).show();
//
//                return;
//            }

            leadDetailRequest.setLeadID(0);
            leadDetailRequest.setCampaignName("");
            leadDetailRequest.setPushLeadID(0);
            leadDetailRequest.setCustomerName(etName.getText().toString());
            leadDetailRequest.setCustomerEmail(etEmail.getText().toString());
            leadDetailRequest.setEmployeeID(EmployeeID);
            leadDetailRequest.setCustomerMobile(etMobile.getText().toString());
            leadDetailRequest.setPolicyExpiryDate(etPolicyExpire.getText().toString());
            leadDetailRequest.setVehicleRegistrationNumber(etState.getText().toString()
                    + " " + etRTO.getText().toString()
                    + " " + etSeries.getText().toString()
                    + " " + etVehicleNumber.getText().toString());

            if (rbPrivateCar.isChecked()) {
                leadDetailRequest.setProductID(1);
            } else if (rb3WheelerCar.isChecked()) {
                leadDetailRequest.setProductID(11);
            } else if (rbLCV.isChecked()) {
                leadDetailRequest.setProductID(12);
            } else if (rb2Wheeler.isChecked()) {
                leadDetailRequest.setProductID(10);
            }

           /* if (spinnerCollection.getSelectedItem().toString().equals("Car")) {
                leadDetailRequest.setProductID(1);

            } else if (spinnerCollection.getSelectedItem().toString().equals("3 wheeler lead collection")) {
                leadDetailRequest.setProductID(11);
            } else if (spinnerCollection.getSelectedItem().toString().equals("LCV lead collection")) {
                leadDetailRequest.setProductID(12);
            } else if (spinnerCollection.getSelectedItem().toString().equals("2 wheeler lead collection")) {
                leadDetailRequest.setProductID(10);
            }
*/
          /*  if (leadDetailRequest.getRCUploadImage() != null) {
                if (leadDetailRequest.getRCUploadImage().toString().length() == 0) {
                    leadDetailRequest.setRCUploadImage("");
                }
            } else {
                leadDetailRequest.setRCUploadImage("");
            }
            if (leadDetailRequest.getDrivingLicenceUploadImage() != null) {
                if (leadDetailRequest.getDrivingLicenceUploadImage().toString().length() == 0) {
                    leadDetailRequest.setDrivingLicenceUploadImage("");
                }
            } else {
                leadDetailRequest.setDrivingLicenceUploadImage("");
            }*/

            leadDetailRequest.setVehiclePhotoUploadImage("");
            showDialog();
            new CreateLeadController(getActivity()).getLead(leadDetailRequest, this);

        }

        if (v.getId() == R.id.txtInsurance) {
            openCamera(INSURANCE);
        }
        if (v.getId() == R.id.txtDrivingLicence) {
            openCamera(DL);
        }
        if (v.getId() == R.id.txtRCbook) {
            openCamera(RC);
        }
    }

    private void openCamera(int type) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, type);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String encoded = "";
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            if (bitmap != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                encoded = Base64.encodeToString(byteArray, Base64.NO_WRAP);
            }


            if (requestCode == INSURANCE) {
                leadDetailRequest.setPolicyUploadImage(encoded);
                txtInsurance.setText("Insurance :" + data.getAction());
            } else if (requestCode == DL) {
                leadDetailRequest.setDrivingLicenceUploadImage(encoded);
                txtDrivingLicence.setText("Driving :" + data.getAction());
            } else if (requestCode == RC) {
                leadDetailRequest.setRCUploadImage(encoded);
                txtRCbook.setText("RC :" + data.getAction());
            }
        }
    }

    public boolean validateEmail(EditText editText) {
        String email = editText.getText().toString().trim();
        if (email.isEmpty() || !isValidEmail(email)) {
            return true;
        }
        return false;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {
        if (response instanceof LeadCreateResponse) {
            cancelDialog();
            if (response.getStatusNo() == 0) {
                Toast.makeText(getActivity(), response.getMessage(), Toast.LENGTH_SHORT).show();
                etName.setText(null);
                etMobile.setText(null);
                etEmail.setText(null);
                etPolicyExpire.setText(null);
                etState.setText(null);
                etSeries.setText(null);
                etVehicleNumber.setText(null);
                etRTO.setText(null);
                txtDrivingLicence.setText(null);
                txtRCbook.setText(null);
                txtInsurance.setText(null);

            }
        }

    }

    @Override
    public void OnFailure(Throwable t) {
        Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
        cancelDialog();
    }
}
