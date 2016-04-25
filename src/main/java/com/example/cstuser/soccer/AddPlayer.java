package com.example.cstuser.soccer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddPlayer extends Activity implements View.OnClickListener  {
    Button ok,cancel;
    EditText name,team,salary,id,bdate;
    AlertDialog.Builder warnningDialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplayer);
        ok = (Button) findViewById(R.id.addplayerok);
        ok.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.addplayercancel);
        cancel.setOnClickListener(this);
        name = (EditText) findViewById(R.id.playerName);
        id = (EditText) findViewById(R.id.playerid);
        salary = (EditText) findViewById(R.id.playerSalary);
        bdate = (EditText) findViewById(R.id.playerBdate);
        team = (EditText) findViewById(R.id.playerTeam);
        warnningDialogBuilder = new AlertDialog.Builder(this);
        warnningDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addplayercancel: {
                finish();
                break;
            }
            case R.id.addplayerok: {
                MySQLiteDataBase db=new MySQLiteDataBase(getApplicationContext());

                if(db.addPlayer(id.getText().toString(),name.getText().toString(),bdate.getText().toString(),salary.getText().toString(),team.getText().toString())) {
                    finish();
                }
                else{
                    warnningDialogBuilder.setMessage("Player ID already exist")
                            .setTitle("Warning");
                    AlertDialog dialog = warnningDialogBuilder.create();
                    dialog.show();
                }
                break;
            }
        }
    }
}
