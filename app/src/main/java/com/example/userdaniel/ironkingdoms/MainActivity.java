package com.example.userdaniel.ironkingdoms;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.userdaniel.ironkingdoms.DrawerListFragments.AbilitiesFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.AlchemyFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.AmmunitionFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.ArchetypesFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.ArmourFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.CareersFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.ClothingFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.EquipmentFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.MechanikaFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.MeleeWeaponsFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.MountsFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.OtherFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.RacesFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.RangedWeaponsFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.SkillsFragment;
import com.example.userdaniel.ironkingdoms.DrawerListFragments.SpellsFragment;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity implements RacesFragment.OnFragmentInteractionListener,
        AbilitiesFragment.OnFragmentInteractionListener, AlchemyFragment.OnFragmentInteractionListener, AmmunitionFragment.OnFragmentInteractionListener,
        ArchetypesFragment.OnFragmentInteractionListener, ArmourFragment.OnFragmentInteractionListener, CareersFragment.OnFragmentInteractionListener,
        ClothingFragment.OnFragmentInteractionListener, EquipmentFragment.OnFragmentInteractionListener, MechanikaFragment.OnFragmentInteractionListener,
        MeleeWeaponsFragment.OnFragmentInteractionListener, MountsFragment.OnFragmentInteractionListener, OtherFragment.OnFragmentInteractionListener,
        RangedWeaponsFragment.OnFragmentInteractionListener, SkillsFragment.OnFragmentInteractionListener, SpellsFragment.OnFragmentInteractionListener {

    private Database dbHelper;
    private SimpleCursorAdapter dataAdapter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private int advanceCharacter;


    @Override
    protected void onResume() {
        super.onResume();
        startDatabaseList();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        String[] array = {"Home", "Races","Careers", "Armor", "Melee Weapons", "Ranged Weapons", "Archetypes", "Abilities", "Spells", "Skills", "Ammunition", "Clothing", "Equipment", "Mounts", "Mechanika"};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, R.layout.drawer_list_item, array);
        mDrawerList.setAdapter(mAdapter);


        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = null;
                        break;
                    case 1:
                        fragment = new RacesFragment();
                        break;
                    case 2:
                        fragment = new CareersFragment();
                        break;
                    case 3:
                        fragment = new ArmourFragment();
                        break;
                    case 4:
                        fragment = new MeleeWeaponsFragment();
                        break;
                    case 5:
                        fragment = new RangedWeaponsFragment();
                        break;
                    case 6:
                        fragment = new ArchetypesFragment();
                        break;
                    case 7:
                        fragment = new AbilitiesFragment();
                        break;
                    case 8:
                        fragment = new SpellsFragment();
                        break;
                    case 9:
                        fragment = new SkillsFragment();
                        break;
                    case 10:
                        fragment = new AmmunitionFragment();
                        break;
                    case 11:
                        fragment = new ClothingFragment();
                        break;
                    case 12:
                        fragment = new EquipmentFragment();
                        break;
                    case 13:
                        fragment = new MountsFragment();
                        break;
                    case 14:
                        fragment = new MechanikaFragment();
                        break;
                    default:
                        break;
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).addToBackStack(null).commit();
                    mDrawerLayout.closeDrawers();
                } else {
                    fragment = getFragmentManager().findFragmentById(R.id.frame_container);
                    if (fragment != null) {
                        getFragmentManager().beginTransaction().remove(fragment).commit();
                    }
                    mDrawerLayout.closeDrawers();
                }
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Database");
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        startDatabaseList();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onFragmentInteraction(String id) {

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() >= 1) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "This app is fucking perfect, you don't need to change anything. Fuck off.", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void startDatabaseList() {
        dbHelper = new Database(this);
        try {
            dbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //generate listview
        Cursor cursor = dbHelper.fetchAllCharacters();

        final String[] columns = new String[] {
                Database.key_name,
                Database.key_archetype,
                Database.key_race,
                Database.key_careers
        };
        final int[] to = new int[] {
                R.id.simplecursortext1,
                R.id.textView266,
                R.id.textView267,
                R.id.textView268


        };
        dataAdapter = new SimpleCursorAdapter(this, R.layout.list_item2, cursor, columns, to, 0);

        final ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(dataAdapter);

        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this, 3).create();
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                alertDialog.setTitle(cursor.getString(cursor.getColumnIndexOrThrow("characterName")));
                alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int characterID = cursor.getInt(cursor.getColumnIndexOrThrow(Database.KEY_ROWID));
                        dbHelper.deleteCharacter(characterID);
                        getApplication().getSharedPreferences(String.valueOf(characterID), Context.MODE_PRIVATE).edit().clear().commit();
                        dataAdapter.getCursor().requery();
                        dataAdapter.notifyDataSetChanged();

                    }

                });

                alertDialog.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                int characterID = cursor.getInt(cursor.getColumnIndexOrThrow(Database.KEY_ROWID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(Database.key_name));
                String race = cursor.getString(cursor.getColumnIndexOrThrow("characterRace"));
                String careers = cursor.getString(cursor.getColumnIndexOrThrow("characterCareers"));
                String archetype = cursor.getString(cursor.getColumnIndexOrThrow("characterArchetype"));
                Integer physique = cursor.getInt(cursor.getColumnIndexOrThrow("characterPhysique"));
                Integer speed = cursor.getInt(cursor.getColumnIndexOrThrow("characterSpeed"));
                Integer strength = cursor.getInt(cursor.getColumnIndexOrThrow("characterStrength"));
                Integer agility = cursor.getInt(cursor.getColumnIndexOrThrow("characterAgility"));
                Integer prowess = cursor.getInt(cursor.getColumnIndexOrThrow("characterProwess"));
                Integer poise = cursor.getInt(cursor.getColumnIndexOrThrow("characterPoise"));
                Integer intellect = cursor.getInt(cursor.getColumnIndexOrThrow("characterIntellect"));
                Integer arcane = cursor.getInt(cursor.getColumnIndexOrThrow("characterArcane"));
                Integer perception = cursor.getInt(cursor.getColumnIndexOrThrow("characterPerception"));
                String feat = cursor.getString(cursor.getColumnIndexOrThrow("characterFeat"));
                String armorSetName = cursor.getString(cursor.getColumnIndexOrThrow("characterArmorSetName"));
                Integer armBonus = cursor.getInt(cursor.getColumnIndexOrThrow("characterArmBonus"));
                Integer spdBonus = cursor.getInt(cursor.getColumnIndexOrThrow("characterSpdBonus"));
                Integer defBonus = cursor.getInt(cursor.getColumnIndexOrThrow("characterDefBonus"));
                Integer miscArm = cursor.getInt(cursor.getColumnIndexOrThrow("characterMiscArm"));
                Integer shieldMod = cursor.getInt(cursor.getColumnIndexOrThrow("characterShieldMod"));
                Integer racialDefMod = cursor.getInt(cursor.getColumnIndexOrThrow("characterRacialDefMod"));
                Integer initEquipmentMod = cursor.getInt(cursor.getColumnIndexOrThrow("characterInitEquipmentMod"));
                Integer miscinitMod = cursor.getInt(cursor.getColumnIndexOrThrow("characterMiscInitMod"));
                Integer commandAbilityMod = cursor.getInt(cursor.getColumnIndexOrThrow("characterCommandAbilityMod"));
                Integer commandSkillMod = cursor.getInt(cursor.getColumnIndexOrThrow("characterCommandSkillMod"));
                String meleeWepName = cursor.getString(cursor.getColumnIndexOrThrow("characterMeleeWeaponTitle"));
                String meleeMat = cursor.getString(cursor.getColumnIndexOrThrow("characterMeleeMat"));
                String meleePow = cursor.getString(cursor.getColumnIndexOrThrow("characterMeleePow"));
                String meleeSpecial = cursor.getString(cursor.getColumnIndexOrThrow("characterMeleeSpecialNotes"));
                String rangeWepName = cursor.getString(cursor.getColumnIndexOrThrow("characterRangedWeaponTitle"));
                String rangeRat = cursor.getString(cursor.getColumnIndexOrThrow("characterRangedRat"));
                String rangeRng = cursor.getString(cursor.getColumnIndexOrThrow("characterRangedRng"));
                String rangeAoe = cursor.getString(cursor.getColumnIndexOrThrow("characterRangedAoe"));
                String rangePow = cursor.getString(cursor.getColumnIndexOrThrow("characterRangedPow"));
                String rangeAmmoCurrent = cursor.getString(cursor.getColumnIndexOrThrow("characterRangedCurrentAmmo"));
                String rangeAmmoTotal = cursor.getString(cursor.getColumnIndexOrThrow("characterRangedTotalAmmo"));
                String rangeSpecial = cursor.getString(cursor.getColumnIndexOrThrow("characterRangedSpecialNotes"));
                String abilityname = cursor.getString(cursor.getColumnIndexOrThrow("characterAbilityName"));
                String abilitydescript = cursor.getString(cursor.getColumnIndexOrThrow("characterAbilityDescription"));
                String spellname = cursor.getString(cursor.getColumnIndexOrThrow("characterSpellName"));
                String spellcost = cursor.getString(cursor.getColumnIndexOrThrow("characterSpellCost"));
                String spellrng = cursor.getString(cursor.getColumnIndexOrThrow("characterSpellRng"));
                String spellaoe = cursor.getString(cursor.getColumnIndexOrThrow("characterSpellAoe"));
                String spellpow = cursor.getString(cursor.getColumnIndexOrThrow("characterSpellPow"));
                String spellup = cursor.getString(cursor.getColumnIndexOrThrow("characterSpellUp"));
                String spelloff = cursor.getString(cursor.getColumnIndexOrThrow("characterSpellOff"));
                String spelldescript = cursor.getString(cursor.getColumnIndexOrThrow("characterSpellDescription"));
                String archetypename = cursor.getString(cursor.getColumnIndexOrThrow("characterArchetypeName"));
                String archetypedescript = cursor.getString(cursor.getColumnIndexOrThrow("characterArchetypeDescription"));
                String skillname = cursor.getString(cursor.getColumnIndexOrThrow("characterSkillName"));
                String skillnum = cursor.getString(cursor.getColumnIndexOrThrow("characterSkillNum"));
                String money = cursor.getString(cursor.getColumnIndexOrThrow("characterMoney"));
                String items = cursor.getString(cursor.getColumnIndexOrThrow("characterItems"));
                String connections = cursor.getString(cursor.getColumnIndexOrThrow("characterConnections"));
                String permanentInjuries = cursor.getString(cursor.getColumnIndexOrThrow("characterPermanentInjuries"));
                String notes = cursor.getString(cursor.getColumnIndexOrThrow("characterNotes"));
                String gender = cursor.getString(cursor.getColumnIndexOrThrow("characterGender"));
                String weight = cursor.getString(cursor.getColumnIndexOrThrow("characterWeight"));
                String height = cursor.getString(cursor.getColumnIndexOrThrow("characterHeight"));
                String hair = cursor.getString(cursor.getColumnIndexOrThrow("characterHair"));
                String eyes = cursor.getString(cursor.getColumnIndexOrThrow("characterEyes"));
                String faith = cursor.getString(cursor.getColumnIndexOrThrow("characterFaith"));
                String definingCharacteristics = cursor.getString(cursor.getColumnIndexOrThrow("characterDefiningCharacteristics"));

                Intent myIntent = new Intent(MainActivity.this, CharacterMenu.class);
                myIntent.putExtra("CharacterID", characterID);
                myIntent.putExtra("name", name);
                myIntent.putExtra("race", race);
                myIntent.putExtra("careers", careers);
                myIntent.putExtra("archetype", archetype);
                myIntent.putExtra("physique", physique);
                myIntent.putExtra("speed", speed);
                myIntent.putExtra("strength", strength);
                myIntent.putExtra("agility", agility);
                myIntent.putExtra("prowess", prowess);
                myIntent.putExtra("poise", poise);
                myIntent.putExtra("intellect", intellect);
                myIntent.putExtra("arcane", arcane);
                myIntent.putExtra("perception", perception);
                myIntent.putExtra("feat", feat);
                myIntent.putExtra("armorsetname", armorSetName);
                myIntent.putExtra("armbonus", armBonus);
                myIntent.putExtra("spdbonus", spdBonus);
                myIntent.putExtra("defbonus", defBonus);
                myIntent.putExtra("miscarm", miscArm);
                myIntent.putExtra("shieldmod", shieldMod);
                myIntent.putExtra("racialdefmod", racialDefMod);
                myIntent.putExtra("initequipmentmod", initEquipmentMod);
                myIntent.putExtra("miscinitmod", miscinitMod);
                myIntent.putExtra("commandabilitymod", commandAbilityMod);
                myIntent.putExtra("commandskillmod", commandSkillMod);
                myIntent.putExtra("meleeweapontitle", meleeWepName);
                myIntent.putExtra("meleemat", meleeMat);
                myIntent.putExtra("meleepow", meleePow);
                myIntent.putExtra("meleespecialnotes", meleeSpecial);
                myIntent.putExtra("rangeweapontitle", rangeWepName);
                myIntent.putExtra("rangerat", rangeRat);
                myIntent.putExtra("rangerng", rangeRng);
                myIntent.putExtra("rangeaoe", rangeAoe);
                myIntent.putExtra("rangepow", rangePow);
                myIntent.putExtra("rangeammocurrent", rangeAmmoCurrent);
                myIntent.putExtra("rangeammototal", rangeAmmoTotal);
                myIntent.putExtra("rangespecialnotes", rangeSpecial);
                myIntent.putExtra("abilityname", abilityname);
                myIntent.putExtra("abilitydescription", abilitydescript);
                myIntent.putExtra("spellname", spellname);
                myIntent.putExtra("spellcost", spellcost);
                myIntent.putExtra("spellrng", spellrng);
                myIntent.putExtra("spellaoe", spellaoe);
                myIntent.putExtra("spellpow", spellpow);
                myIntent.putExtra("spellup", spellup);
                myIntent.putExtra("spelloff", spelloff);
                myIntent.putExtra("spelldescript", spelldescript);
                myIntent.putExtra("archetypename", archetypename);
                myIntent.putExtra("archetypedescript", archetypedescript);
                myIntent.putExtra("skillname", skillname);
                myIntent.putExtra("skillnum", skillnum);
                myIntent.putExtra("money", money);
                myIntent.putExtra("items", items);
                myIntent.putExtra("connections", connections);
                myIntent.putExtra("permanentinjuries", permanentInjuries);
                myIntent.putExtra("notes", notes);
                myIntent.putExtra("gender", gender);
                myIntent.putExtra("weight", weight);
                myIntent.putExtra("height", height);
                myIntent.putExtra("hair", hair);
                myIntent.putExtra("eyes", eyes);
                myIntent.putExtra("faith", faith);
                myIntent.putExtra("definingcharacteristics", definingCharacteristics);
                startActivity(myIntent);

            }
        });
    }

    public void startCharacter(View view) {
        Intent myIntent = new Intent(MainActivity.this, CharacterMenu.class);
        Database.DatabaseHelper mDbHelper = new Database.DatabaseHelper(getApplicationContext());
        SQLiteDatabase sqLiteDatabase = mDbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT ROWID from Characters order by ROWID DESC limit 1", null);

        int myId;
        if (cursor != null && cursor.moveToFirst()) {
            myId = cursor.getInt(0);
        } else {
            myId = 0;
        }
        myIntent.putExtra("CharacterID", myId + 1);
        myIntent.putExtra("newCharacter", 1);
        startActivity(myIntent);
        cursor.close();
        mDbHelper.close();



    }

}
