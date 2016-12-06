package com.example.userdaniel.ironkingdoms.InfoFragments;


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
public class SpellsInfoFragment extends Fragment {


    public SpellsInfoFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spells_info, container, false);

        TextView text1 = (TextView)view.findViewById(R.id.abilityTitle);
        TextView text2 = (TextView)view.findViewById(R.id.prerequisiteView);
        TextView text3 = (TextView)view.findViewById(R.id.textView3);
        TextView text4 = (TextView)view.findViewById(R.id.textView7);
        TextView text5 = (TextView)view.findViewById(R.id.textView11);
        TextView text6 = (TextView)view.findViewById(R.id.textView297);
        TextView text7 = (TextView)view.findViewById(R.id.textView299);
        TextView text8 = (TextView)view.findViewById(R.id.abilityDescriptionView);

        text1.setText(getArguments().getString("name"));
        text2.setText(getArguments().getString("cost"));
        text3.setText(getArguments().getString("rng"));
        text4.setText(getArguments().getString("aoe"));
        text5.setText(getArguments().getString("pow"));
        text6.setText(getArguments().getString("up"));
        text7.setText(getArguments().getString("off"));
        text8.setText(getArguments().getString("descript"));


        return view;

    }


}
