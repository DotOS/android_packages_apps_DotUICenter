package com.dot.uicenter.ui_controller.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dot.uicenter.R;
import com.dot.uicenter.utils.ExpandableLayout;
import com.dot.uicenter.utils.ObjectToolsAnimator;

public class PanelEditor extends RelativeLayout {

    String object;
    boolean isText;
    boolean expand;
    View object_view;
    ImageButton close;
    TextView obj_name;
    RelativeLayout head_layout;
    ExpandableLayout expandableLayout;
    RadioButton visibility_state_visible;
    RadioButton visibility_state_invisible;
    Status status = Status.IDLE;

    public PanelEditor(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public PanelEditor(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public PanelEditor(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }

    public PanelEditor(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PanelEditor, defStyleAttr, defStyleRes);
        object = typedArray.getString(R.styleable.PanelEditor_panel_editor_object);
        isText = typedArray.getBoolean(R.styleable.PanelEditor_panel_editor_is_text, false);
        expand = typedArray.getBoolean(R.styleable.PanelEditor_panel_editor_expand, false);
        typedArray.recycle();
        View view = inflate(context, R.layout.qs_panel_menu_editor, this);
        View qs_view = inflate(context, R.layout.qs_panel_layout, null);
        head_layout = view.findViewById(R.id.head);
        obj_name = view.findViewById(R.id.part_name);
        expandableLayout = view.findViewById(R.id.expandable_view);
        switch (object) {
            case "Carrier":
                object_view = qs_view.findViewById(R.id.qs_l_carrier);
                break;
        }
        optionsBox(view);
        obj_name.setText(object);
        updateStatus();
        head_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equals(Status.COLLPASE)) {
                    expandableLayout.expand(true);
                    status = Status.EXPAND;
                }
                if (status.equals(Status.EXPAND)) {
                    expandableLayout.collapse(true);
                    status = Status.COLLPASE;
                }
            }
        });
        closeEditorListener(view);
    }

    private void closeEditorListener(final View view) {
        close = view.findViewById(R.id.close_editor);
        close.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectToolsAnimator.moveAndAnimate(view, "alpha", 1,0, 1000);
                ObjectToolsAnimator.moveAndAnimate(view, "translationY", 0, 2000, 500);
                if (view.getAlpha() == 0 && view.getTranslationY() == 2000) {
                    view.setVisibility(INVISIBLE);
                    view.setVisibility(GONE);
                }

            }
        });
    }

    private void optionsBox(View view) {
        visibility_state_visible = view.findViewById(R.id.state_changer_visible);
        visibility_state_invisible = view.findViewById(R.id.state_changer_invisible);
        visibility_state_visible.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getObjectView().getVisibility() != View.VISIBLE) {
                    getObjectView().setVisibility(VISIBLE);
                }
            }
        });
        visibility_state_invisible.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getObjectView().getVisibility() != View.INVISIBLE && getObjectView().getVisibility() != View.GONE) {
                    getObjectView().setVisibility(INVISIBLE);
                }
            }
        });
        switch (object_view.getVisibility()) {
            case View.VISIBLE:
                visibility_state_visible.setChecked(true);
                break;
            case View.INVISIBLE:
                visibility_state_invisible.setChecked(true);
                break;
        }
    }

    public void updateStatus() {
        if (this.expand) {
            this.expandableLayout.expand(true);
            this.status = Status.EXPAND;
        }
        if (!expand) {
            expandableLayout.collapse(true);
            status = Status.COLLPASE;
        }
        if (this.expandableLayout.isCollapsed()) {
            status = Status.COLLPASE;
        } else if (expandableLayout.isExpanded()) {
            status = Status.EXPAND;
        }
    }


    public void setObjectView(String view) {
        object = view;
    }
    public String getObjectName() {
        return object;
    }
    public View getObjectView() {
        return object_view;
    }

    public enum Status {
        EXPAND, COLLPASE, IDLE
    }

}
