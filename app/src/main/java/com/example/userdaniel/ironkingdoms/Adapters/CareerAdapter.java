package com.example.userdaniel.ironkingdoms.Adapters;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.InfoFragments.ArchetypeInfoFragment;
import com.example.userdaniel.ironkingdoms.InfoFragments.CareerInfoFragment;
import com.example.userdaniel.ironkingdoms.Models.Career;
import com.example.userdaniel.ironkingdoms.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by UserDaniel on 6/20/16.
 */
public class CareerAdapter extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
	private List<Career> worldpopulationlist = null;
	private ArrayList<Career> arraylist;

	public CareerAdapter(Context context, List<Career> worldpopulationlist) {
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<>();
		this.arraylist.addAll(worldpopulationlist);
	}


	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public Career getItem(int position) {
		return worldpopulationlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		ViewHolder holder;

		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.list_item1, null);
			holder.name = (TextView) view.findViewById(R.id.simplecursortext1);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();

		}
		// Set the results into TextViews
		holder.name.setText(worldpopulationlist.get(position).getName());

		// Listen for ListView Item Click
		view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Bundle bundle = new Bundle();
                bundle.putString("name", worldpopulationlist.get(position).getName());
                bundle.putString("prereq", worldpopulationlist.get(position).getPrereq());
                bundle.putString("startingability", worldpopulationlist.get(position).getStartingability());
                bundle.putString("startingconnection", worldpopulationlist.get(position).getStartingconnection());
                bundle.putString("startingmilitary", worldpopulationlist.get(position).getStartingmilitary());
                bundle.putString("startingoccupational", worldpopulationlist.get(position).getStartingoccupational());
                bundle.putString("startingspells", worldpopulationlist.get(position).getStartingspells());
                bundle.putString("startingspecial", worldpopulationlist.get(position).getStartingspecial());
                bundle.putString("startingassets", worldpopulationlist.get(position).getStartingassets());
                bundle.putString("potentialability", worldpopulationlist.get(position).getPotentialability());
                bundle.putString("potentialconnection", worldpopulationlist.get(position).getPotentialconnection());
                bundle.putString("potentialmilitary", worldpopulationlist.get(position).getPotentialmilitary());
                bundle.putString("potentialoccupational", worldpopulationlist.get(position).getPotentialoccupational());


                Fragment newFragment = new CareerInfoFragment();
                ((Activity)mContext).getFragmentManager().beginTransaction()
                        .replace(R.id.frame_container, newFragment)
                        .addToBackStack(null)
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .commit();
                newFragment.setArguments(bundle);
            }
        });

		return view;
	}

	static class ViewHolder {
		TextView name;
	}

	// Filter Class
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		worldpopulationlist.clear();
		if (charText.length() == 0) {
			worldpopulationlist.addAll(arraylist);
		}
		else
		{
			for (Career ability : arraylist)
			{
				if (ability.getName().toLowerCase(Locale.getDefault()).contains(charText))
				{
					worldpopulationlist.add(ability);
				}
			}
		}
		notifyDataSetChanged();
	}

}