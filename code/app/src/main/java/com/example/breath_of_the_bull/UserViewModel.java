//Project name: Breath of the Bull
//Description: Breath of the Bull is an Android mobile application that provides
//Zen-based support and techniques such as mindfulness exercises, daily quotes
//from Zen masters, and guided meditation sessions to help alleviate stress and anxiety.
//Filename: UserViewModel.java
//Description: This file defines view model for database tasks
//Last modified on: 4/24/19
package com.example.breath_of_the_bull;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.util.Log;

//View Model for all asynchronous database communications
public class UserViewModel extends AndroidViewModel {

    private String TAG = this.getClass().getSimpleName();
    private DAO user_dao;
    private BOTBDatabase DB;
    private Entity curr_user;

    public UserViewModel(Application application) {
        super(application);

        DB = BOTBDatabase.getDatabase(application);
        user_dao = DB.DAO();
    }

    public void insert(Entity user) {
        new InsertAsyncTask(user_dao).execute(user);
    }

    public void update(Entity user) {
        new UpdateAsyncTask(user_dao).execute(user);
    }

    public Entity getUser(String Username, String Password) {
        new LoginAsyncTask(user_dao).execute(Username);
        return getCurrUser();
    }

    public void setCurrUser(Entity curr) {
        this.curr_user = curr;
    }

    public Entity getCurrUser() {
        return this.curr_user;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }

    //Task for inserting a new user into the database
    private class InsertAsyncTask extends AsyncTask<Entity, Void, Void> {
        DAO newDAO;

        public InsertAsyncTask(DAO newDAO) {
            this.newDAO = newDAO;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            newDAO.insert(entities[0]);
            return null;
        }
    }

    //Task for updating a user already in the database
    private class UpdateAsyncTask extends AsyncTask<Entity, Void, Void> {
        DAO newDAO;

        public UpdateAsyncTask(DAO newDAO) {
            this.newDAO = newDAO;
        }

        @Override
        protected Void doInBackground(Entity... entities) {
            newDAO.update(entities[0]);
            return null;
        }
    }

    //Task for retrieving user information from database at login request
    private class LoginAsyncTask extends AsyncTask<String, Void, Entity> {
        DAO newDAO;

        public LoginAsyncTask(DAO newDAO) {
            this.newDAO = newDAO;
        }

        @Override
        protected Entity doInBackground(String... strings) {
            Entity user = newDAO.findUser(strings[0]);
            return user;
        }

        protected void onPostExecute(Entity result) {
            setCurrUser(result);
        }
    }
}
