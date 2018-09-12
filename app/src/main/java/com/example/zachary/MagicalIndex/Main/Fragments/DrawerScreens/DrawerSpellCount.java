package com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zachary.MagicalIndex.Handling.CustomAdapters.RecyclerAdapter.CustomAdapterSpellCount;
import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpellCount;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.ObjectBuilder;
import com.example.zachary.MagicalIndex.R;

/**
 * Created by Zachary on 10-Jul-16.
 */
public class DrawerSpellCount extends Fragment{

    App App;
    View view;
    Activity context;
    int Char, Level, Mod;
    int[] MaxSpells;
    String[] Characters;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.screen_spellslots, container, false);
        context = getActivity();
        App = new App(context);
        Characters = App.parseStringArray(R.array.characters);

        Char = App.getCharacter();
        Level = App.getLevel();
        Mod = App.getMod();


        ObjectBuilder ob = new ObjectBuilder(context);
        DataSpellCount ag = ob.getSpellCount(Char, Level, Mod);
        MaxSpells = ag.getSlots();
        setViews(ag);
        // TODO - Set up Warlock differently
        refreshAdapter();
        return view;
    }

    public void setViews(DataSpellCount ag){
        TextView tvClass = (TextView) view.findViewById(R.id.tvClass);
        ImageView ivIcon = (ImageView) view.findViewById(R.id.ivIcon);
        TextView tvRitual = (TextView) view.findViewById(R.id.tvRitual);
        TextView tvFocus = (TextView) view.findViewById(R.id.tvFocus);
        TextView tvKnown = (TextView) view.findViewById(R.id.tvKnown);
        TextView tvPrepared = (TextView) view.findViewById(R.id.tvPrepared);
        TextView tvAttack = (TextView) view.findViewById(R.id.tvAttack);
        TextView tvSave = (TextView) view.findViewById(R.id.tvSave);

        Integer[] icons = App.parseImageIDs(R.array.character_images);
        tvClass.setText(Characters[Char] + " (" + Level + ")");
        ivIcon.setImageResource(icons[Char]);
        tvRitual.setText(ag.getRitual());
        tvFocus.setText(ag.getFocus());
        tvKnown.setText(ag.getKnown());
        tvPrepared.setText(ag.getPrepare());
        tvAttack.setText(ag.getAttack());
        tvSave.setText(ag.getSave());

        if(Char == 6){ setWarlock(ag); }
    }

    public void setWarlock(DataSpellCount ag) {
        RelativeLayout R1 = (RelativeLayout)view.findViewById(R.id.WARLOCK1);
        RelativeLayout R2 = (RelativeLayout)view.findViewById(R.id.WARLOCK2);
        R1.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        R2.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;

        TextView tvSlotLevel = (TextView) view.findViewById(R.id.tvSlotLevel);
        TextView tvInvocations = (TextView) view.findViewById(R.id.tvInvocations);

        tvSlotLevel.setText(ag.getSlotLevel());
        tvInvocations.setText(ag.getInvocations());
    }

    public void refreshAdapter() {
        App.refreshLinearAdapter(view, context, new CustomAdapterSpellCount(context, MaxSpells));
    }

}
