package com.dot.uicenter.night_mode;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Switch;

import com.dot.uicenter.R;
import com.dot.uicenter.utils.ExpandableLayout;

import static com.dot.uicenter.utils.ShellUtils.run;

public class NightModeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = inflater.inflate(R.layout.nightmode_layout, container, false);
        final Switch legacy_night = view.findViewById(R.id.enable_night_legacy);
        final Switch custom_night = view.findViewById(R.id.enable_night_custom);
        custom_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                legacy_night.setChecked(false);
                custom_night.setChecked(isChecked);
                enableDotNight(view, isChecked);
            }
        });
        legacy_night.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                custom_night.setChecked(false);
                legacy_night.setChecked(isChecked);
                enableLegacyNight(view, isChecked);
            }
        });
        final SeekBar warm = view.findViewById(R.id.warm_intensity);
        warm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setWarm(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        final SeekBar legacy_warm = view.findViewById(R.id.warm_intensity_legacy);
        legacy_warm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setWarm(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        SeekBarsButtonsController(view);
        return view;
    }

    public void SeekBarsButtonsController(View view) {
        final SeekBar warm = view.findViewById(R.id.warm_intensity);
        ImageButton plus_warm = view.findViewById(R.id.plus_warm);
        plus_warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warm.setProgress(warm.getProgress() - 300);
            }
        });
        ImageButton remove_warm = view.findViewById(R.id.remove_warm);
        remove_warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warm.setProgress(warm.getProgress() + 300);
            }
        });
        final SeekBar legacy_warm = view.findViewById(R.id.warm_intensity_legacy);
        ImageButton plus_legacy_warm = view.findViewById(R.id.plus_warm_legacy);
        plus_legacy_warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                legacy_warm.setProgress(legacy_warm.getProgress() - 300);
            }
        });
        ImageButton remove_legacy_warm = view.findViewById(R.id.remove_warm_legacy);
        remove_legacy_warm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                legacy_warm.setProgress(legacy_warm.getProgress() + 300);
            }
        });
    }

    public static void enableLegacyNight(View view, boolean mode) {
        run(mode ? "settings put secure night_display_activated 1" : "settings put secure night_display_activated 0");
        ExpandableLayout night_prefs = view.findViewById(R.id.legacy_night_mode_prefs);
        if (mode) {
            night_prefs.expand(true);
        } if (!mode && night_prefs.isExpanded()) {
            night_prefs.collapse(true);
        }
    }
    public static void enableDotNight(View view, boolean mode) {
        ExpandableLayout night_prefs = view.findViewById(R.id.custom_night_mode_prefs);
        if (mode) {
            night_prefs.expand(true);
        } if (!mode && night_prefs.isExpanded()) {
            night_prefs.collapse(true);
        }
        run(mode ? "settings put secure night_display_activated 1" : "settings put secure night_display_activated 0");
    }
    public void setWarm(int value) {
        run("settings put secure night_display_color_temperature " + value);
    }

}
