//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: QuoteList.java
//Description: This file creates quote objects and selects the current days quote
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import java.util.Calendar;

public class QuoteList {

    //Declaration of current quote objects
    static QuoteObject q1 = generateQuote("\"The mind of the beginner is empty, free of the habits of the expert, ready to accept, to doubt, and open to all the possibilities.\"", "-Shunryu Suzuki");
    static QuoteObject q2 = generateQuote("\"Wherever you are, it's the place you need to be.\"", "-Maxime Lagace");
    static QuoteObject q3 = generateQuote("\"Nothing ever goes away until it has taught us what we need to know.\"", "-Pema Chodron");
    static QuoteObject q4 = generateQuote("\"Before enlightenment; chop wood, carry water. After enlightenment; chop wood, carry water.\"", "-Buddha");
    static QuoteObject q5 = generateQuote("\"The search for happiness is one of the chief sources of unhappiness.\"", "-Eric Hoffer");
    static QuoteObject q6 = generateQuote("\"Education breeds confidence. Confidence breeds hope. Hope breeds peace.\"", "-Confucious");
    static QuoteObject q7 = generateQuote("\"Learn to be comfortable in adversity but most importantly, learn to be comfortable when you're bored.\"", "-Maxime Lagace");
    static QuoteObject q8 = generateQuote("\"When thoughts arise, then do all things arise. When thoughts vanish, then do all things vanish.\"", "-Huang Po");
    static QuoteObject q9 = generateQuote("\"The place to improve the world is first in one's own heart and head and hands.\"", "-Rober M. Pirsig");
    static QuoteObject q10 = generateQuote("\"If you are depressed, you are living in the past. If you are anxious, you are living in the future. If you are at peace, you are living in the present.\"", "-Lao Tzu");
    static QuoteObject q11 = generateQuote("\"Life is a balance of holding on and letting go.\"", "-Rumi");
    static QuoteObject q12 = generateQuote("\"When one first seeks the truth, one separates oneself from it.\"", "-Dogen");
    static QuoteObject q13 = generateQuote("\"Don't seek, don't search, don't ask, don't knock, don't demand - relax.\"", "-Osho");
    static QuoteObject q14 = generateQuote("\"The resistance to the unpleasant situation is the root of suffering.\"", "-Ram Dass");
    static QuoteObject q15 = generateQuote("\"Life isn't as serious as the mind makes it out to be.\"", "-Eckhart Tolle");
    static QuoteObject q16 = generateQuote("\"Do you have the patience to wait until your mud settles and the water is clear?\"", "-Lao Tzu");
    static QuoteObject q17 = generateQuote("\"Forget the years, forget the distinctions. Leap into the boundless and make it your home.\"", "-Zhuangzi");
    static QuoteObject q18 = generateQuote("\"One loses joy and hapiness in the attempt to possess them.", "-Masanobu Fukuoka\"");
    static QuoteObject q19 = generateQuote("\"Man suffers only because he takes seriously what the gods made for fun.", "-Alan Watts\"");
    static QuoteObject q20 = generateQuote("\"The purpose of Zen is to see things as they are, to observe things as they are, and to let everything go as it goes.\"", "-Shunryu Suzuki");

    //-----------------------------------------------------------------
    //
    //Function: QuoteObject()
    //
    //Parameters:
    //None
    //
    //Pre-conditions: User requests to view daily quote
    //Post-condition: Quote is determined from day of the year and returned
    //-------------------------------------------------------------------
    public static QuoteObject getCurrQuote() {

        int idx = 0;
        QuoteObject[] curr_list = buildQuoteList();

        Calendar cal = Calendar.getInstance();
        int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);

        idx = (dayOfYear % 20);

        return curr_list[idx];
    }

    //Builds list of current quotes
    public static QuoteObject[] buildQuoteList() {

        QuoteObject[] quote_list = {q1, q2, q3, q4, q5, q6, q7, q8, q9, q10,
                                    q11, q12, q13, q14, q15, q16, q17, q18, q19, q20};

        return quote_list;
    }

    //Creates quote object
    public static QuoteObject generateQuote(String quote, String author) {

        QuoteObject q = new QuoteObject(quote, author);
        return q;
    }
}
