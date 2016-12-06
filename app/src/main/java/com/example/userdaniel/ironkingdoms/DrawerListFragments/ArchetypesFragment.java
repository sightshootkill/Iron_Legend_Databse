package com.example.userdaniel.ironkingdoms.DrawerListFragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.example.userdaniel.ironkingdoms.Adapters.ArchetypeAdapter;
import com.example.userdaniel.ironkingdoms.Models.Archetype;
import com.example.userdaniel.ironkingdoms.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ArchetypesFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private ArrayList<Archetype> arrayList = new ArrayList<>();
    private ArchetypeAdapter archetypeAdapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ArchetypesFragment() {
    }

  @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchable_list1, container, false);

        String[][] archetypes = { {"Additional Study","The character delves further into the mysteries of the arcane and is rewarded with a spell from one of his career spell lists. This benefit can be taken multiple times, but a character still cannot exceed twice his INT in spells known."},
        {"Combat Caster","When this character makes a magic attack roll, he gains an additional die. Discard the lowest die of each roll."},
        {"Fast Caster","The character gains one extra quick action each activation that can be used only to cast a spell."},
        {"Feat: Dominator","The character can spend 1 feat point during his turn to double his control area for one round."},
        {"Feat: Powerful Caster","The character can spend 1 feat point when he casts a spell to increase the RNG (range) of the spell by twelve feet (2̋). Spells with a range of CTRL (control area) or SP (spray attack) are not affected."},
        {"Feat: Quick Cast","The character can spend 1 feat point to immediately cast one upkeep spell at the start of combat before the  rst round. When casting a spell as a result of this benefit, the character is not required to pay the COST of the spell."},
        {"Feat: Strength of Will","After failing a fatigue roll, the character can spend 1 feat point to instead automatically succeed on the roll. This benefit can be taken only by characters with the will weaver tradition."},
        {"Magic Sensitivity","The character can automatically sense when another character casts a spell within  fty feet for each point of his ARC stat. Such characters can tune out this detection as background noise but are aware of particularly powerful magic. Additionally, a character with the focuser tradition can sense other focusers within their detection range."},
        {"Rune Reader","The character can identify any spell cast in his line of sight by reading the accompanying spell runes. He can also learn the type of magic cast (the spell list it came from) and the tradition of the character casting the spell."},
        {"Warding Circle","The character can spend fifteen minutes to create a circle of warding runes around a small room or campsite. The names of the characters he intends to keep safe within the circle are incorporated into the runes. When any other character enters the circle, all named characters are alerted. While in the circle, non-named characters lose incorporeal, and non-named undead and infernal characters suffer –2 on attack rolls."},
        {"Battlefield Coordination","The character is a skilled battlefield commander. He is able to coordinate the movement and attacks of friendly forces to maximum effect. While in his command range, friendly characters do not suffer the firing into melee penalty for ranged attacks and spells and do not have a chance to hit friendly characters when they miss with ranged or magic attacks while firing into melee."},
        {"Feat: Flawless Timing","The character can spend 1 feat point to use this benefit during his turn. When he uses Flawless Timing, the character names an enemy. The next time that enemy directly hits him with an attack that encounter, the attack is instead considered to be a miss."},
        {"Feat: Prescient","The character can spend 1 feat point to win initiative automatically and take the  rst turn that combat. If two or more characters use this ability, they make initiative rolls to determine which of them goes first."},
        {"Feat: Perfect Plot","The character is a flawless planner and allows nothing to escape his attention. Assuming he is able to oversee all aspects of his plan, scout out the related sites, and do his research in great detail, he is sure to succeed. Of course this degree of planning takes time and care, but perfection is not without its cost. The character must spend 1 feat point to use this ability. A character following this character’s plans gains an additional die on non-combat related rolls during the day in which the plan was enacted."},
        {"Feat: Plan of Action","At the start of combat, the character can spend 1 feat point to use this benefit. During that combat, he and friendly characters who follow his plan gain +2 to their initiative rolls and +2 to their attack rolls during the  rst round of combat."},
        {"Feat: Quick Thinking","The character’s quick thinking enables him to act impossibly fast. Once per round, the character can spend 1 feat point to make one attack or quick action at the start of another character’s turn."},
        {"Feat: Unconventional Warfare","The character is quick thinking enough to assess any situation, see every potential angle and outcome, and use the environment itself as a weapon. He can use his attacks to off-balance foes and send them careening off ledges or into nearby vats of molten metal, cause them to stumble over terrain features, hit their weak spots to knock them to the ground, or otherwise maneuver them into a position of weakness and jeopardy. The character must spend 1 feat point to use this ability and explain to the Game Master how he is turning the environment against his enemy. The Game Master then determines the likely effect of the character’s action or attack. Outcomes include a boosted damage roll, knockdown, push, slam, or a fall from a height."},
        {"Genius","The character possesses an incredible aptitude for intellectual pursuits. The character’s INT rolls are boosted."},
        {"Hyper Perception","The character’s keen senses miss few details. The character’s PER rolls are boosted."},
        {"Photographic Memory","The character has a photographic memory and can recall every event in perfect detail. During play he can call upon his memory to ask the Game Master questions pertaining to anything he has seen or experienced."},
        {"Beat Back","When this character hits a target with a melee attack, he can immediately push his target 1̋ directly away. After the target is pushed, this character can advance up to 1 ̋."},
        {"Feat: Back Swing","Once per turn, this character can spend 1 feat point to gain one additional melee attack."},
        {"Feat: Bounding Leap","The character is capable of preternatural feats of athleticism. Once during each of his turns in which the character does not run or charge, he can spend 1 feat point to pitch himself over the heads of his enemies into the heart of battle. When the character uses this bene t, place him anywhere within 5̋ of his current location."},
        {"Feat: Counter Charge","When an enemy advances and ends its movement within thirty-six feet (6̋) of this character and in his line of sight, this character can immediately spend 1 feat point to charge the enemy. The character cannot make a counter charge while engaged."},
        {"Feat: Invulnerable","The character can spend 1 feat point during his turn to gain +3 ARM for one round."},
        {"Feat: Revitalize","The character can spend 1 feat point during his turn to regain a number of vitality points equal to his PHY stat immediately. If a character suffers damage during his turn, the damage must be resolved before a character can use this feat. An incapacitated character cannot use Revitalize."},
        {"Feat: Shield Breaker","When this character hits a target that has a shield with a melee attack, the character can spend 1 feat point to use this benefit. When the character uses this benefit, after damage has been dealt the other character’s shield is completely destroyed as a result of the attack."},
        {"Feat: Vendetta","The character can spend 1 feat point during his turn to use this benefit. When this ability is used the character names one enemy. For the rest of the encounter, this character gains boosted attack rolls against that enemy. A character can use this bene t only once per encounter unless the original subject of his vendetta is destroyed, at which point the character can spend a feat point to use this benefit again."},
        {"Righteous Anger","When one or more characters who are friendly to this character are damaged by an enemy attack while in this character’s command range, this character gains +2 STR and ARM for one round."},
        {"Tough","The character is incredibly hardy. When this character is disabled, roll a d6. On a 5 or 6, the character heals 1 vitality point, is no longer disabled, and is knocked down."},
        {"Ambidextrous","The character does not suffer the normal attack roll penalty with a second weapon while using the Two-Weapon Fighting ability."},
        {"Cagey","When this character becomes knocked down, he can immediately move up to twelve feet (2̋) and cannot be targeted by free strikes during this movement. This benefit has no effect while this character is mounted. While knocked down, this character is not automatically hit by melee attacks and his DEF is not reduced. The character can stand up during his turn without forfeiting his movement or action."},
        {"Deft","The character has nimble fingers and steady hands. The character gains boosted AGL rolls."},
        {"Feat: Defensive Strike","When an enemy advances into and ends its movement in this character’s melee range, this character can spend 1 feat point to immediately make one melee attack targeting it."},
        {"Feat: Disarm","After directly hitting an enemy with a non- spray, non-AOE (area of effect) ranged or melee attack, instead of making a damage roll, the character can spend 1 feat point to disarm his opponent. When this bene t is used, the enemy’s weapon, or any object in his hand,  ies from his grasp. He suffers no damage from the attack."},
        {"Feat: Swashbuckler","Once during each of his turns, this character can spend 1 feat point to use Swashbuckler. The next time this character makes an attack with a hand weapon after using this bene t, his front arc extends to 360°, and he can make one melee attack against each enemy in his line of sight in his melee range. Regardless of the number of characters hit, Swashbuckler can trigger the Sidestep benefit only once."},
        {"Feat: Untouchable","The character can spend 1 feat point during his turn to gain +3 DEF for one round."},
        {"Preternatural Awareness","The character’s uncanny perception keeps him constantly aware of his surroundings. The character gains boosted Initiative rolls. Additionally, enemies never gain back strike bonuses against this character."},
        {"Sidestep","When this character hits an enemy character with a melee weapon, he can advance up to 2̋ after the attack is resolved. This character cannot be targeted by free strikes during this movement."},
        {"Virtuoso","Choose a military skill. When making a non- AOE attack with a weapon that uses that skill, this character gains an additional die on his attack and damage rolls. Discard the lowest die of each roll. This benefit can be taken more than once, each time specifying a different military skill."},
};

        arrayList.clear();

        for (String[] ability1 : archetypes) {
            Archetype ability = new Archetype(ability1[0], ability1[1]);
            arrayList.add(ability);
        }

        //Sorting
        Collections.sort(arrayList, new Comparator<Archetype>() {
            @Override
            public int compare(Archetype lhs, Archetype rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });

        final ListView listView = (ListView) view.findViewById(R.id.listView1);
        archetypeAdapter = new ArchetypeAdapter(getActivity(), arrayList);
        listView.setAdapter(archetypeAdapter);



        final EditText myFilter = (EditText)view.findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = myFilter.getText().toString().toLowerCase(Locale.getDefault());
                archetypeAdapter.filter(text);
            }

            @Override
            public void afterTextChanged(Editable s) {

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

