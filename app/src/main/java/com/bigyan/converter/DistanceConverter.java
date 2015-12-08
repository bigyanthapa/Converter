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

import java.util.ArrayList;
import java.util.List;

public class DistanceConverter extends Activity implements AdapterView.OnItemSelectedListener{

    private Spinner convertFromSpinner,convertToSpinner ;
    private Button convertButton;
    private EditText inputText;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_converter);

        convertFromSpinner = (Spinner)findViewById(R.id.distance_convertFromSpinner);
        convertToSpinner = (Spinner)findViewById(R.id.distance_convertToSpinner);
        convertFromSpinner.setOnItemSelectedListener(this);

        convertButton = (Button)findViewById(R.id.distance_convertButton);
        inputText = (EditText)findViewById(R.id.distanceEditText);
        outputText = (TextView)findViewById(R.id.convertedDistance);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertDistance();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem= String.valueOf(convertFromSpinner.getSelectedItem());

        if(selectedItem.contentEquals("Kilometres")) {
            List<String> withoutNRS = new ArrayList<String>();
            withoutNRS.add("Miles");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutNRS);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter);
        }
        else if(selectedItem.contentEquals("Miles")) {
            List<String> withoutUSD = new ArrayList<String>();
            withoutUSD.add("Kilometres");

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutUSD);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //convert and display distance
    public void convertDistance(){

        //check if there in input value or not
        if (inputText.getText().toString().length() < 1) {
            Toast.makeText(this, "Please input a value to convert : ", Toast.LENGTH_LONG).show();
        }
        else {

            final String from_distance = String.valueOf(convertFromSpinner.getSelectedItem());
            final String to_distance = String.valueOf(convertToSpinner.getSelectedItem());

            final Double input_distance = Double.valueOf(inputText.getText().toString());
            inputText.setText("");
            Double convertedDistance = 0.0;

            //check inside each element
            switch (from_distance){
                //if Centigrade is selected in the first spinner, check what is selected in the second spinner
                case "Kilometres":
                    switch (to_distance){
                        //Convert Centigrade to Fahrenheit
                        case "Miles":
                            convertedDistance = input_distance*0.62;
                            outputText.setText(input_distance+" Km "+"= "+convertedDistance+" Mi");
                            break;
                    } // Completed for Conversion of Centigrade to Others
                    break;

                //if Fahrenheit is selected in the first spinner, check what is selected in the second spinner
                case "Miles" :
                    switch (to_distance){
                        //In this case, we are converting from Fahrenheit to Centigrade
                        case "Kilometres":
                            convertedDistance = input_distance * 1.62;
                            outputText.setText(input_distance + " Mi " + "= " + convertedDistance + " Km");
                            break;
                    }// Completed for Conversion of Fahrenheit to Others
                    break;
            }

        }

    }
}
