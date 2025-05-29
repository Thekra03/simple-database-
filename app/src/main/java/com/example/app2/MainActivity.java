package com.example.app2;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String PREFS_NAME = "MyPreferences";
    static final String PREF_ID = "id";
    static final String PREF_MESSAGE_BODY = "message";
    static final String PREF_MESSAGE_TITLE = "title";

    SharedPreferences sharedPreferences;
    EditText USERid, Mbody, Mtitle;
    Button sp, SQLbtn;
    DatabaseHelper databaseHelper;
    ImageView img;
    RadioButton male, female;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        databaseHelper = new DatabaseHelper(this);

        img = findViewById(R.id.imageView);
        Mbody = findViewById(R.id.editTextText3);
        USERid = findViewById(R.id.editTextText2);
        Mtitle = findViewById(R.id.editTextText);
        sp = findViewById(R.id.button);
        SQLbtn = findViewById(R.id.button2);
        male = findViewById(R.id.r1);
        female = findViewById(R.id.r2);

        // تغيير الصورة مباشرة عند اختيار الجنس
        male.setOnClickListener(v -> img.setImageResource(R.drawable.male));
        female.setOnClickListener(v -> img.setImageResource(R.drawable.fe));

        sp.setOnClickListener(v -> {
            if (Mtitle.getText().toString().isEmpty() &&
                    Mbody.getText().toString().isEmpty() &&
                    USERid.getText().toString().isEmpty()) {

                Toast.makeText(MainActivity.this, "Please enter a message first.", Toast.LENGTH_SHORT).show();
            } else {
                saveDataToSharedPreferences();
                showReviewMessage();
            }
        });

        SQLbtn.setOnClickListener(v -> {
            if (Mtitle.getText().toString().isEmpty() &&
                    Mbody.getText().toString().isEmpty() &&
                    USERid.getText().toString().isEmpty()) {

                Toast.makeText(MainActivity.this, "Please enter a message first.", Toast.LENGTH_SHORT).show();
            } else {
                saveDataToSQL();
            }
        });
    }

    private void saveDataToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREF_ID, USERid.getText().toString());
        editor.putString(PREF_MESSAGE_BODY, Mbody.getText().toString());
        editor.putString(PREF_MESSAGE_TITLE, Mtitle.getText().toString());
        editor.apply();
    }

    private void showReviewMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(Mtitle.getText().toString());

        String userId = sharedPreferences.getString(PREF_ID, "");
        String userMessage = sharedPreferences.getString(PREF_MESSAGE_BODY, "");

        builder.setMessage("User ID: " + userId + "\n\nMessage Body: " + userMessage);
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();

        USERid.setText("");
        Mbody.setText("");
        Mtitle.setText("");
    }

    private void saveDataToSQL() {
        int id = Integer.parseInt(USERid.getText().toString());
        String MessageBody = Mbody.getText().toString();
        String MessageTitle = Mtitle.getText().toString();
        databaseHelper.addMessage(id, MessageBody, MessageTitle);
        USERid.setText("");
        Mbody.setText("");
        Mtitle.setText("");
        Toast.makeText(this, "Saved successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }
}
