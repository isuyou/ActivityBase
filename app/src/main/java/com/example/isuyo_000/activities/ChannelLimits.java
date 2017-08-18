package com.example.isuyo_000.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.isuyo_000.activities.fragments.ChannelFragmentAdapter;

public class ChannelLimits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_tab);

        // Get the viewpager and set its pageradapter so that it can display
        // items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(
                new ChannelFragmentAdapter(getSupportFragmentManager(),
                        ChannelLimits.this));

        //Give the tablayout the viewpager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
