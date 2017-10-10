package com.example.isuyo_000.activities;

import com.example.isuyo_000.activities.UserData.Data;

/**
 * Created by isuyo_000 on 7/31/2017.
 */

public class GlobalVariables {

    public GlobalVariables(){
        Data<Integer> data = new Data<Integer>("time", 5);
        Class x = data.getValue().getClass();
    }
}

