package com.example.isuyo_000.activities.UserData;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Iterator;

/**
 * Created by McLovin on 10/5/2017.
 */

public class PatientSettings implements Parcelable{
    private int id;
    private double[][] channels;
    private double[] amplitudeLimits;
    private double[] pulsewidthLimits;

    public static final int channelsLimit = 32;
    public static final int sizeLimit = 32;


    public PatientSettings(int id, double[][] channels, double[] amplitudeLimits, double[] pulsewidthLimits){
        this.id = id;

        this.channels = new double[channelsLimit][];
        double[] channel;
        for(int i = 0; i < channelsLimit; i++){
            //if channels are given; otherwise default to array of 0's
            if(i < channels.length) {
                changeChannelValues(i, channels[i]);
            }
            else{
                this.channels[i] = new double[sizeLimit];
            }
        }

        //sets amplitude initialization
        this.amplitudeLimits = new double[channelsLimit];
        initializeChannel(this.amplitudeLimits, amplitudeLimits);

        //sets pulsewidthLimits initialization
        this.pulsewidthLimits = new double[channelsLimit];
        initializeChannel(this.pulsewidthLimits, pulsewidthLimits);

    }

    public static final Creator<PatientSettings> CREATOR = new Creator<PatientSettings>() {
        @Override
        public PatientSettings createFromParcel(Parcel in) {
            return new PatientSettings(in);
        }

        @Override
        public PatientSettings[] newArray(int size) {
            return new PatientSettings[size];
        }
    };

    //changes specified channel's vlaues to input array; indices from 0-31
    private void changeChannelValues(int index, double[] channel) throws IllegalArgumentException{
        if(index >= sizeLimit){
            throw new IllegalArgumentException("chose a non-existent channel: Channel numbers must be between 0-" + (sizeLimit - 1) + "\n Given index was: " + index);
        }
        double[] newchannel = new double[sizeLimit];
        for (int i = 0; i < sizeLimit ; i++) {
            if(i < channel.length)
                newchannel[i] = channel[i];
            else
                newchannel[i] = 0;
        }

        this.channels[index] = newchannel;
    }

    //sets all amplitude and channel defaults in the array to default values of 0
    private void initializeChannel(double[] storage, double[] inputLimits){
        int inputLength;
        if(inputLimits == null)
            inputLength = 0;
        else
            inputLength = inputLimits.length;
        for(int i = 0; i < storage.length; i++) {
            if(i < inputLength)
                storage[i] = inputLimits[i];
            else
                storage[i] = 0;
        }
    }

    /**
     * Getters and Setters
     */
    public int getId(){
        return this.id;
    }

    //returns the channel at the specified index
    public double[] getChannel(int index) throws IllegalArgumentException{
        if(index >= sizeLimit){
            throw new IllegalArgumentException("chose a non-existent channel: Channel numbers must be between 0-" + (sizeLimit - 1) + "\n Given index was: " + index);
        }
        return this.channels[index];
    }

    //changes the current channel and returns the old values
    public double[] setChannel(int index, double[] channel)throws IllegalArgumentException{
        if(index >= sizeLimit){
            throw new IllegalArgumentException("chose a non-existent channel: Channel numbers must be between 0-" + (sizeLimit - 1) + "\n Given index was: " + index);
        }
        double[] oldchannel = this.channels[index];
        changeChannelValues(index, channel);
        return oldchannel;
    }

    //sets the amplitude channel limits for specified patient
    public void setAmplitudeLimits(Iterable<Double> limits){
        if(limits == null)
            return;
        int i = 0;
        for(Double limit : limits){
            amplitudeLimits[i] = limit;
            i++;
        }
    }

    //returns the amplitude limits for specified patient
    public double[] getAmplitudeLimits(){
        return amplitudeLimits;
    }

    //sets the pulse width limits for specified patient
    public void setPulsewidthLimits(Iterable<Double> limits){
        if(limits == null)
            return;
        int i = 0;
        for(Double limit : limits){
            pulsewidthLimits[i] = limit;
            i++;
        }
    }

    //returns the pulse width limits for specifiedd patient
    public double[] getPulsewidthLimits(){
        return pulsewidthLimits;
    }


    //Parcelable Implementation
    public PatientSettings(Parcel parcel){
        int id;
        double[][] channels;
        double[] amplitudeLimits = new double[channelsLimit];
        double[] pulsewidthLimits = new double[channelsLimit];

        id = parcel.readInt();
        parcel.readDoubleArray(amplitudeLimits);
        parcel.readDoubleArray(pulsewidthLimits);

        this.id = id;
        this.amplitudeLimits = amplitudeLimits;
        this.pulsewidthLimits = pulsewidthLimits;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDoubleArray(amplitudeLimits);
        dest.writeDoubleArray(pulsewidthLimits);
    }
}
