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

public class FoundationEmployeeLivingQuarters extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public boolean hasId;

    public void onNextFoundationEmployeesButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_option_group);
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
                // They chose to go to the armory
                if (hasId) {
                    Intent armoryIntent = new Intent(this, FoundationEmployeeLivingQuartersArmory.class);

                    // Start the new activity.
                    startActivity(armoryIntent);
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_text);
                    text.setText("The door is locked. An ID scanner to the side of the door is flashing red.");
                }
            } else if (position == 1) {
                // They chose to examine Dr. Millard's room.
                Intent room1Intent = new Intent(this, FoundationEmployeeLivingQuartersDr1Room.class);

                // Start the new activity.
                startActivity(room1Intent);
            } else if (position == 2) {
                // They chose to examine Dr. Alvarez's room.
                Intent room2Intent = new Intent(this, FoundationEmployeeLivingQuartersDr2Room.class);

                // Start the new activity.
                startActivity(room2Intent);
            } else if (position == 3) {
                // They chose to examine Dr. Shen's room.
                Intent room3Intent = new Intent(this, FoundationEmployeeLivingQuartersDr3Room.class);

                // Start the new activity.
                startActivity(room3Intent);
            } else if (position == 4) {
                // They chose to examine Sec. Guard Samson's room.
                Intent room4Intent = new Intent(this, FoundationEmployeeLivingQuartersSecurityGuardRoom.class);

                // Start the new activity.
                startActivity(room4Intent);
            } else if (position == 5) {
                // They chose to examine the recreational lounge.
                Intent loungeIntent = new Intent(this, FoundationEmployeeLivingQuartersLounge.class);

                // Start the new activity.
                startActivity(loungeIntent);
            } else if (position == 6) {
                // They chose to return to go to the stairwell.
                Intent stairIntent = new Intent(this, FoundationEmployeeLivingQuartersStairwell.class);

                // Start the new activity.
                startActivity(stairIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_quarters_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters);

        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        this.hasId = inventory.getFoundationEmployeeIdCard();
    }
}
