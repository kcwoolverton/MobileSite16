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

public class PrisonStairwell extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public boolean hasId;

    public void onNextCellButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.prison_stairwell_option_group);
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
                Intent livingQuartersIntent = new Intent(this, FoundationEmployeeLivingQuartersStairwell.class);

                // Start the new activity.
                startActivity(livingQuartersIntent);
            } else if (position == 1) {
                // Go down
                if (hasId) {
                    Intent scpIntent = new Intent(this, ScpStairwell.class);

                    // Start the new activity.
                    startActivity(scpIntent);
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_prison_checkpoint_text);
                    text.setText("The door reads \"SCP Containment: Authorized Personnel Only\" in large red letters. You try to open it, but it doesn't budge. A card scanner to the side of the door is flashing red.");
                }
            } else if (position == 2) {
                // Go back to prison checkpoint
                Intent prisonIntent = new Intent(this, PrisonCheckpoint.class);

                // Start the new activity.
                startActivity(prisonIntent);
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
        setContentView(R.layout.activity_prison_stairwell);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        this.hasId = inventory.getFoundationEmployeeIdCard();
    }
}
