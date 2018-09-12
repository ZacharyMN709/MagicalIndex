package com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zachary.MagicalIndex.Handling.CustomAdapters.RecyclerAdapter.CustomAdapterSpellList;
import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpell;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.NavigationController;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.SpellSearch;
import com.example.zachary.MagicalIndex.Main.Fragments.PopUps.PopUpAddSpell;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;

/**
 * Created by Zachary on 03-May-17.
 */
public class DrawerSpells extends Fragment implements CustomAdapterSpellList.AddSpellPass{

    ArrayList<DataSpell> Spells = new ArrayList<>();

    SpellSearch SpellSearch;
    String Search, Refine;
    Activity context;
    View view;
    com.example.zachary.MagicalIndex.Handling.LocalHandling.App App;

    public DrawerSpells(){ }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.viewtemplate_list_recyclerview, container, false);
        context = getActivity();
        App = new App(context);
        App.hideListFAB(view);
        Bundle bundle = getArguments();
        Bundle args = bundle.getBundle("Bundle");
        Search = args.getString("Search", "ALL");
        Refine = args.getString("Refine", "ALL");

        SpellSearch = new SpellSearch(context);

        if ((!Refine.equals(Search))) {
            SpellSearch ss = new SpellSearch(context, SpellSearch.SearchSpells(Refine));
            Spells = ss.SearchSpells(Search);
        } else {
            Spells = SpellSearch.SearchSpells(Search);
        }

        refreshAdapter();
        return view;
    }

    @Override
    public void onSpellListPass(String id, String name) {
        App.createPopUp(new PopUpAddSpell(), "SingleSpellbookAdd", NavigationController.AddSpell(id, name), getActivity());
    }


    @Override
    public void onResume () {
        super.onResume();
        refreshAdapter();
    }

    public void refreshAdapter() {
        App.refreshLinearAdapter(view, context, new CustomAdapterSpellList(context, Spells, null, this));
    }

}
