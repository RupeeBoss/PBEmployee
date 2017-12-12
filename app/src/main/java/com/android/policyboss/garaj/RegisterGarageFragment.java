package com.android.policyboss.garaj;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.policyboss.BaseFragment;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.garage.RegisterGarageController;
import com.android.policyboss.core.requestEntity.RegisterGarajRequestEntity;
import com.android.policyboss.core.response.GarajRegisterResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterGarageFragment extends BaseFragment implements View.OnClickListener, IResponseSubcriber {

    EditText etName, etAddress, etCity, etLandline, etContactPerson, etMobile, etEmail, etCustomerBase, etRemarks;
    Button btnRegister;
    Spinner spSegment;

    public RegisterGarageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_garaj, container, false);
        initialise(view);

        return view;
    }

    private void initialise(View view) {
        etName = (EditText) view.findViewById(R.id.etName);
        etAddress = (EditText) view.findViewById(R.id.etAddress);
        etCity = (EditText) view.findViewById(R.id.etCity);
        etLandline = (EditText) view.findViewById(R.id.etLandline);
        etContactPerson = (EditText) view.findViewById(R.id.etContactPerson);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etCustomerBase = (EditText) view.findViewById(R.id.etCustomerBase);
        etRemarks = (EditText) view.findViewById(R.id.etRemarks);
        spSegment = (Spinner) view.findViewById(R.id.spSegment);

        btnRegister = (Button) view.findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {

            RegisterGarajRequestEntity entity = new RegisterGarajRequestEntity();
            entity.setName(etName.getText().toString());
            entity.setAddress(etAddress.getText().toString());
            entity.setCity(etCity.getText().toString());
            entity.setTelNo(etLandline.getText().toString());
            entity.setContactPerson(etContactPerson.getText().toString());
            entity.setMobNo(etMobile.getText().toString());
            entity.setEmailId(etEmail.getText().toString());
            entity.setExistingCustomerBase(etCustomerBase.getText().toString());
            entity.setRemarks(etRemarks.getText().toString());
            entity.setSegmentId(spSegment.getSelectedItemPosition() + 1);
            //Change when login is active
            entity.setEmpId(40000521);
            showDialog();
            new RegisterGarageController(getContext()).registerGarage(entity, this);

        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();

        if (response instanceof GarajRegisterResponse) {
            Toast.makeText(getActivity(), "" + response.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(getActivity(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
