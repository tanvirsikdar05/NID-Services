package com.tanvir.nidservice.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tanvir.nidservice.R;

public class guidefragment extends Fragment {

    CardView download;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_guidefragment, container, false);


        download=rootView.findViewById(R.id.crdhowdownload);

        download.setOnClickListener(view -> {

            DatabaseReference reference= FirebaseDatabase.getInstance().getReference("nid").child("tutrial");

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String url=snapshot.getValue(String.class);
                    if (url != null){
                        Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse(url));
                        getActivity().startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        });
        return rootView;
    }
}