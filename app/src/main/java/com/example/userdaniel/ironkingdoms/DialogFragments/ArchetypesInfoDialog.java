package com.example.userdaniel.ironkingdoms.DialogFragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.userdaniel.ironkingdoms.Database;
import com.example.userdaniel.ironkingdoms.R;

import java.sql.SQLException;

/**
 * Created by UserDaniel on 3/1/16.
 */
public class ArchetypesInfoDialog extends DialogFragment{

    private Database dbHelper;
    private SimpleCursorAdapter dataAdapter;


    public ArchetypesInfoDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchable_list1, container, false);


        dbHelper = new Database(getActivity());
        try {
            dbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //clean all data
        dbHelper.deleteAllArchetypes();

        //add some data
        dbHelper.insertArchetypes();

        //generate listview
        Cursor cursor = dbHelper.fetchAllArchetypes();

        String[] columns = new String[] {
                Database.KEY_ARCHETYPES_NAME
        };

        int[] to = new int[] {
                R.id.simplecursortext1
        };

        dataAdapter = new SimpleCursorAdapter(getActivity(), R.layout.list_item1, cursor, columns, to, 0);

        final ListView listView = (ListView) view.findViewById(R.id.listView1);
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                String archetypename = cursor.getString(cursor.getColumnIndexOrThrow("archetypeName"));
                String archetypedescript = cursor.getString(cursor.getColumnIndexOrThrow("archetypeDescription"));




                EditArchetypeDialogListener activity = (EditArchetypeDialogListener)getActivity();

                activity.onFinishArchetypeDialog(archetypename, archetypedescript);

                getDialog().dismiss();

            }
        });

        EditText myFilter = (EditText)view.findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                try {
                    return dbHelper.fetchArchetypesByName(constraint.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });


        return view;

    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }


    public interface EditArchetypeDialogListener {
        void onFinishArchetypeDialog(String inputText, String inputText2);
    }



}
