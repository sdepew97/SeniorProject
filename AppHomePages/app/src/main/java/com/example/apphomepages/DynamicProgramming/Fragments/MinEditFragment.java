package com.example.apphomepages.DynamicProgramming.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.apphomepages.DynamicProgramming.Algorithms.DynamicProgrammingAlgorithms;
import com.example.apphomepages.DynamicProgramming.Animations.DynamicProgrammingAnimations;
import com.example.apphomepages.DynamicProgramming.DialogueFragments.MinEditDialogueFragment;
import com.example.apphomepages.DynamicProgramming.Drawable.MinEditDrawable;
import com.example.apphomepages.General.DataTypes.MinEditReturnType;
import com.example.apphomepages.R;

import java.util.ArrayList;
import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MinEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MinEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MinEditFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;

    //Class variables
    private int N = 4; //Default board size is 4x4
    private int[] possibleBoardSizes = {4, 5, 6, 7, 8};
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable = new AnimationDrawable();

    public MinEditFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MinEditFragment.
     */
    public static MinEditFragment newInstance()
    {
        MinEditFragment fragment = new MinEditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View viewGlobal = inflater.inflate(R.layout.fragment_minedit, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button startButton = viewGlobal.findViewById(R.id.startButtonMinEdit);
        Button stopButton = viewGlobal.findViewById(R.id.stopButtonMinEdit);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButtonMinEdit);
        Button proofButton = viewGlobal.findViewById(R.id.proofButtonMinEdit);
        Button instructionsButton = viewGlobal.findViewById(R.id.instructionsButtonMinEdit);
        Button generateButton = viewGlobal.findViewById(R.id.generateButtonMinEdit);

        //Get random initial index for the board size selection
        final Random r = new Random();

        //Create a list of similar words
        final String[] words = {"dolphin", "turtle", "albatross", "armadillo", "rabbit", "pig", "dove", "chickadee", "eagle", "cat", "dog", "cow", "longhorn", "tiger", "sphinx", "greyhound", "zebra", "gorilla", "bear", "panther"};

        //Set Up
        populateScreen(r, words, viewGlobal);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                populateScreen(r, words, viewGlobal);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animationDrawable.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animationDrawable.stop();
            }
        });

        rewindButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animationDrawable.setVisible(true, true);
                animationDrawable.stop();
            }
        });

        proofButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Open a webpage! (resource used to figure out code at https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application)
                //TODO: replace with proper resource!
                Uri uri = Uri.parse("http://home.cse.ust.hk/faculty/golin/COMP271Sp03/Notes/MyL08.pdf");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        instructionsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                MinEditDialogueFragment dialogFragment = new MinEditDialogueFragment();
                dialogFragment.show(fm, "Instructions Fragment");
            }
        });

        return viewGlobal;
    }

    private void populateScreen(Random r, String[] words, View viewGlobal)
    {
        //Set up variables for the method
        MinEditDrawable[] stopMotionAnimation;

        //populate the location spinner according to graph type selection a.k.a get a value to seek in the graph during the execution of the algorithm
        int boardSizeIndex = r.nextInt(possibleBoardSizes.length);  //"index" into the array of board sizes
        N = possibleBoardSizes[boardSizeIndex];

        String target = words[r.nextInt(words.length)];
        String source = words[r.nextInt(words.length)];

        ArrayList<MinEditReturnType> frames = DynamicProgrammingAlgorithms.minEditDist(target, source);

        stopMotionAnimation = new MinEditDrawable[frames.size()];

        animationDrawable = new AnimationDrawable();
        image = viewGlobal.findViewById(R.id.imageViewMinEdit);

        DynamicProgrammingAnimations.generateMinEditDistance(frames, target, source, stopMotionAnimation, image, animationDrawable);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
    }
}
