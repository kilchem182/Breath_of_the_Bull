package com.example.breath_of_the_bull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

//Used to manage all user input and control functionality of application.
//Manages, manipulates, and utilizes data from entity classes
public class ManageUserInput {

    //generate String array of meditation session parameters
    public static String[] generateSessionParams() {

        //test data for meditation session generator
        HashMap<String, Integer> med_position_info = new HashMap<>();
        med_position_info.put("Chair", 3);
        med_position_info.put("CrossLegged", 0);
        med_position_info.put("Burmese", 4);
        med_position_info.put("HandsOnKnees", 1);
        med_position_info.put("HandsInLap", 2);
        HashMap<String, Integer> med_breathing_info = new HashMap<>();
        med_breathing_info.put("DeepSlow", 4);
        med_breathing_info.put("DeepQuick", 3);
        med_breathing_info.put("UpperSlow", 2);
        med_breathing_info.put("UpperQuick", 1);
        med_breathing_info.put("Fire", 0);
        HashMap<String, Integer> med_length_info = new HashMap<>();
        med_length_info.put("10min", 0);
        med_length_info.put("15min", 3);
        med_length_info.put("20min", 4);
        med_length_info.put("25min", 2);
        med_length_info.put("30min", 1);
        HashMap<String, Integer> med_sound_info = new HashMap<>();
        med_sound_info.put("birds", 0);
        med_sound_info.put("rain", 3);
        med_sound_info.put("wind", 1);
        med_sound_info.put("bells", 4);
        med_sound_info.put("none", 2);

        Random rand = new Random();

        int pos_choice = rand.nextInt(5);
        int breath_choice = rand.nextInt(5);
        int length_choice = rand.nextInt(5);
        int sound_choice = rand.nextInt(5);

        //while the euclidean distance of the randomly generated session parameters and the users
        //optimal parameters exceeds a threshold of 4.00, pick new parameters for the session
        while (calcEuclideanDist(pos_choice, breath_choice, length_choice, sound_choice) >= 4.00) {
            pos_choice = rand.nextInt(5);
            breath_choice = rand.nextInt(5);
            length_choice = rand.nextInt(5);
            sound_choice = rand.nextInt(5);
        }

        //get parameters from generated rankings
        String pos_choice_str = getKey(med_position_info, pos_choice);
        String breath_choice_str = getKey(med_breathing_info, breath_choice);
        String length_choice_str = getKey(med_length_info, length_choice);
        String sound_choice_str = getKey(med_sound_info, sound_choice);

        return new String[]{pos_choice_str, breath_choice_str, length_choice_str, sound_choice_str};
    }

    //get the message for the corresponding position parameter
    public static String getPositionMessage(String pos_choice) {
        String msg;

        if (pos_choice == "Chair") {
            msg = "Sit upright in a chair. Get comfortable, but be sure to sit up straight.";
        }
        else if (pos_choice == "CrossLegged") {
            msg = "Sit on a flat surface with your legs crossed. Relax your shoulders and straighten your neck and back.";
        }
        else if (pos_choice == "Burmese") {
            msg = "Cross your legs and lay your feet in front of you, one after the other. Relax your shoulders and straighten your neck and back. ";
        }
        else if (pos_choice == "HandsOnKnees") {
            msg = "Cross your legs and place your hands on your knees. Relax your shoulders and straighten your neck and back.";
        }
        else {
            msg = "Cross your legs and place your hands in your lap, one on top of the other with your thumbs touching. Relax your shoulders and straighten your neck and back.";
        }

        return msg;
    }

    //get the message for the corresponding breathing technique parameter
    public static String getBreathingMessage(String breath_choice) {
        String msg = "";

        if (breath_choice == "DeepSlow") {
            msg = "Breath in for 5 seconds and out for 5 seconds, focusing your breath deep in your stomach.";
        }
        else if (breath_choice == "DeepQuick") {
            msg = "Breath in for 3 seconds and out for 3 seconds, focusing your breath deep in your stomach.";
        }
        else if (breath_choice == "UpperSlow") {
            msg = "Breath in for 5 seconds and out for 5 seconds, focusing your breath at the center of your chest.";
        }
        else if (breath_choice == "UpperQuick") {
            msg = "Breath in for 3 seconds and out for 3 seconds, focusing your breath at the center of your chest.";
        }
        else {
            msg = "Alternate between breathing in and out quickly every second for ten seconds and breathing normally for 20 seconds";
        }

        return msg;
    }

    //get the start time for the corresponding session length parameter
    public static long getLengthMessage(String length_choice) {
        long timer;

        if (length_choice == "10min") {
            timer = 600000;
        }
        else if (length_choice == "15min") {
            timer = 900000;
        }
        else if (length_choice == "20min") {
            timer = 1200000;
        }
        else if (length_choice == "25min") {
            timer = 1500000;
        }
        else {
            timer = 1800000;
        }

        return timer;
    }

    //get the message for the corresponding session audio parameter
    public static String getSoundMessage(String sound_choice) {
        String msg;

        if (sound_choice == "birds") {
            msg = "Add bird sounds.";
        }
        else if (sound_choice == "rain") {
            msg = "Add rain sounds.";
        }
        else if (sound_choice == "wind") {
            msg = "Add wind sounds.";
        }
        else if (sound_choice == "bells") {
            msg = "Add bell/gong sounds.";
        }
        else {
            msg = "Add no sounds.";
        }

        return msg;
    }

    //calculate the euclidean distance between random parameters and optimal parameters
    public static double calcEuclideanDist(int pos, int breath, int length, int sound) {
        int a = (4 - pos) * (4 - pos);
        int b = (4 - breath) * (4 - breath);
        int c = (4 - length) * (4 - length);
        int d = (4 - sound) * (4 - sound);

        int total = a + b + c + d;

        return Math.sqrt(total);
    }

    //retrieve the hash key that corresponds to the given hash value
    public static String getKey(HashMap<String, Integer> map, int key) {
        String choice = "";
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            if ((int)pair.getValue() == key) {
                choice = (String)pair.getKey();
            }
        }

        return choice;
    }
}
