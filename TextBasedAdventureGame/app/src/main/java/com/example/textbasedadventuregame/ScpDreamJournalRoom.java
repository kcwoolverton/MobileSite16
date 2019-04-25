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

public class ScpDreamJournalRoom extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextScpDreamJournalButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.scp_dream_journal_option_group);
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
                // They chose to examine the dream journal
                TextView text = (TextView) findViewById(R.id.additional_scp_bunny_text);
                text.setText("dream journal description");
            } else if (position == 1) {
                // They chose to use the dream journal
                TextView text = (TextView) findViewById(R.id.additional_scp_bunny_text);
                text.setText("dream journal text");
            } else if (position == 2) {
                // They chose to return to the scp containment floor
                Intent quartersIntent = new Intent(this, Scp.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_scp_bunny_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scp_dream_journal_room);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
