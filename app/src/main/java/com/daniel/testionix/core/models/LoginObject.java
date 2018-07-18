package com.daniel.testionix.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class LoginObject implements Parcelable {

    @Expose
    private int responseCode;
    @Expose
    private String description;
    @Expose
    private ResultObject result;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ResultObject getResult() {
        return result;
    }

    public void setResult(ResultObject result) {
        this.result = result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.responseCode);
        dest.writeString(this.description);
        dest.writeParcelable(this.result, flags);
    }

    public LoginObject() {
    }

    protected LoginObject(Parcel in) {
        this.responseCode = in.readInt();
        this.description = in.readString();
        this.result = in.readParcelable(ResultObject.class.getClassLoader());
    }

    public static final Parcelable.Creator<LoginObject> CREATOR = new Parcelable.Creator<LoginObject>() {
        @Override
        public LoginObject createFromParcel(Parcel source) {
            return new LoginObject(source);
        }

        @Override
        public LoginObject[] newArray(int size) {
            return new LoginObject[size];
        }
    };
}
