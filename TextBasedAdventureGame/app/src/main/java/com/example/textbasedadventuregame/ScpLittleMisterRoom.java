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

public class ScpLittleMisterRoom extends AppCompatActivity {
    public static AppDatabase DBINSTANCE;

    boolean promisedHelpRobot;
    boolean attackedRobot;
    boolean subduedRobot;
    boolean tookRobot;
    boolean abandonedRobot;
    boolean COTBGRobot;

    public void onNextScpLittleMisterButtonClick(View view) {
        RadioGroup group = (RadioGroup) findViewById(R.id.scp_little_mister_option_group);
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
                // They chose to examine the little mister
                if (subduedRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("Robot is beaten up and only semi-conscious");
                } else if (attackedRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("Robot is glaring at you, and looks ready to defend itself");
                } else if (abandonedRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("Robot is sad");
                } else if (COTBGRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("Robot is glaring at you");
                } else if (promisedHelpRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("Robot is happy");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("Robot exists and looking at you curiously/warily");
                }
            } else if (position == 1) {
                // They chose to talk to the little mister
                if (attackedRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("The robot just groans.");
                } else if (abandonedRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("\"You aren't going to help me. Just leave me be.\"");
                } else if (COTBGRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("\"I won't ever go back to them. Go away.\"");
                } else if (promisedHelpRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("\"Thank you so much for helping me. Help me up and let's get out of here!\"");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("Robot tells you its story and asks for you to take it away from both the SCP and the COTBG");
                }
            } else if (position == 2) {
                // They chose to abandon the robot to its fate
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                status.setAbandonedRobot(true);
                statusEntityDao.update(status);
                abandonedRobot = true;

                RadioButton button1 = (RadioButton) group.getChildAt(2);
                RadioButton button2 = (RadioButton) group.getChildAt(3);
                RadioButton button3 = (RadioButton) group.getChildAt(4);
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);

                TextView mainText = findViewById(R.id.additional_scp_little_mister_text);
                mainText.setText("abandon robot text");
            } else if (position == 3) {
                // They chose to talk to promise to help the little mister
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                status.setPromisedToHelpRobot(true);
                statusEntityDao.update(status);
                promisedHelpRobot = true;

                RadioButton button1 = (RadioButton) group.getChildAt(2);
                RadioButton button2 = (RadioButton) group.getChildAt(3);
                RadioButton button3 = (RadioButton) group.getChildAt(4);
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);

                TextView mainText = findViewById(R.id.additional_scp_little_mister_text);
                mainText.setText("promise to help robot text");
            } else if (position == 4) {
                // They chose to tell the little mister that they are going to take it to the COTBG
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                status.setCOTBGRobot(true);
                statusEntityDao.update(status);
                COTBGRobot = true;

                RadioButton button1 = (RadioButton) group.getChildAt(2);
                RadioButton button2 = (RadioButton) group.getChildAt(3);
                RadioButton button3 = (RadioButton) group.getChildAt(4);
                button1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button3.setVisibility(View.GONE);

                TextView mainText = findViewById(R.id.additional_scp_little_mister_text);
                mainText.setText("COBTGrobot text");
            } else if (position == 5) {
                // They chose to subdue the little mister using force
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                status.setAttackedRobot(true);

                InventoryEntityDao inventoryEntityDao = DBINSTANCE.inventoryEntityDao();
                List<InventoryEntity> inventoryList = inventoryEntityDao.getAll();
                InventoryEntity inventory = inventoryList.get(0);
                boolean hasScalpel = inventory.getScalpel();
                boolean hasShiv = inventory.getShiv();
                boolean hasGun = inventory.getGun();
                int ammo = inventory.getAmmo();
                boolean hasArmor = inventory.getArmor();

                attackedRobot = true;
                subduedRobot = false;

                String promisedRobotText = "\"Why? You promised to help me...\"";
                String attackText = "";
                String finalText = "";

                if (hasScalpel) {
                    attackText = "You pull the scalpel out, and quickly slice one of the cords that is sticking out of the robot. It's eyes begin to flicker.";
                    subduedRobot = true;
                } else if (hasShiv) {
                    attackText = "You pull the shiv out, and quickly stab the robot in its chest. It's eyes begin to flicker.";
                    subduedRobot = true;
                } else if (hasGun) {
                    if (ammo > 0) {
                        attackText = "You pull the gun out, and pull the trigger. The bullet shoots through the robot's chest. It's eyes begin to flicker.";
                        subduedRobot = true;
                    } else {
                        attackText = "You pull the gun out, and pull the trigger. However, the trigger clicks and nothing happens. You must be out of ammo. The robot takes a breath and laughs in relief before glaring at you and positioning itself defensively.";
                    }
                } else {
                    attackText = "You attempt to punch the robot, but even in its weakened state it is able to block your attacks. It glares at you, and positions itself defensively.";
                }

                status.setSubduedRobot(subduedRobot);
                statusEntityDao.update(status);

                if (subduedRobot) {
                    RadioButton button2 = (RadioButton) group.getChildAt(3);
                    RadioButton button3 = (RadioButton) group.getChildAt(5);
                    button2.setVisibility(View.GONE);
                    button3.setVisibility(View.GONE);
                    if (promisedHelpRobot) {
                        finalText = attackText + " " + promisedRobotText + " The robots eyes finally go dark.";
                    } else {
                        finalText = attackText + " \"Why...\" Finally, its eyes go dark.";
                    }
                    TextView mainText = findViewById(R.id.additional_scp_little_mister_text);
                    mainText.setText(finalText);
                } else {
                    RadioButton button2 = (RadioButton) group.getChildAt(3);
                    button2.setVisibility(View.GONE);
                    if (promisedHelpRobot) {
                        finalText = attackText + " " + promisedRobotText + " It looks betrayed.";
                    } else {
                        finalText = attackText + " \"Why would you do that? What have I ever done to you?\"";
                    }
                    TextView mainText = findViewById(R.id.additional_scp_little_mister_text);
                    mainText.setText(finalText);
                }
            } else if (position == 6) {
                StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
                List<StatusEntity> statusList = statusEntityDao.getAll();
                StatusEntity status = statusList.get(0);
                if (promisedHelpRobot && !attackedRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("The robot smiles as you pick him up. \"Let's get out of here!\"");
                    status.setTookRobot(true);
                    statusEntityDao.update(status);
                } else if (subduedRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("You pick up the robot's inert form and take it with you.");
                    status.setTookRobot(true);
                    statusEntityDao.update(status);
                } else if (COTBGRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("\"Stay away from me! I'll never go back to the Church!\" The robot bats your hands away.");
                } else if (abandonedRobot) {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("\"I thought you were just going to leave me here. Just go away. I don't trust you.\" The robot bats your hands away.");
                } else {
                    TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                    text.setText("\"Wait, are you going to help me? Promise that you'll help me.\" The robot bats your hands away and looks up at you expectantly.");
                }
                TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                text.setText("about the obsidian knife");
            } else if (position == 7) {
                // They chose to return to the scp containment floor
                Intent quartersIntent = new Intent(this, Scp.class);

                // Start the new activity.
                startActivity(quartersIntent);
            } else {
                // panic?
                TextView text = (TextView) findViewById(R.id.additional_scp_little_mister_text);
                text.setText("panic?");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scp_little_mister_room);
        DBINSTANCE = AppDatabase.getDatabase(getApplicationContext());

        StatusEntityDao statusEntityDao = DBINSTANCE.statusEntityDao();
        List<StatusEntity> statusList = statusEntityDao.getAll();
        StatusEntity status = statusList.get(0);
        promisedHelpRobot = status.getPromisedToHelpRobot();
        attackedRobot = status.getAttackedRobot();
        subduedRobot = status.getSubduedRobot();
        tookRobot = status.getTookRobot();
        abandonedRobot = status.getAbandonedRobot();
        COTBGRobot = status.getCOTBGRobot();

        if (tookRobot) {
            RadioGroup group = (RadioGroup) findViewById(R.id.scp_little_mister_option_group);
            RadioButton button_1 = (RadioButton) group.getChildAt(0);
            RadioButton button0 = (RadioButton) group.getChildAt(1);
            RadioButton button1 = (RadioButton) group.getChildAt(2);
            RadioButton button2 = (RadioButton) group.getChildAt(3);
            RadioButton button3 = (RadioButton) group.getChildAt(4);
            RadioButton button4 = (RadioButton) group.getChildAt(5);
            RadioButton button5 = (RadioButton) group.getChildAt(6);
            button_1.setVisibility(View.GONE);
            button0.setVisibility(View.GONE);
            button1.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            button4.setVisibility(View.GONE);
            button5.setVisibility(View.GONE);
            TextView mainText = findViewById(R.id.textView3);
            mainText.setText("The room is empty");
        }

        if (promisedHelpRobot || abandonedRobot || COTBGRobot) {
            // Player already has already promised the robot something
            RadioGroup group = (RadioGroup) findViewById(R.id.scp_little_mister_option_group);
            RadioButton button1 = (RadioButton) group.getChildAt(2);
            RadioButton button2 = (RadioButton) group.getChildAt(3);
            RadioButton button3 = (RadioButton) group.getChildAt(4);
            button1.setVisibility(View.GONE);
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
        }

        if (subduedRobot) {
            // Player already has successfully attacked the robot
            RadioGroup group = (RadioGroup) findViewById(R.id.scp_little_mister_option_group);
            RadioButton button2 = (RadioButton) group.getChildAt(2);
            RadioButton button3 = (RadioButton) group.getChildAt(3);
            RadioButton button4 = (RadioButton) group.getChildAt(4);
            RadioButton button5 = (RadioButton) group.getChildAt(5);
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            button4.setVisibility(View.GONE);
            button5.setVisibility(View.GONE);
        }

        if (attackedRobot) {
            RadioGroup group = (RadioGroup) findViewById(R.id.scp_little_mister_option_group);
            RadioButton button3 = (RadioButton) group.getChildAt(3);
            button3.setVisibility(View.GONE);
        }
    }
}
