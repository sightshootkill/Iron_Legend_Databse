package com.example.userdaniel.ironkingdoms.InfoFragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.DrawerListFragments.AbilitiesFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.SpellsFragment;
import com.example.userdaniel.ironkingdoms.R;

/**
 * Created by UserDaniel on 3/1/16.
 */
public class CareerInfoFragment extends Fragment {

    private ScrollView sc;
    private int yPos = 0;

    public CareerInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.careers_layout1, container, false);

        TextView name = (TextView)view.findViewById(R.id.careerTitle);
        TextView prereq = (TextView)view.findViewById(R.id.prerequisitesView);
        TextView startingabilities = (TextView)view.findViewById(R.id.startingAbilitiesView);
        TextView startingconnections = (TextView)view.findViewById(R.id.textView240);
        TextView startingmilitary = (TextView)view.findViewById(R.id.startingMilitarySkillsView);
        TextView startingoccupational = (TextView)view.findViewById(R.id.textView180);
        TextView startingspells = (TextView)view.findViewById(R.id.textView238);
        TextView startingspecial = (TextView)view.findViewById(R.id.startingOccupationalSkillsView);
        TextView startingassets = (TextView)view.findViewById(R.id.startingAssetsView);
        TextView potentialability = (TextView)view.findViewById(R.id.careerAbilitiesView);
        TextView potentialconnect = (TextView)view.findViewById(R.id.careerConnectionsView);
        TextView potentialmilitary = (TextView)view.findViewById(R.id.careerMilitarySkillsView);
        TextView potentialoccupational = (TextView)view.findViewById(R.id.textView242);
        TextView potentialspells = (TextView)view.findViewById(R.id.textView248);
        TextView startingObjectsTitle = (TextView)view.findViewById(R.id.startingObjectsTitle);


        LinearLayout connectionsLayout = (LinearLayout)view.findViewById(R.id.connectLayout);
        LinearLayout abilitiesLayout = (LinearLayout)view.findViewById(R.id.abilitiesLayout);
        LinearLayout spellsLayout = (LinearLayout)view.findViewById(R.id.spellsLayout);
        LinearLayout specialLayout = (LinearLayout)view.findViewById(R.id.specialLayout);

        TextView abilitiesTitle = (TextView)view.findViewById(R.id.careerAbilitiesTitle);
        TextView connectionsTitle = (TextView)view.findViewById(R.id.careerConnectionsTitle);
        TextView MilitaryTitle = (TextView)view.findViewById(R.id.careerMilitarySkillsTitle);
        TextView occupationalTitle = (TextView)view.findViewById(R.id.textView241);
        TextView spellsTitle = (TextView)view.findViewById(R.id.textView243);
        ImageView spellsImage = (ImageView)view.findViewById(R.id.imageView12);

        LinearLayout spellsBottomLayout = (LinearLayout)view.findViewById(R.id.linearLayout6);

        if (getArguments().getString("startingconnection").equals("")) {
            connectionsLayout.setVisibility(View.GONE);
        }

        if (getArguments().getString("startingspells").equals("")) {
            spellsLayout.setVisibility(View.GONE);
            spellsBottomLayout.setVisibility(View.GONE);
        }
        if (getArguments().getString("startingspecial").equals("")) {
            specialLayout.setVisibility(View.GONE);
        }
        if (getArguments().getString("startingconnection").equals("")) {
            connectionsLayout.setVisibility(View.GONE);
        }
        if (getArguments().getString("startingconnection").equals("")) {
            connectionsLayout.setVisibility(View.GONE);
        }
        if (getArguments().getString("startingability").equals("")) {
            abilitiesLayout.setVisibility(View.GONE);
        }





        name.setText(getArguments().getString("name"));
        prereq.setText(" " + getArguments().getString("prereq"));
        startingabilities.setText(getArguments().getString("startingability"));
        startingconnections.setText(getArguments().getString("startingconnection"));
        startingmilitary.setText(getArguments().getString("startingmilitary"));
        startingoccupational.setText(getArguments().getString("startingoccupational"));
        startingspells.setText(getArguments().getString("startingspells"));
        startingspecial.setText(getArguments().getString("startingspecial"));
        startingassets.setText(getArguments().getString("startingassets"));
        potentialability.setText(getArguments().getString("potentialability"));
        potentialconnect.setText(getArguments().getString("potentialconnection"));
        potentialmilitary.setText(getArguments().getString("potentialmilitary"));
        potentialoccupational.setText(getArguments().getString("potentialoccupational"));
        potentialspells.setText("Spells from the " + getArguments().getString("name") + " spell list");






        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/amplifier_light.otf");
        Typeface font2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/itc_fenice_it_regular.ttf");

        name.setTypeface(font);
        startingObjectsTitle.setTypeface(font2);
        abilitiesTitle.setTypeface(font2);
        abilitiesTitle.setText(getArguments().getString("name") + " Abilities");
        connectionsTitle.setTypeface(font2);
        connectionsTitle.setText(getArguments().getString("name") + " Connections");
        MilitaryTitle.setTypeface(font2);
        MilitaryTitle.setText(getArguments().getString("name") + " Military Skills");
        occupationalTitle.setTypeface(font2);
        occupationalTitle.setText(getArguments().getString("name") + " Occupational Skills");
        spellsTitle.setTypeface(font2);
        spellsTitle.setText(getArguments().getString("name") + " Spells");





        sc = (ScrollView) view.findViewById(R.id.scrollview1);

        ImageView abilitiesImage = (ImageView) view.findViewById(R.id.careerAbilitiesImage);
        abilitiesImage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // do something
                Fragment fragment = new AbilitiesFragment();
                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frame_container, fragment).commit();

            }
        });

        spellsImage.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                // do something
                Fragment fragment = new SpellsFragment();
                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().addToBackStack(null).setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frame_container, fragment).commit();

            }
        });




        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        yPos = sc.getScrollY();
    }

    @Override
    public void onResume() {
        super.onResume();
        sc.post(new Runnable() {
            public void run() {
                sc.scrollTo(0, yPos);
            }
        });
    }


}