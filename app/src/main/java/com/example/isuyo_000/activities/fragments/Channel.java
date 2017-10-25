package com.example.isuyo_000.activities.fragments;

/**
 * Created by McLovin on 10/24/2017.
 */

public class Channel {
    private double amplitude;

    private double pulsewidth;



    public Channel(double amplitude, double pulsewidth){
        this.amplitude = amplitude;
        this.pulsewidth = pulsewidth;
    }

    public double getAmplitude(){
        return this.amplitude;
    }

    public double getPulsewidth(){
        return this.pulsewidth;
    }

}
