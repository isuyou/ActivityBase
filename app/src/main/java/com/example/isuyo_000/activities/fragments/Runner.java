package com.example.isuyo_000.activities.fragments;

/**
 * Created by McLovin on 10/18/2017.
 */
interface Executable{
    void execute();
}

public class Runner {
    public void run(Executable ex){
        ex.execute();
    }
}
