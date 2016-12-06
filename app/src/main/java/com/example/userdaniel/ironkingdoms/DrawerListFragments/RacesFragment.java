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

import com.example.userdaniel.ironkingdoms.R;
import com.example.userdaniel.ironkingdoms.RacesFragments.DwarfFragment;
import com.example.userdaniel.ironkingdoms.RacesFragments.GobberFragment;
import com.example.userdaniel.ironkingdoms.RacesFragments.HumanFragment;
import com.example.userdaniel.ironkingdoms.RacesFragments.IosanFragment;
import com.example.userdaniel.ironkingdoms.RacesFragments.NyssFragment;
import com.example.userdaniel.ironkingdoms.RacesFragments.OgrunFragment;
import com.example.userdaniel.ironkingdoms.RacesFragments.SatyxisFragment;
import com.example.userdaniel.ironkingdoms.RacesFragments.TrollkinFragment;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class RacesFragment extends ListFragment {

    private OnFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RacesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: Change Adapter to display your content
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.ironkingdoms_list_item_1, getResources().getStringArray(R.array.races_items)));
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
                fragment = new HumanFragment();
                break;
            case 1:
                fragment = new DwarfFragment();
                break;
            case 2:
                fragment = new GobberFragment();
                break;
            case 3:
                fragment = new IosanFragment();
                break;
            case 4:
                fragment = new NyssFragment();
                break;
            case 5:
                fragment = new OgrunFragment();
                break;
            case 6:
                fragment = new TrollkinFragment();
                break;
            case 7:
                fragment = new SatyxisFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .addToBackStack(null).setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right).replace(R.id.frame_container, fragment).commit();

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
