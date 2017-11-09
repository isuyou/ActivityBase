package com.example.isuyo_000.activities.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

import com.example.isuyo_000.activities.CSV.CSVManager;
import com.example.isuyo_000.activities.JSon.JSonManager;
import com.example.isuyo_000.activities.MainActivity;
import com.example.isuyo_000.activities.R;
import com.example.isuyo_000.activities.UserData.PatientSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by isuyo_000 on 8/18/2017.
 */

public class ChannelFragmentAdapter extends FragmentStatePagerAdapter {
    final int PAGE_COUNT = 3;
    private String[] tabTitles = new String[]{"Tab1", "Tab2", "Tab3"};

    private AppCompatActivity context;

    private int numChannels;

    //Fragment tracking references
    private Map<Integer, String> tags;
    private FragmentManager fragmentManager;

    //Channel values holding
    private List<String> channelTabs = new ArrayList<>();
    private List<Double> amplitudes = new ArrayList<>();
    private List<Double> pulsewidths = new ArrayList<>();
    private PatientSettings user;




    public ChannelFragmentAdapter(FragmentManager fm, AppCompatActivity context, PatientSettings user) {
        super(fm);
        this.context = context;
        this.user = user;
        numChannels = user.channelsLimit;
        tags = new HashMap<>();
        for(int i = 1; i <= numChannels; i++){
            channelTabs.add("" + i);
        }
        this.fragmentManager = fm;
    }

    @Override
    public int getCount() {
        return channelTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ChannelFragment.newInstance(position + 1, this);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //generate title based on item position
        //return tabTitles[position];
        return  channelTabs.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        tags.put(position, createdFragment.getTag());
        //TODO ... save the tags somewhere so you can reference them later
        return createdFragment;
    }

    //saves data of all current channels onto the disk
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
                pulsewidths.add(currentChannel.getPulsewidth());
            }
        }

        //CSVManager.createCSV();
        JSonManager.saveData(context, user);
    }

    //loads data into all current channels on disk
    public void load(){

    }
}
