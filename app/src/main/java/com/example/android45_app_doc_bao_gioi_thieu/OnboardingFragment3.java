package com.example.android45_app_doc_bao_gioi_thieu;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.android45_app_doc_bao.MainActivity;
import com.example.android45_app_doc_bao.R;

public class OnboardingFragment3 extends Fragment {


    Button btnGetStart;
    View mView;
    public OnboardingFragment3() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_onboarding3, container, false);

        btnGetStart = mView.findViewById(R.id.btGetStart);

        btnGetStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

            }
        });
        return  mView;
    }
}