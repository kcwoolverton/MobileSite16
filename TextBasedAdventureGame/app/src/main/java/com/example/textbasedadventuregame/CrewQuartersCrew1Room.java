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

public class CrewQuartersCrew1Room extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCrewQuartersCrewRoomButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.crew_quarters_crew_room_option_group);
        int checkedButtonId = group.getCheckedRadioButtonId();
        if (checkedButtonId == -1) {
            Context context = getApplicationContext();
            CharSequence text = "What would you like to do?";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else {
            RadioButton selectedButton = findViewById(checkedButtonId);
            int position = group.indexOfChild(selectedButton);
            if (position == 0) {
                // They chose to search through the closet
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_crew_room_text);
                text.setText("closet");
            } else if (position == 1) {
                // They chose to go through the bathroom
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_crew_room_text);
                text.setText("bathroom");
            } else if (position == 2) {
                // They chose to go through the desk
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_crew_room_text);
                text.setText("desk");
            } else if (position == 3) {
                // They chose to look at the bookshelf
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_crew_room_text);
                text.setText("bookshelf");
            } else if (position == 4) {
                // They chose to return to the crew quarters
                Intent quartersIntent = new Intent(this, CrewQuarters.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_crew_room_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_quarters_crew1_room);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
