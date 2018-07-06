package com.dot.uicenter.activities;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;

import com.dot.uicenter.R;
import com.dot.uicenter.utils.SettingsUtils;
import com.dot.uicenter.utils.argb_picker.ColorPickerDialog;

public class QSPanelCustomization extends AppCompatActivity implements ColorPickerDialog.ColorPickerDialogListener {

    LinearLayout header;
    CardView go_back;
    ImageButton picker;
    SeekBar qs_alpha, tiles_alpha;
    Switch tiles_circle;
    private int alphaValue = 0;
    private int redValue = 0;
    private int greenValue = 0;
    private int blueValue = 0;
    private String hexAlpha = Integer.toHexString(alphaValue);
    private String hexRed = Integer.toHexString(redValue);
    private String hexGreen = Integer.toHexString(greenValue);
    private String hexBlue = Integer.toHexString(blueValue);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qs_panel_customization);
        go_back = findViewById(R.id.go_back);
        go_back.setOnClickListener(v -> finish());
        picker = findViewById(R.id.open_picker);
        picker.setOnClickListener(v -> showDialog());
        header = findViewById(R.id.header);
        qs_alpha = findViewById(R.id.qs_alpha);
        tiles_alpha = findViewById(R.id.qs_tiles_bg_alpha);
        tiles_circle = findViewById(R.id.qs_tiles_enable_circle);
        header.setBackgroundColor(Color.parseColor(SettingsUtils.getHeaderColor(this)));
        initSeekbars();
        initSwitch();
    }

    public void initSeekbars() {
		qs_alpha.setProgress(SettingsUtils.getQSAlpha(this));
        qs_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsUtils.setQSAlpha(QSPanelCustomization.this, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
		tiles_alpha.setProgress(SettingsUtils.getTilesAlpha(this));
        tiles_alpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SettingsUtils.setTilesAlpha(QSPanelCustomization.this, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void initSwitch() {
        tiles_circle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                qs_alpha.setActivated(true);
                qs_alpha.setEnabled(true);
                SettingsUtils.setCircleState(QSPanelCustomization.this, 1);
            } else {
                qs_alpha.setActivated(false);
                qs_alpha.setEnabled(false);
                SettingsUtils.setCircleState(QSPanelCustomization.this, 0);
            }
        });
        switch (SettingsUtils.getCircleState(this)) {
            case 0:
                tiles_circle.setChecked(false);
                break;
            case 1:
                tiles_circle.setChecked(true);
                break;
        }
    }

    public String hexParser() {
        if (hexAlpha.length() == 1) {
            hexAlpha = hexAlpha + hexAlpha;
        }
        if (hexRed.length() == 1) {
            hexRed = hexRed + hexRed;
        }
        if (hexGreen.length() == 1) {
            hexGreen = hexGreen + hexGreen;
        }
        if (hexBlue.length() == 1) {
            hexBlue = hexBlue + hexBlue;
        }
        return String.format("#%S%S%S%S", hexAlpha, hexRed, hexGreen, hexBlue);
    }

    public void showDialog() {
        ColorPickerDialog colorPickerDialog = new ColorPickerDialog();
        colorPickerDialog.setInitialColor(alphaValue, redValue, greenValue, blueValue);
        colorPickerDialog.show(getFragmentManager(), "InterfaceCenter");
    }

    @Override
    public void onColorConfirmed(DialogFragment dialog, int alpha, int red, int green, int blue) {
        alphaValue = alpha;
        redValue = red;
        greenValue = green;
        blueValue = blue;
        hexAlpha = Integer.toHexString(alphaValue);
        hexRed = Integer.toHexString(redValue);
        hexGreen = Integer.toHexString(greenValue);
        hexBlue = Integer.toHexString(blueValue);
        header.setBackgroundColor(Color.parseColor(hexParser()));
        SettingsUtils.setHeaderColor(this, hexParser());
    }

    @Override
    public void onCancelled(DialogFragment dialog) {

    }
}
