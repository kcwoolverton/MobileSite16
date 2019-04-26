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
                TextView text = (TextView) findViewById(R.id.additional_cell_sink_text);
                text.setText("You look up in the mirror and lock eyes with your reflection. " +
                        "Is that really you? God, how long have you been in this place? " +
                        "Your eyes are sunken in and your hair is unkempt. You can barely recognize yourself.");
            } else if (position == 1) {
                // They chose to turn on the faucet.
                Context context = getApplicationContext();
                TextView text = (TextView) findViewById(R.id.additional_cell_sink_text);

                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                boolean tookPipe = status.getTookCellSinkPipe();

                if (tookPipe) {
                    text.setText("Water silently pours from the faucet and then down the drain. " +
                            "From there, it spills out on to the floor from the break in the pipe.");
                }

                else {
                    text.setText("Water silently pours out of the faucet and down the drain.");
                }
            } else if (position == 2) {
                // They chose to take pipe
                TextView text = (TextView) findViewById(R.id.additional_cell_sink_text);
                text.setText("Noticing how loose the bolts holding the pipe to the sink look, you " +
                        "slowly turn them by hand until they come off. Once they're all off, " +
                        "you are able to take the pipe from the wall.");

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
