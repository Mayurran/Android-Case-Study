package com.example.cstuser.soccer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by baoqiyu on 4/14/2016.
 */
public class AddTeam extends Activity implements View.OnClickListener  {
    Button ok,cancel;
    EditText name,city,country,id;
    AlertDialog.Builder warnningDialogBuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_team_activity);
        ok = (Button) findViewById(R.id.addTeamOk);
        ok.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.addTeamCancel);
        cancel.setOnClickListener(this);
        name = (EditText) findViewById(R.id.addTeamName);
        id = (EditText) findViewById(R.id.addTeamId);
        city = (EditText) findViewById(R.id.addTeamCity);
        country = (EditText) findViewById(R.id.addTeamCountry);

        warnningDialogBuilder = new AlertDialog.Builder(this);
        warnningDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.addTeamCancel: {
                finish();
                break;
            }
            case R.id.addTeamOk: {
                MySQLiteDataBase db=new MySQLiteDataBase(getApplicationContext());
                if(db.addTeam(id.getText().toString(),name.getText().toString(),city.getText().toString(),country.getText().toString())) {
                    finish();
                }
                else{
                    warnningDialogBuilder.setMessage("Team ID already exist")
                            .setTitle("Warning");
                    AlertDialog dialog = warnningDialogBuilder.create();
                    dialog.show();
                }
                break;
            }
        }
    }
}