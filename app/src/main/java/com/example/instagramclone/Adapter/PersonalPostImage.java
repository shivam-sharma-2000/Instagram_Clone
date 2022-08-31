package com.example.instagramclone.Adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;

public class PersonalPostImage extends RecyclerView.Adapter<PersonalPostImage.ViewHolder> {

    ArrayList<Uri> urlArrayList;

    public PersonalPostImage(ArrayList<Uri> urlArrayList) {
        this.urlArrayList = urlArrayList;
    }

    @NonNull
    @Override
    public PersonalPostImage.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.personal_posted_images_layout,null);
        PersonalPostImage.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalPostImage.ViewHolder holder, int position) {
        Picasso.get().load(urlArrayList.get(position)).into(holder.personalPostedImage);
    }

    @Override
    public int getItemCount() {
        return urlArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView personalPostedImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personalPostedImage = itemView.findViewById(R.id.post_image);
        }
    }
}
