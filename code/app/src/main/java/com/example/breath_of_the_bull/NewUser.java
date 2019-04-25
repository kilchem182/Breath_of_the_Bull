//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: NewUser.java
//Description: This file manages the request for a new account
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

//Used to create a new user and new user information to database
public class NewUser extends AppCompatActivity {

    public static final String USERNAME_ADDED = "Add username";
    public static final String PASSWORD_ADDED = "Add Password";

    private EditText new_username;
    private EditText new_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        new_username = findViewById(R.id.username);
        new_password = findViewById(R.id.password);

        FloatingActionButton new_login = (FloatingActionButton) findViewById(R.id.login2);
        new_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();

                //Check username and password fields and save result if applicable
                if (TextUtils.isEmpty(new_username.getText()) || TextUtils.isEmpty(new_password.getText())) {
                    setResult(RESULT_CANCELED, resultIntent);
                } else {
                    String user = new_username.getText().toString();
                    resultIntent.putExtra(USERNAME_ADDED, user);
                    String pass = new_password.getText().toString();
                    resultIntent.putExtra(PASSWORD_ADDED, pass);
                    setResult(RESULT_OK, resultIntent);
                }

                finish();
            }
        });

        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.back_new_user);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });
    }

    public void openLogin() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
