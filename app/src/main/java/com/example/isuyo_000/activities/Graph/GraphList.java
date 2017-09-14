package com.example.isuyo_000.activities.Graph;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.isuyo_000.activities.R;

import java.util.ArrayList;

/**
 * Created by McLovin on 9/7/2017.
 */

public class GraphList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multi_graph);

        populateListView();

    }

    //adds all items from data into list view
    private void populateListView(){
        ListView listView = (ListView) findViewById(R.id.userList);

        //create/find list of items
        ArrayList<Coordinates> graphs = new ArrayList<Coordinates>();
        for(int i = 0; i<10; i++)
            graphs.add(new Coordinates(1,1));

        //Build adapter
        GraphAdapter adapter = new GraphAdapter(graphs, this);

        //configure list view
        listView.setAdapter(adapter);
    }

}
