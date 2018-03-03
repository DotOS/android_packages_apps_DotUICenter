package com.dot.uicenter.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.dot.uicenter.R;

public class DeviceAttrReciver extends CardView {

    @SuppressLint("StaticFieldLeak")
    static View view;
    @SuppressLint("StaticFieldLeak")
    static TextView colorPrimary, colorPrimaryDark, colorAccent, textColorPrimary;

    public DeviceAttrReciver(@NonNull Context context) {
        super(context);
        view = inflate(context, R.layout.device_attrs_layout, this);
        colorPrimary = view.findViewById(R.id.txt_attr_primary);
        colorPrimaryDark = view.findViewById(R.id.txt_attr_primaryDark);
        colorAccent = view.findViewById(R.id.txt_attr_accent);
        textColorPrimary = view.findViewById(R.id.txt_attr_textColor);
    }

    public static ColorStateList getColorPrimary() {
        return colorPrimary.getTextColors();
    }

    public static ColorStateList getColorPrimaryDark() {
        return colorPrimaryDark.getTextColors();
    }

    public static ColorStateList getColorAccent() {
        return colorAccent.getTextColors();
    }

    public static ColorStateList getTextColorPrimary() {
        return textColorPrimary.getTextColors();
    }

}
