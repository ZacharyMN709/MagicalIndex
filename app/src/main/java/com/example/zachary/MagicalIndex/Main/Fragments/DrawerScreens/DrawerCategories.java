package com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.zachary.MagicalIndex.Handling.CustomAdapters.BaseAdapter.CustomAdapterWireframeItems;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.R;

public class DrawerCategories extends Fragment {

    View view;
    App App;
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.viewtemplate_list_gridview, container, false);
        context = getActivity();
        App = new App(context);
        App.hideListFAB(view);
        refreshAdapter();
        return view;
    }

    public void refreshAdapter() {
        /** Gets and sets the Category List text and drawables. */
        GridView catlist = (GridView) view.findViewById(R.id.viewList);
        catlist.setAdapter(new CustomAdapterWireframeItems(context));
    }

}

