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

import com.example.userdaniel.ironkingdoms.Adapters.SpellAdapter;
import com.example.userdaniel.ironkingdoms.Models.Spell;
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
public class SpellsFragment extends Fragment {

     private OnFragmentInteractionListener mListener;
    private ArrayList<Spell> arrayList = new ArrayList<>();
    private SpellAdapter archetypeAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpellsFragment() {
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchable_list1, container, false);

        String[][] archetypes = { {"Arcane Bolt", "2", "12", "––", "11", "NO", "YES", "Magical bolts of energy streak toward the target"},
        {"Arcane Strike", "1", "8", "––", "8", "NO", "YES", "An arcane force blasts toward the target."},
        {"Arcantrik Bolt", "2", "10", "––", "12", "NO", "YES", "A steamjack damaged by this attack becomes stationary for one round."},
        {"Ashen Cloud", "2", "CTRL", "3", "––", "YES", "NO", "Place a 3\" AOE cloud effect anywhere completely in the spellcaster's control area. Characters without Immunity: Fire suffer -2 on attack rolls while within the AOE."},
        {"Ashes To Ashes", "4", "8", "*", "10", "NO", "YES", "If target character is hit he and d6 of the nearest enemies within 5\" of the target suffer a POW 10 fire damage roll."},
        {"Aura Of Protection", "2", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster's control area, friendly characters gain +2 ARM."},
        {"Awareness", "3", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster's control area, the front arcs of characters in his battlegroup are extended to 360'. When determining LOS. those characters ignore cloud effects, forests, and intervening characters. Awareness lasts for one round."},
        {"Banishing Ward", "2", "6", "––", "––", "YES", "NO", "Enemy upkeep spells on the targeted friendly character expire. The affected character cannot be targeted by enemy spells or animi."},
        {"Barrier Of Flames", "3", "SELF", "CTRL", "––", "NO", "NO", "Friendly characters in the spellcaster's control area gain +1 DEF. When a friendly character is hit by a melee attack while in the spellcaster's control area, the attacker suffers thc Fire continuous effect. Barrier of Flames lasts for one round."},
        {"Battering Ram", "2", "6", "––", "12", "NO", "YES", "When a character is hit by Battering Ram, he can be pushed 3\" directly away from the spell's point of origin"},
        {"Batten Down The Hatches", "3", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster’s control area, characters in his battlegroup cannot be knocked down and gain +3 ARM but suffer –2 DEF. Batten Down the Hatches lasts for one round."},
        {"Black Out", "4", "SELF", "CTRL", "––", "NO", "NO", "Mechanika devices in the possession of enemy characters in the spellcaster’s control area immediately deactivate. If an enemy enters the spellcaster’s control area, mechanika devices in his possession immediately deactivate. While in the spellcaster’s control area, enemy characters cannot activate mechanika devices. Black Out has no effect on steamjacks or mechanika armor. Black Out lasts for one round."},
        {"Blade Of Radiance", "2", "10", "––", "10", "NO", "YES", "Infernal and Undead characters hit by this spell suffer an additional die of damage."},
        {"Blazing Effigy", "4", "SELF", "•", "14", "NO", "NO", "Enemies within 2 ̋ of the targeted friendly character suffer a POW 14  re damage roll."},
        {"Blessing Of Health", "1", "6", "––", "––", "YES", "NO", "Target character gains +3 on PHY rolls to resist poison, disease, and infection. Additionally, if the affected character is currently suffering from the effects of a poison, he immediately makes a PHY roll against the toxin rating of the poison. If the roll succeeds, the effects of the poison immediately expire."},
        {"Blessing Of Morrow", "3", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster’s control area, friendly living characters do not suffer the effects of lost aspects."},
        {"Blessings Of War", "2", "6", "––", "––", "YES", "NO", "Target character’s weapons gains Blessed. (When making an attack with a weapon with Blessed, ignore spell effects that add to the attacked character’s ARM or DEF.)"},
        {"Blizzard", "1", "6", "––", "––", "NO", "NO", "Center a 3 ̋ AOE cloud effect on target character. The AOE remains centered on the character. If the target character is destroyed, remove the AOE from play. Blizzard lasts for one round."},
        {"Brittle Frost", "3", "8", "––", "––", "YES", "YES", "The next time target enemy suffers damage, halve its base ARM when calculating damage from the damage roll. After applying this damage, Brittle Frost expires."},
        {"Boundless Charge", "2", "6", "––", "––", "NO", "NO", "During his turn, target character can charge without spending focus or being forced and gains +2 ̋ movement and Path nder when it charges. Boundless Charge lasts for one round."},
        {"Broadside", "3", "SELF", "CTRL", "––", "NO", "NO", "The spellcaster and steamjacks under the spellcaster’s control currently in his control area can immediately make one normal ranged attack. Broadside can be cast only once per turn."},
        {"Celerity", "2", "6", "––", "––", "YES", "NO", "Target character gains one additional quick action during each of his turns."},
        {"Chain Lightning", "3", "10", "––", "10", "NO", "YES", "A character hit by Chain Lightning suffers a POW 10 electrical damage roll, and lightning arcs from that character to d6 consecutive additional characters. The lightning arcs to the nearest character it has not already arced to within 4̋ of the last model it arced to, ignoring the spellcaster. Each character the lightning arcs to suffers a POW 10 electrical damage roll."},
        {"Chiller", "2", "6", "––", "––", "YES", "NO", "While within 2 ̋ of the targeted friendly character, enemy characters suffer –2 DEF unless they have Immunity: Cold."},
        {"Cleansing Fire", "3", "8", "3", "14", "NO", "YES", "Cleansing Fire causes  re damage. On a critical hit, characters hit suffer the Fire continuous effect."},
        {"Convection", "2", "10", "––", "12", "NO", "YES", "When Convection destroys a living character, you can allocate 1 focus point to a steamjack in the spellcaster’s battlegroup that is in his control area."},
        {"Crevasse", "3", "8", "––", "12", "NO", "YES", "If Crevasse incapacitates its original target, you can make a SP 6 attack using the incapacitated character as the attack’s point of origin. Characters hit suffer a POW 12 magic damage roll."},
        {"Crusader's Call", "3", "SELF", "CTRL", "––", "NO", "NO", "Friendly characters beginning a charge while in the spellcaster’s control area gain +2 ̋ movement. Crusader’s Call lasts for one round."},
        {"Daylight", "3", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster’s control area, Infernal and Undead characters suffer –3 DEF and ARM. Additionally, the area around the spellcaster glows with enough light for anyone in his control area to see in darkness (p. 225). Daylight lasts for one round."},
        {"Deceleration", "3", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster’s control area, friendly characters gain +2 DEF and ARM against ranged attacks. Deceleration lasts for one round."},
        {"Deep Freeze", "3", "SELF", "––", "––", "NO", "NO", "Characters within 2 ̋ of the spellcaster suffer a POW 12 cold damage roll. Characters damaged by this spell cannot run, charge, or make power attacks for one round."},
        {"Earthquake", "3", "10", "5", "––", "NO", "YES", "Characters in the AOE are knocked down."},
        {"Earthsplitter", "4", "10", "3", "14", "NO", "YES", "Characters hit suffer a POW 14  re damage roll. The AOE is a cloud effect that remains in play for one round. Characters entering or ending their turn in the AOE suffer an unboostable POW 14  re damage roll."},
        {"Electrical Blast", "3", "8", "3", "13", "NO", "YES", "Electrical Blast causes electrical damage. Steamjacks damaged by Electrical Blast suffer Disruption. (A steamjack suffering Disruption loses its focus points and cannot be allocated focus or channel spells for one round)."},
        {"Electrify", "2", "6", "––", "––", "YES", "NO", "If target character is hit by a melee attack, after the attack is resolved the attacker is pushed d3 ̋ directly away from the affected character and suffers an unboostable POW 14 electrical damage roll, then Electrify expires."},
        {"Eliminator", "3", "8", "3", "13", "NO", "YES", "Immediately after this attack is resolved, the spellcaster can advance up to 2 ̋ for each enemy incapacitated by the attack."},
        {"Entangle", "1", "8", "––", "––", "NO", "YES", "Target character suffers –1 SPD and cannot run or charge for one round."},
        {"Eyes Of Truth", "2", "SELF", "––", "––", "YES", "NO", "This character’s PER rolls are boosted. Additionally, the target number for Deception rolls against this character is increased by 3."},
        {"Extinguisher", "2", "SELF", "CTRL", "––", "NO", "NO", "Fire continuous effects in the spellcaster’s control area immediately expire."},
        {"Fail Safe", "3", "6", "––", "––", "YES", "NO", "Target steamjack gains +2 ARM and does not suffer the effects of crippled systems."},
        {"Fair Winds", "1", "SELF", "––", "––", "NO", "NO", "The spellcaster gains +1 SPD this turn."},
        {"Fire Group", "2", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster’s control area, his weapons and the ranged weapons of steamjacks under his control gain +2 RNG. Fire Group lasts for one round."},
        {"Fire Starter", "1", "8", "––", "––", "NO", "•", "The spellcaster starts a small  re within the range of the spell and in line of sight. This spell can be used to target an enemy, in which case it requires an attack roll. If the enemy is hit, he suffers the Fire continuous effect."},
        {"Flames Of Wrath", "1", "6", "––", "––", "NO", "NO", "When target character incapacitates an enemy with a melee attack, enemy characters within 1 ̋ of the incapacitated character suffer the Fire continuous effect. Flames of Wrath lasts for one round."},
        {"Flare", "3", "SELF", "CTRL", "––", "NO", "NO", "Enemies in the spellcaster’s control area suffering the Fire continuous effect immediately suffer an additional unboostable POW 12  re damage roll. This spell can be cast only once per turn."},
        {"Fog Of War", "3", "SELF", "CTRL", "––", "YES", "NO", "Characters gain concealment while in the spellcaster’s control area."},
        {"Force Field", "3", "SELF", "CTRL", "––", "YES", "NO", "The spellcaster does not suffer blast or collateral damage and cannot be knocked down. When an enemy AOE ranged attack deviates from a point in the spellcaster’s control area, after the deviation distance is rolled the spellcaster’s player chooses the deviation direction."},
        {"Force Hammer", "4", "10", "––", "12", "NO", "YES", "If Force Hammer hits a non-incorporeal target, instead of suffering a normal damage roll, that target is slammed d6̋ directly away from the spell’s point of origin regardless of its base size and suffers a POW 12 damage roll. Collateral damage from this slam is POW 12."},
        {"Force Of Faith", "4", "SELF", "CTRL", "––", "NO", "NO", "Enemies currently in the spellcaster’s control area are immediately pushed d6 ̋ directly away from the spellcaster in the order he chooses."},
        {"Fortify", "2", "6", "––", "––", "YES", "NO", "Target steamjack under the spellcaster’s control gains +2 ARM. The affected steamjack and any friendly character B2B with it cannot be knocked down, pushed, or slammed."},
        {"Foxhole", "2", "CTRL", "5", "––", "YES", "NO", "Place a 5 ̋ AOE anywhere completely in the spellcaster’s control area. Characters completely in the AOE have cover and do not suffer blast damage. When drawing LOS to a character not completely within the AOE, ignore intervening characters completely within the AOE."},
        {"Freezing Grip", "4", "8", "––", "––", "NO", "YES", "Target character hit becomes stationary for one round unless the target has Immunity: Cold."},
        {"Freezing Mist", "4", "SELF", "•", "––", "NO", "NO", "While in the spellcaster’s control area, enemy characters without Immunity: Cold suffer –2 SPD and DEF. Freezing Mist lasts for one round."},
        {"Frozen Ground", "3", "SELF", "––", "––", "YES", "NO", "Enemies that move more than 2̋ and end their movement in the spellcaster’s control area are knocked down at the end of their movement. Frozen Ground lasts for one round."},
        {"Frostbite", "2", "SP8", "––", "12", "NO", "YES", "Frostbite causes cold damage."},
        {"Fuel The Flames", "3", "SELF", "CTRL", "––", "YES", "NO", "Fire continuous effects on enemies in the spellcaster’s control area never expire."},
        {"Full Throttle", "3", "SELF", "CTRL", "––", "NO", "NO", "Steamjacks under the spellcaster’s control beginning their turns in his control area can run, charge, or make slam or trample power attacks without spending focus or being driven that activation. The spellcaster and steamjacks under the spellcaster’s control in his control area gain boosted melee attack rolls. Full Throttle lasts for one turn."},
        {"Grind", "3", "10", "––", "14", "NO", "YES", "When a steamjack is hit by Grind, it suffers 1 damage point to its  rst available Movement system box."},
        {"Guided Blade", "1", "6", "––", "––", "NO", "NO", "Target friendly character gains +1 on his melee attack rolls and his melee weapons gain Magical Weapon. Guided Blade lasts for one round."},
        {"Guided Fire", "3", "SELF", "CTRL", "––", "NO", "NO", "The spellcaster and steamjacks under the spellcaster’s control in his control area gain boosted ranged attack rolls. Guided Fire lasts until for one round."},
        {"Hand Of Fate", "2", "6", "––", "––", "YES", "NO", "Target character gains an additional die on attack and damage rolls. Discard the low die in each roll."},
        {"Heal", "4", "•", "––", "––", "NO", "NO", "Target friendly incapacitated character B2B with the spellcaster is no longer incapacitated and regains 1 vitality point in each aspect. The character no longer suffers from the results of his most recent roll on the Injury Table. The target character becomes knocked down. Each time a character is targeted by this spell make a d6 roll on the Price of Healing Table below, adding +1 to the roll for each time the character has been targeted by this spell after the first time."},
        {"Heightened Reflexes", "2", "6", "––", "––", "YES", "NO", "Target character cannot be knocked down or made stationary."},
        {"Hex Blast", "3", "10", "3", "13", "NO", "YES", "Upkeep spells and animi on the character directly hit by Hex Blast immediately expire."},
        {"Hoarfrost", "3", "8", "3", "14", "NO", "YES", "Hoarfrost causes cold damage. On a critical hit, the characters hit become stationary for one round unless they have Immunity: Cold."},
        {"Howling Flames", "2", "SP8", "––", "10", "NO", "YES", "Howling Flames causes  re damage. On a critical hit, the character hit suffers the Fire continuous effect."},
        {"Hymn Of Battle", "2", "6", "––", "––", "NO", "NO", "Target steamjack gains +2 on attack and damage rolls. Hymn of Battle lasts for one round."},
        {"Hymn Of Passage", "2", "6", "––", "––", "NO", "NO", "Target steamjack cannot be targeted by non-magical ranged attacks. Hymn of Passage lasts for one round."},
        {"Hymn Of Shielding", "4", "SELF", "CTRL", "––", "NO", "NO", "While in this character’s control area, friendly characters cannot be targeted by enemy spells. Hymn of Shielding lasts for one round."},
        {"Ice Bolt", "2", "10", "––", "12", "NO", "YES", "Ice Bolt causes cold damage. On a critical hit, the character hit become stationary for one round unless he has Immunity: Cold."},
        {"Ice Shield", "1", "6", "––", "––", "YES", "NO", "Target character gains +2 ARM. Ice Shield immediately expires if the affected character moves or is damaged."},
        {"Icy Grip", "2", "8", "––", "––", "YES", "YES", "Target character without Immunity: Cold suffers –2 DEF and cannot run or make power attacks."},
        {"Ignite", "2", "6", "––", "––", "YES", "NO", "Target character gains +2 on melee attack damage rolls. The affected character gains Critical Fire on his normal melee attacks."},
        {"Immolation", "2", "8", "––", "12", "NO", "YES", "Immolation causes  re damage. On a critical hit, the character hit suffers the Fire continuous effect."},
        {"Inferno", "3", "10", "3", "12", "NO", "YES", "All characters hit suffer a POW 12  re damage roll. The AOE remains in play for one round. Characters entering or ending their turns in the AOE suffer an unboostable POW 12  re damage roll."},
        {"Influence", "1", "10", "––", "––", "NO", "YES", "The spellcaster makes a contested Willpower roll against target living enemy hit by this spell. If the spellcaster loses, nothing happens. If the spellcaster wins, his player takes control of the character hit. The character immediately makes one normal melee attack, then In uence expires."},
        {"Inhospitable Ground", "3", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster’s control area, other characters treat open terrain as rough terrain. Inhospitable Ground lasts for one round."},
        {"Iron Aggression", "3", "6", "––", "––", "YES", "NO", "Target steamjack can run, charge, or make slam or trample power attacks without spending focus or being driven and gains boosted melee attack rolls."},
        {"Jackhammer", "1", "6", "––", "––", "NO", "NO", "The targeted friendly steamjack can immediately make one melee attack."},
        {"Jump Start", "1", "SELF", "CTRL", "––", "NO", "NO", "The spellcaster and steamjacks under the spellcaster’s control in his control area can immediately turn to face any direction. Affected steamjacks that are stationary or knocked down are no longer stationary and stand up."},
        {"Lamentation", "3", "SELF", "CTRL", "––", "YES", "NO", "While in this character’s control area, enemies pay double the fatigue, focus, or fury point cost to cast or upkeep spells."},
        {"Light In The Darkness", "1", "SELF", "CTRL", "––", "YES", "NO", "The area around the spellcaster glows with enough light for anyone in his control area to see in darkness."},
        {"Lightning Tendrils", "3", "6", "––", "––", "YES", "NO", "The targeted friendly character gains Immunity: Electricity, and the character’s melee weapons gain Reach and Electro Leap. (When a character is hit by a weapon with Electro Leap, you can have lightning arc to the nearest character within 4 ̋ of the character hit, ignoring the attacker. The character the lightning arcs to suffers an unboostable POW 10 electrical damage roll.)"},
        {"Locomotion", "1+", "6", "––", "––", "NO", "NO", "The spellcaster spends up to 3 focus points to cast Locomotion. Target steamjack immediately advances up to 1 ̋ for each focus point spent. A steamjack can be targeted by Locomotion only once per round."},
        {"Mirage", "3", "6", "––", "––", "YES", "NO", "During the spellcaster’s Control Phase after upkeep has been paid, the targeted friendly character’s controller can place him anywhere completely within 2 ̋ of his current location."},
        {"Obliteration", "4", "10", "4", "15", "NO", "YES", "The force of this attack blasts apart the earth itself."},
        {"Occultation", "2", "6", "––", "––", "YES", "NO", "Target character gains stealth and +3 on his Sneak rolls."},
        {"Overmind", "4", "SELF", "CTRL", "––", "NO", "NO", "The spellcaster immediately makes a contested Willpower roll against all living enemies in his control area. Roll once for the spellcaster. If the spellcaster beats an enemy’s Willpower roll, he can cause that character to advance up to 3 ̋ and perform one non-spell, non-feat quick action. If the enemy beats or ties the spellcaster’s roll, he is not affected. This spell can be cast only once per round."},
        {"Polarity Shield", "2", "6", "––", "––", "YES", "NO", "Target character cannot be targeted by a charge made by a character in his front arc."},
        {"Positive Charge", "2", "6", "––", "––", "NO", "NO", "descrTarget steamjack gains +2 on melee attack and melee damage rolls. While within 3 ̋ of the affected steamjack, friendly characters gain +2 on melee attack and melee damage rolls. Positive Charge lasts for one round.ipt"},
        {"Power Booster", "1", "5", "––", "––", "NO", "NO", "If the target steamjack the spellcaster controls has no focus points, it gains 1 focus point. If the steamjack is Disrupted, it is no longer Disrupted."},
        {"Prayer Of Guidance", "3", "6", "––", "––", "NO", "NO", "Target character gains two additional dice on his next skill roll. Discard the lowest two dice in the roll. Prayer of Guidance can be cast only once per day."},
        {"Protection From Cold", "1", "6", "––", "––", "YES", "NO", "Target character gains Immunity: Cold."},
        {"Protection From Corrosion", "1", "6", "––", "––", "YES", "NO", "Target character gains Immunity: Corrosion."},
        {"Protection From Electricty", "1", "6", "––", "––", "YES", "NO", "Target character gains Immunity: Electricity and cannot be disrupted."},
        {"Protection From Fire", "1", "6", "––", "––", "YES", "NO", "Target character gains Immunity: Fire."},
        {"Purification", "3", "SELF", "CTRL", "––", "NO", "NO", "Continuous effects, animi, and upkeep spells in the spellcaster’s control area immediately expire."},
        {"Raging Winds", "4", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster’s control area, enemies suffer –2 DEF. Enemies beginning their turn in the spellcaster’s control area cannot run or charge. Raging Winds lasts for one round."},
        {"Razor Wind", "2", "10", "––", "12", "NO", "YES", "A blade of wind slices through the target."},
        {"Redline", "2", "6", "––", "––", "YES", "NO", "The targeted friendly steamjack gains +2 STR and SPD and can run, charge, or make power attack slams or tramples without spending focus or being driven. When the steamjack ends its turn, it suffers d3 damage points."},
        {"Refuge", "2", "6", "––", "––", "YES", "NO", "When target character directly hits another character with an attack during his turn, immediately after his turn ends the character affected by this spell can make a full advance. The character cannot be targeted by free strikes during this movement."},
        {"Return Fire", "1", "6", "––", "––", "NO", "NO", "When target character is targeted by an enemy ranged attack, after the attack is resolved the affected character can make one normal melee or ranged attack, then Return Fire expires. Return Fire lasts for one round."},
        {"Rift", "3", "8", "4", "13", "NO", "YES", "The AOE is rough terrain and remains in play for one round."},
        {"Righteous Flames", "2", "6", "––", "––", "NO", "NO", "Target character gains Immunity: Fire. When a character without Immunity: Fire ends his turn within 2̋ of the affected character, the character without Immunity: Fire suffers the Fire continuous effect. Righteous Flames lasts for one round."},
        {"Rime", "2", "6", "––", "––", "NO", "NO", "Target character gains Immunity: Cold. When a character without Immunity: Cold ends his turn within 2̋ of the affected character, the character without Immunity: Cold becomes stationary until the end of his next turn. Rime lasts for one round."},
        {"Rock Hammer", "3", "10", "3", "14", "NO", "YES", "On a critical hit, characters hit are knocked down."},
        {"Rock Wall", "2", "CTRL", "WALL", "––", "YES", "NO", "Place a wall template (p. 352) anywhere completely in the spellcaster’s control area where it does not touch a character’s base, an obstruction, or an obstacle. The wall is a linear obstacle that provides cover."},
        {"Rune Shot: Accuracy", "1", "SELF", "––", "––", "NO", "NO", "The spellcaster’s next rune shot ranged attack roll this turn is boosted."},
        {"Rune Shot: Black Penny", "1", "SELF", "––", "––", "NO", "NO", "The spellcaster’s next rune shot ranged attack roll this turn ignores the  ring into melee penalty."},
        {"Rune Shot: Brutal", "1", "SELF", "––", "––", "NO", "NO", "The spellcaster’s next rune shot ranged attack gains a boosted ranged attack damage roll against the target directly hit."},
        {"Rune Shot: Detonator", "2", "SELF", "•", "––", "NO", "NO", "If the spellcaster directly hits a target with its next rune shot ranged attack this turn, center a 4̋ AOE on the target. Characters other than the original target within the AOE suffer an unboostable damage roll with a POW equal to the POW of the ranged weapon."},
        {"Rune Shot: Earth Shaker", "3", "SELF", "•", "––", "NO", "NO", "If this character directly hits a target with its next rune shot ranged attack this turn, the attack becomes AOE 5 and POW 0. Characters hit by the AOE suffer no damage but are knocked down."},
        {"Rune Shot: Fire Beacon", "2", "SELF", "•", "––", "NO", "NO", "The spellcaster’s next rune shot ranged attack this turn becomes AOE 5 and POW —–. While a character is within the AOE, he loses Camou age and stealth, and other characters can ignore cloud effects when determining LOS to him. The AOE lasts for one round."},
        {"Rune Shot: Freeze Fire", "4", "SELF", "––", "––", "NO", "NO", "If the spellcaster’s next rune shot ranged attack this turn hits, the target directly hit becomes stationary for one round."},
        {"Rune Shot: Heart Stopper", "4", "SELF", "––", "––", "NO", "NO", "Damage exceeding the ARM of the character hit by spellcaster’s next rune shot ranged attack this turn is doubled. A character disabled by this attack cannot make a Tough roll."},
        {"Rune Shot: Iron Rot", "1", "SELF", "––", "––", "NO", "NO", "If the spellcaster’s next rune shot ranged attack this turn directly hits a steamjack, in addition to any other damage and effects from the attack, the steamjack also suffers d3 damage points."},
        {"Rune Shot: Molten Shot", "1", "SELF", "––", "––", "NO", "NO", "If the spellcaster’s next rune shot ranged attack this turn hits, the target directly hit suffers the Fire continuous effect."},
        {"Rune Shot: Momentum", "4", "SELF", "––", "––", "NO", "NO", "If the spellcaster hits with his next rune shot ranged attack this turn, the character directly hit is slammed d6 ̋ directly away from the spellcaster regardless of his base size and suffers a damage roll with a POW equal to the ranged weapon. Collateral damage from this slam is equal to the POW of the ranged weapon."},
        {"Rune Shot: Phantom Seeker", "3", "SELF", "––", "––", "NO", "NO", "The spellcaster’s next rune shot ranged attack this turn ignores LOS when making ranged attacks. The attack also ignores concealment and cover."},
        {"Rune Shot: Shadow Fire", "2", "SELF", "––", "––", "NO", "NO", "If the spellcaster hits a target with his next rune shot ranged attack this turn, friendly characters can ignore the target when determining LOS and making ranged or magic attacks for one round."},
        {"Rune Shot: Silencer", "1", "SELF", "––", "––", "NO", "NO", "The spellcaster’s next rune shot ranged attack is completely silent, and gives no sign of being  red. Neither the  ring of the weapon, nor the impact of its ammunition causes a sound. Any immediate sound from a target that is hit, such as a scream, shout, or the fall of a body, is silenced."},
        {"Rune Shot: Spell Cracker", "3", "SELF", "––", "––", "NO", "NO", "If the spellcaster directly hits a target with his next rune shot ranged attack this turn, upkeep spells and animi on the target hit immediately expire."},
        {"Rune Shot: Spontaneous Combustion", "1", "SELF", "•", "––", "NO", "NO", "If the spellcaster destroys a living character with his next rune shot ranged attack, center a 3 ̋ AOE cloud effect on the destroyed character, then remove the destroyed character from the table. The AOE remains in play for one round."},
        {"Rune Shot: Thunderbolt", "1", "SELF", "––", "––", "NO", "NO", "If the spellcaster directly hits a target with his next rune shot ranged attack this turn, the target is pushed d3 ̋ directly away from this character. On a critical hit, the target is knocked down after being pushed."},
        {"Rune Shot: Trick Shot", "2", "SELF", "––", "––", "NO", "NO", "If the spellcaster directly hits a target with its rune shot next ranged attack this turn, choose a character within 4 ̋ of the target that was hit. After the attack is resolved, the spellcaster immediately makes a ranged attack roll against the chosen character. If the chosen character is hit, it suffers a magical damage roll with a POW equal to that of his ranged weapon but does not suffer any effects of other Rune Shot spells cast on the original attack. The point of origin for this damage is the character originally hit."},
        {"Sanguine Blessing", "3", "SELF", "CTRL", "––", "YES", "NO", "When a friendly character in the spellcaster’s control area would suffer a damage roll, the spellcaster can suffer the damage roll instead. Decide whether the spellcaster suffers the damage before the roll is made."},
        {"Sea Of Fire", "4", "SELF", "•", "––", "NO", "NO", "Enemy characters without Immunity: Fire within 5̋ of the spellcaster suffer the Fire continuous effect."},
        {"Shatter Storm", "2", "6", "––", "––", "YES", "NO", "When target character directly hits and destroys an enemy with a ranged or melee attack, center a 3̋ AOE on the destroyed character, then remove that character from the table. Characters in the AOE are hit and suffer an unboostable POW 8 blast damage roll."},
        {"Shield Of Faith", "2", "6", "––", "––", "YES", "NO", "Target character gains +2 ARM against magic attacks and attacks made by Infernal or Undead characters."},
        {"Shock Wave", "4", "SELF", "•", "13", "NO", "NO", "Characters with 5̋ of the spellcaster suffer a POW 13 damage roll. Each enemy damaged by Shock Wave is pushed d6 ̋ directly away the spellcaster in the order you choose."},
        {"Short Out", "1", "8", "––", "––", "NO", "YES", "Mechanika devices in the possession of target character hit immediately deactivate. Short Out has no effect on steamjacks or mechanika armor."},
        {"Snipe", "2", "6", "––", "––", "YES", "NO", "Target character’s ranged weapons gain +4 RNG."},
        {"Solid Ground", "2", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster’s control area, friendly characters cannot be knocked down and do not suffer blast damage."},
        {"Solovin's Boon", "1", "SELF", "––", "––", "YES", "NO", "The spellcaster can reroll failed Medicine skill rolls. Each failed roll can be rerolled only once as a result of Solovin’s Boon."},
        {"Star Fire", "4", "SELF", "CTRL", "––", "NO", "NO", "Enemies that move and end their movement closer to the spellcaster than they began suffer an unboostable POW 12 damage roll. Star Fire lasts for one round."},
        {"Staying Winter's Hand", "2", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster’s control area, friendly characters gain +2 ARM against cold damage. Additionally, while affected by this spell, characters never suffer the effects of exposure to cold weather and are kept warm."},
        {"Stone Stance", "1", "6", "––", "––", "NO", "NO", "Target character cannot be knocked down, pushed, or slammed for one round."},
        {"Stone Strength", "2", "6", "––", "––", "YES", "NO", "Target character gains +1 STR and ARM."},
        {"Storm Tossed", "1", "8", "––", "––", "NO", "YES", "When an enemy character is hit by Storm Tossed, he is pushed 3̋ directly away from the spell’s point of origin."},
        {"Sunburst", "3", "10", "3", "13", "NO", "YES", "Blast damage from this spell only affects enemies."},
        {"Superiority", "3", "6", "––", "––", "YES", "NO", "The targeted friendly steamjack gains +2 SPD, MAT, and DEF and cannot be knocked down."},
        {"Telekinesis", "2", "8", "––", "––", "NO", "•", "Place target character completely within 2 ̋ of its current location. When Telekinesis targets an enemy character, it is an offensive spell and requires a magic attack roll. A character can be affected by Telekinesis only once per round."},
        {"Temper Metal", "2", "6", "––", "––", "YES", "NO", "The targeted friendly steamjack gains +2 ARM and is immune to continuous effects."},
        {"Tempest", "4", "8", "4", "12", "NO", "YES", "Characters hit by Tempest are knocked down and suffer a POW 12 damage roll."},
        {"Tide Of Steel", "4", "SELF", "CTRL", "––", "NO", "NO", "The spellcaster and steamjacks under his control currently in his control area can immediately advance up to 3̋."},
        {"Tornado", "4", "10", "––", "13", "NO", "YES", "Instead of suffering a normal damage roll, a non-incorporeal character hit by Tornado is thrown d6 ̋ directly away from the spell’s point of origin regardless of its base size and suffers a POW 13 damage roll. Collateral damage from this throw is POW 13."},
        {"Transference", "2", "SELF", "CTRL", "––", "YES", "NO", "The spellcaster can allow other friendly living characters in his control area to spend focus points on him to boost melee attack or melee damage rolls during their turns at a rate of 1 focus point per boost."},
        {"Triage", "2", "B2B", "––", "––", "NO", "NO", "The spellcaster must be B2B with an incapacitated character who needs to be stabilized to cast this spell. When this spell is cast the incapacitated character is immediately stabilized."},
        {"True Path", "3", "SELF", "CTRL", "––", "NO", "NO", "Friendly characters beginning their turns in the spellcaster’s control area gain +2 ̋ movement and Path nder during their turns. True Path lasts for one round."},
        {"True Sight", "2", "SELF", "––", "––", "YES", "NO", "This character ignores concealment, Camou age, and stealth. The character can also see in complete darkness."},
        {"Vision", "2", "6", "––", "––", "YES", "NO", "The next time target character is directly hit by an attack, he suffers no damage roll from the attack, then Vision expires."},
        {"Voltaic Lock", "4", "10", "•", "––", "NO", "YES", "Target steamjack hit cannot advance and suffers –4 DEF. A steamjack beginning an advance within 3 ̋ of the steamjack hit cannot run or charge and can only advance directly toward it. Voltaic Lock lasts for one round."},
        {"Wall Of Fire", "2", "CTRL", "WALL", "––", "YES", "NO", "Place the wall template anywhere completely in the spellcaster’s control area where it does not touch a character’s base, an obstruction, or an obstacle. When a character enters or ends his turn in the wall area, he suffers an unboostable POW 12 fire damage roll and the Fire continuous effect. Characters within the wall template gain concealment."},
        {"White Out", "4", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster’s control area, enemies have their LOS reduced to 5 ̋."},
        {"Wind Blast", "2", "CTRL", "5", "––", "NO", "NO", "Place a 5 ̋ AOE anywhere completely in the spellcaster’s control area. Cloud effects overlapping the AOE expire. Characters suffer –3 RAT while within the AOE. The AOE remains in play for one round."},
        {"Wind Strike", "1", "6", "––", "––", "NO", "YES", "This spell does not inflict damage. An enemy hit by this spell can be pushed 1 ̋ directly away from the spellcaster. After the enemy is pushed, the spellcaster can advance up to 1̋ toward the pushed enemy."},
        {"Wings Of Air", "2", "SELF", "––", "––", "NO", "NO", "Place the spellcaster anywhere completely within 5 ̋ of his current location. Wings of Air can be cast only once per turn."},
        {"Winter Storm", "3", "SELF", "CTRL", "––", "NO", "NO", "Enemies that begin their turns in the spellcaster’s control range lose Eyeless Sight, Flight, and Path nder during their turns. Winter Storm lasts for one round."},
        {"Zephyr", "3", "6", "––", "––", "NO", "NO", "Target character can immediately advance up to 5̋. A character can be affected by Zephyr only once per round."},
        {"Arcane Blast", "3", "10", "3", "13", "NO", "YES", "A magical energy blast radiates from a single point to strike all characters in the AOE."},
        {"Blur", "2", "6", "––", "––", "YES", "NO", "Target character gains +3 DEF against ranged and magic attack rolls."},
        {"Deadeye", "2", "6", "––", "––", "NO", "NO", "Target character gains an additional die on his first ranged attack roll this turn."},
        {"Disruptor", "2", "10", "––", "––", "NO", "YES", "Target steamjack hit loses its focus points and cannot be allocated focus or channel spells for one round."},
        {"Exorcism", "2", "SELF", "CTRL", "––", "YES", "NO", "Characters in the spellcaster’s control area lose Incorporeal."},
        {"Lightning Shroud", "2", "6", "––", "––", "YES", "NO", "Target warjack in this character’s battlegroup gains +2 STR and its melee weapons gain Electro Leap. (When a character is hit by a weapon with Electro Leap, you can choose to have lightning arc to the nearest character within 4  of the character hit, ignoring the attacking character. The character the lightning arcs to suffers an unboostable POW 10 electrical damage roll.)"},
        {"Rune Shot: Disruption", "2", "SELF", "––", "––", "NO", "NO", "If the spellcaster’s next rune shot ranged attack this turn hits a steamjack, the steamjack suffers Disruption. (A steamjack suffering Disruption loses its focus points and cannot be allocated focus or channel spells for one round)."},
        {"Battle Rage", "3", "6", "––", "––", "NO", "NO", "Target friendly living character gains +2 to his melee attack rolls and becomes Fearless. Battle Rage lasts for one round."},
        {"Gallows", "3", "10", "––", "13", "NO", "YES", "When an enemy is hit by this attack, he can be pushed d\"6 directly toward Gallows’ point of origin."},
        {"Infernal Machine", "2", "6", "––", "––", "YES", "NO", "Target steamjack in the spellcaster’s battlegroup gains Terror [14] against enemy characters and +2 MAT and SPD."},
        {"Iron Flesh", "2", "6", "––", "––", "YES", "NO", "Target friendly living character gains +3 DEF but suffers –1 SPD."},
        {"Ravager", "2", "6", "––", "––", "NO", "NO", "Target steamjack in the spellcaster’s battlegroup gains Berserk for one turn. While affected by Ravager, a steamjack cannot make Chain Attacks. (When a steamjack with Berserk incapacitates or destroys one or more characters with a melee attack during its turn, immediately after the attack is resolved it must make one additional melee attack against another character in its melee range.)"},
        {"Watcher", "3", "SELF", "––", "––", "YES", "NO", "When an enemy character advances and ends its movement within 6\" of the spellcaster, choose a steamjack in this character's battlegroup that is in his control area. That steamjack can immediately make a full advance and then can make one normal melee or ranged attack targeting the enemy character. The attack and damage rolls against that character are boosted. After the attack is resolved, Watcher expires."},
        {"Admonition", "2", "6", "––", "––", "YES", "NO", "When an enemy advances and ends its movement within 6  of target character in spellcaster’s battlegroup, the affected character can immediately advance up to 3 , then Admonition expires. The affected character cannot be targeted by free strikes during this movement."},
        {"Quickened", "4", "SELF", "––", "––", "YES", "NO", "The spellcaster can make one attack or quick action at the start                                                       "},
        {"Rune Shot: Piercer", "2", "SELF", "––", "––", "NO", "NO", "The spellcaster’s next rune shot ranged attack this turn ignores spell effects that add to the target’s ARM or DEF."},
        {"Twister", "2", "10", "3", "10", "NO", "YES", "The AOE is a cloud effect that remains in play for one round."},
        {"Bow The Man Down", "3", "8", "––", "15", "NO", "YES", "A character hit by Blow the Man Down is knocked down. On a critical hit, instead of suffering a normal damage roll, a non- incorporeal character is slammed d6  directly away from the spell’s point of origin regardless of his base size. Collateral damage from this slam is POW 15. Incorporeal characters are not slammed; they are just knocked down and suffer a damage roll."},
        {"Bullet Dodger", "2", "6", "––", "––", "YES", "NO", "Target friendly character gains +2 DEF against ranged attack rolls and Dodger. (A character with Dodger can advance up to 2  immediately after an enemy attack that missed him is resolved unless he was missed while advancing. He cannot be targeted by free strikes during this movement.)"},
        {"Buoyancy", "1", "6", "––", "––", "YES", "NO", "Target friendly character gains +5 on Swimming skill rolls."},
        {"Deadweight", "2", "8", "––", "12", "NO", "YES", "When Deadweight destroys an enemy living or undead character, choose a character within 2  of the destroyed character. The chosen character must forfeit either his movement or his action during his next turn."},
        {"Fortune", "2", "6", "––", "––", "YES", "NO", "Target friendly character can reroll his missed attack rolls. Each attack roll can be rerolled only once as a result of Fortune."},
        {"Hot Shot", "2", "6", "––", "––", "YES", "NO", "Target friendly character in the spellcaster’s battlegroup gains boosted ranged attack damage rolls."},
        {"Phantasm", "2", "6", "––", "––", "YES", "NO", "When friendly target character is targeted by a ranged attack, the Effective Range of the attack is reduced by 30 feet (5 )."},
        {"Powder Keg", "4", "10", "5", "14", "NO", "YES", "On a critical hit, characters hit lose one attack during their next activation."},
        {"Sea Legs", "2", "6", "––", "––", "YES", "NO", "Target friendly character cannot be knocked down."},
        {"Ashen Veil", "2", "6", "––", "––", "YES", "NO", "Target friendly character gains concealment. Living enemies suffer –2 to attack rolls while within 2  of an affected character."},
        {"Blaze Of Glory", "2", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster’s control area, the weapons of characters in his battlegroup become magical weapons."},
        {"Chasten", "2", "8", "––", "12", "NO", "YES", "Enemy upkeep spells and animi on a character damaged by Chasten expire."},
        {"Cloak Of Fear", "2", "SELF", "––", "––", "YES", "NO", "The spellcaster gains Terror [Willpower +2]."},
        {"Devil's Tongue", "2", "SELF", "––", "––", "YES", "NO", "The spellcaster gains boosted Deception rolls."},
        {"Eye Of Menoth", "3", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster’s control area, friendly Menite characters and steamjacks controlled by friendly Menite characters gain +1 to attack and damage rolls."},
        {"Hallowed Avenger", "2", "6", "––", "––", "YES", "NO", "When an enemy attack incapacitates or destroys one or more friendly characters within 5  of target steamjack in the spellcaster’s battlegroup, after the attack is resolved the affected steamjack can charge an enemy character. Hallowed Avenger then expires."},
        {"Hex Hammer", "3", "SELF", "CTRL", "––", "YES", "NO", "When an enemy character casts a spell or uses an animus while in the spellcaster’s control area, after the spell or animus has been cast or used the enemy character suffers d3 damage points."},
        {"Holy Ward", "2", "6", "––", "––", "YES", "NO", "Target friendly character gains +2 DEF and cannot be targeted by enemy spells or animi."},
        {"Mindlock", "4", "8", "––", "––", "YES", "YES", "Each time a character affected by this spell attempts to cast a spell, he must make a contested Willpower roll against the spellcaster. If the affected character wins, he can cast his spell normally. If the spellcaster wins, the affected character cannot cast the spell and loses the quick action he would have used to cast the spell."},
        {"Perdition", "2", "10", "––", "10", "NO", "YES", "When Perdition damages an enemy, immediately after the attack is resolved one steamjack in the spellcaster’s battlegroup that is currently in his control area can make a full advance toward the nearest enemy. A steamjack can advance as a result of Perdition only once per turn."},
        {"Protector's Mark", "3", "8", "––", "––", "YES", "YES", "When the spellcaster is directly hit by an enemy attack, if the character affected by this spell is within 3  of him, the spellcaster can cause the affected character to be directly hit instead. That character is automatically hit and suffers all damage and effects from the attack, then this spell expires."},
        {"Synergy", "2", "10", "––", "10", "NO", "YES", "While in the spellcaster’s control area, characters in the spellcaster’s battlegroup gain a +1 cumulative bonus to melee attack and melee damage rolls for each other character in the battlegroup that hit an enemy character with a melee attack this turn while in the spellcaster’s control area."},
        {"Affliction", "3", "8", "––", "––", "YES", "YES", "When a damage roll resulting from a direct hit fails to exceed the ARM of the affected character, that character suffers d3 damage points."},
        {"Annihilation", "4", "10", "3", "10", "NO", "YES", "Characters hit suffer a POW 10 damage roll. If the spellcaster can gain soul tokens, he can claim the soul tokens of characters destroyed by this spell regardless of range."},
        {"Bleed", "2", "8", "––", "10", "NO", "YES", "When this spell damages a living character, the spellcaster regains d3 vitality points."},
        {"Bone Shaker", "2", "8", "––", "12", "NO", "YES", "When this spell destroys living or undead character, the spellcaster can immediately make a full advance toward the destroyed character followed by a normal melee attack. The destroyed character is then removed from the table. The destroyed character cannot be targeted by free strikes during this movement."},
        {"Dark Fire", "2", "10", "––", "12", "YES", "NO", "If the spellcaster can gain soul tokens, he can claim the soul tokens of characters destroyed by this spell regardless of range."},
        {"Death Field", "4", "SELF", "•", "––", "NO", "NO", "The spellcaster gains Dark Shroud. While within eighteen feet (3\") of the spellcaster, friendly characters also gain Dark Shroud. (While in the melee range of a character with Dark Shroud, enemy characters suffer –2 ARM.) Death Field lasts for one turn."},
        {"Dominate Undead", "3", "10", "––", "––", "YES", "NO", "The spellcaster makes a contested Willpower roll against a target undead enemy hit by this spell. If the spellcaster loses, nothing happens. If the spellcaster wins, his player takes control of the character hit. The spellcaster immediately makes a full advance with the undead character followed by a normal melee attack, then Dominate Undead expires."},
        {"Entropic Force", "3", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster’s control area, other characters lose Tough and cannot regain vitality."},
        {"Ghost Shroud", "3", "SELF", "––", "––", "YES", "NO", "The spellcaster gains +2 DEF and Poltergeist. (When an enemy misses a character with Poltergeist with an attack, immediately after the attack is resolved the spellcaster can choose to push the enemy d3\" directly away him."},
        {"Grave Whispers", "1", "SELF", "––", "––", "YES", "NO", "The spellcaster can speak to the dead. When he casts the spell he must be touching the skull or head of the deceased individual he wishes to contact. If the skull has been destroyed, no contact is possible. Likewise, if the deceased individual’s soul has been destroyed or is otherwise inaccessible, no contact is possible." +
                "\n\nCasting this spell awakens the spirit of the deceased to talk to the spellcaster. The spirit is not bound to speak and might simply ignore the spellcaster, especially if it has been dead for some time. If the spirit replies, it speaks in a chilling, displaced whisper audible to all in the spellcaster’s vicinity. Even if the spirit speaks, the spellcaster understands the spirit only if he knows the language it is speaking." +
                "\n\nFor Game Masters, this spell can be an incredibly powerful tool for storytelling, but it can also become a hindrance. If its use would foil the plot of a story, you can always choose to have the spirit act uncooperatively or incoherently. Death, especially violent death, can mar a spirit’s psyche with madness, dementia, rage, mindless terror, or amnesia. The spirit might also choose to lie for its own reasons."},
        {"Hellfire", "3", "10", "––", "14", "NO", "YES", "A character hit by Hellfire must make a Willpower roll against Terror with a target number equal to the spellcaster’s Willpower +2."},
        {"Soul Mark", "1", "8", "––", "––", "YES", "YES", "The spellcaster can automatically sense the presence and direction of the target living character while the living character is within fifty feet for each point of his ARC stat. Additionally, if the spellcaster can gain soul tokens and the living character is destroyed, the spellcaster can claim the soul token of the destroyed character regardless of range or the proximity of other characters."},
        {"Star Crossed", "3", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster’s control area, enemies gain an additional die on attack rolls. Discard the highest die of each roll. Star-Crossed lasts for one round."},
        {"Stygian Abyss", "3", "10", "––", "12", "NO", "YES", "On a critical hit, the character hit suffers Shadow Bind for one round. (A character suffering Shadow Bind suffers –3 DEF, and for one round when he advances he cannot move except to change facing.)"},
        {"Telgesh Mark", "2", "6", "––", "––", "YES", "NO", "The spellcaster can channel spells through the affected friendly character while the friendly character is in his control area."},
        {"Backlash", "3", "8", "––", "12", "YES", "YES", "When target enemy warjack that is part of a battlegroup is damaged, its controller suffers 1 damage point."},
        {"Dissolution Bolt", "2", "8", "––", "12", "NO", "YES", "A character hit by Dissolution Bolt cannot channel spells for one round."},
        {"Domination", "3", "8", "––", "––", "NO", "YES", "Take control of target enemy warjack that has a functional cortex. You can make a full advance with the warjack and can then make one normal attack, then Domination expires. Domination can be cast only once per turn.\n\nIf the target warjack hit by this spell is bonded, the spellcaster makes a contested Willpower roll against its controller. If the spellcaster loses, Domination does not take effect. If the spellcaster succeeds, Domination takes effect normally."},
        {"Electrodynamics", "4", "SELF", "CTRL", "––", "YES", "NO", "While in the spellcaster's control area, friendly characters gain +2 to their electrical damage rolls and friendly construct characters' melee attacks inflict an additional d3 points of electrical damage."},
        {"Electrical Charge", "1", "SELF", "––", "––", "NO", "NO", "The spellcaster's next melee attack this turn causes 1 additional point of electrical damage. Steamjacks damaged by the spellcaster's next melee attack this turn suffer Disruption. (A steamjack suffering Disruption loses its focus points and cannot be allocated focus or channel spells for one round)."},
        {"Magnetic Hold", "2", "8", "––", "––", "YES", "YES", "Target character suffers -2 SPD and DEF. Friendly Construct characters charing an affected model gains +2\"."},
        {"Razor Wall", "2", "CTRL", "WALL", "––", "YES", "NO", "Place the wall template anywhere completely in the spellcaster's control area where it does not touch a character's base, an obstruction, or an obstacle. When a character enters or ends its activation in the wall area, he suffers d3 damage points."},
        {"Realignment", "4", "SELF", "CTRL", "––", "NO", "NO", "While in the spellcaster's control area, worshippers of Cyriss can reroll their failed attack and skill rolls. Each roll can be rerolled only once as a result of Realignment. Realignment lasts for one round. Realignment can be cast only once per encounter."},
        {"Reconstruction", "4", "SELF", "CTRL", "––", "NO", "NO", "Remove d6 damage point from each construct character in the spellcaster's control area currently in his control range."},
        {"Shrapnel Swarm", "3", "8", "•", "13", "NO", "YES", "On a direct hit against an enemy, center a 4\" AOE on the character hit. After resolving the damage roll, characters in the AOE are hit and suffer 1 point of damage."},
        {"Total Spectrum", "3", "SELF", "––", "––", "YES", "NO", "The spellcaster treats all light conditions as bright light."},
        {"Explosivo","1","6","––","––","NO","NO","Target character’s next normal attack with a non-spray ranged weapon becomes magical. If the weapon was AOE – it becomes AOE 3. Characters in the AOE other than the character that was directly hit suffer blast damage but do not suffer any other effects of the attack. Explosivo expires immediately after resolving the affected character’s next ranged attack. Explosivo lasts for one round."},
        {"Fire For Effect","3","6","––","––","YES","NO","Boost the attack and damage rolls of target character’s ranged attack during each of his turns."},
        {"Ground Zero","3","SELF","5","13","NO","NO","Center a 5 ̋ AOE on the spellcaster. Each character other than the spellcaster in the AOE is hit and suffers a POW 13 damage roll. Characters damaged by Ground Zero are pushed d6 ̋ directly away from the spellcaster in the order his controller chooses."},
        {"Molten Metal","2","10","––","––","NO","YES","Target steamjack suffers 1 point of  re damage to each column on its damage grid."},
        {"Stranglehold","2","10","––","11","NO","YES","A character damaged by Stranglehold forfeits either its movement or its action during its next turn."},
        {"Stone Spray","3","SP 8","––","12","NO","YES","On a critical hit, the character hit is knocked down."},
        {"Unstoppable Force","2","SELF","CTRL","––","NO","NO","While in the spellcaster’s control area, characters in his battlegroup gain Bulldoze. Unstoppable Force lasts for one turn. (When a character with Bulldoze advances into B2B contact with an enemy during its activation, it can push that character up to 2 ̋ directly away from it. A character can be pushed by Bulldoze only once per turn. Bulldoze has no effect if the affected character makes a trample power attack.)"},
        {"Blight Bringer","4","10","5","12","NO","NO","Center a 5̋ AOE on target friendly warbeast. Enemies in the AOE when it is put in play are hit and suffer a POW 12 corrosion damage roll. An enemy character or friendly non-blighted character entering or ending its activation in the AOE suffers d3 points of corrosion damage. Blight Bringer lasts for one round."},
        {"Blight Field","2","CTRL","4","––","NO","NO","Place a 4̋ AOE anywhere completely in the spellcaster’s control area. While in the AOE, enemies cannot be used to channel spells, be forced, or be allocated focus. The AOE remains in play for one round. Blight Field can be cast once per round."},
        {"Blood Rain","3","8","3","12","NO","YES","Blood Rain causes corrosion damage. Characters hit suffer the Corrosion continuous effect.\n"},
        {"Draconic Blessing","2","6","––","––","YES","NO","Target friendly blighted character or dragonspawn gains +2 STR and Terror [Willpower + 4]."},
        {"Dragon's Blood","2","6","––","––","YES","NO","Target friendly character gains +2 ARM. When an affected character is disabled by a melee attack, roll a d6. On a roll of 5 or 6, the attacker suffers d3 damage points and the Corrosion continuous effect."},
        {"Dragonsight","1","6","––","––","NO","NO","Target friendly character gains Eyeless Sight. A character with Eyeless Sight ignores cloud effects and forests when determining LOS. A character with Eyeless Sight ignores concealment and stealth when making attacks. Dragonsight lasts for one round."},
        {"Eruption Of Spines","3","10","––","10","NO","YES","If target character is hit, d6 nearest characters within thirty feet (5̋) of it suffer a POW 10 damage roll."},
        {"Mystic Wards","3","SELF","CTRL","––","NO","NO","Enemy animi and enemy upkeep spells on models in the spellcaster’s battlegroup in its control range immediately expire. While within 5 ̋ of a model in the spellcaster’s battlegroup in its control range, enemy models cannot cast, channel, or upkeep spells. Mystic Wards lasts for one round."},
        {"Playing God","2","6","––","––","YES","NO","Target warbeast in this character’s battlegroup can make power attacks without being forced and gains Terror [Willpower + 4]. Its melee weapons gain Open Fist and become reach weapons."},
        {"Respawn","3","6","––","––","YES","NO","When target warbeast in this character’s battlegroup is disabled by an enemy attack any time except while it is advancing, place the warbeast anywhere within 18 feet (3̋) of its current location. It heals 1 damage point in each aspect, then Respawn expires."},
        {"Wind Wall","3","SELF","––","––","NO","NO","This character cannot make ranged attacks, and non-magical ranged attacks targeting it automatically miss. While completely within 18 feet (3 ̋) of the spellcaster, characters cannot make ranged attacks and non-magical ranged attacks targeting them automatically miss. Wind Wall lasts for one round."},
        {"Aerial Coordination","2","SELF","CTRL","––","NO","NO","While within the spellcaster’s control area, friendly flying blighted warbeasts gain +2 on melee attack rolls. Aerial Coordination lasts for one turn."},
        {"Arcane Static","4","SELF","CTRL","––","YES","NO","Enemy magic attacks targeting a character in the spellcasters control area suffer –5 RNG and suffers –2 to hit."},
        {"Blind Spot","2","SELF","CTRL","––","YES","NO","Enemy characters roll one fewer die on Detection rolls to detect this character or friendly characters while in the spellcaster’s control area. This spell does not produce spell runes."},
        {"Blight Storm","4","CTRL","5","––","NO","NO","Place a 5̋ AOE anywhere completely within the spellcaster’s control area. When an enemy character in the AOE is directly hit by an attack and the damage roll fails to exceed its ARM, the enemy automatically suffers d3 damage points. Blight Storm lasts for one round."},
        {"Blight Strike","2","8","––","10","NO","YES","Bolts of blighted lightning streak toward the target. A living character damaged by this spell cannot heal or be healed for one round."},
        {"Dash","2","SELF","CTRL","––","NO","NO","While in the spellcaster’s control area, friendly living characters cannot be targeted by free strikes. This character and friendly characters activating in the character’s control area gain +1 SPD. Dash lasts for one turn."},
        {"Hex Bolt","2","6","––","13","NO","YES","A character hit by Hex Bolt cannot make power attacks for one round."},
        {"Howling Wind","1","SELF","•","––","NO","NO","For one round the spellcaster can be heard by friendly characters for a number of miles equal to his ARC stat."},
        {"Ice Cage","3","10","––","10","NO","YES","A character hit suffers a cumulative –2 DEF for one turn unless it has Immunity: Cold. When a character without Immunity: Cold is hit with three or more Ice Cage attacks the same turn, it becomes stationary for one round."},
        {"Abuse","2","6","––","––","NO","NO","Target friendly warbeast gains +2 SPD and STR for one round but suffers d3 damage points."},
        {"Beyond Death","4","B2B","––","––","YES","NO","Target living character’s spirit is locked in his body, and he cannot die. This spell is generally used either to preserve a victim beyond the normal limits of torture or to preserve his spirit for later extraction. If the affected character suffers destruction or would be killed for any reason, instead of being destroyed and dying he enters a death-like state of paralysis. If this spell expires, so does the affected character, generating a corpse and soul token only at this time.\n"},
        {"Blood Mark","2","8","––","––","YES","YES","Target enemy character suffers –2 ARM. The spellcaster can transfer damage from an enemy attack to the affected character one time, then Blood Mark expires."},
        {"Carnage","3","SELF","CTRL","––","NO","NO","Friendly characters gain +2 to melee attack rolls against enemies in the spellcaster’s control range. Carnage lasts for one round."},
        {"Essence Blast","3","CTRL","•","––","NO","YES","Choose a friendly living character in the spellcaster’s control range. The spellcaster makes a contested Willpower roll against the selected character. If the selected character wins, nothing happens. If the spellcaster wins, make a SP 6 magic attack using the selected character as the attack’s point of origin. Characters hit suffer a damage roll with a POW equal to 5 + the base STR of the selected character. After the spell is resolved, the selected character is destroyed without generating any tokens."},
        {"Fury","3","6","––","––","NO","NO","Target friendly living character gains +3 to melee damage rolls but suffers –1 DEF."},
        {"Grip Of Death","2","SELF","CTRL","––","YES","NO","While in the spellcaster’s control area, friendly living characters automatically pass Willpower rolls to resist Terror."},
        {"Guidance","2","6","––","––","YES","NO","Target friendly character gains Eyeless Sight and his weapons become magical weapons. (A character with Eyeless Sight ignores cloud effects and forests when determining line of sight. The character ignores concealment and stealth when making attacks.)"},
        {"Hardened Flesh","1","6","––","––","NO","NO","Target friendly non-warbeast character gains the Tough Mighty archetype bene t for one round."},
        {"Hollow","2","6","––","––","YES","NO","Target friendly living non-warbeast character becomes undead and gains the Tough Mighty archetype benefit. When the affected character is destroyed, the spellcaster gains the destroyed character’s soul token."},
        {"Illusion Of Vitality","3","SELF","CTRL","––","YES","NO","While in the spellcaster’s control area, friendly living characters do not suffer the effects of lost aspects."},
        {"Mage Sight","2","CTRL","5","––","YES","NO","Place a 5 ̋ AOE completely in the spellcaster’s control range. While a character is within the AOE, friendly characters ignore forests and cloud effects when drawing LOS to him and ignore stealth when attacking him."},
        {"Parasitic Invigoration","1","10","––","––","NO","NO","Target friendly character suffers d3 damage points and immediately removes one degree of exhaustion (see Iron Kingdoms Unleashed Roleplaying Game: Core Rules, p. 225). The spellcaster can choose to suffer the damage roll instead. Decide whether the spellcaster suffers the damage before the roll is made."},
        {"Psychic Vampire","3","SELF","CTRL","––","YES","NO","When an enemy casts a spell or uses an animus while in the spellcaster’s control range, the enemy suffers d3 damage points and the spellcaster regains d3 vitality points."},
        {"Sacrificial Pawn","3","SELF","•","––","YES","NO","When the spellcaster is directly hit by an enemy ranged attack, you can choose to have one friendly, living non-incorporeal character within 3̋ of the spellcaster directly hit instead. That character is automatically hit and suffers all damage and effects."},
        {"Savagery","2","6","––","––","YES","NO","Target friendly character gains +5 SPD when making a full advance but cannot make ranged attacks. The affected character rolls one less die on INT and non-Intimidation Social rolls."},
        {"Shadow Sight","1","6","––","––","YES","NO","Target friendly character can see perfectly in darkness."},
        {"Somnambulist","3","CTRL","––","––","NO","NO","Choose a friendly living character in the spellcaster’s control range. The spellcaster makes a contested Willpower roll against the selected character. If the selected character wins, nothing happens. If the spellcaster wins, his controlling player can advance the selected character up to eighteen feet (3 ̋)."},
        {"Soulfire","2","10","––","12","NO","YES","When a living non-soulless character is destroyed by Soul re, the spellcaster gains 1 fury point. A character destroyed by Soul re does not generate a soul token."},
        {"Torment","2","10","––","12","NO","YES","When a character is damaged by Torment, he loses Tough, cannot heal or be healed, and cannot transfer damage for one round."},
        {"Unease","2","8","––","––","NO","YES","The spellcaster uses his powers of mortitheurgy to subtly cause unease in his target enemy, increasing his heartbeat, causing shortness of breath, restricting blood  ow, etc. The affected character suffers –2 to Willpower, fatigue, and non-combat skill rolls. Unease lasts for one round."},
        {"Void Curse","4","––","––","––","NO","NO","This spell permanently turns a skorne spirit into a void spirit (see Iron Kingdoms Skorne Empire, p. 148) under the spellcaster’s control. The spellcaster must have a soul token that formerly belonged to a skorne character. The spellcaster makes a contested Willpower role against the character who generated the token. If the spellcaster loses, the soul token is lost to the void and nothing else happens. If the spellcaster wins, he creates a void spirit enslaved to his will. Place the void spirit in play B2B with the spellcaster. The void spirit can make attacks only while in the spellcaster’s command range. A spellcaster’s void spirits take their turns during his activation. A spellcaster can have up to three void spirits at a time."},
        {"Wave Of Vivification","3","SELF","CTRL","––","NO","NO","The spellcaster suffers d3 damage points and friendly characters in his control area can choose to suffer 1 damage point to immediately remove one degree of exhaustion (see Iron Kingdoms Unleashed Roleplaying Game: Core Rules, p. 225)."},
        {"Aggravator","3","SELF","CTRL","––","YES","NO","While in the spellcaster’s control area, friendly warbeasts gain Hyper Aggressive. (When a character with Hyper Aggressive su ers   m ge from  n enem   tt     n time e  e t  hi e it is advancing, after the attack is resolved it can immediately make a full advance directly toward the attacking character.)"},
        {"Agitation","3","SELF","CTRL","––","NO","NO","Place 1 fury point on each enemy warbeast currently in the spellcaster’s control area. This spell cannot cause a warbeast to exceed its FURY in fury points. This spell can be cast only once per turn."},
        {"Awakened Spirit","2","6","––","––","YES","NO","Target warbeast in the spellcaster’s battlegroup can use its animus once during its activation without being forced. A warbeast that uses its animus as a result of Awakened Spirit cannot also be forced to use its animus that activation."},
        {"Bad Blood","2","10","––","––","YES","YES","A warlock leaching from target warbeast suffers 1 damage point for each fury point leached. The affected warbeast cannot regain vitality or have damage transferred to it."},
        {"Battle Charged","2","SELF","CTRL","––","YES","NO","While in the spellcaster’s control area, characters in his battlegroup gain Counter Charge. (When an enemy advances and ends its movement within 6 ̋ of a character with Counter Charge that is in his LOS, the character with Counter Charge can immediately charge the enemy. A character can use Counter Charge only once per round. A character cannot counter charge while engaged.)"},
        {"Bestial","3","SELF","CTRL","––","NO","NO","While in the spellcaster’s control area, enemies cannot cast spells or be used to channel spells. Bestial lasts for one round."},
        {"Blessing Of The Devourer","2","6","––","––","YES","NO","Target character gains +3 on Climbing, Jumping, Sneak, and Swimming rolls."},
        {"Blood Feast","4","SELF","––","––","NO","NO","The spellcaster gains +1 STR and ARM for each living character he destroys. Blood Feast lasts for one round.\n"},
        {"Blood Magic: Accurate Strike","1","SELF","––","––","NO","NO","The spellcaster’s next empowered weapon melee attack roll this turn is boosted."},
        {"Blood Magic: Black Poison","1","SELF","––","––","NO","NO","If the spellcaster’s next empowered weapon melee attack this turn hits  the t rget  ire t   hit su ers  ontinuous   e t   orrosion."},
        {"Blood Magic: Bleeder","2","SELF","––","––","NO","NO","If the spellcaster’s next empowered weapon melee attack this turn damages a living character, the spellcaster regains d3 vitality points."},
        {"Blood Magic: Blood Burst","2","SELF","*","––","NO","NO","If the spellcaster destroys a living character with his next empowered weapon melee attack, center a 5 ̋ AOE on the destroyed character, then remove the destroyed character from the table. Enemy characters in the AOE suffer an unboostable blast damage roll with a POW equal to the STR of the destroyed character."},
        {"Blood Magic: Brain Damage","3","SELF","––","––","NO","NO","A living character damaged by the spellcaster’s next empowered weapon melee attack this turn cannot cast spells, upkeep spells, or use an animus for one round."},
        {"Blood Magic: Brutal Strike","1","SELF","––","––","NO","NO","The spellcaster’s next empowered weapon melee attack this turn gains an additional damage die."},
        {"Blood Magic: Dispel","2","SELF","––","––","NO","NO","If the spellcaster directly hits a target with his next empowered weapon melee attack this turn, upkeep spells and animi on the target immediately expire."},
        {"Blood Magic: Grievous Strike","3","SELF","––","––","NO","NO","A living character damaged by the spellcaster’s next empowered weapon melee attack this turn loses Tough, cannot heal or be healed, and cannot transfer damage for one round."},
        {"Blood Magic: Heart Stopper","4","SELF","––","––","NO","NO","Damage exceeding the ARM of the character hit by the spellcaster’s next empowered weapon melee attack this turn is doubled. A character disabled by this attack cannot make a Tough roll."},
        {"Blood Magic: Hobbler","2","SELF","––","––","NO","NO","A living character damaged by the spellcaster’s next empowered weapon melee attack this turn suffers -2 DEF and cannot run or charge for one round."},
        {"Blood Magic: Invigoration","2","SELF","––","––","NO","NO","If the spellcaster’s next empowered weapon melee attack this turn destroys a living character, the spellcaster can advance up to 3̋ immediately after the attack has been completely resolved."},
        {"Blood Magic: Weakness","2","SELF","––","––","NO","NO","A living character damaged by the spellcaster’s next empowered weapon melee attack this turn suffers -3 STR for one round."},
        {"Carnivore","2","6","––","––","YES","NO","Target character gains +2 to melee attack rolls against living targets. When the affected character destroys a living character with a melee attack, the spellcaster regains d3 vitality points. The destroyed character does not provide corpse, heart, or soul tokens."},
        {"Cloak Of The Predator","1","6","––","––","NO","NO","Target character gains stealth while within terrain that provides concealment, the AOE of a spell that provides concealment, or the AOE of a cloud effect. Cloak of the Predator lasts for one round."},
        {"Cold-Blooded","1","6","––","––","YES","NO","Target character can reroll missed attack rolls against living characters. Each roll can be rerolled only once as a result of Cold-Blooded. Cold-Blooded lasts for one round."},
        {"Cross-Country","2","6","––","––","YES","NO","Target friendly character gains Pathfinder and Hunter. (A character with Hunter ignores forests, concealment, and cover when determining LOS or making a ranged attack.)"},
        {"Curse Of Shadows","3","8","––","––","YES","YES","Target enemy suffers -2 ARM and cannot make free strikes. A character can advance through the affected character if he has enough movement to move completely past the affected character’s base."},
        {"Dark Water","3","CTRL","4","––","NO","NO","Place a 4 ̋ AOE anywhere completely within the spellcaster’s control area where it does not touch a character’s base. The AOE is shallow water and remains in play for one round. While completely within the AOE, a character with the Amphibious ability cannot be targeted by ranged attacks."},
        {"Death Pact","2","6","––","––","YES","NO","Target living character gains +2 ARM and becomes Undead."},
        {"Deathly Slumber","4","6","––","––","NO","NO","Target living incapacitated friendly character is immediately stabilized and enters a slumber from which he cannot be awakened for d6 + 3 days. During this time, the character cannot be further injured, his flesh takes on the appearance of death, and anyone who touches him suffers d3 points of cold damage. When the character awakens, he is healed of all injuries sustained in the encounter in which he became incapacitated."},
        {"Dirge Of Mists","1","6","––","––","NO","NO","Target friendly character gains +1 DEF and Terror [Willpower]. Dirge of Mists lasts for one round."},
        {"Dog Pile","2","10","––","––","YES","YES","Warbeasts in this character’s battlegroup can charge or make slam power attacks against target enemy without being forced and regardless of LOS. When a warbeast does, it gains +2̋ movement and gains Pathfinder while resolving that charge or slam."},
        {"Earth's Cradle","1","SELF","––","––","YES","NO","The spellcaster gains cover, does not suffer blast damage, and does not block LOS. Earth’s Cradle expires if this character moves, is placed, or is engaged."},
        {"Elemental Protection","4","SELF","CTRL","––","YES","NO","While in the spellcaster’s control area, friendly characters gain Immunity: Cold, Immunity: Electricity, and Immunity: Fire."},
        {"Enthrall Spirit","3","8","––","––","YES","YES","The spellcaster makes a contested Willpower roll against target undead incorporeal enemy hit by this spell. If the spellcaster loses, nothing happens. If the spellcaster wins, he takes control of the target for as long as the upkeep is paid."},
        {"Eruption Of Life","3","10","––","13","NO","YES","If this attack destroys a living or undead enemy, center a 3 ̋ AOE on the destroyed character. The AOE is forest terrain that remains in play for one round. Enemies in the AOE are hit and suffer an unboostable POW 13 blast damage roll."},
        {"Feast Of Worms","4","10","4","12","YES","YES","While in the AOE, enemies suffer -2 ARM. The AOE remains in play for as long as upkeep is paid."},
        {"Flesh Eater","3","10","––","13","NO","YES","When a living character is destroyed by Flesh Eater, the spellcaster or a living warbeast in his battlegroup that is in his control area regains d3 vitality points."},
        {"Force Bolt","2","10","––","10","NO","YES","An enemy hit by Force Bolt can be pushed d3 ̋ directly toward or away from the spellcaster. Choose the direction before rolling the distance. On a critical hit, the enemy is knocked down after being pushed."},
        {"Forced Evolution","2","6","––","––","YES","NO","Target friendly living warbeast gains +2 STR and DEF."},
        {"Ghost Walk","3","6","––","––","NO","NO","Target character gains Ghostly for one turn. (A character with Ghostly can advance through terrain and obstacles without penalty and can advance through obstructions if he has enough movement to move completely them. An affected character cannot be targeted by free strikes.)"},
        {"Hallowed Guardian","2","6","––","––","YES","NO","When an enemy attack incapacitates or destroys one or more friendly characters within 5̋ of target warbeast in the spellcaster’s battlegroup, after the attack is resolved the affected warbeast can charge an enemy. Hallowed Guardian then expires."},
        {"Harvest","4","SELF","CTRL","––","NO","YES","When a living or undead enemy character is destroyed in the spellcaster’s control area, the spellcaster can gain 1 fury point. The spellcaster’s fury point total cannot exceed his FURY as a result of Harvest."},
        {"Hellmouth","4","8","*","––","NO","YES","If this attack misses, nothing happens. If it hits, before making the damage roll, push characters within 3̋ of the character hit 3̋ directly toward the character hit in the order you choose, then center a 3̋ AOE on the character hit. Characters in the AOE suffer a POW 12 damage roll."},
        {"Hidden Path","1","SELF","––","––","YES","NO","The target character’s path is magically obscured whether he is mounted or on foot. The target number for Tracking rolls to follow the target character’s trail is increased by 3."},
        {"Hunter's Mark","4","10","––","––","NO","NO","Friendly characters can charge or make a slam power attack against an enemy hit by Hunter’s Mark without being forced or spending focus. A character charging an enemy hit by Hunter’s Mark gains +2 ̋ movement. Hunter’s Mark lasts for one round."},
        {"Incite","4","SELF","––","––","NO","NO","Characters in the spellcaster’s battlegroup gain +2 to attack and damage rolls against enemies in the spellcaster’s command range. Incite lasts for one turn."},
        {"Inviolable Resolve","2","6","––","––","YES","NO","Target friendly character gains +2 ARM and automatically passes Willpower rolls to resist Terror."},
        {"Jaws Of Death","2","SELF","––","––","YES","NO","In addition to his normal attacks, the spellcaster can make one unarmed melee attack with his fanged maw during each of his turns. This POW 3 attack uses the Unarmed Combat skill. If the spellcaster destroys a living character with this attack, he can consume it to regain d3 vitality points."},
        {"Killing Ground","3","SELF","CTRL","––","NO","NO","Friendly characters beginning a charge in the spellcaster’s control area gain Pathfinder. Warbeasts in the spellcaster’s battlegroup beginning their turns in his control area can charge or make slam power attacks against enemies without being forced. Killing Ground lasts for one round."},
        {"Killing Tide","3","SELF","––","––","YES","NO","The spellcaster gains Berserk. (When a character with Berserk incapacitates or destroys one or more characters with a melee attack during his turn, immediately after the attack is resolved he must make one additional melee attack against another character in his melee range.)"},
        {"Leash","2","6","––","––","YES","NO","Immediately after the spellcaster ends his normal movement, target warbeast in his battlegroup can advance up to 3̋ toward him."},
        {"Lightning Storm","3","8","3","10","NO","YES","Characters hit suffer a POW 10 electrical damage roll. The AOE remains in play for one round. Characters entering or ending their turns in the AOE suffer a POW 10 electrical damage roll."},
        {"Lightning Tongue","3","10","––","13","NO","YES","On a hit, you can have lightning arc to the nearest character within 4̋ of the character hit. The character the lightning arcs to suffer an unboostable POW 10 electrical damage roll."},
        {"Marked For Death","2","8","––","––","YES","YES","Target enemy suffers -2 DEF, loses incorporeal and stealth, and cannot gain those abilities while affected by Marked for Death. Friendly characters ignore LOS when targeting an affected character."},
        {"Medicate","2","6","––","––","NO","NO","Target living warbeast regains d3 vitality points."},
        {"Mist Shroud","1","6","––","––","NO","NO","Target character gains concealment for one round."},
        {"Mobility","2","SELF","CTRL","––","NO","NO","Characters in the spellcaster’s battlegroup currently in his control area gain +2 SPD and Pathfinder."},
        {"Mortality","3","10","––","––","NO","YES","Target enemy suffers -2 DEF and ARM and cannot reg in vitality. Mortality lasts for one round."},
        {"Murder Of Crows","3","CTRL","5","––","YES","NO","Place a 5̋ AOE cloud effect anywhere completely within the spellcaster’s control area. Characters that enter or end their activations in the AOE suffer an unboostable POW 8 damage roll unless they are part of the spellcaster’s battlegroup. This is not an offensive spell, and no damage is dealt when the AOE is placed."},
        {"Muzzle","2","10","––","12","NO","YES","A warbeast damaged by Muzzle cannot advance toward the spellcaster for one round."},
        {"Mystic Wards","3","SELF","CTRL","––","NO","NO","Enemy animi and enemy upkeep spells on characters in the spellcaster’s battlegroup that are in his control area immediately expire. While within 5 ̋ of a character in the spellcaster’s battlegroup that is in his control area, enemies cannot cast, channel, or upkeep spells. Mystic Ward lasts for one round."},
        {"Parasite","3","8","––","––","YES","YES","Target character suffers -3 ARM, and the spellcaster gains +1 ARM."},
        {"Pig Pen","2","SELF","––","––","YES","NO","While within 3̋ of the spellcaster, enemies treat open terrain as rough terrain."},
        {"Primal Shock","2","CTRL","––","*","NO","YES","Choose a warbeast in the spellcaster’s battlegroup that is in his control area. Target an enemy within 8̋ of the chosen warbeast, and make a magic attack against it. The chosen warbeast is the attacks' point of origin. If the enemy is hit, he suffers a magic damage roll with a POW equal to the warbeast’s base STR."},
        {"Quagmire","2","6","––","––","YES","NO","While B2B with target friendly character, enemies suffer -2 DEF and cannot advance except to change facing."},
        {"Raise Dead","4","SELF","CTRL","––","YES","NO","When a living character is destroyed in the spellcaster’s control area, the spellcaster can immediately make a full advance with the destroyed character followed by a normal melee attack, then remove the destroyed character from the table. During these attacks, the destroyed character is considered to be undead."},
        {"Rampager","3","10","––","––","NO","YES","The spellcaster takes control of target enemy warbeast. He can make one full advance with the warbeast and then make one normal attack with it, then Rampager expires. While the warbeast is affected by Rampager, it cannot use its animus. Rampager can be cast only once per turn."},
        {"Rapid Growth","2","CTRL","4","––","YES","NO","Place the AOE completely in this character’s control area. The AOE is a forest that remains in play as long as the upkeep is paid."},
        {"Ravager (Unleashed)","2","6","––","––","NO","NO","Target warbeast in the spellcaster’s battlegroup gains Berserk for one turn. While affected by Ravager, a warbeast cannot make chain attacks. (When a warbeast with Berserk incapacitates or destroys one or more characters with a melee attack during its turn, immediately after the attack is resolved it must make one additional melee attack against another character in its melee range.)"},
        {"Rawhide","2","6","––","––","YES","NO","Target warbeast gains +2 ARM. The affected warbeast and friendly characters B2B with it cannot be knocked down, pushed, or slammed."},
        {"Restoration","2","6","––","––","YES","NO","Target friendly living character gains +2 ARM. When this spell’s upkeep is paid, the spellcaster regains d3 vitality if he is within 3̋ of another character affected by this spell."},
        {"Rise In Death","3","8","––","––","YES","YES","When target living warbeast is incapacitated, it immediately regains its vitality points and becomes an undead character under the spellcaster’s control. If Rise in Death expires or the warbeast is incapacitated again, it is destroyed."},
        {"Rising Tide","4","SELF","CTRL","––","NO","NO","Characters currently in the spellcaster’s control area treat the area as shallow water (see Iron Kingdoms Unleashed Core Rules, p. 221) with a depth of three feet. Rising Tide lasts for one round."},
        {"Roots Of The Earth","2","6","––","––","NO","NO","Target friendly character gains +3 ARM, cannot be knocked down, and cannot move or be placed. Roots of the Earth lasts for one round."},
        {"Shadow Pack","3","SELF","CTRL","––","YES","NO","Characters in the spellcaster’s battlegroup gain stealth while in his control area."},
        {"Soothing Song","1","SELF","CTRL","––","YES","NO","Remove up to 1 fury point from each friendly living warbeast in the spellcaster’s battlegroup that is in his control area. Soothing Song can be cast only once per turn."},
        {"Soul Slave","2","6","––","––","YES","NO","Target warbeast in the spellcaster’s battlegroup automatically passes threshold checks. The spellcaster can channel spells through the affected warbeast."},
        {"Spirit Fang","2","10","––","12","NO","YES","A character damaged by Spirit Fang suffers -2 SPD and DEF for one round."},
        {"Spirit Lash","2","10","––","10","NO","YES","A character damaged by Spirit Lash cannot run, charge, or be placed for one round."},
        {"Stone Form ","2","SELF","––","––","NO","NO","The spellcaster gains +4 ARM but immediately becomes stationary. Stone Form lasts for one round or until the spellcaster is no longer stationary."},
        {"Stone Hold","4","SELF","CTRL","––","YES","NO","While in the spellcaster’s control area, friendly characters cannot be knocked down and gain +2 ARM while engaged."},
        {"Stone Skin","2","6","––","––","YES","NO","Target friendly character gains +2 STR and ARM but suffers –1 SPD and DEF."},
        {"Storm Wall","3","SELF","CTRL","––","NO","NO","While in the spellcaster's control area, enemies suffer -5 RNG on their ranged attacks. When an enemy AOE ranged attack deviates from a point in the spellcaster’s control area, the spellcaster’s player chooses the deviation direction after the deviation distance is rolled. Storm Wall lasts for one round."},
        {"Summon Vortex","2","SELF","––","––","NO","NO","Center a 3̋ AOE cloud effect on the spellcaster. Enemies suffer –2 to attack rolls while in the AOE. Summon Vortex lasts for one round."},
        {"Sunder Spirit","2","10","––","12","NO","YES","A warbeast damaged by Sunder Spirit loses its animus for one round."},
        {"Sunhammer","3","SELF","CTRL","––","YES","NO","Enemy warbeasts and warjacks that advance more than 1̋ and end their normal movement in the spellcaster’s control area suffer d3 damage points."},
        {"Sure Foot","3","6","––","––","YES","NO","Target character gains +2 DEF and cannot be knocked down. While within 3̋ of the affected character, friendly characters also gain +2 DEF and cannot be knocked down."},
        {"Unminding","3","10","––","––","YES","YES","Target enemy warbeast suffers -2 FURY and THR and loses its animus as a spell as if the animus belonged to a warbeast he controls."},
        {"Unnatural Aggression","2","6","––","––","YES","NO","If target warbeast in the spellcaster's battlegroup sufferd damage during an enemy’s turn during the last round, the warbeast can make a full advance during the spellcaster’s next Maintenance Phase. During this movement, it must move toward the nearest enemy."},
        {"Veil Of Mists","3","CTRL","4","––","YES","NO","Place a 4 ̋ AOE cloud effect anywhere completely in the spellcaster’s control area. This AOE does not block friendly characters’ LOS. While in the AOE, friendly characters ignore movement penalties for rough terrain and can move through obstructions and other characters if they have enough movement to move completely past them."},
        {"Venom","2","SP 8","––","10","NO","YES","Characters hit suffer the Corrosion continuous effect."},
        {"Voodoo Doll","2","8","––","––","NO","YES","Choose one of target living enemy’s aspects on his damage spiral. That aspect suffers the effects of being crippled for one round."},
        {"Warpath","2","SELF","CTRL","––","YES","NO","When a character in the spellcaster’s battlegroup that is in his control area destroys one or more enemies with a melee or ranged attack during his activation, one warbeast in the spellcaster’s battlegroup that is in his control area can advance up to 3 ̋ immediately after the attack is resolved. A warbeast can advance only once per turn as a result of Warpath."},
        {"Watcher (Unleashed)","3","SELF","––","––","YES","NO","When an enemy character advances and ends its movement within 6 ̋ of the spellcaster, choose a warbeast in the spellcaster’s battlegroup that is in his control area. That warbeast can immediately make a full advance and then can make one normal melee or ranged attack targeting the enemy character. The attack and damage rolls against that character are boosted. After the attack is resolved, Watcher expires."},
        {"Weald Hunter","2","6","––","––","YES","NO","Target character gains Treewalker. (A character with Treewalker ignores forests when determining LOS. While in a forest, a character with Treewalker gains +2 DEF against melee attack rolls and can advance through obstructions and other characters if he has enough movement to move completely past them.)"},
        {"Wild Aggression","3","6","––","––","YES","NO","Target living warbeast in the spellcaster’s battlegroup can run, charge, or make slam or trample power attacks without being forced and gains boosted melee attack rolls."},
    };

        arrayList.clear();

        for (String[] ability1 : archetypes) {
            Spell ability = new Spell(ability1[0], ability1[1], ability1[2], ability1[3], ability1[4], ability1[5], ability1[6], ability1[7]);
            arrayList.add(ability);
        }

        //Sorting
        Collections.sort(arrayList, new Comparator<Spell>() {
            @Override
            public int compare(Spell lhs, Spell rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });

        final ListView listView = (ListView) view.findViewById(R.id.listView1);
        archetypeAdapter = new SpellAdapter(getActivity(), arrayList);
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
