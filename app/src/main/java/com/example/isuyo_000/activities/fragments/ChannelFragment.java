package com.example.isuyo_000.activities.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.isuyo_000.activities.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ChannelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PAGE = "ARG_PAGE";

    // TODO: Rename and change types of parameters
    private int mPage;

    //object references
    private SeekBar pulseWidthBar;
    private SeekBar amplitudeBar;
    private TextView pulseWidthValueText;
    private TextView amplitudeValueText;

    //parent adapter reference
    private ChannelFragmentAdapter parent;



    public ChannelFragment() {
        //proxy constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChannelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelFragment newInstance(int page, ChannelFragmentAdapter parent) {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        fragment.setParent(parent);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            super.onCreate(savedInstanceState);
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

    //TODO add save button functionality
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.activity_channel_limits, container, false);
        view = attachSeekBarListener(view);

        return view;
    }

    //sets the
    private void setParent(ChannelFragmentAdapter parent){
        this.parent = parent;
    }



    //attaches listeners for the scrolling tab and the text
    private View attachSeekBarListener(View view){
        //references to screen objects
        amplitudeBar = (SeekBar) view.findViewById(R.id.AmplitudeBar);
        amplitudeValueText = (EditText) view.findViewById(R.id.AmplitudeValueIndicator);
        pulseWidthBar = (SeekBar) view.findViewById(R.id.PulseWidthBar);
        pulseWidthValueText = (EditText) view.findViewById(R.id.PulseWidthValueIndicator);


        //adds controller for the pulse width scrollBar
        amplitudeBar.setOnSeekBarChangeListener(new ChannelFragmentListener.SeekBarListener(amplitudeValueText));
        amplitudeValueText.setOnEditorActionListener(new ChannelFragmentListener.EditorActionListener(amplitudeBar));

        pulseWidthBar.setOnSeekBarChangeListener(new ChannelFragmentListener.SeekBarListener(pulseWidthValueText));
        pulseWidthValueText.setOnEditorActionListener(new ChannelFragmentListener.EditorActionListener(pulseWidthBar));
        return view;
    }

    //attaches listeners and references to the buttons on the fragment page
    private View attachButtonListeners(View view){
        Button testButton = (Button) view.findViewById(R.id.ChannelTestButton);
        Button saveButton = (Button) view.findViewById(R.id.ChannelSaveButton);
        Button finishButton = (Button) view.findViewById(R.id.ChannelFinishButton);

        if(parent != null) {
            saveButton.setOnClickListener(new ChannelFragmentListener.ButtonListener(new Executable() {
                @Override
                public void execute() {
                    parent.save();
                }
            }));
        }

        return view;
    }

    private void storedCode(){
        /*
        switch (mPage) {
            case 1:

                break;
            case 2:
                view = inflater.inflate(R.layout.activity_channel_limits, container, false);
                break;
            default:
                view = inflater.inflate(R.layout.activity_channel_limits, container, false);
                break;
        }
        */
    }

    //Saves data from the current onto the disk
    public Channel save(){
        return new Channel(amplitudeBar.getProgress(), pulseWidthBar.getProgress());
    }





}
