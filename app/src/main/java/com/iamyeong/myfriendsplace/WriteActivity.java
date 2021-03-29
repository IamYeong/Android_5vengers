package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {

    private EditText editText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        editText = findViewById(R.id.et_write);
        btn = findViewById(R.id.btn_write);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WriteActivity.this, "Button click!", Toast.LENGTH_SHORT).show();

                String content = editText.getText().toString();
                //Data save

                finish();
            }
        });

    }
}