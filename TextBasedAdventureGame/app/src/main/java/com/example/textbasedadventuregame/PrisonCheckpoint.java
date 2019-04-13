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
                Context context = getApplicationContext();
                CharSequence text1 = "security station desc";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("security station desc");
            } else if (position == 1) {
                // They chose to try to open the stairwell door
                Context context = getApplicationContext();
                CharSequence text1 = "You try to open the door, but it doesn't budge. A card scanner is flashing red.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("You try to open the door, but it doesn't budge. A card scanner is flashing red.");
            } else if (position == 2) {
                // They chose to return to the prison
                Intent prisonIntent = new Intent(this, Prison.class);

                // Start the new activity.
                startActivity(prisonIntent);
            } else if (position == 3) {
                // They opened the door with the id
                Intent stairwellIntent = new Intent(this, PrisonStairwell.class);

                // Start the new activity.
                startActivity(stairwellIntent);
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
        setContentView(R.layout.activity_prison_checkpoint);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        boolean hasId = inventory.getPrisonFloorIdCard();

        if (!hasId) {
            RadioGroup group = (RadioGroup) findViewById(R.id.prison_checkpoint_option_group);
            RadioButton button = (RadioButton) group.getChildAt(3);
            button.setVisibility(View.GONE);
        } else {
            RadioGroup group = (RadioGroup) findViewById(R.id.prison_checkpoint_option_group);
            RadioButton button = (RadioButton) group.getChildAt(3);
            button.setVisibility(View.VISIBLE);
        }
    }
}
