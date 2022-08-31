package com.example.myapplication;

import static android.provider.ContactsContract.Data.CONTENT_URI;

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

    public static final int CONTENT_URI_1 = 1;
    public static final int CONTENT_URI_2 = 2;
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
                startActivityForResult(pickContactActivity,CONTENT_URI_2);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == CONTENT_URI_1 && resultCode == RESULT_OK ){
            Uri uriContatto = data.getData();
            ContentResolver contentProvider = getContentResolver();

            Cursor contatto = contentProvider.query(uriContatto, null,null,null);
            contatto.moveToFirst();

            int colonna = contatto.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            Log.d("intent activity",contatto.getString(colonna));

        }

        if( requestCode == CONTENT_URI_2 && resultCode == RESULT_OK ){

            Uri uriContatto = data.getData();
            ContentResolver resolver = getContentResolver();

            Cursor idContatto = resolver.query(uriContatto, null, null,null, null);
            idContatto.moveToFirst();
            String id = idContatto.getString((int) idContatto.getColumnIndex(ContactsContract.Contacts._ID));

            String whereClausure = ContactsContract.Contacts._ID+"= ? AND "+ContactsContract.Data.MIMETYPE+"= ?";
            String[] whereArgs = { id, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE };

            idContatto = resolver.query(ContactsContract.Data.CONTENT_URI, null,whereClausure, whereArgs,null);

            idContatto.moveToFirst();

            int colonna = idContatto.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            Log.d("intentActivity",idContatto.getString(colonna));

        }

    }
}