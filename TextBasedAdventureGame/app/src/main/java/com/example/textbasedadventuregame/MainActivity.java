package com.example.textbasedadventuregame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        // Create an Intent to start the second activity
        Intent gameLoadIntent = new Intent(this, GameLoadPage.class);

        // Start the new activity.
        startActivity(gameLoadIntent);
    }


}
