package com.example.zachary.MagicalIndex.Main.Fragments.PopUps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zachary.MagicalIndex.Handling.CustomAdapters.RecyclerAdapter.CustomAdapterSpellbooks;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;

public class PopUpAddSpell extends Fragment implements View.OnClickListener{


    App App;
    ArrayList<String> Spellbooks;
    ArrayList<Integer> SpellbookIcons = new ArrayList<>();
    String Spell, SpellName;
    Activity context;
    View view;
    LayoutInflater inflater;
    ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        context = getActivity();
        App = new App(context);
        Bundle bundle = getArguments();
        Bundle args = bundle.getBundle("Bundle");
        Spell = args.getString("Spell", null);
        SpellName = args.getString("SpellName", null);


        createLists();
        setLayout();
        return view;
    }

    public void createLists() {
        if (Spellbooks != null) {Spellbooks.clear();}
        if (SpellbookIcons != null) {SpellbookIcons.clear();}
        Spellbooks = App.getSpellbooks();
        for(int x = 0; x < Spellbooks.size(); x++) { SpellbookIcons.add(App.getSpellbookIcon(Spellbooks.get(x))); }
    }

    public void setLayout() {
        view = inflater.inflate(R.layout.popup_addspell, container, false);
        view.findViewById(R.id.shadow).setOnClickListener(this);
        App.hideListFAB(view);
        App.refreshGridAdapter(view, context, 2, new CustomAdapterSpellbooks(context, Spellbooks, SpellbookIcons, Spell, SpellName));
    }


    public void onClick(View v) {
        context.onBackPressed();
    }

}

