package com.android.policyboss.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.policyboss.BaseActivity;
import com.android.policyboss.R;
import com.android.policyboss.core.APIResponse;
import com.android.policyboss.core.IResponseSubcriber;
import com.android.policyboss.core.controller.authentication.AuthenticationController;
import com.android.policyboss.core.response.LoginResponse;
import com.android.policyboss.navigationview.HomeActivity;
import com.android.policyboss.splashscreen.SplashScreenActivity;

public class LoginActivity extends BaseActivity implements View.OnClickListener, IResponseSubcriber {

    EditText etEmployeeCode, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_employee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initwidgets();
    }

    private void initwidgets() {
        etEmployeeCode = (EditText) findViewById(R.id.etEmployeeCode);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnLogin) {
           /* if (etEmployeeCode.getText().toString().equals("")) {
                etEmployeeCode.setError("Invalid input");
                etEmployeeCode.setFocusable(true);
                return;
                //Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }

            if (etPassword.getText().toString().equals("")) {
                etPassword.setError("Invalid input");
                etPassword.setFocusable(true);
                return;
                //Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }

            if (etPassword.getText().toString().length() < 6) {
                etPassword.setError("Minimum 6 character required");
                etPassword.setFocusable(true);
                return;
                //Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
            }*/

            showDialog();
            new AuthenticationController(this).login(etEmployeeCode.getText().toString(),
                    etPassword.getText().toString(), this);

        }
    }

    @Override
    public void OnSuccess(APIResponse response, String message) {

        cancelDialog();
        if (response instanceof LoginResponse) {
            if (response.getStatusNo() == 0) {
                finish();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            } else {
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void OnFailure(Throwable t) {
        cancelDialog();
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();

    }
}
