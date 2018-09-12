package com.example.zachary.MagicalIndex.Handling.CustomAdapters.RecyclerAdapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.NavigationController;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;

/**
 * Created by Zachary on 03-May-17.
 */
public class CustomAdapterSpellbooks extends RecyclerView.Adapter<CustomAdapterSpellbooks.Holder>{

    ArrayList<String> Spellbooks;
    ArrayList<Integer> SpellbookIcons;
    String Spell, SpellName;
    Activity context;
    App App;

    public CustomAdapterSpellbooks(Activity callingclass, ArrayList<String> spellbooks, ArrayList<Integer> spellicons, String spell, String spellname) {
        Spellbooks      = spellbooks;
        SpellbookIcons  = spellicons;
        Spell           = spell;
        SpellName       = spellname;
        context         = callingclass;
        App             = new App(context);
    }

    @Override
    public int getItemCount()  {
        return Spellbooks.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView name;
        public ImageView image;
        public RelativeLayout relativelayout;

        public Holder(View rowView) {
            super(rowView);

            name = (TextView) rowView.findViewById(R.id.tvWireText);
            image = (ImageView) rowView.findViewById(R.id.ivWireImage);
            relativelayout = (RelativeLayout) rowView.findViewById(R.id.HOLDER);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView;
        if (Spell != null) {
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wireframe_mini, parent, false);
        } else {
            rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wireframe, parent, false);
        }

        return new Holder(rowView);
    }

    @Override
    public void onBindViewHolder(Holder tempholder, int itemnum) {
        final Holder holder = tempholder;
        final int position = itemnum;

        holder.name.setText(Spellbooks.get(itemnum));
        holder.image.setImageResource(SpellbookIcons.get(itemnum));

        holder.relativelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Spell != null) {
                    String Spellbook = Spellbooks.get(position);
                    ArrayList<String> SpellbookSpells = App.getDataSet(Spellbook);
                    if ((SpellbookSpells.indexOf(Spell) == -1)) {
                        SpellbookSpells.add(Spell);
                        App.setDataSet(SpellbookSpells, Spellbook);
                        Toast.makeText(context, "Added " + SpellName + " to " + Spellbook, Toast.LENGTH_SHORT).show();
                        context.onBackPressed();
                    } else {
                        Toast.makeText(context, SpellName + " already exists in " + Spellbook + "!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    toSpellbookOpen(Spellbooks.get(position));
                }

            }
        });
    }

    public void toSpellbookOpen(String spellbook) {
        NavigationController.toFillSpellbook(context, spellbook, false);
    }

}

