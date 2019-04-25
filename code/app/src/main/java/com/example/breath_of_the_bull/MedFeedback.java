//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: MedFeedback.java
//Description: This file manages user input when meditation session feedback is requested
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MedFeedback extends AppCompatActivity {

    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_feedback);

        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.back_feedback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });

        String[] spinner_choices = {"I loved it", "I liked it", "It was ok", "I didn't like it", "I hated it"};

        //Several drop down menus to receive feedback in a way that avoids user error
        final Spinner position_spinner = (Spinner) findViewById(R.id.spinner_pos);
        ArrayAdapter<String> position_adapter = new ArrayAdapter<String>(MedFeedback.this,
                android.R.layout.simple_expandable_list_item_1, spinner_choices);
        position_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        position_spinner.setAdapter(position_adapter);

        final Spinner breathing_spinner = (Spinner) findViewById(R.id.spinner_breath);
        ArrayAdapter<String> breathing_adapter = new ArrayAdapter<String>(MedFeedback.this,
                android.R.layout.simple_expandable_list_item_1, spinner_choices);
        breathing_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breathing_spinner.setAdapter(breathing_adapter);

        final Spinner length_spinner = (Spinner) findViewById(R.id.spinner_length);
        ArrayAdapter<String> length_adapter = new ArrayAdapter<String>(MedFeedback.this,
                android.R.layout.simple_expandable_list_item_1, spinner_choices);
        length_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        length_spinner.setAdapter(length_adapter);

        final Spinner sound_spinner = (Spinner) findViewById(R.id.spinner_sound);
        ArrayAdapter<String> sound_adapter = new ArrayAdapter<String>(MedFeedback.this,
                android.R.layout.simple_expandable_list_item_1, spinner_choices);
        sound_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sound_spinner.setAdapter(sound_adapter);

        FloatingActionButton submit = (FloatingActionButton) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pos_str = position_spinner.getSelectedItem().toString();
                String breath_str = breathing_spinner.getSelectedItem().toString();
                String length_str = length_spinner.getSelectedItem().toString();
                String sound_str = sound_spinner.getSelectedItem().toString();
                Entity updated_user = ManageUserInput.updateUser(pos_str, breath_str, length_str, sound_str);
                saveData(updated_user);
            }
        });

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    //Returns user to Home screen
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //Updates user data with meditation session feedback and returns user to Home screen
    public void saveData(Entity user) {

        userViewModel.update(user);
        Toast.makeText(getApplicationContext(), "Preferences Have Been Updated", Toast.LENGTH_LONG).show();
        openMain();
    }

}
