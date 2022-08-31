package com.example.instagramclone;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    ActivityResultLauncher<String> get;
    ImageView postImage;
    TextView sharePostTextView, postCaption;
    String postImageUri;
    FirebaseStorage fS = FirebaseStorage.getInstance();
    StorageReference sR = fS.getReference().child("Post");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        postImage = findViewById(R.id.post_image);
        postCaption = findViewById(R.id.post_caption);
        sharePostTextView = findViewById(R.id.share_post_text_view);

        get = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                Intent intent= new Intent(PostActivity.this, CropperActivity.class);
                intent.putExtra("DATA", result.toString());
                startActivityForResult(intent, 101);
            }
        });

        get.launch("image/*");

        sharePostTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePost();
            }

            private void sharePost() {
                String caption, imageUri;

                caption = postCaption.getText().toString();
                imageUri = postImageUri;

                StorageReference fileRef = sR.child(String.valueOf(System.currentTimeMillis())+".jpg");
                Task uploadTask = fileRef.putFile(Uri.parse(imageUri));

                uploadTask.continueWithTask(task -> {
                    if(!task.isSuccessful()){
                        Toast.makeText(PostActivity.this, "File did not upload. There was an error."+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                    return fileRef.getDownloadUrl();
                }).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Uri downloadUrl = (Uri) task.getResult();
                            String myUrl = downloadUrl.toString();
                            DatabaseReference postRef = FirebaseDatabase.getInstance().getReference().child("Posts");
                            String postId = postRef.push().getKey();

                            Map<String, Object> postMap = new HashMap<>();
                            postMap.put("postId",postId);
                            postMap.put("caption",caption);
                            postMap.put("publisher",FirebaseAuth.getInstance().getCurrentUser().getUid());
                            postMap.put("postImage",myUrl);

                            postRef.child(postId).updateChildren(postMap);
                  //          loadingDialog.stopLoadingDialog();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    //    loadingDialog.stopLoadingDialog();
                        Toast.makeText(PostActivity.this, "Check Your Internet Connectivity" + e, Toast.LENGTH_SHORT).show();
                        Log.e("ErrorStorage", "" + e);
                    }
                });
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == -1 && requestCode == 101) {
            String result = data.getStringExtra("RESULT");
            if(result.isEmpty())
            {
                return;
            }
            postImageUri=result;
            postImage.setImageURI(Uri.parse(result));
        }
        else if (resultCode == RESULT_CANCELED){
            finish();
        }
    }
}