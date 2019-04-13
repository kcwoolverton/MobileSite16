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

public class PrisonMedbay extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextPrisonMedbayButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.prison_medbay_option_group);
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
                // They chose to search through cabinets
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                boolean tookMedkit = status.getTookPrisonMedkit();

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);

                if (tookMedkit) {
                    TextView text = (TextView) findViewById(R.id.additional_prison_medbay_text);
                    text.setText("cabinets description without medkit");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_prison_medbay_text);
                    text.setText("cabinets description with medkit");
                    inventory.setMedkit(true);
                    inventoryEntityDao.update(inventory);
                }
            } else if (position == 1) {
                // They chose to go through the papers
                TextView text = (TextView) findViewById(R.id.additional_prison_medbay_text);
                text.setText("papers");
            } else if (position == 2) {
                // They chose to go through the desk
                TextView text = (TextView) findViewById(R.id.additional_prison_medbay_text);
                text.setText("desk");
            } else if (position == 3) {
                // They chose to go through the labcoat
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                boolean tookPrisonFloorIdCard = status.getTookPrisonIdCard();

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);

                if (tookPrisonFloorIdCard) {
                    TextView text = (TextView) findViewById(R.id.additional_prison_medbay_text);
                    text.setText("labcoat without id card");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_prison_medbay_text);
                    text.setText("labcoat with id card");
                    inventory.setPrisonFloorIdCard(true);
                    inventoryEntityDao.update(inventory);
                }
            } else if (position == 4) {
                // They chose to go through the medical instruments
                TextView text = (TextView) findViewById(R.id.additional_prison_medbay_text);

                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                boolean tookScalpel = status.getTookPrisonMedbayScalpel();

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);

                if (tookScalpel) {
                    text.setText("medical instruments without scalpel");
                } else {
                    text.setText("medical instruments with scalpel");
                    inventory.setScalpel(true);
                    inventoryEntityDao.update(inventory);
                }
            } else if (position == 5) {
                // They chose to return to the cells
                Intent prisonIntent = new Intent(this, Prison.class);

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
        setContentView(R.layout.activity_prison_medbay);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
