package com.example.notemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPass;
    private Button btnSignUp;
    private Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPass = findViewById(R.id.etConfirmPass);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    //check format email
    private boolean checkFormatEmail(String email){
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Boolean b = email.matches(EMAIL_REGEX);
        if (b ==false){
            return false;
        }
        return true;
    }

    //check input
    private boolean checkInput(){
        if (TextUtils.isEmpty(etEmail.getText().toString())){
            etEmail.setError(getResources().getString(R.string.require));
            return  false;
        }

        if (!checkFormatEmail(etEmail.getText().toString())){
            etEmail.setError(getResources().getString(R.string.format_email));
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError(getResources().getString(R.string.require));
            return false;
        }
        if (TextUtils.isEmpty(etConfirmPass.getText().toString())){
            etConfirmPass.setError(getResources().getString(R.string.require));
            return  false;
        }

        if (!TextUtils.equals(etPassword.getText().toString(), etConfirmPass.getText().toString())){
            etConfirmPass.setError(getResources().getString(R.string.pass_are_not_match));
            etPassword.setError("require");
            return false;
        }


        return true;
    }

    //
    private void signInForm(){
        Intent intent = new Intent(this, SignInActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString("signUpEmail", etEmail.getText().toString());
        intent.putExtra("EmailToSignIn",bundle);

        startActivity(intent);
        finish();

    }

    //
    private void signUp(){
        if (checkInput() == false)
            return;

        //Lưu thông tin vào Database
        //...
        Toast.makeText(this, getResources().getString(R.string.sign_up_complete), Toast.LENGTH_LONG).show();
        signInForm();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignUp:{
                signUp();
                break;
            }
            case R.id.btnSignIn:{
                signInForm();
                break;
            }
        }

    }
}