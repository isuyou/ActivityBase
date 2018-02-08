package com.example.isuyo_000.activities.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.example.isuyo_000.activities.JSon.JSonManager;
import com.example.isuyo_000.activities.UserData.PatientSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adapter attaches ChannelFragment references to the ChannelLimits class
 * Creates and tracks all ChannelFragments
 * used PatientSettings data to initialize fragment variables
 */

public class ChannelFragmentAdapter extends FragmentStatePagerAdapter {
    final int PAGE_COUNT = 3;
    private String[] tabTitles = new String[]{"Tab1", "Tab2", "Tab3"};

    private AppCompatActivity activityContext;

    private int numChannels;

    //Fragment tracking references
    private Map<Integer, String> tags;
    private FragmentManager fragmentManager;

    //Channel values holding
    private List<String> channelTabs = new ArrayList<>();
    private List<Double> amplitudes = new ArrayList<>();
    private List<Double> pulsewidths = new ArrayList<>();
    private double[] amplitudeValues;
    private double[] pulsewidthValues;
    private PatientSettings user;




    public ChannelFragmentAdapter(FragmentManager fm, AppCompatActivity activityContext, PatientSettings user) {
        super(fm);
        this.activityContext = activityContext;
        this.user = user;
        numChannels = user.channelsLimit;
        amplitudeValues = user.getAmplitudeLimits();
        pulsewidthValues = user.getPulsewidthLimits();
        tags = new HashMap<>();
        for(int i = 1; i <= numChannels; i++){
            channelTabs.add("" + i);
        }
        this.fragmentManager = fm;
    }


    /**
     * @return the number of fragments/tabs/items created and referenced in the Adapter
     */
    @Override
    public int getCount() {
        return channelTabs.size();
    }

    /**
     * standard getItem method used during creation/attaching of an Adapter to AppActivity
     * @param position index of the fragment to created
     * @return fragment specific to the position
     */
    @Override
    public Fragment getItem(int position) {
        return ChannelFragment.newInstance(position, this, user);
    }

    /**
     * method called to attach the title of the tab for each fragment created
     * @param position index of the fragment referenced
     * @return character sequence representation of the index of fragment
     */
    @Override
    public CharSequence getPageTitle(int position) {
        //generate title based on item position
        //return tabTitles[position];
        return  channelTabs.get(position);
    }

    /**
     * Extension of standard Adapter instantiateItem method to allow automatic method
     * to create a fragment while saving the Tags for each fragment for future reference
     * @param container ViewGroup used in standard Adapter
     * @param position index of the fragment referenced
     * @return returns Fragment created after tag has been saved
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        tags.put(position, createdFragment.getTag());
        //TODO ... save the tags somewhere so you can reference them later
        return createdFragment;
    }

    //saves data of all current channels onto the disk
    public void save() {
        amplitudes = asList(amplitudeValues);
        pulsewidths = asList(pulsewidthValues);

        user.setAmplitudeLimits(amplitudes);
        user.setPulsewidthLimits(pulsewidths);
        JSonManager.saveData(activityContext, user);
    }

    //loads data into all current channels on disk
    public void load(){

    }

    //quits out of the adapter with desired data
    public void quit(){
        activityContext.finish();
    }




    //sets amplitude and pulse width values of selected channel

    /**
     * @param position index of channel to change amplitudes for
     * @param value value of the amplitude to set
     * @throws ArrayIndexOutOfBoundsException thrown if 'position'
     */
    public void setAmplitudeValue(int position, double value) throws ArrayIndexOutOfBoundsException{
        if(position > amplitudeValues.length){
            throw new ArrayIndexOutOfBoundsException("channel number too large: Does Not Exist in current activityContext");
        }
        amplitudeValues[position] = value;
    }

    public void setPulsewidthValue(int position, double value) throws ArrayIndexOutOfBoundsException{
        if(position > pulsewidthValues.length){
            throw new ArrayIndexOutOfBoundsException("channel number too large: Does Not Exist in current activityContext");
        }
        pulsewidthValues[position] = value;

    }


    /**
     * private conversion method to create list of Double from array of double
     * @param array double array to convert
     * @return converted List of Doubles from the input 'array'
     */
    public List<Double> asList(double[] array){
        ArrayList<Double> output = new ArrayList<>();
        for(double value : array){
            output.add(value);
        }
        return output;
    }
}
