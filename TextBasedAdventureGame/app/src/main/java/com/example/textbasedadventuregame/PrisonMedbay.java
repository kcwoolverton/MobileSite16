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

public class PrisonMedbay extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextPrisonMedbayButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.prison_medbay_option_group);
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
                // They chose to search through cabinets
                Context context = getApplicationContext();
                CharSequence text1 = "cabinets description";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("cabinets description");
            } else if (position == 1) {
                // They chose to go through the papers
                Context context = getApplicationContext();
                CharSequence text1 = "papers";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("papers");
            } else if (position == 2) {
                // They chose to go through the desk
                Context context = getApplicationContext();
                CharSequence text1 = "desk";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("desk");
            } else if (position == 3) {
                // They chose to go through the labcoat
                Context context = getApplicationContext();
                CharSequence text1 = "labcoat";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("labcoat");
            } else if (position == 4) {
                // They chose to go through the medical instruments
                Context context = getApplicationContext();
                CharSequence text1 = "medical instruments";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("medical instruments");
            } else if (position == 5) {
                // They chose to return to the cells
                Intent prisonIntent = new Intent(this, Prison.class);

                // Start the new activity.
                startActivity(prisonIntent);
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
        setContentView(R.layout.activity_prison_medbay);
    }
}
