package com.example.apphomepages.Greedy.Fragments;

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

import com.example.apphomepages.General.DataTypes.DijkstrasReturnType;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.Greedy.Algorithms.GreedyAlgorithms;
import com.example.apphomepages.Greedy.Animations.GreedyAnimations;
import com.example.apphomepages.Greedy.DialogueFragments.DijkstrasDialogueFragment;
import com.example.apphomepages.Greedy.Drawable.DijkstrasDrawable;
import com.example.apphomepages.R;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DijkstrasFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DijkstrasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DijkstrasFragment extends Fragment
{
    int[][] graph = null; //the current graph
    private OnFragmentInteractionListener mListener;
    //Class variables
    private List<Integer> numbers = new ArrayList<>(); //numbers on nodes searched
    private Node<Integer> soughtAfter = null; //actual node being sought
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable = new AnimationDrawable();


    public DijkstrasFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DijkstrasFragment.
     */
    public static DijkstrasFragment newInstance()
    {
        DijkstrasFragment fragment = new DijkstrasFragment();
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
        final View viewGlobal = inflater.inflate(R.layout.fragment_dijkstras, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button startButton = viewGlobal.findViewById(R.id.startButtonDijkstras);
        Button stopButton = viewGlobal.findViewById(R.id.stopButtonDijkstras);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButtonDijkstras);
        Button proofButton = viewGlobal.findViewById(R.id.proofButtonDijkstras);
        Button instructionsButton = viewGlobal.findViewById(R.id.instructionsButtonDijkstras);

        //Set Up
        populateScreen(viewGlobal);

        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                animationDrawable.setVisible(true, true);
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
                DijkstrasDialogueFragment dialogFragment = new DijkstrasDialogueFragment();
                dialogFragment.show(fm, "Instructions Fragment");
            }
        });

        return viewGlobal;
    }

    private void populateScreen(View viewGlobal)
    {
        //Set up variables for the method
        DijkstrasDrawable[] stopMotionAnimation;

        //https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm is the graph used...
        graph = new int[][]{
                {0, 7, 9, 0, 0, 14},
                {7, 0, 10, 15, 0, 0},
                {9, 10, 0, 11, 0, 2},
                {0, 15, 11, 0, 6, 0},
                {0, 0, 0, 6, 0, 9},
                {14, 0, 2, 0, 9, 0}
        };

        ArrayList<DijkstrasReturnType> frames = GreedyAlgorithms.DijkstrasAlgorithm(graph, 0, graph.length);

        stopMotionAnimation = new DijkstrasDrawable[frames.size()];

        animationDrawable = new AnimationDrawable();
        image = viewGlobal.findViewById(R.id.imageViewDijkstras);

        GreedyAnimations.generateDijkstras(frames, graph, stopMotionAnimation, image, animationDrawable);
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
