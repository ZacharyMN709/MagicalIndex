package com.example.zachary.MagicalIndex.Main.Start;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.zachary.MagicalIndex.Handling.DataObjects.DataSpellCount;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.App;
import com.example.zachary.MagicalIndex.Handling.LocalHandling.ObjectBuilder;
import com.example.zachary.MagicalIndex.Main.Drawer_Main;
import com.example.zachary.MagicalIndex.R;

public class StartScreen extends AppCompatActivity {

    App App;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App = new App(this);
        boolean firstload = App.getFirstLoad();

        if (firstload) {
            setContentView(R.layout.startscreen_style1);
        }
        else {
            Intent intent = new Intent(this, Drawer_Main.class);
            this.finish();
            startActivity(intent);
            }
        }


    // ---------------------------------------------------------------------------------------------

    public void signup(View view) {
        App.setFullCharacter(7, 1, 0);
        ObjectBuilder ob = new ObjectBuilder(this);
        DataSpellCount ag = ob.getSpellCount(7, 1, 0);
        App.setFullSpellcount(ag.getSlots());
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        String Username = etUsername.getText().toString();
        App.setUSR(Username);
        App.setFirstLoad();
        Intent intent = new Intent(this, Drawer_Main.class);
        this.finish();
        startActivity(intent);
    }

}




