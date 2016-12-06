package com.example.userdaniel.ironkingdoms.DialogFragments;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillsInfoDialog extends DialogFragment implements View.OnClickListener {

    AutoCompleteTextView autoCompleteTextView;

    String[] skills  = {"Archery","Crossbow", "Great Weapon", "Hand Weapon", "Lance", "Light Artillery", "Pistol", "Rifle", "Shield", "Thrown Weapon", "Unarmed Combat", "Alchemy", "Bribery", "Command", "Cryptography", "Deception", "Disguise", "Escape Artist", "Etiquette", "Fell Calling", "Animal Handling", "Climbing", "Detection", "Driving", "Forensic Science", "Forgery"
            , "Interrogation", "Law", "Lock Picking", "Mechanikal Engineering", "Medicine", "Navigation", "Negotiation", "Oratory", "Gambling", "Intimidation", "Jumping", "Pickpocket", "Research", "Rope Use", "Sailing", "Seduction", "Sneak", "Streetwise", "Survival", "Tracking", "Riding", "Swimming"};

    public SkillsInfoDialog() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_info_dialog_layout8, container, false);
        Button bt1 = (Button)view.findViewById(R.id.button2);
        bt1.setOnClickListener(this);
        autoCompleteTextView = (AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextView);
        ArrayAdapter<String> autocompleteadapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, skills);

        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setAdapter(autocompleteadapter);


        return view;

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        EditSkillsDialogListener activity = (EditSkillsDialogListener)getActivity();
        EditText edit2 = (EditText)getView().findViewById(R.id.autoCompleteTextView);
        EditText edit3 = (EditText)getView().findViewById(R.id.editText6);
        if ("".equals(edit2.getText().toString())) edit2.setText("Unknown Skill");
        if ("".equals(edit3.getText().toString())) edit3.setText("0");
        activity.onFinishSkillsDialog(edit2.getText().toString(), edit3.getText().toString());
        this.dismiss();

    }

    public interface EditSkillsDialogListener {
        void onFinishSkillsDialog(String inputText, String inputText2);
    }


}
