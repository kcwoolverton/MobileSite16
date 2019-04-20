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

public class Scp extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public boolean hasId;

    public void onNextScpButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.scp_option_group);
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
                // They chose to go to open room 1
                TextView text = (TextView) findViewById(R.id.additional_scp_text);
                text.setText("The door is locked.");
            } else if (position == 1) {
                // They chose to open biography pen room
                Intent room1Intent = new Intent(this, ScpBiographyPenRoom.class);

                // Start the new activity.
                startActivity(room1Intent);
            } else if (position == 2) {
                // They chose to open bunny room
                Intent room2Intent = new Intent(this, ScpBunnyRoom.class);

                // Start the new activity.
                startActivity(room2Intent);
            } else if (position == 3) {
                // They chose to open cassie room
                Intent room3Intent = new Intent(this, ScpCassieRoom.class);

                // Start the new activity.
                startActivity(room3Intent);
            } else if (position == 4) {
                // They chose to open dream journal room
                Intent room4Intent = new Intent(this, ScpDreamJournalRoom.class);

                // Start the new activity.
                startActivity(room4Intent);
            } else if (position == 5) {
                // They chose to open room 6
                TextView text = (TextView) findViewById(R.id.additional_scp_text);
                text.setText("The door is locked.");
            } else if (position == 6) {
                // They chose to open little mister room
                Intent room4Intent = new Intent(this, ScpLittleMisterRoom.class);

                // Start the new activity.
                startActivity(room4Intent);
            } else if (position == 7) {
                // They chose to open nostalgic tissue box room
                Intent room4Intent = new Intent(this, ScpNostalgicTissueBoxRoom.class);

                // Start the new activity.
                startActivity(room4Intent);
            } else if (position == 8) {
                // They chose to open obsidian knife room
                Intent room4Intent = new Intent(this, ScpObsidianKnifeRoom.class);

                // Start the new activity.
                startActivity(room4Intent);
            } else if (position == 9) {
                // They chose to open office supplies man room
                Intent room4Intent = new Intent(this, ScpOfficeSuppliesManRoom.class);

                // Start the new activity.
                startActivity(room4Intent);
            } else if (position == 10) {
                // They chose to return to go to the stairwell
                Intent stairIntent = new Intent(this, ScpStairwell.class);

                // Start the new activity.
                startActivity(stairIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_scp_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foundation_employee_living_quarters);

        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);
        this.hasId = inventory.getFoundationEmployeeIdCard();
    }
}
