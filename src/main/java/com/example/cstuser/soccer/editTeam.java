package com.example.cstuser.soccer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by baoqiyu on 4/14/2016.
 */
public class editTeam extends Activity implements View.OnClickListener {
    Button ok, cancel;
    EditText name, country, id, city, oldid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_team_activity);
        ok = (Button) findViewById(R.id.editTeamOk);
        ok.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.editTeamCancel);
        cancel.setOnClickListener(this);
        name = (EditText) findViewById(R.id.editTeamName);
        id = (EditText) findViewById(R.id.editTeamId);
        oldid = (EditText) findViewById(R.id.editTeamCurrentId);
        city = (EditText) findViewById(R.id.editTeamCity);
        country = (EditText) findViewById(R.id.editTeamCountry);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editTeamCancel: {
                finish();
                break;
            }
            case R.id.editTeamOk: {
                MySQLiteDataBase db = new MySQLiteDataBase(getApplicationContext());
                db.editTeam(oldid.getText().toString(), id.getText().toString(), name.getText().toString(), city.getText().toString(), country.getText().toString());
                finish();
                break;
            }
        }
    }
}