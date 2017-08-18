package com.example.isuyo_000.activities.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.isuyo_000.activities.R;

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


    public ChannelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChannelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelFragment newInstance(int page) {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        switch (mPage) {
            case 1:
                view = inflater.inflate(R.layout.activity_channel_limits, container, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.activity_channel_limits, container, false);
                break;
            default:
                view = inflater.inflate(R.layout.activity_channel_limits, container, false);
                break;
        }
        return view;
    }
}
