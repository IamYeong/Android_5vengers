package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity {

    private EditText et_title, et_content;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        btn = findViewById(R.id.btn_write);
        UserModel user = UserModel.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WriteActivity.this, "Button click!", Toast.LENGTH_SHORT).show();

                String title = et_title.getText().toString();
                String content = et_content.getText().toString();
                String userName = user.getKnickName();

                System.out.println(title + ", " + content + ", " + userName);



                finish();
            }
        });

    }
}