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

public class CE_Overlay_Showcase_Black extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.overlay_black_showcase, container, false);
        ImageButton action_left = view.findViewById(R.id.overlay_switch_left_black);
        ImageButton action_right = view.findViewById(R.id.overlay_switch_right_black);
        action_left.setOnClickListener(this);
        action_right.setOnClickListener(this);
        checkCardVisibility(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        ImageView settings_overlay_black = getView().findViewById(R.id.overlay_black_settings);
        ImageView qs_overlay_black = getView().findViewById(R.id.overlay_black_qs);
        switch (v.getId()) {
            case R.id.overlay_switch_left_black:
                if (settings_overlay_black.getVisibility() == View.VISIBLE) {
                    settings_overlay_black.setVisibility(View.GONE);
                    qs_overlay_black.setVisibility(View.VISIBLE);
                } else if (qs_overlay_black.getVisibility() == View.VISIBLE) {
                    settings_overlay_black.setVisibility(View.VISIBLE);
                    qs_overlay_black.setVisibility(View.GONE);
                }
                checkCardVisibility(getView());
                break;
            case R.id.overlay_switch_right_black:
                if (settings_overlay_black.getVisibility() == View.VISIBLE) {
                    settings_overlay_black.setVisibility(View.GONE);
                    qs_overlay_black.setVisibility(View.VISIBLE);
                } else if (qs_overlay_black.getVisibility() == View.VISIBLE) {
                    settings_overlay_black.setVisibility(View.VISIBLE);
                    qs_overlay_black.setVisibility(View.GONE);
                }
                checkCardVisibility(getView());
                break;
        }
    }
    public void checkCardVisibility(View view) {
        TextView overlay_text_black = view.findViewById(R.id.overlay_text_showcase_black);
        ImageView settings_overlay_black = view.findViewById(R.id.overlay_black_settings);
        ImageView qs_overlay_black = view.findViewById(R.id.overlay_black_qs);
        if (settings_overlay_black.getVisibility() == View.VISIBLE) {
            overlay_text_black.setText(R.string.settings_overlay_black);
        }
        else if (qs_overlay_black.getVisibility() == View.VISIBLE) {
            overlay_text_black.setText(R.string.qs_overlay_black);
        }
    }
}
