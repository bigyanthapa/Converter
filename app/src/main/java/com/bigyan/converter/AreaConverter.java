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

public class AreaConverter extends Activity implements AdapterView.OnItemSelectedListener{

    //declare widgets
    private Spinner convertFromSpinner,convertToSpinner ;
    private Button convertButton;
    private EditText inputText;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_converter);

        convertFromSpinner = (Spinner)findViewById(R.id.area_convertFromSpinner);
        convertToSpinner = (Spinner)findViewById(R.id.area_convertToSpinner);
        convertFromSpinner.setOnItemSelectedListener(this);

        convertButton = (Button)findViewById(R.id.area_convertButton);
        inputText = (EditText)findViewById(R.id.areaEditText);
        outputText = (TextView)findViewById(R.id.convertedArea);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertArea();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem= String.valueOf(convertFromSpinner.getSelectedItem());

        if(selectedItem.contentEquals("Hectare")) {
            List<String> withoutNRS = new ArrayList<String>();
            withoutNRS.add("Acre");
            withoutNRS.add("Square Foot");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutNRS);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter);
        }
        else if(selectedItem.contentEquals("Acre")) {
            List<String> withoutUSD = new ArrayList<String>();
            withoutUSD.add("Hectare");
            withoutUSD.add("Square Foot");

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutUSD);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter2);
        }
        else if(selectedItem.contentEquals("Square Feet")) {
            List<String> withoutAUD = new ArrayList<String>();
            withoutAUD.add("Hectare");
            withoutAUD.add("Square Foot");

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

    //convert and display area
    public void convertArea(){

        //check if there in input value or not
        if (inputText.getText().toString().length() < 1) {
            Toast.makeText(this, "Please input a value to convert : ", Toast.LENGTH_LONG).show();
        }
        else {

            final String from_area = String.valueOf(convertFromSpinner.getSelectedItem());
            final String to_area = String.valueOf(convertToSpinner.getSelectedItem());

            final Double input_area = Double.valueOf(inputText.getText().toString());
            inputText.setText("");

            Double convertedArea = 0.0;

            //convert and display
            DecimalFormat format_2Places = new DecimalFormat("0.00");

            //check inside each element
            switch (from_area){
                case "Hectare":
                    switch (to_area){
                        //Convert Hectare to Acre
                        case "Acre":
                            convertedArea = Double.valueOf(format_2Places.format(input_area * 2.47105));
                            outputText.setText(input_area+" hec "+"= "+convertedArea+" Ac");
                            break;
                        //Convert Hectare to Sq. Foot
                        case "Square Foot":
                            convertedArea = Double.valueOf(format_2Places.format(input_area * 107639));
                            outputText.setText(input_area+" hec "+"= "+convertedArea+" Sq.Ft");
                            break;
                    }
                    break;

                //if Fahrenheit is selected in the first spinner, check what is selected in the second spinner
                case "Acre" :
                    switch (to_area){
                        //Convert Hectare to Acre
                        case "Hectare":
                            convertedArea = Double.valueOf(format_2Places.format(input_area / 2.47105));
                            outputText.setText(input_area+" Ac "+"= "+convertedArea+" hec");
                            break;
                        //Convert Hectare to Sq. Foot
                        case "Square Foot":
                            convertedArea = Double.valueOf(format_2Places.format(input_area * 43560));
                            outputText.setText(input_area+" Ac "+"= "+convertedArea+" Sq.Ft");
                            break;
                    }
                    break;

                //if Kelvin is selected in the first spinner, check what is selected in the second spinner
                case "Square Foot":
                    switch (to_area){
                        //Convert Hectare to Acre
                        case "Acre":
                            convertedArea = Double.valueOf(format_2Places.format(input_area / 43560));
                            outputText.setText(input_area+" Sq.Ft "+"= "+convertedArea+" Ac");
                            break;
                        //Convert Hectare to Sq. Foot
                        case "Hectare":
                            convertedArea = Double.valueOf(format_2Places.format(input_area/107639));
                            outputText.setText(input_area+" Sq.Ft "+"= "+convertedArea+" hec");
                            break;
                    }
                    break;
            }

        }


    }
}
