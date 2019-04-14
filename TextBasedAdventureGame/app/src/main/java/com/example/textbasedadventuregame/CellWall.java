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

public class CellWall extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCellButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_wall_option_group);
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
                TextView text = (TextView) findViewById(R.id.additional_cell_wall_text);
                text.setText("crack in wall description");
            } else if (position == 1) {
                // They chose to punch wall
                TextView text = (TextView) findViewById(R.id.additional_cell_wall_text);
                text.setText("punched wall");
            } else if (position == 2) {
                // Leave through the already broken wall.
                Intent prisonIntent = new Intent(this, Prison.class);

                // Start the new activity.
                startActivity(prisonIntent);
            } else if (position == 3) {
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                status.setBrokeCellWall(true);
                statusEntityDao.update(status);

                // Tear down that wall!
                TextView text = (TextView) findViewById(R.id.additional_cell_wall_text);
                text.setText("You lift the pipe into the air and bring it down on the crack that your roommate created. The crack grows in size. You hit it again, and the glass shatters, leaving a gap large enough for you to fit through.");
                RadioButton button = (RadioButton) group.getChildAt(2);
                button.setVisibility(View.VISIBLE);
                RadioButton originalButton = (RadioButton) group.getChildAt(3);
                originalButton.setVisibility(View.GONE);

            } else if (position == 4) {
                // They chose to return to cell
                Intent cellIntent = new Intent(this, PlayerCell.class);

                // Start the new activity.
                startActivity(cellIntent);
            }
            else {
                    // panic?
                    TextView text = (TextView) findViewById(R.id.additional_cell_wall_text);
                    text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_wall);

        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        boolean hasPipe = inventory.getPipe();

        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);
        boolean smashedWall = status.getBrokeCellWall();

        if (smashedWall) {
            // Player already broke the wall.
            RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_wall_option_group);
            RadioButton button = (RadioButton) group.getChildAt(3);
            button.setVisibility(View.GONE);

            RadioButton secondButton = (RadioButton) group.getChildAt(2);
            secondButton.setVisibility(View.VISIBLE);
        } else if (!hasPipe) {
            // Player does not have the pipe and has not broken the wall.
            RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_wall_option_group);
            RadioButton button = (RadioButton) group.getChildAt(3);
            button.setVisibility(View.GONE);

            RadioButton secondButton = (RadioButton) group.getChildAt(2);
            button.setVisibility(View.GONE);
        } else {
            // Player has the pipe but has not broken the wall.
            RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_wall_option_group);
            RadioButton button = (RadioButton) group.getChildAt(3);
            button.setVisibility(View.VISIBLE);

            RadioButton secondButton = (RadioButton) group.getChildAt(2);
            secondButton.setVisibility(View.GONE);
        }
    }
}
