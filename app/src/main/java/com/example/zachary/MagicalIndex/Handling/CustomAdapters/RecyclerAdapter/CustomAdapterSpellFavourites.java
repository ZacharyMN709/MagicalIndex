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
import android.widget.Toast;

import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpell;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.NavigationController;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;

/**
 * Created by Zachary on 03-May-17.
 */
public class CustomAdapterSpellFavourites extends RecyclerView.Adapter<CustomAdapterSpellFavourites.Holder>{
    
    ArrayList<DataSpell> dataSpells;
    CustomAdapterSpellFavourites.UnfavouritedPass favouritePasser;
    CustomAdapterSpellFavourites.AddSpellPass spellPasser;
    ArrayList<String> Favourites = new ArrayList<>();
    boolean clickdirect;
    Activity context;
    App App;

    public CustomAdapterSpellFavourites(Activity callingclass, ArrayList<DataSpell> dataadlist, boolean movetoprofile, CustomAdapterSpellFavourites.UnfavouritedPass unfavouritedpass, CustomAdapterSpellFavourites.AddSpellPass spellpass) {
        favouritePasser     = unfavouritedpass;
        spellPasser         = spellpass;
        clickdirect         = movetoprofile;
        dataSpells          = dataadlist;
        context             = callingclass;
        App                 = new App(context);
        Favourites          = App.getFavouriteSpells();
    }

    public interface UnfavouritedPass {
        void onDataPass(String data);
    }

    public interface AddSpellPass {
        void onSpellFavouritePass(String id, String name);
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
            boolean delete = false;

            @Override
            public void onClick(View v) {
                if (delete) {
                    holder.favourite.setImageResource(R.drawable.ic_favorite_border_white_24dp);
                    Favourites.remove(Favourites.indexOf(id));
                    App.setFavouriteSpells(Favourites);
                    favouritePasser.onDataPass(id);
                } else {
                    delete = true;
                    Toast.makeText(context, "Tap again to remove from Favourites", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spellPasser.onSpellFavouritePass(id, Spell.getSpellName());
            }
        });
    }


    public boolean favourited(String adid) {
        return Favourites.indexOf(adid) != -1;
    }

}

