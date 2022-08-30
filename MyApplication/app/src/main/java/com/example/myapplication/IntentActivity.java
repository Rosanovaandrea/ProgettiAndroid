package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity {

    public static final int NUMBER = 1;
    Button geoIntentButton;
    Button pickContactButtonIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);
        geoIntentButton = findViewById(R.id.geoButtonIntent);
        pickContactButtonIntent = findViewById(R.id.pickContactButtonIntent);

        geoIntentButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View geoIntentButton){
                Intent geoActivity = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Via+Villanova+130"));
                startActivity(geoActivity);
            }
        });

        pickContactButtonIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickContactActivity = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(pickContactActivity,NUMBER);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == NUMBER && resultCode == RESULT_OK ){
            Uri uriContatto = data.getData();
            ContentResolver contentProvider = getContentResolver();

            Cursor contatto = contentProvider.query(uriContatto,
                    null
                    ,null,null);
            contatto.moveToFirst();
            int colonna = contatto.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            Log.d("intent activity",contatto.getString(colonna));

        }

    }
}