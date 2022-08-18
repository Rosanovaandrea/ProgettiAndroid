package com.example.myapplication.commons.fragments;

import android.app.Activity;
import android.os.Bundle;

import android.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.Interfaces.ButtonComunicator;
import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonsFragment extends Fragment {

    Button bottone;
    ButtonComunicator event;

    public ButtonsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        event =(ButtonComunicator) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buttons, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();
        bottone = getView().findViewById(R.id.bottone3);
        bottone.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                event.setButton("active");
            }
        });
    }
}