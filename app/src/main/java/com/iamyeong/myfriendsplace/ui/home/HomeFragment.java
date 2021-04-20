package com.iamyeong.myfriendsplace.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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
import com.iamyeong.myfriendsplace.FirestoreManager;

import com.iamyeong.myfriendsplace.MyRecyclerViewAdapter;

import com.iamyeong.myfriendsplace.Post;
import com.iamyeong.myfriendsplace.R;
import com.iamyeong.myfriendsplace.WriteActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FloatingActionButton fab;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference document;
    private FirestoreManager firestoreManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Post> postList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        swipeRefreshLayout = root.findViewById(R.id.swipe_home);

        firestoreManager = FirestoreManager.getInstance(getActivity());
        postList = firestoreManager.getPosts();

        fab = root.findViewById(R.id.fab_home);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = root.findViewById(R.id.rv_home);
        adapter = new MyRecyclerViewAdapter(postList, getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapterNotification();

                swipeRefreshLayout.setRefreshing(false);

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

        adapterNotification();
        Log.d("HomeFragment : ", "onResume");
    }

    private void adapterNotification() {

        postList.clear();
        postList = firestoreManager.getPosts();
        adapter.notifyDataSetChanged();

    }


}