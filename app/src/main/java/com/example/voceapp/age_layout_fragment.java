package com.example.voceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class age_layout_fragment extends Fragment {

    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View view = (ViewGroup) inflater.inflate(R.layout.age_layout_fragment, container,false);

        radioGroup = view.findViewById(R.id.radiogroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup group, int checkedId){
                switch (checkedId){
                    case R.id.b_age:
//                        open below age read activity
                        Intent intent = new Intent(getActivity(), below.class);
                        startActivity(intent);
                    case R.id.a_age:
//                        open above age read activity
                        Intent intentTwo = new Intent(getActivity(), above.class);
                        startActivity(intentTwo);

                }
            }
        });


        return view;
    }
}
