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

public class CellBody extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCellButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_body_option_group);
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
                // They chose to examine the body's head.
                TextView text = (TextView) findViewById(R.id.additional_cell_body_text);
                text.setText("Your cellmate is bleeding from the forehead. It looks like they hit their head " +
                        "against the wall so much that you can see the skull beneath the skin.");
            } else if (position == 1) {
                // They chose to examine the body's torso.
                TextView text = (TextView) findViewById(R.id.additional_cell_body_text);

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                boolean hasShiv = inventory.getShiv();

                if (hasShiv) {
                    text.setText("You find nothing else tucked into your cellmate's shirt.");
                } else {
                    text.setText("Searching your cellmate's shirt, you find a makeshift shiv " +
                            "tucked into a hidden pocket. You slide the shiv out and take it for yourself.");
                    inventory.setShiv(true);
                    inventoryEntityDao.update(inventory);
                }
            } else if (position == 2) {
                // They chose to examine the body's legs.
                TextView text = (TextView) findViewById(R.id.additional_cell_body_text);
                text.setText("Your cellmate is wearing the same Foundation issue pants as everyone else. " +
                        "There is nothing in the pockets.");
            } else if (position == 3) {
                // They chose to return to cell
                Intent cellIntent = new Intent(this, PlayerCell.class);

                // Start the new activity.
                startActivity(cellIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_cell_body_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_body);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
