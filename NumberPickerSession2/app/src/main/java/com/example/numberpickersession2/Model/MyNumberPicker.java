package com.example.numberpickersession2.Model;

import android.content.Context;
import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by suren on 04/03/2018.
 */

// creates custom number picker
public class MyNumberPicker extends NumberPicker {

    private Human human;
    private NumberPicker layoutNumberPicker;

    public MyNumberPicker(Context context, Human human, View layoutNumberPicker) {
        super(context);
        this.human = human;
        this.layoutNumberPicker = (NumberPicker) layoutNumberPicker;
    }

    // set on value changed listener to the number picker
    public void setOnValueChangedListener(OnValueChangeListener onValueChangeListener){
        layoutNumberPicker.setOnValueChangedListener(onValueChangeListener);
    }

    // Initialize the number picker for picking height
    public void assignNumberPickerHeight(){
        this.layoutNumberPicker.setMaxValue(this.human.maxHeight);
        this.layoutNumberPicker.setMinValue(this.human.minHeight);
        this.layoutNumberPicker.setValue(this.human.getHeight());
    }

    // Initialize the number picker for picking weight
    public void assignNumberPickerWeight(){
        this.layoutNumberPicker.setMaxValue(this.human.maxWeight);
        this.layoutNumberPicker.setMinValue(this.human.minWeight);
        this.layoutNumberPicker.setValue(this.human.getWeight());
    }


}
