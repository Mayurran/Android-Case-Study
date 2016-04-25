package com.example.cstuser.soccer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by baoqiyu on 4/13/2016.
 */
public class soccerDataBaseActivity extends Activity implements View.OnClickListener  {
    Button mainMenu,newPlayer,searchPlayer,deletePlayer,editPlayer;
    Button newTeam,searchTeam,deleteTeam,editTeam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soccer_database_activity);
        mainMenu = (Button) findViewById(R.id.returnMain);
        mainMenu.setOnClickListener(this);
        newPlayer = (Button) findViewById(R.id.newPlayer);
        newPlayer.setOnClickListener(this);
        searchPlayer = (Button) findViewById(R.id.searchPlayer);
        searchPlayer.setOnClickListener(this);
        deletePlayer = (Button) findViewById(R.id.deletePlayer);
        deletePlayer.setOnClickListener(this);
        editPlayer = (Button) findViewById(R.id.editPlayer);
        editPlayer.setOnClickListener(this);
        newTeam = (Button) findViewById(R.id.newTeam);
        newTeam.setOnClickListener(this);
        searchTeam = (Button) findViewById(R.id.searchTeam);
        searchTeam.setOnClickListener(this);
        deleteTeam = (Button) findViewById(R.id.deleteTeam);
        deleteTeam.setOnClickListener(this);
        editTeam = (Button) findViewById(R.id.editTeam);
        editTeam.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.returnMain: {
                finish();
                break;
            }
            case R.id.newTeam: {
                Intent intent = new Intent(soccerDataBaseActivity.this,AddTeam.class);
                startActivity(intent);
                break;
            }
            case R.id.searchTeam: {
                Intent intent = new Intent(soccerDataBaseActivity.this,searchTeam.class);
                startActivity(intent);
                break;
            }
            case R.id.deleteTeam: {
                Intent intent = new Intent(soccerDataBaseActivity.this,deleteTeam.class);
                startActivity(intent);
                break;
            }
            case R.id.editTeam: {
                Intent intent = new Intent(soccerDataBaseActivity.this,editTeam.class);
                startActivity(intent);
                break;
            }

            case R.id.newPlayer: {
                Intent intent = new Intent(soccerDataBaseActivity.this,AddPlayer.class);
                startActivity(intent);
                break;
            }
            case R.id.searchPlayer: {
                Intent intent = new Intent(soccerDataBaseActivity.this,searchDisplayPlayer.class);
                startActivity(intent);
                break;
            }
            case R.id.deletePlayer: {
                Intent intent = new Intent(soccerDataBaseActivity.this,deletePlayer.class);
                startActivity(intent);
                break;
            }
            case R.id.editPlayer: {
                Intent intent = new Intent(soccerDataBaseActivity.this,editPlayer.class);
                startActivity(intent);
                break;
            }
        }
    }
}
