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

import com.example.userdaniel.ironkingdoms.R;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterInfoDialog extends DialogFragment implements View.OnClickListener {






    public CharacterInfoDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_info_dialog_layout1, container, false);
        Button bt1 = (Button)view.findViewById(R.id.button2);
        bt1.setOnClickListener(this);
        String stringtext1 = getArguments().getString("character");
        String stringtext2 = getArguments().getString("race");
        String stringtext3 = getArguments().getString("career");
        String stringtext4 = getArguments().getString("archetype");
        EditText edit1 = (EditText)view.findViewById(R.id.editText5);
        EditText edit2 = (EditText)view.findViewById(R.id.editText6);
        EditText edit3 = (EditText)view.findViewById(R.id.editText4);
        EditText edit4 = (EditText)view.findViewById(R.id.editText7);
        edit1.setText(stringtext1);
        edit2.setText(stringtext2);
        edit3.setText(stringtext3);
        edit4.setText(stringtext4);

        if (Objects.equals(stringtext1, "CHARACTER NAME")) edit1.setText("");
        if (Objects.equals(stringtext2, "0")) edit2.setText("");
        if (Objects.equals(stringtext3, "0")) edit3.setText("");
        if (Objects.equals(stringtext4, "0")) edit4.setText("");
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
        EditCharacterDialogListener activity = (EditCharacterDialogListener)getActivity();
        EditText edit2 = (EditText)getView().findViewById(R.id.editText5);
        EditText edit3 = (EditText)getView().findViewById(R.id.editText6);
        EditText edit4 = (EditText)getView().findViewById(R.id.editText4);
        EditText edit5 = (EditText)getView().findViewById(R.id.editText7);
        activity.onFinishCharacterDialog(edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString(), edit5.getText().toString());
        this.dismiss();

    }

    public interface EditCharacterDialogListener {
        void onFinishCharacterDialog(String inputText, String inputText2, String inputText3, String inputText4);
    }




}
