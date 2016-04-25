package com.example.cstuser.soccer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivitySlideshow extends Activity implements View.OnClickListener{

    Button returnToMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_slideshow);

        returnToMain = (Button)this.findViewById(R.id.returnHome);
        returnToMain.setOnClickListener(this);
    }

    public void onClick(View view) {
        if(view == returnToMain) {
            finish();
        }
    }
}

