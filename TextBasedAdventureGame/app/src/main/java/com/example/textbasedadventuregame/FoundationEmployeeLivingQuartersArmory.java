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

public class FoundationEmployeeLivingQuartersArmory extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextFoundationEmployeesArmoryButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_armory_option_group);
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
                // They chose to search through the weapons
                TextView text = (TextView) findViewById(R.id.additional_quarters_armory_text);
                text.setText("weapons");

                // Give them a gun and some ammo
                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                inventory.setGun(true);
                inventory.setAmmo(4);
                inventoryEntityDao.update(inventory);
            } else if (position == 1) {
                // They chose to go through the armor
                TextView text = (TextView) findViewById(R.id.additional_quarters_armory_text);
                text.setText("armor");

                // Give them some armor
                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                inventory.setArmor(true);
                inventoryEntityDao.update(inventory);
            } else if (position == 2) {
                // They chose to go through the desk
                TextView text = (TextView) findViewById(R.id.additional_quarters_armory_text);
                text.setText("desk");
            } else if (position == 3) {
                // They chose to go through the lockers
                TextView text = (TextView) findViewById(R.id.additional_quarters_armory_text);
                text.setText("lockers");
            } else if (position == 4) {
                // They chose to look at the monitors
                TextView text = (TextView) findViewById(R.id.additional_quarters_armory_text);
                text.setText("monitors");
            } else if (position == 5) {
                // They chose to return to the employee quarters
                Intent quartersIntent = new Intent(this, FoundationEmployeeLivingQuarters.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_quarters_armory_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters_armory);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
