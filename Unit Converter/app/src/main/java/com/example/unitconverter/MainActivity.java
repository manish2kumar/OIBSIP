package com.example.unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner fromSpinner;
    private Spinner toSpinner;
    private EditText fromEditText;
    private EditText toEditText;
    private Button btn_Convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fromSpinner = findViewById(R.id.fromSpinner);
        toSpinner = findViewById(R.id.toSpinner);
        fromEditText = findViewById(R.id.fromEditText);
        toEditText = findViewById(R.id.toEditText);
        btn_Convert = findViewById(R.id.btn_Convert);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        fromSpinner.setAdapter(adapter);
        toSpinner.setAdapter(adapter);

        fromSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        toSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convert();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        btn_Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
            }
        });
    }

    private void convert() {
        String fromUnit = fromSpinner.getSelectedItem().toString();
        String toUnit = toSpinner.getSelectedItem().toString();

        Double inputValue = null;
        try {
            inputValue = Double.parseDouble(fromEditText.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (inputValue != null) {
            double result = performConversion(fromUnit, toUnit, inputValue);
            toEditText.setText(String.valueOf(result));
        } else {
            toEditText.setText("");
        }
    }

    private double performConversion(String fromUnit, String toUnit, double value) {
        switch (fromUnit) {
            case "Celsius":
                switch (toUnit) {
                    case "Celsius":
                        return value;
                    case "Fahrenheit":
                        return (value * 9 / 5) + 32;
                    case "Kelvin":
                        return value + 273.15;
                    default:
                        return value;
                }
            case "Fahrenheit":
                switch (toUnit) {
                    case "Celsius":
                        return (value - 32) * 5 / 9;
                    case "Fahrenheit":
                        return value;
                    case "Kelvin":
                        return (value - 32) * 5 / 9 + 273.15;
                    default:
                        return value;
                }
            case "Kelvin":
                switch (toUnit) {
                    case "Celsius":
                        return value - 273.15;
                    case "Fahrenheit":
                        return (value - 273.15) * 9 / 5 + 32;
                    case "Kelvin":
                        return value;
                    default:
                        return value;
                }
            case "Meters":
                switch (toUnit) {
                    case "Meters":
                        return value;
                    case "Kilometers":
                        return value / 1000;
                    case "Miles":
                        return value / 1609.34;
                    default:
                        return value;
                }
            case "Kilometers":
                switch (toUnit) {
                    case "Meters":
                        return value * 1000;
                    case "Kilometers":
                        return value;
                    case "Miles":
                        return value / 1.60934;
                    default:
                        return value;
                }
            case "Miles":
                switch (toUnit) {
                    case "Meters":
                        return value * 1609.34;
                    case "Kilometers":
                        return value * 1.60934;
                    case "Miles":
                        return value;
                    default:
                        return value;
                }
            case "Seconds":
                switch (toUnit) {
                    case "Seconds":
                        return value;
                    case "Minutes":
                        return value / 60;
                    case "Hours":
                        return value / 3600;
                    default:
                        return value;
                }
            case "Minutes":
                switch (toUnit) {
                    case "Seconds":
                        return value * 60;
                    case "Minutes":
                        return value;
                    case "Hours":
                        return value / 60;
                    default:
                        return value;
                }
            case "Hours":
                switch (toUnit) {
                    case "Seconds":
                        return value * 3600;
                    case "Minutes":
                        return value * 60;
                    case "Hours":
                        return value;
                    default:
                        return value;
                }
            default:
                return value;
        }
    }

}
