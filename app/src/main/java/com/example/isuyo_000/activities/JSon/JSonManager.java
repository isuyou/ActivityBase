package com.example.isuyo_000.activities.JSon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.isuyo_000.activities.UserData.Data;
import com.example.isuyo_000.activities.UserData.PatientSettings;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by McLovin on 9/22/2017.
 */

public class JSonManager {
    private final static String directoryLocation = "/sdcard/hybrid_app/";


    //save data
    private void saveData(AppCompatActivity activity, PatientSettings data){
        createDirectory();
        Gson gson = new Gson();
        String s = gson.toJson(data);

        FileOutputStream outputStream;
        //TODO Error Handling
        try {
            outputStream = activity.openFileOutput(directoryLocation + "userData.txt", Context.MODE_PRIVATE);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //read data from Json format
    private void readData(AppCompatActivity activity, Context context){
        FileInputStream fis = null;
        //TODO error handling
        try {
            fis = activity.getApplicationContext().openFileInput(directoryLocation + "userData.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        //TODO error handling
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String json = sb.toString();
        Gson gson = new Gson();
        PatientSettings data = gson.fromJson(json, PatientSettings.class);
    }


    //helper method to make a file within the target static directory
    private File createDirectory(){

        //TODO might need to check if directory already exists
        File dir = new File(directoryLocation);
        dir.mkdirs();
        return dir;
    }
}
