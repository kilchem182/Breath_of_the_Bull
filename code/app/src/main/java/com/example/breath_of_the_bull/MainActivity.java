package com.example.breath_of_the_bull;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //listen for meditate button to be pressed, and bring user to meditation session on press
        Button meditate = (Button) findViewById(R.id.meditate);
        meditate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMeditate();
            }
        });

        //listen for quote button to be pressed, and bring user to daily quote activity on press
        Button quote = (Button) findViewById(R.id.quote);
        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuote();
            }
        });
    }

    //bring user to meditation session screen
    public void openMeditate() {
        Intent intent = new Intent(this, Meditate.class);
        startActivity(intent);
    }

    //bring user to daily quote screen
    public void openQuote() {
        Intent intent = new Intent(this, Quote.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
