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

public class CrewQuartersGalley extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCrewQuartersGalleyButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.crew_quarters_galley_option_group);
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
                // They chose to search through the tables
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                text.setText("tables");
            } else if (position == 1) {
                // They chose to go through the pantry
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                boolean tookSupplies = status.getTookSupplies();
                boolean needSupplies = status.getNeedSupplies();

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);

                if (needSupplies) {
                    if (tookSupplies) {
                        TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                        text.setText("pantry description with fewer supplies");
                    } else {
                        TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                        text.setText("pantry description, you take some of the supplies");
                    }
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                    text.setText("pantry description with all supplies");
                }

            } else if (position == 2) {
                // They chose to go through the refrigerator
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                boolean tookCheese = status.getTookCheese();

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);

                if (tookCheese) {
                    TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                    text.setText("fridge without cheese");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                    text.setText("fridge with cheese");
                    inventory.setCheese(true);
                    inventoryEntityDao.update(inventory);
                    status.setTookCheese(true);
                    statusEntityDao.update(status);
                }
            } else if (position == 3) {
                // They chose to go through the cabinets
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                text.setText("cabinets");
            } else if (position == 4) {
                // They chose to go through the countertops
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                text.setText("countertops");
            } else if (position == 5) {
                // They chose to look at the buffet area
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                text.setText("buffet area");
            } else if (position == 6) {
                // They chose to return to the crew quarters
                Intent quartersIntent = new Intent(this, CrewQuarters.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_crew_quarters_galley_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crew_quarters_galley);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());
    }
}
