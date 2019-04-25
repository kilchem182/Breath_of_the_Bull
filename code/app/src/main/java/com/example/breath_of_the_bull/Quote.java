//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: Quote.java
//Description: This file displays the daily quote to the user upon request
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quote extends AppCompatActivity {

    private TextView quote;
    private TextView author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);

        //Get current quote
        QuoteObject curr_quote = QuoteList.getCurrQuote();

        //Set text fields
        quote = (TextView)findViewById(R.id.quote);
        quote.setText(curr_quote.getQuote());
        author = (TextView)findViewById(R.id.author);
        author.setText(curr_quote.getAuthor());

        //Back button functionality
        FloatingActionButton back = (FloatingActionButton) findViewById(R.id.back_quote);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
