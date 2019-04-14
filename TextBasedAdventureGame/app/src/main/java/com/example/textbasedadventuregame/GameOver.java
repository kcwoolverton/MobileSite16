package com.example.textbasedadventuregame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextDeckButtonClick(View view) {
        Intent gameoverIntent = new Intent(this, SelectName.class);
        startActivity(gameoverIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        StatusEntity status = statusEntityDao.getAll().get(0);

        PlayerEntityDao playerEntityDao = DBINSTANCE.playerEntityDao();
        PlayerEntity player = playerEntityDao.getAll().get(0);
        int background = player.getBackgroundId();

        if (status.getDeathByOcean()) {
            // If they are not cotbg
            if (background != 3) {
                TextView text = (TextView) findViewById(R.id.textView3);
                text.setText("You walk up to the side of the deck and look over the railing. You think, anything has to be better than this. Whoever comes to rescue this ship will not be happy to see you walking around it--you need to get out of here. You lift yourself over the railing, take a deep breath, and jump. You hit the water hard. It is cold and thrashes around you. You swim for as long as you can in one direction. After a few hours, you see planes fly overhead, but they do not stop for you.");
            // If they are cotbg
            } else {
                TextView text = (TextView) findViewById(R.id.textView3);
                text.setText("You walk up to the side of the deck and look over the railing. You know that your brothers and sisters in the church have given you this opportunity for freedom, but what do they want you to do with it? You are unsure about how to leave the boat--maybe, you think, if you can swim far enough away, they will rescue you. You lift yourself over the railing, take a deep breath, and jump. You hit the water hard. It is cold and thrashes around you. You swim for as long as you can in one direction. After a few hours, you see planes fly overhead, but they do not stop for you.");
            }
        }
    }
}
