package com.example.isuyo_000.activities.fragments;

/**
 * Created by McLovin on 10/24/2017.
 */

public class Channel {
    private double amplitude;

    private double pulsewidth;



    public Channel(double amplitude, double amplitudescale,  double pulsewidth, double pulsewidthscale){
        this.amplitude = (amplitude * 1.0)/(amplitude * 1.0);
        this.pulsewidth = (pulsewidth* 1.0)/ (pulsewidthscale *1.0);
    }

    public double getAmplitude(){ return this.amplitude;  }

    public double getPulseWidth(){ return this.pulsewidth;}

}
