package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Button reg_button;
    private EditText etfullName, etusername, etemail, etpasswod;
    private TextView switchToLogin;

    private FirebaseAuth mAuth;
    public static final String KEY_FULLNAME =  "fullName";
    public static final String KEY_USERNAME =  "username";
    public static final String KEY_EMAIL =  "email";
    public static final String KEY_UID =  "userId";
    public static final String KEY_BIO =  "bio";
    public static final String KEY_IMAGE =  "imageUri";

    private FirebaseDatabase fD= FirebaseDatabase.getInstance();
    private DatabaseReference dR = fD.getReference();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth fA = FirebaseAuth.getInstance();
    private FirebaseUser fU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etfullName = findViewById(R.id.edit_text_1);
        etusername = findViewById(R.id.edit_text_2);
        etemail = findViewById(R.id.edit_text_3);
        etpasswod = findViewById(R.id.edit_text_4);
        mAuth = FirebaseAuth.getInstance();
        reg_button = findViewById(R.id.reg_button);
        switchToLogin = findViewById(R.id.switch_to_login);

    }

    @Override
    protected void onStart() {
        super.onStart();

//        if(mAuth.getCurrentUser() != null)
//        {
//            finish();
//            return;
//        }
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
        switchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToLogin();
            }
        });
    }

    private void switchToLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerUser() {
        String fullName, username, email, password, imageUri;
        fullName = etfullName.getText().toString();
        username = etusername.getText().toString();
        email = etemail.getText().toString();
        password = etpasswod.getText().toString();

        if(fullName.isEmpty() ||username.isEmpty() ||email.isEmpty() ||password.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveUserInformation(fullName,username,email);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void saveUserInformation(String fullName, String username, String email) {

        fU = fA.getCurrentUser();
        String userId = fU.getUid();
        Map<String, Object> user = new HashMap<>();
        user.put(KEY_UID,userId);
        user.put(KEY_USERNAME,username);
        user.put(KEY_FULLNAME,fullName);
        user.put(KEY_EMAIL,email);
        user.put(KEY_BIO,"Hi, There I am using Instagram clone");
        user.put(KEY_IMAGE, "");

        dR.child("users").child(userId).setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                            dR.child("Follow").child(userId)
                                    .child("Following").child(userId)
                                    .setValue(true);
                            showLoginActivity();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Password or Email Invalid", Toast.LENGTH_SHORT).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "failure", Toast.LENGTH_SHORT).show();
                    }
                });
        // register activity comment

    }

    private void showLoginActivity() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}