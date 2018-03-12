package com.dot.uicenter.bottom_fragments.overlay_engine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dot.uicenter.R;

public class CE_Overlay_Showcase_Dark extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.overlay_dark_showcase, container, false);
        ImageButton action_left = view.findViewById(R.id.overlay_switch_left_dark);
        ImageButton action_right = view.findViewById(R.id.overlay_switch_right_dark);
        action_left.setOnClickListener(this);
        action_right.setOnClickListener(this);
        checkCardVisibility(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        ImageView settings_overlay_dark = getView().findViewById(R.id.overlay_dark_settings);
        ImageView qs_overlay_dark = getView().findViewById(R.id.overlay_dark_qs);
        switch (v.getId()) {
            case R.id.overlay_switch_left_dark:
                if (settings_overlay_dark.getVisibility() == View.VISIBLE) {
                    settings_overlay_dark.setVisibility(View.GONE);
                    qs_overlay_dark.setVisibility(View.VISIBLE);
                } else if (qs_overlay_dark.getVisibility() == View.VISIBLE) {
                    settings_overlay_dark.setVisibility(View.VISIBLE);
                    qs_overlay_dark.setVisibility(View.GONE);
                }
                checkCardVisibility(getView());
                break;
            case R.id.overlay_switch_right_dark:
                if (settings_overlay_dark.getVisibility() == View.VISIBLE) {
                    settings_overlay_dark.setVisibility(View.GONE);
                    qs_overlay_dark.setVisibility(View.VISIBLE);
                } else if (qs_overlay_dark.getVisibility() == View.VISIBLE) {
                    settings_overlay_dark.setVisibility(View.VISIBLE);
                    qs_overlay_dark.setVisibility(View.GONE);
                }
                checkCardVisibility(getView());
                break;
        }
    }
    public void checkCardVisibility(View view) {
        TextView overlay_text_dark = view.findViewById(R.id.overlay_text_showcase_dark);
        ImageView settings_overlay_dark = view.findViewById(R.id.overlay_dark_settings);
        ImageView qs_overlay_dark = view.findViewById(R.id.overlay_dark_qs);
        if (settings_overlay_dark.getVisibility() == View.VISIBLE) {
            overlay_text_dark.setText(R.string.settings_overlay_dark);
        }
        else if (qs_overlay_dark.getVisibility() == View.VISIBLE) {
            overlay_text_dark.setText(R.string.qs_overlay_dark);
        }
    }
}
