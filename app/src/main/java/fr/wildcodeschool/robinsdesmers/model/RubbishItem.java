package fr.wildcodeschool.robinsdesmers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RubbishItem implements Parcelable {

    private Long id;
    private String title;
    private String description;
    private Integer sommeDechet;
    private boolean isAtSea;
    private boolean isCollected;
    private Long latitude;
    private Long longitude;

    public RubbishItem(Long id, String title, String description, Integer sommeDechet, boolean isAtSea, boolean isCollected, Long latitude, Long longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sommeDechet = sommeDechet;
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
            sommeDechet = null;
        } else {
            sommeDechet = in.readInt();
        }
        isAtSea = in.readByte() != 0;
        isCollected = in.readByte() != 0;
        if (in.readByte() == 0) {
            latitude = null;
        } else {
            latitude = in.readLong();
        }
        if (in.readByte() == 0) {
            longitude = null;
        } else {
            longitude = in.readLong();
        }
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

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
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
        if (sommeDechet == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sommeDechet);
        }
        dest.writeByte((byte) (isAtSea ? 1 : 0));
        dest.writeByte((byte) (isCollected ? 1 : 0));
        if (latitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(latitude);
        }
        if (longitude == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(longitude);
        }
    }
}
