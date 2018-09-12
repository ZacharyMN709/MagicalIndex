package com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens;

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

public class DrawerSpellbooks extends Fragment {

    ArrayList <String> Spellbooks = new ArrayList<>();
    ArrayList <Integer> SpellbookIcons = new ArrayList<>();
    View view;
    Activity context;
    App App;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.viewtemplate_list_recyclerview, container, false);
        context = getActivity();
        App = new App(context);

        createLists();

        return view;
    }

    public void createLists() {
        if (Spellbooks != null) {Spellbooks.clear();}
        if (SpellbookIcons != null) {SpellbookIcons.clear();}
        Spellbooks = App.getSpellbooks();
        for(int x = 0; x < Spellbooks.size(); x++) {
            SpellbookIcons.add(App.getSpellbookIcon(Spellbooks.get(x)));
        }
    }

    @Override
    public void onResume () {
        super.onResume();
        createLists();
        refreshAdapter();
    }

    public void refreshAdapter() {
        App.refreshGridAdapter(view, context, 2, new CustomAdapterSpellbooks(context, Spellbooks, SpellbookIcons, null, null));
    }

}


