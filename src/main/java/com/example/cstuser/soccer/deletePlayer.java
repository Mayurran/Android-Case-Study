package com.example.cstuser.soccer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by baoqiyu on 4/14/2016.
 */
public class deletePlayer extends Activity implements View.OnClickListener {
    MySQLiteDataBase database;
    Button deleteb, mainmenu;
    EditText id;
    GridView gridView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_player_activity);
        deleteb = (Button) findViewById(R.id.deletePlayerDeleteButton);
        deleteb.setOnClickListener(this);
        mainmenu = (Button) findViewById(R.id.deletePlayerReturnButton);
        mainmenu.setOnClickListener(this);

        id = (EditText) findViewById(R.id.deletePlayerId);

        gridView = (GridView) findViewById(R.id.playerList);
        ArrayList<String> listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.mytextview, listItems);
        gridView.setAdapter(adapter);
        database=new MySQLiteDataBase(getApplicationContext());
        database.getPlayers(adapter,"");

    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.deletePlayerReturnButton: {
                finish();
                break;
            }
            case R.id.deletePlayerDeleteButton: {
                if(id.getText().toString().length()>0) {
                    database.deletePlayer(id.getText().toString());
                    database.getPlayers(adapter, "");
                    id.setText("");
                }

                break;
            }
        }
    }
}
