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

    private Long id;
    private String title;
    private String description;
    private Integer sumRubbish;
    private boolean atSea;
    private boolean collected;
    private Double latitude;
    private Double longitude;

    public RubbishItem() {
    }

    public RubbishItem(String title, String description, Integer sumRubbish, boolean atSea, boolean collected, Double latitude, Double longitude) {
        this.title = title;
        this.description = description;
        this.sumRubbish = sumRubbish;
        this.atSea = atSea;
        this.collected = collected;
        this.latitude = latitude;
        this.longitude = longitude;
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
        atSea = in.readByte() != 0;
        collected = in.readByte() != 0;
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

    public Integer getSumRubbish() {
        return sumRubbish;
    }

    public void setSumRubbish(Integer sumRubbish) {
        this.sumRubbish = sumRubbish;
    }

    public boolean isAtSea() {
        return atSea;
    }

    public void setAtSea(boolean atSea) {
        this.atSea = atSea;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
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
        if (sumRubbish == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sumRubbish);
        }
        dest.writeByte((byte) (atSea ? 1 : 0));
        dest.writeByte((byte) (collected ? 1 : 0));
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
