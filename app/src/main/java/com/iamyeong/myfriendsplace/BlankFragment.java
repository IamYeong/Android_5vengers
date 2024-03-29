package com.iamyeong.myfriendsplace;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment implements OnGetCommentListener {


    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CommentAdapter adapter;
    private LinearLayoutManager layoutManager;
    private Button button;
    private EditText editText;
    private FirestoreManager firestoreManager;
    private ArrayList<Comment> commentArrayList;
    private String documentId;

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
        documentId = getArguments().getString("DOCUMENT");

        firestoreManager = FirestoreManager.getInstance(getActivity());
        UserModel user = UserModel.getInstance();

        button = view.findViewById(R.id.btn_comment);
        editText = view.findViewById(R.id.et_comment);
        swipeRefreshLayout = view.findViewById(R.id.swipe_comment);

        firestoreManager.getComments(BlankFragment.this, documentId);

        commentArrayList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rv_comment);
        adapter = new CommentAdapter(commentArrayList, getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                firestoreManager.getComments(BlankFragment.this, documentId);
                swipeRefreshLayout.setRefreshing(false);

                Toast.makeText(getActivity(), "Refresh!", Toast.LENGTH_SHORT).show();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editText.getText().toString();
                long userId = user.getUserId();
                Comment commentObject = new Comment(userId, comment);

                firestoreManager.addComment(documentId, commentObject, BlankFragment.this);
                editText.setText("");

                Toast.makeText(getActivity(), "완료", Toast.LENGTH_SHORT).show();

            }
        });



        return view;
    }

    private void commentNotify(String documentId) {

        firestoreManager.getComments(BlankFragment.this, documentId);

    }

    @Override
    public void OnGetComment(ArrayList<Comment> commentList) {

        Collections.sort(commentList);

        adapter.updateCommentList(commentList);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void OnAddedComment() {

        firestoreManager.getComments(BlankFragment.this, documentId);

        Toast.makeText(getActivity(), "Comment add complete!", Toast.LENGTH_SHORT).show();

    }


}