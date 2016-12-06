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
public class BlightedNyssSwordsman extends Fragment {
    TextView t;


    public BlightedNyssSwordsman() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.armors_layout1, container, false);

        t = (TextView)V.findViewById(R.id.monsterTitle);
        t.setText("Blighted Nyss Swordsman");

        t = (TextView)V.findViewById(R.id.spd);
        t.setText("6");
        t = (TextView)V.findViewById(R.id.str);
        t.setText("7");
        t = (TextView)V.findViewById(R.id.mat);
        t.setText("7");
        t = (TextView)V.findViewById(R.id.rat);
        t.setText("4");
        t = (TextView)V.findViewById(R.id.def);
        t.setText("14");
        t = (TextView)V.findViewById(R.id.arm);
        t.setText("13");
        t = (TextView)V.findViewById(R.id.willpower);
        t.setText("10");
        t = (TextView)V.findViewById(R.id.initative);
        t.setText("16");
        t = (TextView)V.findViewById(R.id.detect);
        t.setText("6");
        t = (TextView)V.findViewById(R.id.sneak);
        t.setText("6");


        t = (TextView)V.findViewById(R.id.meleeweaponTitle1);
        t.setText("NYSS CLAYMORE");
        t = (TextView)V.findViewById(R.id.pow);
        t.setText("4");
        t = (TextView)V.findViewById(R.id.powplusstr);
        t.setText("11");

        V.findViewById(R.id.rangedicon).setVisibility(View.GONE);
        V.findViewById(R.id.rangedweaponinfoTable).setVisibility(View.GONE);

        t = (TextView)V.findViewById(R.id.abilityTitle1);
        t.setText("Nyss:");
        t = (TextView)V.findViewById(R.id.abilityDescript1);
        t.setText("This character gains +3 ARM against cold damage but suffers -3 ARM against fire damage.");
        t = (TextView)V.findViewById(R.id.abilityTitle2);
        t.setText("Fearless:");
        t = (TextView)V.findViewById(R.id.abilityDescript2);
        t.setText("This character never suffers the effects of Terror.");
        t = (TextView)V.findViewById(R.id.abilityTitle3);
        t.setText("Weapon Master");
        t = (TextView)V.findViewById(R.id.abilityDescript3);
        t.setText("This character gains an additional die on his melee damage rolls.");

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
