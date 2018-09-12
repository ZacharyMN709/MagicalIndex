package com.example.zachary.MagicalIndex.Handling.CustomAdapters.BaseAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.R;

public class CustomAdapterMiniWireframeItems extends BaseAdapter {

    CustomAdapterMiniWireframeItems.IconPass dataPasser;
    String[] CategoryName;
    Integer[] CategoryImage;
    Activity context;
    App App;
    private static LayoutInflater inflater=null;
    public CustomAdapterMiniWireframeItems(Activity con, CustomAdapterMiniWireframeItems.IconPass listener) {
        context = con;
        dataPasser   = listener;
        App = new App(context);
        CategoryName = App.parseStringArray(R.array.Spellbook_names);
        CategoryImage = App.parseImageIDs(R.array.Spellbook_images);

        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public interface IconPass {
        void onDataPass(int data);
    }

    @Override
    // Get number of items in list
    public int getCount() {
        return CategoryName.length;
    }

    @Override
    // Get position of item in list
    public Object getItem(int position) {
        return position;
    }

    @Override
    // ???
    public long getItemId(int position) {
        return position;
    }

    public class Holder
    {
        TextView name;
        ImageView iv;
    }
    @Override
    public View getView(final int index, View convertView, final ViewGroup parent) {
        final int position = index;
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.item_wireframe_mini, null);

        /** Get relevant view
         * Set image resource, based on list name, based on list position
         */

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIcon(position);
            }
        });

        holder.name = (TextView) rowView.findViewById(R.id.tvWireText);
        holder.name.setText(CategoryName[position]);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIcon(position);
            }
        });

        holder.iv = (ImageView) rowView.findViewById(R.id.ivWireImage);
        holder.iv.setImageResource(CategoryImage[position]);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIcon(position);
            }
        });

        return rowView;
    }

    public void sendIcon(int position) {
        dataPasser.onDataPass(CategoryImage[position]);
    }

}
