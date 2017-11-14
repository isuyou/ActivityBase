package com.example.isuyo_000.activities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.isuyo_000.activities.R;
import com.example.isuyo_000.activities.UserData.PatientSettings;

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

    //reference to overarching user/patient values
    private PatientSettings user;

    //proxy constructor
    public ChannelFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChannelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelFragment newInstance(int page, ChannelFragmentAdapter parent, PatientSettings user) {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);

        fragment.setArguments(args);
        fragment.setParent(parent);
        fragment.setUser(user);
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
        view = inflater.inflate(R.layout.settings_channel_limits, container, false);
        view = attachSeekBarListener(view);
        attachButtonListeners(view);

        return view;
    }

    //sets the parent adapter reference in the fragment
    private ChannelFragment setParent(ChannelFragmentAdapter parent){
        this.parent = parent;
        return this;
    }

    //set the user values' reference in the fragment
    private ChannelFragment setUser(PatientSettings user){
        this.user = user;
        return this;
    }



    //attaches listeners for the scrolling tab and the text
    private View attachSeekBarListener(View view){
        //references to screen objects
        amplitudeBar = (SeekBar) view.findViewById(R.id.AmplitudeBar);
        amplitudeValueText = (EditText) view.findViewById(R.id.AmplitudeValueIndicator);
        pulseWidthBar = (SeekBar) view.findViewById(R.id.PulseWidthBar);
        pulseWidthValueText = (EditText) view.findViewById(R.id.PulseWidthValueIndicator);

        int amplitudeBarScale = amplitudeBar.getMax();
        int pulseWidthBarScale = pulseWidthBar.getMax();

        //initializes values for pulse width and amplitude bars/texts
        double amplitudeValue = user.getAmplitudeLimits()[mPage];
        amplitudeBar.setProgress((int) amplitudeValue * amplitudeBarScale);
        amplitudeValueText.setText("" + amplitudeValue);

        double pulseWidthValue = user.getPulsewidthLimits()[mPage];
        pulseWidthBar.setProgress((int) pulseWidthValue * pulseWidthBarScale);
        pulseWidthValueText.setText("" + pulseWidthValue);


        //adds controller for the amplitude and pulse width scrollBar
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
                view = inflater.inflate(R.layout.settings_channel_limits, container, false);
                break;
            default:
                view = inflater.inflate(R.layout.settings_channel_limits, container, false);
                break;
        }
        */
    }

    //Saves data from the current accessed channel onto the disk
    public Channel save(){
        return new Channel(amplitudeBar.getProgress(), amplitudeBar.getMax(), pulseWidthBar.getProgress(), pulseWidthBar.getMax());
    }





}
