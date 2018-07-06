package com.dot.uicenter.utils.argb_picker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dot.uicenter.R;

/**
 * This class creates a colorPicker using the base
 * class DialogFragment.
 * To show this dialog, first instantiate then
 * use the show method to display the dialog.
 */
public class ColorPickerDialog extends DialogFragment implements Observer {


    private boolean initialColor;
    private ColorPickerDialogListener mListener;

    //methods for the Observer interface
    private TextView currentColorTV;
    private AlphaPickerBar alphaPickerBar;
    private RedPickerBar redPickerBar;
    private BluePickerBar bluePickerBar;
    private GreenPickerBar greenPickerBar;
    private int alphaValue;
    private int redValue;
    private int blueValue;
    private int greenValue;

    @Override
    public void update(Subject subject, int value) {
        String hexColor = String.format("#%S%S%S%S", Integer.toHexString(alphaValue), Integer.toHexString(redValue), Integer.toHexString(greenValue), Integer.toHexString(blueValue));
        if (subject instanceof AlphaPickerBar) {
            alphaValue = value;
        } else if (subject instanceof RedPickerBar) {
            redValue = value;
        } else if (subject instanceof GreenPickerBar) {
            greenValue = value;
        } else if (subject instanceof BluePickerBar) {
            blueValue = value;
        }
        currentColorTV.setTextColor(Color.argb(alphaValue, redValue, greenValue, blueValue));
        currentColorTV.setText(hexColor);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity/fragment implements the callback interface
        try {
            // Instantiate the ColorPickerDialogListener so we can send events to the host
            // If there is a target fragment then use that as the listener.
            mListener = (ColorPickerDialogListener) this.getTargetFragment();
            if (mListener == null) {
                //no target fragment so use activity
                mListener = (ColorPickerDialogListener) activity;
            }
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement ColorPickerDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.color_picker, null);
        builder.setView(view);
        builder.setPositiveButton("Confirm", (dialog, id) -> {
            //user confirmed
            alphaValue = alphaPickerBar.getColorValue();
            redValue = redPickerBar.getColorValue();
            greenValue = greenPickerBar.getColorValue();
            blueValue = bluePickerBar.getColorValue();
            mListener.onColorConfirmed(ColorPickerDialog.this, alphaValue, redValue, greenValue, blueValue);
        })
                .setNegativeButton("Cancel", (dialog, id) -> {
                    // User cancelled
                    mListener.onCancelled(ColorPickerDialog.this);
                });

        currentColorTV = view.findViewById(R.id.currentColorTV);

        alphaPickerBar = view.findViewById(R.id.alphaBar);
        redPickerBar = view.findViewById(R.id.redBar);
        greenPickerBar = view.findViewById(R.id.greenBar);
        bluePickerBar = view.findViewById(R.id.blueBar);

        alphaPickerBar.register(this);
        alphaPickerBar.register(redPickerBar);
        alphaPickerBar.register(greenPickerBar);
        alphaPickerBar.register(bluePickerBar);

        redPickerBar.register(this);
        redPickerBar.register(alphaPickerBar);
        redPickerBar.register(greenPickerBar);
        redPickerBar.register(bluePickerBar);

        greenPickerBar.register(this);
        greenPickerBar.register(alphaPickerBar);
        greenPickerBar.register(redPickerBar);
        greenPickerBar.register(bluePickerBar);

        bluePickerBar.register(this);
        bluePickerBar.register(alphaPickerBar);
        bluePickerBar.register(redPickerBar);
        bluePickerBar.register(greenPickerBar);

        Dialog dialog = builder.create();

        if (initialColor) {
            alphaPickerBar.setColor(alphaValue, redValue, greenValue, blueValue);
            redPickerBar.setColor(alphaValue, redValue, greenValue, blueValue);
            greenPickerBar.setColor(alphaValue, redValue, greenValue, blueValue);
            bluePickerBar.setColor(alphaValue, redValue, greenValue, blueValue);

            currentColorTV.setBackgroundColor(Color.argb(alphaValue, redValue, greenValue, blueValue));
            currentColorTV.setText(String.format("#%S%S%S%S", Integer.toHexString(alphaValue), Integer.toHexString(redValue), Integer.toHexString(greenValue), Integer.toHexString(blueValue)));
            initialColor = false;
        }

        if (savedInstanceState != null) {
            alphaValue = savedInstanceState.getInt("alpha");
            redValue = savedInstanceState.getInt("red");
            greenValue = savedInstanceState.getInt("green");
            blueValue = savedInstanceState.getInt("blue");
            alphaPickerBar.setColor(alphaValue, redValue, greenValue, blueValue);
            redPickerBar.setColor(alphaValue, redValue, greenValue, blueValue);
            greenPickerBar.setColor(alphaValue, redValue, greenValue, blueValue);
            bluePickerBar.setColor(alphaValue, redValue, greenValue, blueValue);

            currentColorTV.setBackgroundColor(Color.argb(alphaValue, redValue, greenValue, blueValue));
            currentColorTV.setText(String.format("#%S%S%S%S", Integer.toHexString(alphaValue), Integer.toHexString(redValue), Integer.toHexString(greenValue), Integer.toHexString(blueValue)));
        }

        return dialog;
    }

    public void setInitialColor(int alphaValue, int redValue, int greenValue, int blueValue) {
        initialColor = true;
        this.alphaValue = alphaValue;
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("alpha", alphaValue);
        outState.putInt("red", redValue);
        outState.putInt("green", greenValue);
        outState.putInt("blue", blueValue);
    }

    /* The activity/fragment that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface ColorPickerDialogListener {
        void onColorConfirmed(DialogFragment dialog, int alpha, int red, int green, int blue);

        void onCancelled(DialogFragment dialog);
    }
}
