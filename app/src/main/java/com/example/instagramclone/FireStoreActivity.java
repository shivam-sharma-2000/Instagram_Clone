package com.example.instagramclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.instagramclone.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FireStoreActivity extends AppCompatActivity {

    private EditText editText1,editText2,editText3;
    private Button button1,button2;
    public static final String KEY_TITLE =  "title";
    public static final String KEY_THOUGHTS =  "thoughts";

    private FirebaseDatabase fD= FirebaseDatabase.getInstance();
    private DatabaseReference dR = fD.getReference();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth fA = FirebaseAuth.getInstance();
    private FirebaseUser fU;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_store);

        button1 = findViewById(R.id.FireStoreButton);
        button2 = findViewById(R.id.FireBaseButton);
        editText1 = findViewById(R.id.et_1);
        editText2 = findViewById(R.id.et_2);
        editText3 = findViewById(R.id.et_3);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title,thoughts;
                title = editText1.getText().toString();
                thoughts = editText2.getText().toString();

                Map<String, Object> data = new HashMap<>();

                data.put(KEY_TITLE, title);
                data.put(KEY_THOUGHTS, thoughts);


                db.collection("Firebase Firestore First Attempt")
                        .document("user information")
                        .set(data)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(FireStoreActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(FireStoreActivity.this, "Un successful", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname,username, email;
                fullname = editText1.getText().toString();
                username = editText2.getText().toString();
                email = editText3.getText().toString();

                User user = new User(fullname,username,email,"hello");

                dR.child("users").child("2").setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(FireStoreActivity.this, "success", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(FireStoreActivity.this, "failure", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}