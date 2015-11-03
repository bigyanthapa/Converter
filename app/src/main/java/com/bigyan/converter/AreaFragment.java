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

public class AreaFragment extends Fragment implements View.OnClickListener {

    private Button convertAreaButton;
    private EditText areaEditText;
    private Spinner areaConvertToSpinner;
    private Spinner areaConvertFromSpinner;

    View rootView;

    public AreaFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.area_fragment, container, false);

        //call initialize method for widgets
        initialize();

        //add action listener for the button
        convertAreaButton.setOnClickListener(this);

        return rootView;
    }


    public void initialize(){
        areaEditText = (EditText)rootView.findViewById(R.id.inputAreaEditText);
        convertAreaButton = (Button) rootView.findViewById(R.id.convertAreaBtn);
        areaConvertFromSpinner = (Spinner)rootView.findViewById(R.id.areaConvertFromSpinner);
        areaConvertToSpinner = (Spinner)rootView.findViewById(R.id.areaConvertToSpinner);
    }


    @Override
    public void onClick(View v) {

        Toast.makeText(getActivity()," Sorry Feature Not Implemented Yet !",Toast.LENGTH_SHORT).show();

        /*Double input = Double.parseDouble(areaEditText.getText().toString());
        Double convertedWeight = 0.0;

        // Index 0 = Kilograms, Index 1 = LB Pounds, Index 3 = Grams

        int indexConvertFrom = areaConvertFromSpinner.getSelectedItemPosition();

        //check inside each element
        switch (indexConvertFrom){
            //if Kilograms is selected in the first spinner, check what is selected in the second spinner
            case 0:
                int indexConvertTo = areaConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, both are Kg to Kg , so simply assign the same value.
                    case 0:
                        areaEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " Kg to Kg ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from kilometres to miles
                    case 1:
                        // 1 Kg = 2.2 lb
                        convertedWeight = input*2.2;
                        areaEditText.setText(Double.toString(convertedWeight));
                        Toast.makeText(getActivity(), " Kg to lb ! ", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        // 1 Kg = 1000 grams
                        convertedWeight = input*1000;
                        areaEditText.setText(Double.toString(convertedWeight));
                        Toast.makeText(getActivity(), " Kg to lb ! ", Toast.LENGTH_SHORT).show();
                        break;

                } // Completed for Conversion of Kilometres to Others
                break;

            //if LB pounds is selected in the first spinner, check what is selected in the second spinner
            case 1 :
                indexConvertTo = areaConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, we are converting from pounds to Kg
                    case 0:
                        convertedWeight = input/2.2;
                        areaEditText.setText(Double.toString(convertedWeight));
                        Toast.makeText(getActivity(), " lb to Kg ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from lb to lb
                    case 1:
                        areaEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " lb to lb ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting lb to grams
                    case 2:
                        // 1 Kg = 2.2 lb
                        convertedWeight = (input/2.2) * 1000;
                        areaEditText.setText(Double.toString(convertedWeight));
                        Toast.makeText(getActivity(), " lb to grams ! ", Toast.LENGTH_SHORT).show();
                        break;

                }// Completed for Conversion of Fahrenheit to Others
                break;

            //if  Grams is selected in the first spinner, check what is selected in the second spinner
            case 2 :
                indexConvertTo = areaConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, we are converting from grams to Kg
                    case 0:
                        convertedWeight = input/1000;
                        areaEditText.setText(Double.toString(convertedWeight));
                        Toast.makeText(getActivity(), " Grams to Kg ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from grams to lb
                    case 1:
                        convertedWeight = (input/1000)*2.2;
                        areaEditText.setText(Double.toString(convertedWeight));
                        Toast.makeText(getActivity(), " Grams to lb ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting grams to grams
                    case 2:
                        areaEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " grams to grams ! ", Toast.LENGTH_SHORT).show();
                        break;

                }// Completed for Conversion of Fahrenheit to Others
                break;

        }*/

    }
}
