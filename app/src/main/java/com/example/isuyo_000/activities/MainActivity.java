package com.example.isuyo_000.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toActivityTwo = (Button) findViewById(R.id.toActivityTwo);

        toActivityTwo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Intent intent = new Intent(getApplicationContext(), FrontLayout.class);
                intent.putExtra("user-age", 30);
                intent.putExtra("user-name", "Roman");
                startActivity(intent);
            }
        });
    }
}
