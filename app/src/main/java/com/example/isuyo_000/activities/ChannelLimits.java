package com.example.isuyo_000.activities;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.isuyo_000.activities.JSon.JSonManager;
import com.example.isuyo_000.activities.UserData.PatientSettings;
import com.example.isuyo_000.activities.UserData.PatientSettingsExample;
import com.example.isuyo_000.activities.fragments.ChannelFragmentAdapter;
import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChannelLimits extends AppCompatActivity {

    PatientSettings user;
    String userFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_tab);

        //retrieves the current user being looked at
        userFileName =  getIntent().getStringExtra("userFileName");
        if(userFileName != null)
            user = loadData(userFileName);
        else
            user = PatientSettingsExample.createExamplePatient(1);

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


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }


    public PatientSettings loadData(){
        return JSonManager.readData(this, getApplicationContext());
    }

    public PatientSettings loadData(String userFileName){
        //TODO use the reference to the user file name in the JSon manager
        return JSonManager.readData(this,getApplicationContext());
    }


}
