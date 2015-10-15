package com.wohlig.rohan.customcolorpicker3;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TimePicker;

import com.ak.android.widget.colorpickerseekbar.ColorPickerSeekBar;

import java.util.Date;


/**
 * Created by Rohan on 13-10-2015.
 */
public class AlertDFragment extends DialogFragment {
    DatePicker datePicker;
    TimePicker timePicker;
    int color_selected=0;
    long note_id;
    ColorPickerSeekBar colorPickerSeekBar=null;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialogfragment, null, false);
        final ViewParent parentView=view.getParent();
        final View v = view.findViewById(R.id.view);
        final MainActivity parentActivity=(MainActivity)getActivity();
        colorPickerSeekBar=(ColorPickerSeekBar)view.findViewById(R.id.colorpicker);
        colorPickerSeekBar.setOnColorSeekbarChangeListener(new ColorPickerSeekBar.OnColorSeekBarChangeListener() {
            @Override
            public void onColorChanged(SeekBar seekBar, int color, boolean fromUser) {

                v.setBackgroundColor(color);
                color_selected=color;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(view)

                .setPositiveButton("Set", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String strColor = String.format("#%06X", 0xFFFFFF & color_selected);
                        parentActivity.setColor(strColor);
                    }


                })

                        // Negative Button
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something else
                    }
                }).create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        note_id=Long.parseLong(getArguments().getString("id"));
        Log.d("Rohan", String.valueOf(note_id));
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}