package com.example.zachary.MagicalIndex.Main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpell;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.ObjectBuilder;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;

/**
 * Created by Zachary on 10-Jul-16.
 */
public class FullSpell extends AppCompatActivity {

    ArrayList<String> Favourites = new ArrayList<>();

    ImageView ivImageA, ivImageB, ivImageC, ivImageD, ivImageE, ivImageF, ivImageG, ivImageH;
    TextView tvSpellName, tvSpellLevel, tvSpellSchool, tvSpellRitual, tvSpellComponents, tvSpellConcentration, tvSpellTime,
            tvSpellDuration, tvSpellRange, tvSpellDescription;
    String spellID;
    boolean favourited;
    int hide;
    App App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App = new App(this);
        setContentView(R.layout.screen_fullspell);
        hide = getResources().getColor(R.color.colorPrimary);

        spellID = getIntent().getStringExtra("SpellID");

        ObjectBuilder ob = new ObjectBuilder(this);
        DataSpell spell = ob.SpellbyID(spellID);

        getSupportActionBar().setTitle(spell.getSpellName());

        if(!spell.isSpellBard()){
            ivImageA = (ImageView) findViewById(R.id.ivBard);
            ivImageA.setColorFilter(hide);
        }
        if(!spell.isSpellCleric()){
            ivImageB = (ImageView) findViewById(R.id.ivCleric);
            ivImageB.setColorFilter(hide);
        }
        if(!spell.isSpellDruid()){
            ivImageC = (ImageView) findViewById(R.id.ivDruid);
            ivImageC.setColorFilter(hide);
        }
        if(!spell.isSpellPaladin()){
            ivImageD = (ImageView) findViewById(R.id.ivPaladin);
            ivImageD.setColorFilter(hide);
        }
        if(!spell.isSpellRanger()){
            ivImageE = (ImageView) findViewById(R.id.ivRanger);
            ivImageE.setColorFilter(hide);
        }
        if(!spell.isSpellSorcerer()){
            ivImageF = (ImageView) findViewById(R.id.ivSorcerer);
            ivImageF.setColorFilter(hide);
        }
        if(!spell.isSpellWarlock()){
            ivImageG = (ImageView) findViewById(R.id.ivWarlock);
            ivImageG.setColorFilter(hide);
        }
        if(!spell.isSpellWizard()){
            ivImageH = (ImageView) findViewById(R.id.ivWizard);
            ivImageH.setColorFilter(hide);
        }



        tvSpellName = (TextView) findViewById(R.id.tvSpellName);
        tvSpellName.setText(spell.getSpellName());
        tvSpellLevel = (TextView) findViewById(R.id.tvSpellLevel);
        tvSpellLevel.setText(spell.getSpellLevel());
        tvSpellSchool = (TextView) findViewById(R.id.tvSpellSchool);
        tvSpellSchool.setText(spell.getSpellSchool());
        tvSpellTime = (TextView) findViewById(R.id.tvSpellTime);
        tvSpellTime.setText(spell.getSpellCastTime());
        tvSpellDuration = (TextView) findViewById(R.id.tvSpellDuration);
        tvSpellDuration.setText(spell.getSpellDuration());
        tvSpellRange = (TextView) findViewById(R.id.tvSpellRange);
        tvSpellRange.setText(spell.getSpellRange());
        tvSpellComponents = (TextView) findViewById(R.id.tvSpellComponents);
        tvSpellComponents.setText(spell.getSpellComponents());
        tvSpellDescription = (TextView) findViewById(R.id.tvSpellDescription);
        tvSpellDescription.setText(Html.fromHtml(spell.getSpellDescription()));

        tvSpellRitual = (TextView) findViewById(R.id.tvSpellRitual);
        if(spell.isSpellRitual()) { tvSpellRitual.setText("Yes");
        } else { tvSpellRitual.setText("No"); }
        tvSpellConcentration = (TextView) findViewById(R.id.tvSpellConcentration);
        if(spell.isSpellConcentration()) { tvSpellConcentration.setText("Yes");
        } else { tvSpellConcentration.setText("No"); }



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_favourite) {
            favourited = (Favourites.indexOf(spellID) != -1);
            if (!favourited) {
                item.setIcon(R.drawable.ic_favorite_white_24dp);
                Favourites.add(spellID);
            } else {
                item.setIcon(R.drawable.ic_favorite_border_white_24dp);
                Favourites.remove(Favourites.indexOf(spellID));
            }
            App.setFavouriteSpells(Favourites);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume () {
        super.onResume();
        this.invalidateOptionsMenu();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.headerbar_fullspell, menu);

        Favourites.clear();
        Favourites = App.getFavouriteSpells();

        if (Favourites.indexOf(spellID) != -1) {
            MenuItem heart = menu.findItem(R.id.action_favourite);
            heart.setIcon(R.drawable.ic_favorite_white_24dp);
        }
        return true;
    }

}
