package com.example.textbasedadventuregame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class TopFloor extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextTopFloorButtonClick(View view) {
        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);
        boolean isSiteDirector = status.getIsSiteDirector();

        TextView text = (TextView) findViewById(R.id.additional_top_floor_text);

        RadioGroup group = (RadioGroup) findViewById(R.id.top_floor_option_group);
        int checkedButtonId = group.getCheckedRadioButtonId();
        if (checkedButtonId == -1) {
            Context context = getApplicationContext();
            CharSequence warningMessage = "What would you like to do?";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, warningMessage, duration);
            toast.show();
        } else {
            RadioButton selectedButton = findViewById(checkedButtonId);
            int position = group.indexOfChild(selectedButton);
            if (position == 0) {
                // Go down
                Intent deckIntent = new Intent(this, Deck.class);

                // Start the new activity.
                startActivity(deckIntent);
            } else if (position == 1) {

                if (isSiteDirector) {
                    // Enter the captain's quarters
                    Intent captainIntent = new Intent(this, TopFloorCaptainQuarters.class);

                    // Start the new activity.
                    startActivity(captainIntent);
                } else {
                    text.setText("The biometric scanner flashes red when you try to use it.");
                }
            } else if (position == 2) {

                if (isSiteDirector) {
                    // Enter the communication's room
                    Intent commIntent = new Intent(this, TopFloorCommRoom.class);

                    // Start the new activity.
                    startActivity(commIntent);
                } else {
                    text.setText("The biometric scanner flashes red when you try to use it.");
                }
            } else if (position == 3) {

                if (isSiteDirector) {
                    // Enter the helm
                    Intent helmIntent = new Intent(this, TopFloorHelm.class);

                    // Start the new activity.
                    startActivity(helmIntent);
                } else {
                    text.setText("The biometric scanner flashes red when you try to use it.");
                }
            } else {
                // panic?
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        setContentView(R.layout.activity_top_floor);
    }
}
