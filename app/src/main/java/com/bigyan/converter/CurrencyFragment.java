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

public class CurrencyFragment extends Fragment implements View.OnClickListener{

    private Button convertCurrencyButton;
    private EditText currencyEditText;
    private Spinner currencyConvertToSpinner;
    private Spinner currencyConvertFromSpinner;

    View rootView;

    public CurrencyFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.currency_fragment, container, false);

        //call initialize method for widgets
        initialize();

        //add action listener for the button
        convertCurrencyButton.setOnClickListener(this);

        return rootView;
    }

    public void initialize(){
        currencyEditText = (EditText)rootView.findViewById(R.id.inputCurrencyEditText);
        convertCurrencyButton = (Button) rootView.findViewById(R.id.convertCurrencyBtn);
        currencyConvertFromSpinner = (Spinner)rootView.findViewById(R.id.currencyConvertFromSpinner);
        currencyConvertToSpinner = (Spinner)rootView.findViewById(R.id.currencyConvertToSpinner);
        currencyEditText.setSelectAllOnFocus(true);
    }


    @Override
    public void onClick(View v) {

        Double input = Double.parseDouble(currencyEditText.getText().toString());
        Double convertedCurrency = 0.0;

        // Index 0 = NRS, Index 1 = US Dollar, Index 3 = Australian Dollar, Index 4 = European euro

        int indexConvertFrom = currencyConvertFromSpinner.getSelectedItemPosition();

        //check inside each element
        switch (indexConvertFrom){
            //if Nrs is selected in the first spinner, check what is selected in the second spinner
            case 0:
                int indexConvertTo = currencyConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, both are Nrs to Nrs , so simply assign the same value.
                    case 0:
                        currencyEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " Nrs to Nrs ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Nrs to US Dollars
                    case 1:
                        //$1 = Nrs 106
                        convertedCurrency = input*0.0096;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " Nrs to US Dollars ! ", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        // 1 Nrs = 0.013 Aus Dollars
                        convertedCurrency = input*0.013;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " Nrs to Aus Dollars ! ", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        // 1 Nrs = 0.0087 Euro
                        convertedCurrency = input*0.0087;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " Nrs to Aus Dollars ! ", Toast.LENGTH_SHORT).show();
                        break;

                } // Completed for Conversion of Kilometres to Others
                break;

            //if US Dollar is selected in the first spinner, check what is selected in the second spinner
            case 1 :
                indexConvertTo = currencyConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, we are converting from US Dollar to Nrs
                    case 0:
                        convertedCurrency = input*104.55;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " US Dollar to Nrs ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from US $ to US
                    case 1:
                        currencyEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " US $ to US $ ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting US $ to AUS $
                    case 2:
                        // 1 US = 1.40 AUS
                        convertedCurrency = input*1.40;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " US $ to AUS $ ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting US $ to Euro
                    case 3:
                        // 1 US = 0.91 Euro
                        convertedCurrency = input*0.91;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " US $ to Euro ! ", Toast.LENGTH_SHORT).show();
                        break;

                }// Completed for Conversion of US $  to Others
                break;

            //if  Australian is selected in the first spinner, check what is selected in the second spinner
            case 2 :
                indexConvertTo = currencyConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, we are converting from Aus $ to Nrs
                    case 0:
                        convertedCurrency = input*74.61;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " AUS $ to Nrs ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from AUS $ to US $
                    case 1:
                        convertedCurrency = input*0.71;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " AUS $ to US $ ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting AUS $ to AUS $
                    case 2:
                        currencyEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " AUS $ to AUS $ ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting AUS $ to Euro
                    case 3:
                        convertedCurrency = input*0.65;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " AUS $ to AUS $ ! ", Toast.LENGTH_SHORT).show();
                        break;

                }// Completed for AUS $ to Others
                break;
            //if  Euro is selected in the first spinner, check what is selected in the second spinner
            case 3:
                indexConvertTo = currencyConvertToSpinner.getSelectedItemPosition();
                switch (indexConvertTo){
                    //In this case, both are Euro to Nrs , so simply assign the same value.
                    case 0:
                        // 1 Euro = 115.10
                        convertedCurrency = input*115.10;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " Euro to Nrs ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Euro to US Dollar
                    case 1:
                        // 1 Euro = 1.10 US $
                        convertedCurrency = input*1.10;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " Euro to US $ ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Euro to Australian Dollar
                    case 2:
                        // 1 Euro = 1.54 Aus $
                        convertedCurrency = input*1.54;
                        currencyEditText.setText(Double.toString(convertedCurrency));
                        Toast.makeText(getActivity(), " Euro to AUS $ ! ", Toast.LENGTH_SHORT).show();
                        break;
                    //In this case, we are converting from Euro to Euro
                    case 3:
                        currencyEditText.setText(Double.toString(input));
                        Toast.makeText(getActivity(), " Euro to Euro ! ", Toast.LENGTH_SHORT).show();
                        break;

                } // Completed for Conversion of Euro to Others
                break;

        }

    }
}
