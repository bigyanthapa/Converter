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

public class DistanceFragment extends Fragment implements View.OnClickListener{

    private Button convertDistanceButton;
    private EditText distanceEditText;
    private Spinner distanceConvertToSpinner;
    private Spinner distanceConvertFromSpinner;

    private View rootView;

    public DistanceFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.distance_fragment, container, false);

        //call initialize method for widgets
        initialize();

        //add action listener for the button
        convertDistanceButton.setOnClickListener(this);

        return rootView;
    }

    //method to initialize
    public void initialize(){
        distanceEditText = (EditText)rootView.findViewById(R.id.inputDistanceEditText);
        convertDistanceButton = (Button) rootView.findViewById(R.id.convertDistanceBtn);
        distanceConvertFromSpinner = (Spinner)rootView.findViewById(R.id.distanceConvertFromSpinner);
        distanceConvertToSpinner = (Spinner)rootView.findViewById(R.id.distanceConvertToSpinner);
    }


    @Override
    public void onClick(View v) {

        Double input = Double.parseDouble(distanceEditText.getText().toString());
        Double convertedDistance = 0.0;

        // Index 0 = Kilometres, Index 1 = Miles

        int indexConvertFrom = distanceConvertFromSpinner.getSelectedItemPosition();

        //check inside each element
        switch (indexConvertFrom){
            //if Kilometres is selected in the first spinner, check what is selected in the second spinner
            case 0:
                int indexConvertTo = distanceConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, both are Kilometres to Kilometres , so simply assign the same value.
                    case 0:
                        distanceEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " Kilometres to Kilometres ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from kilometres to miles
                    case 1:
                        // 1 miles = 1.6 kilometres
                        convertedDistance = input*0.62;
                        distanceEditText.setText(Double.toString(convertedDistance));
                        Toast.makeText(getActivity(), " Kilometres to Miles ! ", Toast.LENGTH_SHORT).show();
                        break;

                } // Completed for Conversion of Kilometres to Others
                break;

            //if Miles is selected in the first spinner, check what is selected in the second spinner
            case 1 :
                indexConvertTo = distanceConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, we are converting from Miles to Kilometres
                    case 0:
                        convertedDistance = input * 1.62;
                        distanceEditText.setText(Double.toString(convertedDistance));
                        Toast.makeText(getActivity(), " Miles to Kilometres ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Miles to Miles
                    case 1:
                        distanceEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " Miles to Miles ! ", Toast.LENGTH_SHORT).show();
                        break;

                }// Completed for Conversion of Fahrenheit to Others
                break;

        }

    }
}
