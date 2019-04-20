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

public class FoundationEmployeeLivingQuartersDr3Room extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextFoundationEmployeesDr3ButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_dr3_option_group);
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
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr3_text);
                text.setText("closet");
            } else if (position == 1) {
                // They chose to go through the bathroom
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr3_text);
                text.setText("bathroom with tothbrush");
            } else if (position == 2) {
                // They chose to go through the desk
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr3_text);
                text.setText("desk");
            } else if (position == 3) {
                // They chose to return to the employee quarters
                Intent quartersIntent = new Intent(this, FoundationEmployeeLivingQuarters.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr3_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters_dr3_room);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
