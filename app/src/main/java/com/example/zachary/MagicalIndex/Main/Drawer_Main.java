package com.example.zachary.MagicalIndex.Main;

import android.app.SearchManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.NavigationController;
import com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens.DrawerCategories;
import com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens.DrawerFavourites;
import com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens.DrawerSpellCount;
import com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens.DrawerSpellRules;
import com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens.DrawerSpellbooks;
import com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens.DrawerSpells;
import com.example.zachary.MagicalIndex.Main.Fragments.PopUps.PopUpNewSpellbook;
import com.example.zachary.MagicalIndex.Main.Fragments.PopUps.PopUpSetCharacter;
import com.example.zachary.MagicalIndex.R;


import java.util.ArrayList;

public class Drawer_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static String DRAWERSPELLS = "Spells";
    static String DRAWERCATEGORIES = "Characters";
    static String DRAWERSPELLBOOKS = "Spellbooks";
    static String FAVOURITESPELLS = "Favourites";
    static String SPELLCOUNT = "SpellCount";
    static String SPELLRULES = "SpellRules";

    Toolbar toolbar = null;
    NavigationView navigationView = null;
    int menuxmllayout, deleteallcounter, Char, Level, Mod;
    String Username, BarTitle, Search, USRID, Refine;
    ArrayList<String> Characters = new ArrayList<>();
    App App;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App = new App(this);
        setContentView(R.layout.drawer_drawerbody);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LocalBroadcastManager.getInstance(this).registerReceiver(
                CategorySelectReceiver, new IntentFilter("CategoryToSearch"));

        USRID = App.getUSRID();
        BarTitle = "Spells";
        Search = "ALL";
        Char = 7;
        Level = 1;
        Mod = 0;
        deleteallcounter = 0;
        Characters = App.parseStringList(R.array.characters);

        handleIntent(getIntent());
        setDrawerLayout();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_allspells:
                BarTitle = "Spells";
                Refine = null;
                loadfragment(DRAWERSPELLS);
                break;
            case R.id.nav_sortspells:
                loadfragment(DRAWERCATEGORIES);
                break;
            case R.id.nav_spellbooks:
                loadfragment(DRAWERSPELLBOOKS);
                break;
            case R.id.nav_favouritespells:
                loadfragment(FAVOURITESPELLS);
                break;
            case R.id.nav_count:
                loadfragment(SPELLCOUNT);
                break;
            case R.id.nav_rules:
                loadfragment(SPELLRULES);
                break;
            case R.id.nav_clearfavourites:
                deleteallcounter++;
                if (deleteallcounter == 4) {
                    App.clearFavouriteSpells();
                    Toast.makeText(Drawer_Main.this, "Spells deleted!", Toast.LENGTH_SHORT).show();
                    deleteallcounter = 0;
                    Search = "ALL";
                    BarTitle = "Spells";
                    loadfragment(DRAWERSPELLS);
                } else {
                    Toast.makeText(Drawer_Main.this, "Tap " + (4 - deleteallcounter)
                            + " more times to delete your favourite spells", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadfragment(String tag) {
        switch(tag){
            case "Spells":
                setMenuBar(R.menu.headerbar_spelllist, BarTitle);
                String fragmenttag = "Spells";
                if (Search.equals("ALL")) {fragmenttag = "Top";}
                App.loadDrawerFragment(new DrawerSpells(), fragmenttag, NavigationController.SpellSearch(Search, Refine), this);
                break;
            case "Characters":
                setMenuBar(R.menu.headerbar_textonly, "Magic Users");
                App.loadDrawerFragment(new DrawerCategories(), "Characters", null, this);
                break;
            case "Spellbooks":
                setMenuBar(R.menu.headerbar_textonly, "Spellbooks");
                App.loadDrawerFragment(new DrawerSpellbooks(), "Spellbooks", null, this);
                break;
            case "Favourites":
                setMenuBar(R.menu.headerbar_textonly, "Favourite Spells");
                App.loadDrawerFragment(new DrawerFavourites(), "Favourites", null, this);
                break;
            case "SpellCount":
                setMenuBar(R.menu.headerbar_textonly, "Daily Spells");
                App.loadDrawerFragment(new DrawerSpellCount(), "SpellCount", null, this);
                break;
            case "SpellRules":
                setMenuBar(R.menu.headerbar_textonly, "Spell Rules");
                App.loadDrawerFragment(new DrawerSpellRules(), "SpellRules", null, this);
                break;
        }

    }

    public void setMenuBar(int xml, String titletext){
        menuxmllayout = xml;
        this.invalidateOptionsMenu();
        getSupportActionBar().setTitle(titletext);
    }

    public void fabClicked(View view){
        if (App.poppableBackstack("Spellbooks", this)) {
            App.createPopUp(new PopUpNewSpellbook(), "CreateSpellbook", null, this);
        }
        if (App.poppableBackstack("SpellCount", this)) {
            //TODO - Change Object to be created
            App.createPopUp(new PopUpSetCharacter(), "SetCharacter", null, this);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            return;
        }

        if (App.poppableBackstack("CreateSpellbook", this)) {
            getFragmentManager().popBackStack();
            loadfragment(DRAWERSPELLBOOKS);
            return;
        }

        if (App.poppableBackstack("SetCharacter", this)) {
            getFragmentManager().popBackStack();
            loadfragment(SPELLCOUNT);
            return;
        }

        if (App.poppableBackstack("SingleSpellbookAdd", this)) {
            getFragmentManager().popBackStack();
            return;
        }

        if (!App.poppableBackstack("Top", this)) {
            navigationView.setCheckedItem(R.id.nav_allspells);
            if ((Refine != null) && (Refine != BarTitle)) {
                BarTitle = Refine;
                Search = Refine;
            } else {
                Refine = null;
                BarTitle = "Spells";
                Search = "ALL";
            }
            loadfragment(DRAWERSPELLS);
            return;
        }

        super.onBackPressed();
    }

    // ---------------------------------------------------------------------------------------------

    @Override
    public void onResume () {
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Search = intent.getStringExtra(SearchManager.QUERY);
            if (Refine != null) { BarTitle = Refine + " - Search Results"; }
            else {BarTitle = "Search Results";}
        }
        loadfragment(DRAWERSPELLS);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(menuxmllayout, menu);

        if (menuxmllayout == R.menu.headerbar_spelllist) {
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            MenuItem searchItem = menu.findItem(R.id.action_search);
            SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        // Unregister since the activity is about to be closed.
        LocalBroadcastManager.getInstance(this).unregisterReceiver(CategorySelectReceiver);
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setDrawerLayout() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerview = navigationView.inflateHeaderView(R.layout.drawer_drawerheader);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_allspells);

        Username = App.getUSR();
        String Email = "Please choose an option.";

        TextView drawerusername = (TextView) headerview.findViewById(R.id.drawerheader_username);
        TextView draweremail = (TextView) headerview.findViewById(R.id.drawerheader_email);
        drawerusername.setText("Hello, " + Username + ".");
        draweremail.setText(Email);
    }

    private BroadcastReceiver CategorySelectReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            BarTitle = intent.getStringExtra("CategoryName");
            Search = BarTitle;
            Refine = Search;
            loadfragment(DRAWERSPELLS);
            navigationView.setCheckedItem(R.id.nav_allspells);
        }
    };

}

