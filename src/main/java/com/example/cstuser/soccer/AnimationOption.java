package com.example.cstuser.soccer;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class AnimationOption extends Activity implements View.OnClickListener {

    ImageView imageView;
	int differenceX = 0, differenceY = 0;
    AnimationSet up, left, down, right, animate;  
    Button startBtn, stopBtn ,returnMenuBtn;
    MediaPlayer radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_view);

        imageView = (ImageView) findViewById(R.id.imageView);

        startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        returnMenuBtn = (Button) findViewById(R.id.menuBtn);

        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        returnMenuBtn.setOnClickListener(this);

        stopBtn.setEnabled(false);
    }
    public void onClick(View v){

        if(v == startBtn){
            startBtn.setEnabled(false);
            stopBtn.setEnabled(true);
            start();


        }else if(v == stopBtn){
            startBtn.setEnabled(true);
            stopBtn.setEnabled(false);
            imageView.clearAnimation();
            radio.stop();
        }
        if(v == returnMenuBtn){
            finish();
        }
    }

    public void start() {

         radio = MediaPlayer.create(this, R.raw.sleepaway);
                radio.start();

        up = new AnimationSet(true);
        left = new AnimationSet(true);
        down = new AnimationSet(true);
        right = new AnimationSet(true);
        animate = new AnimationSet(true);

        animate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!startBtn.isEnabled()) {
                    imageView.startAnimation(animate);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        up.addAnimation(createAnimationSet(0, -800, true, true, false, false));

        left.addAnimation(createAnimationSet(-550, 0, true, true, true, false));
        left.setStartOffset(1000);

        down.addAnimation(createAnimationSet(0, 800, true, true, true, true));
        down.setStartOffset(2000);

        right.addAnimation(createAnimationSet(550, 0, false, true, false, false));
        right.setStartOffset(3000);

        animate.addAnimation(up);
        animate.addAnimation(left);
        animate.addAnimation(down);
        animate.addAnimation(right);

        imageView.startAnimation(animate);

        differenceY = 0;
        differenceX = 0;
    }

	public AnimationSet createAnimationSet(int xValue, int yValue, boolean  rotate, boolean translate, boolean scale, boolean fade){
		AnimationSet set = new AnimationSet(true);
        if(rotate == true){
            RotateAnimation rotateImage = new RotateAnimation(0, 360, Animation.ABSOLUTE,(75*Animation.RELATIVE_TO_SELF) + differenceX,  Animation.ABSOLUTE,(75*Animation.RELATIVE_TO_SELF) +differenceY);
            rotateImage.setDuration(1000);
            set.addAnimation(rotateImage);
        }
        if(translate == true){
			TranslateAnimation translateImage = new TranslateAnimation(0,xValue,0,yValue);
            differenceY+=yValue;
            differenceX+= xValue;
            translateImage.setDuration(1000);
            set.addAnimation(translateImage);
			set.setFillAfter(true);

		}

		if(scale == true){
			ScaleAnimation scaleImage = new ScaleAnimation(1,1.5f,1,1.5f, Animation.ABSOLUTE,(75*Animation.RELATIVE_TO_SELF) + differenceX,  Animation.ABSOLUTE,(75*Animation.RELATIVE_TO_SELF) +differenceY);
            scaleImage.setDuration(1000);

            set.addAnimation(scaleImage);
        }
		if(fade == true){
            AlphaAnimation fadeInImage = new AlphaAnimation(1, 0.5f);

            fadeInImage.setDuration(1000);

            set.addAnimation(fadeInImage);


		}

        return set;
    }
	
	
}
