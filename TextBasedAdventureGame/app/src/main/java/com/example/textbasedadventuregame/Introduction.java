package com.example.textbasedadventuregame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Introduction extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        // get the player
        PlayerEntityDao playerDao = DBINSTANCE.playerEntityDao();
        PlayerEntity player = playerDao.getAll().get(0);

        int background = player.getBackgroundId();

        final TextView intro_text = findViewById(R.id.introduction_text);

        if (background == 0) {
            intro_text.setText("You are a serial killer.");
        } else if (background == 1) {
            intro_text.setText("From a young age, you've had big dreams. You are a visionary and a leader, and you deserve the money, power, and following that such a status grants you. Using your natural intelligence and charisma, you devised a plan that would give you just that--to become the top of an essential oil selling pyramid scheme. Sadly, the world was not ready for your innovative ideas, and you found yourself looking at a lifetime in prison for the lives you \"destroyed.\" In an attempt to shorten your sentence and return to making your impact on the world, you took a deal to become a \"D-class\" personnel at an organization known as the SCP...");
        } else if (background == 2) {
            intro_text.setText("You are a journalist for a mid-sized newspaper. You just got a great opportunity to do an expose on one of the most oppressive regimes in the world. This once in a lifetime opportunity quickly turned into an end of a lifetime opportunity, however. Shortly upon landed in the country, you found yourself in the regime's prison. You were then handed over to an organization known only as the SCP...");
        } else if (background == 3) {
            intro_text.setText("God is real and He is a machine. You belong to the Church of the Broken God. You see the holiness in the mechanical and the blasphemy in the organic. In a raid on the SCP to recover a piece of your god, you were captured and doomed to spending the rest of your short life as one of their disposable meat sacks. You are biding your limited time looking for an opportunity to escape and return to your people...");
        } else if (background == 4) {
            intro_text.setText("Art");
        } else if (background == 5) {
            intro_text.setText("research");
        } else if (background == 6) {
            intro_text.setText("You are stupid");
        } else {
            intro_text.setText("There has been a problem. This is not part of the game--there is a legitimate problem.");
        }
    }

    public void onNextIntroClick(View view) {
        // Create an Intent to start the second activity
        Intent playerCellIntent = new Intent(this, PlayerCell.class);

        // Start the new activity.
        startActivity(playerCellIntent);
    }
}
