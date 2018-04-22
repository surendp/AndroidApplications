package com.example.numberpickersession2;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.numberpickersession2.Model.Human;
import com.example.numberpickersession2.Model.MyNumberPicker;

public class MainActivity extends AppCompatActivity {

    private Human human;
    private TextView textViewBMI;

    private MyNumberPicker pickHeight;
    private MyNumberPicker pickWeight;

    private SharedPreferences preference;
    private SharedPreferences.Editor editPreference;

    private NumberPicker.OnValueChangeListener onValueChange = new NumberPicker.OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {
            if(numberPicker.getId() == R.id.numberPickerHeight){
                human.setHeight(newValue);
                showBMI();
            }else{
                human.setWeight(newValue);
                showBMI();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate preference to use saved date
        this.preference = getSharedPreferences("saveHumanData", MODE_PRIVATE);

        int height = this.preference.getInt("height", 0);
        int weight = this.preference.getInt("weight", 0);

        Log.d("MainActivity", "height: " + height
                + "\nweight: "+weight);

        // create human instance
        human = new Human(height,weight);

        // create the number picker instances
        this.pickHeight = new MyNumberPicker( this, this.human, findViewById(R.id.numberPickerHeight));
        this.pickWeight = new MyNumberPicker( this, this.human, findViewById(R.id.numberPickerWeight));


        //assign number picker min , max and initial values
        this.pickHeight.assignNumberPickerHeight();
        this.pickWeight.assignNumberPickerWeight();

        // set event listener to the number pickers
        this.pickHeight.setOnValueChangedListener(onValueChange);
        this.pickWeight.setOnValueChangedListener(onValueChange);

        // create text view instance to show the bmi
        this.textViewBMI = (TextView) findViewById(R.id.textViewBMI);
        showBMI();
    }

    @Override
    protected void onStop() {
        super.onStop();

        this.editPreference = this.preference.edit();
        this.editPreference.putInt("height", this.human.getHeight());
        this.editPreference.putInt("weight", this.human.getWeight());
        this.editPreference.apply();

    }

    private void showBMI(){
        this.textViewBMI.setText("BMI of current human is: "+human.getBMI());
    }

}