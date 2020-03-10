package com.golden.coronaviruscases.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "country_table")
public class Country {

    @PrimaryKey
    @NonNull
    private
    String name;

    private int cases;

    public Country(@NonNull String name, int cases) {
        this.name = name;
        this.cases = cases;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }
}
