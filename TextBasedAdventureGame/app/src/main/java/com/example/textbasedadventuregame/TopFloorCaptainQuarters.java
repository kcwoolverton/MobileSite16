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

public class TopFloorCaptainQuarters extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCaptainQuartersButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_option_group);
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
                // They chose to examine the Captain's computer.
                Intent captainsComputerIntent = new Intent(this, CaptainsComputer.class);

                // Start the new activity.
                startActivity(captainsComputerIntent);
            } else if (position == 1) {
                // They chose to take the escape boat.
                Intent cellWallIntent = new Intent(this, CellWall.class);

                // Start the new activity.
                startActivity(cellWallIntent);
            } else if (position == 2) {
                // They chose to return to the top floor.
                Intent topFloorIntent = new Intent(this, TopFloor.class);

                // Start the new activity.
                startActivity(topFloorIntent);
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
        setContentView(R.layout.activity_top_floor_captain_quarters);
    }
}
