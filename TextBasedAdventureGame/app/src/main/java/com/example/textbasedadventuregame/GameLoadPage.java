package com.example.textbasedadventuregame;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class GameLoadPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_load_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void makeNewGame(View view) {
        // Create an Intent to start the second activity
        Intent newGameIntent = new Intent(this, TheGame.class);

        // Start the new activity.
        startActivity(newGameIntent);
    }

    public void continueGame(View view) {
        // Create an Intent to start the second activity
        Intent continueGameIntent = new Intent(this, TheGame.class);

        // Start the new activity.
        startActivity(continueGameIntent);
    }
}
