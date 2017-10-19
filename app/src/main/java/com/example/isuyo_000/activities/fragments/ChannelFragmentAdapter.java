package com.example.isuyo_000.activities.fragments;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;

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

public class ChannelFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String[] tabTitles = new String[]{"Tab1", "Tab2", "Tab3"};

    private Activity context;

    private int numChannels;

    private Map<Integer, String> tags;


    private List<String> channelTabs = new ArrayList<>();

    public ChannelFragmentAdapter(FragmentManager fm, Activity context, PatientSettings user) {
        super(fm);
        this.context = context;
        numChannels = user.channelsLimit;
        tags = new HashMap<>();
        for(int i = 1; i <= numChannels; i++){
            channelTabs.add("" + i);
        }
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

    }
}
