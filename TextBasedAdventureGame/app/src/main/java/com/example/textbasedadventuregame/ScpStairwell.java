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

public class ScpStairwell extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextScpStairwellButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.scp_stairwell_option_group);
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
                Intent prisonStairwellIntent = new Intent(this, PrisonStairwell.class);

                // Start the new activity.
                startActivity(prisonStairwellIntent);
            } else if (position == 1) {
                // Enter scp floor
                Intent scpFloorIntent = new Intent(this, Scp.class);

                // Start the new activity.
                startActivity(scpFloorIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_scp_stairwell_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scp_stairwell);
    }
}
