package com.example.numberpickersession2.Model;

/**
 * Created by suren on 03/02/2018.
 */

public class Human {

    public static final int minHeight = 15;
    public static final int minWeight = 5;
    public static final int maxHeight = 300;
    public static final int maxWeight = 500;

    public static final int initialHeight = 160;
    public static final int initialWeight = 60;

    private int height;
    private int weight;

    //  If either of the parameters is
    // 0, constructor choose initial values
    public Human(int height, int weight){
        this.height = ((height == 0) ? initialHeight : height);
        this.weight = ((weight == 0) ? initialWeight : weight);
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWeight(){
        return this.weight;
    }

    public float getBMI(){
        return (float) (this.weight/(this.height*this.height*0.0001));
    }

}
