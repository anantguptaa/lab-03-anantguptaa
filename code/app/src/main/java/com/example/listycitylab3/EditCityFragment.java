package com.example.listycitylab3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.io.Serializable;

public class EditCityFragment extends DialogFragment {

    private City city; // Holds the selected city
    private EditText cityNameEditText;
    private EditText provinceNameEditText;

    private CityArrayAdapter cityAdapter;

    // Factory method to create a new instance of the fragment
    public static EditCityFragment newInstance(City city, CityArrayAdapter adapter) {
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle();
        args.putSerializable("city", (Serializable) city);
        fragment.setArguments(args);
        fragment.cityAdapter = adapter;
        return fragment;
    }

    @Nullable
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_edit_city, null);
        city = (City) this.getArguments().getSerializable("city");

        cityNameEditText = view.findViewById(R.id.edit_text_city_text);
        provinceNameEditText = view.findViewById(R.id.edit_text_province_text);

        // Set existing city and province text in EditTexts
        if (city != null) {
            cityNameEditText.setText(city.getName());
            provinceNameEditText.setText(city.getProvince());
        }

        // Set up dialog buttons
        builder.setView(view)
                .setTitle("Edit City")
                .setPositiveButton("Save", (dialog, which) -> {
                    // Save changes to the city object
                    if (city != null) {
                        city.setName(cityNameEditText.getText().toString());
                        city.setProvince(provinceNameEditText.getText().toString());
                        cityAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel",null);

        return builder.create();
    }
}