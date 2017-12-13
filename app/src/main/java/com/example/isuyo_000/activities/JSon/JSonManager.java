package com.example.isuyo_000.activities.JSon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.isuyo_000.activities.UserData.Data;
import com.example.isuyo_000.activities.UserData.PatientSettings;
import com.example.isuyo_000.activities.UserData.PatientSettingsExample;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.MalformedJsonException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by McLovin on 9/22/2017.
 */

public class JSonManager {
    private final static String directoryLocation = "/sdcard/hybrid_app/";


    //save data
    public static void saveData(AppCompatActivity activity, PatientSettings data){
        createDirectory();
        Gson gson = new Gson();
        String s = gson.toJson(data);

        FileOutputStream outputStream;
        //TODO Error Handling
        try {
            File file = new File(directoryLocation + "userData.txt");
            boolean deleted = false;
            if(file.exists())
                deleted = file.delete();
            outputStream = new FileOutputStream(file, true);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //read data from Json format
    public static PatientSettings readData(AppCompatActivity activity, Context context) throws JSonManagerException{
        FileInputStream fis = null;
        //TODO error handling
        try {
            fis = new FileInputStream(new File(directoryLocation + "userData.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new JSonManagerException(JSonManagerException.ErrorType.File, "file did not load properly", e.getMessage());
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
            throw new JSonManagerException(JSonManagerException.ErrorType.File, "file did not read properly", e.getMessage());
        }

        String json = sb.toString();
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setLenient(true);
        Gson gson = new Gson();
        PatientSettings data;
        try {
            data = gson.fromJson(reader, PatientSettings.class);
        }
        catch(Exception e){
            throw new JSonManagerException(JSonManagerException.ErrorType.JSonParsing, "file was malformed", e.getMessage());
        }

        return data;
    }


    //helper method to make a file within the target static directory
    private static File createDirectory(){

        //TODO might need to check if directory already exists
        File dir = new File(directoryLocation);
        dir.mkdirs();
        return dir;
    }
}
