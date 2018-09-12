package com.example.zachary.MagicalIndex.Main.Fragments.PopUps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.zachary.MagicalIndex.Handling.CustomAdapters.BaseAdapter.CustomAdapterMiniWireframeItems;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.NavigationController;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;

public class PopUpNewSpellbook extends Fragment implements View.OnClickListener, CustomAdapterMiniWireframeItems.IconPass {


    App App;
    ArrayList<String> Spellbooks;
    String Spellbook;
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
        setLayout();
        Spellbooks = App.getSpellbooks();
        return view;
    }

    public void setLayout() {
        view = inflater.inflate(R.layout.popup_newspellbook, container, false);
        view.findViewById(R.id.shadow).setOnClickListener(this);
        view.findViewById(R.id.confirm).setOnClickListener(this);
        view.findViewById(R.id.shadow2).setOnClickListener(this);
        App.hideListFAB(view);
        GridView catlist = (GridView) view.findViewById(R.id.viewList);
        catlist.setAdapter(new CustomAdapterMiniWireframeItems(getActivity(), this));
    }


    public void onClick(View v) {
        if (v.getId() == R.id.confirm) {
            App.hideSoftKeyboard(context);
            EditText tv = (EditText) view.findViewById(R.id.etNewSpellbook);
            Spellbook = tv.getText().toString();
            if (Spellbooks.indexOf(Spellbook) == -1) {
                RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.shadow2);
                rl.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
                return;
            } else {
                Toast.makeText(context, "Error! Spellbook already exists.", Toast.LENGTH_SHORT).show();
                tv.setText("");
                return;
            }
        }
        App.hideSoftKeyboard(context);
        context.onBackPressed();
    }

    @Override
    public void onDataPass(int data) {
        Spellbooks.add(0, Spellbook);
        App.setSpellbooks(Spellbooks);
        App.setSpellbookIcon(Spellbook, data);
        App.hideSoftKeyboard(context);
        context.getFragmentManager().popBackStack();
        NavigationController.toFillSpellbook(context, Spellbook, true);
    }
}

