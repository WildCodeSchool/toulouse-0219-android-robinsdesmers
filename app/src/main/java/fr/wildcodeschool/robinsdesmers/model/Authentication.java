package fr.wildcodeschool.robinsdesmers.model;

public class Authentication {
    private String error;
    private User user;

    public Authentication() {
    }

    public Authentication(String error, User user) {
        this.error = error;
        this.user = user;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
