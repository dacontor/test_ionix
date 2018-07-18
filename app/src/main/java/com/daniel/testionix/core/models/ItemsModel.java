package com.daniel.testionix.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class ItemsModel implements Parcelable {

    @Expose
    private String name;
    @Expose
    private DetailModel detail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DetailModel getDetail() {
        return detail;
    }

    public void setDetail(DetailModel detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeParcelable(this.detail, flags);
    }

    public ItemsModel() {
    }

    protected ItemsModel(Parcel in) {
        this.name = in.readString();
        this.detail = in.readParcelable(DetailModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<ItemsModel> CREATOR = new Parcelable.Creator<ItemsModel>() {
        @Override
        public ItemsModel createFromParcel(Parcel source) {
            return new ItemsModel(source);
        }

        @Override
        public ItemsModel[] newArray(int size) {
            return new ItemsModel[size];
        }
    };
}
