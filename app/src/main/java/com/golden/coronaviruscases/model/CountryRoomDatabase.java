package com.golden.coronaviruscases.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Country.class}, version = 1, exportSchema = false)
public abstract class CountryRoomDatabase extends RoomDatabase {
    private static CountryRoomDatabase INSTANCE;
    public abstract CountryDao countryDao();

    public static CountryRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
             synchronized (CountryRoomDatabase.class) {
                 if (INSTANCE == null) {
                     INSTANCE = Room.databaseBuilder(context,
                             CountryRoomDatabase.class, "country_db")
                             .fallbackToDestructiveMigration()
                             .addCallback(callback)
                             .build();
                 }
             }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final CountryDao mDao;
        String[] countryNames = {"Egypt", "USA", "UK", "Italy"};
        int[] countryCases = {50, 30, 10, 300};

        public PopulateDbAsync(CountryRoomDatabase db) {
            mDao = db.countryDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            for (int i = 0; i < countryNames.length; ++i) {
                mDao.insert(new Country(countryNames[i], countryCases[i]));
            }
            return null;
        }
    }
}
