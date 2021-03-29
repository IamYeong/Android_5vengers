package com.iamyeong.myfriendsplace;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private ArrayList<MyCardView> arrayList;
    private Context context;

    public MyRecyclerViewAdapter(ArrayList<MyCardView> arrayList, Context context) {

        this.context = context;
        this.arrayList = arrayList;

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

        holder.title.setText(arrayList.get(position).getTitle());
        holder.user.setText(arrayList.get(position).getUser());

        String m = Integer.toString(position);
        System.out.println(m);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, m, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, PostActivity.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return (arrayList != null? arrayList.size() : 0);
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
