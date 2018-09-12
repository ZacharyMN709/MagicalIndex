package com.example.zachary.MagicalIndex.Handling.LocalHandling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.zachary.MagicalIndex.Main.FullSpell;
import com.example.zachary.MagicalIndex.Main.Spellbook;
import com.example.zachary.MagicalIndex.Main.Start.StartScreen;

/**
 * Created by Zachary on 19-Jul-16.
 */
public class NavigationController {

    public static void toFullSpell (Activity context, String spellid, String spellname) {
        Intent fullspell = new Intent(context, FullSpell.class);
        fullspell.putExtra("SpellID", spellid);
        fullspell.putExtra("SpellName", spellname);
        context.startActivity(fullspell);
    }

    public static void toFillSpellbook(Activity context, String name, boolean first) {
        Intent spellbook = new Intent(context, Spellbook.class);
        spellbook.putExtra("Spellbook", name);
        spellbook.putExtra("SetUp", first);
        context.startActivity(spellbook);
    }

    public static void toStartScreen(Activity context) {
        Intent logout = new Intent(context, StartScreen.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.finish();
        context.startActivity(logout);
    }

    //---------------------------------------------------------------------------------------------

    public static Bundle SpellSearch(String search, String refine) {
        Bundle args = new Bundle();
        args.putString("Search", search);
        args.putString("Refine", refine);
        return args;
    }

    public static Bundle AddSpell(String id, String name) {
        Bundle args = new Bundle();
        args.putString("Spell", id);
        args.putString("SpellName", name);
        return args;
    }

}
