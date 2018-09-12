package com.example.zachary.MagicalIndex.Main.Fragments.PopUps;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpellCount;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.ObjectBuilder;
import com.example.zachary.MagicalIndex.R;

/**
 * Created by Zachary on 10-Jul-16.
 */
public class PopUpSetCharacter extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    App App;
    View view;
    Activity context;
    Spinner sClass;
    EditText etMod, etLevel;
    int Level, Char, Mod;
    String[] Mods;
    Button confirm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.popup_setcharacter, container, false);
        context = getActivity();
        App = new App(context);
        Mods = App.parseStringArray(R.array.character_mods);

        sClass = (Spinner) view.findViewById(R.id.spinnerClass);
        etLevel = (EditText) view.findViewById(R.id.etLevel);
        etMod = (EditText) view.findViewById(R.id.etMod);
        confirm = (Button) view.findViewById(R.id.confirm);
        App.createSpinner(sClass, R.array.characters, context);
        sClass.setOnItemSelectedListener(this);
        confirm.setOnClickListener(this);
        view.findViewById(R.id.shadow).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.confirm) {
            if (checkData()) {
                ObjectBuilder ob = new ObjectBuilder(context);
                DataSpellCount ag = ob.getSpellCount(Char, Level, Mod);
                App.setFullCharacter(Char, Level, Mod);
                App.setFullSpellcount(ag.getSlots());
                App.hideSoftKeyboard(context);
                context.onBackPressed();
            } else {
                Toast.makeText(context, "Invalid Input! Please try again.", Toast.LENGTH_SHORT).show();
            }
        } else {context.onBackPressed();}
    }

    public boolean checkData() {
        String l = etLevel.getText().toString();
        String m = etMod.getText().toString();

        if((l.equals("")) || (m.equals(""))) {return false;}
        if((l.length() > 2) || (m.length() > 2)) {return false;}

        Level = Integer.valueOf(l);
        Mod = Integer.valueOf(m);

        if((Level > 20) || (Level == 0)) {return false;}
        if(Mod < -5){return false;}
        // TODO - Add safety for Mod value
        return true;
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        Char = pos;
        etMod.setHint(Mods[pos] + " Mod:");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

}
