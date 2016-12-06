package com.example.userdaniel.ironkingdoms.BestiaryFragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlightedNyssStrider extends Fragment {


    public BlightedNyssStrider() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.armors_layout1, container, false);

        V.findViewById(R.id.abilitiesLayout6).setVisibility(View.GONE);
        return V;
    }


}
