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

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button meditate = (Button) findViewById(R.id.meditate);
        meditate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMeditate();
            }
        });

        Button mindfulness = (Button) findViewById(R.id.mindfulness);
        mindfulness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMindfulness();
            }
        });

        Button quote = (Button) findViewById(R.id.quote);
        quote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuote();
            }
        });
    }

    public void openMeditate() {
        Intent intent = new Intent(this, Meditate.class);
        startActivity(intent);
    }

    public void openMindfulness() {
        Intent intent = new Intent(this, Mindfulness.class);
        startActivity(intent);
    }

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
