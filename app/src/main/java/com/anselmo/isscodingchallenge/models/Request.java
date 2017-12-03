
package com.anselmo.isscodingchallenge.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request implements Parcelable
{

    @SerializedName("altitude")
    @Expose
    private Integer altitude;
    @SerializedName("datetime")
    @Expose
    private Integer datetime;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("passes")
    @Expose
    private Integer passes;
    public final static Creator<Request> CREATOR = new Creator<Request>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        public Request[] newArray(int size) {
            return (new Request[size]);
        }

    }
    ;

    protected Request(Parcel in) {
        this.altitude = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.datetime = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.latitude = ((Double) in.readValue((Double.class.getClassLoader())));
        this.longitude = ((Double) in.readValue((Double.class.getClassLoader())));
        this.passes = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public Request() {
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
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

    public Integer getPasses() {
        return passes;
    }

    public void setPasses(Integer passes) {
        this.passes = passes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(altitude);
        dest.writeValue(datetime);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(passes);
    }

    public int describeContents() {
        return  0;
    }

}
