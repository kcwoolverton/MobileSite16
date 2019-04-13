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

public class CrewQuarters extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCrewQuartersButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.crew_quarters_option_group);
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
                // They chose to go to the galley

            } else if (position == 1) {
                // They chose to go to the crew medbay

            } else if (position == 2) {
                // They chose to examine the gym
                Context context = getApplicationContext();
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_text);
                text.setText("gym description");
            } else if (position == 3) {
                // They chose to examine crew member TODO's room

            } else if (position == 4) {
                // They chose to return to go to the stairwell
                Intent stairIntent = new Intent(this, CrewQuartersStairwell.class);

                // Start the new activity.
                startActivity(stairIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_text);
                text.setText("panic?");
            }

            // Create an Intent to start the next activity
            //Intent introductionIntent = new Intent(this, Introduction.class);

            // Start the new activity.
            //startActivity(introductionIntent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_quarters);
    }
}
