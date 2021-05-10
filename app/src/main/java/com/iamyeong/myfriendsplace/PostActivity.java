package com.iamyeong.myfriendsplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class PostActivity extends AppCompatActivity {

    private TextView tv_post, tv_comment, tv_time, tv_title, tv_publisher;
    private Post post;
    private SimpleDateFormat formatter;
    private String name;
    private boolean isCommitFragment = false;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private BlankFragment fragment;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        post = (Post) intent.getSerializableExtra("POSTBUNDLE");
        name = intent.getStringExtra("NAME");

        long time = post.getTimes();

        tv_post = findViewById(R.id.tv_scroll_post);
        tv_comment = findViewById(R.id.tv_comment);
        tv_time = findViewById(R.id.tv_post_time);
        tv_title = findViewById(R.id.tv_title_post);
        tv_publisher = findViewById(R.id.tv_post_publisher);
        formatter = new SimpleDateFormat("yyyy-MM-dd");

        tv_time.setText(formatter.format(time));
        tv_post.setText(post.getContent());
        tv_title.setText(post.getTitle());
        tv_publisher.setText(name);

        bundle = new Bundle();
        bundle.putString("DOCUMENT", post.getPostId());

        tv_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PostActivity.this, "Comment Fragment createView", Toast.LENGTH_SHORT).show();

                fragment = new BlankFragment();

                if (fragment.isAdded()) {
                    ft.remove(fragment);
                }

                fm = PostActivity.this.getSupportFragmentManager();
                ft = fm.beginTransaction();

                fragment.setArguments(bundle);
                ft.add(R.id.constraint_post_activity, fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });


    }

}