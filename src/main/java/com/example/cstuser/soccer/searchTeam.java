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
public class searchTeam extends Activity implements View.OnClickListener {
    MySQLiteDataBase database;
    Button search, mainmenu;
    EditText id;
    GridView gridView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_team_activity);
        search = (Button) findViewById(R.id.searchTeamSearchButton);
        search.setOnClickListener(this);
        mainmenu = (Button) findViewById(R.id.searchTeamReturnButton);
        mainmenu.setOnClickListener(this);
        id = (EditText) findViewById(R.id.searchTeamId);
        gridView = (GridView) findViewById(R.id.teamList);
        ArrayList<String> listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.mytextview, listItems);
        gridView.setAdapter(adapter);
        database=new MySQLiteDataBase(getApplicationContext());
        database.getTeams(adapter,"");

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchTeamReturnButton: {
                finish();
                break;
            }
            case R.id.searchTeamSearchButton: {
                database.getTeams(adapter,id.getText().toString());
                break;
            }
        }
    }
}