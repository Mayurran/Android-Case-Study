package com.example.cstuser.soccer;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by baoqiyu on 4/13/2016.
 */
public class MySQLiteDataBase extends SQLiteOpenHelper  {
    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "T9db";

    public MySQLiteDataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void reset(Activity act){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS T9tblPlayer");
        db.execSQL("DROP TABLE IF EXISTS T9tblTeam");
        String CREATE__TABLE = "CREATE TABLE T9tblPlayer ( " +
                "id INT, " +
                "name TEXT, " +
                "birthday TEXT, " +
                "salary TEST, " +
                "TeamId INT )";
        db.execSQL(CREATE__TABLE);
        CREATE__TABLE = "CREATE TABLE T9tblTeam ( " +
                "id INT, " +
                "name TEXT, " +
                "city TEXT, " +
                "country TEXT )";
        db.execSQL(CREATE__TABLE);


        ArrayList<Player> players= new ArrayList<Player>();
        ArrayList<Team> teams= new ArrayList<Team>();

        FileManager.createDataFile(act);
        FileManager.readFile(players,teams,act);
        for(Player p:players){
            addPlayer(p.id,p.name,p.birthDate,p.salary,p.teamid);

        }
        for(Team t:teams){
            addTeam(t.id,t.name,t.city,t.country);
        }
        db.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE__TABLE = "CREATE TABLE T9tblPlayer ( " +
                "id INT, " +
                "name TEXT, " +
                "birthday TEXT, " +
                "salary TEST, " +
                "TeamId INT )";
        db.execSQL(CREATE__TABLE);
        CREATE__TABLE = "CREATE TABLE T9tblTeam ( " +
                "id INT, " +
                "name TEXT, " +
                "city TEXT, " +
                "country TEXT )";
        db.execSQL(CREATE__TABLE);



    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS T9tblPlayer");
        db.execSQL("DROP TABLE IF EXISTS T9tblTeam");
        this.onCreate(db);
    }


    public void deletePlayer(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("T9tblPlayer", //table name
                "id = ?",//KEY_ID+" = ?",  - selection
                new String[]{id}//new String[] { String.valueOf(Id)}   -  selections parameters
        );
        db.close();
    }

    public boolean addPlayer(String id, String name, String date, String salary, String team) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("T9tblPlayer", // a. table
                new String[]{"id","name","birthday","salary","TeamId"}, // b. column names
                id.length()==0 ? null: "id = ?" , // c. selections
                id.length()==0 ? null: new String[]{id} , // d. selections parameters
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null &&  cursor.moveToFirst()){
            db.close();
            return false;
        }
        db.close();


        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("birthday",date);
        values.put("salary",salary);
        values.put("TeamId",team);
        db.insert("T9tblPlayer", // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        db.close();
        return true;
    }
    public void editPlayer(String oldid,String id, String name, String date, String salary, String team) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("birthday",date);
        values.put("salary",salary);
        values.put("TeamId",team);
        db.update("T9tblPlayer", // table
                values, //nullColumnHack
                "id = ?",
                new String[]{oldid}); // key/value -> keys = column names/ values = column values
        db.close();
    }
    public void getPlayers( ArrayAdapter<String> adapter, String id){
        adapter.clear();
        adapter.add("ID");
        adapter.add("Name");
        adapter.add("BirthDate");
        adapter.add("Salary");
        adapter.add("Team ID");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("T9tblPlayer", // a. table
                new String[]{"id","name","birthday","salary","TeamId"}, // b. column names
                id.length()==0 ? null: "id = ?" , // c. selections
                id.length()==0 ? null: new String[]{id} , // d. selections parameters
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results
        if (cursor != null &&  cursor.moveToFirst()){
            while(true){
                for(int i=0;i<5;i++)
                    adapter.add(cursor.getString(i));
                if(cursor.moveToNext()==false)break;
            }

        }
    }

    public boolean addTeam(String id, String name, String city, String country) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("T9tblTeam", // a. table
                new String[]{"id","name","city","country"}, // b. column names
                id.length()==0 ? null: "id = ?" , // c. selections
                id.length()==0 ? null: new String[]{id} , // d. selections parameters
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor != null &&  cursor.moveToFirst()){
            db.close();
            return false;
        }
        db.close();

        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("city",city);
        values.put("country",country);
        db.insert("T9tblTeam", // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values
        db.close();
        return true;
    }


    public void getTeams( ArrayAdapter<String> adapter, String id){
        adapter.clear();
        adapter.add("ID");
        adapter.add("Name");
        adapter.add("City");
        adapter.add("Country");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("T9tblTeam", // a. table
                new String[]{"id","name","city","country"}, // b. column names
                id.length()==0 ? null: "id = ?" , // c. selections
                id.length()==0 ? null: new String[]{id} , // d. selections parameters
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        // 3. if we got results
        if (cursor != null &&  cursor.moveToFirst()){
            while(true){
                for(int i=0;i<4;i++)
                    adapter.add(cursor.getString(i));
                if(cursor.moveToNext()==false)break;
            }

        }
    }
    public void editTeam(String oldid,String id, String name, String city, String country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("name",name);
        values.put("city",city);
        values.put("country",country);
        db.update("T9tblTeam", // table
                values, //nullColumnHack
                "id = ?",
                new String[]{oldid}); // key/value -> keys = column names/ values = column values
        db.close();
    }
    public boolean deleteTeam(String id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("T9tblPlayer", // a. table
                new String[]{"id","name","birthday","salary","TeamId"}, // b. column names
                id.length()==0 ? null: "TeamId = ?" , // c. selections
                id.length()==0 ? null: new String[]{id} , // d. selections parameters
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null &&  cursor.moveToFirst()){

            db.close();
            return false;
        }
        db.close();
        db = this.getWritableDatabase();
        db.delete("T9tblTeam", //table name
                "id = ?",//KEY_ID+" = ?",  - selection
                new String[]{id}//new String[] { String.valueOf(Id)}   -  selections parameters
        );
        db.close();
        return true;
    }
}
