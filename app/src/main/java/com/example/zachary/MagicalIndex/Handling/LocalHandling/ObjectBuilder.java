package com.example.zachary.MagicalIndex.Handling.LocalHandling;

import android.app.Activity;

import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpellCount;
import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpell;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;

/**
 * Created by Zachary on 18-Jul-16.
 */
public class ObjectBuilder {

    Activity context;
    App App;
    String[] SpellName, SpellSchool, SpellLevel, SpellTime, SpellRange, SpellComponents,
            SpellDuration, SpellRitual, SpellConcentration, SpellDescription, SpellUsers;



    public ObjectBuilder (Activity con) {
        context = con;
        App = new App(context);
    }

    private void loadSpellLists(){
        SpellName = App.getContext().getResources().getStringArray(R.array.spell_name);
        SpellSchool = App.getContext().getResources().getStringArray(R.array.spell_school);
        SpellLevel = App.getContext().getResources().getStringArray(R.array.spell_level);
        SpellTime = App.getContext().getResources().getStringArray(R.array.spell_time);
        SpellRange = App.getContext().getResources().getStringArray(R.array.spell_range);
        SpellComponents = App.getContext().getResources().getStringArray(R.array.spell_components);
        SpellDuration = App.getContext().getResources().getStringArray(R.array.spell_duration);
        SpellRitual = App.getContext().getResources().getStringArray(R.array.spell_ritual);
        SpellConcentration = App.getContext().getResources().getStringArray(R.array.spell_concentration);
        SpellDescription = App.getContext().getResources().getStringArray(R.array.spell_description);
        SpellUsers = App.getContext().getResources().getStringArray(R.array.spell_users);
    }

    public ArrayList<DataSpell> SpellstoObject(){
        loadSpellLists();
        ArrayList<DataSpell> SpellList = new ArrayList<>();

        for (int i = 0; i < SpellName.length; i++) {
            DataSpell spell = new DataSpell();
            spell.setSpellName(SpellName[i]);
            spell.setSpellSchool(SpellSchool[i]);
            spell.setSpellLevel(SpellLevel[i]);
            spell.setSpellCastTime(SpellTime[i]);
            spell.setSpellRange(SpellRange[i]);
            spell.setSpellComponents(SpellComponents[i]);
            spell.setSpellDuration(SpellDuration[i]);
            if (SpellConcentration[i].equals("Yes")) {
                spell.setSpellConcentration(true);
            } else {
                spell.setSpellConcentration(false);
            }
            if (SpellRitual[i].equals("Yes")) {
                spell.setSpellRitual(true);
            } else {
                spell.setSpellRitual(false);
            }
            spell.setSpellDescription(SpellDescription[i]);

            spell.setSpellBard(SpellUsers[i].contains("Bard"));
            spell.setSpellCleric(SpellUsers[i].contains("Cleric"));
            spell.setSpellDruid(SpellUsers[i].contains("Druid"));
            spell.setSpellPaladin(SpellUsers[i].contains("Paladin"));
            spell.setSpellRanger(SpellUsers[i].contains("Ranger"));
            spell.setSpellSorcerer(SpellUsers[i].contains("Sorcerer"));
            spell.setSpellWarlock(SpellUsers[i].contains("Warlock"));
            spell.setSpellWizard(SpellUsers[i].contains("Wizard"));

            spell.setId(String.valueOf(i));
            SpellList.add(spell);
        }

        return SpellList;
    }


    public ArrayList<DataSpell> SpellstoObjectbyID(ArrayList<String> ids){
        loadSpellLists();
        ArrayList<DataSpell> SpellList = new ArrayList<>();

        for (int x = 0; x < ids.size(); x++) {
            int i = Integer.parseInt(ids.get(x));
            DataSpell spell = new DataSpell();
            spell.setSpellName(SpellName[i]);
            spell.setSpellSchool(SpellSchool[i]);
            spell.setSpellLevel(SpellLevel[i]);
            spell.setSpellCastTime(SpellTime[i]);
            spell.setSpellRange(SpellRange[i]);
            spell.setSpellComponents(SpellComponents[i]);
            spell.setSpellDuration(SpellDuration[i]);
            if (SpellConcentration[i].equals("Yes")) {
                spell.setSpellConcentration(true);
            } else {
                spell.setSpellConcentration(false);
            }
            if (SpellRitual[i].equals("Yes")) {
                spell.setSpellRitual(true);
            } else {
                spell.setSpellRitual(false);
            }
            spell.setSpellDescription(SpellDescription[i]);

            spell.setSpellBard(SpellUsers[i].contains("Bard"));
            spell.setSpellCleric(SpellUsers[i].contains("Cleric"));
            spell.setSpellDruid(SpellUsers[i].contains("Druid"));
            spell.setSpellPaladin(SpellUsers[i].contains("Paladin"));
            spell.setSpellRanger(SpellUsers[i].contains("Ranger"));
            spell.setSpellSorcerer(SpellUsers[i].contains("Sorcerer"));
            spell.setSpellWarlock(SpellUsers[i].contains("Warlock"));
            spell.setSpellWizard(SpellUsers[i].contains("Wizard"));

            spell.setId(String.valueOf(i));
            SpellList.add(spell);
        }

        return SpellList;
    }

    public DataSpell SpellbyID(String id){
        loadSpellLists();
        int i = Integer.parseInt(id);

        DataSpell spell = new DataSpell();
        spell.setSpellName(SpellName[i]);
        spell.setSpellSchool(SpellSchool[i]);
        spell.setSpellLevel(SpellLevel[i]);
        spell.setSpellCastTime(SpellTime[i]);
        spell.setSpellRange(SpellRange[i]);
        spell.setSpellComponents(SpellComponents[i]);
        spell.setSpellDuration(SpellDuration[i]);
        if (SpellConcentration[i].equals("Yes")) {
            spell.setSpellConcentration(true);
        } else {
            spell.setSpellConcentration(false);
        }
        if (SpellRitual[i].equals("Yes")) {
            spell.setSpellRitual(true);
        } else {
            spell.setSpellRitual(false);
        }
        spell.setSpellDescription(SpellDescription[i]);

        spell.setSpellBard(SpellUsers[i].contains("Bard"));
        spell.setSpellCleric(SpellUsers[i].contains("Cleric"));
        spell.setSpellDruid(SpellUsers[i].contains("Druid"));
        spell.setSpellPaladin(SpellUsers[i].contains("Paladin"));
        spell.setSpellRanger(SpellUsers[i].contains("Ranger"));
        spell.setSpellSorcerer(SpellUsers[i].contains("Sorcerer"));
        spell.setSpellWarlock(SpellUsers[i].contains("Warlock"));
        spell.setSpellWizard(SpellUsers[i].contains("Wizard"));

        spell.setId(String.valueOf(i));

        return spell;

    }

    // ---------------------------------------------------------------------------------------------

    public DataSpellCount getSpellCount(int character, int level, int mod){
        DataSpellCount ag;
        switch (character) {
            case 0:
                ag = setBard(mod, level);
                break;
            case 1:
                ag = setCleric(mod, level);
                break;
            case 2:
                ag = setDruid(mod, level);
                break;
            case 3:
                ag = setPaladin(mod, level);
                break;
            case 4:
                ag = setRanger(mod, level);
                break;
            case 5:
                ag = setSorcerer(mod, level);
                break;
            case 6:
                ag = setWarlock(mod, level);
                break;
            case 7:
                ag = setWizard(mod, level);
                break;
            default:
                ag = null;
                break;
        }
        return ag;
    }


    public DataSpellCount setBard(int m, int l) {

        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int[] Slots;
        int[][] SpellSlots = {
                {2,2,0,0,0,0,0,0,0,0},
                {2,3,0,0,0,0,0,0,0,0},
                {2,4,2,0,0,0,0,0,0,0},
                {3,4,3,0,0,0,0,0,0,0},
                {3,4,3,2,0,0,0,0,0,0},

                {3,4,3,3,0,0,0,0,0,0},
                {3,4,3,3,1,0,0,0,0,0},
                {3,4,3,3,2,0,0,0,0,0},
                {3,4,3,3,3,1,0,0,0,0},
                {3,4,3,3,3,2,0,0,0,0},

                {4,4,3,3,3,2,1,0,0,0},
                {4,4,3,3,3,2,1,0,0,0},
                {4,4,3,3,3,2,1,1,0,0},
                {4,4,3,3,3,2,1,1,0,0},
                {4,4,3,3,3,2,1,1,1,0},

                {4,4,3,3,3,2,1,1,1,0},
                {4,4,3,3,3,2,1,1,1,1},
                {4,4,3,3,3,3,1,1,1,1},
                {4,4,3,3,3,3,2,1,1,1},
                {4,4,3,3,3,3,2,2,1,1}
        };

        int[] learned = {4,5,6,7,8,9,10,11,12,14,15,15,16,18,19,19,20,22,22,22};
            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "Yes";
            Ritual = "Yes";
            Prepare = "Unlimited";
            Known = String.valueOf(learned[Level]);
            Slots = SpellSlots[Level];


        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots);
        return ag;
    }

    public DataSpellCount setCleric(int m, int l) {

        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int[] Slots;

        int[][] SpellSlots = {
                {3,2,0,0,0,0,0,0,0,0},
                {3,3,0,0,0,0,0,0,0,0},
                {3,4,2,0,0,0,0,0,0,0},
                {4,4,3,0,0,0,0,0,0,0},
                {4,4,3,2,0,0,0,0,0,0},

                {4,4,3,3,0,0,0,0,0,0},
                {4,4,3,3,1,0,0,0,0,0},
                {4,4,3,3,2,0,0,0,0,0},
                {4,4,3,3,3,1,0,0,0,0},
                {5,4,3,3,3,2,0,0,0,0},

                {5,4,3,3,3,2,1,0,0,0},
                {5,4,3,3,3,2,1,0,0,0},
                {5,4,3,3,3,2,1,1,0,0},
                {5,4,3,3,3,2,1,1,0,0},
                {5,4,3,3,3,2,1,1,1,0},

                {5,4,3,3,3,2,1,1,1,0},
                {5,4,3,3,3,2,1,1,1,1},
                {5,4,3,3,3,3,1,1,1,1},
                {5,4,3,3,3,3,2,1,1,1},
                {5,4,3,3,3,3,2,2,1,1}
        };

            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "Yes";
            Ritual = "If Prepared";
            int prep =  Level + 1 + Mod;
            if(prep < 1) {prep = 1;}
            Prepare = String.valueOf(prep);
            Known = "All";
            Slots = SpellSlots[Level];

        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots);
        return ag;
    }

    public DataSpellCount setDruid(int m, int l) {

        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int[] Slots;

        int[][] SpellSlots = {
                {2,2,0,0,0,0,0,0,0,0},
                {2,3,0,0,0,0,0,0,0,0},
                {2,4,2,0,0,0,0,0,0,0},
                {3,4,3,0,0,0,0,0,0,0},
                {3,4,3,2,0,0,0,0,0,0},

                {3,4,3,3,0,0,0,0,0,0},
                {3,4,3,3,1,0,0,0,0,0},
                {3,4,3,3,2,0,0,0,0,0},
                {3,4,3,3,3,1,0,0,0,0},
                {4,4,3,3,3,2,0,0,0,0},

                {4,4,3,3,3,2,1,0,0,0},
                {4,4,3,3,3,2,1,0,0,0},
                {4,4,3,3,3,2,1,1,0,0},
                {4,4,3,3,3,2,1,1,0,0},
                {4,4,3,3,3,2,1,1,1,0},

                {4,4,3,3,3,2,1,1,1,0},
                {4,4,3,3,3,2,1,1,1,1},
                {4,4,3,3,3,3,1,1,1,1},
                {4,4,3,3,3,3,2,1,1,1},
                {4,4,3,3,3,3,2,2,1,1}
        };

            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "Yes";
            Ritual = "If Prepared";
            Prepare = String.valueOf(Level + Mod);
            Known = "All";
            Slots = SpellSlots[Level];

        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots);
        return ag;
    }

    public DataSpellCount setPaladin(int m, int l) {

        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int[] Slots;

        int[][] SpellSlots = {
                {0,0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0,0},
                {0,4,2,0,0,0,0,0,0,0},

                {0,4,2,0,0,0,0,0,0,0},
                {0,4,3,0,0,0,0,0,0,0},
                {0,4,3,0,0,0,0,0,0,0},
                {0,4,3,2,0,0,0,0,0,0},
                {0,4,3,2,0,0,0,0,0,0},

                {0,4,3,3,0,0,0,0,0,0},
                {0,4,3,3,0,0,0,0,0,0},
                {0,4,3,3,1,0,0,0,0,0},
                {0,4,3,3,1,0,0,0,0,0},
                {0,4,3,3,2,0,0,0,0,0},

                {0,4,3,3,2,0,0,0,0,0},
                {0,4,3,3,2,1,0,0,0,0},
                {0,4,3,2,2,1,0,0,0,0},
                {0,4,3,2,2,2,0,0,0,0},
                {0,4,3,2,2,2,0,0,0,0}
        };

            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "Yes";
            Ritual = "No";
            Prepare = String.valueOf((Level/2) + Mod);
            Known = "All";
            Slots = SpellSlots[Level];

        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots);
        return ag;
    }

    public DataSpellCount setRanger(int m, int l) {

        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int[] Slots;

        int[][] SpellSlots = {
                {0,0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0,0},
                {0,3,0,0,0,0,0,0,0,0},
                {0,4,2,0,0,0,0,0,0,0},

                {0,4,2,0,0,0,0,0,0,0},
                {0,4,3,0,0,0,0,0,0,0},
                {0,4,3,0,0,0,0,0,0,0},
                {0,4,3,2,0,0,0,0,0,0},
                {0,4,3,2,0,0,0,0,0,0},

                {0,4,3,3,0,0,0,0,0,0},
                {0,4,3,3,0,0,0,0,0,0},
                {0,4,3,3,1,0,0,0,0,0},
                {0,4,3,3,1,0,0,0,0,0},
                {0,4,3,3,2,0,0,0,0,0},

                {0,4,3,3,2,0,0,0,0,0},
                {0,4,3,3,2,1,0,0,0,0},
                {0,4,3,2,2,1,0,0,0,0},
                {0,4,3,2,2,2,0,0,0,0},
                {0,4,3,2,2,2,0,0,0,0}
        };

        int[] learned = {0,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11};

            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "No";
            Ritual = "No";
            Prepare = "No";
            Known = String.valueOf(learned[Level]);
            Slots = SpellSlots[Level];

        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots);
        return ag;
    }

    public DataSpellCount setSorcerer(int m, int l) {

        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int[] Slots;

        int[][] SpellSlots = {
                {4,2,0,0,0,0,0,0,0,0},
                {4,3,0,0,0,0,0,0,0,0},
                {4,4,2,0,0,0,0,0,0,0},
                {5,4,2,0,0,0,0,0,0,0},
                {5,4,3,2,0,0,0,0,0,0},

                {5,4,3,3,0,0,0,0,0,0},
                {5,4,3,3,1,0,0,0,0,0},
                {5,4,3,3,2,0,0,0,0,0},
                {5,4,3,3,3,1,0,0,0,0},
                {6,4,3,3,3,2,0,0,0,0},

                {6,4,3,3,3,2,1,0,0,0},
                {6,4,3,3,3,2,1,0,0,0},
                {6,4,3,3,3,2,1,1,0,0},
                {6,4,3,3,3,2,1,1,0,0},
                {6,4,3,3,3,2,1,1,1,0},

                {6,4,3,3,3,2,1,1,1,0},
                {6,4,3,3,3,2,1,1,1,1},
                {6,4,3,3,3,3,1,1,1,1},
                {6,4,3,3,3,3,2,1,1,1},
                {6,4,3,3,3,3,2,2,1,1}
        };

        int[] learned = {2,3,4,5,6,7,8,9,10,11,12,12,13,13,14,14,15,15,15,15};

            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "Yes";
            Ritual = "No";
            Prepare = "No";
            Known = String.valueOf(learned[Level]);
            Slots = SpellSlots[Level];

        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots);
        return ag;
    }

    public DataSpellCount setWarlock(int m, int l) {

        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int[] Slots;

        // TODO - Reformat SpellSlots
        int[][] SpellSlots = {
                {2,1,0,0,0,0,0,0,0,0},
                {2,2,0,0,0,0,0,0,0,0},
                {2,0,2,0,0,0,0,0,0,0},
                {3,0,2,0,0,0,0,0,0,0},
                {3,0,0,2,0,0,0,0,0,0},

                {3,0,0,2,0,0,0,0,0,0},
                {3,0,0,0,2,0,0,0,0,0},
                {3,0,0,0,2,0,0,0,0,0},
                {3,0,0,0,0,2,0,0,0,0},
                {4,0,0,0,0,2,0,0,0,0},

                {4,0,0,0,0,3,0,0,0,0},
                {4,0,0,0,0,3,0,0,0,0},
                {4,0,0,0,0,3,0,0,0,0},
                {4,0,0,0,0,3,0,0,0,0},
                {4,0,0,0,0,3,0,0,0,0},

                {4,0,0,0,0,3,0,0,0,0},
                {4,0,0,0,0,4,0,0,0,0},
                {4,0,0,0,0,4,0,0,0,0},
                {4,0,0,0,0,4,0,0,0,0},
                {4,0,0,0,0,4,0,0,0,0}
        };

        int[] SlotLevel =   {1,1,2,2,3,3,4,4,5,5,5,5,5,5,5,5,5,5,5,5};
        int[] Invocations = {0,2,2,2,3,3,4,4,5,5,5,6,6,6,7,7,7,8,8,8};
        int[] learned =     {2,3,4,5,6,7,8,9,10,10,11,11,12,12,13,13,14,14,15,15};

            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "Yes";
            Ritual = "No";
            Prepare = "No";
            Known = String.valueOf(learned[Level]);
            Slots = SpellSlots[Level];


        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots, SlotLevel[Level], Invocations[Level]);
        return ag;
    }

    public DataSpellCount setWizard(int m, int l) {

        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int Slots[];

        int[][] SpellSlots = {
                {3,2,0,0,0,0,0,0,0,0},
                {3,3,0,0,0,0,0,0,0,0},
                {3,4,2,0,0,0,0,0,0,0},
                {4,4,3,0,0,0,0,0,0,0},
                {4,4,3,2,0,0,0,0,0,0},

                {4,4,3,3,0,0,0,0,0,0},
                {4,4,3,3,1,0,0,0,0,0},
                {4,4,3,3,2,0,0,0,0,0},
                {4,4,3,3,3,1,0,0,0,0},
                {5,4,3,3,3,2,0,0,0,0},

                {5,4,3,3,3,2,1,0,0,0},
                {5,4,3,3,3,2,1,0,0,0},
                {5,4,3,3,3,2,1,1,0,0},
                {5,4,3,3,3,2,1,1,0,0},
                {5,4,3,3,3,2,1,1,1,0},

                {5,4,3,3,3,2,1,1,1,0},
                {5,4,3,3,3,2,1,1,1,1},
                {5,4,3,3,3,3,1,1,1,1},
                {5,4,3,3,3,3,2,1,1,1},
                {5,4,3,3,3,3,2,2,1,1}
        };

            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "Yes";
            Ritual = "If Known";
            Prepare = String.valueOf(Level + Mod);
            Known = String.valueOf(4 + (2*Level));
            Slots = SpellSlots[Level];

        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots);
        return ag;
    }

    public DataSpellCount TEMPLATE(int m, int l){
        String Focus, Ritual, Prepare, Known;
        int Level, Mod, Prof, Save, Attack;
        int Slots[];

        int[][] SpellSlots = {
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {},
                {}
        };

        int[] learned = {};

            Mod = m;
            Level = l - 1;
            Prof = 2 + ((int) Math.floor(Level / 4));
            Attack = Prof + Mod;
            Save = Prof + Mod + 8;
            Focus = "";
            Ritual = "";
            Prepare = "";
            Prepare = String.valueOf(Level + Mod);
            Known = "All";
            Known = String.valueOf(learned[Level]);
            Slots = SpellSlots[Level];


        DataSpellCount ag = new DataSpellCount(Attack, Save, Focus, Ritual, Prepare, Known, Slots);
        return ag;
    }

}
