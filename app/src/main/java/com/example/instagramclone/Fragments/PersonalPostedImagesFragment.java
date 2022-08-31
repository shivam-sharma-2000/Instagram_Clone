package com.example.instagramclone.Fragments;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.instagramclone.Adapter.PersonalPostImage;
import com.example.instagramclone.Model.Post;
import com.example.instagramclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PersonalPostedImagesFragment extends Fragment {

    private ArrayList<Uri> uriArrayList;
    private PersonalPostImage personalPostImage;
    private RecyclerView imageGridView;
    private String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
    private FirebaseDatabase fD = FirebaseDatabase.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal_posted_images, container, false);

        imageGridView = view.findViewById(R.id.recyclerview_personal_post);
        imageGridView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        uriArrayList = new ArrayList<>();
        personalPostImage = new PersonalPostImage(uriArrayList);// Adapter
        imageGridView.setAdapter(personalPostImage);
        DatabaseReference postRef = fD.getReference().child("Posts");
        postRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uriArrayList.clear();
                for(DataSnapshot postData: snapshot.getChildren())
                {
                    Post post = postData.getValue(Post.class);
                    Log.d("data", post.getCaption().toString());

                    if(postData != null && post.getPublisher().equals(currentUser))
                    {
                        uriArrayList.add(Uri.parse(post.getPostImage()));
                    }
                }
                personalPostImage.notifyDataSetChanged();
//                loadingDialog.stopLoadingDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return view;
    }
}