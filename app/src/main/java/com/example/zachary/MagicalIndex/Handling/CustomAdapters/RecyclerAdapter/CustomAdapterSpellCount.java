package com.example.zachary.MagicalIndex.Handling.CustomAdapters.RecyclerAdapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.R;

/**
 * Created by Zachary on 03-May-17.
 */
public class CustomAdapterSpellCount extends RecyclerView.Adapter<CustomAdapterSpellCount.Holder>{

    Activity context;
    App App;
    int[] MaxSpells;

    public CustomAdapterSpellCount(Activity callingclass, int[] max) {
        context         = callingclass;
        App             = new App(context);
        MaxSpells       = max;
    }

    @Override
    public int getItemCount()  {
        return MaxSpells.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder extends RecyclerView.ViewHolder {

        public TextView Max, Count, Level;
        public ImageButton Up, Down;

        public Holder(View rowView) {
            super(rowView);

            Level = (TextView) rowView.findViewById(R.id.tvSpellLevel);
            Max = (TextView) rowView.findViewById(R.id.tvMaxSpells);
            Count = (TextView) rowView.findViewById(R.id.tvCurrentSpells);
            Up = (ImageButton) rowView.findViewById(R.id.ivInc);
            Down = (ImageButton) rowView.findViewById(R.id.ivDec);
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_spellcounter, parent, false);
        return new Holder(rowView);
    }

    @Override
    public void onBindViewHolder(Holder tempholder, int itemnum) {
        final Holder holder = tempholder;
        final int position = itemnum;

        holder.Level.setText(String.valueOf(position));
        holder.Max.setText(String.valueOf(MaxSpells[position]));
        holder.Count.setText(String.valueOf(App.getSpellCount(position)));

        holder.Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = App.getSpellCount(position);
                x++;
                holder.Count.setText(String.valueOf(x));
                App.setSpellCount(position, x);
            }
        });

        holder.Down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = App.getSpellCount(position);
                x--;
                holder.Count.setText(String.valueOf(x));
                App.setSpellCount(position, x);
            }
        });

    }

}

