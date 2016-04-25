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
public class searchDisplayPlayer  extends Activity implements View.OnClickListener {
    MySQLiteDataBase database;
    Button search, mainmenu;
    EditText  id;
    GridView gridView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_player_activity);
        search = (Button) findViewById(R.id.searchPlayerSearchButton);
        search.setOnClickListener(this);
        mainmenu = (Button) findViewById(R.id.searchPlayerReturnButton);
        mainmenu.setOnClickListener(this);

        id = (EditText) findViewById(R.id.searchPlayerId);

        gridView = (GridView) findViewById(R.id.playerList);
        ArrayList<String> listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.mytextview, listItems);
        gridView.setAdapter(adapter);
        database=new MySQLiteDataBase(getApplicationContext());
        database.getPlayers(adapter,"");

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.searchPlayerReturnButton: {
                finish();
                break;
            }
            case R.id.searchPlayerSearchButton: {
                database.getPlayers(adapter,id.getText().toString());
                break;
            }
        }
    }
}