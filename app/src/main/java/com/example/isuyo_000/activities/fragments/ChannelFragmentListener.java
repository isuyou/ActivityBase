package com.example.isuyo_000.activities.Fragments;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.Callable;

/**
 * Created by McLovin on 10/12/2017.
 */

public class ChannelFragmentListener {

    public final static double benchmarkModifier = 2.0;

    //listener class for the seek bars to allow them to change the editText values
    public static class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
        TextView target;
        Callable<Void> function;

        public SeekBarListener(TextView target, Callable<Void> function){
            this.target = target;
            this.function = function;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            //TODO
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            //TODO
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            double value = seekBar.getProgress();
            target.setText("" + value*benchmarkModifier/seekBar.getMax());
            try {
                function.call();
            }
            catch(Exception e){

            }
        }
    }

    //listener class for the edit text items to change the seekBar values on changes to the text
    public static  class EditTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

    }

    //listener class for the edit text items to change seekBar values once the user has finished editing the text
    public static class EditorActionListener implements EditText.OnEditorActionListener{
        SeekBar target;
        Callable<Void> function;

        public EditorActionListener(SeekBar target, Callable<Void> function){
            this.target = target;
            this.function = function;
        }

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE ||
                    event.getAction() == KeyEvent.ACTION_DOWN &&
                            event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                if (!event.isShiftPressed()) {
                    // the user is done typing.
                    matchTarget(v);
                    return true; // consume.
                }
            }
            return false; // pass on to other listeners.
        }

        //changes SeekBar value within bounds from the given number
        private void matchTarget(TextView textView){
            double newValue = 0.0;
            try {
                newValue = Double.parseDouble(textView.getText().toString());
                newValue = newValue * target.getMax()/benchmarkModifier;
                newValue = adjustTargetValue(newValue);

            }
            catch (Exception e){
                newValue = adjustTargetValue(0.0);
            }
            textView.setText("" + newValue*benchmarkModifier/target.getMax());
            try {
                function.call();
            }
            catch(Exception e){

            }
        }

        //finds the bounds of the seekbar and modifies the given number accordingly
        private double adjustTargetValue(double newValue){
            if(newValue > target.getMax()){
                newValue = target.getMax() + 0.0;
            }
            else if(newValue < 0.0){
                newValue = 0.0;
            }
            int setValue = (int)newValue;
            target.setProgress(setValue);
            return newValue;
        }
    }

    //attaches a 'function'  executable to specific clickable button
    public static class ButtonListener implements View.OnClickListener{
        Executable ex;
        public ButtonListener(Executable ex)
        {
            this.ex = ex;
        }


        @Override
        public void onClick(View view) {
            ex.execute();
        }
    }

}
