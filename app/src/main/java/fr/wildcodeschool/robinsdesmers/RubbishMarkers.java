package fr.wildcodeschool.robinsdesmers;

import android.os.Parcel;
import android.os.Parcelable;

public class RubbishMarkers implements Parcelable {

    double latitude;
    double longitude;

    public RubbishMarkers(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public RubbishMarkers(){

    }

    protected RubbishMarkers(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
