package com.example.zachary.MagicalIndex.Main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.zachary.MagicalIndex.Handling.CustomAdapters.RecyclerAdapter.CustomAdapterSpellList;
import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpell;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.ObjectBuilder;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.SpellSearch;
import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Spellbook extends AppCompatActivity {

    ArrayList<String> SpellIDs;
    ArrayList<DataSpell> Spells, SpellsbyID, SearchSpells;
    String Spellbook, Search;

    boolean delete = false;
    boolean search, editable;
    App App;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App = new App(this);
        setContentView(R.layout.viewtemplate_list_recyclerview);
        view = findViewById(android.R.id.content);
        Spellbook = getIntent().getStringExtra("Spellbook");
        editable = getIntent().getBooleanExtra("SetUp", false);
        search =  getIntent().getBooleanExtra("Search", false);
        RecyclerView rv = (RecyclerView) findViewById(R.id.viewList);
        rv.setBackgroundResource(App.getSpellbookIcon(Spellbook));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.listfab);
        fab.setImageResource(R.drawable.ic_edit);

        loadClassSpells();
        refreshSpells();

        refreshApadpters();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        intent.putExtra("Spellbook", Spellbook);
        intent.putExtra("SetUp", editable);
        intent.putExtra("Search", true);
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    public void onResume () {
        invalidateOptionsMenu();
        super.onResume();
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            Search = intent.getStringExtra(SearchManager.QUERY);
            search = true;
            this.getCurrentFocus().clearFocus();
            SpellSearch ss;
            if(editable) {ss = new SpellSearch(this, Spells);}
            else {ss = new SpellSearch(this, SpellsbyID);}
            SearchSpells = ss.SearchSpells(Search);
            refreshAdapterSearch();
        }
    }

    private void loadClassSpells() {
        int x = new ArrayList<>(Arrays.asList(App.parseImageIDs(R.array.Spellbook_images))).indexOf(App.getSpellbookIcon(Spellbook));

        String[] CategoryNames = getResources().getStringArray(R.array.characters);

        if(x < 2) {
            ObjectBuilder ob = new ObjectBuilder(this);
            Spells = ob.SpellstoObject();
        } else {
            SpellSearch ss = new SpellSearch(this);
            Spells = ss.SearchSpells(CategoryNames[x - 2]);
        }
    }

    private void refreshSpells() {
        if (SpellIDs != null) {SpellIDs.clear();}
        if (SpellsbyID != null) {SpellsbyID.clear();}
        ObjectBuilder ob = new ObjectBuilder(this);
        SpellIDs = App.getDataSet(Spellbook);
        if(SpellIDs != null) {SpellsbyID = ob.SpellstoObjectbyID(SpellIDs);}
    }

    public void fabClicked(View view){
        invalidateOptionsMenu();
        App.hideSoftKeyboard(this);
        if(this.getCurrentFocus() != null) {this.getCurrentFocus().clearFocus();}
        editable = !editable;
        refreshApadpters();
    }

    public void refreshApadpters() {
        refreshSpells();
        if (!editable) {refreshAdapterMain();}
        if (editable) {refreshAdapterEdit();}
    }

    public void refreshAdapterMain() {
        getSupportActionBar().setTitle(Spellbook);
        App.refreshLinearAdapter(view, this, new CustomAdapterSpellList(this, SpellsbyID, Spellbook, null));
    }

    public void refreshAdapterEdit() {
        getSupportActionBar().setTitle("Edit");
        App.refreshLinearAdapter(view, this, new CustomAdapterSpellList(this, Spells, Spellbook, null));
    }

    public void refreshAdapterSearch() {
        getSupportActionBar().setTitle("Search");
        App.refreshLinearAdapter(view, this, new CustomAdapterSpellList(this, SearchSpells, Spellbook, null));
    }

    @Override
    public void onBackPressed() {
        if(search) {
            search = false;
            refreshApadpters();
            return;
        }
        this.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_search) {}
        if (id == R.id.action_delete) {
            if (delete) {
                App.deleteSpellbook(Spellbook);
                this.finish();
            } else {
                delete = true;
                Toast.makeText(Spellbook.this, "Press again to delete this Spellbook.", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.headerbar_spellbook, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchItem.collapseActionView();
        App.hideSoftKeyboard(this);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchItem.collapseActionView();
        return super.onPrepareOptionsMenu(menu);
    }

}
