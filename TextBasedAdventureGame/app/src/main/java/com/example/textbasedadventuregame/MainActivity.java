package com.example.textbasedadventuregame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        // Create an Intent to start the second activity
        Intent gameLoadIntent = new Intent(this, SelectName.class);

        // Start the new activity.
        startActivity(gameLoadIntent);
    }
}
