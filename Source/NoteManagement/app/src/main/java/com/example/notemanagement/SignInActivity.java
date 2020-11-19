package com.example.notemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etEmail;
    private EditText etPassword;
    private CheckBox chkRemember;
    private Button btnSignIn;
    private Button btnExit;

    private FloatingActionButton fabRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etEmail= findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        chkRemember = findViewById(R.id.chkRemember);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnExit = findViewById(R.id.btnExit);
        fabRegister = findViewById(R.id.fABtnAdd);

        btnSignIn.setOnClickListener(this);
        btnExit.setOnClickListener(this);
        fabRegister.setOnClickListener(this);

        Intent i = getIntent();
        Bundle bundle = i.getBundleExtra("EmailToSignIn");
        if (bundle !=null){
            etEmail.setText(bundle.getString("signUpEmail"));
        }
    }

    private boolean checkFormatEmail(String email){
        String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Boolean b = email.matches(EMAIL_REGEX);
        if (b ==false){
            return false;
        }
        return true;
    }


    private boolean checkinput(){
        if (TextUtils.isEmpty(etEmail.getText().toString())){
            etEmail.setError(getResources().getString(R.string.require));
            return false;
        }

        if (!checkFormatEmail(etEmail.getText().toString())){
            etEmail.setError(getResources().getString(R.string.format_email));
            return false;
        }

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(getResources().getString(R.string.require));
            return false;
        }
        //Kiểm tra với thông tin trong Database
        //...

        return true;
    }

    private void signIn(){
        if (!checkinput()){
            return;
        }
//        if (chkRemember.isChecked()){
//            //Lưu thông tin đăng nhập cho thiết bị
//            //....
//        }
        //dẫn tới Home
        //...

        Toast.makeText(this, getResources().getString(R.string.sign_in_complete), Toast.LENGTH_LONG).show();
        home();
    }
    private void register(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    private void home(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSignIn:
                signIn();

                break;
            case R.id.btnExit:
                finish();
                break;
            case R.id.fABtnAdd:
                register();
                break;
        }
    }
}