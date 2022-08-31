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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth fA = FirebaseAuth.getInstance();
    private FirebaseDatabase fD = FirebaseDatabase.getInstance();
    private DatabaseReference dR = fD.getReference().child("users").child(fA.getCurrentUser().getUid());

    public static final String KEY_FULLNAME = "fullName";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_UID = "userId";
    public static final String KEY_BIO = "bio";
    public static final String KEY_IMAGE = "imageUri";

    EditText etname, etusername, etemail, etbio;
    ImageView clear, profilePic;
    TextView saveInfo, changeProfilePic;
    ActivityResultLauncher<String> getContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etname = findViewById(R.id.edit_text_1);
        etusername = findViewById(R.id.edit_text_2);
        etemail = findViewById(R.id.edit_text_3);
        etbio = findViewById(R.id.edit_text_4);
        saveInfo = findViewById(R.id.save_info);
        clear = findViewById(R.id.clear_button);
        profilePic = findViewById(R.id.profile_pic);
        changeProfilePic = findViewById(R.id.change_profile_text_view);
    }

    @Override
    protected void onStart() {
        super.onStart();

        //for getting default user information
        getUserInformation();

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToMainActivity();
            }
        });

        changeProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContent.launch("image/*");
            }
        });

        getContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if (result != null) {
                    Intent intent = new Intent(EditProfileActivity.this, CropperActivity.class);
                    intent.putExtra("DATA", result.toString());
                    startActivityForResult(intent, 101);
                }
            }
        });
    }

    private void switchToMainActivity() {
        finish();
    }

    private void saveUserInformation() {

        Map<String, Object> user = new HashMap<>();

        String fullName, username, email, bio;
        fullName = etname.getText().toString();
        username = etusername.getText().toString();
        email = etemail.getText().toString();
        bio = etbio.getText().toString();

        user.put(KEY_USERNAME, username);
        user.put(KEY_FULLNAME, fullName);
        user.put(KEY_EMAIL, email);
        user.put(KEY_BIO, bio);

        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || bio.isEmpty()) {
            Toast.makeText(EditProfileActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            finish();
        }

        dR.updateChildren(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(EditProfileActivity.this, "saved successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfileActivity.this, "some thing went wrong" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    //one time value get function
    private void getUserInformation() {

        dR.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                        // TODO : Do something if task is unsuccessful
//                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    User user = task.getResult().getValue(User.class);
                    String name, username, email, bio, imageUri;
                    name = user.getFullName();
                    username = user.getUsername();
                    email = user.getEmail();
                    bio = user.getBio();
                    imageUri = user.getImageUri();

                    etname.setText(name);
                    etusername.setText(username);
                    etemail.setText(email);
                    etbio.setText(bio);

                    if (user.getImageUri() == null) {
                        profilePic.setImageResource(R.drawable.ic_baseline_person_24);
                    } else {
                        imageUri = user.getImageUri();
//                        Picasso.get().load(imageUri).placeholder(R.drawable.ic_baseline_person_24).into(profilePic);
                        profilePic.setImageURI(Uri.parse(imageUri));
                    }
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            String result = data.getStringExtra("RESULT");
            Uri resultUri = null;
            if (result == null) {
                return;
            }
            resultUri = Uri.parse(result);
            profilePic.setImageURI(resultUri);
            Map<String, Object> user = new HashMap<>();
            user.put(KEY_IMAGE, result);
            dR.updateChildren(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(EditProfileActivity.this, "saved successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(EditProfileActivity.this, "some thing went wrong" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else if(resultCode == RESULT_CANCELED) {
            Toast.makeText(EditProfileActivity.this, "Profile photo did not change.", Toast.LENGTH_SHORT).show();
        }
    }
}