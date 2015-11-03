package com.bigyan.converter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TemperatureFragment extends Fragment implements View.OnClickListener {

    public TemperatureFragment(){}

    private View rootView;

    private Button convertTempButton;
    private EditText temperatureEditText;
    private Spinner tempConvertToSpinner;
    private Spinner tempConvertFromSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        rootView = inflater.inflate(R.layout.temperature_fragment, container, false);

        //call initialize method for widgets
        initialize();

        //add action listener for the button
        convertTempButton.setOnClickListener(this);

        return rootView;

    }


    @Override
    public void onClick(View v) {
        Double input = Double.parseDouble(temperatureEditText.getText().toString());git
        Double convertedTemp = 0.0;

        //Case 1 : Convert centigrade to farhenheit
        // Index 0 = Centigrade, Index 1 = Fahrenheit, Index 2 = Kelvin

        int indexConvertFrom = tempConvertFromSpinner.getSelectedItemPosition();

        //check inside each element
        switch (indexConvertFrom){
            //if Centigrade is selected in the first spinner, check what is selected in the second spinner
            case 0:
                int indexConvertTo = tempConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, both are centigrade to centigrade , so simply assign the same value.
                    case 0:
                        temperatureEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " Centigrade to Centigrade ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Centigrade to farhenheit
                    case 1:

                        //Double convertedTemp = ((input-32.0)*5)/9;
                        convertedTemp = (input * 1.8) + 32;
                        temperatureEditText.setText(Double.toString(convertedTemp));
                        Toast.makeText(getActivity(), " Centigrade to Fahrenheit ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Centigrade to Kelvin
                    case 2:
                        //Celsius = Kelvin -273.15 => Kelvin = Celsius + 273.15
                        convertedTemp = input + 273.15;
                        temperatureEditText.setText(Double.toString(convertedTemp));
                        Toast.makeText(getActivity(), " Centigrade to Kelvin ! ", Toast.LENGTH_SHORT).show();
                        break;
                } // Completed for Conversion of Centigrade to Others
                break;

            //if Farhenheit is selected in the first spinner, check what is selected in the second spinner
            case 1 :
                indexConvertTo = tempConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, we are converting from Farhenheit to Centigrade
                    case 0:
                        convertedTemp = (input- 32)*5/9;
                        temperatureEditText.setText(Double.toString(convertedTemp));
                        Toast.makeText(getActivity(), " Fahrenheit to Centigrade ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Fahrenheit to Fahrenheit
                    case 1:
                        temperatureEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " Fahrenheit to Fahrenheit ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Fahrenheit to Kelvin
                    case 2:
                        //Celsius = Kelvin -273.15 => Kelvin = Celsius + 273.15
                        convertedTemp = ((input- 32)/1.8) + 273.15;
                        temperatureEditText.setText(Double.toString(convertedTemp));
                        Toast.makeText(getActivity(), " Fahrenheit to Kelvin ! ", Toast.LENGTH_SHORT).show();
                        break;
                }// Completed for Conversion of Fahrenheit to Others
                break;

            //if Kelvin is selected in the first spinner, check what is selected in the second spinner
            case 2:
                indexConvertTo = tempConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, both are Kelvin to centigrade , so simply assign the same value.
                    case 0:
                        convertedTemp = input - 273.15;
                        temperatureEditText.setText(Double.toString(convertedTemp));
                        Toast.makeText(getActivity(), " Kelvin to Centigrade ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Kelvin to Farhenheit
                    case 1:
                        convertedTemp = ((input - 273.15) * 1.8)+32;
                        temperatureEditText.setText(Double.toString(convertedTemp));
                        Toast.makeText(getActivity(), " Kelvin to Fahrenheit ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are from Kelvin to Kelvin
                    case 2:
                        temperatureEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " Kelvin to Kelvin ! ", Toast.LENGTH_SHORT).show();
                        break;
                }// Completed for Conversion of Kelvin to Others
                break;
        }

    }

    //method to initialize
    public void initialize(){
        temperatureEditText = (EditText)rootView.findViewById(R.id.inputTempEditText);
        convertTempButton = (Button) rootView.findViewById(R.id.convertTempBtn);
        tempConvertFromSpinner = (Spinner)rootView.findViewById(R.id.tempConvertFromSpinner);
        tempConvertToSpinner = (Spinner)rootView.findViewById(R.id.tempConvertToSpinner);
    }
}
