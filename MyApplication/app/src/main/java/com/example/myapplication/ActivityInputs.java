package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

public class ActivityInputs extends AppCompatActivity {

    CheckBox checkbox;
    RadioGroup radioGroup;
    Map<String,String> options;
    Button intentActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputs);
        checkbox = findViewById(R.id.checkbox4);
        radioGroup = findViewById(R.id.radioChoose);
        options = new HashMap<String, String>();
        intentActivityButton = findViewById(R.id.buttonIntentActivity);

        options.put("option 1","option 1");
        options.put("option 2","option 2");

        intentActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intentActivity = new Intent(getApplicationContext(),IntentActivity.class);
                startActivity(intentActivity);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton choosed = findViewById(i);
                Log.d("clicked options",options.get(choosed.getText().toString()));
            }
        });

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d("clicked","clicked");
            }
        });
    }

    @Override
    protected  void onResume(){
        super.onResume();

    }

}