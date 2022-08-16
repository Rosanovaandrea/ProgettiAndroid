package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.myapplication.databinding.ActivityLayoutExampleBinding;

public class LayoutActivityExample extends AppCompatActivity {

    private static final int CODE_ONE=1;
    private AppBarConfiguration appBarConfiguration;
    private ActivityLayoutExampleBinding binding;
    String value;
    EditText input;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_example);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        value = savedInstanceState.getString("valore");
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView primo = findViewById(R.id.primo);
        String nome = (String) getIntent().getExtras().get("nome");
        primo.setText(nome);
        input = findViewById(R.id.nome);
        if (value!=null){
            input.setText(value);
        }


        Button indietro = findViewById(R.id.invia);

        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = input.getText().toString();

                if (!value.equals("")) {
                    Intent ritorno = new Intent();
                    ritorno.putExtra("valore", value);
                    setResult(RESULT_OK, ritorno);
                } else {
                    setResult(RESULT_CANCELED);
                }

                LayoutActivityExample.this.finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        if(!(input == null || input.getText().equals(""))){
            outState.putString("valore",input.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }
}