package com.tanvir.nidservice.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.tanvir.nidservice.R;


public class aboutfragment extends Fragment {
    ReviewManager manager;
    ReviewInfo reviewInfo;
    Button rateus;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView=inflater.inflate(R.layout.fragment_aboutfragment, container, false);
        activereviewinfo();

        rateus=rootView.findViewById(R.id.rateus);


        rateus.setOnClickListener(view -> {
            startreviewflow();
        });







        return rootView;

    }
    private void activereviewinfo(){
         manager = ReviewManagerFactory.create(getContext());
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                 reviewInfo = task.getResult();
            } else {
                Log.i("error review","review");
            }
        });

    }
    private void startreviewflow(){
        if (reviewInfo != null){
            manager.launchReviewFlow(getActivity(),reviewInfo);
        }
    }
}