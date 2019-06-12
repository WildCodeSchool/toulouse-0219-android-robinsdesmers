package fr.wildcodeschool.robinsdesmers;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    int score;

    public User(int score) {
        this.score = score;
    }

    protected User(Parcel in) {
        score = in.readInt();
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(score);
    }

    public User() {
    }
}
