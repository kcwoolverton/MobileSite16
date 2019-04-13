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
                // They chose to examine crack in wall
                Context context = getApplicationContext();
                CharSequence text1 = "dead body head description";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("dead body head description");
            } else if (position == 1) {
                // They chose to punch wall
                Context context = getApplicationContext();
                CharSequence text1 = "dead body torso description";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("dead body torso description");
                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                inventory.setShiv(true);
                inventoryEntityDao.update(inventory);
            } else if (position == 2) {
                // They chose to punch wall
                Context context = getApplicationContext();
                CharSequence text1 = "dead body legs description";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text1, duration);
                toast.show();
                TextView text = (TextView) findViewById(R.id.additional_cell_text);
                text.setText("dead body legs description");
            } else if (position == 3) {
                // They chose to return to cell
                Intent cellIntent = new Intent(this, PlayerCell.class);

                // Start the new activity.
                startActivity(cellIntent);
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
        setContentView(R.layout.activity_cell_body);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
