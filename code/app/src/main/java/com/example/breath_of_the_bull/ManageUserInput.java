//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: ManageUserInput.java
//Description: This file acts as the control class for the application, and handles most
//of the calculations needed.
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

//Used to manage all user input and control functionality of application.
//Manages, manipulates, and utilizes data from entity classes
public class ManageUserInput {

    private static Entity user;
    private static String[] med_choices;

    public void setUser(Entity user) {
        this.user = user;
    }

    //--------------------------------------------------------------------------------------------
    //
    //Function: updateUser()
    //
    //Parameters:
    //input String; all four parameters are strings pertaining to the ranks of session parameters
    //(position, breathing technique, duration, and sound) selected by the user after a meditation
    //session has been completed
    //
    //Pre-condition: User ranks meditation session parameters and submits ranks
    //Post-condition: User information is updated and returned
    //--------------------------------------------------------------------------------------------
    public static Entity updateUser(String pos_str, String breath_str, String length_str, String sound_str) {

        //get integer representations of rankings given by user
        int pos_rank = getRank(pos_str);
        int breath_rank = getRank(breath_str);
        int length_rank = getRank(length_str);
        int sound_rank = getRank(sound_str);

        user = updateUserMedInfo(med_choices, pos_rank, breath_rank, length_rank, sound_rank);
        return user;
    }

    //------------------------------------------------------------------------------------------
    //
    //Function: updateUserMedInfo()
    //
    //Parameters:
    //input String[]; array of generated meditation session parameters
    //input int; following four parameters are numerical ranks obtained from user feedback
    //on meditation session parameters
    //
    //Pre-condition: String rank statements are converted to numerical ranks and update is requested
    //Post-condition: User meditation session preferences are updated and new user Entity object
    //is returned
    //------------------------------------------------------------------------------------------
    public static Entity updateUserMedInfo(String[] choosen_params, int new_pos, int new_breath, int new_length, int new_sound) {

        HashMap<String, Integer> med_position_info = null;
        HashMap<String, Integer> med_breathing_info = null;
        HashMap<String, Integer> med_length_info = null;
        HashMap<String, Integer> med_sound_info = null;

        //Convert current user JSON information to HashMaps
        try {
            med_position_info = jsonToMap(user.getMed_position_info());
            med_breathing_info = jsonToMap(user.getMed_breathing_info());
            med_length_info = jsonToMap(user.getMed_length_info());
            med_sound_info = jsonToMap(user.getMed_sound_info());
        } catch(JSONException e) {
            e.printStackTrace();
        }

        //Update meditation session parameters
        med_position_info = updateMap(med_position_info, choosen_params[0], new_pos);
        med_breathing_info = updateMap(med_breathing_info, choosen_params[1], new_breath);
        med_length_info = updateMap(med_length_info, choosen_params[2], new_length);
        med_sound_info = updateMap(med_sound_info, choosen_params[3], new_sound);

        //Convert back to JSON strings
        JSONObject json_pos = new JSONObject(med_position_info);
        JSONObject json_breath = new JSONObject(med_breathing_info);
        JSONObject json_length = new JSONObject(med_length_info);
        JSONObject json_sound = new JSONObject(med_sound_info);
        String str_pos = json_pos.toString();
        String str_breath = json_breath.toString();
        String str_length = json_length.toString();
        String str_sound = json_sound.toString();

        //Create newly updated user object
        Entity new_user = new Entity(user.getId(), user.getUsername(), user.getPassword(),
                str_pos, str_breath, str_length, str_sound);

        return new_user;
    }

    //------------------------------------------------------------------------------------
    //
    //Function: updateMap()
    //
    //Parameters:
    //input HashMap; HashMap for single meditation session parameter
    //input String; key for parameter selected in currently generated meditation session
    //input int; new rank for parameter to be updated
    //
    //Pre-condition: Numerical rankings have been obtained and request for map update is made
    //Post-condition: Map is updated and returned
    //--------------------------------------------------------------------------------------
    public static HashMap<String, Integer> updateMap(HashMap<String, Integer> map, String key, int new_value) {
        int temp = -1;

        //Set parameter with current desired rank to null
        Iterator it1 = map.entrySet().iterator();
        while (it1.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it1.next();
            if(((String)pair.getKey()).equals(key) && (int)pair.getValue() == new_value) {
                return map;
            } else if ((int)pair.getValue() == new_value) {
                pair.setValue(null);
            }
        }

        //Set session parameter to new rank
        Iterator it2 = map.entrySet().iterator();
        while (it2.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it2.next();
            if (((String)pair.getKey()).equals(key)) {
                temp = (int)pair.getValue();
                pair.setValue(new_value);
            }
        }

        //Replace other parameter's rank with current session parameter's rank
        Iterator it3 = map.entrySet().iterator();
        while (it3.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it3.next();
            if (pair.getValue() == null) {
                pair.setValue(temp);
            }
        }

        return map;
    }

    //Convert String rank statements obtained from session feedback to integer values
    public static int getRank(String rank_str) {

        if (rank_str.equals("I loved it")) {
            return 4;
        } else if (rank_str.equals("I liked it")) {
            return 3;
        } else if (rank_str.equals("It was ok")) {
            return 2;
        } else if (rank_str.equals("I didn't like it")) {
            return 1;
        } else {
            return 0;
        }
    }

    //-----------------------------------------------------------------------------------
    //
    //Function: generateSessionParams()
    //
    //Parameters:
    //None
    //
    //Pre-condition: User selects meditation session functionality
    //Post-condition: Generate String array of meditation session parameters
    //-----------------------------------------------------------------------------------
    public static String[] generateSessionParams() {

        HashMap<String, Integer> med_position_info = null;
        HashMap<String, Integer> med_breathing_info = null;
        HashMap<String, Integer> med_length_info = null;
        HashMap<String, Integer> med_sound_info = null;

        //Convert JSOn data to HashMap
        try {
            med_position_info = jsonToMap(user.getMed_position_info());
            med_breathing_info = jsonToMap(user.getMed_breathing_info());
            med_length_info = jsonToMap(user.getMed_length_info());
            med_sound_info = jsonToMap(user.getMed_sound_info());
        } catch(JSONException e) {
            e.printStackTrace();
        }

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

        med_choices = new String[]{pos_choice_str, breath_choice_str, length_choice_str, sound_choice_str};
        return med_choices;
    }

    //-------------------------------------------------------------------------------------
    //
    //Function: jsonToMap()
    //
    //Parameters:
    //input String: json string to be converted into HashMap
    //
    //Pre-condition: Json string is obtained from user's information and request for
    //conversion is made
    //Post-condition: HashMap representation of data is returned
    //------------------------------------------------------------------------------------
    public static HashMap<String, Integer> jsonToMap(String json_str) throws JSONException {

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        JSONObject jObject = new JSONObject(json_str);
        Iterator<?> keys = jObject.keys();

        while( keys.hasNext() ){
            String key = (String)keys.next();
            String value = jObject.getString(key);
            map.put(key, Integer.parseInt(value));
        }

        return map;
    }

    //get the message for the corresponding position parameter
    public static String getPositionMessage(String pos_choice) {
        String msg;

        if (pos_choice.equals("Chair")) {
            msg = "Sit upright in a chair. Get comfortable, but be sure to sit up straight.";
        }
        else if (pos_choice.equals("CrossLegged")) {
            msg = "Sit on a flat surface with your legs crossed. Relax your shoulders and straighten your neck and back.";
        }
        else if (pos_choice.equals("Burmese")) {
            msg = "Cross your legs and lay your feet in front of you, one after the other. Relax your shoulders and straighten your neck and back. ";
        }
        else if (pos_choice.equals("HandsOnKnees")) {
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

        if (breath_choice.equals("DeepSlow")) {
            msg = "Breath in for 5 seconds and out for 5 seconds, focusing your breath deep in your stomach.";
        }
        else if (breath_choice.equals("DeepQuick")) {
            msg = "Breath in for 3 seconds and out for 3 seconds, focusing your breath deep in your stomach.";
        }
        else if (breath_choice.equals("UpperSlow")) {
            msg = "Breath in for 5 seconds and out for 5 seconds, focusing your breath at the center of your chest.";
        }
        else if (breath_choice.equals("UpperQuick")) {
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

        if (length_choice.equals("10min")) {
            timer = 600000;
        }
        else if (length_choice.equals("15min")) {
            timer = 900000;
        }
        else if (length_choice.equals("20min")) {
            timer = 1200000;
        }
        else if (length_choice.equals("25min")) {
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

        if (sound_choice.equals("birds")) {
            msg = "Add bird sounds.";
        }
        else if (sound_choice.equals("rain")) {
            msg = "Add rain sounds.";
        }
        else if (sound_choice.equals("wind")) {
            msg = "Add wind sounds.";
        }
        else if (sound_choice.equals("bells")) {
            msg = "Add bell/gong sounds.";
        }
        else {
            msg = "Add no sounds.";
        }

        return msg;
    }

    //---------------------------------------------------------------------------------
    //
    //Function: calcEuclideanDist()
    //
    //Parameters:
    //input int; all parameters are generated session rankings used to decide meditation
    //session parameters
    //
    //Pre-condition: Session parameter rankings have been generated
    //Post-condition: Calculates the euclidean distance between random parameters and optimal parameters
    //--------------------------------------------------------------------------------
    public static double calcEuclideanDist(int pos, int breath, int length, int sound) {
        int a = (4 - pos) * (4 - pos);
        int b = (4 - breath) * (4 - breath);
        int c = (4 - length) * (4 - length);
        int d = (4 - sound) * (4 - sound);

        int total = a + b + c + d;

        return Math.sqrt(total);
    }

    //---------------------------------------------------------------------------------
    //
    //Function: getKey();
    //
    //Parameters:
    //input HashMap; map containing desired key
    //input int; value used to determine desired key
    //
    //Pre-condition: Verify generated meditation session parameter rank and request
    //String pertaining to said parameter
    //Post-condition: Retrieve the hash key that corresponds to the given hash value
    //--------------------------------------------------------------------------------
    public static String getKey(HashMap<String, Integer> map, int value) {
        String choice = "";
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry pair = (HashMap.Entry)it.next();
            if ((int)pair.getValue() == value) {
                choice = (String)pair.getKey();
            }
        }

        return choice;
    }
}
