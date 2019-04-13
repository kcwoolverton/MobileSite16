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

public class FoundationEmployeeLivingQuartersStairwell extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCellButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_stairwell_option_group);
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
                // Go up
                Intent crewQuartersStairsIntent = new Intent(this, CrewQuartersStairwell.class);

                // Start the new activity.
                startActivity(crewQuartersStairsIntent);
            } else if (position == 1) {
                // Go down
                Intent prisonStairsIntent = new Intent(this, PrisonStairwell.class);

                // Start the new activity.
                startActivity(prisonStairsIntent);
            } else if (position == 2) {
                // Enter living quarters
                Intent livingQuartersIntent = new Intent(this, FoundationEmployeeLivingQuarters.class);

                // Start the new activity.
                startActivity(livingQuartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters_stairwell);
    }
}
