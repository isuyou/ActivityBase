package com.example.isuyo_000.activities.UserData;

/**
 * Created by McLovin on 10/5/2017.
 */

public class PatientSettings {
    private int id;
    private double[][] channels;

    public static final int channelsLimit = 32;
    public static final int sizeLimit = 32;


    public PatientSettings(int id, double[][] channels){
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
    }

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




}
