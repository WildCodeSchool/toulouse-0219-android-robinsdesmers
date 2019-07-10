package fr.wildcodeschool.robinsdesmers.model;

public class Stats {
    private int nbUsers;
    private int nbCollectPoints;
    private int nbRubbishes;

    public Stats() {
    }

    public int getNbUsers() {
        return nbUsers;
    }

    public void setNbUsers(int nbUsers) {
        this.nbUsers = nbUsers;
    }

    public int getNbCollectPoints() {
        return nbCollectPoints;
    }

    public void setNbCollectPoints(int nbCollectPoints) {
        this.nbCollectPoints = nbCollectPoints;
    }

    public int getNbRubbishes() {
        return nbRubbishes;
    }

    public void setNbRubbishes(int nbRubbishes) {
        this.nbRubbishes = nbRubbishes;
    }
}
