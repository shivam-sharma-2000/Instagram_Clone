package com.example.instagramclone;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.instagramclone.Fragments.HomeFragment;
import com.example.instagramclone.Fragments.NotificationFragment;
import com.example.instagramclone.Fragments.ProfileFragment;
import com.example.instagramclone.Fragments.SearchFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private String uid;
    private BottomNavigationView bottomNavigationView;
    ActivityResultLauncher<String> getContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth= FirebaseAuth.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();

        bottomNavigationView = findViewById(R.id.nav_view);

    }

    @Override
    protected void onStart() {
        super.onStart();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                OpenFragments(id);

                return true;
            }
        });
    }

    private void OpenFragments(int id) {
        switch (id)
        {
            case R.id.navigation_home:
                addFragment(new HomeFragment(),0);
                break;
            case R.id.navigation_notifications:
                addFragment(new NotificationFragment(),1);
                break;
            case R.id.navigation_profile:
                addFragment(new ProfileFragment(),1);
                break;
            case R.id.navigation_search:
                addFragment(new SearchFragment(),1);
                break;
            case R.id.navigation_add_post:
                startActivityForResult(new Intent(MainActivity.this, PostActivity.class), 103);
                break;
        }
        return;
    }

    public void addFragment(Fragment fragment, int flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag == 0)
        {
            ft.replace(R.id.fragment_container, fragment);
            fm.popBackStack("abcd", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            ft.addToBackStack("abcd");
        }
        else{
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
        }

        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 103)
        {
            bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        }
    }

    @Override
    public void onBackPressed() {

        if( getSupportFragmentManager().getBackStackEntryCount()>0) {
            getSupportFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }
}