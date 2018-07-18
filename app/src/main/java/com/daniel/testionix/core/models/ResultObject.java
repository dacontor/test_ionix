package com.daniel.testionix.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ResultObject implements Parcelable {

    @Expose
    private List<ItemsModel> items = new ArrayList<>();

    public List<ItemsModel> getItems() {
        return items;
    }

    public void setItems(List<ItemsModel> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.items);
    }

    public ResultObject() {
    }

    protected ResultObject(Parcel in) {
        this.items = new ArrayList<ItemsModel>();
        in.readList(this.items, ItemsModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<ResultObject> CREATOR = new Parcelable.Creator<ResultObject>() {
        @Override
        public ResultObject createFromParcel(Parcel source) {
            return new ResultObject(source);
        }

        @Override
        public ResultObject[] newArray(int size) {
            return new ResultObject[size];
        }
    };
}
