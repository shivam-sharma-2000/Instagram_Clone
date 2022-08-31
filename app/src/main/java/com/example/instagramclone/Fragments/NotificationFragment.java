package com.example.instagramclone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.instagramclone.R;

public class NotificationFragment extends Fragment {
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Toolbar toolbar = findViewById(R.id.home_tool_bar);
////        setSupportActionBar(toolbar);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notification, container, false);
        Toolbar toolbar = view.findViewById(R.id.home_tool_bar);
        return view;
    }
}