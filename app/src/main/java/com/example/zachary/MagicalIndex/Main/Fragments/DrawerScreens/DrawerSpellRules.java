package com.example.zachary.MagicalIndex.Main.Fragments.DrawerScreens;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.R;

/**
 * Created by Zachary on 10-Jul-16.
 */
public class DrawerSpellRules extends Fragment implements View.OnClickListener {

    App App;
    String[] Titles, Texts;
    TextView Title, Text, Page;
    Button Next, Previous;
    Activity context;
    int x = 0, max;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_rules, container, false);
        context = getActivity();
        App = new App(context);

        Titles = App.parseStringArray(R.array.rules_names);
        Texts = App.parseStringArray(R.array.rules_text);
        max = Texts.length;
        Title = (TextView) view.findViewById(R.id.tvTitle);
        Text = (TextView) view.findViewById(R.id.tvText);
        Page = (TextView) view.findViewById(R.id.tvPage);
        Next = (Button) view.findViewById(R.id.bconfirm);
        Previous = (Button) view.findViewById(R.id.bcancel);
        Next.setOnClickListener(this);
        Previous.setOnClickListener(this);
        setRules(x);
        return view;
    }

    private void setRules(int r) {
        Title.setText(Titles[r]);
        Text.setText(Html.fromHtml(Texts[r]));
        Page.setText((r+1) + "/" + max);
    }


    @Override
    public void onClick(View v) {
        Log.e("onClick: ", "Click registered");
        switch (v.getId()){
            case R.id.bconfirm:
                x++;
                setRules(x % Titles.length);
                break;
            case R.id.bcancel:
                x--;
                if(x == -1){x = 39;}
                setRules(x % Titles.length);
                break;
        }

    }

}
