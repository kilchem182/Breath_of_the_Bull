//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: QuoteObject.java
//Description: This file provides the structure for a quote object
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

public class QuoteObject {

    private String quote;
    private String author;

    public QuoteObject(String q, String a) {

        this.quote = q;
        this.author = a;
    }

    public String getQuote() {
        return this.quote;
    }

    public String getAuthor() {
        return this.author;
    }
}
