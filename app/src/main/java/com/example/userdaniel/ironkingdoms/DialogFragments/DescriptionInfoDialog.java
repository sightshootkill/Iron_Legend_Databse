package com.example.userdaniel.ironkingdoms.DialogFragments;


import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DescriptionInfoDialog extends DialogFragment implements View.OnClickListener {


    public DescriptionInfoDialog() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_info_dialog_layout3, container, false);
        Button bt1 = (Button)view.findViewById(R.id.descriptionValidate);
        bt1.setOnClickListener(this);
        String stringtext1 = getArguments().getString("gender");
        String stringtext2 = getArguments().getString("weight");
        String stringtext3 = getArguments().getString("height");
        String stringtext4 = getArguments().getString("hair");
        String stringtext5 = getArguments().getString("eyes");
        String stringtext6 = getArguments().getString("faith");
        String stringtext7 = getArguments().getString("definingcharacteristics");
        EditText edit1 = (EditText)view.findViewById(R.id.editText5);
        EditText edit2 = (EditText)view.findViewById(R.id.editText6);
        EditText edit3 = (EditText)view.findViewById(R.id.editText4);
        EditText edit4 = (EditText)view.findViewById(R.id.editText7);
        EditText edit5 = (EditText)view.findViewById(R.id.editText34);
        EditText edit6 = (EditText)view.findViewById(R.id.editText12);
        EditText edit7 = (EditText)view.findViewById(R.id.editText35);
        edit1.setText(stringtext1);
        edit2.setText(stringtext2);
        edit3.setText(stringtext3);
        edit4.setText(stringtext4);
        edit5.setText(stringtext5);
        edit6.setText(stringtext6);
        edit7.setText(stringtext7);

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
        EditDescriptionDialogListener activity = (EditDescriptionDialogListener)getActivity();
        EditText edit2 = (EditText)getView().findViewById(R.id.editText5);
        EditText edit3 = (EditText)getView().findViewById(R.id.editText6);
        EditText edit4 = (EditText)getView().findViewById(R.id.editText4);
        EditText edit5 = (EditText)getView().findViewById(R.id.editText7);
        EditText edit6 = (EditText)getView().findViewById(R.id.editText34);
        EditText edit7 = (EditText)getView().findViewById(R.id.editText12);
        EditText edit8 = (EditText)getView().findViewById(R.id.editText35);
        activity.onFinishDescriptionDialog(edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString(), edit5.getText().toString(),  edit6.getText().toString(), edit7.getText().toString(), edit8.getText().toString());
        this.dismiss();

    }

    public interface EditDescriptionDialogListener {
        void onFinishDescriptionDialog(String inputText, String inputText2, String inputText3, String inputText4, String inputText5, String inputText6, String inputText7);
    }


}
