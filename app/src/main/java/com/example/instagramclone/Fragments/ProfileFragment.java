package com.example.instagramclone.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.Adapter.PersonalPostImage;
import com.example.instagramclone.EditProfileActivity;
import com.example.instagramclone.Model.Post;
import com.example.instagramclone.R;
import com.example.instagramclone.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private FirebaseAuth fA = FirebaseAuth.getInstance();
    private FirebaseDatabase fD = FirebaseDatabase.getInstance();
    private DatabaseReference dR = fD.getReference();

    Button switchToEditProfile;
    ImageView profilePic;
    TextView nameTV,bioTV,usernameTV,followers,following;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        switchToEditProfile = view.findViewById(R.id.switch_to_edit_profile);
        nameTV = view.findViewById(R.id.name);
        bioTV = view.findViewById(R.id.bio);
        usernameTV = view.findViewById(R.id.username);
        profilePic = view.findViewById(R.id.profile_pic);
        followers = view.findViewById(R.id.no_of_followers);
        following = view.findViewById(R.id.no_of_following);



        // Getting user information from firebase storage
        getUserInformation();

        switchToEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToEditProfileActivity();
            }
        });

        dR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DataSnapshot usersRef = snapshot.child("users").child(fA.getCurrentUser().getUid());
                if (snapshot.exists())
                {

                    User user = usersRef.getValue(User.class);
                    String name,username, bio;
                    name = user.getFullName();
                    username = user.getUsername();
                    bio = user.getBio();

                    nameTV.setText(name);
                    usernameTV.setText(username);
                    bioTV.setText(bio);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void switchToEditProfileActivity() {
        Intent intent = new Intent(getActivity(), EditProfileActivity.class);
        startActivity(intent);
    }

    private void getUserInformation() {

        dR.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    DataSnapshot usersRef = task.getResult().child("users").child(fA.getCurrentUser().getUid());
                    DataSnapshot followRef = task.getResult().child("Follow").child(fA.getCurrentUser().getUid());

//                    Log.d("firebase", String.valueOf(task.getResult().child("users").child(fA.getCurrentUser().getUid())));

                    User user = usersRef.getValue(User.class);
                    String name,username, bio, imageUri = null;

                    String noOfFollowers= String.valueOf(followRef.child("Followers").getChildrenCount());
                    String noOfFollowing= String.valueOf(followRef.child("Following").getChildrenCount());

                    name = user.getFullName();
                    username = user.getUsername();
                    bio = user.getBio();
                    if(user.getImageUri().isEmpty())
                    {
                        profilePic.setImageResource(R.drawable.ic_baseline_person_24);
                    }
                    else
                    {
                        imageUri = user.getImageUri();
//                        Picasso.get().load(imageUri).placeholder(R.drawable.ic_baseline_person_24).into(profilePic);
                        profilePic.setImageURI(Uri.parse(imageUri));
                    }

                    if(!noOfFollowers.isEmpty())
                    {
                        followers.setText(noOfFollowers);
                    }
                    if(!noOfFollowing.isEmpty())
                    {
                        following.setText(noOfFollowing);
                    }

                    nameTV.setText(name);
                    usernameTV.setText(username);
                    bioTV.setText(bio);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


}