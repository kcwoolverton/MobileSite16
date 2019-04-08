package com.example.textbasedadventuregame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GameLoadPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_load_page);
    }

    public void makeNewGame(View view) {
        // Create an Intent to start the second activity
        Intent newGameIntent = new Intent(this, SelectName.class);

        // Start the new activity.
        startActivity(newGameIntent);
    }

    public void continueGame(View view) {
        // Create an Intent to start the second activity
        Intent continueGameIntent = new Intent(this, SelectName.class);

        // Start the new activity.
        startActivity(continueGameIntent);
    }
}
