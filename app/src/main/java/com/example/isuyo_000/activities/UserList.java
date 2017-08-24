package com.example.isuyo_000.activities;

/**
 * Created by isuyo_000 on 8/4/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserList extends AppCompatActivity {

    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);



        populateListView();

    }

    //adds all items from data into list view
    private void populateListView(){
        ListView listView = (ListView) findViewById(R.id.userList);

        //create/find list of items
        String[] myItems = {"Blue", "Green", "Purple", "Red"};

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Click ListItem Number " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });

        //Build adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.line_basic,
                myItems);

        //configure list view
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new listSelect());
    }
    //determines the occurance of selecting an item from the user list
    private class listSelect implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView textView = (TextView) view;
            String message = "you clicked # " + position +
                    "which is string: " + textView.getText().toString();
            Toast.makeText(UserList.this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode == RESULT_OK){
            if (data.hasExtra("selectedUser")) {
                user = (data.getExtras().getParcelable("selectedUser"));
                Toast.makeText(
                        this,
                        "User found is :  " + user.getID(),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    //returns User selected to FrontLayout
    @Override
    public void onBackPressed(){
        Intent data = new Intent();
        data.putExtra("selectedUser", user);
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        finish();
    }
}

