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

public class FoundationEmployeeLivingQuarters extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextFoundationEmployeesButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_option_group);
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
                // They chose to go to the armory

            } else if (position == 1) {
                // They chose to examine Dr. TODO's room

            } else if (position == 2) {
                // They chose to examine Dr. TODO's room

            } else if (position == 3) {
                // They chose to examine Dr. TODO's room

            } else if (position == 4) {
                // They chose to examine Sec. Guard TODO's room

            } else if (position == 5) {
                // They chose to examine the recreational lounge

            } else if (position == 6) {
                // They chose to return to go to the stairwell
                Intent stairIntent = new Intent(this, FoundationEmployeeLivingQuartersStairwell.class);

                // Start the new activity.
                startActivity(stairIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_quarters_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters);
    }
}
