package com.example.myapplication;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication.Interfaces.ButtonComunicator;
import com.example.myapplication.commons.fragments.ButtonsFragment;
import com.example.myapplication.commons.fragments.ListaContatti;
import com.example.myapplication.databinding.ActivityLayoutExampleBinding;

public class LayoutActivityExample extends AppCompatActivity implements ButtonComunicator {

    private static final int CODE_ONE=1;
    private AppBarConfiguration appBarConfiguration;
    private ActivityLayoutExampleBinding binding;
    String value;
    EditText input;
    ButtonsFragment lista;
    Button avanti;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_example);
        avanti=findViewById(R.id.avant);
    }

    //SALVA DATI ACTIVITY AD UN CAMBIAMENTO DI STATO
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
        input = findViewById(R.id.secondo);
        if (value!=null){
            input.setText(value);
        }

        //ESEMPIO DI AGGIUNTA FRAGMENT IN MANIERA PROGRAMMATICA
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        lista = new ButtonsFragment();
        transaction.add( R.id.appender, lista );
        transaction.commit();

        Button indietro = findViewById(R.id.invia);


        indietro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String value = input.getText().toString();

                //ESEMPIO DI CODICE PER IL RITORNO DEI DATI AD UNA ACTIVITY CHIAMANTE CHE HA UTILIZZATO IL METODO
                //START_ACTIVITY_FOR_RESULT
                if (!value.equals("")) {
                    Intent ritorno = new Intent();
                    ritorno.putExtra("valore", value);
                    setResult(RESULT_OK, ritorno);
                } else {
                    setResult(RESULT_CANCELED);
                }

                LayoutActivityExample.this.finish();
                //
            }
        });

        CheckBox checkbox = findViewById(R.id.checkbox1);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                        Log.d("log","active");
                    else
                        Log.d("log","unchecked");
            }
        });

        avanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent layoutactivity = new Intent(getApplicationContext(),ActivityInputs.class);
                startActivity(layoutactivity);
            }
        });
    }


    // CODICE DI RIPRISTINO ACTIVITY DA CAMBIAMENTO DI STATO
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        if(!(input == null || input.getText().equals(""))){
            outState.putString("valore",input.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    // METODO SOVRASCRITTO DELL'INTERFACCIA DI UN FRAGMENT PER COMUNICAZIONE ACTIVITY FRAGMENT
    @Override
    public void setButton(String active) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.appender, new ListaContatti());
        transaction.commit();
    }
}