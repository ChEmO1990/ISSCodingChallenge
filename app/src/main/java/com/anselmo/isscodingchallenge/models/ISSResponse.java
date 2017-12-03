
package com.anselmo.isscodingchallenge.models;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ISSResponse implements Parcelable
{

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("request")
    @Expose
    private Request request;
    @SerializedName("response")
    @Expose
    ArrayList<Response> response = new ArrayList<Response>();
    public final static Creator<ISSResponse> CREATOR = new Creator<ISSResponse>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ISSResponse createFromParcel(Parcel in) {
            return new ISSResponse(in);
        }

        public ISSResponse[] newArray(int size) {
            return (new ISSResponse[size]);
        }

    }
    ;

    protected ISSResponse(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.request = ((Request) in.readValue((Request.class.getClassLoader())));
        in.readList(this.response, (com.anselmo.isscodingchallenge.models.Response.class.getClassLoader()));
    }

    public ISSResponse() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<Response> response) {
        this.response = response;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(request);
        dest.writeList(response);
    }

    public int describeContents() {
        return  0;
    }

}
