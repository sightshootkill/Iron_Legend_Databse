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
import android.widget.Toast;

import com.example.userdaniel.ironkingdoms.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacteristicsInfoDialog extends DialogFragment implements View.OnClickListener {


    public CharacteristicsInfoDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_info_dialog_layout2, container, false);
        Button bt1 = (Button)view.findViewById(R.id.charactersiticsValidateButton);
        bt1.setOnClickListener(this);
        String stringtext1 = getArguments().getString("phynum");
        String stringtext2 = getArguments().getString("spdnum");
        String stringtext3 = getArguments().getString("strnum");
        String stringtext4 = getArguments().getString("aginum");
        String stringtext5 = getArguments().getString("prwnum");
        String stringtext6 = getArguments().getString("poinum");
        String stringtext7 = getArguments().getString("intnum");
        String stringtext8 = getArguments().getString("arcnum");
        String stringtext9 = getArguments().getString("pernum");
        EditText edit1 = (EditText)view.findViewById(R.id.editText5);
        EditText edit2 = (EditText)view.findViewById(R.id.editText6);
        EditText edit3 = (EditText)view.findViewById(R.id.editText4);
        EditText edit4 = (EditText)view.findViewById(R.id.editText7);
        EditText edit5 = (EditText)view.findViewById(R.id.editText8);
        EditText edit6 = (EditText)view.findViewById(R.id.editText9);
        EditText edit7 = (EditText)view.findViewById(R.id.inteditfield);
        EditText edit8 = (EditText)view.findViewById(R.id.arceditfield);
        EditText edit9 = (EditText)view.findViewById(R.id.pereditfield);
        edit1.setText(stringtext1);
        edit2.setText(stringtext2);
        edit3.setText(stringtext3);
        edit4.setText(stringtext4);
        edit5.setText(stringtext5);
        edit6.setText(stringtext6);
        edit7.setText(stringtext7);
        edit8.setText(stringtext8);
        edit9.setText(stringtext9);

        if (Objects.equals(stringtext1, "0")) edit1.setText("");
        if (Objects.equals(stringtext2, "0")) edit2.setText("");
        if (Objects.equals(stringtext3, "0")) edit3.setText("");
        if (Objects.equals(stringtext4, "0")) edit4.setText("");
        if (Objects.equals(stringtext5, "0")) edit5.setText("");
        if (Objects.equals(stringtext6, "0")) edit6.setText("");
        if (Objects.equals(stringtext7, "0")) edit7.setText("");
        if (Objects.equals(stringtext8, "0")) edit8.setText("");
        if (Objects.equals(stringtext9, "0")) edit9.setText("");
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
        EditCharacteristicsDialogListener activity = (EditCharacteristicsDialogListener)getActivity();
        EditText edit1 = (EditText)getView().findViewById(R.id.editText5);
        EditText edit2 = (EditText)getView().findViewById(R.id.editText6);
        EditText edit3 = (EditText)getView().findViewById(R.id.editText4);
        EditText edit4 = (EditText)getView().findViewById(R.id.editText7);
        EditText edit5 = (EditText)getView().findViewById(R.id.editText8);
        EditText edit6 = (EditText)getView().findViewById(R.id.editText9);
        EditText edit7 = (EditText)getView().findViewById(R.id.inteditfield);
        EditText edit8 = (EditText)getView().findViewById(R.id.arceditfield);
        EditText edit9 = (EditText)getView().findViewById(R.id.pereditfield);

        if (edit1.getText().toString().equals("")) {
            edit1.setText("0");
        }
        if (edit4.getText().toString().equals("")) {
            edit4.setText("0");
        }
        if (edit7.getText().toString().equals("")) {
            edit7.setText("0");
        }

        activity.onFinishCharacteristicsDialog(edit1.getText().toString(), edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString(),
                edit5.getText().toString(), edit6.getText().toString(), edit7.getText().toString(), edit8.getText().toString(), edit9.getText().toString());
            this.dismiss();



    }

    public interface EditCharacteristicsDialogListener {
        void onFinishCharacteristicsDialog(String inputText, String inputText2, String inputText3, String inputText4,
                                           String inputText5, String inputText6, String inputText7, String inputText8,
                                           String inputText9);
    }




}
