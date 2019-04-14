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

public class FoundationEmployeeLivingQuartersLounge extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextFoundationEmployeesLoungeButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_lounge_option_group);
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
                // They chose to search through the kitchenette
                TextView text = (TextView) findViewById(R.id.additional_quarters_lounge_text);
                text.setText("kitchenette");
            } else if (position == 1) {
                // They chose to go through the couches
                TextView text = (TextView) findViewById(R.id.additional_quarters_lounge_text);
                text.setText("couches");
            } else if (position == 2) {
                // They chose to go through the vending machine
                TextView text = (TextView) findViewById(R.id.additional_quarters_lounge_text);
                text.setText("vending machine");
            } else if (position == 4) {
                // They chose to return to the employee quarters
                Intent quartersIntent = new Intent(this, FoundationEmployeeLivingQuarters.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_quarters_lounge_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters_lounge);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
