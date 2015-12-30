package com.bigyan.converter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TemperatureConverter extends Activity implements AdapterView.OnItemSelectedListener{

    //declare widgets
    private Spinner convertFromSpinner,convertToSpinner ;
    private Button convertButton;
    private EditText inputText;
    private TextView outputText;

    DecimalFormat numberFormat = new DecimalFormat("#.0000");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_converter);

        convertFromSpinner = (Spinner)findViewById(R.id.temperature_convertFromSpinner);
        convertToSpinner = (Spinner)findViewById(R.id.temperature_convertToSpinner);
        convertFromSpinner.setOnItemSelectedListener(this);

        convertButton = (Button)findViewById(R.id.temperature_convertButton);
        inputText = (EditText)findViewById(R.id.temperatureEditText);
        outputText = (TextView)findViewById(R.id.convertedTemperature);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem= String.valueOf(convertFromSpinner.getSelectedItem());

        if(selectedItem.contentEquals("Centigrade")) {
            List<String> withoutNRS = new ArrayList<String>();
            withoutNRS.add("Fahrenheit");
            withoutNRS.add("Kelvin");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutNRS);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter);
        }
        else if(selectedItem.contentEquals("Fahrenheit")) {
            List<String> withoutUSD = new ArrayList<String>();
            withoutUSD.add("Centigrade");
            withoutUSD.add("Kelvin");

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutUSD);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter2);
        }
        else if(selectedItem.contentEquals("Kelvin")) {
            List<String> withoutAUD = new ArrayList<String>();
            withoutAUD.add("Centigrade");
            withoutAUD.add("Fahrenheit");

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutAUD);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter2);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Method definition to convert and display temperature
    public void convertTemperature(){

        //check if there in input value or not
        if (inputText.getText().toString().length() < 1) {
            Toast.makeText(this, "Please input a value to convert : ", Toast.LENGTH_LONG).show();
        }
        else {

            final String from_temperature = String.valueOf(convertFromSpinner.getSelectedItem());
            final String to_temperature = String.valueOf(convertToSpinner.getSelectedItem());

            final Double input_temperature = Double.valueOf(inputText.getText().toString());
            inputText.setText("");

            Double convertedTemp = 0.0;

            //check inside each element
            switch (from_temperature){
                //if Centigrade is selected in the first spinner, check what is selected in the second spinner
                case "Centigrade":
                    switch (to_temperature){
                       //Convert Centigrade to Fahrenheit
                        case "Fahrenheit":
                            convertedTemp = (input_temperature * 1.8) + 32;
                            outputText.setText(input_temperature+" C "+"= "+convertedTemp+" F");
                            break;
                        //Convert Centigrade to Kelvin
                        case "Kelvin":
                            //Kelvin = Celsius + 273.15
                            convertedTemp = input_temperature + 273.15;
                            outputText.setText(numberFormat.format(input_temperature)+" C "+"= "+numberFormat.format(convertedTemp)+" K");
                            //Double convertedTemp = ((input-32.0)*5)/9;
                            break;
                    } // Completed for Conversion of Centigrade to Others
                    break;

                //if Fahrenheit is selected in the first spinner, check what is selected in the second spinner
                case "Fahrenheit" :
                    switch (to_temperature){
                        //In this case, we are converting from Fahrenheit to Centigrade
                        case "Centigrade":
                            convertedTemp = (input_temperature- 32)*5/9;
                            outputText.setText(numberFormat.format(input_temperature) + " F " + "= " + numberFormat.format(convertedTemp) + " C");
                            break;
                        //In this case, we are converting from Fahrenheit to Kelvin
                        case "Kelvin":
                            //Celsius = Kelvin -273.15 => Kelvin = Celsius + 273.15
                            convertedTemp = ((input_temperature- 32)/1.8) + 273.15;
                            outputText.setText(numberFormat.format(input_temperature) + " F " + "= " + numberFormat.format(convertedTemp) + " K");
                            break;
                    }// Completed for Conversion of Fahrenheit to Others
                    break;

                //if Kelvin is selected in the first spinner, check what is selected in the second spinner
                case "Kelvin":
                    switch (to_temperature){
                        //In this case, both are Kelvin to centigrade , so simply assign the same value.
                        case "Centigrade":
                            convertedTemp = input_temperature - 273.15;
                            outputText.setText(numberFormat.format(input_temperature) + " K " + "= " + numberFormat.format(convertedTemp) + " C");
                            break;
                        //In this case, we are converting from Kelvin to Fahrenheit
                        case "Fahrenheit":
                            convertedTemp = ((input_temperature - 273.15) * 1.8)+32;
                            outputText.setText(numberFormat.format(input_temperature) + " K" + "= "+numberFormat.format(convertedTemp)+" F");
                            break;
                    }// Completed for Conversion of Kelvin to Others
                    break;
            }

        }
    }
}
