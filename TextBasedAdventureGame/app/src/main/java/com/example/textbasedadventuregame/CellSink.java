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

public class CellSink extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCellButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_sink_option_group);
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
                // They chose look at the mirror
                Context context = getApplicationContext();
                CharSequence text1 = "existential crisis";
                TextView text = (TextView) findViewById(R.id.additional_cell_sink_text);
                text.setText("existential crisis");
            } else if (position == 1) {
                // They chose to turn on the faucet.
                Context context = getApplicationContext();
                TextView text = (TextView) findViewById(R.id.additional_cell_sink_text);

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                boolean checkPipe = inventory.getPipe();

                if (checkPipe) {
                    text.setText("broken faucet on");
                }

                else {
                    text.setText("regular faucet on");
                }
            } else if (position == 2) {
                // They chose to take pipe
                Context context = getApplicationContext();
                CharSequence text1 = "take pipe";
                TextView text = (TextView) findViewById(R.id.additional_cell_sink_text);
                text.setText("You took the pipe.");

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                inventory.setPipe(true);
                inventoryEntityDao.update(inventory);

                RadioButton button = (RadioButton) group.getChildAt(2);
                button.setVisibility(View.GONE);
            } else if (position == 3) {
                // They chose to go back to the cell
                Intent cellIntent = new Intent(this, PlayerCell.class);

                // Start the new activity.
                startActivity(cellIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_cell_sink_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_sink);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        boolean hasPipe = inventory.getPipe();

        if (hasPipe) {
            RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_sink_option_group);
            RadioButton button = (RadioButton) group.getChildAt(2);
            button.setVisibility(View.GONE);
        } else {
            RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_sink_option_group);
            RadioButton button = (RadioButton) group.getChildAt(2);
            button.setVisibility(View.VISIBLE);
        }
    }
}
