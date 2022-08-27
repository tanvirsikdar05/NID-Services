package com.tanvir.nidservice.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tanvir.nidservice.R;
import com.tanvir.nidservice.buttonActivity.nidDownload;
import com.tanvir.nidservice.buttonActivity.rootActivity;
import com.tanvir.nidservice.dataclass.allfirebasedata;


public class mainFragment extends Fragment {
    CardView niddownload,nidRevision,Nidabedon,Borncheck,calculate,smartcard;
    String link1="null",link2="null",link3="null",link4="null",link5="null",link6="null";
    ProgressBar progressBar;
    String adtypet;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_main, container, false);

        progressBar=rootView.findViewById(R.id.mainpg);

        progressBar.setVisibility(View.VISIBLE);

        LoafirebaseData();

       niddownload=rootView.findViewById(R.id.crdnidDownload);
       nidRevision=rootView.findViewById(R.id.crdnidRevision);
       Nidabedon=rootView.findViewById(R.id.crdnidAbedon);
       Borncheck=rootView.findViewById(R.id.crdBorncheck);
       calculate=rootView.findViewById(R.id.crdcalculate);
       smartcard=rootView.findViewById(R.id.crdsmartcard);

       clicksall();

        return rootView;
    }

    private void LoafirebaseData() {

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("nid").child("linkks");

        if (isNetworkConnected()){
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {



                        allfirebasedata lotData=snapshot.getValue(allfirebasedata.class);
                        if (lotData.getLinkfive() != null
                                && lotData.getLinkone() != null && lotData.getLinktwo() != null
                                && lotData.getLinkthree() != null && lotData.getLinkfour() != null
                                && lotData.getLinksix() != null){

                            link1=lotData.getLinkone();
                            link2=lotData.getLinktwo();
                            link3=lotData.getLinkthree();
                            link4=lotData.getLinkfour();
                            link5=lotData.getLinkfive();
                            link6=lotData.getLinksix();



                        }
                        loadadtype();



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.i("error","dataLoad error");
                    progressBar.setVisibility(View.GONE);

                }
            });


        }else {
            Toast.makeText(getActivity(), "No Internet Connection error", Toast.LENGTH_SHORT).show();
        }


    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void loadadtype(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("nid").child("ads");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String adtype=snapshot.getValue(String.class);
                if (adtype != null && adtype.equals("admov")){
                    adtypet="admov";

                    progressBar.setVisibility(View.GONE);






                }else if (adtype != null && adtype.equals("start")){
                    adtypet="start";
                    progressBar.setVisibility(View.GONE);

                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.i("firebae","error");

            }
        });
    }



    private void clicksall() {

        niddownload.setOnClickListener(view -> {

            if (link1.equals("null")){
                toastm("not internet connection error");
            }else {
                Intent intent=new Intent(getActivity(), nidDownload.class);
                intent.putExtra("url",link1);
                intent.putExtra("adtype",adtypet);
                startActivity(intent);
            }

        });
        nidRevision.setOnClickListener(view -> {

            if (link2.equals("null")){
                toastm("not internet connection error");
            }else {
                Intent intent=new Intent(getActivity(), rootActivity.class);
                intent.putExtra("url",link2);
                intent.putExtra("adtype",adtypet);
                startActivity(intent);

            }

        });
        Nidabedon.setOnClickListener(view -> {
            if (link3.equals("null")){
                toastm("not internet connection error");
            }else {

                Intent intent=new Intent(getActivity(), rootActivity.class);
                intent.putExtra("url",link3);
                intent.putExtra("adtype",adtypet);
                startActivity(intent);
            }

        });
        Borncheck.setOnClickListener(view -> {

            if (link4.equals("null")){
                toastm("not internet connection error");
            }else {

                Intent intent=new Intent(getActivity(), rootActivity.class);
                intent.putExtra("url",link4);
                intent.putExtra("adtype",adtypet);
                startActivity(intent);
            }

        });

        calculate.setOnClickListener(view -> {
            if (link5.equals("null")){
                toastm("not internet connection error");
            }else {

                Intent intent=new Intent(getActivity(), rootActivity.class);
                intent.putExtra("url",link5);
                intent.putExtra("adtype",adtypet);
                startActivity(intent);
            }
        });

        smartcard.setOnClickListener(view -> {
            if (link6.equals("null")){
                toastm("not internet connection error");
            }else {

                Intent intent=new Intent(getActivity(), rootActivity.class);
                intent.putExtra("url",link6);
                intent.putExtra("adtype",adtypet);
                startActivity(intent);
            }
        });
    }
    private void toastm(String txt){
        Toast.makeText(getActivity(), txt, Toast.LENGTH_SHORT).show();
    }


}