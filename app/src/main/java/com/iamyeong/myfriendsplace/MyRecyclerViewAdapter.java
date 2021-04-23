package com.iamyeong.myfriendsplace;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<Post> arrayList;
    private Context context;
    private UserManager userManager;
    private Map<Long, String> userMap;

    public MyRecyclerViewAdapter(ArrayList<Post> arrayList, Context context) {

        this.context = context;
        this.arrayList = arrayList;
        this.userManager = new UserManager(context);
        this.userMap = new HashMap<>();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardview_model, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Post post = arrayList.get(position);
        long userId = post.getPublisher();

        holder.title.setText(post.getTitle());

        checkUserData(userId);

        String dd = post.getPublisher().toString();
        holder.user.setText(dd);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PostActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("POSTBUNDLE", post);
                intent.putExtras(bundle);

                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return (arrayList != null? arrayList.size() : 0);
    }

    private boolean checkUserData(long userId) {

        if (userMap.containsKey(userId)) {
            return true;
        }

        return false;

    }

    private String getUserName(long userId) {


        return "";

    }

}

class MyViewHolder extends RecyclerView.ViewHolder {

    public CardView cardView;
    public TextView title, user;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        cardView = itemView.findViewById(R.id.card_model);
        title = itemView.findViewById(R.id.tv_title_card);
        user = itemView.findViewById(R.id.tv_user_name_card);


    }
}
