package com.example.isuyo_000.activities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuyo_000 on 8/8/2017.
 */

public class User implements Parcelable {
    private int id;
    //need to create new object to safely contain all limits data
    private double[] limits;

    // Constructor
    public User(int id, double[] limits) {
        this.id = id;
        this.limits = limits;
    }


    //TODO
    //Getters and Setters
    public int getID(){
        return id;
    }

    // Parcelling part
    public User(Parcel in){
        int id;
        double[] limits = new double[32];

        id = in.readInt();
        in.readDoubleArray(limits);
        // the order needs to be the same as in writeToParcel() method
        this.id = id;
        this.limits = limits;
    }

    @Override
    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDoubleArray(limits);
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}