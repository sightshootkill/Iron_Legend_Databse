package com.example.userdaniel.ironkingdoms.DialogAdapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.userdaniel.ironkingdoms.DialogFragments.SpellsInfoDialog;
import com.example.userdaniel.ironkingdoms.Models.Spell;
import com.example.userdaniel.ironkingdoms.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by UserDaniel on 5/22/16.
 */
public class SpellDialogAdapter extends BaseAdapter {

	// Declare Variables
	Context mContext;
	LayoutInflater inflater;
    SpellsInfoDialog mSpellsInfoDialog;
	private List<Spell> worldpopulationlist = null;
	private ArrayList<Spell> arraylist;

	public SpellDialogAdapter(Context context, List<Spell> worldpopulationlist, SpellsInfoDialog spellsInfoDialog) {
		mContext = context;
		this.worldpopulationlist = worldpopulationlist;
        this.mSpellsInfoDialog = spellsInfoDialog;
		inflater = LayoutInflater.from(mContext);
		this.arraylist = new ArrayList<>();
		this.arraylist.addAll(worldpopulationlist);
	}


	@Override
	public int getCount() {
		return worldpopulationlist.size();
	}

	@Override
	public Spell getItem(int position) {
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
                bundle.putString("cost", worldpopulationlist.get(position).getCost());
                bundle.putString("rng", worldpopulationlist.get(position).getRng());
                bundle.putString("aoe", worldpopulationlist.get(position).getAoe());
                bundle.putString("pow", worldpopulationlist.get(position).getPow());
                bundle.putString("up", worldpopulationlist.get(position).getUpkeep());
                bundle.putString("off", worldpopulationlist.get(position).getOffensive());
                bundle.putString("descript", worldpopulationlist.get(position).getDescription());

                SpellsInfoDialog.EditSpellsDialogListener activity = (SpellsInfoDialog.EditSpellsDialogListener) mContext;
                activity.onFinishSpellsDialog(worldpopulationlist.get(position).getName(), worldpopulationlist.get(position).getCost(),
                        worldpopulationlist.get(position).getRng(), worldpopulationlist.get(position).getAoe(),
                        worldpopulationlist.get(position).getPow(), worldpopulationlist.get(position).getUpkeep(),
                        worldpopulationlist.get(position).getOffensive(), worldpopulationlist.get(position).getDescription());
                mSpellsInfoDialog.dismiss();
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
			for (Spell ability : arraylist)
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