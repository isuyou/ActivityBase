package com.example.isuyo_000.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.isuyo_000.activities.Graph.GraphList;
import com.example.isuyo_000.activities.UserData.PatientSettings;
import com.example.isuyo_000.activities.UserData.PatientSettingsExample;
import com.example.isuyo_000.activities.UserData.User;
import com.example.isuyo_000.activities.UserData.UserList;



public class FrontLayout extends AppCompatActivity {

    private final static int graphLayoutCode = 457;
    private final static int userListCode = 458;
    private final static int settingsCode = 459;

    User user;
    PatientSettings patientSettings;
    String userFileName;

    @Override
    /**
     * called when activity is started
     * initializes activity settings and attaches listeners to buttons
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_layout);
        //retrieve stored data through Intent
        //int age = getIntent().getIntExtra("user-age", -1);

        User p = (User) getIntent().getExtras().get("selectedUser");
        patientSettings = (PatientSettings) getIntent().getExtras().get("patientSettings");
        if(patientSettings == null)
            patientSettings = PatientSettingsExample.createExamplePatient();

        user = getIntent().getParcelableExtra("selectedUser");

        //Switch to GraphLayout
        Button toGraphLayout = (Button) findViewById(R.id.toGraphLayout);

        toGraphLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getApplicationContext(), GraphList.class);
                intent.putExtra("userSelected", user);
                //TODO normalize default user in program
                startActivityForResult(intent, graphLayoutCode);
            }
        });

        //switch to UserList
        Button toUsersScreen = (Button) findViewById(R.id.toUsers);

        toUsersScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(getApplicationContext(), UserList.class);
                intent.putExtra("userSelected", user);
                //TODO normalize default user in program
                startActivityForResult(intent, userListCode);
            }
        });


        //Switch to UserSettings
        Button toSettingsScreen = (Button) findViewById(R.id.toSettings);

        toSettingsScreen.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(getApplicationContext(), ChannelLimits.class);
                intent.putExtra("userSelected", user);
                intent.putExtra("userFileName", userFileName);
                //TODO normalize default user in program
                startActivityForResult(intent, settingsCode);
            }
        });
    }

    /**
     * called when the child activity from startActivityForResult(intent, code) is ended
     * handles data being passed back
     * @param requestCode the identifying integer indicating which activity data was received from
     * @param resultCode the status of how the activity was ended (forced or manually ended)
     * @param data the data being passed back to the parent activity bundled in Intent format
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == userListCode) {
            if(resultCode == RESULT_OK) {
                //TODO userList method
                this.userFileName = data.getParcelableExtra("userFileName");
            }
        }
        else if(requestCode == graphLayoutCode) {
            if(resultCode == RESULT_OK) {
                //TODO graphLayout method
            }
        }
        else if(requestCode == settingsCode){
            if(resultCode == RESULT_OK) {
                //TODO settings method
            }
        }
    }

    public void storeJson(){
    }


}
