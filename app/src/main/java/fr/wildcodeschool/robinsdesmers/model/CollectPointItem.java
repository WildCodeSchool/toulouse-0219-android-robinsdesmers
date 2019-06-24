package fr.wildcodeschool.robinsdesmers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CollectPointItem implements Parcelable {

    private long id;
    private String title;
    private String description;
    double latitude;
    double longitude;
    String date;
    public boolean notHere;
    String key;

    public CollectPointItem() {
    }

    public CollectPointItem(long id, String title, String description, double latitude, double longitude, String date, boolean notHere, String key) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.notHere = notHere;
        this.key = key;
    }


    protected CollectPointItem(Parcel in) {
        id = in.readLong();
        title = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        date = in.readString();
        notHere = in.readByte() != 0;
        key = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(date);
        dest.writeByte((byte) (notHere ? 1 : 0));
        dest.writeString(key);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CollectPointItem> CREATOR = new Creator<CollectPointItem>() {
        @Override
        public CollectPointItem createFromParcel(Parcel in) {
            return new CollectPointItem(in);
        }

        @Override
        public CollectPointItem[] newArray(int size) {
            return new CollectPointItem[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isNotHere() {
        return notHere;
    }

    public void setNotHere(boolean notHere) {
        this.notHere = notHere;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public static Creator<CollectPointItem> getCREATOR() {
        return CREATOR;
    }
}