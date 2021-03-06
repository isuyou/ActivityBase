package com.example.isuyo_000.activities.JSon;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.example.isuyo_000.activities.UserData.PatientSettings;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

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
    /**
     * creates a file with a PatientSettings formated user data into a Json formatted file
     *@param activity the reference to the specific activity/screen that calls the method
     *@param data the specific patient data to save into a Json formatted file
     *@return void after method called either:
     *      file has correctly been saved with patient data to /sdcard/hybrid_app/userData#.txt
     *      no change has been made: Error messagelogged
     */
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
    /**
     * retrieves a file with a PatientSettings formmated user data inot a Json formatted file
     *@param activity the reference to the specific activity/screen that calls the method
     *@param context reference to application context that file is being read to
     *@return PatientSetting converted object of patient data from file
     *      otherwise: error has occurred and JSonManagerException thrown
     */
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
            reader.close();
        }
        catch(Exception e){
            throw new JSonManagerException(JSonManagerException.ErrorType.JSonParsing, "file was malformed", e.getMessage());
        }

        return data;
    }


    //helper method to make a file within the target static directory

    /**
     * createDirectory()
     * @return returns the fileName and location of the filepath prefix
     * Creates the necessary folders/directories before returning full filepath prefix
     */
    private static File createDirectory(){

        //TODO might need to check if directory already exists
        File dir = new File(directoryLocation);
        dir.mkdirs();
        return dir;
    }
}
