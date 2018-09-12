package com.example.zachary.MagicalIndex.Handling.CustomAdapters.RecyclerAdapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpell;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.NavigationController;
import com.example.zachary.MagicalIndex.R;


import java.util.ArrayList;

/**
 * Created by Zachary on 03-May-17.
 */
public class CustomAdapterSpellList extends RecyclerView.Adapter<CustomAdapterSpellList.Holder>{
    ArrayList<DataSpell> dataSpells;
    ArrayList<String> Favourites, SpellbookSpells;
    CustomAdapterSpellList.AddSpellPass dataPasser;
    String Spellbook;
    Activity context;
    App App;

    public CustomAdapterSpellList(Activity callingclass, ArrayList<DataSpell> dataadlist, String spellbook, CustomAdapterSpellList.AddSpellPass listener) {
        dataPasser   = listener;
        Spellbook    = spellbook;
        dataSpells   = dataadlist;
        context      = callingclass;
        App          = new App(context);
        Favourites   = App.getFavouriteSpells();
        if (Spellbook != null) {
            SpellbookSpells = App.getDataSet(Spellbook);
        }
    }


    public interface AddSpellPass {
        void onSpellListPass(String id, String name);
    }

    @Override
    public int getItemCount()  {
        return dataSpells.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView name, school, level, time, concentration, ritual;
        public ImageView image, favourite;
        public RelativeLayout nameholder, levelholder;
        public LinearLayout textholder;

        public Holder(View rowView) {
            super(rowView);
            name = (TextView) rowView.findViewById(R.id.tvSpellName);
            school = (TextView) rowView.findViewById(R.id.tvSpellSchool);
            level = (TextView) rowView.findViewById(R.id.tvSpellLevel);
            time = (TextView) rowView.findViewById(R.id.tvCastingTime);
            concentration = (TextView) rowView.findViewById(R.id.tvConcentration);
            ritual = (TextView) rowView.findViewById(R.id.tvRitual);
            image = (ImageView) rowView.findViewById(R.id.ivButton);
            favourite = (ImageView) rowView.findViewById(R.id.ivFavourite);
            nameholder = (RelativeLayout) rowView.findViewById(R.id.NAMEHOLDER);
            levelholder = (RelativeLayout) rowView.findViewById(R.id.LEVELHOLDER);
            textholder = (LinearLayout) rowView.findViewById(R.id.TEXTHOLDER);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spellpreview, parent, false);
        return new Holder(rowView);
    }

    @Override
    public void onBindViewHolder(Holder tempholder, int itemnum) {
        final Holder holder = tempholder;
        final int position = itemnum;

        final DataSpell Spell = dataSpells.get(holder.getAdapterPosition());
        final String id = Spell.getId();

        holder.name.setText(Spell.getSpellName());
        holder.school.setText(Spell.getSpellSchool());
        holder.level.setText(Spell.getSpellLevel());
        holder.time.setText(Spell.getSpellCastTime());

        if(Spell.isSpellConcentration()){holder.concentration.setText("Yes");
        } else {holder.concentration.setText("No");}
        if(Spell.isSpellRitual()) {holder.ritual.setText("Yes");
        } else {holder.ritual.setText("No");}


        if (favourited(Spell.getId())) { holder.favourite.setImageResource(R.drawable.ic_favorite_white_24dp);}
        else {holder.favourite.setImageResource(R.drawable.ic_favorite_border_white_24dp);}

        if (Spellbook != null) {
            if (inspellbook(Spell.getId())) { holder.image.setImageResource(R.drawable.ic_spellbook_open);}
            else {holder.image.setImageResource(R.drawable.ic_spellbook);}
        }

        holder.nameholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationController.toFullSpell(context, id, Spell.getSpellName());
            }
        });
        holder.levelholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationController.toFullSpell(context, id, Spell.getSpellName());
            }
        });
        holder.textholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavigationController.toFullSpell(context, id, Spell.getSpellName());
            }
        });

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchfavourites(holder, id);
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Spellbook == null){
                    dataPasser.onSpellListPass(id, Spell.getSpellName());
                } else {
                    rewritespellbook(holder, id);
                }
            }
        });

    }


    public void switchfavourites(Holder holder, String spellid) {
        if (!favourited(spellid)) {
            holder.favourite.setImageResource(R.drawable.ic_favorite_white_24dp);
            Favourites.add(spellid);
        } else {
            holder.favourite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
            Favourites.remove(Favourites.indexOf(spellid));
        }
        App.setFavouriteSpells(Favourites);
    }

    public void rewritespellbook(Holder holder, String spellid) {
        if (!inspellbook(spellid)) {
            holder.image.setImageResource(R.drawable.ic_spellbook_open);
            SpellbookSpells.add(spellid);
        } else {
            holder.image.setImageResource(R.drawable.ic_spellbook);
            SpellbookSpells.remove(SpellbookSpells.indexOf(spellid));
        }
        App.setDataSet(SpellbookSpells, Spellbook);
    }


    public boolean favourited(String adid) {
        return Favourites.indexOf(adid) != -1;
    }
    public boolean inspellbook(String adid) {
        return SpellbookSpells.indexOf(adid) != -1;
    }

}

