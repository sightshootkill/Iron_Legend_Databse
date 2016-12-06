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
public class BlightedNyssArcher extends Fragment {
    TextView t;


    public BlightedNyssArcher() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.armors_layout1, container, false);

        t = (TextView)V.findViewById(R.id.monsterTitle);
        t.setText("Blighted Nyss Archer");

        t = (TextView)V.findViewById(R.id.spd);
        t.setText("6");
        t = (TextView)V.findViewById(R.id.str);
        t.setText("4");
        t = (TextView)V.findViewById(R.id.mat);
        t.setText("5");
        t = (TextView)V.findViewById(R.id.rat);
        t.setText("5");
        t = (TextView)V.findViewById(R.id.def);
        t.setText("13");
        t = (TextView)V.findViewById(R.id.arm);
        t.setText("11");
        t = (TextView)V.findViewById(R.id.willpower);
        t.setText("7");
        t = (TextView)V.findViewById(R.id.initative);
        t.setText("13");
        t = (TextView)V.findViewById(R.id.detect);
        t.setText("6");
        t = (TextView)V.findViewById(R.id.sneak);
        t.setText("6");

        t = (TextView)V.findViewById(R.id.meleeweaponTitle1);
        t.setText("SWORD");
        t = (TextView)V.findViewById(R.id.pow);
        t.setText("3");
        t = (TextView)V.findViewById(R.id.powplusstr);
        t.setText("7");

        t = (TextView)V.findViewById(R.id.rangedweapontitle1);
        t.setText("NYSS BOW");
        t = (TextView)V.findViewById(R.id.rng);
        t.setText("12");
        t = (TextView)V.findViewById(R.id.aoe);
        t.setText("---");
        t = (TextView)V.findViewById(R.id.pow2);
        t.setText("10");

        V.findViewById(R.id.abilitiesLayout3).setVisibility(View.GONE);
        V.findViewById(R.id.abilitiesLayout4).setVisibility(View.GONE);
        V.findViewById(R.id.abilitiesLayout5).setVisibility(View.GONE);
        V.findViewById(R.id.abilitiesLayout6).setVisibility(View.GONE);

        t = (TextView)V.findViewById(R.id.vitality);
        t.setText("5");
        t = (TextView)V.findViewById(R.id.encounterPoints);
        t.setText("3");
        return V;
    }


}
