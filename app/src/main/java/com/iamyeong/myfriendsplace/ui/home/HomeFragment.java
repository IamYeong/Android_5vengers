package com.iamyeong.myfriendsplace.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.j2objc.annotations.Weak;
import com.iamyeong.myfriendsplace.Comment;
import com.iamyeong.myfriendsplace.CommentAdapter;
import com.iamyeong.myfriendsplace.FirestoreManager;

import com.iamyeong.myfriendsplace.MyRecyclerViewAdapter;

import com.iamyeong.myfriendsplace.Post;
import com.iamyeong.myfriendsplace.R;
import com.iamyeong.myfriendsplace.WriteActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;

import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton fab;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirestoreManager firestoreManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Post> postList;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout = root.findViewById(R.id.swipe_home);
        fab = root.findViewById(R.id.fab_home);

        MyThread thread = new MyThread();
        thread.start();


        if(postList != null) {
            postList.clear();
        }

        firestoreManager = FirestoreManager.getInstance(getActivity());
        postList = firestoreManager.getPosts();

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = root.findViewById(R.id.rv_home);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerViewAdapter(postList, getActivity());
        recyclerView.setAdapter(adapter);



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                adapter.notifyDataSetChanged();

                swipeRefreshLayout.setRefreshing(false);

                thread.stopThread();

                Toast.makeText(getActivity(), "Refresh complete!", Toast.LENGTH_SHORT).show();

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FAB Click!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);

            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        Log.d("HomeFragment : ", "onResume");
    }


    class MyThread extends Thread {

        boolean stopped = false;
        int i = 0;

        public void stopThread() {
            stopped = true;
        }

        @Override
        public void run() {
            super.run();

            while(!stopped) {

                i++;

                System.out.println(i);

                try {

                    sleep(1000);

                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
    }



}