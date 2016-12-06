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
public class EquipmentInfoDialog extends DialogFragment implements View.OnClickListener {


    public EquipmentInfoDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_info_dialog_layout6, container, false);
        Button bt1 = (Button)view.findViewById(R.id.button2);
        bt1.setOnClickListener(this);
        String stringtext1 = getArguments().getString("money");
        String stringtext2 = getArguments().getString("equipment");
        EditText edit1 = (EditText)view.findViewById(R.id.editText5);
        EditText edit2 = (EditText)view.findViewById(R.id.editText6);
        edit1.setText(stringtext1);
        edit2.setText(stringtext2);
        if ("0".equals(edit1.getText().toString())) edit1.setText("");
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
        EditEquipmentDialogListener activity = (EditEquipmentDialogListener)getActivity();
        EditText edit2 = (EditText)getView().findViewById(R.id.editText5);
        EditText edit3 = (EditText)getView().findViewById(R.id.editText6);
        if ("".equals(edit2.getText().toString())) edit2.setText("0");
        activity.onFinishEquipmentDialog(edit2.getText().toString(), edit3.getText().toString());
        this.dismiss();

    }

    public interface EditEquipmentDialogListener {
        void onFinishEquipmentDialog(String inputText, String inputText2);
    }


}
