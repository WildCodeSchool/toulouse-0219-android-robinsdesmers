package fr.wildcodeschool.robinsdesmers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RubbishItem implements Parcelable {

    private Long id;
    private String title;
    private String description;
    private Integer sumRubbish;
    private boolean isAtSea;
    public boolean isCollected;
    private double latitude;
    private double longitude;

    public RubbishItem(Long id, String title, String description, Integer sumRubbish, boolean isAtSea, boolean isCollected, double latitude, double longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sumRubbish = sumRubbish;
        this.isAtSea = isAtSea;
        this.isCollected = isCollected;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public RubbishItem() {
    }

    protected RubbishItem(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            sumRubbish = null;
        } else {
            sumRubbish = in.readInt();
        }
        isAtSea = in.readByte() != 0;
        isCollected = in.readByte() != 0;
        latitude = in.readDouble();
        longitude = in.readDouble();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getSumRubbish() {
        return sumRubbish;
    }

    public void setSumRubbish(Integer sumRubbish) {
        this.sumRubbish = sumRubbish;
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

    }


}
