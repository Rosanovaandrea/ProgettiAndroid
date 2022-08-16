package com.example.magazzino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AggiungiArticolo extends AppCompatActivity {

    EditText articolo;
    Button invia;
    String descrizioneArticolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_articolo);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        articolo=findViewById(R.id.nomeAritcolo);
        invia = findViewById(R.id.invia);
        invia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnListaArticoli = new Intent();
                returnListaArticoli.putExtra("articolo",articolo.getText().toString());
                setResult(RESULT_OK, returnListaArticoli);
                finish();
                }
            });
        }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        articolo=findViewById(R.id.nomeAritcolo);
        outState.putString("articolo", articolo.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (!savedInstanceState.getString("articolo").equals("")){
            articolo.setText(savedInstanceState.getString("articolo"));
        }
    }
}