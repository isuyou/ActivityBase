package com.example.isuyo_000.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 230;

    private User user = null;

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
                intent.putExtra("selectedUser", new User(-1, new double[32]));
                //TODO normalize default user in program
                startActivity(intent);
            }
        });
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
}