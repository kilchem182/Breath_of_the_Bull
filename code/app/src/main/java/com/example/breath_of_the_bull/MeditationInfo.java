package com.example.breath_of_the_bull;

import java.util.HashMap;

//Defines information on a user's meditation information
//All four session parameters (Position, Breathing, Length, and Sound) are stores here.
public class MeditationInfo extends UserInfo {

    private HashMap<String, Integer> med_position_info = new HashMap<>();
    private HashMap<String, Integer> med_breathing_info = new HashMap<>();
    private HashMap<String, Integer> med_length_info = new HashMap<>();
    private HashMap<String, Integer> med_sound_info = new HashMap<>();

    public MeditationInfo(String user, String pass, HashMap<String, Integer> position,
                          HashMap<String, Integer> breathing, HashMap<String, Integer> length, HashMap<String, Integer> sound) {
        username = user;
        password = pass;
        med_position_info = position;
        med_breathing_info = breathing;
        med_length_info = length;
        med_sound_info = sound;
    }

    public void setMedPosInfo(HashMap<String, Integer> position) {
        this.med_position_info = position;
    }

    public void setMedBreathInfo(HashMap<String, Integer> breathing) {
        this.med_breathing_info = breathing;
    }

    public void setMedLengthInfo(HashMap<String, Integer> length) {
        this.med_length_info = length;
    }

    public void setMedSoundInfo(HashMap<String, Integer> sound) {
        this.med_sound_info = sound;
    }

    public HashMap<String, Integer> getMedPosInfo() {
        return this.med_position_info;
    }

    public HashMap<String, Integer> getMedBreathInfo() {
        return this.med_breathing_info;
    }

    public HashMap<String, Integer> getMedLengthInfo() {
        return this.med_length_info;
    }

    public HashMap<String, Integer> getMedSoundInfo() {
        return this.med_sound_info;
    }
}
