//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: BOTBDatabase.java
//Description: This file creates an instance of the database which is used to
//store all user data.
//Last modified on: 4/24/19

package com.example.breath_of_the_bull;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

//Database initialization to store user data
@Database(entities = {Entity.class}, version = 1)
public abstract class BOTBDatabase extends RoomDatabase {

    public abstract DAO DAO();

    private static volatile BOTBDatabase botb_database_instance;

    static BOTBDatabase getDatabase(final Context context) {
        if (botb_database_instance == null) {
            synchronized (BOTBDatabase.class) {
                if (botb_database_instance == null) {
                    botb_database_instance = Room.databaseBuilder(context.getApplicationContext(),
                            BOTBDatabase.class, "botb_database").build();
                }
            }
        }
        return botb_database_instance;
    }
}
