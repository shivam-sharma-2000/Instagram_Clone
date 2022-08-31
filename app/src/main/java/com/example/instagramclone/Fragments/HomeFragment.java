package com.example.instagramclone.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.Adapter.PostAdapter;
import com.example.instagramclone.Model.Post;
import com.example.instagramclone.Model.User;
import com.example.instagramclone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FirebaseDatabase fD= FirebaseDatabase.getInstance();
    private DatabaseReference Ref = fD.getReference();
    ArrayList<Post> postList;
    PostAdapter postAdapter;
    RecyclerView recyclerViewHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewHome = view.findViewById(R.id.recyclerview_home);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        postList = new ArrayList<>();
        postAdapter = new PostAdapter(postList);
        recyclerViewHome.setAdapter(postAdapter);

        DatabaseReference postRef = fD.getReference().child("Posts");
        postRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postList.clear();
                for(DataSnapshot postData: snapshot.getChildren())
                {
                    Post post = postData.getValue(Post.class);
                    Log.d("data", post.getCaption().toString());

                    if(postData != null)
                    {
                        postList.add(post);
                    }
                }
                postAdapter.notifyDataSetChanged();
//                loadingDialog.stopLoadingDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}