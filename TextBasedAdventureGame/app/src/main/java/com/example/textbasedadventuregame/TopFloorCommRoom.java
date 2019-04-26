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

public class TopFloorCommRoom extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextCommRoomButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.top_floor_comm_room_group);
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
                TextView text = (TextView) findViewById(R.id.additional_comm_room_text);
                text.setText("GPS information description.");

                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                status.setHasGpsInformation(true);

                RadioButton button = (RadioButton) group.getChildAt(0);
                button.setVisibility(View.GONE);

            } else if (position == 1) {
                // They chose to return to the top floor.
                Intent topFloorIntent = new Intent(this, TopFloor.class);

                // Start the new activity.
                startActivity(topFloorIntent);

            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_comm_room_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_floor_captain_quarters);

        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);
        boolean hasGpsInformation = status.getHasGpsInformation();

        if (hasGpsInformation) {
            RadioGroup group = (RadioGroup) findViewById(R.id.top_floor_comm_room_group);
            RadioButton button = (RadioButton) group.getChildAt(0);
            button.setVisibility(View.GONE);
        } else {
            RadioGroup group = (RadioGroup) findViewById(R.id.top_floor_comm_room_group);
            RadioButton button = (RadioButton) group.getChildAt(0);
            button.setVisibility(View.VISIBLE);
        }
    }
}