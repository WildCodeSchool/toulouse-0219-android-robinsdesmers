package fr.wildcodeschool.robinsdesmers;

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

    public User getUser() {
        if (user.getScore() == null) {
            user.setScore(0);
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


