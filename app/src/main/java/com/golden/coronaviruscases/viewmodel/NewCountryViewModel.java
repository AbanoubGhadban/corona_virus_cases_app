package com.golden.coronaviruscases.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.golden.coronaviruscases.model.Country;
import com.golden.coronaviruscases.model.CountryRepository;

import java.util.List;

public class NewCountryViewModel extends AndroidViewModel {
    CountryRepository repository;

    public NewCountryViewModel(@NonNull Application application) {
        super(application);
        repository = new CountryRepository(application);
    }

    public LiveData<List<Country>> getAllCountries() {
        return repository.getAllCountries();
    }

    public void delete(String countryName) {
        repository.delete(countryName);
    }

    public LiveData<Country> getCountry(String countryName) {
        return repository.getCountry(countryName);
    }
}
