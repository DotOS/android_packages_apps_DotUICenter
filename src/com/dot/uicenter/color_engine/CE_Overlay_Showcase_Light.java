package com.dot.uicenter.color_engine;

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

public class CE_Overlay_Showcase_Light extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.overlay_light_showcase, container, false);
        ImageButton action_left = view.findViewById(R.id.overlay_switch_left);
        ImageButton action_right = view.findViewById(R.id.overlay_switch_right);
        action_left.setOnClickListener(this);
        action_right.setOnClickListener(this);
        checkCardVisibility(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        ImageView settings_overlay_light = getView().findViewById(R.id.overlay_light_settings);
        ImageView qs_overlay_light = getView().findViewById(R.id.overlay_light_qs);
        switch (v.getId()) {
            case R.id.overlay_switch_left:
                if (settings_overlay_light.getVisibility() == View.VISIBLE) {
                    settings_overlay_light.setVisibility(View.GONE);
                    qs_overlay_light.setVisibility(View.VISIBLE);
                } else if (qs_overlay_light.getVisibility() == View.VISIBLE) {
                    settings_overlay_light.setVisibility(View.VISIBLE);
                    qs_overlay_light.setVisibility(View.GONE);
                }
                checkCardVisibility(getView());
                break;
            case R.id.overlay_switch_right:
                if (settings_overlay_light.getVisibility() == View.VISIBLE) {
                    settings_overlay_light.setVisibility(View.GONE);
                    qs_overlay_light.setVisibility(View.VISIBLE);
                } else if (qs_overlay_light.getVisibility() == View.VISIBLE) {
                    settings_overlay_light.setVisibility(View.VISIBLE);
                    qs_overlay_light.setVisibility(View.GONE);
                }
                checkCardVisibility(getView());
                break;
        }
    }
    public void checkCardVisibility(View view) {
        TextView overlay_text = view.findViewById(R.id.overlay_text_showcase);
        ImageView settings_overlay_light = view.findViewById(R.id.overlay_light_settings);
        ImageView qs_overlay_light = view.findViewById(R.id.overlay_light_qs);
        if (settings_overlay_light.getVisibility() == View.VISIBLE) {
            overlay_text.setText(R.string.settings_overlay_light);
        }
        else if (qs_overlay_light.getVisibility() == View.VISIBLE) {
            overlay_text.setText(R.string.qs_overlay_light);
        }
    }
}
