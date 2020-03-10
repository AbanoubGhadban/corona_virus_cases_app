package com.golden.coronaviruscases.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.golden.coronaviruscases.R;
import com.golden.coronaviruscases.model.Country;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {
    private final LayoutInflater mInflater;
    private List<Country> mCountries;

    CountryListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if (mCountries != null) {
            Country country = mCountries.get(position);
            holder.countryNameTv.setText(country.getName());
            holder.countryCasesTv.setText(String.valueOf(country.getCases()));
        } else {
            holder.countryNameTv.setText("No Country");
        }
    }

    @Override
    public int getItemCount() {
        if (mCountries == null)
            return 0;
        return mCountries.size();
    }

    public void setCountries(List<Country> countries) {
        mCountries = countries;
        notifyDataSetChanged();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        private final TextView countryNameTv;
        private final TextView countryCasesTv;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryNameTv = itemView.findViewById(R.id.tv_country_name);
            countryCasesTv = itemView.findViewById(R.id.tv_country_cases);
        }
    }
}
