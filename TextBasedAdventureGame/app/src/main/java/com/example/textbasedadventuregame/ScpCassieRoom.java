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

public class ScpCassieRoom extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    public void onNextScpCassieButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.scp_cassie_option_group);
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
                // They chose to examine cassie
                TextView text = (TextView) findViewById(R.id.additional_scp_cassie_text);
                text.setText("cassie description");
            } else if (position == 1) {
                // They chose to talk to cassie
                TextView text = (TextView) findViewById(R.id.additional_scp_cassie_text);
                text.setText("talk to cassie by writing on the sheet");
            } else if (position == 2) {
                // They chose to ask the bunny what it is doing here
                TextView text = (TextView) findViewById(R.id.additional_scp_cassie_text);
                text.setText("cassie talks about its backstory");

                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                status.setKnowsBunny(true);

                // Player now knows about the bunny
                RadioButton button = (RadioButton) group.getChildAt(3);
                button.setVisibility(View.VISIBLE);
            }  else if (position == 3) {
                // They chose to ask the bunny to manifest on object
                if (Math.random() < 0.7) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_cassie_text);
                    text.setText("bunny manifests a carrot");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_scp_cassie_text);
                    text.setText("bunny manifests an artwork");
                }
            } else if (position == 2) {
                // They chose to return to the scp containment floor
                Intent quartersIntent = new Intent(this, Scp.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_scp_cassie_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scp_cassie_room);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);
        boolean knowsBunny = status.getKnowsBunny();

        if (knowsBunny) {
            // Player knows about the bunny
            RadioGroup group = (RadioGroup) findViewById(R.id.scp_cassie_option_group);
            RadioButton button = (RadioButton) group.getChildAt(3);
            button.setVisibility(View.VISIBLE);
        } else {
            // Player doesn't know about the bunny
            RadioGroup group = (RadioGroup) findViewById(R.id.scp_cassie_option_group);
            RadioButton button = (RadioButton) group.getChildAt(3);
            button.setVisibility(View.GONE);
        }
    }
}
