package fr.wildcodeschool.robinsdesmers;

import android.os.Parcel;
import android.os.Parcelable;

public class RubbishMarkers implements Parcelable {

    public static final Creator<RubbishMarkers> CREATOR = new Creator<RubbishMarkers>() {
        @Override
        public RubbishMarkers createFromParcel(Parcel in) {
            return new RubbishMarkers(in);
        }

        @Override
        public RubbishMarkers[] newArray(int size) {
            return new RubbishMarkers[size];
        }
    };
    double latitude;
    double longitude;
    String infoRubbish;
    String infoSup;
    String date;
    boolean isCollected;
    String key;

    public RubbishMarkers(double latitude, double longitude, String infoRubbish, String infoSup, String date, String key) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.infoRubbish = infoRubbish;
        this.infoSup = infoSup;
        this.date = date;
        this.key = key;
        this.isCollected = false;
    }

    public RubbishMarkers() {

    }

    protected RubbishMarkers(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        infoRubbish = in.readString();
        infoSup = in.readString();
        date = in.readString();
    }

    public static Creator<RubbishMarkers> getCREATOR() {
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
