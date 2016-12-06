package com.example.userdaniel.ironkingdoms;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.view.ContextThemeWrapper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.userdaniel.ironkingdoms.DialogFragments.AbilitiesInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.ArchetypesInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.CharacterInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.CharacterStateInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.CharacteristicsInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.DescriptionInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.EquipmentInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.MeleeWeaponInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.MiscellaneousInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.RangedWeaponInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.SkillsInfoDialog;
import com.example.userdaniel.ironkingdoms.DialogFragments.SpellsInfoDialog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CharacterMenu extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener,CharacterInfoDialog.EditCharacterDialogListener, CharacteristicsInfoDialog.EditCharacteristicsDialogListener,
        DescriptionInfoDialog.EditDescriptionDialogListener, EquipmentInfoDialog.EditEquipmentDialogListener, CharacterStateInfoDialog.EditCharacterStateDialogListener, MeleeWeaponInfoDialog.EditMeleeWeaponDialogListener, RangedWeaponInfoDialog.EditRangedWeaponDialogListener,
        MiscellaneousInfoDialog.EditMiscellaneousDialogListener, SkillsInfoDialog.EditSkillsDialogListener, AbilitiesInfoDialog.EditAbilitiesDialogListener, ArchetypesInfoDialog.EditArchetypeDialogListener, SpellsInfoDialog.EditSpellsDialogListener {

    TextView charname, racename, careername, archetypename, phy, spd, str, agi, prw, poi, intellect, arc, per,
            gender, weight, height, hair, eyes, faith, defcharacteristics, money, equipment, def, arm, armName, armBonus, spdMod, defMod, miscArmMod, shieldMod, initEquipMod,
            miscInitMod, racialDEFMod, init, cmdRange, cmdRangeAbilityMod, cmdRangeSkillMod, feat1, connections, permanentinjuries, notes;


    Database dbHelper;

    Bundle bundle = null;
    SharedPreferences sharedPref = null;

    List<CheckBox> physical = new ArrayList<>();
    List<CheckBox> agility = new ArrayList<>();
    List<CheckBox> intelligence = new ArrayList<>();
    List<CheckBox> powershield = new ArrayList<>();


    ArrayList<String> abilityNameArrayList = new ArrayList<>();
    ArrayList<String> abilityDescriptionArrayList = new ArrayList<>();
    ArrayList<String> spellNameArrayList = new ArrayList<>();
    ArrayList<String> spellCostArrayList = new ArrayList<>();
    ArrayList<String> spellRngArrayList = new ArrayList<>();
    ArrayList<String> spellAoeArrayList = new ArrayList<>();
    ArrayList<String> spellPowArrayList = new ArrayList<>();
    ArrayList<String> spellUpArrayList = new ArrayList<>();
    ArrayList<String> spellOffArrayList = new ArrayList<>();
    ArrayList<String> spellDescriptionArrayList= new ArrayList<>();
    ArrayList<String> archetypeNameArrayList = new ArrayList<>();
    ArrayList<String> archetypeDescriptionArrayList = new ArrayList<>();
    ArrayList<String> skillNameArrayList = new ArrayList<>();
    ArrayList<String> skillNumArrayList = new ArrayList<>();
    ArrayList<String> meleeNameArrayList = new ArrayList<>();
    ArrayList<String> meleeMatArrayList = new ArrayList<>();
    ArrayList<String> meleePowArrayList = new ArrayList<>();
    ArrayList<String> meleeSpecialNotesArrayList = new ArrayList<>();
    ArrayList<String> rangeNameArrayList = new ArrayList<>();
    ArrayList<String> rangeRatArrayList = new ArrayList<>();
    ArrayList<String> rangeRngArrayList = new ArrayList<>();
    ArrayList<String> rangeAoeArrayList = new ArrayList<>();
    ArrayList<String> rangePowArrayList = new ArrayList<>();
    ArrayList<String> rangeCurrentAmmoArrayList = new ArrayList<>();
    ArrayList<String> rangeTotalAmmoArrayList = new ArrayList<>();
    ArrayList<String> rangeSpecialNotesArrayList = new ArrayList<>();

    @Override
    protected void onPause() {
        super.onPause();


        TextView feat1 = (TextView)findViewById(R.id.textView14);

        TextView connections = (TextView) findViewById(R.id.textView386);
        TextView permanentinjuries = (TextView) findViewById(R.id.textView293);
        TextView notes = (TextView) findViewById(R.id.textView291);

        Intent intent = getIntent();
        bundle = intent.getExtras();

        dbHelper = new Database(this);
        try {
            dbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String abilityName = "";
        String abilityDescript = "";
        for (int i = 0; i < abilityNameArrayList.size(); i++) {
            abilityName = abilityName + abilityNameArrayList.get(i) + "|";
            abilityDescript = abilityDescript + abilityDescriptionArrayList.get(i) + "|";
        }

        String spellName = "";
        String spellCost = "";
        String spellRng = "";
        String spellAoe = "";
        String spellPow = "";
        String spellUp = "";
        String spellOff = "";
        String spellDescript = "";
        for (int i = 0; i < spellNameArrayList.size(); i++) {
            spellName = spellName + spellNameArrayList.get(i) + "|";
            spellCost = spellCost + spellCostArrayList.get(i) + "|";
            spellRng = spellRng + spellRngArrayList.get(i) + "|";
            spellAoe = spellAoe + spellAoeArrayList.get(i) + "|";
            spellPow = spellPow + spellPowArrayList.get(i) + "|";
            spellUp = spellUp + spellUpArrayList.get(i) + "|";
            spellOff = spellOff + spellOffArrayList.get(i) + "|";
            spellDescript = spellDescript + spellDescriptionArrayList.get(i) + "|";
        }

        String archetypeName = "";
        String archetypeDescript = "";
        for (int i = 0; i < archetypeNameArrayList.size(); i++) {
            archetypeName = archetypeName + archetypeNameArrayList.get(i) + "|";
            archetypeDescript = archetypeDescript + archetypeDescriptionArrayList.get(i) + "|";
        }

        String skillName = "";
        String skillNum = "";
        for (int i = 0; i < skillNameArrayList.size(); i++) {
            skillName = skillName + skillNameArrayList.get(i) + "|";
            skillNum = skillNum + skillNumArrayList.get(i) + "|";
        }

        String meleeWeaponName = "";
        String meleeMat = "";
        String meleePow = "";
        String meleeSpecial = "";
        for (int i = 0; i < meleeNameArrayList.size(); i++) {
            meleeWeaponName = meleeWeaponName + meleeNameArrayList.get(i) + "|";
            meleeMat = meleeMat + meleeMatArrayList.get(i) + "|";
            meleePow = meleePow + meleePowArrayList.get(i) + "|";
            meleeSpecial = meleeSpecial + meleeSpecialNotesArrayList.get(i) + "|";
        }


        String rangeWeaponName = "";
        String rangeRat = "";
        String rangeRng = "";
        String rangeAoe = "";
        String rangePow = "";
        String rangeAmmoCurrent = "";
        String rangeAmmoTotal = "";
        String rangeSpecial = "";
        for (int i = 0; i < rangeNameArrayList.size(); i++) {
            rangeWeaponName = rangeWeaponName + rangeNameArrayList.get(i) + "|";
            rangeRat = rangeRat + rangeRatArrayList.get(i) + "|";
            rangeRng = rangeRng + rangeRngArrayList.get(i) + "|";
            rangeAoe = rangeAoe + rangeAoeArrayList.get(i) + "|";
            rangePow = rangePow + rangePowArrayList.get(i) + "|";
            rangeAmmoCurrent = rangeAmmoCurrent + rangeCurrentAmmoArrayList.get(i) + "|";
            rangeAmmoTotal = rangeAmmoTotal + rangeTotalAmmoArrayList.get(i) + "|";
            rangeSpecial = rangeSpecial + rangeSpecialNotesArrayList.get(i) + "|";
        }
        if (bundle.getInt("newCharacter") == 1) {
            dbHelper.createCharacter(charname.getText().toString(), racename.getText().toString(), careername.getText().toString(), archetypename.getText().toString(), Integer.valueOf(phy.getText().toString())
                    , Integer.valueOf(spd.getText().toString()), Integer.valueOf(str.getText().toString()), Integer.valueOf(agi.getText().toString()), Integer.valueOf(prw.getText().toString()), Integer.valueOf(poi.getText().toString()), Integer.valueOf(intellect.getText().toString())
                    , Integer.valueOf(arc.getText().toString()), Integer.valueOf(per.getText().toString()), feat1.getText().toString(), armName.getText().toString(), Integer.valueOf(armBonus.getText().toString())
                    , Integer.valueOf(spdMod.getText().toString()), Integer.valueOf(defMod.getText().toString()), Integer.valueOf(miscArmMod.getText().toString()), Integer.valueOf(shieldMod.getText().toString()), Integer.valueOf(racialDEFMod.getText().toString())
                    , Integer.valueOf(initEquipMod.getText().toString()), Integer.valueOf(miscInitMod.getText().toString()), Integer.valueOf(cmdRangeAbilityMod.getText().toString()), Integer.valueOf(cmdRangeSkillMod.getText().toString()), meleeWeaponName, meleeMat, meleePow, meleeSpecial,
                      rangeWeaponName, rangeRat, rangeRng, rangeAoe, rangePow, rangeAmmoCurrent, rangeAmmoTotal, rangeSpecial, abilityName, abilityDescript, spellName, spellCost, spellRng, spellAoe, spellPow, spellUp, spellOff, spellDescript, archetypeName,
                      archetypeDescript, skillName, skillNum, money.getText().toString(), equipment.getText().toString(), connections.getText().toString(), permanentinjuries.getText().toString(),
                     notes.getText().toString(), gender.getText().toString(), weight.getText().toString(), height.getText().toString(), hair.getText().toString(), eyes.getText().toString(), faith.getText().toString(), defcharacteristics.getText().toString());
        } else {
            dbHelper.updateCharacter(String.valueOf(bundle.getInt("CharacterID")), charname.getText().toString(), racename.getText().toString(), careername.getText().toString(), archetypename.getText().toString(), Integer.valueOf(phy.getText().toString())
                    , Integer.valueOf(spd.getText().toString()), Integer.valueOf(str.getText().toString()), Integer.valueOf(agi.getText().toString()), Integer.valueOf(prw.getText().toString()), Integer.valueOf(poi.getText().toString()), Integer.valueOf(intellect.getText().toString())
                    , Integer.valueOf(arc.getText().toString()), Integer.valueOf(per.getText().toString()), feat1.getText().toString(), armName.getText().toString(), Integer.valueOf(armBonus.getText().toString())
                    , Integer.valueOf(spdMod.getText().toString()), Integer.valueOf(defMod.getText().toString()), Integer.valueOf(miscArmMod.getText().toString()), Integer.valueOf(shieldMod.getText().toString()), Integer.valueOf(racialDEFMod.getText().toString())
                    , Integer.valueOf(initEquipMod.getText().toString()), Integer.valueOf(miscInitMod.getText().toString()), Integer.valueOf(cmdRangeAbilityMod.getText().toString()), Integer.valueOf(cmdRangeSkillMod.getText().toString()), meleeWeaponName, meleeMat, meleePow, meleeSpecial,
                    rangeWeaponName, rangeRat, rangeRng, rangeAoe, rangePow, rangeAmmoCurrent, rangeAmmoTotal, rangeSpecial,abilityName, abilityDescript , spellName, spellCost, spellRng, spellAoe, spellPow, spellUp, spellOff, spellDescript, archetypeName, archetypeDescript, skillName,
                    skillNum, money.getText().toString(), equipment.getText().toString(), connections.getText().toString(), permanentinjuries.getText().toString(), notes.getText().toString(), gender.getText().toString(), weight.getText().toString(), height.getText().toString(),
                    hair.getText().toString(), eyes.getText().toString(), faith.getText().toString(), defcharacteristics.getText().toString());
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_layout);
        racename = (TextView) findViewById(R.id.raceName);
        charname = (TextView) findViewById(R.id.characterName);
        careername = (TextView) findViewById(R.id.careerName);
        archetypename = (TextView) findViewById(R.id.archetypeName);

        phy = (TextView) findViewById(R.id.textView254);
        spd = (TextView) findViewById(R.id.textView257);
        str = (TextView) findViewById(R.id.textView262);
        agi = (TextView) findViewById(R.id.agistatnumber);
        prw = (TextView) findViewById(R.id.textView264);
        poi = (TextView) findViewById(R.id.poistatnumber);
        intellect = (TextView) findViewById(R.id.intstatnumber);
        arc = (TextView) findViewById(R.id.arcstatnumber);
        per = (TextView) findViewById(R.id.perstatnumber);

        gender = (TextView) findViewById(R.id.textView217);
        weight = (TextView) findViewById(R.id.textView218);
        height = (TextView) findViewById(R.id.textView219);
        hair = (TextView) findViewById(R.id.textView220);
        eyes = (TextView) findViewById(R.id.textView221);
        faith = (TextView) findViewById(R.id.textView229);
        defcharacteristics = (TextView) findViewById(R.id.textView342);

        money = (TextView) findViewById(R.id.textView195);
        equipment = (TextView) findViewById(R.id.textView200);

        def = (TextView) findViewById(R.id.textView185);
        arm = (TextView) findViewById(R.id.textView198);
        armName = (TextView) findViewById(R.id.textView205);
        armBonus = (TextView) findViewById(R.id.textView17);
        spdMod = (TextView) findViewById(R.id.textView22);
        defMod = (TextView) findViewById(R.id.textView19);
        miscArmMod = (TextView) findViewById(R.id.textView212);
        shieldMod = (TextView) findViewById(R.id.textView213);
        initEquipMod = (TextView) findViewById(R.id.textView214);
        miscInitMod = (TextView) findViewById(R.id.textView215);
        racialDEFMod = (TextView) findViewById(R.id.textView216);
        init = (TextView) findViewById(R.id.textView201);
        cmdRange = (TextView) findViewById(R.id.textView203);
        cmdRangeAbilityMod = (TextView) findViewById(R.id.textView13);
        cmdRangeSkillMod = (TextView) findViewById(R.id.textView24);

        feat1 = (TextView) findViewById(R.id.textView14);

        connections = (TextView) findViewById(R.id.textView386);
        permanentinjuries = (TextView) findViewById(R.id.textView293);
        notes = (TextView) findViewById(R.id.textView291);


        Intent intent = getIntent();
        bundle = intent.getExtras();



        String[] abilitynamesarray = bundle.getString("abilityname","").split("\\|");
        String[] abilitydescriptarray = bundle.getString("abilitydescription","").split("\\|");


        if (!bundle.getString("abilityname","").equals("")) {
            for (int i = 0; i < abilitynamesarray.length; i++) {
                LinearLayout container = (LinearLayout) findViewById(R.id.container);
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_ability, null);
                final LinearLayout dynamicLayout = (LinearLayout) addView.findViewById(R.id.linearLayout1);
                final TextView textOut = (TextView) addView.findViewById(R.id.textout);
                final TextView textOut3 = (TextView) addView.findViewById(R.id.textView317);

                textOut.setText(abilitynamesarray[i]);
                textOut3.setText(abilitydescriptarray[i]);
                abilityNameArrayList.add(abilitynamesarray[i]);
                abilityDescriptionArrayList.add(abilitydescriptarray[i]);

                final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();

                addView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        alertDialog.setTitle(textOut.getText());
                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((LinearLayout) addView.getParent()).removeView(addView);
                                abilityNameArrayList.remove(textOut.getText().toString());
                                abilityDescriptionArrayList.remove(textOut3.getText().toString());

                            }
                        });
                        alertDialog.show();

                        return true;
                    }
                });

                addView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dynamicLayout.getVisibility() == View.GONE) {
                            dynamicLayout.setVisibility(View.VISIBLE);
                        } else {
                            dynamicLayout.setVisibility(View.GONE);
                        }
                    }
                });

                container.addView(addView);

            }
        }

        String[] archetypenamesarray = bundle.getString("archetypename","").split("\\|");
        String[] archetypedescriptarray = bundle.getString("archetypedescript","").split("\\|");


        if (!bundle.getString("archetypename","").equals("")) {
            for (int i = 0; i < archetypenamesarray.length; i++) {
                LinearLayout container = (LinearLayout) findViewById(R.id.linearLayout9);
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_archetype, null);
                final TextView textOut = (TextView) addView.findViewById(R.id.textout);
                final TextView textOut3 = (TextView) addView.findViewById(R.id.textView317);

                textOut.setText(archetypenamesarray[i]);
                textOut3.setText(archetypedescriptarray[i]);
                archetypeNameArrayList.add(archetypenamesarray[i]);
                archetypeDescriptionArrayList.add(archetypedescriptarray[i]);

                final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();

                addView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        alertDialog.setTitle(textOut.getText());
                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((LinearLayout) addView.getParent()).removeView(addView);
                                archetypeNameArrayList.remove(textOut.getText().toString());
                                archetypeDescriptionArrayList.remove(textOut3.getText().toString());

                            }
                        });
                        alertDialog.show();

                        return true;
                    }
                });

                addView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (textOut3.getVisibility() == View.GONE) {
                            textOut3.setVisibility(View.VISIBLE);
                        } else {
                            textOut3.setVisibility(View.GONE);
                        }
                    }
                });

                container.addView(addView);

            }
        }

        String[] spellnamesarray = bundle.getString("spellname","").split("\\|");
        String[] spellcostarray = bundle.getString("spellcost","").split("\\|");
        String[] spellrngarray = bundle.getString("spellrng","").split("\\|");
        String[] spellaoearray = bundle.getString("spellaoe","").split("\\|");
        String[] spellpowarray = bundle.getString("spellpow","").split("\\|");
        String[] spelluparray = bundle.getString("spellup","").split("\\|");
        String[] spelloffarray = bundle.getString("spelloff","").split("\\|");
        String[] spelldescriptarray = bundle.getString("spelldescript","").split("\\|");


        if (!bundle.getString("spellname","").equals("")) {
            for (int i = 0; i < spellnamesarray.length; i++) {
                LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout2);
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_spell, null);
                final LinearLayout dynamicLayout = (LinearLayout)addView.findViewById(R.id.linearLayout7);

                final TextView textOut = (TextView)addView.findViewById(R.id.textout);
                final TextView textcost = (TextView)addView.findViewById(R.id.textView393);
                final TextView textrng = (TextView)addView.findViewById(R.id.textView394);
                final TextView textaoe = (TextView)addView.findViewById(R.id.textView395);
                final TextView textpow = (TextView)addView.findViewById(R.id.textView396);
                final TextView textup = (TextView)addView.findViewById(R.id.textView397);
                final TextView textoff = (TextView)addView.findViewById(R.id.textView398);
                final TextView textdescript = (TextView)addView.findViewById(R.id.textView399);

                textOut.setText(spellnamesarray[i]);
                textcost.setText(spellcostarray[i]);
                textrng.setText(spellrngarray[i]);
                textaoe.setText(spellaoearray[i]);
                textpow.setText(spellpowarray[i]);
                textup.setText(spelluparray[i]);
                textoff.setText(spelloffarray[i]);
                textdescript.setText(spelldescriptarray[i]);

                spellNameArrayList.add(spellnamesarray[i]);
                spellCostArrayList.add(spellcostarray[i]);
                spellRngArrayList.add(spellrngarray[i]);
                spellAoeArrayList.add(spellaoearray[i]);
                spellPowArrayList.add(spellpowarray[i]);
                spellUpArrayList.add(spelluparray[i]);
                spellOffArrayList.add(spelloffarray[i]);
                spellDescriptionArrayList.add(spelldescriptarray[i]);

                final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();

                addView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        alertDialog.setTitle(textOut.getText());
                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((LinearLayout) addView.getParent()).removeView(addView);
                                spellNameArrayList.remove(textOut.getText().toString());
                                spellCostArrayList.remove(textcost.getText().toString());
                                spellRngArrayList.remove(textrng.getText().toString());
                                spellAoeArrayList.remove(textaoe.getText().toString());
                                spellPowArrayList.remove(textpow.getText().toString());
                                spellUpArrayList.remove(textup.getText().toString());
                                spellOffArrayList.remove(textoff.getText().toString());
                                spellDescriptionArrayList.remove(textdescript.getText().toString());
                            }
                        });
                        alertDialog.show();

                        return true;
                    }
                });

                addView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dynamicLayout.getVisibility() == View.GONE) {
                            dynamicLayout.setVisibility(View.VISIBLE);
                        } else {
                            dynamicLayout.setVisibility(View.GONE);
                        }
                    }
                });

                container.addView(addView);

            }
        }

        String[] skillnamesarray = bundle.getString("skillname","").split("\\|");
        String[] skillnumsarray = bundle.getString("skillnum","").split("\\|");
        if (!bundle.getString("skillname","").equals("")) {
            for (int i = 0; i < skillnamesarray.length; i++) {
                LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout4);
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_skill, null);
                final TextView textOut = (TextView)addView.findViewById(R.id.textout);
                TextView textOutParent = (TextView)addView.findViewById(R.id.textView304);
                TextView textOutParentNum = (TextView)addView.findViewById(R.id.textView317);
                final TextView textOutSkillLevelNum = (TextView)addView.findViewById(R.id.textView232);
                TextView textOutTotal = (TextView)addView.findViewById(R.id.textView234);

                textOut.setText(skillnamesarray[i]);
                textOutSkillLevelNum.setText(skillnumsarray[i]);

                skillNameArrayList.add(skillnamesarray[i]);
                skillNumArrayList.add(skillnumsarray[i]);

                switch (skillnamesarray[i]) {
                    case "Archery":
                    case "Crossbow":
                    case "Light Artillery":
                    case "Pistol":
                    case "Rifle":
                    case "Heavy Artillery":
                    case "Fell Calling":
                        textOutParent.setText("Poise");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("poise",0)));
                        textOutTotal.setText("" + (bundle.getInt("poise", 0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                    case "Great Weapon":
                    case "Hand Weapon":
                    case "Lance":
                    case "Shield":
                    case "Thrown Weapon":
                    case "Unarmed Combat":
                        textOutParent.setText("Prowess");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("prowess",0)));
                        textOutTotal.setText("" + (bundle.getInt("prowess",0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                    case "Alchemy":
                    case "Cryptography":
                    case "Disguise":
                    case "Forensic Science":
                    case "Law":
                    case "Mechanikal Engineering":
                    case "Medicine":
                    case "Research":
                        textOutParent.setText("Intellect");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("intellect",0)));
                        textOutTotal.setText("" + (bundle.getInt("intellect",0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                    case "Bribery":
                    case "Command":
                    case "Deception":
                    case "Etiquette":
                    case "Animal Handling":
                    case "Interrogation":
                    case "Negotiation":
                    case "Oratory":
                    case "Intimidation":
                    case "Seduction":
                        textOutParent.setText("Social");
                        textOutParentNum.setText("*");
                        textOutTotal.setText(skillnumsarray[i]);
                        break;
                    case "Escape Artist":
                    case "Climbing":
                    case "Driving":
                    case "Lock Picking":
                    case "Pickpocket":
                    case "Rope Use":
                    case "Sneak":
                    case "Riding":
                        textOutParent.setText("Agility");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("agility",0)));
                        textOutTotal.setText("" + (bundle.getInt("agility",0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                    case "Detection":
                    case "Navigation":
                    case "Gambling":
                    case "Streetwise":
                    case "Survival":
                    case "Tracking":
                        textOutParent.setText("Perception");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("perception",0)));
                        textOutTotal.setText("" + (bundle.getInt("perception",0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                    case "Forgery":
                        textOutParent.setText("Agility or Intellect");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("agility",0)) + " or " + String.valueOf(bundle.getInt("intellect",0)));
                        textOutTotal.setText("" + (bundle.getInt("agility",0) + Integer.valueOf(skillnumsarray[i])) + " or " + (bundle.getInt("intellect",0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                    case "Jumping":
                        textOutParent.setText("Physique");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("physique",0)));
                        textOutTotal.setText("" + (bundle.getInt("physique",0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                    case "Sailing":
                        textOutParent.setText("Intellect or Strength");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("intellect",0)) + " or " + String.valueOf(bundle.getInt("strength",0)));
                        textOutTotal.setText("" + (bundle.getInt("intellect",0) + Integer.valueOf(skillnumsarray[i])) + " or " + (bundle.getInt("strength",0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                    case "Swimming":
                        textOutParent.setText("Strength");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("strength",0)));
                        textOutTotal.setText("" + (bundle.getInt("strength",0) + Integer.valueOf(skillnumsarray[i])));

                        break;
                    default:
                        textOutParent.setText("Intellect");
                        textOutParentNum.setText(String.valueOf(bundle.getInt("intellect",0)));
                        textOutTotal.setText("" + (bundle.getInt("intellect",0) + Integer.valueOf(skillnumsarray[i])));
                        break;
                }

                final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
                addView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        alertDialog.setTitle(textOut.getText());
                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((LinearLayout) addView.getParent()).removeView(addView);
                                skillNameArrayList.remove(textOut.getText().toString());
                                skillNumArrayList.remove(textOutSkillLevelNum.getText().toString());

                            }
                        });
                        alertDialog.show();

                        return true;
                    }
                });

                container.addView(addView);

            }
        }


        String[] rangeweaponnamesarray = bundle.getString("rangeweapontitle","").split("\\|");
        String[] rangeratarray = bundle.getString("rangerat","").split("\\|");
        String[] rangerngarray = bundle.getString("rangerng","").split("\\|");
        String[] rangeaoearray = bundle.getString("rangeaoe","").split("\\|");
        String[] rangepowarray = bundle.getString("rangepow","").split("\\|");
        String[] rangecurrentammoarray = bundle.getString("rangeammocurrent","").split("\\|");
        String[] rangetotalammoarray = bundle.getString("rangeammototal","").split("\\|");
        String[] rangespecialnotesarray = bundle.getString("rangespecialnotes","").split("\\|");

        if (!bundle.getString("rangeweapontitle","").equals("")) {
            for (int i = 0; i < rangeweaponnamesarray.length; i++) {
                final LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout21);
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_range, null);
                final TextView name = (TextView)addView.findViewById(R.id.textView32);
                final TextView rat = (TextView)addView.findViewById(R.id.textView277);
                final TextView rng = (TextView)addView.findViewById(R.id.textView273);
                final TextView aoe = (TextView)addView.findViewById(R.id.textView226);
                final TextView pow = (TextView)addView.findViewById(R.id.textView224);
                final TextView ammocurrent = (TextView)addView.findViewById(R.id.textView286);
                final TextView ammototal = (TextView)addView.findViewById(R.id.textView236);
                final TextView special = (TextView)addView.findViewById(R.id.textView206);

                name.setText(rangeweaponnamesarray[i]);
                rat.setText(rangeratarray[i]);
                rng.setText(rangerngarray[i]);
                aoe.setText(rangeaoearray[i]);
                pow.setText(rangepowarray[i]);
                ammocurrent.setText(rangecurrentammoarray[i]);
                ammototal.setText(rangetotalammoarray[i]);
                special.setText(rangespecialnotesarray[i]);

                rangeNameArrayList.add(rangeweaponnamesarray[i]);
                rangeRatArrayList.add(rangeratarray[i]);
                rangeRngArrayList.add(rangerngarray[i]);
                rangeAoeArrayList.add(rangeaoearray[i]);
                rangePowArrayList.add(rangepowarray[i]);
                rangeCurrentAmmoArrayList.add(rangecurrentammoarray[i]);
                rangeTotalAmmoArrayList.add(rangetotalammoarray[i]);
                rangeSpecialNotesArrayList.add(rangespecialnotesarray[i]);


                final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
                addView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        alertDialog.setTitle(name.getText());
                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((LinearLayout) addView.getParent()).removeView(addView);
                                rangeNameArrayList.remove(name.getText().toString());
                                rangeRatArrayList.remove(rat.getText().toString());
                                rangeRngArrayList.remove(rng.getText().toString());
                                rangeAoeArrayList.remove(aoe.getText().toString());
                                rangePowArrayList.remove(pow.getText().toString());
                                rangeCurrentAmmoArrayList.remove(ammocurrent.getText().toString());
                                rangeTotalAmmoArrayList.remove(ammototal.getText().toString());
                                rangeSpecialNotesArrayList.remove(special.getText().toString());
                            }
                        });
                        alertDialog.show();

                        return true;
                    }
                });


                addView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = getFragmentManager();
                        RangedWeaponInfoDialog dialogFragment5 = new RangedWeaponInfoDialog();
                        dialogFragment5.show(fm, "Sample Fragment");
                        Bundle bundle = new Bundle();
                        bundle.putInt("key", 1);
                        bundle.putString("name", name.getText().toString());
                        bundle.putString("rat", rat.getText().toString());
                        bundle.putString("rng", rng.getText().toString());
                        bundle.putString("aoe", aoe.getText().toString());
                        bundle.putString("pow", pow.getText().toString());
                        bundle.putString("ammocurrent", ammocurrent.getText().toString());
                        bundle.putString("ammototal", ammototal.getText().toString());
                        bundle.putString("specialnotes", special.getText().toString());
                        dialogFragment5.setArguments(bundle);
                        rangeNameArrayList.remove(name.getText().toString());
                        rangeRatArrayList.remove(rat.getText().toString());
                        rangeRngArrayList.remove(rng.getText().toString());
                        rangeAoeArrayList.remove(aoe.getText().toString());
                        rangePowArrayList.remove(pow.getText().toString());
                        rangeCurrentAmmoArrayList.remove(ammocurrent.getText().toString());
                        rangeTotalAmmoArrayList.remove(ammototal.getText().toString());
                        rangeSpecialNotesArrayList.remove(special.getText().toString());
                        container.removeView(addView);

                    }
                });



                container.addView(addView);

            }
        }


        String[] meleeweaponnamesarray = bundle.getString("meleeweapontitle","").split("\\|");
        String[] meleematarray = bundle.getString("meleemat","").split("\\|");
        String[] meleepowarray = bundle.getString("meleepow","").split("\\|");
        String[] meleespecialnotesarray = bundle.getString("meleespecialnotes","").split("\\|");

        if (!bundle.getString("meleeweapontitle","").equals("")) {
            for (int i = 0; i < meleeweaponnamesarray.length; i++) {
                final LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout21);
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_melee, null);
                final TextView name = (TextView)addView.findViewById(R.id.textView32);
                final TextView mat = (TextView)addView.findViewById(R.id.textView226);
                final TextView pow = (TextView)addView.findViewById(R.id.textView224);
                final TextView specialnotes = (TextView)addView.findViewById(R.id.textView206);

                name.setText(meleeweaponnamesarray[i]);
                mat.setText(meleematarray[i]);
                pow.setText("" + (Integer.parseInt(meleepowarray[i]) + bundle.getInt("strength", 0)));
                specialnotes.setText(meleespecialnotesarray[i]);

                meleeNameArrayList.add(meleeweaponnamesarray[i]);
                meleeMatArrayList.add(meleematarray[i]);
                meleePowArrayList.add(meleepowarray[i]);
                meleeSpecialNotesArrayList.add(meleespecialnotesarray[i]);

                final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
                addView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        alertDialog.setTitle(name.getText());
                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((LinearLayout)addView.getParent()).removeView(addView);
                                meleeNameArrayList.remove(name.getText().toString());
                                meleeMatArrayList.remove(mat.getText().toString());
                                meleePowArrayList.remove("" + (Integer.parseInt(pow.getText().toString()) - bundle.getInt("strength", 0)));
                                meleeSpecialNotesArrayList.remove(specialnotes.getText().toString());
                            }
                        });
                        alertDialog.show();

                        return true;
                    }
                });


                addView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = getFragmentManager();
                        MeleeWeaponInfoDialog dialogFragment5 = new MeleeWeaponInfoDialog();
                        dialogFragment5.show(fm, "Sample Fragment");
                        Bundle bundle = new Bundle();
                        bundle.putInt("key", 1);
                        bundle.putString("name", name.getText().toString());
                        bundle.putString("mat", mat.getText().toString());
                        bundle.putString("pow", "" + (Integer.parseInt(pow.getText().toString()) - bundle.getInt("strength", 0)));
                        bundle.putString("specialnotes", specialnotes.getText().toString());
                        dialogFragment5.setArguments(bundle);
                        meleeNameArrayList.remove(name.getText().toString());
                        meleeMatArrayList.remove(mat.getText().toString());
                        meleePowArrayList.remove("" + (Integer.parseInt(pow.getText().toString()) - bundle.getInt("strength", 0)));
                        meleeSpecialNotesArrayList.remove(specialnotes.getText().toString());
                        container.removeView(addView);

                    }
                });



                container.addView(addView);

            }
        }




        charname.setText(bundle.getString("name", "CHARACTER NAME"));
        racename.setText(bundle.getString("race"));
        careername.setText(bundle.getString("careers"));
        archetypename.setText(bundle.getString("archetype"));
        phy.setText(String.valueOf(bundle.getInt("physique", 0)));
        spd.setText(String.valueOf(bundle.getInt("speed", 0)));
        str.setText(String.valueOf(bundle.getInt("strength", 0)));
        agi.setText(String.valueOf(bundle.getInt("agility", 0)));
        prw.setText(String.valueOf(bundle.getInt("prowess", 0)));
        poi.setText(String.valueOf(bundle.getInt("poise", 0)));
        intellect.setText(String.valueOf(bundle.getInt("intellect", 0)));
        arc.setText(String.valueOf(bundle.getInt("arcane", 0)));
        per.setText(String.valueOf(bundle.getInt("perception", 0)));

        feat1.setText(bundle.getString("feat", "3"));

        armName.setText(bundle.getString("armorsetname", "Armor Name"));
        armBonus.setText(String.valueOf(bundle.getInt("armbonus", 0)));
        spdMod.setText(String.valueOf(bundle.getInt("spdbonus", 0)));
        defMod.setText(String.valueOf(bundle.getInt("defbonus", 0)));
        miscArmMod.setText(String.valueOf(bundle.getInt("miscarm", 0)));
        shieldMod.setText(String.valueOf(bundle.getInt("shieldmod", 0)));
        racialDEFMod.setText(String.valueOf(bundle.getInt("racialdefmod", 0)));
        initEquipMod.setText(String.valueOf(bundle.getInt("initequipmentmod", 0)));
        miscInitMod.setText(String.valueOf(bundle.getInt("miscinitmod", 0)));
        cmdRangeAbilityMod.setText(String.valueOf(bundle.getInt("commandabilitymod", 0)));
        cmdRangeSkillMod.setText(String.valueOf(bundle.getInt("commandskillmod", 0)));

        money.setText(bundle.getString("money", "0 GC"));
        equipment.setText(bundle.getString("items"));

        connections.setText(bundle.getString("connections"));
        permanentinjuries.setText(bundle.getString("permanentinjuries"));
        notes.setText(bundle.getString("notes"));

        gender.setText(bundle.getString("gender"));
        weight.setText(bundle.getString("weight"));
        height.setText(bundle.getString("height"));
        hair.setText(bundle.getString("hair"));
        eyes.setText(bundle.getString("eyes"));
        faith.setText(bundle.getString("faith"));
        defcharacteristics.setText(bundle.getString("definingcharacteristics"));

        def.setText(String.valueOf(bundle.getInt("speed", 0) + bundle.getInt("agility", 0) + bundle.getInt("perception", 0) - bundle.getInt("defbonus",0)));
        arm.setText(String.valueOf(bundle.getInt("physique", 0) + bundle.getInt("armbonus", 0) + bundle.getInt("shieldmod", 0) + bundle.getInt("miscarm", 0)));
        init.setText(String.valueOf(bundle.getInt("speed", 0) + bundle.getInt("prowess", 0) + bundle.getInt("perception", 0) + bundle.getInt("initequipmentmod", 0) + bundle.getInt("miscinitmod", 0)));
        cmdRange.setText(String.valueOf(bundle.getInt("intellect", 0) + bundle.getInt("commandabilitymod", 0) + bundle.getInt("commandskillmod", 0)));

        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/amplifier_light.otf");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/itc_fenice_it_regular.ttf");
        charname.setTypeface(font);

        TextView characteristicstext = (TextView) findViewById(R.id.textView250);
        characteristicstext.setTypeface(font);
        TextView descriptiontext = (TextView) findViewById(R.id.textView323);
        descriptiontext.setTypeface(font);
        TextView characterStateText = (TextView)findViewById(R.id.textView276);
        characterStateText.setTypeface(font);
        TextView weaponstext = (TextView) findViewById(R.id.textView344);
        weaponstext.setTypeface(font);
        TextView skillsText = (TextView)findViewById(R.id.textView178);
        skillsText.setTypeface(font);
        TextView abilitysAndArchetypes = (TextView)findViewById(R.id.textView303);
        abilitysAndArchetypes.setTypeface(font);
        TextView spellsText = (TextView)findViewById(R.id.textView391);
        spellsText.setTypeface(font);
        money.setTypeface(font);
        TextView miscellanousText = (TextView)findViewById(R.id.textView292);
        miscellanousText.setTypeface(font);
        TextView equipmenttitle = (TextView) findViewById(R.id.textView194);
        equipmenttitle.setTypeface(font);
        TextView itemstitle = (TextView) findViewById(R.id.textView196);
        itemstitle.setTypeface(font2);
        TextView characterStateTitle = (TextView) findViewById(R.id.textView183);
        characterStateTitle.setTypeface(font);
        def.setTypeface(font2);
        arm.setTypeface(font2);
        init.setTypeface(font2);
        cmdRange.setTypeface(font2);

        physical.add((CheckBox)findViewById(R.id.checkbox1_1));
        physical.add((CheckBox)findViewById(R.id.checkbox1_2));
        physical.add((CheckBox)findViewById(R.id.checkbox2_1));
        physical.add((CheckBox)findViewById(R.id.checkbox1_3));
        physical.add((CheckBox)findViewById(R.id.checkbox2_2));
        physical.add((CheckBox)findViewById(R.id.checkbox1_4));
        physical.add((CheckBox)findViewById(R.id.checkbox2_3));
        physical.add((CheckBox)findViewById(R.id.checkbox1_5));
        physical.add((CheckBox)findViewById(R.id.checkbox2_4));
        physical.add((CheckBox)findViewById(R.id.checkbox1_6));
        agility.add((CheckBox)findViewById(R.id.checkbox3_1));
        agility.add((CheckBox)findViewById(R.id.checkbox3_2));
        agility.add((CheckBox)findViewById(R.id.checkbox4_1));
        agility.add((CheckBox)findViewById(R.id.checkbox3_3));
        agility.add((CheckBox)findViewById(R.id.checkbox4_2));
        agility.add((CheckBox)findViewById(R.id.checkbox3_4));
        agility.add((CheckBox)findViewById(R.id.checkbox4_3));
        agility.add((CheckBox)findViewById(R.id.checkbox3_5));
        intelligence.add((CheckBox)findViewById(R.id.checkbox5_1));
        intelligence.add((CheckBox)findViewById(R.id.checkbox5_2));
        intelligence.add((CheckBox)findViewById(R.id.checkbox6_1));
        intelligence.add((CheckBox)findViewById(R.id.checkbox5_3));
        intelligence.add((CheckBox)findViewById(R.id.checkbox6_2));
        intelligence.add((CheckBox)findViewById(R.id.checkbox5_4));
        intelligence.add((CheckBox)findViewById(R.id.checkbox6_3));
        intelligence.add((CheckBox)findViewById(R.id.checkbox5_5));
        powershield.add((CheckBox)findViewById(R.id.shield1));
        powershield.add((CheckBox)findViewById(R.id.shield2));
        powershield.add((CheckBox)findViewById(R.id.shield3));
        powershield.add((CheckBox)findViewById(R.id.shield4));
        powershield.add((CheckBox)findViewById(R.id.shield5));
        powershield.add((CheckBox)findViewById(R.id.shield6));

        setSpiral();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Intent intent = getIntent();
        bundle = intent.getExtras();

        sharedPref = getSharedPreferences(String.valueOf(bundle.getInt("CharacterID", 0)), MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        if (isChecked) {
            editor.putBoolean(String.valueOf(buttonView.getId()), true);
        } else editor.remove(String.valueOf(buttonView.getId()));
        editor.apply();
        checkStatus();
    }



    public void setSpiral() {
        sharedPref = getSharedPreferences(String.valueOf(bundle.getInt("CharacterID")), MODE_PRIVATE);
        int i;
        for (CheckBox box : physical) {
            box.setEnabled(true);
            box.setChecked(sharedPref.getBoolean(String.valueOf(box.getId()), false));
            box.setOnCheckedChangeListener(this);
        }
        for (CheckBox box2 : agility) {
            box2.setEnabled(true);
            box2.setChecked(sharedPref.getBoolean(String.valueOf(box2.getId()), false));
            box2.setOnCheckedChangeListener(this);
        }
        for (CheckBox box3 : intelligence) {
            box3.setEnabled(true);
            box3.setChecked(sharedPref.getBoolean(String.valueOf(box3.getId()), false));
            box3.setOnCheckedChangeListener(this);
        }
        for (CheckBox box4 : powershield) {
            box4.setEnabled(true);
            box4.setChecked(sharedPref.getBoolean(String.valueOf(box4.getId()), false));
            box4.setOnCheckedChangeListener(this);
        }
        for (i = Integer.valueOf(phy.getText().toString()); i < physical.size(); i++) {
            physical.get(i).setEnabled(false);
        }
        for (i = Integer.valueOf(agi.getText().toString()); i < agility.size(); i++) {
            agility.get(i).setEnabled(false);
        }
        for (i = Integer.valueOf(intellect.getText().toString()); i < intelligence.size(); i++) {
            intelligence.get(i).setEnabled(false);
        }
        checkStatus();
    }


    public void checkStatus() {
        TextView status = (TextView)findViewById(R.id.crippled_str);
        boolean physicalBroken = true;
        for (CheckBox box : physical) {
            if (box.isEnabled() && !box.isChecked()) {
                physicalBroken = false;
            }
        }
        boolean agilityBroken = true;
        for (CheckBox box2 : agility) {
            if (box2.isEnabled() && !box2.isChecked()) {
                agilityBroken = false;
            }
        }
        boolean intelligenceBroken = true;
        for (CheckBox box3 : intelligence) {
            if (box3.isEnabled() && !box3.isChecked()) {
                intelligenceBroken = false;
            }
        }
        String statusBuilder = "";
        if (physicalBroken) {
            statusBuilder = statusBuilder + "-2 STR";
        }
        if (agilityBroken) {
            statusBuilder = statusBuilder + "\n-2 to Attack Rolls";
        }
        if (intelligenceBroken) {
            statusBuilder = statusBuilder + "\n-2 DEF and cannot upkeep spells";
        }
        status.setText(statusBuilder);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_character_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.overflow:
                // search action
                Toast.makeText(getApplicationContext(), "This app is fucking perfect, you don't need to change anything. Fuck off.", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_lang:
                final Random random = new Random();


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3);
                alertDialog.setTitle("Roll d6");

                final NumberPicker picker = new NumberPicker(CharacterMenu.this);
                picker.setMinValue(1);
                picker.setMaxValue(5);
                picker.setWrapSelectorWheel(false);
                FrameLayout parent = new FrameLayout(getApplicationContext());
                parent.addView(picker, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
                alertDialog.setView(parent);

                alertDialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (picker.getValue()) {
                            case 1:
                                Toast.makeText(getApplicationContext(), String.valueOf(random.nextInt(5)+1), Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(getApplicationContext(), String.valueOf(((int) (Math.random()*6)+1) + ((int) (Math.random()* 6) +1)  ), Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(getApplicationContext(), String.valueOf(((int) (Math.random()*6)+1) + ((int) (Math.random()* 6) +1) + ((int) (Math.random() * 6) +1) ), Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(getApplicationContext(), String.valueOf(((int) (Math.random()*6)+1) + ((int) (Math.random()* 6) +1) + ((int) (Math.random() * 6) +1) + ((int) (Math.random() * 6) +1))  , Toast.LENGTH_SHORT).show();
                                break;
                            case 5:
                                Toast.makeText(getApplicationContext(), String.valueOf(((int) (Math.random()*6)+1) + ((int) (Math.random()* 6) +1) + ((int) (Math.random() * 6) +1) + ((int) (Math.random() * 6) +1) +   ((int) (Math.random() * 6) +1))  , Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                });
                alertDialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void onAddNewSkillClicked(View v) {
        //Inflate a new row and hide the button self.
        FragmentManager fm = getFragmentManager();
        SkillsInfoDialog dialogFragment1 = new SkillsInfoDialog();
        dialogFragment1.show(fm, "Sample Fragment");
    }

    public void onMinusFeatPoint(View v) {
        if (Integer.valueOf(feat1.getText().toString()) == 3)
            feat1.setText("2");
        else if (Integer.valueOf(feat1.getText().toString()) == 2)
            feat1.setText("1");
        else if (Integer.valueOf(feat1.getText().toString()) == 1)
            feat1.setText("0");
    }

    public void onAddFeatPoint(View v) {
        if (feat1.getText().equals("0"))
            feat1.setText("1");
        else if (feat1.getText().equals("1"))
            feat1.setText("2");
        else if (feat1.getText().equals("2"))
            feat1.setText("3");
    }

    public void startMeleeDialog(View view) {
        FragmentManager fm = getFragmentManager();
        MeleeWeaponInfoDialog dialogFragment5 = new MeleeWeaponInfoDialog();
        dialogFragment5.show(fm, "Sample Fragment");
        Bundle bundle = new Bundle();
        bundle.putInt("key", 0);
        dialogFragment5.setArguments(bundle);
    }

    public void startRangedDialog(View view) {
        FragmentManager fm = getFragmentManager();
        RangedWeaponInfoDialog dialogFragment5 = new RangedWeaponInfoDialog();
        dialogFragment5.show(fm, "Sample Fragment");
        Bundle bundle = new Bundle();
        bundle.putInt("key", 0);
        dialogFragment5.setArguments(bundle);
    }



    public void startCharacterDialog(View view) {
        FragmentManager fm = getFragmentManager();
        CharacterInfoDialog dialogFragment1 = new CharacterInfoDialog();
        dialogFragment1.show(fm, "Sample Fragment");
        Bundle bundle = new Bundle();
        bundle.putString("character", charname.getText().toString());
        bundle.putString("race", racename.getText().toString());
        bundle.putString("career", careername.getText().toString());
        bundle.putString("archetype", archetypename.getText().toString());
        dialogFragment1.setArguments(bundle);

    }

    public void startCharacteristicsDialog(View view) {
        FragmentManager fm = getFragmentManager();
        CharacteristicsInfoDialog dialogFragment2 = new CharacteristicsInfoDialog();
        dialogFragment2.show(fm, "Sample Fragment");
        Bundle bundle1 = new Bundle();
        bundle1.putString("phynum", phy.getText().toString());
        bundle1.putString("spdnum", spd.getText().toString());
        bundle1.putString("strnum", str.getText().toString());
        bundle1.putString("aginum", agi.getText().toString());
        bundle1.putString("prwnum", prw.getText().toString());
        bundle1.putString("poinum", poi.getText().toString());
        bundle1.putString("intnum", intellect.getText().toString());
        bundle1.putString("arcnum", arc.getText().toString());
        bundle1.putString("pernum", per.getText().toString());
        dialogFragment2.setArguments(bundle1);

    }

    public void startDescriptionDialog(View view) {
        FragmentManager fm = getFragmentManager();
        DescriptionInfoDialog dialogFragment3 = new DescriptionInfoDialog();
        dialogFragment3.show(fm, "Sample Fragment");
        Bundle bundle = new Bundle();
        bundle.putString("gender", gender.getText().toString());
        bundle.putString("weight", weight.getText().toString());
        bundle.putString("height", height.getText().toString());
        bundle.putString("hair", hair.getText().toString());
        bundle.putString("eyes", eyes.getText().toString());
        bundle.putString("faith", faith.getText().toString());
        bundle.putString("definingcharacteristics", defcharacteristics.getText().toString());
        dialogFragment3.setArguments(bundle);

    }

    public void startEquipmentDialog(View view) {
        FragmentManager fm = getFragmentManager();
        EquipmentInfoDialog dialogFragment6 = new EquipmentInfoDialog();
        dialogFragment6.show(fm, "Sample Fragment");
        Bundle bundle = new Bundle();
        Scanner s = new Scanner(money.getText().toString()).useDelimiter("\\s*GC\\s*");
        bundle.putString("money", s.next());
        bundle.putString("equipment", equipment.getText().toString());
        dialogFragment6.setArguments(bundle);

    }

    public void startCharacterStateDialog(View view) {
        FragmentManager fm = getFragmentManager();
        CharacterStateInfoDialog dialogFragment7 = new CharacterStateInfoDialog();
        dialogFragment7.show(fm, "Sample Fragment");

        Bundle bundle = new Bundle();
        bundle.putString("armorname", armName.getText().toString());
        bundle.putString("armbonus", armBonus.getText().toString());
        bundle.putString("spdmod", spdMod.getText().toString());
        bundle.putString("defmod", defMod.getText().toString());
        bundle.putString("miscarm", miscArmMod.getText().toString());
        bundle.putString("shieldmod", shieldMod.getText().toString());
        bundle.putString("racialdef", racialDEFMod.getText().toString());
        bundle.putString("initequipmod", initEquipMod.getText().toString());
        bundle.putString("miscinit", miscInitMod.getText().toString());
        bundle.putString("cmd", cmdRangeAbilityMod.getText().toString());
        bundle.putString("cmdskill", cmdRangeSkillMod.getText().toString());
        dialogFragment7.setArguments(bundle);

    }

    public void startMiscellaneousEditDialog(View view) {
        FragmentManager fm = getFragmentManager();
        MiscellaneousInfoDialog dialogFragment5 = new MiscellaneousInfoDialog();
        dialogFragment5.show(fm, "Sample Fragment");
        Bundle bundle = new Bundle();
        bundle.putString("connections", connections.getText().toString());
        bundle.putString("permanentinjuries", permanentinjuries.getText().toString());
        bundle.putString("notes", notes.getText().toString());
        dialogFragment5.setArguments(bundle);


    }

    public void startAbilitiesDialog(View view) {
        FragmentManager fm = getFragmentManager();
        AbilitiesInfoDialog dialogFragment6 = new AbilitiesInfoDialog();
        dialogFragment6.show(fm, "Sample Fragment");

    }

    public void startArchetypesDialog(View view) {
        FragmentManager fm = getFragmentManager();
        ArchetypesInfoDialog dialogFragment6 = new ArchetypesInfoDialog();
        dialogFragment6.show(fm, "Sample Fragment");

    }

    public void startSpellsDialog(View view) {
        FragmentManager fm = getFragmentManager();
        SpellsInfoDialog dialogFragment6 = new SpellsInfoDialog();
        dialogFragment6.show(fm, "Sample Fragment");

    }


    @Override
    public void onFinishCharacterDialog(String inputText, String inputText2, String inputText3, String inputText4) {
        charname.setText(inputText);
        racename.setText(inputText2);
        careername.setText(inputText3);
        archetypename.setText(inputText4);

        if ("".equals(inputText)) charname.setText("CHARACTER NAME");

        

    }

    @Override
    public void onFinishCharacteristicsDialog(String inputText, String inputText2, String inputText3, String inputText4,
                                              String inputText5, String inputText6, String inputText7, String inputText8,
                                              String inputText9) {

        phy.setText(inputText);
        spd.setText(inputText2);
        str.setText(inputText3);
        agi.setText(inputText4);
        prw.setText(inputText5);
        poi.setText(inputText6);
        intellect.setText(inputText7);
        arc.setText(inputText8);
        per.setText(inputText9);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        SharedPreferences sharedPref = getSharedPreferences(String.valueOf(bundle.getInt("characterID", 0)), Context.MODE_PRIVATE);

        final LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout4);
        container.removeAllViews();

            for (int i = 0; i < skillNameArrayList.size(); i++) {

                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_skill, null);

                final TextView textOut = (TextView)addView.findViewById(R.id.textout);
                final TextView textOut2 = (TextView)addView.findViewById(R.id.textView232);
                TextView textOutParent = (TextView)addView.findViewById(R.id.textView304);
                TextView textOutParentNum = (TextView)addView.findViewById(R.id.textView317);
                TextView textOutTotal = (TextView)addView.findViewById(R.id.textView234);
                int num1 = Integer.valueOf(inputText);
                int num2 = Integer.valueOf(inputText3);
                int num3 = Integer.valueOf(inputText4);
                int num4 = Integer.valueOf(inputText5);
                int num5 = Integer.valueOf(inputText6);
                int num6 = Integer.valueOf(inputText7);
                int num7 = Integer.valueOf(inputText9);


                textOut.setText(skillNameArrayList.get(i));
                textOut2.setText(skillNumArrayList.get(i));

                switch (skillNameArrayList.get(i)) {
                    case "Archery":
                    case "Crossbow":
                    case "Light Artillery":
                    case "Pistol":
                    case "Rifle":
                    case "Heavy Artillery":
                    case "Fell Calling":
                        textOutParent.setText("Poise");
                        textOutParentNum.setText(inputText6);
                        textOutTotal.setText("" + (num5 + skillNumArrayList.get(i)));
                        break;
                    case "Great Weapon":
                    case "Hand Weapon":
                    case "Lance":
                    case "Shield":
                    case "Thrown Weapon":
                    case "Unarmed Combat":
                        textOutParent.setText("Prowess");
                        textOutParentNum.setText(inputText5);
                        textOutTotal.setText("" + (num4 + skillNumArrayList.get(i)));
                        break;
                    case "Alchemy":
                    case "Cryptography":
                    case "Disguise":
                    case "Forensic Science":
                    case "Law":
                    case "Mechanikal Engineering":
                    case "Medicine":
                    case "Research":
                        textOutParent.setText("Intellect");
                        textOutParentNum.setText(inputText7);
                        textOutTotal.setText("" + (num6 + skillNumArrayList.get(i)));
                        break;
                    case "Bribery":
                    case "Command":
                    case "Deception":
                    case "Etiquette":
                    case "Animal Handling":
                    case "Interrogation":
                    case "Negotiation":
                    case "Oratory":
                    case "Intimidation":
                    case "Seduction":
                        textOutParent.setText("Social");
                        textOutParentNum.setText("*");
                        textOutTotal.setText(skillNumArrayList.get(i));
                        break;
                    case "Escape Artist":
                    case "Climbing":
                    case "Driving":
                    case "Lock Picking":
                    case "Pickpocket":
                    case "Rope Use":
                    case "Sneak":
                    case "Riding":
                        textOutParent.setText("Agility");
                        textOutParentNum.setText(inputText4);
                        textOutTotal.setText("" + (num3 + skillNumArrayList.get(i)));
                        break;
                    case "Detection":
                    case "Navigation":
                    case "Gambling":
                    case "Streetwise":
                    case "Survival":
                    case "Tracking":
                        textOutParent.setText("Perception");
                        textOutParentNum.setText(inputText9);
                        textOutTotal.setText("" + (num7 + skillNumArrayList.get(i)));
                        break;
                    case "Forgery":
                        textOutParent.setText("Agility or Intellect");
                        textOutParentNum.setText(inputText4 + "or" + inputText7);
                        textOutTotal.setText("" + (num3 + skillNumArrayList.get(i)) + "or" + (num6 + skillNumArrayList.get(i)));
                        break;
                    case "Jumping":
                        textOutParent.setText("Physique");
                        textOutParentNum.setText(inputText);
                        textOutTotal.setText("" + (num1 + skillNumArrayList.get(i)));
                        break;
                    case "Sailing":
                        textOutParent.setText("Intellect or Strength");
                        textOutParentNum.setText(inputText7 + "or" + inputText3);
                        textOutTotal.setText("" + (num6 + skillNumArrayList.get(i)) + "or" + (num2 + skillNumArrayList.get(i)));
                        break;
                    case "Swimming":
                        textOutParent.setText("Strength");
                        textOutParentNum.setText(inputText3);
                        textOutTotal.setText("" + (num2 + skillNumArrayList.get(i)));

                        break;
                    default:
                        textOutParent.setText("Intellect");
                        textOutParentNum.setText(inputText7);
                        textOutTotal.setText("" + (num6 + skillNumArrayList.get(i)));
                        break;
                }

                final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();

                addView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        alertDialog.setTitle(textOut.getText());
                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((LinearLayout) addView.getParent()).removeView(addView);
                                skillNameArrayList.remove(textOut.getText().toString());
                                skillNumArrayList.remove(textOut2.getText().toString());

                            }
                        });
                        alertDialog.show();

                        return true;
                    }
                });

                container.addView(addView);

            }


            final LinearLayout container2 = (LinearLayout)findViewById(R.id.linearLayout21);
            container2.removeAllViews();
            for (int i = 0; i < meleeNameArrayList.size(); i++) {
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_melee, null);
                final TextView name = (TextView)addView.findViewById(R.id.textView32);
                final TextView mat = (TextView)addView.findViewById(R.id.textView226);
                final TextView pow = (TextView)addView.findViewById(R.id.textView224);
                final TextView specialnotes = (TextView)addView.findViewById(R.id.textView206);

                name.setText(meleeNameArrayList.get(i));
                mat.setText(meleeMatArrayList.get(i));
                pow.setText("" + (Integer.parseInt(meleePowArrayList.get(i)) + Integer.parseInt(str.getText().toString())));
                specialnotes.setText(meleeSpecialNotesArrayList.get(i));

                final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
                addView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        alertDialog.setTitle(name.getText());
                        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ((LinearLayout)addView.getParent()).removeView(addView);
                                meleeNameArrayList.remove(name.getText().toString());
                                meleeMatArrayList.remove(mat.getText().toString());
                                meleePowArrayList.remove("" + (Integer.parseInt(pow.getText().toString()) - Integer.parseInt(str.getText().toString())));
                                meleeSpecialNotesArrayList.remove(specialnotes.getText().toString());
                            }
                        });
                        alertDialog.show();

                        return true;
                    }
                });


                addView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FragmentManager fm = getFragmentManager();
                        MeleeWeaponInfoDialog dialogFragment5 = new MeleeWeaponInfoDialog();
                        dialogFragment5.show(fm, "Sample Fragment");
                        Bundle bundle = new Bundle();
                        bundle.putInt("key", 1);
                        bundle.putString("name", name.getText().toString());
                        bundle.putString("mat", mat.getText().toString());
                        bundle.putString("pow", "" + (Integer.parseInt(pow.getText().toString()) - Integer.parseInt(str.getText().toString())));
                        bundle.putString("specialnotes", specialnotes.getText().toString());
                        dialogFragment5.setArguments(bundle);
                        meleeNameArrayList.remove(name.getText().toString());
                        meleeMatArrayList.remove(mat.getText().toString());
                        meleePowArrayList.remove("" + (Integer.parseInt(pow.getText().toString()) - Integer.parseInt(str.getText().toString())));
                        meleeSpecialNotesArrayList.remove(specialnotes.getText().toString());
                        container2.removeView(addView);

                    }
                });



                container2.addView(addView);

            }




        Scanner s1 = new Scanner(defMod.getText().toString()).useDelimiter("[^0-9]+");
        int i1 = s1.nextInt();
        Scanner s2 = new Scanner(armBonus.getText().toString()).useDelimiter("[^0-9]+");
        int i2 = s2.nextInt();


        phy.setText(inputText);
        agi.setText(inputText4);
        intellect.setText(inputText7);



        if ("".equals(inputText2)) {
            spd.setText("0");
        }
        if ("".equals(inputText3)) {
            str.setText("0");
        }

        if ("".equals(inputText5)) {
            prw.setText("0");
        }
        if ("".equals(inputText6)) {
            poi.setText("0");
        }

        if ("".equals(inputText8)) {
            arc.setText("0");
        }
        if ("".equals(inputText9)) {
            per.setText("0");
        }

        arm.setText("" + (Integer.valueOf(phy.getText().toString()) + i2 + Integer.valueOf(shieldMod.getText().toString()) + Integer.valueOf(miscArmMod.getText().toString())));
        def.setText("" + (Integer.valueOf(spd.getText().toString()) + Integer.valueOf(agi.getText().toString()) + Integer.valueOf(per.getText().toString()) + Integer.valueOf(racialDEFMod.getText().toString()) - i1));
        init.setText("" + (Integer.valueOf(spd.getText().toString()) + Integer.valueOf(prw.getText().toString()) + Integer.valueOf(per.getText().toString()) + Integer.valueOf(miscInitMod.getText().toString()) + Integer.valueOf(initEquipMod.getText().toString())));
        cmdRange.setText("" + (Integer.valueOf(intellect.getText().toString()) + Integer.valueOf(cmdRangeAbilityMod.getText().toString()) + Integer.valueOf(cmdRangeSkillMod.getText().toString())));

        setSpiral();

/*
            if ("3".equals(inputText)) {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(false);
                checkbox1_4.setEnabled(false);
                checkbox1_5.setEnabled(false);
                checkbox1_6.setEnabled(false);
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_1.setEnabled(true);
                checkbox2_2.setEnabled(false);
                checkbox2_3.setEnabled(false);
                checkbox2_4.setEnabled(false);
            } else if ("4".equals(inputText)) {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_3.setChecked(sharedPref.getBoolean("checkbox1_3", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(true);
                checkbox1_4.setEnabled(false);
                checkbox1_5.setEnabled(false);
                checkbox1_6.setEnabled(false);
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_1.setEnabled(true);
                checkbox2_2.setEnabled(false);
                checkbox2_3.setEnabled(false);
                checkbox2_4.setEnabled(false);
            } else if ("5".equals(inputText)) {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_3.setChecked(sharedPref.getBoolean("checkbox1_3", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(true);
                checkbox1_4.setEnabled(false);
                checkbox1_5.setEnabled(false);
                checkbox1_6.setEnabled(false);
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_2.setChecked(sharedPref.getBoolean("checkbox2_2", false));
                checkbox2_1.setEnabled(true);
                checkbox2_2.setEnabled(true);
                checkbox2_3.setEnabled(false);
                checkbox2_4.setEnabled(false);
            } else if ("6".equals(inputText)) {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_3.setChecked(sharedPref.getBoolean("checkbox1_3", false));
                checkbox1_4.setChecked(sharedPref.getBoolean("checkbox1_4", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(true);
                checkbox1_4.setEnabled(true);
                checkbox1_5.setEnabled(false);
                checkbox1_6.setEnabled(false);
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_2.setChecked(sharedPref.getBoolean("checkbox2_2", false));
                checkbox2_1.setEnabled(true);
                checkbox2_2.setEnabled(true);
                checkbox2_3.setEnabled(false);
                checkbox2_4.setEnabled(false);
            } else if ("7".equals(inputText)) {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_3.setChecked(sharedPref.getBoolean("checkbox1_3", false));
                checkbox1_4.setChecked(sharedPref.getBoolean("checkbox1_4", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(true);
                checkbox1_4.setEnabled(true);
                checkbox1_5.setEnabled(false);
                checkbox1_6.setEnabled(false);
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_2.setChecked(sharedPref.getBoolean("checkbox2_2", false));
                checkbox2_3.setChecked(sharedPref.getBoolean("checkbox2_3", false));
                checkbox2_1.setEnabled(true);
                checkbox2_2.setEnabled(true);
                checkbox2_3.setEnabled(true);
                checkbox2_4.setEnabled(false);
            } else if ("8".equals(inputText)) {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_3.setChecked(sharedPref.getBoolean("checkbox1_3", false));
                checkbox1_4.setChecked(sharedPref.getBoolean("checkbox1_4", false));
                checkbox1_5.setChecked(sharedPref.getBoolean("checkbox1_5", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(true);
                checkbox1_4.setEnabled(true);
                checkbox1_5.setEnabled(true);
                checkbox1_6.setEnabled(false);
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_2.setChecked(sharedPref.getBoolean("checkbox2_2", false));
                checkbox2_3.setChecked(sharedPref.getBoolean("checkbox2_3", false));
                checkbox2_1.setEnabled(true);
                checkbox2_2.setEnabled(true);
                checkbox2_3.setEnabled(true);
                checkbox2_4.setEnabled(false);
            }  else if ("9".equals(inputText)) {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_3.setChecked(sharedPref.getBoolean("checkbox1_3", false));
                checkbox1_4.setChecked(sharedPref.getBoolean("checkbox1_4", false));
                checkbox1_5.setChecked(sharedPref.getBoolean("checkbox1_5", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(true);
                checkbox1_4.setEnabled(true);
                checkbox1_5.setEnabled(true);
                checkbox1_6.setEnabled(false);
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_2.setChecked(sharedPref.getBoolean("checkbox2_2", false));
                checkbox2_3.setChecked(sharedPref.getBoolean("checkbox2_3", false));
                checkbox2_4.setChecked(sharedPref.getBoolean("checkbox2_4", false));
                checkbox2_1.setEnabled(true);
                checkbox2_2.setEnabled(true);
                checkbox2_3.setEnabled(true);
                checkbox2_4.setEnabled(true);
            } else if ("10".equals(inputText)) {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_3.setChecked(sharedPref.getBoolean("checkbox1_3", false));
                checkbox1_4.setChecked(sharedPref.getBoolean("checkbox1_4", false));
                checkbox1_5.setChecked(sharedPref.getBoolean("checkbox1_5", false));
                checkbox1_6.setChecked(sharedPref.getBoolean("checkbox1_6", false));
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_2.setChecked(sharedPref.getBoolean("checkbox2_2", false));
                checkbox2_3.setChecked(sharedPref.getBoolean("checkbox2_3", false));
                checkbox2_4.setChecked(sharedPref.getBoolean("checkbox2_4", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(true);
                checkbox1_4.setEnabled(true);
                checkbox1_5.setEnabled(true);
                checkbox1_6.setEnabled(true);
                checkbox2_1.setEnabled(true);
                checkbox2_2.setEnabled(true);
                checkbox2_3.setEnabled(true);
                checkbox2_4.setEnabled(true);
            } else {
                checkbox1_1.setChecked(sharedPref.getBoolean("checkbox1_1", false));
                checkbox1_2.setChecked(sharedPref.getBoolean("checkbox1_2", false));
                checkbox1_1.setEnabled(true);
                checkbox1_2.setEnabled(true);
                checkbox1_3.setEnabled(false);
                checkbox1_4.setEnabled(false);
                checkbox1_5.setEnabled(false);
                checkbox1_6.setEnabled(false);
                checkbox2_1.setChecked(sharedPref.getBoolean("checkbox2_1", false));
                checkbox2_1.setEnabled(true);
                checkbox2_2.setChecked(false);
                checkbox2_3.setEnabled(false);
                checkbox2_4.setEnabled(false);
            }



            if ("3".equals(inputText4)) {
                checkbox3_1.setChecked(sharedPref.getBoolean("checkbox3_1", false));
                checkbox3_2.setChecked(sharedPref.getBoolean("checkbox3_2", false));
                checkbox3_1.setEnabled(true);
                checkbox3_2.setEnabled(true);
                checkbox3_3.setEnabled(false);
                checkbox3_4.setEnabled(false);
                checkbox3_5.setEnabled(false);
                checkbox4_1.setChecked(sharedPref.getBoolean("checkbox4_1", false));
                checkbox4_2.setEnabled(true);
                checkbox4_2.setChecked(false);
                checkbox4_3.setEnabled(false);
            } else if ("4".equals(inputText4)) {
                checkbox3_1.setChecked(sharedPref.getBoolean("checkbox3_1", false));
                checkbox3_2.setChecked(sharedPref.getBoolean("checkbox3_2", false));
                checkbox3_3.setChecked(sharedPref.getBoolean("checkbox3_3", false));
                checkbox3_1.setEnabled(true);
                checkbox3_2.setEnabled(true);
                checkbox3_3.setEnabled(true);
                checkbox3_4.setEnabled(false);
                checkbox3_5.setEnabled(false);
                checkbox4_1.setChecked(sharedPref.getBoolean("checkbox4_1", false));
                checkbox4_2.setEnabled(true);
                checkbox4_2.setChecked(false);
                checkbox4_3.setEnabled(false);
            } else if ("5".equals(inputText4)) {
                checkbox3_1.setChecked(sharedPref.getBoolean("checkbox3_1", false));
                checkbox3_2.setChecked(sharedPref.getBoolean("checkbox3_2", false));
                checkbox3_3.setChecked(sharedPref.getBoolean("checkbox3_3", false));
                checkbox3_1.setEnabled(true);
                checkbox3_2.setEnabled(true);
                checkbox3_3.setEnabled(true);
                checkbox3_4.setEnabled(false);
                checkbox3_5.setEnabled(false);
                checkbox4_1.setChecked(sharedPref.getBoolean("checkbox4_1", false));
                checkbox4_2.setChecked(sharedPref.getBoolean("checkbox4_2", false));
                checkbox4_1.setEnabled(true);
                checkbox4_2.setEnabled(true);
                checkbox4_3.setEnabled(false);
            } else if ("6".equals(inputText4)) {
                checkbox3_1.setChecked(sharedPref.getBoolean("checkbox3_1", false));
                checkbox3_2.setChecked(sharedPref.getBoolean("checkbox3_2", false));
                checkbox3_3.setChecked(sharedPref.getBoolean("checkbox3_3", false));
                checkbox3_4.setChecked(sharedPref.getBoolean("checkbox3_4", false));
                checkbox3_1.setEnabled(true);
                checkbox3_2.setEnabled(true);
                checkbox3_3.setEnabled(true);
                checkbox3_4.setEnabled(true);
                checkbox3_5.setEnabled(false);
                checkbox4_1.setChecked(sharedPref.getBoolean("checkbox4_1", false));
                checkbox4_2.setChecked(sharedPref.getBoolean("checkbox4_2", false));
                checkbox4_1.setEnabled(true);
                checkbox4_2.setEnabled(true);
                checkbox4_3.setEnabled(false);
            } else if ("7".equals(inputText4)) {
                checkbox3_1.setChecked(sharedPref.getBoolean("checkbox3_1", false));
                checkbox3_2.setChecked(sharedPref.getBoolean("checkbox3_2", false));
                checkbox3_3.setChecked(sharedPref.getBoolean("checkbox3_3", false));
                checkbox3_4.setChecked(sharedPref.getBoolean("checkbox3_4", false));
                checkbox3_1.setEnabled(true);
                checkbox3_2.setEnabled(true);
                checkbox3_3.setEnabled(true);
                checkbox3_4.setEnabled(true);
                checkbox3_5.setEnabled(false);
                checkbox4_1.setChecked(sharedPref.getBoolean("checkbox4_1", false));
                checkbox4_2.setChecked(sharedPref.getBoolean("checkbox4_2", false));
                checkbox4_3.setChecked(sharedPref.getBoolean("checkbox4_3", false));
                checkbox4_1.setEnabled(true);
                checkbox4_2.setEnabled(true);
                checkbox4_3.setEnabled(true);
            } else if ("8".equals(inputText4)) {
                checkbox3_1.setChecked(sharedPref.getBoolean("checkbox3_1", false));
                checkbox3_2.setChecked(sharedPref.getBoolean("checkbox3_2", false));
                checkbox3_3.setChecked(sharedPref.getBoolean("checkbox3_3", false));
                checkbox3_4.setChecked(sharedPref.getBoolean("checkbox3_4", false));
                checkbox3_5.setChecked(sharedPref.getBoolean("checkbox3_5", false));
                checkbox4_1.setChecked(sharedPref.getBoolean("checkbox4_1", false));
                checkbox4_2.setChecked(sharedPref.getBoolean("checkbox4_2", false));
                checkbox4_3.setChecked(sharedPref.getBoolean("checkbox4_3", false));
                checkbox3_1.setEnabled(true);
                checkbox3_2.setEnabled(true);
                checkbox3_3.setEnabled(true);
                checkbox3_4.setEnabled(true);
                checkbox3_5.setEnabled(true);
                checkbox4_1.setEnabled(true);
                checkbox4_2.setEnabled(true);
                checkbox4_3.setEnabled(true);
            } else {
                checkbox3_1.setChecked(sharedPref.getBoolean("checkbox3_1", false));
                checkbox3_2.setChecked(sharedPref.getBoolean("checkbox3_2", false));
                checkbox3_1.setEnabled(true);
                checkbox3_2.setEnabled(true);
                checkbox3_3.setEnabled(false);
                checkbox3_4.setEnabled(false);
                checkbox3_5.setEnabled(false);
                checkbox4_1.setChecked(sharedPref.getBoolean("checkbox4_1", false));
                checkbox4_1.setEnabled(true);
                checkbox4_2.setChecked(false);
                checkbox4_3.setEnabled(false);
            }




            if ("3".equals(inputText7)) {
                checkbox5_1.setChecked(sharedPref.getBoolean("checkbox5_1", false));
                checkbox5_2.setChecked(sharedPref.getBoolean("checkbox5_2", false));
                checkbox5_1.setEnabled(true);
                checkbox5_2.setEnabled(true);
                checkbox5_3.setEnabled(false);
                checkbox5_4.setEnabled(false);
                checkbox5_5.setEnabled(false);
                checkbox6_1.setChecked(sharedPref.getBoolean("checkbox6_1", false));
                checkbox6_1.setEnabled(true);
                checkbox6_2.setEnabled(false);
                checkbox6_3.setEnabled(false);
            } else if ("4".equals(inputText7)) {
                checkbox5_1.setChecked(sharedPref.getBoolean("checkbox5_1", false));
                checkbox5_2.setChecked(sharedPref.getBoolean("checkbox5_2", false));
                checkbox5_3.setChecked(sharedPref.getBoolean("checkbox5_3", false));
                checkbox5_1.setEnabled(true);
                checkbox5_2.setEnabled(true);
                checkbox5_3.setEnabled(true);
                checkbox5_4.setEnabled(false);
                checkbox5_5.setEnabled(false);
                checkbox6_1.setChecked(sharedPref.getBoolean("checkbox6_1", false));
                checkbox6_1.setEnabled(true);
                checkbox6_2.setEnabled(false);
                checkbox6_3.setEnabled(false);
            } else if ("5".equals(inputText7)) {
                checkbox5_1.setChecked(sharedPref.getBoolean("checkbox6_1", false));
                checkbox5_2.setChecked(sharedPref.getBoolean("checkbox6_2", false));
                checkbox5_3.setChecked(sharedPref.getBoolean("checkbox6_3", false));
                checkbox5_1.setEnabled(true);
                checkbox5_2.setEnabled(true);
                checkbox5_3.setEnabled(true);
                checkbox5_4.setEnabled(false);
                checkbox5_5.setEnabled(false);
                checkbox6_1.setChecked(sharedPref.getBoolean("checkbox6_1", false));
                checkbox6_2.setChecked(sharedPref.getBoolean("checkbox6_2", false));
                checkbox6_1.setEnabled(true);
                checkbox6_2.setEnabled(true);
                checkbox6_3.setEnabled(false);
            } else if ("6".equals(inputText7)) {
                checkbox5_1.setChecked(sharedPref.getBoolean("checkbox5_1", false));
                checkbox5_2.setChecked(sharedPref.getBoolean("checkbox5_2", false));
                checkbox5_3.setChecked(sharedPref.getBoolean("checkbox5_3", false));
                checkbox5_4.setChecked(sharedPref.getBoolean("checkbox5_4", false));
                checkbox5_1.setEnabled(true);
                checkbox5_2.setEnabled(true);
                checkbox5_3.setEnabled(true);
                checkbox5_4.setEnabled(true);
                checkbox5_5.setEnabled(false);
                checkbox6_1.setChecked(sharedPref.getBoolean("checkbox6_1", false));
                checkbox6_2.setChecked(sharedPref.getBoolean("checkbox6_2", false));
                checkbox6_1.setEnabled(true);
                checkbox6_2.setEnabled(true);
                checkbox6_3.setEnabled(false);
            } else if ("7".equals(inputText7)) {
                checkbox5_1.setChecked(sharedPref.getBoolean("checkbox5_1", false));
                checkbox5_2.setChecked(sharedPref.getBoolean("checkbox5_2", false));
                checkbox5_3.setChecked(sharedPref.getBoolean("checkbox5_3", false));
                checkbox5_4.setChecked(sharedPref.getBoolean("checkbox5_4", false));
                checkbox5_1.setEnabled(true);
                checkbox5_2.setEnabled(true);
                checkbox5_3.setEnabled(true);
                checkbox5_4.setEnabled(true);
                checkbox5_5.setEnabled(false);
                checkbox6_1.setChecked(sharedPref.getBoolean("checkbox6_1", false));
                checkbox6_2.setChecked(sharedPref.getBoolean("checkbox6_2", false));
                checkbox6_3.setChecked(sharedPref.getBoolean("checkbox6_3", false));
                checkbox6_1.setEnabled(true);
                checkbox6_2.setEnabled(true);
                checkbox6_3.setEnabled(true);
            } else if ("8".equals(inputText7)) {
                checkbox5_1.setChecked(sharedPref.getBoolean("checkbox5_1", false));
                checkbox5_2.setChecked(sharedPref.getBoolean("checkbox5_2", false));
                checkbox5_3.setChecked(sharedPref.getBoolean("checkbox5_3", false));
                checkbox5_4.setChecked(sharedPref.getBoolean("checkbox5_4", false));
                checkbox5_5.setChecked(sharedPref.getBoolean("checkbox5_5", false));
                checkbox6_1.setChecked(sharedPref.getBoolean("checkbox6_1", false));
                checkbox6_2.setChecked(sharedPref.getBoolean("checkbox6_2", false));
                checkbox6_3.setChecked(sharedPref.getBoolean("checkbox6_3", false));
                checkbox5_1.setEnabled(true);
                checkbox5_2.setEnabled(true);
                checkbox5_3.setEnabled(true);
                checkbox5_4.setEnabled(true);
                checkbox5_5.setEnabled(true);
                checkbox6_1.setEnabled(true);
                checkbox6_2.setEnabled(true);
                checkbox6_3.setEnabled(true);
            } else {
                checkbox5_1.setChecked(sharedPref.getBoolean("checkbox5_1", false));
                checkbox5_2.setChecked(sharedPref.getBoolean("checkbox5_2", false));
                checkbox5_1.setEnabled(true);
                checkbox5_2.setEnabled(true);
                checkbox5_3.setEnabled(false);
                checkbox5_4.setEnabled(false);
                checkbox5_5.setEnabled(false);
                checkbox6_1.setChecked(sharedPref.getBoolean("checkbox6_1", false));
                checkbox6_1.setEnabled(true);
                checkbox6_2.setEnabled(false);
                checkbox6_3.setEnabled(false);
            }




        checkbox1_1.setOnCheckedChangeListener(this);
        checkbox1_2.setOnCheckedChangeListener(this);
        checkbox1_3.setOnCheckedChangeListener(this);
        checkbox1_4.setOnCheckedChangeListener(this);
        checkbox1_5.setOnCheckedChangeListener(this);
        checkbox1_6.setOnCheckedChangeListener(this);
        checkbox2_1.setOnCheckedChangeListener(this);
        checkbox2_2.setOnCheckedChangeListener(this);
        checkbox2_3.setOnCheckedChangeListener(this);
        checkbox2_4.setOnCheckedChangeListener(this);
        checkbox3_1.setOnCheckedChangeListener(this);
        checkbox3_2.setOnCheckedChangeListener(this);
        checkbox3_3.setOnCheckedChangeListener(this);
        checkbox3_4.setOnCheckedChangeListener(this);
        checkbox3_5.setOnCheckedChangeListener(this);
        checkbox4_1.setOnCheckedChangeListener(this);
        checkbox4_2.setOnCheckedChangeListener(this);
        checkbox4_3.setOnCheckedChangeListener(this);
        checkbox5_1.setOnCheckedChangeListener(this);
        checkbox5_2.setOnCheckedChangeListener(this);
        checkbox5_3.setOnCheckedChangeListener(this);
        checkbox5_4.setOnCheckedChangeListener(this);
        checkbox5_5.setOnCheckedChangeListener(this);
        checkbox6_1.setOnCheckedChangeListener(this);
        checkbox6_2.setOnCheckedChangeListener(this);
        checkbox6_3.setOnCheckedChangeListener(this);
        */


    }

    @Override
    public void onFinishDescriptionDialog(String inputText, String inputText2, String inputText3, String inputText4, String inputText5, String inputText6, String inputText7) {

        if ("".equals(inputText)) {
            gender.setText("");
        } else {
            gender.setText(inputText);
        }

        if ("".equals(inputText2)) {
            weight.setText("");
        } else {
            weight.setText(inputText2);
        }

        if ("".equals(inputText3)) {
            height.setText("");
        } else {
            height.setText(inputText3);

        }

        if ("".equals(inputText4)) {
            hair.setText("");
        } else {
            hair.setText(inputText4);
        }
        if ("".equals(inputText5)) {
            eyes.setText("");
        }else {
            eyes.setText(inputText5);
        }
        if ("".equals(inputText6)) {
            faith.setText("");
        }
        else {
            faith.setText(inputText6);
        }

        if ("".equals(inputText7))
            defcharacteristics.setText("");
        else defcharacteristics.setText(inputText7);



    }




    @Override
    public void onFinishEquipmentDialog(String inputText, String inputText2) {
        money.setText(inputText + " GC");
        equipment.setText(inputText2);
    }

    @Override
    public void onFinishSkillsDialog(String inputText, String inputText2) {

        LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout4);
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row_skill, null);
        final TextView textOut = (TextView)addView.findViewById(R.id.textout);
        TextView textOutParent = (TextView)addView.findViewById(R.id.textView304);
        TextView textOutParentNum = (TextView)addView.findViewById(R.id.textView317);
        final TextView textOutSkillLevelNum = (TextView)addView.findViewById(R.id.textView232);
        TextView textOutTotal = (TextView)addView.findViewById(R.id.textView234);
        int num1 = Integer.valueOf(phy.getText().toString());
        int num2 = Integer.valueOf(str.getText().toString());
        int num3 = Integer.valueOf(agi.getText().toString());
        int num4 = Integer.valueOf(prw.getText().toString());
        int num5 = Integer.valueOf(poi.getText().toString());
        int num6 = Integer.valueOf(intellect.getText().toString());
        int num7 = Integer.valueOf(per.getText().toString());


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        skillNameArrayList.add(inputText);
        skillNumArrayList.add(inputText2);

        sharedPref = getSharedPreferences(String.valueOf(bundle.getInt("characterID", 0)), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        textOut.setText(inputText);
        switch (inputText) {
            case "Archery":
            case "Crossbow":
            case "Light Artillery":
            case "Pistol":
            case "Rifle":
            case "Heavy Artillery":
            case "Fell Calling":
                textOutParent.setText("Poise:");
                textOutParentNum.setText(poi.getText());
                textOutTotal.setText("" + (num5 + Integer.valueOf(inputText2)));
                break;
            case "Great Weapon":
            case "Hand Weapon":
            case "Lance":
            case "Shield":
            case "Thrown Weapon":
            case "Unarmed Combat":
                textOutParent.setText("Prowess:");
                textOutParentNum.setText(prw.getText());
                textOutTotal.setText("" + (num4 + Integer.valueOf(inputText2)));
                break;
            case "Alchemy":
            case "Cryptography":
            case "Disguise":
            case "Forensic Science":
            case "Law":
            case "Mechanikal Engineering":
            case "Medicine":
            case "Research":
                textOutParent.setText("Intellect:");
                textOutParentNum.setText(intellect.getText());
                textOutTotal.setText("" + (num6 + Integer.valueOf(inputText2)));
                break;
            case "Bribery":
            case "Command" :
            case "Deception":
            case "Etiquette":
            case "Animal Handling":
            case "Interrogation":
            case "Negotiation":
            case "Oratory":
            case "Intimidation":
            case "Seduction":
                textOutParent.setText("Social:");
                textOutParentNum.setText("*");
                textOutTotal.setText(inputText2);
                break;
            case "Escape Artist":
            case "Climbing":
            case "Driving":
            case "Lock Picking":
            case "Pickpocket":
            case "Rope Use":
            case "Sneak":
            case "Riding":
                textOutParent.setText("Agility:");
                textOutParentNum.setText(agi.getText());
                textOutTotal.setText("" + (num3 + Integer.valueOf(inputText2)));
                break;
            case "Detection":
            case "Navigation":
            case "Gambling":
            case "Streetwise":
            case "Survival":
            case "Tracking":
                textOutParent.setText("Perception:");
                textOutParentNum.setText(per.getText());
                textOutTotal.setText("" + (num7 + Integer.valueOf(inputText2)));
                break;
            case "Forgery":
                textOutParent.setText("Agility or Intellect:");
                textOutParentNum.setText(agi.getText() + " or " + intellect.getText());
                textOutTotal.setText("" + (num3 + Integer.valueOf(inputText2)) + " or " + (num6 + Integer.valueOf(inputText2)));
                break;
            case "Jumping":
                textOutParent.setText("Physique:");
                textOutParentNum.setText(phy.getText());
                textOutTotal.setText("" + (num1 + Integer.valueOf(inputText2)));
                break;
            case "Sailing":
                textOutParent.setText("Intellect or Strength:");
                textOutParentNum.setText(intellect.getText() + " or " + str.getText());
                textOutTotal.setText("" + (num6 + Integer.valueOf(inputText2)) + " or " + (num2 + Integer.valueOf(inputText2)));

                break;
            case "Swimming":
                textOutParent.setText("Strength:");
                textOutParentNum.setText(str.getText());
                textOutTotal.setText("" + (num2 + Integer.valueOf(inputText2)));
                break;
            default:
                textOutParent.setText("Intellect:");
                textOutParentNum.setText(intellect.getText());
                textOutTotal.setText("" + (num6 + Integer.valueOf(inputText2)));
                break;
        }

        textOutSkillLevelNum.setText(inputText2);

        editor.putString(inputText, inputText2);
        editor.apply();


        final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
        addView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog.setTitle(textOut.getText());
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                        skillNameArrayList.remove(textOut.getText().toString());
                        skillNumArrayList.remove(textOutSkillLevelNum.getText().toString());


                    }
                });
                alertDialog.show();

                return true;
            }
        });



        container.addView(addView);


    }



    @Override
    public void onFinishCharacterStateDialog(String inputText, String inputText2, String inputText3, String inputText4, String inputText5,
                                             String inputText6, String inputText7, String inputText8, String inputText9, String inputText10, String inputText11) {
        int num = Integer.valueOf(spd.getText().toString());
        int num2 = Integer.valueOf(agi.getText().toString());
        int num3 = Integer.valueOf(per.getText().toString());

        defMod.setText(inputText4);
        racialDEFMod.setText(inputText7);

        if (inputText4.equals("")) defMod.setText("0");
        if (inputText7.equals("")) racialDEFMod.setText("0");


        def.setText("" + (num + num2 + num3 - Integer.valueOf(defMod.getText().toString()) + Integer.valueOf(racialDEFMod.getText().toString())));

        int num4 = Integer.valueOf(phy.getText().toString());

        armBonus.setText(inputText2);
        miscArmMod.setText(inputText5);
        shieldMod.setText(inputText6);
        armName.setText(inputText);
        spdMod.setText(inputText3);

        if (inputText2.equals("")) armBonus.setText("0");
        if (inputText5.equals("")) miscArmMod.setText("0");
        if (inputText6.equals("")) shieldMod.setText("0");
        if (inputText3.equals("")) spdMod.setText("0");
        if (inputText.equals("")) armName.setText("Armor Name");

        arm.setText("" + (num4 + Integer.valueOf(armBonus.getText().toString()) + Integer.valueOf(miscArmMod.getText().toString()) + Integer.valueOf(shieldMod.getText().toString())));


        int num5 = Integer.valueOf(spd.getText().toString());
        int num6 = Integer.valueOf(prw.getText().toString());
        int num7 = Integer.valueOf(per.getText().toString());

        initEquipMod.setText(inputText8);
        miscInitMod.setText(inputText9);

        if (inputText8.equals("")) initEquipMod.setText("0");
        if (inputText9.equals("")) miscInitMod.setText("0");

        init.setText("" + (num5 + num6 + num7 + Integer.valueOf(initEquipMod.getText().toString()) + Integer.valueOf(miscInitMod.getText().toString())));



        int num8 = Integer.valueOf(intellect.getText().toString());

        cmdRangeAbilityMod.setText(inputText10);
        cmdRangeSkillMod.setText(inputText11);


        if (inputText10.equals("")) cmdRangeAbilityMod.setText("0");
        if (inputText11.equals("")) cmdRangeSkillMod.setText("0");

        cmdRange.setText("" + (num8 + Integer.valueOf(cmdRangeAbilityMod.getText().toString()) + Integer.valueOf(cmdRangeSkillMod.getText().toString())));



    }

    @Override
    public void onFinishMeleeWeaponDialog(String inputText, String inputText2, final String inputText3, String inputText4) {

        final LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout21);
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row_melee, null);
        final TextView name = (TextView)addView.findViewById(R.id.textView32);
        final TextView mat = (TextView)addView.findViewById(R.id.textView226);
        final TextView pow = (TextView)addView.findViewById(R.id.textView224);
        final TextView specialnotes = (TextView)addView.findViewById(R.id.textView206);

        name.setText(inputText);
        mat.setText(inputText2);
        pow.setText("" + (Integer.parseInt(inputText3) + Integer.parseInt(str.getText().toString())));
        specialnotes.setText(inputText4);

        meleeNameArrayList.add(inputText);
        meleeMatArrayList.add(inputText2);
        meleePowArrayList.add(inputText3);
        meleeSpecialNotesArrayList.add(inputText4);



        final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
        addView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog.setTitle(name.getText());
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                        meleeNameArrayList.remove(name.getText().toString());
                        meleeMatArrayList.remove(mat.getText().toString());
                        meleePowArrayList.remove(pow.getText().toString());
                        meleeSpecialNotesArrayList.remove(specialnotes.getText().toString());
                    }
                });
                alertDialog.show();

                return true;
            }
        });


        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                MeleeWeaponInfoDialog dialogFragment5 = new MeleeWeaponInfoDialog();
                dialogFragment5.show(fm, "Sample Fragment");
                Bundle bundle = new Bundle();
                bundle.putString("name", name.getText().toString());
                bundle.putString("mat", mat.getText().toString());
                bundle.putString("pow", inputText3);
                bundle.putString("specialnotes", specialnotes.getText().toString());
                bundle.putInt("key", 1);
                dialogFragment5.setArguments(bundle);
                meleeNameArrayList.remove(name.getText().toString());
                meleeMatArrayList.remove(mat.getText().toString());
                meleePowArrayList.remove(inputText3);
                meleeSpecialNotesArrayList.remove(specialnotes.getText().toString());
                container.removeView(addView);
            }
        });



        container.addView(addView);
    }

    @Override
    public void onFinishRangeWeaponDialog(String inputText, String inputText2, String inputText3, String inputText4, String inputText5, String inputText6, String inputText7, String inputText8) {

        final LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout21);
        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row_range, null);
        final TextView name = (TextView)addView.findViewById(R.id.textView32);
        final TextView rat = (TextView)addView.findViewById(R.id.textView277);
        final TextView rng = (TextView)addView.findViewById(R.id.textView273);
        final TextView aoe = (TextView)addView.findViewById(R.id.textView226);
        final TextView pow = (TextView)addView.findViewById(R.id.textView224);
        final TextView ammocurrent = (TextView)addView.findViewById(R.id.textView286);
        final TextView ammototal = (TextView)addView.findViewById(R.id.textView236);
        final TextView special = (TextView)addView.findViewById(R.id.textView206);

        name.setText(inputText);
        rat.setText(inputText2);
        rng.setText(inputText3);
        aoe.setText(inputText4);
        pow.setText(inputText5);
        ammocurrent.setText(inputText6);
        ammototal.setText(inputText7);
        special.setText(inputText8);

        rangeNameArrayList.add(inputText);
        rangeRatArrayList.add(inputText2);
        rangeRngArrayList.add(inputText3);
        rangeAoeArrayList.add(inputText4);
        rangePowArrayList.add(inputText5);
        rangeCurrentAmmoArrayList.add(inputText6);
        rangeTotalAmmoArrayList.add(inputText7);
        rangeSpecialNotesArrayList.add(inputText8);



        final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
        addView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog.setTitle(name.getText());
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((LinearLayout) addView.getParent()).removeView(addView);
                        rangeNameArrayList.remove(name.getText().toString());
                        rangeRatArrayList.remove(rat.getText().toString());
                        rangeRngArrayList.remove(rng.getText().toString());
                        rangeAoeArrayList.remove(aoe.getText().toString());
                        rangePowArrayList.remove(pow.getText().toString());
                        rangeCurrentAmmoArrayList.remove(ammocurrent.getText().toString());
                        rangeTotalAmmoArrayList.remove(ammototal.getText().toString());
                        rangeSpecialNotesArrayList.remove(special.getText().toString());
                    }
                });
                alertDialog.show();

                return true;
            }
        });


        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                RangedWeaponInfoDialog dialogFragment5 = new RangedWeaponInfoDialog();
                dialogFragment5.show(fm, "Sample Fragment");
                Bundle bundle = new Bundle();
                bundle.putString("name", name.getText().toString());
                bundle.putString("rat", rat.getText().toString());
                bundle.putString("rng", rng.getText().toString());
                bundle.putString("aoe", aoe.getText().toString());
                bundle.putString("pow", pow.getText().toString());
                bundle.putString("ammocurrent", ammocurrent.getText().toString());
                bundle.putString("ammototal", ammototal.getText().toString());
                bundle.putString("specialnotes", special.getText().toString());
                bundle.putInt("key", 1);
                dialogFragment5.setArguments(bundle);
                rangeNameArrayList.remove(name.getText().toString());
                rangeRatArrayList.remove(rat.getText().toString());
                rangeRngArrayList.remove(rng.getText().toString());
                rangeAoeArrayList.remove(aoe.getText().toString());
                rangePowArrayList.remove(pow.getText().toString());
                rangeCurrentAmmoArrayList.remove(ammocurrent.getText().toString());
                rangeTotalAmmoArrayList.remove(ammototal.getText().toString());
                rangeSpecialNotesArrayList.remove(special.getText().toString());
                container.removeView(addView);
                container.clearAnimation();

            }
        });



        container.addView(addView);
    }

    @Override
    public void onFinishMiscellaneousDialog(String inputText, String inputText2, String inputText3) {
        connections.setText(inputText);
        permanentinjuries.setText(inputText2);
        notes.setText(inputText3);

    }

    @Override
    public void onFinishAbilitiesDialog(String inputText, String inputText2, String inputText3) {

        final LinearLayout container = (LinearLayout)findViewById(R.id.container);

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row_ability, null);
        final TextView textOut = (TextView)addView.findViewById(R.id.textout);
        final TextView textOut3 = (TextView)addView.findViewById(R.id.textView317);
        final LinearLayout dynamicLayout = (LinearLayout)addView.findViewById(R.id.linearLayout1);

        textOut.setText(inputText);
        textOut3.setText(inputText3);

        abilityNameArrayList.add(inputText);
        abilityDescriptionArrayList.add(inputText3);


        final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
        addView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog.setTitle(textOut.getText());
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                        abilityNameArrayList.remove(textOut.getText().toString());
                        abilityDescriptionArrayList.remove(textOut3.getText().toString());

                    }
                });
                alertDialog.show();

                return true;
            }
        });


        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dynamicLayout.getVisibility() == View.GONE) {
                    dynamicLayout.setVisibility(View.VISIBLE);
                } else {
                    dynamicLayout.setVisibility(View.GONE);
                }

            }
        });



        container.addView(addView);
    }

    @Override
    public void onFinishArchetypeDialog(String inputText, String inputText2) {

        final LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout9);

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row_archetype, null);
        final TextView textOut = (TextView)addView.findViewById(R.id.textout);
        final TextView textOut3 = (TextView)addView.findViewById(R.id.textView317);

        textOut.setText(inputText);
        textOut3.setText(inputText2);

        archetypeNameArrayList.add(inputText);
        archetypeDescriptionArrayList.add(inputText2);


        final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
        addView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog.setTitle(textOut.getText());
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                        archetypeNameArrayList.remove(textOut.getText().toString());
                        archetypeDescriptionArrayList.remove(textOut3.getText().toString());

                    }
                });
                alertDialog.show();

                return true;
            }
        });


        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textOut3.getVisibility() == View.GONE) {
                    textOut3.setVisibility(View.VISIBLE);
                } else {
                    textOut3.setVisibility(View.GONE);
                }

            }
        });



        container.addView(addView);
    }



    @Override
    public void onFinishSpellsDialog(String inputText, String inputText2, String inputText3, String inputText4, String inputText5, String inputText6, String inputText7, String inputText8) {

        LinearLayout container = (LinearLayout)findViewById(R.id.linearLayout2);

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.row_spell, null);
        final TextView textOut = (TextView)addView.findViewById(R.id.textout);
        final TextView textcost = (TextView)addView.findViewById(R.id.textView393);
        final TextView textrng = (TextView)addView.findViewById(R.id.textView394);
        final TextView textaoe = (TextView)addView.findViewById(R.id.textView395);
        final TextView textpow = (TextView)addView.findViewById(R.id.textView396);
        final TextView textup = (TextView)addView.findViewById(R.id.textView397);
        final TextView textoff = (TextView)addView.findViewById(R.id.textView398);
        final TextView textdescript = (TextView)addView.findViewById(R.id.textView399);
        final LinearLayout dynamicLayout = (LinearLayout)addView.findViewById(R.id.linearLayout7);

        textOut.setText(inputText);
        textcost.setText(inputText2);
        textrng.setText(inputText3);
        textaoe.setText(inputText4);
        textpow.setText(inputText5);
        textup.setText(inputText6);
        textoff.setText(inputText7);
        textdescript.setText(inputText8);

        spellNameArrayList.add(inputText);
        spellCostArrayList.add(inputText2);
        spellRngArrayList.add(inputText3);
        spellAoeArrayList.add(inputText4);
        spellPowArrayList.add(inputText5);
        spellUpArrayList.add(inputText6);
        spellOffArrayList.add(inputText7);
        spellDescriptionArrayList.add(inputText8);

        final AlertDialog alertDialog = new AlertDialog.Builder(CharacterMenu.this, 3).create();
        addView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog.setTitle(textOut.getText());
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remove", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ((LinearLayout)addView.getParent()).removeView(addView);
                        spellNameArrayList.remove(textOut.getText().toString());
                        spellCostArrayList.remove(textcost.getText().toString());
                        spellRngArrayList.remove(textrng.getText().toString());
                        spellAoeArrayList.remove(textaoe.getText().toString());
                        spellPowArrayList.remove(textpow.getText().toString());
                        spellUpArrayList.remove(textup.getText().toString());
                        spellOffArrayList.remove(textoff.getText().toString());
                        spellDescriptionArrayList.remove(textdescript.getText().toString());

                    }
                });
                alertDialog.show();

                return true;
            }
        });

        addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dynamicLayout.getVisibility() == View.GONE) {
                    dynamicLayout.setVisibility(View.VISIBLE);
                } else {
                    dynamicLayout.setVisibility(View.GONE);
                }

            }
        });



        container.addView(addView);
    }


}
