package com.example.isuyo_000.activities;

/**
 * Created by isuyo_000 on 7/31/2017.
 */

//generic data structure to hold and store variables names and their associated values across activities
public class Data<T> {
    public String name;
    public T value;


    public Data(String name, T input){

        this.name = name;
        this.value = input;
    }


    /**
     * getter methods
     */
    public String getName(){
        return name;
    }

    public T getValue(){
        return value;
    }




    /**
     * setter methods
     */
    public void  setName(String name){
        this.name = name;
    }

    /**
     *
     */
    public void setValue(T input){
        this.value = input;

    }

}
