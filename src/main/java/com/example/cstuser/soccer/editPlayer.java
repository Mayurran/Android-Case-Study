package com.example.cstuser.soccer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by baoqiyu on 4/14/2016.
 */
public class editPlayer extends Activity implements View.OnClickListener  {
    Button ok,cancel;
    EditText name,team,salary,id,bdate,oldid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_player_activity);
        ok = (Button) findViewById(R.id.editPlayerOk);
        ok.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.editPlayerCancel);
        cancel.setOnClickListener(this);
        name = (EditText) findViewById(R.id.editPlayerName);
        id = (EditText) findViewById(R.id.editPlayerId);
        oldid = (EditText) findViewById(R.id.editPlayerCurrentId);
        salary = (EditText) findViewById(R.id.editPlayerSalary);
        bdate = (EditText) findViewById(R.id.editPlayerBdate);
        team = (EditText) findViewById(R.id.editPlayerTeam);
    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.editPlayerCancel: {
                finish();
                break;
            }
            case R.id.editPlayerOk: {
                MySQLiteDataBase db=new MySQLiteDataBase(getApplicationContext());
                db.editPlayer(oldid.getText().toString(),id.getText().toString(),name.getText().toString(),bdate.getText().toString(),salary.getText().toString(),team.getText().toString());
                finish();
                break;
            }
        }
    }
}