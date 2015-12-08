package com.bigyan.converter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bigyan.converter.app.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CurrencyConverter extends Activity implements AdapterView.OnItemSelectedListener{

    private Spinner convertFromSpinner,convertToSpinner ;
    private Button convertButton;
    private EditText inputText;
    private TextView outputText;

    // Progress dialog
    private ProgressDialog pDialog;

    private String base_url = "https://openexchangerates.org/api/latest.json?app_id=eac4a2b4a79e4ea0b7d792194c20e1b8&base=USD";

    private final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        convertFromSpinner = (Spinner)findViewById(R.id.currency_convertFromSpinner);
        convertToSpinner = (Spinner)findViewById(R.id.currency_convertToSpinner);
        convertFromSpinner.setOnItemSelectedListener(this);

        convertButton = (Button)findViewById(R.id.currency_convertButton);
        inputText = (EditText)findViewById(R.id.currencyEditText);
        outputText = (TextView)findViewById(R.id.convertedCurrency);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Getting Rates...");
        pDialog.setCancelable(false);

        //initialize AQuery
       // aq = new AQuery(this);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //If Network is available, get data from Network, else get data from database.
                if (isNetworkAvailable()) {

                    // MY_PREFS_NAME - a static String variable like:
                    editor.clear();

                    editor.putString("name", "Elena");
                    editor.putInt("idName", 12);
                    editor.commit();
                    //if the network is available, delete the records from the db and insert the fresh ones
                   //db.deleteAll();

                    // then call the webservice
                    convertCurrency();
                } else {
                    //get data from database
                    //descriptionModelList = db.getAllSearch();
                }

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem= String.valueOf(convertFromSpinner.getSelectedItem());

        if(selectedItem.contentEquals("NPR")) {
            List<String> withoutNRS = new ArrayList<String>();
            withoutNRS.add("AUD");
            withoutNRS.add("EUR");
            withoutNRS.add("USD");

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutNRS);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter);
        }
        else if(selectedItem.contentEquals("USD")) {
            List<String> withoutUSD = new ArrayList<String>();
            withoutUSD.add("NPR");
            withoutUSD.add("AUD");
            withoutUSD.add("EUR");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutUSD);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter2);
        }
        else if(selectedItem.contentEquals("AUD")) {
            List<String> withoutAUD = new ArrayList<String>();
            withoutAUD.add("NPR");
            withoutAUD.add("USD");
            withoutAUD.add("EUR");
            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutAUD);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter2);
        }
        else if(selectedItem.contentEquals("EUR")) {
            List<String> withoutEUR = new ArrayList<String>();
            withoutEUR.add("NPR");
            withoutEUR.add("USD");
            withoutEUR.add("AUD");

            ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, withoutEUR);
            dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter2.notifyDataSetChanged();
            convertToSpinner.setAdapter(dataAdapter2);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Method to convert the currency
    public void convertCurrency() {


        //check if there is value in edittext or not
        if (inputText.getText().toString().length() < 1) {
            Toast.makeText(this, "Please input a value to convert : ", Toast.LENGTH_LONG).show();
        }
        else {

            final String from_currency = String.valueOf(convertFromSpinner.getSelectedItem());
            final String to_currency = String.valueOf(convertToSpinner.getSelectedItem());

            final Double input_currency = Double.valueOf(inputText.getText().toString());

            showpDialog();

            JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                    base_url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {

                    try {
                        // Parsing json object response
                        // response will be a json object

                        JSONObject rate = response.getJSONObject("rates");
                        //this gives equivalent of from_currency in USD
                        Double usdInFromCurrency = rate.getDouble(from_currency);
                        //this gives equivalent of to_currency in USD
                        Double usdInToCurrency = rate.getDouble(to_currency);

                        //convert and display
                        DecimalFormat format_2Places = new DecimalFormat("0.00");

                        Double actualRate = getActualRate(usdInFromCurrency, usdInToCurrency);

                        Double formatedOutput = Double.valueOf(format_2Places.format(input_currency * actualRate));
                        Double formatedInput = Double.valueOf(format_2Places.format(input_currency));

                        outputText.setText(formatedInput+" "+from_currency+" = "+formatedOutput +" "+to_currency);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                    hidepDialog();
                    inputText.setText("");
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),
                            error.getMessage(), Toast.LENGTH_SHORT).show();
                    // hide the progress dialog
                    hidepDialog();
                    inputText.setText("");
                }
            });

            // Adding request to request queue
            AppController.getInstance().addToRequestQueue(jsonObjReq);
        }
    }

    //getActualRate
    public Double getActualRate(Double usdFrom, Double usdTo){
        Double rate = usdTo/usdFrom;
        return rate;
    }

    //Check if Network is available or not
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }



    //Showing and hiding Dialog

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


}
