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

public class PrisonCheckpoint extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public boolean hasId;

    public void onNextPrisonCheckpointButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.prison_checkpoint_option_group);
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
                // They chose to examine security station
                TextView text = (TextView) findViewById(R.id.additional_prison_checkpoint_text);
                text.setText("On the side of the checkpoint opposite the cells is a security station " +
                        "with a set of monitors. This must be where the guards stay.");
            } else if (position == 1) {
                // They chose to try to open the stairwell door
                if (hasId) {
                    // They opened the door with the id
                    Intent stairwellIntent = new Intent(this, PrisonStairwell.class);

                    // Start the new activity.
                    startActivity(stairwellIntent);
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_prison_checkpoint_text);
                    text.setText("You try to open the door, but it doesn't budge. A card scanner to the side of the door is flashing red.");
                }
            } else if (position == 2) {
                // They chose to return to the prison
                Intent prisonIntent = new Intent(this, Prison.class);

                // Start the new activity.
                startActivity(prisonIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_prison_checkpoint_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prison_checkpoint);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        this.hasId = inventory.getPrisonFloorIdCard();
    }
}
