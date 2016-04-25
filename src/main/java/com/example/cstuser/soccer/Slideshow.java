package com.example.cstuser.soccer;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.media.MediaPlayer;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Slideshow extends Fragment implements constants {

    private Integer[] imageIDs = IMAGE_IDS;
    private String[] captions = CAPTIONS;
    private int currentImageIndex;
    private ImageView slideShow;
    private TextView caption;
    private CheckBox repeat;
    private RadioButton legendText;
    private Button start;
    private RadioButton playMusic;
    private RadioGroup group;
    private MediaPlayer mp;
    private Handler mHandler;
    private Runnable mUpdateResults;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //---Inflate (or expand or fill) the layout for this fragment---
        return inflater.inflate(R.layout.slideshow, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mp = MediaPlayer.create(getActivity(),R.raw.kalimbamp3);
        repeat = (CheckBox) getActivity().findViewById(R.id.repeat);
        legendText = (RadioButton) getActivity().findViewById(R.id.legendText);
        caption = (TextView) getActivity().findViewById(R.id.captions);
        start = (Button) getActivity().findViewById(R.id.startButton);
        slideShow = (ImageView) this.getActivity().findViewById(R.id.ImageView);
        playMusic = (RadioButton)this.getActivity().findViewById(R.id.playSoundFile);
        group =(RadioGroup)this.getActivity().findViewById(R.id.radioGroup);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentImageIndex = FIRST_IMAGE;
                mHandler = new Handler();
                mUpdateResults = new Runnable() {
                    public void run() {
                        startSlideShow();
                        currentImageIndex++;
                        if (currentImageIndex >= imageIDs.length) {
                            if (repeat.isChecked()) {
                                currentImageIndex = FIRST_IMAGE;
                            } else currentImageIndex = lAST_IMAGE;
                        }
                    }
                };

                int delay = DELAY; // delay for 2 sec.
                int period = PERIOD; // repeat every 5 sec.
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new TimerTask() {
                    public void run() {
                        mHandler.post(mUpdateResults);
                    }
                }, delay, period);
            }
        });

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (playMusic.isChecked()) {
                    mp.start();
                }
            }
        });
    }

    public void startSlideShow() {
        slideShow.setImageResource(imageIDs[currentImageIndex]);
        if (legendText.isChecked()) {
            caption.setText(captions[currentImageIndex]);
        }
    }
}