package com.example.isuyo_000.activities.Fragments;

/**
 * Created by McLovin on 10/12/2017.
 */

public class ChannelFragmentGlobalVariables {

    public final static double benchmarkModifier = 2.0;


    //Deprecated saving using tags in ChannelFragmentAdapter
    /*
    public void save(){
        //TODO
        String tag;
        ChannelFragment temp;
        Channel currentChannel;

        for(int i = 0; i < tags.size(); i++){

            if( tags.get(i) != null){
                tag = tags.get(i);
                //TODO add in saving to file
                temp = (ChannelFragment) fragmentManager.findFragmentByTag(tag);
                currentChannel = temp.save();
                amplitudes.add(currentChannel.getAmplitude());
                pulsewidths.add(currentChannel.getPulseWidth());
            }
        }

        //CSVManager.createCSV();
        JSonManager.saveData(context, user);
    }
    */
}
