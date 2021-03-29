package com.iamyeong.myfriendsplace.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iamyeong.myfriendsplace.MyCardView;
import com.iamyeong.myfriendsplace.MyRecyclerViewAdapter;
import com.iamyeong.myfriendsplace.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private MyRecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<MyCardView> arrayList = new ArrayList<>();
        arrayList.add(new MyCardView("다들 잘 보임?", "정광영"));
        arrayList.add(new MyCardView("5월 약속일!!!", "여인승"));
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = root.findViewById(R.id.rv_home);
        adapter = new MyRecyclerViewAdapter(arrayList, getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        return root;
    }


    private void initializeRecyclerView(View root) {
    }
}