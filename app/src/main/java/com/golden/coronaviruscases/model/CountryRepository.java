package com.golden.coronaviruscases.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryRepository {
    CountryDao countryDao;
    LiveData<List<Country>> allCountries;

    public CountryRepository(Application application) {
        CountryRoomDatabase db = CountryRoomDatabase.getDatabase(application);
        countryDao = db.countryDao();
        allCountries = countryDao.getAllCountries();
    }

    public LiveData<List<Country>> getAllCountries() {
        return allCountries;
    }

    public LiveData<Country> getCountry(String countryName) {
        return countryDao.getCountry(countryName);
    }

    public void insert(Country country) {
        new InsertAsyncTask(countryDao).execute(country);
    }

    public void delete(String countryName) {
        new DeleteAsyncTask(countryDao).execute(countryName);
    }

    private static class InsertAsyncTask extends AsyncTask<Country, Void, Void> {
        private CountryDao asyncCountryDao;

        public InsertAsyncTask(CountryDao countryDao) {
            asyncCountryDao = countryDao;
        }

        @Override
        protected Void doInBackground(Country... countries) {
            asyncCountryDao.insert(countries[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<String, Void, Void> {
        private CountryDao asyncCountryDao;

        public DeleteAsyncTask(CountryDao countryDao) {
            asyncCountryDao = countryDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            asyncCountryDao.deleteCountry(strings[0]);
            return null;
        }
    }
}
