package com.example.cstuser.soccer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.util.ArrayList;

/**
 * Created by baoqiyu on 4/13/2016.
 */
public class FileManager {
    public static void createDataFile(Activity activity){
        try {

            FileOutputStream fos = activity.openFileOutput("T9txtFile.dat", Context.MODE_PRIVATE);
            FileWriter writer = new FileWriter(fos.getFD());

            writer.append("100 Mike 01/04/1995 1500 1\n");
            writer.append("101 Jack 01/04/1995 1800 2\n");
            writer.append("102 Tom 01/04/1995 2000 1\n");
            writer.append("103 Ben 01/04/1995 1000 3\n");
            writer.append("\n1 BARSA Barselon Spain\n");
            writer.append("2 RealMadrid Madirid Spain\n");
            writer.append("3 M.United Manchester U.K\n");
            writer.close();
        }
        catch(Exception ex){
            Log.i("TAG", ex.toString());

        }
    }
    public static void readFile(ArrayList<Player> p, ArrayList<Team> t,Activity act){
        try {
            int tf=0;
            String line;
            FileInputStream fos = act.openFileInput("T9txtFile.dat");
            BufferedReader reader = new BufferedReader(new FileReader(fos.getFD()));

            while ((line = reader.readLine()) != null) {
                if(line.length()==0) {
                    tf = 1;
                    continue;
                }
                String [] params=line.split(" ");
                if(tf==1){
                    t.add(new Team(params[0],params[1],params[2],params[3]));
                }
                else
                    p.add(new Player(params[0],params[1],params[2],params[3],params[4]));
            }
            reader.close();
        }
        catch(Exception ex){
            Log.i("TAG", ex.toString());
        }
    }
}
