package fr.wildcodeschool.robinsdesmers;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    int compteur;

    public User(int compteur) {
        this.compteur = compteur;
    }

    protected User(Parcel in) {
        compteur = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getCompteur() {
        return compteur;
    }

    public void setCompteur(int compteur) {
        this.compteur = compteur;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(compteur);
    }

    public User() {
    }
}
