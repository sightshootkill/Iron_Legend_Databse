package com.example.userdaniel.ironkingdoms.DialogFragments;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.R;

import java.util.Objects;

import static com.example.userdaniel.ironkingdoms.R.id.cancel_action;
import static com.example.userdaniel.ironkingdoms.R.id.scrollView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterStateInfoDialog extends DialogFragment implements View.OnClickListener {

    AutoCompleteTextView autoCompleteTextView;

    String[] armors  = {"Alchemist's Leather","Armored Great Coat","Custom Battle Armor","Leather Armor","Chain Mail","Infantry Armor","Tailored Plate","Full Plate","Storm Knight Armor"};



    public CharacterStateInfoDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.enter_info_dialog_layout7, container, false);
        Button bt1 = (Button)view.findViewById(R.id.armorinfoValidate);
        bt1.setOnClickListener(this);

        autoCompleteTextView = (AutoCompleteTextView)view.findViewById(R.id.autoCompleteTextView2);
        ArrayAdapter<String> autocompleteadapter = new ArrayAdapter<>(getActivity(), android.R.layout.select_dialog_item, armors);

        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setAdapter(autocompleteadapter);
        String stringtext1 = getArguments().getString("armorname");
        String stringtext2 = getArguments().getString("armbonus");
        String stringtext3 = getArguments().getString("spdmod");
        String stringtext4 = getArguments().getString("defmod");
        String stringtext5 = getArguments().getString("miscarm");
        String stringtext6 = getArguments().getString("shieldmod");
        String stringtext7 = getArguments().getString("racialdef");
        String stringtext8 = getArguments().getString("initequipmod");
        String stringtext9 = getArguments().getString("miscinit");
        String stringtext10 = getArguments().getString("cmd");
        String stringtext11 = getArguments().getString("cmdskill");
        final EditText edit2 = (EditText)view.findViewById(R.id.editText6);
        final EditText edit3 = (EditText)view.findViewById(R.id.editText4);
        final EditText edit4 = (EditText)view.findViewById(R.id.editText7);
        EditText edit5 = (EditText)view.findViewById(R.id.editText34);
        EditText edit6 = (EditText)view.findViewById(R.id.editText2);
        EditText edit7 = (EditText)view.findViewById(R.id.editText35);
        EditText edit8 = (EditText)view.findViewById(R.id.editText3);
        EditText edit9 = (EditText)view.findViewById(R.id.editText);
        EditText edit10 = (EditText)view.findViewById(R.id.editText14);
        EditText edit11 = (EditText)view.findViewById(R.id.editText15);
        autoCompleteTextView.setText(stringtext1);
        edit2.setText(stringtext2);
        edit3.setText(stringtext3);
        edit4.setText(stringtext4);
        edit5.setText(stringtext5);
        edit6.setText(stringtext6);
        edit7.setText(stringtext7);
        edit8.setText(stringtext8);
        edit9.setText(stringtext9);
        edit10.setText(stringtext10);
        edit11.setText(stringtext11);



        if (Objects.equals(stringtext1, "Armor Name")) autoCompleteTextView.setText("");
        if (Objects.equals(stringtext2, "0")) edit2.setText("");
        if (Objects.equals(stringtext3, "0")) edit3.setText("");
        if (Objects.equals(stringtext4, "0")) edit4.setText("");
        if (Objects.equals(stringtext5, "0")) edit5.setText("");
        if (Objects.equals(stringtext6, "0")) edit6.setText("");
        if (Objects.equals(stringtext7, "0")) edit7.setText("");
        if (Objects.equals(stringtext8, "0")) edit8.setText("");
        if (Objects.equals(stringtext9, "0")) edit9.setText("");
        if (Objects.equals(stringtext10, "0")) edit10.setText("");
        if (Objects.equals(stringtext11, "0")) edit11.setText("");

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
        EditCharacterStateDialogListener activity = (EditCharacterStateDialogListener)getActivity();
        EditText edit2 = (EditText)getView().findViewById(R.id.autoCompleteTextView2);
        EditText edit3 = (EditText)getView().findViewById(R.id.editText6);
        EditText edit4 = (EditText)getView().findViewById(R.id.editText4);
        EditText edit5 = (EditText)getView().findViewById(R.id.editText7);
        EditText edit6 = (EditText)getView().findViewById(R.id.editText34);
        EditText edit7 = (EditText)getView().findViewById(R.id.editText2);
        EditText edit8 = (EditText)getView().findViewById(R.id.editText35);
        EditText edit9 = (EditText)getView().findViewById(R.id.editText3);
        EditText edit10 = (EditText)getView().findViewById(R.id.editText);
        EditText edit11 = (EditText)getView().findViewById(R.id.editText14);
        EditText edit12 = (EditText)getView().findViewById(R.id.editText15);
        activity.onFinishCharacterStateDialog(edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString(), edit5.getText().toString(), edit6.getText().toString(),
                edit7.getText().toString(), edit8.getText().toString(), edit9.getText().toString(), edit10.getText().toString(), edit11.getText().toString(), edit12.getText().toString());

        this.dismiss();

    }

    public interface EditCharacterStateDialogListener {
        void onFinishCharacterStateDialog(String inputText, String inputText2, String inputText3, String inputText4, String inputText5, String inputText6, String inputText7, String inputText8, String inputText9, String inputText10, String inputText11);
    }




}
