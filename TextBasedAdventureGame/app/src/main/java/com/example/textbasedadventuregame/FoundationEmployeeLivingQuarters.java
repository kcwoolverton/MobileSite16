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
                // They chose to examine other cells
                Context context = getApplicationContext();
                CharSequence text1 = "other cell description";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("other cell description");
            } else if (position == 1) {
                // They chose to examine medbay
                Context context = getApplicationContext();
                CharSequence text1 = "go to medbay";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("go to medbay");

                Intent medbayIntent = new Intent(this, PrisonMedbay.class);

                // Start the new activity.
                startActivity(medbayIntent);
            } else if (position == 2) {
                // They chose to examine showers
                Context context = getApplicationContext();
                CharSequence text1 = "examine showers text";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("examine showers text");
            } else if (position == 3) {
                // They chose to return to go to the stairwell
                Intent stairIntent = new Intent(this, FoundationEmployeeLivingQuartersStairwell.class);

                // Start the new activity.
                startActivity(stairIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
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
        setContentView(R.layout.activity_foundation_employee_living_quarters);
    }
}
