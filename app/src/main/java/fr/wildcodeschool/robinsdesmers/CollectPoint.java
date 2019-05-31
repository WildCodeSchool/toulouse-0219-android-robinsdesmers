package fr.wildcodeschool.robinsdesmers;

import android.os.Parcel;
import android.os.Parcelable;

public class CollectPoint implements Parcelable {

    double latitude;
    double longitude;
    String infoCollectPoint;
    String infoSup;
    String date;
    boolean notHere;
    String key;

    public CollectPoint(){

    }

    public CollectPoint(double latitude, double longitude, String infoCollectPoint, String infoSup, String date, String key) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.infoCollectPoint = infoCollectPoint;
        this.infoSup = infoSup;
        this.date = date;
        this.notHere = false;
        this.key = key;
    }

    protected CollectPoint(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        infoCollectPoint = in.readString();
        infoSup = in.readString();
        date = in.readString();
        key = in.readString();
    }

    public static final Creator<CollectPoint> CREATOR = new Creator<CollectPoint>() {
        @Override
        public CollectPoint createFromParcel(Parcel in) {
            return new CollectPoint(in);
        }

        @Override
        public CollectPoint[] newArray(int size) {
            return new CollectPoint[size];
        }
    };

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

    public String getInfoCollectPoint() {
        return infoCollectPoint;
    }

    public void setInfoCollectPoint(String infoCollectPoint) {
        this.infoCollectPoint = infoCollectPoint;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(infoCollectPoint);
        dest.writeString(infoSup);
        dest.writeString(date);
        dest.writeString(key);
    }
}
