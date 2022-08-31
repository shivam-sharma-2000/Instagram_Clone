package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button log_button;
    private EditText etemail, etpasswod;
    private FirebaseAuth mAuth;
    private LoadingDialog loadingDialog;
    private TextView switchToRegister;
    private static int splash_screen_time_out = 2000;
    LinearLayout linearLayout;
    ConstraintLayout mainLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etemail = findViewById(R.id.edit_text_1);
        etpasswod = findViewById(R.id.edit_text_2);
        log_button = findViewById(R.id.log_button);
        switchToRegister = findViewById(R.id.switch_to_register);
        mAuth = FirebaseAuth.getInstance();
        linearLayout = findViewById(R.id.splash_activity);
        mainLayout = findViewById(R.id.main_layout);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mAuth.getCurrentUser() != null)
                {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    mainLayout.setAlpha(1);
                    linearLayout.setAlpha(0);
                    return;
                }
            }
        }, splash_screen_time_out);


    }

    @Override
    protected void onStart() {
        super.onStart();



        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
        switchToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchToRegister();
            }
        });

    }

    private void switchToRegister() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginUser() {
        String email, password;
        email = etemail.getText().toString();
        password = etpasswod.getText().toString();

        if(email.isEmpty() ||password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            return;
        }
        loadingDialog = new LoadingDialog(LoginActivity.this);
        loadingDialog.startLoadingDialog();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMainActivity();

                        } else {
                            loadingDialog.stopLoadingDialog();
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        loadingDialog.stopLoadingDialog();
        finish();
    }
}