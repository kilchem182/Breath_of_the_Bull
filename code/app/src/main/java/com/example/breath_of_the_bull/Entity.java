//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: Entity.java
//Description: This file defines the format of the database where user information
//such as meditation parameters and their priorities are stored.
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

//Class used to create format of table in database
@android.arch.persistence.room.Entity(tableName = "users")
public class Entity {

    //get methods for each aspect of table
    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public String getUsername() {
        return this.username;
    }

    @NonNull
    public String getPassword() {
        return this.password;
    }

    public String getMed_position_info() {
        return this.med_position_info;
    }

    public String getMed_breathing_info() {
        return this.med_breathing_info;
    }

    public String getMed_length_info() {
        return this.med_length_info;
    }

    public String getMed_sound_info() {
        return this.med_sound_info;
    }

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    @ColumnInfo(name = "Username")
    private String username;
    @NonNull
    @ColumnInfo(name = "Password")
    private String password;

    @ColumnInfo(name = "Meditation Position Information")
    private String med_position_info;
    @ColumnInfo(name = "Meditation Breathing Information")
    private String med_breathing_info;
    @ColumnInfo(name = "Meditation Length Information")
    private String med_length_info;
    @ColumnInfo(name = "Meditation Sound Information")
    private String med_sound_info;

    //Entity constructor
    public Entity(String id, String username, String password, String med_position_info,
                  String med_breathing_info, String med_length_info,
                  String med_sound_info) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.med_position_info = med_position_info;
        this.med_breathing_info = med_breathing_info;
        this.med_length_info = med_length_info;
        this.med_sound_info = med_sound_info;
    }
}
