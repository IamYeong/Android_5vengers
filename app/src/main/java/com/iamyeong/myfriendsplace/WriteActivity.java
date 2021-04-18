package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity {

    private EditText et_title, et_content;
    private Button btn;

    private UserModel user;
    private FirestoreManager firestoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        firestoreManager = new FirestoreManager(WriteActivity.this);

        et_title = findViewById(R.id.et_title);
        et_content = findViewById(R.id.et_content);
        btn = findViewById(R.id.btn_write);
        user = UserModel.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WriteActivity.this, "Button click!", Toast.LENGTH_SHORT).show();

                String title = et_title.getText().toString();
                String content = et_content.getText().toString();
                Long userId = user.getUserId();

                System.out.println(title + ", " + userId + ", " + content);

                Post post = new Post(userId, title, content);

                firestoreManager.addPost(post);

                finish();
            }
        });

    }



}