package com.example.isuyo_000.activities.UserData;

/**
 * Created by isuyo_000 on 8/4/2017.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isuyo_000.activities.R;

import java.util.ArrayList;


public class UserList extends AppCompatActivity {

    private ArrayList<User> users= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_menu);


        //TODO initialize user entires
        attachUsers();


        populateListView();

    }

    private void attachUsers(){
        users.add(new User(1, new double[]{4.2, 5.6, 2.4, 5.5}));
        users.add(new User(2, new double[]{4.2, 5.6, 2.4, 5.5}));
        users.add(new User(3, new double[]{4.2, 5.6, 2.4, 5.5}));
        users.add(new User(4, new double[]{4.2, 5.6, 2.4, 5.5}));
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
        UserAdapter adapter = new UserAdapter(users, this);

        //configure list view
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new listSelect());
    }
    //determines the occurance of selecting an item from the user list
    private class listSelect implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            RelativeLayout line = (RelativeLayout) view;
            TextView textView = (TextView) line.findViewById(R.id.userID);
            String message = "you clicked # " + position +
                    " which is string: " + textView .getText().toString();
            Toast.makeText(UserList.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}

