package com.example.userdaniel.ironkingdoms.DialogFragments;


import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.userdaniel.ironkingdoms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeleeWeaponInfoDialog extends DialogFragment implements View.OnClickListener {

    EditText edit1, edit2, edit3, edit4;


    public MeleeWeaponInfoDialog() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_info_dialog_layout4, container, false);
        Button bt1 = (Button)view.findViewById(R.id.meleeweaponValidate);
        bt1.setOnClickListener(this);
        edit1 = (EditText)view.findViewById(R.id.editText5);
        edit2 = (EditText)view.findViewById(R.id.editText6);
        edit3 = (EditText)view.findViewById(R.id.editText4);
        edit4 = (EditText)view.findViewById(R.id.editText7);
        if (getArguments().getInt("key") != 0) {
            edit1.setText(getArguments().getString("name"));
            edit2.setText(getArguments().getString("mat"));
            edit3.setText(getArguments().getString("pow"));
            edit4.setText(getArguments().getString("specialnotes"));
        }
        if ("Unnamed Melee".equals(edit1.getText().toString())) edit1.setText("");
        if ("0".equals(edit2.getText().toString())) edit2.setText("");
        if ("0".equals(edit3.getText().toString())) edit3.setText("");
        if ("Special Notes".equals(edit4.getText().toString())) edit4.setText("");
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
        EditMeleeWeaponDialogListener activity = (EditMeleeWeaponDialogListener)getActivity();
        if ("".equals(edit1.getText().toString())) edit1.setText("Unnamed Melee");
        if ("".equals(edit2.getText().toString())) edit2.setText("0");
        if ("".equals(edit3.getText().toString())) edit3.setText("0");
        if ("".equals(edit4.getText().toString())) edit4.setText("Special Notes");
        activity.onFinishMeleeWeaponDialog(edit1.getText().toString(), edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString());
        this.dismiss();

    }

    public interface EditMeleeWeaponDialogListener {
        void onFinishMeleeWeaponDialog(String inputText, String inputText2, String inputText3, String inputText4);
    }


}