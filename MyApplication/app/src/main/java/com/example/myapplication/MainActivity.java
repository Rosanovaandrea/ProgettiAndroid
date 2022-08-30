package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.myapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private static final int CODE_ONE=1;
    private String[] elementiDinamici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elementiDinamici = new String[]{"uno","due","tre","quattro"};
    }

    @Override
    protected void onResume(){
        super.onResume();

        ListView listaDinamica = findViewById(R.id.listaProgrammatica);
        /*ArrayAdapter listaNumeri = new ArrayAdapter(getApplicationContext(),
                                                    R.layout.layout_riga_main_activity,
                                                    R.id.testoRiga,
                                                    elementiDinamici);
        listaDinamica.setAdapter(listaNumeri);*/

        ArrayList<Contatto> contatti = new ArrayList<Contatto>();

        for (int i = 0; i < 100; i++){
            contatti.add(new Contatto("ciao "+i, "ciao "+i));
        }
        ContattoAdapter numeri = new ContattoAdapter(getApplicationContext(),R.layout.layout_riga_main_activity,contatti);

        listaDinamica.setAdapter(numeri);

        listaDinamica.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("mainActivity",adapterView.getItemAtPosition(i).toString());
            }
        });


        Button avanti = findViewById(R.id.invia);
        avanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View avanti) {

                //ESEMPIO CODICE DI AVVIO ACTIVITY PER OTTENERE UN RISULTATO
                Intent secondaActivity = new Intent(getApplicationContext(),LayoutActivityExample.class);
                secondaActivity.putExtra("nome","nome");
                startActivityForResult(secondaActivity,CODE_ONE);
            }
        });

    }


   //CODICE PER ELABORARE I DATI DELL' ACTIVITY CHIAMATA CON START_ACTIVITY_FOR_RESULT
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK){
            Button avanti = findViewById(R.id.invia);
            avanti.setText((String)data.getExtras().get("valore"));
        }
    }




}