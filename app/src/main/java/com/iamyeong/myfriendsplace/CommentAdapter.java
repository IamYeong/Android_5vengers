package com.iamyeong.myfriendsplace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommentAdapter extends RecyclerView.Adapter<CommentViewHolder> {

    private ArrayList<Comment> arrayList;
    private Context context;
    private UserManager userManager;
    private Map<Long, String> userMap;
    private SimpleDateFormat formatter;

    public CommentAdapter(ArrayList<Comment> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        userManager = new UserManager(context);
        userMap = new HashMap<>();
        formatter = new SimpleDateFormat("yyyy-MM-dd");

    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.comment, parent, false);
        CommentViewHolder viewHolder = new CommentViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        Comment comment = arrayList.get(position);

        long userId = comment.getUserKakaoId();
        long time = comment.getTimes();
        String content = comment.getComment();

        String userName = getUserName(userId);

        holder.time.setText(formatter.format(time));
        holder.name.setText(userName);
        holder.comment.setText(content);

        System.out.println("^^^^^^^^^^^^^^^" + position + ", " + "comment position!");

    }

    @Override
    public int getItemCount() {
        return (arrayList != null? arrayList.size() : 0);
    }

    private boolean checkUserData(long userId) {

        System.out.println("checkUserdata fun start");

        if (userMap.containsKey(userId)) {
            return true;
        }

        return false;

    }

    private String getUserName(long userId) {

        if (checkUserData(userId)) {

            return userMap.get(userId);

        }

        String name = userManager.toUserName(userId);

        userMap.put(userId, name);

        System.out.println(userMap);

        return name;

    }

}

class CommentViewHolder extends RecyclerView.ViewHolder {

    public TextView name, comment;
    public TextView time;

    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.tv_user_name_comment);
        comment = itemView.findViewById(R.id.tv_comment_content);
        time = itemView.findViewById(R.id.tv_time_comment);

    }
}