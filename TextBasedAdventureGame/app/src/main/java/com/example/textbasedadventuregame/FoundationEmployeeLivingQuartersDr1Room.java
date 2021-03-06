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

public class FoundationEmployeeLivingQuartersDr1Room extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextFoundationEmployeesDr1ButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_dr1_option_group);
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
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr1_text);
                text.setText("You look through the room's closet. You see Dr. Millard written on " +
                        "many of the lab coats. It looks like the Dr. Millard was a man of one " +
                        "style, as all of the pants and shirts are of the same basic design.");
            } else if (position == 1) {
                // They chose to go through the bathroom
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr1_text);
                text.setText("The room has a small bathroom attached to it. It has a shower, " +
                        "toilet, and sink. On the sink is a toothbrush and some toothpaste, and " +
                        "in the shower is a bottle of shampoo and a half-used bar of soap.");
            } else if (position == 2) {
                // They chose to go through the desk
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr1_text);
                text.setText("The room has a desk covered in papers with notes about some of the " +
                        "locations the ship has visited.");
            } else if (position == 3) {
                // They chose to look at the posters
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr1_text);
                text.setText("On the wall is a poster with a cat hanging off a tree branch " +
                        "with the words \"Hang in There\" written on it. The only weird thing " +
                        "is that the cat only has its upper half");
            } else if (position == 4) {
                // They chose to return to the employee quarters
                Intent quartersIntent = new Intent(this, FoundationEmployeeLivingQuarters.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr1_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters_dr1_room);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
