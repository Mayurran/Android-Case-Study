package com.example.cstuser.soccer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Widgets extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //---Inflate (or expand or fill) the layout for this fragment---
        return inflater.inflate(R.layout.widget_display, container, false);
    }
}
