package com.example.asuss.instaclonefirebase;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostClass extends ArrayAdapter<String> {

    private final ArrayList<String> userEmailList;
    private final ArrayList<String> userCommentList;
    private final ArrayList<String> userImageUrl;
    private final Activity context;

    public PostClass(ArrayList<String> userEmailList,ArrayList<String> userCommentList,ArrayList<String> userImageUrl,Activity context){

        super(context,R.layout.custom_view,userEmailList);

        this.userEmailList = userEmailList;
        this.userCommentList = userCommentList;
        this.userImageUrl = userImageUrl;
        this.context = context;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();

        View customView = layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView userEmailText = customView.findViewById(R.id.userEmailCustomView);
        TextView userCommentText = customView.findViewById(R.id.userCommentCustomView2);
        ImageView imageText = customView.findViewById(R.id.imageViewCustomView);

        userEmailText.setText(userEmailList.get(position));
        userCommentText.setText(userCommentList.get(position));
        Picasso.get().load(userImageUrl.get(position)).into(imageText);
        return customView;
    }
}
