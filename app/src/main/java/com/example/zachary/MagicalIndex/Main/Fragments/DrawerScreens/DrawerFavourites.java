package com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zachary.MagicalIndex.Handling.CustomAdapters.RecyclerAdapter.CustomAdapterSpellFavourites;
import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpell;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.NavigationController;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.ObjectBuilder;
import com.example.zachary.MagicalIndex.Main.Fragments.PopUps.PopUpAddSpell;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;

public class DrawerFavourites extends Fragment implements CustomAdapterSpellFavourites.UnfavouritedPass, CustomAdapterSpellFavourites.AddSpellPass {

    ArrayList <DataSpell> SpellList = new ArrayList<>();
    View view;
    Activity context;
    App App;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.viewtemplate_list_recyclerview, container, false);
        context = getActivity();
        App = new App(context);
        App.hideListFAB(view);

        createLists();
        return view;
    }

    @Override
    public void onResume () {
        super.onResume();
        createLists();
        refreshAdapter();
    }

    public void createLists() {
        ArrayList<String> Favourites = App.getFavouriteSpells();
        SpellList.clear();
        ObjectBuilder ob = new ObjectBuilder(context);
        SpellList = ob.SpellstoObjectbyID(Favourites);
        refreshAdapter();
    }

    @Override
    public void onDataPass(String ID) {
        updateAdapter(ID);
    }

    public void updateAdapter(String ID) {
        ArrayList<String> AdIDList = new ArrayList();
        for (int x = 0; x < SpellList.size(); x++) {
            AdIDList.add(SpellList.get(x).getId());
        }
        int i = AdIDList.indexOf(ID);
        SpellList.remove(i);
        refreshAdapter();
    }

    public void refreshAdapter() {
        App.refreshLinearAdapter(view, context, new CustomAdapterSpellFavourites(context, SpellList, true, this, this));
    }

    @Override
    public void onSpellFavouritePass(String id, String name) {
        App.createPopUp(new PopUpAddSpell(), "SingleSpellbookAdd", NavigationController.AddSpell(id, name), getActivity());
    }
}

