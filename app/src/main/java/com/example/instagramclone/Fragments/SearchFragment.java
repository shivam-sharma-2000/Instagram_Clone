package com.example.instagramclone.Fragments;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagramclone.Adapter.UserAdapter;
import com.example.instagramclone.LoadingDialog;
import com.example.instagramclone.LoginActivity;
import com.example.instagramclone.Model.User;
import com.example.instagramclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    RecyclerView userItem;
    EditText etSearch;
    private FirebaseDatabase fD= FirebaseDatabase.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private LoadingDialog loadingDialog;
    ArrayList<User> usersList;
    UserAdapter userAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        userItem = view.findViewById(R.id.recyclerview_search);
        userItem.setLayoutManager(new LinearLayoutManager(getActivity()));
        etSearch = view.findViewById(R.id.search_user);

        usersList = new ArrayList<>();
        userAdapter = new UserAdapter(usersList);
        loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startLoadingDialog();
        retrieveUsers();
        userItem.setAdapter(userAdapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!s.toString().isEmpty())
                {
                    String username = s.toString();
                    searchUser(username);
                }
                else{
                    retrieveUsers();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void retrieveUsers() {
        Query drf = fD.getReference().child("users");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        drf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();
                for(DataSnapshot userData: snapshot.getChildren())
                {
                    User user = userData.getValue(User.class);

                    if(userData != null)
                    {
                        String id = user.getUserId();
                        if(!id.equals(uid))
                        {
                            usersList.add(user);
                        }
                    }
                }
                userAdapter.notifyDataSetChanged();
                loadingDialog.stopLoadingDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // TODO: write code for result not loading
            }
        });
    }

    private void searchUser(String username) {

        Query drf = fD.getReference().child("users").orderByChild("username").startAt(username).endAt(username+"\uf8ff");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        drf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();
                for(DataSnapshot userData : snapshot.getChildren())
                {
                    User user = userData.getValue(User.class);
                    String id = user.getUserId();
                    if(userData != null)
                    {
                        if(!id.equals(uid))
                        {
                            usersList.add(user);
                        }
                    }
                }
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.d("error",error.toString());
            }
        });
    }
}