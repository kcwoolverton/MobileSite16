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

public class FoundationEmployeeLivingQuartersDr2Room extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    boolean hasFedJosie;

    public void onNextFoundationEmployeesDr2ButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_dr2_option_group);
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
                // They chose to search through the closet
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("closet");
            } else if (position == 1) {
                // They chose to go through the bathroom
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("bathroom");
            } else if (position == 2) {
                // They chose to go through the desk
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("desk");
            } else if (position == 3) {
                // They chose to look at the papers on the ground
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("paper on the ground");
            } else if (position == 4) {
                // They chose to look at josie
                if (hasFedJosie) {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie basic description, no id, looks happy as she nibbles on cheese.");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie basic description, yes id, looks mildly agitated.");
                }
            } else if (position == 5) {
                // They chose to approach josie
                if (hasFedJosie) {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie purrs as you pet her. You slip the ID out from under her, and take it with you.");

                    InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                    List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                    InventoryEntity inventory = inventoryList.get(0);
                    inventory.setFoundationEmployeeIdCard(true);
                    inventoryEntityDao.update(inventory);
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie growls and claws at your hand as you try to get the ID card. You retreat back to safety.");
                }
            } else if (position == 6) {
                // They chose to feed josie some cheese
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("josie + cheese");

                // Remove the cheese from player's inventory
                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                inventory.setCheese(false);
                inventoryEntityDao.update(inventory);

                // The player has now fed Josie
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                status.setFedJosie(true);
                statusEntityDao.update(status);
            } else if (position == 7) {
                // They chose to return to the employee quarters
                Intent quartersIntent = new Intent(this, FoundationEmployeeLivingQuarters.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters_dr2_room);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        boolean hasCheese = inventory.getCheese();

        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);
        boolean fedJosie = status.getFedJosie();
        this.hasFedJosie = fedJosie;

        if (fedJosie || !hasCheese) {
            // Player already fed Josie or doesn't have cheese.
            RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_dr2_option_group);
            RadioButton button = (RadioButton) group.getChildAt(6);
            button.setVisibility(View.GONE);
        } else {
            // Player has the cheese but has not fed Josie.
            RadioGroup group = (RadioGroup) findViewById(R.id.foundation_employees_dr2_option_group);
            RadioButton button = (RadioButton) group.getChildAt(6);
            button.setVisibility(View.VISIBLE);
        }
    }
}
