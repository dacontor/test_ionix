package com.daniel.testionix.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class DetailModel implements Parcelable {

    @Expose
    private String email;
    @Expose
    private String phone_number;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.email);
        dest.writeString(this.phone_number);
    }

    public DetailModel() {
    }

    protected DetailModel(Parcel in) {
        this.email = in.readString();
        this.phone_number = in.readString();
    }

    public static final Parcelable.Creator<DetailModel> CREATOR = new Parcelable.Creator<DetailModel>() {
        @Override
        public DetailModel createFromParcel(Parcel source) {
            return new DetailModel(source);
        }

        @Override
        public DetailModel[] newArray(int size) {
            return new DetailModel[size];
        }
    };
}
