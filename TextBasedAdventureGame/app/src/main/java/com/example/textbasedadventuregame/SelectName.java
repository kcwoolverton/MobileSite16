package com.example.textbasedadventuregame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class SelectName extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextButtonClick(View view) {
        EditText firstNameView = (EditText) findViewById(R.id.first_name_field);
        EditText lastNameView = (EditText) findViewById(R.id.last_name_field);
        String firstName = firstNameView.getText().toString();
        String lastName = lastNameView.getText().toString();
        PlayerEntity player = new PlayerEntity(firstName, lastName);

        // Save to db
        PlayerEntityDao playerEntityDao = DBINSTANCE.playerEntityDao();
        playerEntityDao.insert(player);

        PlayerEntityDao dao = DBINSTANCE.playerEntityDao();
        List<PlayerEntity> players = dao.getAll();
        PlayerEntity gottenPlayer = players.get(0);
        String gottenFirstName = gottenPlayer.getFirstName();
        String gottenLastName = gottenPlayer.getLastName();

        Context context = getApplicationContext();
        CharSequence text = "Welcome " + gottenFirstName + " " + gottenLastName;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        // Create an Intent to start the second activity
        Intent backgroundIntent = new Intent(this, SelectBackground.class);

        // Start the new activity.
        startActivity(backgroundIntent);

    }

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_name);

        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
        DBINSTANCE.clearAllTables();
    }
}
