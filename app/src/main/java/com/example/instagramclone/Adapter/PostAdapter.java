package com.example.instagramclone.Adapter;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.Model.Post;
import com.example.instagramclone.Model.User;
import com.example.instagramclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private FirebaseDatabase fD= FirebaseDatabase.getInstance();
    private DatabaseReference postRef = fD.getReference().child("Posts");
    private DatabaseReference ref = fD.getReference();
    private String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();

    ArrayList<Post> posts;

    public PostAdapter(ArrayList<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_layout,null);
        PostAdapter.ViewHolder viewHolder = new PostAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {

        int pos = position;
        holder.postCaptionText.setText(posts.get(pos).getCaption());
        Picasso.get().load(posts.get(pos).getPostImage()).into(holder.postImage);
        String postImageUrl, postId, publisher;
        postImageUrl = posts.get(pos).getPostImage();
        postId = posts.get(pos).getPostId();
        publisher = posts.get(pos).getPublisher();
        DatabaseReference usersRef = fD.getReference().child("users");
        usersRef.child(publisher).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String username, profilePicUri;
                username = user.getUsername();
                profilePicUri = user.getImageUri();
                holder.postUserProfilePic.setImageURI(Uri.parse(profilePicUri));
                holder.postUsernameText.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ref.child("Likes").child(postId).child("likedBy").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    if(task.getResult().getValue()!=null)
                    {
                        String n = String.valueOf(task.getResult().getChildrenCount());
                        holder.noOfLikes.setText(n);
                    }
                }
            }
        });
        DatabaseReference likeRef = fD.getReference().child("Likes").child(postId).child("likedBy").child(currentUser);
        likeRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(!(task.getResult().getValue()==null))
                    {
                        holder.likeButton.setImageResource(R.drawable.heart_431);
                        holder.likeButton.setTag("1");
                    }
                }

            }
        });
        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(holder.noOfLikes.getText().toString());
                if(holder.likeButton.getTag().toString().equals("0")){
                    holder.likeButton.setTag("1");
                    holder.likeButton.setImageResource(R.drawable.heart_431);
                    ref.child("Likes").child(postId)
                            .child("likedBy").child(currentUser).setValue(true);
                    holder.noOfLikes.setText(Integer.toString(n+1));
                }else{
                    holder.likeButton.setTag("0");
                    holder.likeButton.setImageResource(R.drawable.ic_outline_favorite_border_24);
                    ref.child("Likes").child(postId)
                            .child("likedBy").child(currentUser).setValue(true);
                    holder.noOfLikes.setText(Integer.toString(n-1));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView postUserProfilePic, postImage;
        TextView postCaptionText, postUsernameText, noOfLikes;
        ImageView likeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postUserProfilePic = itemView.findViewById(R.id.post_user_profile_pic);
            postImage = itemView.findViewById(R.id.post_image);
            postCaptionText = itemView.findViewById(R.id.post_caption);
            postUsernameText = itemView.findViewById(R.id.post_username);
            likeButton = itemView.findViewById(R.id.post_like);
            noOfLikes = itemView.findViewById(R.id.no_of_likes);
        }
    }
}
