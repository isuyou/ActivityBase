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
import android.widget.Toast;

import com.example.isuyo_000.activities.JSon.JSonManager;
import com.example.isuyo_000.activities.JSon.JSonManagerException;
import com.example.isuyo_000.activities.UserData.PatientSettings;
import com.example.isuyo_000.activities.UserData.PatientSettingsExample;
import com.example.isuyo_000.activities.Fragments.ChannelFragmentAdapter;
import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChannelLimits extends AppCompatActivity {

    PatientSettings user;
    String userFileName;

    /**standard creation of Activity for android apps
     * initializes global variables and attaches adapters and layout items
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channel_tab);

        //retrieves the PatientSettings data current user being looked at, then puts data into local userData variable
        userFileName =  getIntent().getStringExtra("userFileName");
        if(userFileName != null) {
             user = loadData(userFileName);
            //user = PatientSettingsExample.createExamplePatient(1);
        }
        else {
            user = loadData();
            //user = PatientSettingsExample.createExamplePatient(1);
        }

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


    //press-down listener to exit the keyboard setting when a press is made outside the keyboard
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


    /**
     * returns PatientSettings data retrieved from default file
     * @return PatientSettings data retrieved
     */
    public PatientSettings loadData(){
        PatientSettings userData;
        try {
            userData = JSonManager.readData(this, getApplicationContext());
        }
        catch(JSonManagerException e){
            userData = PatientSettingsExample.createExamplePatient();
        }
        return userData;
    }

    /**
     *returns PatientSettings data retrieved from selected file
     * @param userFileName selected file by JSonManager filename protocol
     * @return PatientSettings data retrieved
     */
    public PatientSettings loadData(String userFileName){
        //TODO use the reference to the user file name in the JSon manager
        try {

            return JSonManager.readData(this, this.getApplicationContext());

        }
        catch(JSonManagerException e){
            Toast.makeText(getApplicationContext(),
                    "Error type: " + e.getErrorType() + "\n" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();

            return PatientSettingsExample.createExamplePatient();
        }
    }


}
