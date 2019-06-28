package fr.wildcodeschool.robinsdesmers;

import android.location.Location;

import fr.wildcodeschool.robinsdesmers.model.User;

public class UserSingleton {

    private static UserSingleton instance;
    private User user = new User();

    private UserSingleton() {
    }

    public static UserSingleton getUserInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    public void registerUser(User newUser) {
        user = newUser;
    }

    public void setUserLocation(Location location) {
        user.setLatitude(location.getLatitude());
        user.setLongitude(location.getLongitude());
    }

    public User getUser() {
        if (user.getScore() == null) {
            user.setScore(0);
        }
        return user;
    }
}


