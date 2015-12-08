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

public class WeightConverter extends Activity implements AdapterView.OnItemSelectedListener{

    //declare widgets
    private Spinner convertFromSpinner,convertToSpinner ;
    private Button convertButton;
    private EditText inputText;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_converter);

        convertFromSpinner = (Spinner)findViewById(R.id.weight_convertFromSpinner);
        convertToSpinner = (Spinner)findViewById(R.id.weight_convertToSpinner);
        convertFromSpinner.setOnItemSelectedListener(this);

        convertButton = (Button)findViewById(R.id.weight_convertButton);
        inputText = (EditText)findViewById(R.id.weightEditText);
        outputText = (TextView)findViewById(R.id.convertedWeight);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertWeight();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem= String.valueOf(convertFromSpinner.getSelectedItem());

        if(selectedItem.contentEquals("Kilo Grams")) {
            List<String> withoutNRS = new ArrayList<String>();
            withoutNRS.add("LB Pounds");
            withoutNRS.add("Grams");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutNRS);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter);
        }
        else if(selectedItem.contentEquals("LB Pounds")) {
            List<String> withoutUSD = new ArrayList<String>();
            withoutUSD.add("Kilo Grams");
            withoutUSD.add("Grams");

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutUSD);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter2);
        }
        else if(selectedItem.contentEquals("Grams")) {
            List<String> withoutAUD = new ArrayList<String>();
            withoutAUD.add("Kilo Grams");
            withoutAUD.add("LB Pounds");

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

    //convert and display Weights
    public void convertWeight(){

        //check if there in input value or not
        if (inputText.getText().toString().length() < 1) {
            Toast.makeText(this, "Please input a value to convert : ", Toast.LENGTH_LONG).show();
        }
        else {

            final String from_weight = String.valueOf(convertFromSpinner.getSelectedItem());
            final String to_weight = String.valueOf(convertToSpinner.getSelectedItem());

            final Double input_weight = Double.valueOf(inputText.getText().toString());
            inputText.setText("");

            //convert and display
            DecimalFormat format_2Places = new DecimalFormat("0.00");

            Double convertedWeight = 0.0;

            //check inside each element
            switch (from_weight){
                //if Centigrade is selected in the first spinner, check what is selected in the second spinner
                case "Kilo Grams":
                    switch (to_weight){
                        //Convert Kg to Pounds
                        case "LB Pounds":
                            convertedWeight = Double.valueOf(format_2Places.format(input_weight * 2.2));
                            outputText.setText(input_weight+" Kg "+"= "+convertedWeight+" Lb");
                            break;
                        //Convert Kg to Grams
                        case "Kelvin":
                            convertedWeight = Double.valueOf(format_2Places.format(input_weight * 1000));
                            outputText.setText(input_weight+" Kg "+"= "+convertedWeight+" g");
                            break;
                    }
                    break;
                case "LB Pounds" :
                    switch (to_weight){
                        case "Kilo Grams":
                            convertedWeight = Double.valueOf(format_2Places.format(input_weight/2.2));
                            outputText.setText(input_weight + " Lb " + "= " + convertedWeight + " Kg");
                            break;
                        //In this case, we are converting from Fahrenheit to Kelvin
                        case "Grams":
                            convertedWeight = Double.valueOf(format_2Places.format((input_weight/2.2) * 1000));
                            outputText.setText(input_weight + " Lb " + "= " + convertedWeight + " g");
                            break;
                    }//
                    break;
                case "Grams":
                    switch (to_weight){
                        //In this case, both are Kelvin to centigrade , so simply assign the same value.
                        case "Kilo Grams":
                            convertedWeight = Double.valueOf(format_2Places.format((input_weight/1000)));
                            outputText.setText(input_weight + " g " + "= " + convertedWeight + " Kg");
                            break;
                        //In this case, we are converting from Kelvin to Fahrenheit
                        case "LB Pounds":
                            convertedWeight = Double.valueOf(format_2Places.format((input_weight/1000)*2.2));
                            outputText.setText(input_weight + " g" + "= "+convertedWeight+" Lb");
                            break;
                    }// Completed for Conversion of Kelvin to Others
                    break;
            }

        }

    }
}
