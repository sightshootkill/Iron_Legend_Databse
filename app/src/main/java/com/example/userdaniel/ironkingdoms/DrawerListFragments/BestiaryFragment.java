package com.example.userdaniel.ironkingdoms.DrawerListFragments;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.userdaniel.ironkingdoms.OtherFragments.CareerAdvancementTableFragment;
import com.example.userdaniel.ironkingdoms.OtherFragments.InjuryTableFragment;
import com.example.userdaniel.ironkingdoms.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BestiaryFragment extends ListFragment {


    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BestiaryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: Change Adapter to display your content
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.ironkingdoms_list_item_1, getResources().getStringArray(R.array.bestiary_items)));
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


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        displayView(position);

        //if (null != mListener) {
        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        //mListener.onFragmentInteraction(DummyContent.ITEMS.get(position).id);
        //}
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new CareerAdvancementTableFragment();
                break;
            case 1:
                fragment = new InjuryTableFragment();
                break;

            default:
                break;

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null).setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
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
