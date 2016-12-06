package com.example.userdaniel.ironkingdoms.DrawerListFragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.userdaniel.ironkingdoms.InfoFragments.ArmourInfoFragment;
import com.example.userdaniel.ironkingdoms.Database;
import com.example.userdaniel.ironkingdoms.R;

import java.sql.SQLException;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ArmourFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private Database dbHelper;
    private SimpleCursorAdapter dataAdapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArmourFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchable_list1, container, false);


        dbHelper = new Database(getActivity());
        try {
            dbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //clean all data
        dbHelper.deleteAllArmor();

        //add some data
        dbHelper.insertArmor();

        //generate listview
        Cursor cursor = dbHelper.fetchAllArmor();

        String[] columns = new String[] {
                Database.KEY_ARMOR_NAME
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
                String name = cursor.getString(cursor.getColumnIndexOrThrow("armorName"));
                String cost = cursor.getString(cursor.getColumnIndexOrThrow("armorCost"));
                String spdmod = cursor.getString(cursor.getColumnIndexOrThrow("armorSPD"));
                String defmod = cursor.getString(cursor.getColumnIndexOrThrow("armorDEF"));
                String armod = cursor.getString(cursor.getColumnIndexOrThrow("armorARM"));
                String descript = cursor.getString(cursor.getColumnIndexOrThrow("armorDescript"));
                String special = cursor.getString(cursor.getColumnIndexOrThrow("armorSpecialRules"));



                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("cost", cost);
                bundle.putString("spdmod", spdmod);
                bundle.putString("defmod", defmod);
                bundle.putString("armod", armod);
                bundle.putString("descript", descript);
                bundle.putString("special", special);
                Fragment newFragment = new ArmourInfoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_container, newFragment);
                transaction.addToBackStack(null).setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

                newFragment.setArguments(bundle);

                transaction.commit();
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
                    return dbHelper.fetchArmorByName(constraint.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String id);
    }



}
