package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ContattoAdapter extends ArrayAdapter<Contatto> {

    private LayoutInflater inflater;
    private TextView riga1;
    private TextView riga2;


    public ContattoAdapter(@NonNull Context context, int resource, @NonNull List<Contatto> objects) {
        super(context, resource, objects);

        //RECUPERA L'INFLATER PER CONVERTIRE FILE XML
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //RECUPERA IL LAYOUT DELLA RIGA, COMPRESI GL ID DEGLI ELEMENTI
        if ( convertView == null ) {
             convertView = inflater.inflate(R.layout.rigadinamica, null);
             riga1 = convertView.findViewById(R.id.riga1);
             riga2 = convertView.findViewById(R.id.riga2);
        }


        //INSERISCI I DATI DELL'ELEMENTO NEL LAYOUT
        Contatto contatto = getItem(position);
        riga1.setText(contatto.getCognome());
        riga2.setText(contatto.getNome());

        return convertView;
    }
}
