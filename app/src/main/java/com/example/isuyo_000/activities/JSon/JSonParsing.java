package com.example.isuyo_000.activities.JSon;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by McLovin on 9/19/2017.
 */

public class JSonParsing {
    public static void writeToFile(Context context, String fileName, String str){
        try{
            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(str.getBytes(), 0, str.length());
            outputStream.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String stringFromAsset(Context context, String assetFileName){
        AssetManager manager = context.getAssets();
        try{
            InputStream is = manager.open(assetFileName);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void readJson(View view){
        try {
            JSONObject item = new JSONObject("user");
            JSONArray users = new JSONArray("users");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
