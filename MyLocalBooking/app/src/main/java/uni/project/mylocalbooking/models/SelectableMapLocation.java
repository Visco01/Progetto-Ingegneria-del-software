package uni.project.mylocalbooking.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.maps.model.GeocodingResult;

public class SelectableMapLocation implements Parcelable {
    public static final Parcelable.Creator<SelectableMapLocation> CREATOR
            = new Parcelable.Creator<SelectableMapLocation>() {
        public SelectableMapLocation createFromParcel(Parcel in) {
            return new SelectableMapLocation(in);
        }

        public SelectableMapLocation[] newArray(int size) {
            return new SelectableMapLocation[size];
        }
    };
    public final String address;
    public final Coordinates coordinates;
    public final String placeId;
    public SelectableMapLocation(GeocodingResult result) {
        address = result.formattedAddress;
        this.coordinates = new Coordinates(result.geometry.location.lat, result.geometry.location.lng);
        this.placeId = result.placeId;
    }

    protected SelectableMapLocation(Parcel in) {
        address = in.readString();
        coordinates = in.readParcelable(Coordinates.class.getClassLoader());
        placeId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeParcelable(coordinates, i);
        parcel.writeString(placeId);
    }
}
