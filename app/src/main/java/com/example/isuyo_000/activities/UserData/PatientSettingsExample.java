package com.example.isuyo_000.activities.UserData;

/**
 * Created by McLovin on 10/10/2017.
 */

//default class to enable quick example creation of patient settings

public class PatientSettingsExample {


    public static PatientSettings createExamplePatient(int id){
        //contained data for each channel's stimulation values
        double[][] data = new double[PatientSettings.channelsLimit][];
        for(int i = 0; i < data.length; i++ ){
            data[i] = new double[PatientSettings.sizeLimit];
            for(int j = 0; j < data[i].length; j++){
                data[i][j] = i + ((double)(j))/100.0;
            }
        }

        //contained data for channel amplitude limits
        double[] amplitudes = new double[PatientSettings.channelsLimit];
        for(int i = 0; i < data.length; i++){
            amplitudes[i] = i/(data.length/2);
        }


        //contained data for channel pulse width limits
        double[] pulseWidths = new double[PatientSettings.channelsLimit];
        for(int i = 0; i < data.length; i++){
            pulseWidths[i] = i/(data.length/2);
        }

        return new PatientSettings(id, data, amplitudes, pulseWidths);
    }

    public static PatientSettings createExamplePatient(){
        return createExamplePatient(0);
    }
}
