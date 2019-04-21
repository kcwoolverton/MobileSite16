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

public class CaptainsComputer extends AppCompatActivity {

    public static AppDatabase DBINSTANCE;

    public void onNextCaptainsComputerButtonClick(View view) {
        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);

        RadioGroup group = (RadioGroup) findViewById(R.id.captains_computer_options_group);
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
                // They chose to aid the SCP Foundation.
                status.setSidedWithFoundation(true);
                status.setChoseASide(true);
                hideButtons();

            } else if (position == 1) {
                // They chose to side with the Church of the Broken God.
                status.setSidedWithChurch(true);
                status.setChoseASide(true);
                hideButtons();

            } else if (position == 2) {
                // They chose to side with neither faction.
                status.setSidedWithNobody(true);
                status.setChoseASide(true);
                hideButtons();

            } else if (position == 3) {
                // They chose to go back to the captain's quarters.
                Intent captainQuartersIntent = new Intent(this, TopFloorCaptainQuarters.class);

                // Start the new activity.
                startActivity(captainQuartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_captains_computer_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captains_computer);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);
        boolean choseASide = status.getChoseASide();

        if (choseASide) {
            hideButtons();

        } else {
            RadioGroup group = (RadioGroup) findViewById(R.id.captains_computer_options_group);
            RadioButton button = (RadioButton) group.getChildAt(0);
            button.setVisibility(View.VISIBLE);

            RadioButton secondButton = (RadioButton) group.getChildAt(1);
            secondButton.setVisibility(View.VISIBLE);

            RadioButton thirdButton = (RadioButton) group.getChildAt(2);
            thirdButton.setVisibility(View.VISIBLE);

        }
    }

    private void hideButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.captains_computer_options_group);
        RadioButton button = (RadioButton) group.getChildAt(0);
        button.setVisibility(View.GONE);

        RadioButton secondButton = (RadioButton) group.getChildAt(1);
        secondButton.setVisibility(View.GONE);

        RadioButton thirdButton = (RadioButton) group.getChildAt(2);
        thirdButton.setVisibility(View.GONE);
    }
}
