package fr.wildcodeschool.robinsdesmers.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CollectPointItem implements Parcelable {

    private Long id;
    private String title;
    private String description;
    private Integer sommePoubelle;
    private Long latitude;
    private Long longitude;

    public CollectPointItem(Long id, String title, String description, Integer sommePoubelle, Long latitude, Long longitude) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sommePoubelle = sommePoubelle;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CollectPointItem() {
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
            sommePoubelle = null;
        } else {
            sommePoubelle = in.readInt();
        }
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

    public Integer getSommePoubelle() {
        return sommePoubelle;
    }

    public void setSommePoubelle(Integer sommePoubelle) {
        this.sommePoubelle = sommePoubelle;
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
        if (sommePoubelle == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(sommePoubelle);
        }
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