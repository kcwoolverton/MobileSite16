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

public class CrewQuartersStairwell extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCrewQuartersStairwellButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.crew_quarters_stairwell_option_group);
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
                Intent deckIntent = new Intent(this, Deck.class);

                // Start the new activity.
                startActivity(deckIntent);
            } else if (position == 1) {
                // Go down
                Intent foundationStairsIntent = new Intent(this, FoundationEmployeeLivingQuartersStairwell.class);

                // Start the new activity.
                startActivity(foundationStairsIntent);
            } else if (position == 2) {
                // Enter crew living quarters
                Intent livingQuartersIntent = new Intent(this, CrewQuarters.class);

                // Start the new activity.
                startActivity(livingQuartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_cq_stairwell_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_quarters_stairwell);
    }
}
