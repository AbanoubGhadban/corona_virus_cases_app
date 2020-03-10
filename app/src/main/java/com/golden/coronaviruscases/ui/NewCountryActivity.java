package com.golden.coronaviruscases.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.golden.coronaviruscases.R;
import com.golden.coronaviruscases.model.Country;
import com.golden.coronaviruscases.viewmodel.NewCountryViewModel;

public class NewCountryActivity extends AppCompatActivity {
    public static final String EXTRA_COUNTRY_NAME = "EXTRA_COUNTRY_NAME";
    public static final String EXTRA_COUNTRY_CASES = "EXTRA_COUNTRY_CASES";

    private EditText countryNameEt;
    private EditText countryCasesEt;
    private NewCountryViewModel newCountryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_country);

        countryNameEt = findViewById(R.id.et_country_name);
        countryCasesEt = findViewById(R.id.et_country_cases);
        newCountryViewModel = ViewModelProviders.of(this).get(NewCountryViewModel.class);
    }

    public void onSave(View view) {
        Intent intent = new Intent();
        if (TextUtils.isEmpty(countryNameEt.getText()) || TextUtils.isEmpty(countryCasesEt.getText())) {
            Toast.makeText(getApplicationContext(), "Country Name or Cases is empty", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, intent);
        } else {
            intent.putExtra(EXTRA_COUNTRY_NAME, countryNameEt.getText().toString());
            int cases = Integer.parseInt(countryCasesEt.getText().toString());
            intent.putExtra(EXTRA_COUNTRY_CASES, cases);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    public void onDelete(View view) {
        newCountryViewModel.delete(countryNameEt.getText().toString());
        finish();
    }

    public void onQuery(View view) {
        final LiveData<Country> countryObserver = newCountryViewModel.getCountry(countryNameEt.getText().toString());
        countryObserver.observe(this, new Observer<Country>() {
            @Override
            public void onChanged(Country country) {
                if (country == null) {
                    Toast.makeText(getApplicationContext(), "Country not found", Toast.LENGTH_SHORT).show();
                    countryCasesEt.setText("");
                } else {
                    countryNameEt.setText(country.getName());
                    countryCasesEt.setText(String.valueOf(country.getCases()));
                }
                countryObserver.removeObserver(this);
            }
        });
    }
}
