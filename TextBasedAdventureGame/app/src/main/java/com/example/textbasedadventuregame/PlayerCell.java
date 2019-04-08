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

public class PlayerCell extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCellButtonClick(View view) {
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
                // They chose look at the toilet
                Context context = getApplicationContext();
                CharSequence text1 = "toilet?";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("toilet description here");
            } else if (position == 1) {
                // They chose look at the glass wall
                Intent cellWallIntent = new Intent(this, CellWall.class);

                // Start the new activity.
                startActivity(cellWallIntent);
            } else if (position == 2) {
                // They chose look at the sink
                Intent cellSinkIntent = new Intent(this, CellSink.class);

                // Start the new activity.
                startActivity(cellSinkIntent);
            } else if (position == 3) {
                // They chose look at the bed
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("Bed description here");
            } else if (position == 4) {
                // They chose look at the body
                Intent cellBodyIntent = new Intent(this, CellBody.class);

                // Start the new activity.
                startActivity(cellBodyIntent);
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
        setContentView(R.layout.activity_player_cell);
    }
}

