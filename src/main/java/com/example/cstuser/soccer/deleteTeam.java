package com.example.cstuser.soccer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
public class deleteTeam extends Activity implements View.OnClickListener {
    MySQLiteDataBase database;
    Button deleteb, mainmenu;
    EditText id;
    GridView gridView;
    ArrayAdapter<String> adapter;
    AlertDialog.Builder warnningDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_team_activity);
        deleteb = (Button) findViewById(R.id.deleteTeamDeleteButton);
        deleteb.setOnClickListener(this);
        mainmenu = (Button) findViewById(R.id.deleteTeamReturnButton);
        mainmenu.setOnClickListener(this);

        id = (EditText) findViewById(R.id.deleteTeamId);

        gridView = (GridView) findViewById(R.id.deletTemList);
        ArrayList<String> listItems = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(this, R.layout.mytextview, listItems);
        gridView.setAdapter(adapter);
        database=new MySQLiteDataBase(getApplicationContext());
        database.getTeams(adapter,"");

        warnningDialogBuilder = new AlertDialog.Builder(this);
        warnningDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.deleteTeamReturnButton: {
                finish();
                break;
            }
            case R.id.deleteTeamDeleteButton: {
                if(id.getText().toString().length()>0) {
                    if(database.deleteTeam(id.getText().toString())) {
                        database.getTeams(adapter, "");
                    }
                    else{
                        warnningDialogBuilder.setMessage("Can not Delete a Team which has Players")
                                .setTitle("Warning");
                        AlertDialog dialog = warnningDialogBuilder.create();
                        dialog.show();
                    }
                    id.setText("");
                }

                break;
            }
        }
    }
}
