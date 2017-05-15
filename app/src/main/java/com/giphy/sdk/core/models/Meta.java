package com.giphy.sdk.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 5/4/17.
 */

public class Meta implements Parcelable {
    private int status;
    private String msg;
    @SerializedName("response_id")
    private String responseId;

    public Meta() {}

    public Meta(final Parcel in) {
        status = in.readInt();
        msg = in.readString();
        responseId = in.readString();
    }

    public Meta(int serverStatus, String message) {
        this.status = serverStatus;
        this.msg = message;
    }

    /**
     * @return error status code
     */
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return error message
     */
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return response id of the request
     */
    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public static final Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel in) {
            return new Meta(in);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(status);
        parcel.writeString(msg);
        parcel.writeString(responseId);
    }
}
