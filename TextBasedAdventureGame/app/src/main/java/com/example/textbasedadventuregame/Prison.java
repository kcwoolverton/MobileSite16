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

public class Prison extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextPrisonButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.prison_option_group);
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
                TextView text = (TextView) findViewById(R.id.additional_prison_text);
                text.setText("The other cells like like yours did. The same bed, the same sink, " +
                        "and the same blood on the walls. The other cells have both prisoners " +
                        "lying unconscious in front of the walls.");
            } else if (position == 1) {
                // They chose to examine medbay
                Intent medbayIntent = new Intent(this, PrisonMedbay.class);

                // Start the new activity.
                startActivity(medbayIntent);
            } else if (position == 2) {
                // They chose to examine showers
                TextView text = (TextView) findViewById(R.id.additional_prison_text);
                text.setText("The showers have soap and shampoo dispensers built into the walls. " +
                        "The showers themselves are designed for a complete lack of privacy, so " +
                        "that the guards can watch the prisoners while they shower.");
            } else if (position == 3) {
                // They chose to look at the checkpoint
                Intent checkpointIntent = new Intent(this, PrisonCheckpoint.class);

                // Start the new activity.
                startActivity(checkpointIntent);
            } else if (position == 4) {
                // They chose to return to cell
                Intent cellIntent = new Intent(this, PlayerCell.class);

                // Start the new activity.
                startActivity(cellIntent);
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
        setContentView(R.layout.activity_prison);
    }
}
