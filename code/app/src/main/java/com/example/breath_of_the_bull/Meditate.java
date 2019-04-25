//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: Meditate.java
//Description: This file manages the meditation session screen
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Meditate extends AppCompatActivity {

    private TextView pos_text;
    private TextView breath_text;
    private TextView sound_text;
    private TextView length_text;

    private CountDownTimer countDownTimer;
    private long time_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditate);

        //generate parameters and retrieve corresponding messages
        String[] params = ManageUserInput.generateSessionParams();
        String pos_msg = ManageUserInput.getPositionMessage(params[0]);
        String breath_msg = ManageUserInput.getBreathingMessage(params[1]);
        time_left = ManageUserInput.getLengthMessage(params[2]);
        String sound_msg = ManageUserInput.getSoundMessage(params[3]);

        //attach variables to text views and populate fields
        pos_text = (TextView) findViewById(R.id.positionText);
        pos_text.setText(pos_msg);
        breath_text = (TextView) findViewById(R.id.breathingText);
        breath_text.setText(breath_msg);
        sound_text = (TextView) findViewById(R.id.soundText);
        sound_text.setText(sound_msg);
        length_text = (TextView) findViewById(R.id.lengthTimer);
        startTimer();

        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.back_med);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMedFeedback();
            }
        });
    }

    public void openMedFeedback() {
        Intent intent = new Intent(this, MedFeedback.class);
        startActivity(intent);
    }

    //----------------------------------------------------------------------------
    //
    //Function: startTimer()
    //
    //Parameters:
    //None
    //
    //Pre-condition: Page is loaded and duration session parameter is used as start time
    //Post-condition: Initialize countdown timer at length parameter
    //-----------------------------------------------------------------------------
    public void startTimer() {
        countDownTimer = new CountDownTimer(time_left, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_left = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                openMedFeedback();
            }
        }.start();
    }

    //--------------------------------------------------------------------------------
    //
    //Function: updateTimer
    //
    //Parameters:
    //None
    //
    //Pre-condition: request to update timer is received
    //Post-condition: update count down timer text field
    //---------------------------------------------------------------------------------
    public void updateTimer() {
        int minutes = (int) time_left / 60000;
        int seconds = (int) time_left % 60000 / 1000;
        String time_left_str;

        time_left_str = "" + minutes;
        time_left_str += ":";
        if (seconds < 10) {
            time_left_str += "0";
        }

        time_left_str += seconds;
        length_text.setText(time_left_str);
    }
}
