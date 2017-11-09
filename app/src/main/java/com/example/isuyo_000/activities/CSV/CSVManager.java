package com.example.isuyo_000.activities.CSV;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.isuyo_000.activities.R;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by McLovin on 10/30/2017.
 */

public class CSVManager{

    public static void createCSV(){
        String csv = Environment.getExternalStorageDirectory() + "/csvfile.csv";
        createDirectory(Environment.getExternalStorageState());
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(csv));
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] {"India", "New Delhi"});
            data.add(new String[] {"United States", "Washington D.C"});
            data.add(new String[] {"Germany", "Berlin"});

            writer.writeAll(data);

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCSV(){

        try {
            File csvfile = new File(Environment.getExternalStorageDirectory() + "/csvfile.csv");
            CSVReader reader = new CSVReader(new FileReader(csvfile.getAbsolutePath()));
            String [] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //helper method to make a file within the target static directory
    private static File createDirectory(String directoryLocation){

        //TODO might need to check if directory already exists
        File dir = new File(directoryLocation);
        dir.mkdirs();
        return dir;
    }


}
