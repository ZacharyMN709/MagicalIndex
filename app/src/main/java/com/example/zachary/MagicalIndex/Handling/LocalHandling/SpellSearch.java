package com.example.zachary.MagicalIndex.Handling.LocalHandling;

import android.app.Activity;
import android.util.Log;

import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpell;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zachary on 03-May-17.
 */
public class SpellSearch {

    Activity context;
    ObjectBuilder Build;
    App App;
    boolean fresh;

    ArrayList<DataSpell> Spells, SortedSpells;
    String[] SpellName, SpellNamebyLevel, SpellSchool, SpellLevel, Characters, Schools;
    ArrayList<String> CharacterList, SchoolList, SpellNameList;
    String alpha, num;

    public SpellSearch(Activity con) {
        context = con;
        App = new App(context);
        Build = new ObjectBuilder(context);
        Spells = Build.SpellstoObject();
        SortedSpells = new ArrayList<>();
        fresh = true;
        setup();
    }

    public SpellSearch(Activity con, ArrayList spells) {
        context = con;
        App = new App(context);
        Spells = spells;
        SortedSpells = new ArrayList<>();
        fresh = false;
        setup();
    }

    public void setup(){
        SpellName = App.getContext().getResources().getStringArray(R.array.spell_name);
        SpellNameList = new ArrayList<>(Arrays.asList(SpellName));
        SpellNamebyLevel = App.getContext().getResources().getStringArray(R.array.spell_name2);
        SpellSchool = App.getContext().getResources().getStringArray(R.array.spell_school);
        SpellLevel = App.getContext().getResources().getStringArray(R.array.spell_level);
        Characters = App.getContext().getResources().getStringArray(R.array.characters);
        CharacterList = new ArrayList<>(Arrays.asList(Characters));
        Schools = App.getContext().getResources().getStringArray(R.array.schools);
        SchoolList = new ArrayList<>(Arrays.asList(Schools));
        alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklnopqrstuvwxyz";
        num = "0123456789";
    }

    public ArrayList<DataSpell> SearchSpells(String search){
        Log.e("SearchSpells: ", search);

        if(search == null) {search = "ALL";}
        if(search.toLowerCase().equals("cantrip") || search.toLowerCase().equals("cantrips")) {search = "0";}
        if(search.equals("ALL")) {
            Log.e("SearchSpells: ", "Search All");
            SortedSpells = byName();
        } else if(search.equals("LEVEL")) {
            Log.e("SearchSpells: ", "Search Level");
            SortedSpells = byLevel();
        } else if(search.equals("RITUAL")) {
            Log.e("SearchSpells: ", "Search Ritual");
            SortedSpells = byRitual();
        } else if(search.equals("CONCENTRATION") || search.equals("FOCUS")) {
            Log.e("SearchSpells: ", "Search Concentration");
            SortedSpells = byConcentration();
        } else if(search.length() == 1) {
            if(alpha.contains(search)){
                Log.e("SearchSpells: ", "Search Alpha");
                // Show spells that start with ""
                SortedSpells = byLetter(search);
            } else if(num.contains(search)) {
                Log.e("SearchSpells: ", "Search Num");
                // Show all spells of level x
                SortedSpells = byLevel(search);
            } else {
                Log.e("SearchSpells: ", "Search Search");
                // Search normally
                SortedSpells = bySearch(search);
            }
        } else if(CharacterList.indexOf(search) != -1) {
            Log.e("SearchSpells: ", "Search Class");
            // Show only spells for that class
            SortedSpells = byClass(search);
        } else if(SchoolList.indexOf(search) != -1) {
            Log.e("SearchSpells: ", "Search School");
            // Show only spells for that class
            SortedSpells = bySchool(search);
        } else {
            Log.e("SearchSpells: ", "Search Search");
            // Search normally
            SortedSpells = bySearch(search);
        }
        return SortedSpells;
    }

    public ArrayList<DataSpell> byLevel(String search){
        for(int i = 0; i < Spells.size(); i++){
            if(search.equals(Spells.get(i).getSpellLevel())){SortedSpells.add(Spells.get(i));}}
        return SortedSpells;
    }

    public ArrayList<DataSpell> byLetter(String search){
        for(int i = 0; i < Spells.size(); i++){
            if(Spells.get(i).getSpellName().charAt(0) == search.toUpperCase().charAt(0)){SortedSpells.add(Spells.get(i));}}
        return SortedSpells;
    }

    public ArrayList<DataSpell> byClass(String search){
        if(fresh){
        SortedSpells = byLevel();
        Spells.clear();
        Spells.addAll(SortedSpells);
        SortedSpells.clear();
        }
        switch (search) {
            case "Bard":
                for(int i = 0; i < Spells.size(); i++){
                    if(Spells.get(i).isSpellBard()){ SortedSpells.add(Spells.get(i)); }}
                break;
            case "Cleric":
                for(int i = 0; i < Spells.size(); i++){
                    if(Spells.get(i).isSpellCleric()){ SortedSpells.add(Spells.get(i)); }}
                break;
            case "Druid":
                for(int i = 0; i < Spells.size(); i++){
                    if(Spells.get(i).isSpellDruid()){ SortedSpells.add(Spells.get(i)); }}
                break;
            case "Paladin":
                for(int i = 0; i < Spells.size(); i++){
                    if(Spells.get(i).isSpellPaladin()){ SortedSpells.add(Spells.get(i)); }}
                break;
            case "Ranger":
                for(int i = 0; i < Spells.size(); i++){
                    if(Spells.get(i).isSpellRanger()){ SortedSpells.add(Spells.get(i)); }}
                break;
            case "Sorcerer":
                for(int i = 0; i < Spells.size(); i++){
                    if(Spells.get(i).isSpellSorcerer()){ SortedSpells.add(Spells.get(i)); }}
                break;
            case "Warlock":
                for(int i = 0; i < Spells.size(); i++){
                    if(Spells.get(i).isSpellWarlock()){ SortedSpells.add(Spells.get(i)); }}
                break;
            case "Wizard":
                for(int i = 0; i < Spells.size(); i++){
                    if(Spells.get(i).isSpellWizard()){ SortedSpells.add(Spells.get(i)); }}
                break;
        }
        return SortedSpells;
    }

    public ArrayList<DataSpell> bySchool(String search){
        SortedSpells = byLevel();
        Spells.clear();
        Spells.addAll(SortedSpells);
        SortedSpells.clear();

        for(int i = 0; i < Spells.size(); i++){
            if(Spells.get(i).getSpellSchool().equals(search)){SortedSpells.add(Spells.get(i)); }}

        return SortedSpells;
    }

    public ArrayList<DataSpell> byRitual (){
        for(int i = 0; i < Spells.size(); i++){
            if(Spells.get(i).isSpellRitual()){SortedSpells.add(Spells.get(i)); }}

        return SortedSpells;
    }

    public ArrayList<DataSpell> byConcentration (){
        for(int i = 0; i < Spells.size(); i++){
            if(Spells.get(i).isSpellConcentration()){SortedSpells.add(Spells.get(i)); }}

        return SortedSpells;
    }

    public ArrayList<DataSpell> bySearch(String search){
        for(int i = 0; i < Spells.size(); i++){
            if(Spells.get(i).getSpellName().toLowerCase().contains(search.toLowerCase())){
                SortedSpells.add(Spells.get(i));
            }
        }
        return SortedSpells;
    }

    public ArrayList<DataSpell> byName(){
        return Spells;
    }

    public ArrayList<DataSpell> byLevel(){
        for(int i = 0; i < SpellNamebyLevel.length; i++){
            int x = SpellNameList.indexOf(SpellNamebyLevel[i]);
            SortedSpells.add(Spells.get(x));
        }
        return SortedSpells;
    }

    public ArrayList<DataSpell> sortByName(){
        for(int i = 0; i < SpellName.length; i++){

        }
        return SortedSpells;
    }

    public ArrayList<DataSpell> sortByLevel(){
        int x = 0;
        for(int i = 0; i < SpellNamebyLevel.length; i++){
            if(SpellNamebyLevel[i].equals(Spells.get(x).getSpellName())){
                SortedSpells.add(Spells.get(x));
                x = x + 1;
            }
        }
        return SortedSpells;
    }
}
