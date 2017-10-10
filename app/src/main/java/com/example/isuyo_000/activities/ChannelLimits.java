package com.example.isuyo_000.activities;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.isuyo_000.activities.UserData.PatientSettings;
import com.example.isuyo_000.activities.UserData.PatientSettingsExample;
import com.example.isuyo_000.activities.fragments.ChannelFragmentAdapter;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChannelLimits extends AppCompatActivity {

    PatientSettings user = PatientSettingsExample.createExamplePatient(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_tab);

        // Get the viewpager and set its pageradapter so that it can display
        // items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(
                new ChannelFragmentAdapter(getSupportFragmentManager(),
                        ChannelLimits.this, user));

        //Give the tablayout the viewpager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }


}
