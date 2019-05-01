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
            intro_text.setText("The things that bring other people joy have never meant anything to you. The only thing that makes you feel is the pain and suffering of others. You were good at passing yourself as just an ordinary person, and you took over a dozen lives before you were finally caught. However, while on death row, guards came and put you in a bus with a bag over your heard. When it was finally removed, you saw signs that read \"SCP Foundation\"...");
        } else if (background == 1) {
            intro_text.setText("From a young age, you've had big dreams. You are a visionary and a leader, and you deserve the money, power, and following that such a status grants you. Using your natural intelligence and charisma, you devised a plan that would give you just that--to become the top of an essential oil selling pyramid scheme. Sadly, the world was not ready for your innovative ideas, and you found yourself looking at a lifetime in prison for the lives you \"destroyed.\" In an attempt to shorten your sentence and return to making your impact on the world, you took a deal to become a \"D-class\" personnel at an organization known as the SCP...");
        } else if (background == 2) {
            intro_text.setText("You are a journalist for a mid-sized newspaper. You just got a great opportunity to do an expose on one of the most oppressive regimes in the world. This once in a lifetime opportunity quickly turned into an end of a lifetime opportunity, however. Shortly upon landed in the country, you found yourself in the regime's prison. You were then handed over to an organization known only as the SCP...");
        } else if (background == 3) {
            intro_text.setText("God is real and He is a machine. You belong to the Church of the Broken God. You see the holiness in the mechanical and the blasphemy in the organic. In a raid on the SCP to recover a piece of your god, you were captured and doomed to spending the rest of your short life as one of their disposable meat sacks. You are biding your limited time looking for an opportunity to escape and return to your people...");
        } else if (background == 4) {
            intro_text.setText("The world has never appreciated your genius. Yes, your work got you into a prestigious art school, but your real master pieces, those that could defy the very fabric of the universe, were never understood. Until one day, that is. On that fateful day, you got a letter with a picture of one your pieces, an address, a time, and the words \"Are We Cool Yet?\". With AWCY, your talents blossomed. However, you eventually caught the attention of a group called the \"SCP Foundation\". Your cell evaded them for months before they finally caught up with you. Now, you bide your time until you can escape and return to creating master pieces for the sheep.");
        } else if (background == 5) {
            intro_text.setText("Shortly after you aquired your PhD, you were contacted by a mysterious organization known as the \"SCP Foundation\". The pay and benefits were great, but the real reward was the work. You worked with objects that your former classmates couldn't even imagine. However, one day you were assigned to perform a procedure that you couldn't stomache. You tried to alleviate a little girl's suffering, just a little bit, but the Foundation caught you and demoted all the way to D-Class as punishment. So be it, the Foundation is corrupt to the core if they could allow something like that to occur.");
        } else if (background == 6) {
            intro_text.setText("A few months ago, you were given a fantastic position. You were promoted to CEO of one of a multi-billion dollar corporation. The joy was brief, however, because less than two months later you were arrested for insider trading and litany of other financial crimes. You belatedly realized that the former executives had left you holding the bag for their crimes. Now, you have been handed over to a group known only as the \"SCP Foundation\". God, what did you do to deserve this?");
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
