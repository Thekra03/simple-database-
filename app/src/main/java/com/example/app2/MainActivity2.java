package com.example.app2;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper db;

    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = new DatabaseHelper(this); // Initialize the DatabaseHelper

        listView = findViewById(R.id.LIST);
        dataList = new ArrayList<>();

        Cursor cursor = db.getItem();
        while (cursor.moveToNext()) {
            String itemID = cursor.getString(0);
            String itemTitle = cursor.getString(1);
            String combinedItem = itemID + "\n" + itemTitle;
            dataList.add(combinedItem);
        }

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            cursor.moveToPosition(position); // Move cursor to the selected position
            String selectedItemId = cursor.getString(0); // Get ID of the selected item
            String messageBody = cursor.getString(2); // Fetch message body directly from cursor

            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
            intent.putExtra("MESSAGE_BODY", messageBody); // Pass the message body to MainActivity3
            startActivity(intent);
        });
    }
}
