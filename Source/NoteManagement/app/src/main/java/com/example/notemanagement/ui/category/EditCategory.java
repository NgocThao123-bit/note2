package com.example.notemanagement.ui.category;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.notemanagement.R;

public class EditCategory extends AppCompatActivity {

    public static final String EXTRA_REPLY =
            "com.example.android.NoteManagement.REPLY";
    private EditText etCate;
    private Button btnCancel, btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_category);
        etCate = findViewById(R.id.etCategory);
        btnCancel= findViewById(R.id.btnCancel);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (TextUtils.isEmpty(etCate.getText())) {
                    setResult(RESULT_CANCELED, intent);
                } else {
                    String word = etCate.getText().toString();
                    intent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}