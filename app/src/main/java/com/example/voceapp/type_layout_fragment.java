package com.example.voceapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.fragment.app.Fragment;

public class type_layout_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


//        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.type_layout_fragment, container,false);

        View view = inflater.inflate(R.layout.type_layout_fragment, container, false);
        Spinner spinner = view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return view;
    }
}
