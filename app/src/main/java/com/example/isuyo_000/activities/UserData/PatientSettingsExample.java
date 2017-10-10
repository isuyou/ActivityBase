package com.example.isuyo_000.activities.UserData;

/**
 * Created by McLovin on 10/10/2017.
 */

//default class to enable quick example creation of patient settings

public class PatientSettingsExample {

    public static PatientSettings createExamplePatient(int id){
        double[][] data = new double[32][];
        for(int i = 0; i < data.length; i++ ){
            data[i] = new double[32];
            for(int j = 0; j < data[i].length; j++){
                data[i][j] = i + ((double)(j))/100.0;
            }
        }

        return new PatientSettings(id, data);
    }

    public static PatientSettings createExamplePatient(){
        return createExamplePatient(0);
    }
}
