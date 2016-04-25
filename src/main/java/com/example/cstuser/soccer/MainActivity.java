package com.example.cstuser.soccer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener,constants {

    Button Exit,DataBaseButton,sendMessage, slideShow, Animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Exit = (Button) findViewById(R.id.Exit);
        Exit.setOnClickListener(this);
        DataBaseButton = (Button) findViewById(R.id.soccerDatabase);
        DataBaseButton.setOnClickListener(this);
        sendMessage = (Button) findViewById(R.id.SendMessage);
        sendMessage.setOnClickListener(this);
        slideShow = (Button) findViewById(R.id.DisplaySlideShow);
        slideShow.setOnClickListener(this);
        Animation = (Button) findViewById(R.id.DisplayAnim);
        Animation.setOnClickListener(this);
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
            case R.id.Exit: {
                Toast.makeText(this,MELANCHOLIC_MESSAGE,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(VANIER_WEBSITE));
                startActivity(intent);
                break;
            }
            case R.id.soccerDatabase: {
                Intent intent = new Intent(MainActivity.this,soccerDataBaseActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.SendMessage: {
                Intent intent = new Intent(MainActivity.this,SMS.class);
                startActivity(intent);
                break;
            }

            case R.id.DisplaySlideShow: {
                Intent intent = new Intent(MainActivity.this,MainActivitySlideshow.class);
                startActivity(intent);
                break;
            }
            case R.id.DisplayAnim: {
                Intent intent = new Intent(MainActivity.this,AnimationOption.class);
                startActivity(intent);
                break;
            }
        }
    }
}

