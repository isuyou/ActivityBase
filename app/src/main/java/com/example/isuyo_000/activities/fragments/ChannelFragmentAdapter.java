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

import com.example.isuyo_000.activities.MainActivity;
import com.example.isuyo_000.activities.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by isuyo_000 on 8/18/2017.
 */

public class ChannelFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String[] tabTitles = new String[]{"Tab1", "Tab2", "Tab3"};

    private Activity context;

    private int[] imageResId;

    {
        imageResId = new int[]{
        };
    }

    private List<String> channelTabs = new ArrayList<>();

    public ChannelFragmentAdapter(FragmentManager fm, Activity context) {
        super(fm);
        this.context = context;
        channelTabs.add("1");
        channelTabs.add("2");
        channelTabs.add("3");
    }

    @Override
    public int getCount() {
        return channelTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        return ChannelFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //generate title based on item position
        //return tabTitles[position];
        return  channelTabs.get(position);
    }
}
