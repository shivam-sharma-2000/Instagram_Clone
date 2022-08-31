package com.example.instagramclone.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.Model.User;
import com.example.instagramclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    ArrayList<User> users;
    private FirebaseDatabase fD= FirebaseDatabase.getInstance();
    private DatabaseReference dR = fD.getReference();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth fA = FirebaseAuth.getInstance();
    private FirebaseUser fU = fA.getCurrentUser();


    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item_layout,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        int pos = position;
        String uid = fU.getUid();
        holder.profilePic.setImageURI(Uri.parse(users.get(pos).getImageUri()));
        holder.fullName.setText(users.get(pos).getFullName());
        holder.username.setText(users.get(pos).getUsername());

        fD.getReference().child("Follow").child(uid).child("Following").child(users.get(pos).getUserId())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            if(!(task.getResult().getValue()==null))
                            {
                                holder.followAndUnfollow.setText(R.string.Unfollow);
                                Log.d("status","successful");
                            }
                        else {
                                holder.followAndUnfollow.setText(R.string.Follow);
                                Log.d("status", "unSuccessful");
                            }
                        }
                    }
                });

        holder.followAndUnfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = holder.followAndUnfollow.getText().toString();

                if (status.equals("Follow")) {
                    holder.followAndUnfollow.setText(R.string.Unfollow);
                    followUser(pos,uid);
                }
                else if (status.equals("Unfollow")){
                    holder.followAndUnfollow.setText(R.string.Follow);
                    unFollowUser(pos,uid);
                }
            }
        });

    }

    private void followUser(int pos, String uid) {
        fD.getReference()
                .child("Follow").child(uid)
                .child("Following").child(users.get(pos).getUserId())
                .setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("Follow").child(users.get(pos).getUserId())
                                    .child("Followers").child(uid)
                                    .setValue(true);
                        }
                    }
                });
    }

    private void unFollowUser(int pos, String uid) {
        fD.getReference()
                .child("Follow").child(uid)
                .child("Following").child(users.get(pos).getUserId())
                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            FirebaseDatabase.getInstance().getReference()
                                    .child("Follow").child(users.get(pos).getUserId())
                                    .child("Followers").child(uid)
                                    .removeValue();
                        }
                    }
                });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        ImageView profilePic;
        TextView fullName, username, noOfLikes;
        Button followAndUnfollow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profilePic = itemView.findViewById(R.id.user_profile_pic);
            fullName = itemView.findViewById(R.id.name);
            username = itemView.findViewById(R.id.username);
            followAndUnfollow = itemView.findViewById(R.id.follow_and_following);

        }
    }
}
