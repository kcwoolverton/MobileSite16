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

public class ScpNostalgicTissueBoxRoom extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextScpNostalgicTissueBoxButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.scp_nostalgic_tissue_option_group);
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
                // They chose to examine the tissue box
                TextView text = (TextView) findViewById(R.id.additional_nostalgic_tissue_box_text);
                text.setText("Look at the tissue box");
            } else if (position == 1) {
                // They chose to use the tissue box
                TextView text = (TextView) findViewById(R.id.additional_nostalgic_tissue_box_text);
                text.setText("They use it");
            } else if (position == 2) {
                // They chose to take the tissue box
                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                inventory.setTissueBox(true);
                inventoryEntityDao.update(inventory);

                TextView text = (TextView) findViewById(R.id.additional_nostalgic_tissue_box_text);
                text.setText("They take it");

                RadioButton button1 = (RadioButton) group.getChildAt(0);
                RadioButton button2 = (RadioButton) group.getChildAt(1);
                RadioButton button3 = (RadioButton) group.getChildAt(2);
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);

                TextView mainText = findViewById(R.id.textView3);
                mainText.setText("The room is empty");
            } else if (position == 3) {
                // They chose to return to the scp containment floor
                Intent quartersIntent = new Intent(this, Scp.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_nostalgic_tissue_box_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scp_nostalgic_tissue_box_room);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        boolean hasTissueBox = inventory.getTissueBox();

        if (hasTissueBox) {
            // Player already has the tissue box
            RadioGroup group = (RadioGroup) findViewById(R.id.scp_nostalgic_tissue_option_group);
            RadioButton button1 = (RadioButton) group.getChildAt(0);
            RadioButton button2 = (RadioButton) group.getChildAt(1);
            RadioButton button3 = (RadioButton) group.getChildAt(2);
            button1.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            TextView mainText = findViewById(R.id.textView3);
            mainText.setText("The room is empty");
        } else {
            // Player has the cheese but has not fed Josie.
            RadioGroup group = (RadioGroup) findViewById(R.id.scp_nostalgic_tissue_option_group);
            RadioButton button1 = (RadioButton) group.getChildAt(0);
            RadioButton button2 = (RadioButton) group.getChildAt(1);
            RadioButton button3 = (RadioButton) group.getChildAt(2);
            button1.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
            button3.setVisibility(View.VISIBLE);
        }
    }
}
