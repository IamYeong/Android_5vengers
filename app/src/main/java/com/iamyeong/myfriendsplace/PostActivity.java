package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    private TextView tv_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        tv_post = findViewById(R.id.tv_scroll_post);
        tv_post.setText(getResources().getString(R.string.post_example));



        tv_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostActivity.this, "Comment Fragment createView", Toast.LENGTH_SHORT).show();
            }
        });


    }
}