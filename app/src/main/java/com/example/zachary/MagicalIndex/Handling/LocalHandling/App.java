package com.example.zachary.MagicalIndex.Handling.LocalHandling;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zachary.MagicalIndex.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Zachary on 18-Jul-16.
 */
public class App {

    public  App() {}

    private Activity mContext;
    private SharedPreferences storage;
    private SharedPreferences.Editor editor;

    public App(Activity context) {
        mContext = context;
        storage = PreferenceManager.getDefaultSharedPreferences(getContext());
        editor = storage.edit();
    }

    //----------------------------------------------------------------------------------------------
    // General Functions
    //----------------------------------------------------------------------------------------------

    // --------------------------------------------------------------------------------------------

    public Activity getContext(){ return mContext; }

    // --------------------------------------------------------------------------------------------

    public String fromStorage(String caller) {
        return storage.getString(caller, "");
    }

    public int fromStorageInt(String caller) {
        return storage.getInt(caller, 0);
    }

    public boolean fromStorageBool(String caller) {
        return storage.getBoolean(caller, false);
    }

    public void toStorage(String caller, String data) {
        editor.putString(caller, data);
        editor.apply();
    }

    public void toStorageInt(String caller, int data) {
        editor.putInt(caller, data);
        editor.apply();
    }

    public void toStorageBool(String caller, boolean data) {
        editor.putBoolean(caller, data);
        editor.apply();
    }

    public void clearStorage(String caller) {
        editor.remove(caller);
    }

    // --------------------------------------------------------------------------------------------

    public void setFirstLoad() {
        editor.putBoolean("FirstLoad", false);
        editor.apply();
    }

    public boolean getFirstLoad() {
        return storage.getBoolean("FirstLoad", true);
    }

    // --------------------------------------------------------------------------------------------

    public String getUSR() {return fromStorage("USR");}
    public String getUSRID() {return fromStorage("USRID");}
    public String getPPS() {return fromStorage("PPS");}

    public void setUSR(String data) {toStorage("USR", data);}


   // ---------------------------------------------------------------------------------------------

    /*
    public static void hideFAB(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.hide();
    }
    */

    public static void hideListFAB(View view) {
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.listfab);
        fab.hide();
        View list = view.findViewById(R.id.viewList);
        list.setPadding(0, 4, 0, 4);
    }

    public static void imageviewresize(ImageView imageview, int size) {
        if (imageview.getDrawable() != null) {
            imageview.requestLayout();
            imageview.getLayoutParams().height = size;
            imageview.getLayoutParams().width = size;
        } else {
            imageview.requestLayout();
            imageview.getLayoutParams().height = 0;
            imageview.getLayoutParams().width = 0;
        }

    }

    // ---------------------------------------------------------------------------------------------

    public static void createSpinner(Spinner spinner, int data, Activity context) {
        ArrayAdapter<CharSequence> holder = ArrayAdapter.createFromResource(context,
                data, R.layout.style_spinnerbody);
        holder.setDropDownViewResource(R.layout.style_spinnerdropdown);
        spinner.setAdapter(holder);
//        spinner.setOnItemClickListener(context);
    }

    // ---------------------------------------------------------------------------------------------

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View focusedView = activity.getCurrentFocus();
        if (focusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
        }
    }

    // ---------------------------------------------------------------------------------------------

    public static void setCentreTextActionBar(ActionBar actionBar, String title) {
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.actionbar_centertexttemplate);
        TextView text = (TextView) actionBar.getCustomView().findViewById(R.id.center_text);
        text.setText(title);
    }

    // ---------------------------------------------------------------------------------------------

    public static Integer[] parseImageIDs(int x) {
        TypedArray tArray = ContextHandler.getContext().getResources().obtainTypedArray(x);
        Integer[] Icons = new Integer[tArray.length()];
        for (int i = 0; i < Icons.length; i++) { Icons[i] = tArray.getResourceId(i, 0); }
        tArray.recycle();
        return Icons;
    }

    public static String[] parseStringArray(int x) {
        String[] Strings = ContextHandler.getContext().getResources().getStringArray(x);
        return Strings;
    }

    public static ArrayList<String> parseStringList(int x) {
        ArrayList StringList = new ArrayList<>(Arrays.asList(parseStringArray(x)));
        return StringList;
    }

    // ---------------------------------------------------------------------------------------------

    public void loadDrawerFragment(Fragment fragment, String tag, Bundle bundle, Activity activity) {
        if (bundle != null) {
            Bundle args = new Bundle();
            args.putBundle("Bundle", bundle);
            fragment.setArguments(args);
        }
        FragmentTransaction FT = activity.getFragmentManager().beginTransaction();
        FT.replace(R.id.drawer_fragment_holder, fragment, tag);
        FT.commit();
    }

    public static void createPopUp(Fragment fragment, String tag, Bundle bundle, Activity activity) {
        hideSoftKeyboard(activity);
        if (bundle != null) {
            Bundle args = new Bundle();
            args.putBundle("Bundle", bundle);
            fragment.setArguments(args);
        }
        FragmentTransaction FT = activity.getFragmentManager().beginTransaction();
        FT.replace(R.id.fragmentholder, fragment, tag);
        FT.addToBackStack(tag);
        FT.commit();
    }

    public static boolean poppableBackstack(String tag, Activity activity) {
        Fragment myFragment = activity.getFragmentManager().findFragmentByTag(tag);
        if (myFragment != null && myFragment.isVisible()) {
            activity.getFragmentManager().popBackStack();
            return true;
        }
        return false;
    }

    // --------------------------------------------------------------------------------------------

    public ArrayList getDataSet(String caller) {
        ArrayList<String> holder = new ArrayList<>();
        Set<String> StringSet = storage.getStringSet(caller, null);
        if (StringSet != null) {holder.addAll(StringSet);}
        return holder;
    }

    public void setDataSet(ArrayList list, String caller) {
        Set<String> StringSet = new HashSet<>(list);
        StringSet.addAll(list);
        editor.remove(caller);
        editor.apply();
        editor.putStringSet(caller, StringSet);
        editor.apply();
    }

    public void clearDataSet(String caller) {
        editor.remove(caller);
        editor.apply();
    }

    // ---------------------------------------------------------------------------------------------

    public void refreshLinearAdapter(View view, Context context, RecyclerView.Adapter mAdapter) {
        RecyclerView adpreviewlist = (RecyclerView) view.findViewById(R.id.viewList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        adpreviewlist.setLayoutManager(mLayoutManager);
        adpreviewlist.setItemAnimator(new DefaultItemAnimator());
        adpreviewlist.setAdapter(mAdapter);
    }

    public void refreshReverseLinearAdapter(View view, Context context, RecyclerView.Adapter mAdapter) {
        RecyclerView fullchat = (RecyclerView) view.findViewById(R.id.viewList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setStackFromEnd(true);
        fullchat.setLayoutManager(layoutManager);
        fullchat.setItemAnimator(new DefaultItemAnimator());
        fullchat.setAdapter(mAdapter);
    }

    public void refreshGridAdapter(View view, Context context, int columns, RecyclerView.Adapter mAdapter) {
        RecyclerView adpreviewlist = (RecyclerView) view.findViewById(R.id.viewList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, columns);
        adpreviewlist.setLayoutManager(mLayoutManager);
        adpreviewlist.setItemAnimator(new DefaultItemAnimator());
        adpreviewlist.setAdapter(mAdapter);
    }


    //----------------------------------------------------------------------------------------------
    // App Specific Functions
    //----------------------------------------------------------------------------------------------

    public ArrayList getFavouriteSpells() {
        return getDataSet("FavouriteSpells");
    }
    public void setFavouriteSpells(ArrayList favourite) {
        setDataSet(favourite, "FavouriteSpells");
    }
    public void clearFavouriteSpells() {
        clearDataSet("FavouriteSpells");
    }

    // --------------------------------------------------------------------------------------------

    public ArrayList getSpellbooks() {
        return getDataSet("Spellbooks");
    }
    public void setSpellbooks(ArrayList list) {
        setDataSet(list, "Spellbooks");
    }

    public int getSpellbookIcon(String listname) {
        return fromStorageInt(listname + "Icon");
    }
    public void setSpellbookIcon(String listname, int icon) {
        toStorageInt(listname + "Icon", icon);
    }
    public void deleteAllSpellbooks() {
        clearDataSet("Spellbooks");
        clearDataSet("SpellbookIcons");
    }

    public void deleteSpellbook(String name) {
        clearDataSet(name);
        editor.remove(name + "Icon");

        ArrayList<String> Spellbooks = getSpellbooks();
        Spellbooks.remove(Spellbooks.indexOf(name));
        setSpellbooks(Spellbooks);
    }

    // ---------------------------------------------------------------------------------------------

    public int getCharacter() {
        return fromStorageInt("Character");
    }
    public void setCharacter(int value) {
        toStorageInt("Character", value);
    }

    public int getLevel() {
        return fromStorageInt("Level");
    }
    public void setLevel(int value) {
        toStorageInt("Level", value);
    }

    public int getMod() {
        return fromStorageInt("Mod");
    }
    public void setMod(int value) {
        toStorageInt("Mod", value);
    }

    public int getSpellCount(int lvl) {
        return fromStorageInt("Count" + lvl);
    }
    public void setSpellCount(int lvl, int count) {
        toStorageInt("Count" + lvl, count);
    }

    public void setFullCharacter(int character, int level, int mod) {
        setCharacter(character);
        setLevel(level);
        setMod(mod);
    }
    public void setFullSpellcount(int[] count) {
        for(int x=0; x<10; x++){ setSpellCount(x, count[x]); }
    }
}
