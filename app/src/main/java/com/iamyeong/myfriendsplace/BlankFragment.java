package com.iamyeong.myfriendsplace;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Button button;
    private EditText editText;
    private FirestoreManager firestoreManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_blank, container, false);

        firestoreManager = new FirestoreManager(getActivity());

        button = view.findViewById(R.id.btn_comment);
        editText = view.findViewById(R.id.et_comment);


        ArrayList<Comment> arrayList = new ArrayList<>();
        arrayList.add(new Comment((long)0, "ㅇㅇㅇㅇㅇㅇ"));
        arrayList.add(new Comment((long)0, "진수 또 못 옴?ㅋㅋ"));

        recyclerView = view.findViewById(R.id.rv_comment);
        adapter = new CommentAdapter(arrayList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editText.getText().toString();
                long userId = UserModel.getInstance().getUserId();
                Comment commentObject = new Comment(userId, comment);

                firestoreManager.addComment(commentObject);

                editText.setText("");
                adapter.notifyDataSetChanged();

                Toast.makeText(getActivity(), "완료", Toast.LENGTH_SHORT).show();

            }
        });



        return view;
    }
}