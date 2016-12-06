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

import com.example.userdaniel.ironkingdoms.Adapters.AbilityAdapter;
import com.example.userdaniel.ironkingdoms.Adapters.MeleeWeaponsAdapter;
import com.example.userdaniel.ironkingdoms.Database;
import com.example.userdaniel.ironkingdoms.InfoFragments.MeleeWeaponInfoFragment;
import com.example.userdaniel.ironkingdoms.Models.Ability;
import com.example.userdaniel.ironkingdoms.Models.MeleeWeapons;
import com.example.userdaniel.ironkingdoms.R;

import java.sql.SQLException;
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
public class MeleeWeaponsFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private ArrayList<MeleeWeapons> arrayList = new ArrayList<>();
    private MeleeWeaponsAdapter abilityAdapter;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MeleeWeaponsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.searchable_list1, container, false);

        String[][] abilities = {{"Assassin's Blade","10 gc","Hand Weapon","-1","4","The assassin’s blade is a thick-bladed short sword designed for thrusting. Due to its high damage output and relative ease of concealment, such weapons are favored by assassins and murderers. Though capable of dealing mortal injuries, the assassin’s blade is a clumsy weapon and best plunged into an unsuspecting victim’s back.","Add +2 to back strike damage rolls with this weapon."},
                {"Axe","8 gc","Hand Weapon","0","3","Hand axes are simple weapons capable of in icting severe wounds. They are commonly used by hunters, trackers, and rangers. The Khadoran Army equips most of their soldiers with axes due to the ease of training and the weapon’s utility as a tool.","None."},
                {"Axe, Great","25 gc","Great Weapon","0","6","These massive two-handed axes are intimidating and deadly. With hafts as long as a man is tall and bearing enormous blades, these weapons are favored by trollkin, ogrun, and humans possessed of exceptional strength.","On a critical hit, this weapon inflicts an additional die of damage.\n\nA character must have at least STR 5 to use this weapon and can only use this weapon two-handed."},
                {"Axe, Horseman's","20 gc","Hand Weapon","-1 (on foot}, 0 (mounted)","3 (on foot}, 5 (mounted)","A horseman’s axe is an axe blade on an extended haft intended for cleaving downward while in battle from horseback.","This weapon has Reach.\n\nWhile wielded by a mounted combatant, add +2 to charge attack damage rolls with this weapon."},
                {"Bayonet","5 gc","Hand Weapon (used as a dagger or affixed to a weapon smaller than a rifle}, Great Weapon (affixed to a rifle)","-1","2 (used as a dagger or affixed to a weapon smaller than a rifle}, 3 (affixed to a rifle)","Bayonets are small, dagger-like blades that can be af xed to the barrel of a  rearm. Bayonets are most often af xed to military ri es used by soldiers in the close quarters of trench warfare. Some specialized bayonets have been crafted for use with multi-barreled firearms as well as crossbows.","A bayonet used as a dagger or affixed to a weapon smaller than a rifle uses the Hand Weapon skill.\n\nA bayonet affixed to a rifle has Reach, must be used two-handed, and uses the Great Weapon skill.\n\nA character gains +2 to his charge attack rolls with a bayonet affixed to a rifle."},
                {"Blasting Pike","50 gc","Great Weapon","-2","7","The Iron Fang blasting pike is among the most devastating polearms ever developed. It is tipped with a powerful explosive charge with a directional blast that obliterates common foes or rips gaping holes in warjacks. The explosion also has the potential to knock foes to the ground should they survive.","Blasting Pikes are Reach weapons.\n\nOn a critical hit, the target is knocked down.\n\nExplosive blasting pike heads must be replaced after every attack."},
                {"Caspian Battleblade","20 gc","Great Weapon","-1 (one-handed}, 0 (two-handed)","4 (one-handed}, 6 (two-handed)","A descendant of the cleaving swords used by the Caspians ages ago, the battleblade is a wide, hefty double- edged blade suitable for cleaving and heavy cuts. The tip of the battleblade is never sharpened; the weapon is used solely as a cleaving weapon. Some battleblades are rounded off at the end while others come to a short, purely ornamental point, and a rare few  are out at the tip in a heart or spade-like shape. Most favored by the nobility and knightly orders of Cygnar, the overall design of the blade is utilized by swordsmen across western Immoren.","A character must have at least STR 5 to use this weapon in one hand."},
                {"Club","3 gc","Hand Weapon","0","2","A club is a wooden implement for delivering blunt trauma. These weapons take many forms, from the polished and tooled truncheons carried by the city watches across the Iron Kingdoms, to brutish weapons carried by the uncivilized races of the wilds.","On a critical hit, a living target hit has a chance to be knocked out by the attack. If the target suffers damage from the attack, he must make a Willpower roll against a target number equal to the attacking character’s STR + 9. If the target succeeds, he stays conscious. If he fails, he is knocked out."},
                {"Club, Banded","6 gc","Great Weapon","-1","4","The banded club is a weapon crafted specifically for war. It is a stout wooden club that has been banded in steel or iron for added weight and reinforcement.","A character must have at least STR 5 to use this weapon.\n\nOn a critical hit, a living target hit has a chance to be knocked out by the attack. If the target suffers damage from the attack, he must make a Willpower roll against a target number equal to the attacking character’s STR + 11. If the target succeeds, he stays conscious. If he fails, he is knocked out."},
                {"Cutlass","15 gc","Hand Weapon","-1","4","A cutlass is a short and broad slashing sword, with a slightly curved blade sharpened on the cutting edge. The hilt of the cutlass features a solid cupped or basket-shaped guard. The weapon is used extensively by both pirates and the navies of the Iron Kingdoms.","None."},
                {"Dagger","5 gc","Hand Weapon","+1","1","Daggers are short, double-edged  ghting knives. They are made in countless shapes and sizes and are popular hold-out weapons with adventurers and soldiers alike.","None."},
                {"Flail","15 gc","Hand Weapon","-1","4","Originally created as an agricultural tool, the  ail is made up of one or more spiked balls or iron bars separated from a long handle by lengths of chain. These clumsy but brutal weapons are capable of circumventing an opponent’s shield to deliver staggering blows. Some  ails are small enough to be wielded in one hand, making them particularly useful to combatants on horseback.","Attacks from flails ignore ARM bonuses from bucklers and shields."},
                {"Flail, Two-Handed","25 gc","Great Weapon","-2","6","Akin to their smaller counterparts, the largest flails are massive weapons with handles over a yard long. Because they require both hands to carry, they are generally wielded only by warriors on foot. The damage a two-handed flail can inflict is even more severe than that of a standard flail, and they are just as capable of circumventing an opponent’s shield.","Two-handed flails are Reach weapons. Two-handed flails must be used two-handed.\n\nAttacks from flails ignore ARM bonuses from bucklers and shields.\n\nOn a critical hit with this weapon, a character can spend 1 feat point to push the target 1̋ away from the attacking character and knock the target down. After the enemy is pushed, the attacking character can advance up to 1̋."},
                {"Halberd","25 gc","Great Weapon","-1 (one-handed}, 0 (two-handed)","4 (one-handed}, 5 (two-handed)","Halberds and other polearms are common weapons of massed infantry. They are versatile weapons that allow the wielder to stay a considerable distance from his opponents while delivering deadly offensives or withstanding the crush of a cavalry charge.","Halberds are Reach weapons.\n\nThis weapon can be used with one or two hands.\n\nA character fighting two-handed with a halberd gains +2 to his charge attack damage rolls with this weapon."},
                {"Knuckledusters","5 gc","Unarmed Combat","0","1","Knuckledusters are metal braces made to fit over the hand to increase damage made by punches and strikes with the hand.","If a character wearing knuckledusters damages his target with a knockout strike, add +2 to the target number to avoid knockout."},
                {"Kopis","15 gc","Hand Weapon","-1","3","The kopis is the traditional heavy cleaving sword of the Idrian tribes.","A character gains +2 to his charge attack rolls with this weapon.\n\nOn a critical hit with this weapon, a character can spend 1 feat point to gain an additional die on the damage roll."},
                {"Lance","15 gc","Lance","0","8","The lance is a long, heavy spear designed for use on horseback. The lance is longer and heavier than similar weapons used by infantrymen. It is utterly unsuited for throwing or for rapid thrusting.","Lances are Reach weapons.\n\nA character must have at least STR 5 to use this weapon.\n\nLances can only be used to make charge attacks and then only while mounted."},
                {"Mace","15 gc","Hand Weapon","-1","4","A mace is essentially a club topped with a metal head capable of delivering brutal strikes. Some maces are spiked to in ict further injury. They are common weapons among the battle chaplains and knights of the Morrowan Church.","On a critical hit, a living target hit has a chance to be knocked out by the attack. If the target suffers damage from the attack, he must make a Willpower roll against a target number equal to the attacking character’s STR + 9. If the target succeeds, he stays conscious. If he fails, he is knocked out."},
                {"Maul","20 gc","Great Weapon","0","6","The maul is a military sledgehammer of wood and banded steel. The massive pulverizing head is af xed to a long, two-handed shaft.","A character must have at least STR 5 to use this weapon and can only be use this weapon two-handed.\n\nOn a critical hit with this weapon, a character can spend 1 feat point to slam the target d3 ̋ away. The POW of the slam damage roll is equal to the Strength of the attacking character plus the POW of this weapon. The POW of collateral damage is equal to the Strength of the attacking character."},
                {"Nyss Claymore","30 gc","Great Weapon","0","6","The favored weapon of the Nyss, this claymore is an elegant and unusual weapon of remarkable quality. The blades of these swords are typically etched with Aeric runes. The hilts are wrapped in  ne leathers and feature a small circular guard. So widely respected are these weapons that common lore has it that they never dull or suffer the ravages of time.\n\nThough once these weapons were exceedingly rare, many have entered the hands of outsiders following the widespread destruction of the Nyss. The hefty price for these weapons reflects the scarcity of those with the skills to create them and the high demand for these exquisite works.","Nyss claymores must be used two-handed.\n\nA character can spend 1 feat point to boost an attack roll with this weapon."},
                {"Ogrun Warcleaver","30 gc","Great Weapon","-1","6","Created to take full advantage of an ogrun’s massive strength and size, the warcleaver is a sturdy polearm so large and heavy that even ogrun must wield it with both hands. A three-foot-long, one-foot-wide cleaver-like blade is af xed to a stout twelve-foot pole, and the back of the cleaver blade is studded with spikes, allowing the warcleaver to perform devastating chopping or piercing blows.","The warcleaver is a Reach weapon.\n\nA character must have at least STR 6 to use this weapon. This weapon must be used two-handed.\n\nA character with the Huge Stature characteristic gains +2 to his charge attack rolls with this weapon."},
                {"Pickaxe","15 gc","Hand Weapon","-1","4","A pickaxe is a sapping tool as well as a weapon of war favored by the armies of Rhul. The head is a spike ending in a sharp point, which curves slightly and has a counterweight to improve ease of use. The stronger the spike, the more effectively the tool can pierce the surface. The counterweight is nearly always a second spike, often with a  at end for prying. The ef cient momentum of a pickaxe, combined with the small contact area, makes it very effective for punching through armor. Rocking an embedded spike aids in removing it, whether from hard-packed earth or the armor and bone of a felled enemy.","On a hit with this weapon against a knocked down target, a character can spend 1 feat point to gain an additional die on the damage roll."},
                {"Rapier","15 gc","Hand Weapon","0","2","A rapier is a slender, sharply pointed dueling sword, ideally used for thrusting attacks. It features a complex hilt constructed to provide protection for the hand wielding it. This weapon is seldom seen on the battlefields of western Immoren and is considered a weapon of the aristocracy and upper classes.","A character can spend 1 feat point to boost both his attack and damage roll with this weapon."},
                {"Shield","20 gc","Shield","0","0","Shields are large metal plates designed to protect their wielder from harm. Though primarily intended to augment the armor worn by their wielder, shields are also weapons in their own right and can in ict crushing blows with the proper strength behind them.","A character armed with a shield gains +1 ARM for each level of the Shield skill he has against attacks originating in his front arc. This bonus is not cumulative with additional shields."},
                {"Shield, Combat","35 gc","Shield","-1","3","Combat shields are designed with additional reinforcement and spikes, making them useful for offense as well as defense.","A character armed with a shield gains +1 ARM for each level of the Shield skill he has against attacks originating in his front arc. This bonus is not cumulative with additional shields."},
                {"Spear","15 gc","Great Weapon","-1","4 (one-handed}, 5 (two-handed)","The simplest of pole weapons, spears are among the most ancient weapons still utilized by the warriors of the Iron Kingdoms.","When wielded two-handed, spears are Reach weapons.\n\nA character wielding a spear two-handed gains +2 to his charge attack rolls."},
                {"Springblade","12 gc","Hand Weapon","0","1","Springblades are daggers with retractable blades that slide back into their handles when not in use. With the touch to the button of the internal release mechanism, a powerful spring forces the blade outward into  ghting position. This weapon is favored by assassins and street  ghters.","A character can draw a springblade without using a quick action."},
                {"Staff","5 gc","Great Weapon","0","3","This is a six-foot length of polished hardwood. It is favored by travelers, wanderers, and anyone seeking an unassuming weapon.","Staffs are Reach weapons.\n\nStaffs must be used two-handed.\n\nA character can spend 1 feat point to make a trip attack instead of a normal attack with his staff. If the attack hits, the target is knocked down instead of suffering damage."},
                {"Staff, Battle","12 gc","Great Weapon","0","4","Battle staffs are weapons of steel or banded hard wood set with a heavy head and one tip used for delivering crushing blows. The clergy of Menoth favor battle staffs as their primary armament and symbols of of ce.","Battle staffs are Reach weapons.\n\nBattle staffs must be used two-handed.\n\nA character can spend 1 feat point to make a trip attack instead of a normal attack with his staff. If the attack hits, the target is knocked down instead of suffering damage.\n\nOn a critical hit, a living target hit has a chance to be knocked out by the attack. If the target suffers damage from the attack, he must make a Willpower roll against a target number equal to the attacking character’s STR + 9. If the target succeeds, he stays conscious. If he fails, he is knocked out."},
                {"Sword","12 gc","Hand Weapon","0","3","Though each nation favors its own style and means of manufacture, swords have been among the most common weapons in use across Immoren since antiquity.","None."},
                {"Sword Cane","15 gc","Hand Weapon","0","0 (when wielded as a cane) or 2 (when drawn)","Effectively a short sword concealed within the haft of a heavy walking stick, the sword cane is a favored weapon of aristocrats, assassins, and duelists.\n","An observant character can discern a sword cane from a normal cane without handling it with a successful PER + Detection roll against a target number of 14.."},
                {"Sword, Great","20 gc","Great Weapon","0","6","Great swords are heavy, double-edged blades wielded with both hands. Two-handed swords have been favored by swordsmen for centuries, particularly those who regularly  ght heavily armored foes or steamjacks.","Great Swords must be used two-handed.\n\nGreat swords have Reach."},
                {"Trench Knife","10 gc","Hand Weapon, Unarmed Combat","0 (Hand}, -1 (Unarmed Combat)","2","Trench knives are long, heavy bladed daggers favored by  ghting men across western Immoren. The weapon features a studded or spiked “skull crusher” hand guard that be used to deliver bone snapping blows with the fist.","Trench knives’ skull crusher hilts can be used to make Unarmed Combat attack rolls. If a character using the skull crusher as a melee weapon makes a knockout strike that damages his target, add +2 to the target number to avoid knockout."},
                {"Trench Sword","15 gc","Hand Weapon≤, Unarmed Combat","-1","4 (Sword}, 2 (skull crusher)","Modeled after the trench knife, the trench sword is a stout and heavy sword blade af xed to a studded or spiked “skull crusher” hilt.","Trench swords’ skull crusher hilts can be used to make Unarmed Combat attack rolls. On a critical hit with a skull crusher, the target is knocked down. If a character using the skull crusher as a melee weapon makes a knockout strike (p. 210) that damages his target, add +2 to the target number to avoid knockout."},
                {"War Hammer","20 gc","Great Weapon","-1","5","Military hammers are oversized versions of the common tool, typically forged from iron or steel and used by individuals possessed of great strength.","A character must have at least STR 6 to use this weapon in one hand.\n\nOn a critical hit, a living target hit has a chance to be knocked out by the attack. If the target suffers damage from the attack, he must make a Willpower roll against a target number equal to the attacking character’s STR + 12. If the target succeeds, he stays conscious. If he fails, he is knocked out."},
                {"Blazing Sword","These weapons are never available for sale.","Great Weapon","0","5","Most blazing swords were forged in antiquity under the baleful gaze of attending priests and now serve as the weapons of the Exemplar Cinerators. Still burning with an unquenchable inner fire, these weapons remain warm to the touch. Those who fall to them are consumed in blasts of fire.","In the hands of a character of the Menite faith, a blazing sword is a magical weapon. When a Menite character armed with a blazing sword incapacitates an enemy with it, the enemy is consumed in a blast of fire and enemy characters within 1\" of the consumed enemy suffer the Fire continuous effect.\n\nA character must have at least STR 5 to use this weapon in one hand."},
                {"Lance, Blessed","These weapons are never available for sale.","Lance","0","8","These finely crafted lances are provided only to Exemplar Vengers. Each lance is inscribed with sacred prayers before being purified and blessed by a priest.","The blessed lances has Reach.\n\n" +
                        "A character must have at least STR 5 to use this weapon.\n\n" +
                        "Blessed lances can be used only to make charge attacks and then only while mounted.\n\n" +
                        "When a character of the Menite faith makes an attack with the blessed lance, ignore spell effects that add to the target’s ARM or DEF. Additionally, in the hands of a Menite character the blessed lance does full damage against Incorporeal characters."},
                {"Halberd, Consecrated","These weapons are never available for sale.","Great Weapon","0","5","Consecrated halberds are holy weapons wielded by Menoth’s holy warriors. These weapons are often found in the hands of the Bastions and the elite Exemplar guards charged to protect the hierarch and the visgoths.","The consecrated halberd has Reach.\n\n" +
                        "In the hands of a character of the Menite faith, a consecrated\n\n" +
                        "halberd is a magical weapon.\n\n" +
                        "A character must have at least STR 5 to use this weapon in one hand."},
                {"Firebrand","These weapons are never available for sale.","Great Weapon","-2","7","The Firebrand is an ancient Menite weapon that erupts with holy power while in the hands of the faithful. Intended to be wielded one-handed, this heavy and unwieldy weapon can take a lifetime to master.","In the hands of a character of the Menite faith, the Firebrand is a magical weapon that causes the Fire continuous effect on a critical hit.\n\n" +
                        "A character must have at least STR 5 to use this weapon in one hand."},
                {"Spear, Flame","30 gc","Great Weapon","-1","4 (one-handed}, 5 (two-handed)","These seven-foot steel spears are the weapons of the Temple Flameguard. The haft of each spear contains a reservoir of Menoth’s Fury designed to superheat the spearhead in combat.","The flame spear has Reach.\n\n" +
                        "While this weapon is fueled and ignited, a character hit by it\n\n" +
                        "The spear’s integral reservoir holds roughly 5 gc worth of fuel, which burns for 30 minutes in combat. Refueling the reservoir takes a full action."},
                {"Reclaimant Torch","30 gc","Great Weapon","-1","4","This is a long brass-and-steel torch fueled by a tank of Menoth’s Fury. The torch ends in a blazing, mace-like head capable of delivering bone-crushing blows. The fuel tank is generally strapped to the wielder’s waist. Reclaimers use these weapons in the execution of their duties, such as purifying the bodies of the faithful and striking down those who would defile the souls of Menites.","The reclaimant torch has Reach.\n\n" +
                        "While this weapon is fueled and ignited, a character hit by this weapon suffers 1 additional point of fire damage and the Fire continuous effect.\n\n" +
                        "The tank holds enough fuel for 45 minutes of use. Replacement tanks cost approximately 10 gc, depending on availability. Replacing the fuel tank takes a full action."},
                {"Relic Blade","These weapons are never available for sale.","Great Weapon","0","5","Relic blades are sacred swords entrusted to the Knights Exemplar. Each is inscribed with holy runes and purified by the prayers of Menite priests.","In the hands of a character of the Menite faith, a relic blade is a magical weapon."},
                {"Spear, Gaff","20 gc","Great Weapon","-1","3 (one-handed}, 4 (two-handed)","The gaff spear is a sturdy spear with a large hook mounted at the base of the spearhead. It can be used as a spear, but the weapon also offers the user the ability to perform a trip attack using the hook.","The Gaff spear has Reach.\n\nA character can spend 1 feat point to make a trip attack instead of a normal attack with his gaff spear. If the attack hits, the target is knocked down but does not suffer any damage."},
                {"Dagger, Poisoner's","20 gc","Hand Weapon","+1","1","The poisoner’s dagger is a hollow-bladed weapon with a poison reservoir concealed in its handle. A would-be assassin can prepare his blade with a touch of a button, causing poison to flow slowly from the reservoir in preparation for a strike.","Triggering the flow of poison takes a quick action. Once triggered, the blade is prepared and the next character damaged by the weapon suffers the effects of the poison.\n\n" +
                        "The dagger can hold up to three doses of poison.\n\nRefilling the reservoir takes five minutes of careful labor.\n\n" +
                        "The dagger can be used to deliver somnolence elixir as well as poisons."},
                {"Garrote","2 gc","Unarmed Combat","0","––","A garrote is a device designed to strangle a victim from behind, regardless of the form it takes. Purpose-built garrotes consist of a length of wire with a handle on each end. The garrote is not a battlefield weapon and can be used only from behind a target with the element of surprise.","This weapon causes no damage. Instead, it is used to choke the life out of a victim.\n" +
                        "To use this weapon, the attacking character must succeed in hitting his target with a back strike with the garrote. If the attack hits, the attacker can begin strangling his victim. The attacker and victim immediately each roll a d6. The attacker adds his STR to his roll and the victim adds his PHY. If the attacker’s total is higher, the victim suffers –1 PHY. If the victim wins, he breaks free. If the totals are equal, he does not.\n\n" +
                        "The attacker can take no other actions while strangling his victim, but he can release the victim at any time. At the start of each of the attacker’s subsequent turns, the two characters roll again to determine the effect of the strangulation.\n\n" +
                        "While being garroted, a victim cannot speak, yell, or use his voice to make any sound. The victim can use his turns to attempt to fight off his attacker as a full action (triggering another strangulation roll that might result in further PHY loss}, fight through his terror with a successful Willpower roll to take another action, or fight to get a mouthful of air (no effect, but does not force another strangulation roll). If the victim attempts to take any action other than fighting his attacker off, he must make a Willpower roll against a target number of 14. If he succeeds, he can take any action he is physically capable of but suffers a –3 penalty on skill and attack rolls and cannot move away from his attacker. If he fails, he must forfeit his turn.\n\n" +
                        "If a victim is reduced to 0 PHY, he is knocked out. If he is strangled beyond 0 PHY, he dies. A character recovers PHY lost as a result of strangulation at a rate of 1 point per hour of rest."},
                {"Garrote, Clockwork","15 gc","Unarmed Combat","0","––","The clockwork garrote is disguised within a common pocket watch. The user slips his finger into a ring at the top of the watch and twists it. He can then unwind the attached garrote cord. After the garrote has been used, the ring is simply released and the cord automatically winds back into the watch.","This weapon causes no damage. Instead, it is used to choke the life out of a victim.\n" +
                        "To use this weapon, the attacking character must succeed in hitting his target with a back strike with the garrote. If the attack hits, the attacker can begin strangling his victim. The attacker and victim immediately each roll a d6. The attacker adds his STR to his roll and the victim adds his PHY. If the attacker’s total is higher, the victim suffers –1 PHY. If the victim wins, he breaks free. If the totals are equal, he does not.\n\n" +
                        "The attacker can take no other actions while strangling his victim, but he can release the victim at any time. At the start of each of the attacker’s subsequent turns, the two characters roll again to determine the effect of the strangulation.\n\n" +
                        "While being garroted, a victim cannot speak, yell, or use his voice to make any sound. The victim can use his turns to attempt to fight off his attacker as a full action (triggering another strangulation roll that might result in further PHY loss}, fight through his terror with a successful Willpower roll to take another action, or fight to get a mouthful of air (no effect, but does not force another strangulation roll). If the victim attempts to take any action other than fighting his attacker off, he must make a Willpower roll against a target number of 14. If he succeeds, he can take any action he is physically capable of but suffers a –3 penalty on skill and attack rolls and cannot move away from his attacker. If he fails, he must forfeit his turn.\n\n" +
                        "If a victim is reduced to 0 PHY, he is knocked out. If he is strangled beyond 0 PHY, he dies. A character recovers PHY lost as a result of strangulation at a rate of 1 point per hour of rest.\n\n" +
                        "A character can discern a clockwork garrote from a normal pocket watch only by handling it and making a successful PER + Detection roll against a target number of 15."},
                {"Pen, Blade","15 gc","Hand Weapon","0","1","The blade pen is a very small knife disguised as a common fountain pen. With the press of a button the pen extends a needle-like blade, making it a favorite implement of spies, assassins, and anyone else wishing to carry a weapon undetected.","A character can discern a blade pen from a normal pen only by handling it and making a successful PER + Detection roll against a target number of 14. A roll of all 1s indicates the character accidentally tripped the mechanism deploying the blade and injured himself in some way determined by the Game Master."},
                {"Pen, Poison","25 gc","Hand Weapon","0","-2","This weapon is an alchemical injector disguised as a common fountain pen. The weapon contains two small reservoirs, one for ink and the other for a poison. An assassin must unscrew the weapon and rotate the reservoirs to prepare the pen to deliver a dose of poison. The tip of the pen is then used to pierce the victim’s skin, and the contents of the poison reservoir are injected into the victim’s bloodstream. Most would-be assassins carry clean nibs that can be swapped out to replace those stained with blood, making identification that much harder for criminal investigators.","A character damaged by a poison pen is injected with the substance it contains. The pen holds one dose of poison.\n\n" +
                        "Refilling the pen's poison reservoir takes five minutes of careful labor. Rotating the pen’s reservoirs takes a full action.\n\n" +
                        "The pen can be used to deliver somnolence elixir as well as poisons.\n\n" +
                        "A character can discern a poison pen from a normal pen only by handling it and making a successful PER + Detection roll against a target number of 15. A roll of all 1s indicates the character accidentally nicked himself with the sharpened tip of the pen. He then suffers the effect of the poison contained within."},
                {"Rynnish Fan","20 gc","Hand Weapon","+1","1","The courts of Llael featured intrigue, literal and figurative backstabbing, and occasional bloodshed. The ladies of the court, ever on the lookout for any advantage, took to carrying all manner of concealed arms for protection as well as to punctuate their dramatic points. The Rynnish fan is one such tool. Favored by courtesans, assassins, and Llaelese noblewomen, the fan conceals a number of hidden blades that can be exposed with an easy flick of the wrist and concealed again just as easily.","The blades of a Rynnish fan are obvious to anyone who handles it. An observant character can discern a Rynnish fan from a normal fan without handling it with a successful PER + Detection roll against a target number of 17."},
                {"Sword, Dress","35+ gc","Hand Weapon","+1","1","Like its larger cousin the rapier, the dress sword is a slender, sharply pointed dueling sword developed in the courts of Llael. Kept polished to a bright sheen and accompanied by a fine sheath, which is sometimes ornamented, most dress swords have no edge and can be used only for thrusting attacks. This weapon is almost never seen on the battlefields of western Immoren and is usually worn as a fashion accessory or symbol by Cygnaran and Llaelese nobels or others trying to fit into high society. Nonetheless, these finely made swords are well balanced and deadly in the hands of a duelist.","A character can spend 1 feat point to boost both his attack and damage roll with this weapon."},
                {"Annihilator Blade","30 gc","Great Weapon","0","5","The primary weapon of the Man-O-War shocktroopers, the annihilator blade is effectively a heavy steel and brass halberd balanced for use one-handed.","A character must have at least STR 7 to use this weapon.\n\nThe annihilator blade has Reach."},
                {"Lance, Blasting","125 gc","Lance","0 (charging}, -2 (not charging)","10 (charging}, 4 (not charging)","The blasting lance is the signature weapon of the Iron Fang Uhlans. In addition to the speed and crushing weight of a rider and his steed driving the lance point forward, the weapon is further enhanced with an explosive spear point designed to rip through the armored hull of a warjack. The haft of the weapon is designed to retract for rearming the explosive head weapon without a rider ever leaving the saddle. The blasting lance is also features a thrusting spear-tip set into the pommel of the weapon for close-quarters fighting once the blasting head has been detonated. It is an awkward way to fight with the weapon but can make the difference between life and death in the field.", "A character must have at least STR 5 to use this weapon. This weapon can be used only while mounted.\n\n" +
                        "This weapon can make charge attacks only while its blasting head is armed and then only with its blasting head. When used to make a charge, a blasting lance has Reach. On a critical hit on a charge, the target is knocked down.\n\n" +
                        "Explosive blasting lance heads must be replaced after every charge attack.\n\n" +
                        "It takes a quick action to replace the blasting lance’s head. Explosive blasting lance head replacements each cost 1 gc."},
                {"Fellblade","These weapons are unavailable at any price and are closely guarded by the Greylords Covenant and its agents.","Great Weapon","-2 (one-handed}, 0 (two-handed)","6","These fearsome arcane weapons are relics left behind by the Orgoth. Gibbering, whispering, and murderous, fellblades literally speak to their wielders. Virtually all who wield a fellblade go mad but are invested with tireless and terrifying reserves of strength and fortitude. Over time these weapons wear down the sanity of their wielders, driving them to murderous rages while stripping them of the last shreds of their humanity. Though the Greylords are said to have knowledge of their creation, such lore is among the order’s greatest secrets.","A character must have at least STR 5 to use this weapon.\n\n" +
                        "The fellblade has Reach and is a magical weapon.\n\n" +
                        "While carrying a fellblade a character cannot cast spells, cannot be targeted by spells, and never suffers the effects of Terror.\n\n" +
                        "A living character in possession of a fellblade must make a Willpower roll against a target number of 16 once per day. If he succeeds, nothing happens. If he fails, his Willpower is reduced by 1. The character must make an additional roll immediately after any encounter in which he destroyed one or more characters.\n\n" +
                        "Additionally, any time a living character in possession of a fellblade attempts to spare the life of an enemy, he must make a Willpower roll against a target number of 16. If he fails, he must attempt to kill the enemy character.\n\n" +
                        "If a character’s Willpower is reduced to 0 as a result of his fellblade possession, he becomes a slave to the weapon and becomes an NPC under the Game Master’s control. The character becomes a living monster that exists to hunt and kill. The character drops all non-Mighty archetype benefits, quickly drops all other weapons and gear except for his fellblade, and gains the Abomination and Berserk abilities.\n\n" +
                        "If a character that has not yet become a slave to a fellblade is separated from the weapon, he regains 1 Willpower point for every full twenty-four hours he is separated from the weapon."},
                {"Staff, Orgoth","These weapons are never available for any price and are closely guarded by the Greylords Covenant and its agents.","Great Weapon","-2 (one-handed}, 0 (two-handed)","6","These dark artifacts are enchanted to give their wielder a degree of control over the gibbering, mad bearers of the fellblades. A relic of the Orgoth, these weapons are ancient and none alive know the secrets of their manufacture.\n\n" +
                        "Carved from a strange, deep-green stone or alien mineral, these staves are covered in howling and distorted faces that repeat their bearer’s words in a voice that resonates deeply with the bearers of fellblades.\n\n" +
                        "The Greylords Covenant takes great pains to secure all Orgoth staves, which are rare in the extreme. The majority were found in the mines beneath Khardov along with stashes of fellblades. These weapons exist virtually only in the hands of senior members of that order, who use them to control the Doom Reavers. Should a staff be discovered outside the order, it will be considered an imperative of the state that it be returned or otherwise secured.","The Orgoth staff has Reach and is a magical weapon.\n\n" +
                        "While carrying an Orgoth staff, a character cannot be targeted by spells (even if he is the caster and the spell’s target is Self) and never suffers the effects of Terror.\n\n" +
                        "A bearer of an Orgoth staff cannot be attacked by a character armed with a fellblade for any reason. Additionally, while within the command range of a character wielding an Orgoth staff, a character armed with a fellblade gains the Mighty archetype benefit Tough and the Silence ability.\n\n" +
                        "A character wielding an Orgoth staff can compel a character armed with a fellblade to follow any order with a successful STR + Command roll against a target number equal to the fellblade bearer’s Willpower. The fellblade bearer must follow the order to his best ability as long as he remains within the Orgoth staff wielder’s command range. If the fellblade bearer leaves the staff wielder’s command range, the effects of the order end."},
                {"Shield, Blast","45 gc","Shield","-1","1","This massive reinforced shield is designed to blunt the force of explosive blasts. Though sometimes used by the security forces of the cities of the Iron Kingdoms, the blast shield is most often found on the battlefield of western Immoren.","A character must have at least STR 6 to use this weapon.\n\n" +
                        "A character armed with a shield gains +1 ARM for each level of the Shield skill he has against attacks originating in his front arc. This bonus is not cumulative with additional shields.\n\n" +
                        "A character armed with a blast shield suffers –2 DEF.\n\n" +
                        "A character armed with a blast shield does not suffer blast damage origination in his front arc."},
                {"Clockwork Injector","50 gc","Hand Weapon","-2","––","The clockwork injector is a complex device consisting of a fragile tube terminating in a maw of sharp needles with four vials mounted on a gear-driven carousel. The wielder simply selects one of the vials and the device swiftly rotates the correct vial into position. The clockwork injector is far from a serviceable weapon, but it can quickly deliver a number of alchemical solutions.","Do not make damage rolls to resolve attacks made with this weapon. A living character hit by this weapon automatically suffers 1 damage point. This weapon cannot damage non-living characters.\n\n" +
                        "When a living character is damaged by this weapon, he is injected with the contents of the vial selected on the injector. If the vial is empty, nothing happens.\n\n" +
                        "Each vial can be loaded with one dose of an injectable alchemical substance. Changing a vial or rotating a fresh vial into use after an injection requires a quick action." +
                        "Before a vial can be reloaded, the entire mechanism must be broken down and thoroughly cleaned, a process requiring at least thirty minutes. Reloading a vial takes five minutes of careful labor."},
                {"Lacerator","These weapons are never available for sale.","Hand Weapon","-3","4","Lacerators are the wicked whips of chain favored by the cruel Satyxis raiders. The secrets of the lacerator’s creation are known only to the blood witches among them.","Lacerators are magical reach weapons.\n\nAttacks from lacerators ignore ARM bonuses from bucklers and shields.\n\nOn a critical hit, the character hit is knocked down.\n\nWhen a lacerator damages a bonded steamjack, the steamjack’s controller suffers 1 damage point."},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""},
                {"Name","","Hand Weapon","0","4","",""}};

        arrayList.clear();

        for (String[] ability1 : abilities) {
            MeleeWeapons ability = new MeleeWeapons(ability1[0], ability1[1], ability1[2], ability1[3], ability1[4], ability1[5], ability1[6]);
            arrayList.add(ability);
        }

        //Sorting
        Collections.sort(arrayList, new Comparator<MeleeWeapons>() {
            @Override
            public int compare(MeleeWeapons lhs, MeleeWeapons rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });

        final ListView listView = (ListView) view.findViewById(R.id.listView1);
        abilityAdapter = new MeleeWeaponsAdapter(getActivity(), arrayList);
        listView.setAdapter(abilityAdapter);



        final EditText myFilter = (EditText)view.findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = myFilter.getText().toString().toLowerCase(Locale.getDefault());
                abilityAdapter.filter(text);
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
