package com.golden.coronaviruscases.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Country country);

    @Query("DELETE FROM country_table")
    void deleteAll();

    @Query("SELECT * FROM country_table ORDER BY name ASC")
    LiveData<List<Country>> getAllCountries();

    @Query("SELECT * FROM country_table WHERE name = :countryName")
    LiveData<Country> getCountry(String countryName);

    @Query("DELETE FROM country_table WHERE name = :countryName")
    void deleteCountry(String countryName);
}
