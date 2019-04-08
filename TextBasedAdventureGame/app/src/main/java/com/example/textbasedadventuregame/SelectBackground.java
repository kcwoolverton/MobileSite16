package com.example.textbasedadventuregame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.List;

public class SelectBackground extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextButtonClick(View view) {
        PlayerEntityDao playerEntityDao = DBINSTANCE.playerEntityDao();
        List<PlayerEntity> playerList = playerEntityDao.getAll();
        PlayerEntity player = playerList.get(0);

        RadioGroup group = (RadioGroup) findViewById(R.id.background_group);
        int checkedButtonId = group.getCheckedRadioButtonId();
        if (checkedButtonId == -1) {
            Context context = getApplicationContext();
            CharSequence text = "Please select a background.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            RadioButton selectedButton = findViewById(checkedButtonId);
            int position = group.indexOfChild(selectedButton);
            player.setBackgroundId(position);

            playerEntityDao.update(player);

            // Create an Intent to start the next activity
            Intent introductionIntent = new Intent(this, Introduction.class);

            // Start the new activity.
            startActivity(introductionIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_background);

        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
