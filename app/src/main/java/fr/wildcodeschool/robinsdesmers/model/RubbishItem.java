package fr.wildcodeschool.robinsdesmers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RubbishItem implements Parcelable {

    public static final Creator<RubbishItem> CREATOR = new Creator<RubbishItem>() {
        @Override
        public RubbishItem createFromParcel(Parcel in) {
            return new RubbishItem(in);
        }

        @Override
        public RubbishItem[] newArray(int size) {
            return new RubbishItem[size];
        }
    };
    double latitude;
    double longitude;
    String infoRubbish;
    String infoSup;
    String date;
    public boolean isCollected;
    String key;

    public RubbishItem(double latitude, double longitude, String infoRubbish, String infoSup, String date, String key) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.infoRubbish = infoRubbish;
        this.infoSup = infoSup;
        this.date = date;
        this.key = key;
        this.isCollected = false;
    }

    public RubbishItem() {

    }

    protected RubbishItem(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        infoRubbish = in.readString();
        infoSup = in.readString();
        date = in.readString();
    }
    public static Creator<RubbishItem> getCREATOR() {
        return CREATOR;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getInfoRubbish() {
        return infoRubbish;
    }

    public void setInfoRubbish(String infoRubbish) {
        this.infoRubbish = infoRubbish;
    }

    public String getInfoSup() {
        return infoSup;
    }

    public void setInfoSup(String infoSup) {
        this.infoSup = infoSup;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(infoRubbish);
        dest.writeString(infoSup);
        dest.writeString(date);

    }
}
