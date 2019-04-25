//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: DAO.java
//Description: This file creates a data access object interface, which is implemented
//in other files in order to interact with the database.
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

//Data Access Object for database
@Dao
public interface DAO {

    @Insert
    void insert(Entity user);

    @Update
    void update(Entity user);

    @Query("SELECT * FROM users WHERE Username LIKE :Username")
    public Entity findUser(String Username);
}
