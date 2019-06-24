package fr.wildcodeschool.robinsdesmers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RubbishItem implements Parcelable {

    private double latitude;
    private double longitude;
    private String title;
    private String description;
    private Integer sommeDechet;
    private boolean isAtSea;
    public boolean isCollected;

    public RubbishItem(double latitude, double longitude, String title, String description, Integer sommeDechet, boolean isAtSea, boolean isCollected) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.title = title;
        this.description = description;
        this.sommeDechet = sommeDechet;
        this.isAtSea = isAtSea;
        this.isCollected = isCollected;
    }

    public RubbishItem() {

    }


    protected RubbishItem(Parcel in) {
        latitude = in.readDouble();
        longitude = in.readDouble();
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            sommeDechet = null;
        } else {
            sommeDechet = in.readInt();
        }
        isAtSea = in.readByte() != 0;
        isCollected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(title);
        dest.writeString(description);
        if (sommeDechet == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sommeDechet);
        }
        dest.writeByte((byte) (isAtSea ? 1 : 0));
        dest.writeByte((byte) (isCollected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

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

    public Integer getSommeDechet() {
        return sommeDechet;
    }

    public void setSommeDechet(Integer sommeDechet) {
        this.sommeDechet = sommeDechet;
    }

    public boolean isAtSea() {
        return isAtSea;
    }

    public void setAtSea(boolean atSea) {
        isAtSea = atSea;
    }

    public boolean isCollected() {
        return isCollected;
    }

    public void setCollected(boolean collected) {
        isCollected = collected;
    }

    public static Creator<RubbishItem> getCREATOR() {
        return CREATOR;
    }

}
