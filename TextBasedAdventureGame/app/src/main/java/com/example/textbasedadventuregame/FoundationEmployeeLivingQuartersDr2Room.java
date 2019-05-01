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

    public void onNextFoundationEmployeesDr2ButtonClick(View view) {

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
        InventoryEntity inventory = inventoryList.get(0);

        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);

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
                text.setText("Clothes are messily spilling out of the closet. It looks like " +
                        "they're covered in cat hair.");
            } else if (position == 1) {
                // They chose to go through the bathroom
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("On the bathroom's sink is a toothbrush, a retainer, and some toothpaste. " +
                        "In the shower is an abundance of products.");
            } else if (position == 2) {
                // They chose to go through the desk
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("A laptop sits on the desk, a game of \"Stardew Valley\" paused " +
                        "mid-conversation.");
            } else if (position == 3) {
                // They chose to look at the papers on the ground
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("The paper on the ground is the SCP documentation on \"Josie\", a cat " +
                        "that somehow exists only with its upper half. The document notes that Josie " +
                        "loves cheese.");
            } else if (position == 4) {
                // They chose to look at Josie.
                boolean hasFedJosie = status.getFedJosie();
                boolean tookJosieIdCard = status.getTookJosieIdCard();

                if (hasFedJosie && tookJosieIdCard) {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie is purring happily as she nibbles on the cheese you " +
                            "gave her.");
                } else if (hasFedJosie) {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie is purring happily as she nibbles on the cheese you " +
                            "gave her. An ID card sits beside her.");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie is sitting next to what looks like an ID card. She's " +
                            "swaying slightly, and you imagine if she had a tail it would be " +
                            "flicking back and forth.");
                }
            } else if (position == 5) {
                // They chose to approach Josie.
                boolean hasFedJosie = status.getFedJosie();
                boolean tookJosieIdCard = status.getTookJosieIdCard();

                if (hasFedJosie) {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie purrs as you pet her. You slip the ID out from under her, " +
                            "and take it with you.");

                    inventory.setFoundationEmployeeIdCard(true);
                    inventoryEntityDao.update(inventory);
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                    text.setText("Josie growls and claws at your hand as you try to get the ID card. " +
                            "You retreat back to safety.");
                }
            } else if (position == 6) {
                // They chose to feed josie some cheese
                TextView text = (TextView) findViewById(R.id.additional_quarters_dr2_text);
                text.setText("You tentatively reach out a cheese filled hand towards Josie. " +
                        "She stands up and picks it up in her mouth, and then sits down " +
                        "and begins nibbling on the cheese.");

                // Remove the cheese from player's inventory.
                inventory.setCheese(false);
                inventoryEntityDao.update(inventory);

                // The player has now fed Josie.
                status.setFedJosie(true);
                statusEntityDao.update(status);

                // Player already fed Josie.
                RadioButton button = (RadioButton) group.getChildAt(6);
                button.setVisibility(View.GONE);
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
