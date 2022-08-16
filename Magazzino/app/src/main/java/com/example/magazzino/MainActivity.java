package com.example.magazzino;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.magazzino.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private static final int AGGIUNGI_ARTICOLO = 1;
    private static final String ERRORE ="Errore aggiunta articolo";
    private Button aggiungi;
    private String articolo;
    private TextView listaArticoli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){

        super.onResume();
        aggiungi= findViewById(R.id.aggiungi);
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aggiungiArticolo = new Intent(getApplicationContext(),AggiungiArticolo.class);
                startActivityForResult(aggiungiArticolo,AGGIUNGI_ARTICOLO);
                }
            });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        articolo = (requestCode == 1 && resultCode == RESULT_OK) ? data.getExtras().getString("articolo") : ERRORE;
        listaArticoli = findViewById(R.id.vistaArticolo);
        listaArticoli.setText(articolo);
    }

}