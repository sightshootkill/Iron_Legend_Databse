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
public class RangedWeaponInfoDialog extends DialogFragment implements View.OnClickListener {

    EditText edit1, edit2, edit3, edit4, edit5, edit6, edit7, edit8;


    public RangedWeaponInfoDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_info_dialog_layout5, container, false);
        Button bt1 = (Button)view.findViewById(R.id.rangedweaponValidate);
        bt1.setOnClickListener(this);
        edit1 = (EditText)view.findViewById(R.id.editText5);
        edit2 = (EditText)view.findViewById(R.id.editText6);
        edit3 = (EditText)view.findViewById(R.id.editText4);
        edit4 = (EditText)view.findViewById(R.id.editText7);
        edit5 = (EditText)view.findViewById(R.id.editText34);
        edit6 = (EditText)view.findViewById(R.id.editText10);
        edit7 = (EditText)view.findViewById(R.id.editText11);
        edit8 = (EditText)view.findViewById(R.id.editText35);
        if (getArguments().getInt("key") != 0) {
            edit1.setText(getArguments().getString("name"));
            edit2.setText(getArguments().getString("rat"));
            edit3.setText(getArguments().getString("rng"));
            edit4.setText(getArguments().getString("aoe"));
            edit5.setText(getArguments().getString("pow"));
            edit6.setText(getArguments().getString("ammocurrent"));
            edit7.setText(getArguments().getString("ammototal"));
            edit8.setText(getArguments().getString("specialnotes"));
        }
        if ("Unknown Ranged".equals(edit1.getText().toString())) edit1.setText("");
        if ("0".equals(edit2.getText().toString())) edit2.setText("");
        if ("––".equals(edit3.getText().toString())) edit3.setText("");
        if ("––".equals(edit4.getText().toString())) edit4.setText("");
        if ("––".equals(edit5.getText().toString())) edit5.setText("");
        if ("0".equals(edit6.getText().toString())) edit6.setText("");
        if ("0".equals(edit7.getText().toString())) edit7.setText("");
        if ("Special Notes".equals(edit8.getText().toString())) edit8.setText("");
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
        EditRangedWeaponDialogListener activity = (EditRangedWeaponDialogListener)getActivity();
        if ("".equals(edit1.getText().toString())) edit1.setText("Unknown Ranged");
        if ("".equals(edit2.getText().toString())) edit2.setText("0");
        if ("".equals(edit3.getText().toString())) edit3.setText("––");
        if ("".equals(edit4.getText().toString())) edit4.setText("––");
        if ("".equals(edit5.getText().toString())) edit5.setText("––");
        if ("".equals(edit6.getText().toString())) edit6.setText("0");
        if ("".equals(edit7.getText().toString())) edit7.setText("0");
        if ("".equals(edit8.getText().toString())) edit8.setText("Special Notes");
        activity.onFinishRangeWeaponDialog(edit1.getText().toString(), edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString(),
                edit5.getText().toString(), edit6.getText().toString(), edit7.getText().toString(), edit8.getText().toString());
        this.dismiss();

    }

    public interface EditRangedWeaponDialogListener {
        void onFinishRangeWeaponDialog(String inputText, String inputText2, String inputText3, String inputText4, String inputText5, String inputText6, String inputText7, String inputText8);
    }


}
