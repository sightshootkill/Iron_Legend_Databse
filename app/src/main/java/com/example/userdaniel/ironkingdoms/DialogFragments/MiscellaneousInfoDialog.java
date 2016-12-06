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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MiscellaneousInfoDialog extends DialogFragment implements View.OnClickListener {


    public MiscellaneousInfoDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.enter_info_dialog_layout9, container, false);
        Button bt1 = (Button)view.findViewById(R.id.button2);
        bt1.setOnClickListener(this);
        EditText edit2 = (EditText)view.findViewById(R.id.editText5);
        EditText edit3 = (EditText)view.findViewById(R.id.editText6);
        EditText edit4 = (EditText)view.findViewById(R.id.editText13);
        edit2.setText(getArguments().getString("connections"));
        edit3.setText(getArguments().getString("permanentinjuries"));
        edit4.setText(getArguments().getString("notes"));
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
        EditMiscellaneousDialogListener activity = (EditMiscellaneousDialogListener)getActivity();
        EditText edit2 = (EditText)getView().findViewById(R.id.editText5);
        EditText edit3 = (EditText)getView().findViewById(R.id.editText6);
        EditText edit4 = (EditText)getView().findViewById(R.id.editText13);
        activity.onFinishMiscellaneousDialog(edit2.getText().toString(), edit3.getText().toString(), edit4.getText().toString());
        this.dismiss();

    }

    public interface EditMiscellaneousDialogListener {
        void onFinishMiscellaneousDialog(String inputText, String inputText2, String inputText3);
    }


}
