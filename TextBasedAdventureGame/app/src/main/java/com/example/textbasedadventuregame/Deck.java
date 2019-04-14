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

public class Deck extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextDeckButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.deck_option_group);
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
                // They chose to look out at the crowd
                TextView text = (TextView) findViewById(R.id.additional_deck_text);
                text.setText("crowd description");
            } else if (position == 1) {
                // They chose to look out at ocean
                TextView text = (TextView) findViewById(R.id.additional_deck_text);
                text.setText("ocean description");
            } else if (position == 2) {
                // They chose to jump off the edge, lol idiot
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                StatusEntity status = statusEntityDao.getAll().get(0);
                status.setDeathByOcean(true);
                statusEntityDao.update(status);

                Intent gameoverIntent = new Intent(this, GameOver.class);
                startActivity(gameoverIntent);
            } else if (position == 3) {
                // They chose to shoot someone in the crowd (but why?)
                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                InventoryEntity inventory = inventoryEntityDao.getAll().get(0);
                int ammo = inventory.getAmmo();

                if (ammo > 0) {
                    TextView text = (TextView) findViewById(R.id.additional_deck_text);
                    text.setText("shoot someone description");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_deck_text);
                    text.setText("The gun clicks when you pull the trigger. You must be out of ammo.");
                }
            } else if (position == 4) {
                // They chose to stab someone in the crowd (again, but why?)
                TextView text = (TextView) findViewById(R.id.additional_deck_text);
                text.setText("shiv someone description");
            } else if (position == 5) {
                // They chose to talk to someone
                TextView text = (TextView) findViewById(R.id.additional_deck_text);
                text.setText("talk to someone description");
            } else if (position == 6) {
                // They chose to search the crowd for the Site Director
                // They have now found the director
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                StatusEntity status = statusEntityDao.getAll().get(0);
                status.setFoundSiteDirector(true);
                statusEntityDao.update(status);

                TextView text = (TextView) findViewById(R.id.additional_deck_text);
                text.setText("you find the site director in crowd");

                // We shouldn't be able to do this again
                RadioButton searchForDirector = (RadioButton) group.getChildAt(6);
                searchForDirector.setVisibility(View.GONE);
            } else if (position == 7) {
                // They chose to use the knife on the site director
                // They have now knifed the director
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                StatusEntity status = statusEntityDao.getAll().get(0);
                status.setKnifedSiteDirector(true);
                statusEntityDao.update(status);

                TextView text = (TextView) findViewById(R.id.additional_deck_text);
                text.setText("knife site director");

                // We shouldn't be able to do this again
                RadioButton searchForDirector = (RadioButton) group.getChildAt(7);
                searchForDirector.setVisibility(View.GONE);
            } else if (position == 8) {
                // They chose to go upstairs
                // TODO
            } else if (position == 9) {
                // They chose to go downstairs
                Intent stairIntent = new Intent(this, CrewQuartersStairwell.class);

                // Start the new activity.
                startActivity(stairIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_deck_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
        InventoryEntity inventory = inventoryEntityDao.getAll().get(0);
        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        StatusEntity status = statusEntityDao.getAll().get(0);
        boolean knowsDirector = status.getKnowSiteDirector();
        boolean foundDirector = status.getFoundSiteDirector();
        boolean knifedDirector = status.getKnifedSiteDirector();
        boolean hasKnife = inventory.getObsidianKnife();
        boolean hasGun = inventory.getGun();
        boolean hasShiv = inventory.getShiv();

        RadioGroup group = (RadioGroup) findViewById(R.id.intro_cell_wall_option_group);
        RadioButton useGun = (RadioButton) group.getChildAt(3);
        RadioButton useShiv = (RadioButton) group.getChildAt(4);
        RadioButton searchForDirector = (RadioButton) group.getChildAt(6);
        RadioButton knifeSiteDirector = (RadioButton) group.getChildAt(7);

        // If we know to look for the director, but haven't yet, this should be an option
        if (knowsDirector && !foundDirector) {
            searchForDirector.setVisibility(View.VISIBLE);
        } else {
            searchForDirector.setVisibility(View.GONE);
        }

        // If we have found the site director, have the knife, and haven't knifed him, give the option to do so
        if (foundDirector && !knifedDirector && hasKnife) {
            knifeSiteDirector.setVisibility(View.VISIBLE);
        } else {
            knifeSiteDirector.setVisibility(View.GONE);
        }

        // If we have a gun you can try to shoot ppl
        if (hasGun) {
            useGun.setVisibility(View.VISIBLE);
        } else {
            useGun.setVisibility(View.GONE);
        }

        // If we have a shiv you can try to shiv ppl
        if (hasShiv) {
            useShiv.setVisibility(View.VISIBLE);
        } else {
            useShiv.setVisibility(View.GONE);
        }
    }
}
