//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: Login.java
//Description: This file controls the login functionality of the application. It manages
//things like user verification and requests for new accounts.
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

//Login screen for application. Allows user to input credentials or create new account
public class Login extends AppCompatActivity {

    private UserViewModel userViewModel;
    private static final int NEW_USER_ACTIVITY_REQUEST_CODE = 1;

    private EditText username;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username_login);
        password = findViewById(R.id.password_login);

        FloatingActionButton login = (FloatingActionButton) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMain();
            }
        });

        Button new_user = (Button) findViewById(R.id.new_user);
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewUser();
            }
        });

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }

    //-------------------------------------------------------------------------------------
    //
    //Function: openMain()
    //
    //Parameters:
    //None
    //
    //Pre-condition: User inputs username and password, then selects submit button.
    //Post-condition: Data is retrieved from database and user validation is requested.
    //-------------------------------------------------------------------------------------
    public void openMain() {

        //Retrieves user entity based off of username given
        userViewModel.getUser(username.getText().toString(), password.getText().toString());

        //Small pause in  order to retrieve information from database, then request login validation
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Entity user = userViewModel.getCurrUser();
                validateLogin(user);
            }
        }, 500);


    }

    //------------------------------------------------------------------------------------
    //
    //Function: validateLogin()
    //
    //Parameters:
    //input Entity; user database object
    //
    //Pre-condition: Validation requested after user inputs information and data is retrieved
    //Post-condition: Validates if given password matches password stored on database for user
    //and either allows or denies access to application
    //-------------------------------------------------------------------------------------
    public void validateLogin(Entity user) {

        //Case for if user cannot be found on database
        if (user == null) {
            Toast.makeText(getApplicationContext(), "Error: Invalid Username", Toast.LENGTH_LONG).show();
        //If given password matches password on database, allow access
        } else if (password.getText().toString().equals(user.getPassword())) {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
            ManageUserInput m = new ManageUserInput();
            m.setUser(user);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        //If passwords do not match, deny access
        } else {
            Toast.makeText(getApplicationContext(), "Error: Invalid Password", Toast.LENGTH_LONG).show();
        }
    }

    //Begin activity for creating new user when create account button is selected
    public void openNewUser() {
        Intent intent = new Intent(this, NewUser.class);
        startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
    }

    //---------------------------------------------------------------------------------
    //
    //Function: onActivityResult()
    //
    //Parameters:
    //input int; request code for adding user to database
    //input int; result code to verify result
    //input Intent; data obtained from new user screen
    //
    //Pre-condition: New user activity is completed and username/password data input by
    // the user is obtained
    //Post-condition: Save new user to database once information is retrieved
    //----------------------------------------------------------------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Initial values for meditation parameters
        HashMap<String, Integer> med_position_info = new HashMap<>();
        med_position_info.put("Chair", 3);
        med_position_info.put("CrossLegged", 0);
        med_position_info.put("Burmese", 4);
        med_position_info.put("HandsOnKnees", 1);
        med_position_info.put("HandsInLap", 2);
        HashMap<String, Integer> med_breathing_info = new HashMap<>();
        med_breathing_info.put("DeepSlow", 4);
        med_breathing_info.put("DeepQuick", 3);
        med_breathing_info.put("UpperSlow", 2);
        med_breathing_info.put("UpperQuick", 1);
        med_breathing_info.put("Fire", 0);
        HashMap<String, Integer> med_length_info = new HashMap<>();
        med_length_info.put("10min", 0);
        med_length_info.put("15min", 3);
        med_length_info.put("20min", 4);
        med_length_info.put("25min", 2);
        med_length_info.put("30min", 1);
        HashMap<String, Integer> med_sound_info = new HashMap<>();
        med_sound_info.put("birds", 0);
        med_sound_info.put("rain", 3);
        med_sound_info.put("wind", 1);
        med_sound_info.put("bells", 4);
        med_sound_info.put("none", 2);

        //Convert maps to json-formatted strings
        JSONObject json_pos = new JSONObject(med_position_info);
        JSONObject json_breath = new JSONObject(med_breathing_info);
        JSONObject json_length = new JSONObject(med_length_info);
        JSONObject json_sound = new JSONObject(med_sound_info);
        String str_pos = json_pos.toString();
        String str_breath = json_breath.toString();
        String str_length = json_length.toString();
        String str_sound = json_sound.toString();

        //If request was successful, save new user to database
        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            final String user_id = UUID.randomUUID().toString();
            Entity user = new Entity(user_id, data.getStringExtra(NewUser.USERNAME_ADDED),
                                     data.getStringExtra(NewUser.PASSWORD_ADDED),
                                      str_pos, str_breath, str_length, str_sound);
            userViewModel.insert(user);
            Toast.makeText(getApplicationContext(), "User Successfully Created", Toast.LENGTH_LONG).show();
        //If unsuccessful, provide error feedback
        } else {
            Toast.makeText(getApplicationContext(),"Error: User Could Not Be Created. Try again", Toast.LENGTH_LONG).show();
        }
    }
}
