package com.dot.uicenter.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ControlledViewPager extends ViewPager {

    private Boolean disable = false;
    public ControlledViewPager(Context context) {
        super(context);
    }
    public ControlledViewPager(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return disable ? false : super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return disable ? false : super.onTouchEvent(event);
    }

    public void disableScroll(Boolean disable){
        this.disable = disable;
    }

    public Boolean isDisabled() {
        return disable;
    }
}