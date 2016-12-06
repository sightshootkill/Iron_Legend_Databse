package com.example.userdaniel.ironkingdoms.InfoFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.R;

/**
 * Created by UserDaniel on 2/26/16.
 */
public class MeleeWeaponInfoFragment extends Fragment {


    public MeleeWeaponInfoFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_melee_info, container, false);

        TextView text0 = (TextView)view.findViewById(R.id.itemTitle);
        TextView text1 = (TextView)view.findViewById(R.id.costView);
        TextView text2 = (TextView)view.findViewById(R.id.textView388);
        TextView text3 = (TextView)view.findViewById(R.id.textView389);
        TextView text4 = (TextView)view.findViewById(R.id.textView390);
        TextView text5 = (TextView)view.findViewById(R.id.descriptionView);
        TextView text6 = (TextView)view.findViewById(R.id.specialRulesView);

        String t0 = getArguments().getString("name");
        String t1 = getArguments().getString("cost");
        String t2 = getArguments().getString("skill");
        String t3 = getArguments().getString("atkmod");
        String t4 = getArguments().getString("pow");
        String t5 = getArguments().getString("descript");
        String t6 = getArguments().getString("special");


        text0.setText(t0);
        text1.setText(t1);
        text2.setText(t2);
        text3.setText(t3);
        text4.setText(t4);
        text5.setText(t5);
        text6.setText(t6);

        return view;

    }


}