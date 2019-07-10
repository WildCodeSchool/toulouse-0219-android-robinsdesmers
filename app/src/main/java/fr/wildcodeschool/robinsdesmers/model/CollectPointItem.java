package fr.wildcodeschool.robinsdesmers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CollectPointItem implements Parcelable {

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

    private Long id;
    private String title;
    private String description;
    private Integer sumCollectPoint;
    private Double latitude;
    private Double longitude;

    public CollectPointItem() {
    }

    public CollectPointItem(String title, String description, Integer sumCollectPoint, Double latitude, Double longitude) {
        this.title = title;
        this.description = description;
        this.sumCollectPoint = sumCollectPoint;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected CollectPointItem(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            sumCollectPoint = null;
        } else {
            sumCollectPoint = in.readInt();
        }
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readDouble();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readDouble();
        }
    }

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

    public Integer getSumCollectPoint() {
        return sumCollectPoint;
    }

    public void setSumCollectPoint(Integer sumCollectPoint) {
        this.sumCollectPoint = sumCollectPoint;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(title);
        dest.writeString(description);
        if (sumCollectPoint == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sumCollectPoint);
        }
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(longitude);
        }
    }
}