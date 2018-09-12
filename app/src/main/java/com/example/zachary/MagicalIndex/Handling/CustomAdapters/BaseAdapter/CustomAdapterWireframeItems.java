package com.example.zachary.MagicalIndex.Handling.CustomAdapters.BaseAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.R;

public class CustomAdapterWireframeItems extends BaseAdapter {

    String[] CategoryName;
    Integer[] CategoryImage;
    Activity context;
    App App;
    private static LayoutInflater inflater=null;
    public CustomAdapterWireframeItems(Activity con) {
        context = con;
        App = new App(context);
        CategoryName = App.parseStringArray(R.array.characters);
        CategoryImage = App.parseImageIDs(R.array.character_images);
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        Holder holder = new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.item_wireframe, null);

        /** Get relevant view
         * Set image resource, based on list name, based on list position
         */

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCategoryBroadcast(position);
            }
        });

        holder.name = (TextView) rowView.findViewById(R.id.tvWireText);
        holder.name.setText(CategoryName[position]);
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCategoryBroadcast(position);
            }
        });

        holder.iv = (ImageView) rowView.findViewById(R.id.ivWireImage);
        holder.iv.setImageResource(CategoryImage[position]);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCategoryBroadcast(position);
            }
        });

        return rowView;
    }

    public void sendCategoryBroadcast(int position) {
        Intent intent = new Intent("CategoryToSearch");
        intent.putExtra("CategoryName", CategoryName[position]);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

}
