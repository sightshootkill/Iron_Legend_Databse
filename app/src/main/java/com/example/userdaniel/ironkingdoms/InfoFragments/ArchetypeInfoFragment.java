package com.example.userdaniel.ironkingdoms.InfoFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.R;

/**
 * Created by UserDaniel on 3/1/16.
 */
public class ArchetypeInfoFragment extends Fragment {



    public ArchetypeInfoFragment() {
        // Required empty public constructor
    }


     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archetype_info, container, false);

        TextView text1 = (TextView)view.findViewById(R.id.abilityTitle);
        TextView text3 = (TextView)view.findViewById(R.id.abilityDescriptionView);

        text1.setText(getArguments().getString("name"));
        text3.setText(getArguments().getString("description"));




        return view;

    }


}