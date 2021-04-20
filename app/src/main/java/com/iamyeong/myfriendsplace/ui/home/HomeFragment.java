package com.iamyeong.myfriendsplace.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.v1.Write;
import com.iamyeong.myfriendsplace.FirestoreManager;
import com.iamyeong.myfriendsplace.MyCardView;
import com.iamyeong.myfriendsplace.MyRecyclerViewAdapter;

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

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        firestoreManager = FirestoreManager.getInstance(getActivity());
        firestoreManager.getPosts();

        fab = root.findViewById(R.id.fab_home);

        ArrayList<MyCardView> arrayList = new ArrayList<>();
        arrayList.add(new MyCardView("다들 잘 보임?", "정광영"));
        arrayList.add(new MyCardView("5월 약속일!!!", "여인승"));


        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = root.findViewById(R.id.rv_home);
        adapter = new MyRecyclerViewAdapter(arrayList, getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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



}