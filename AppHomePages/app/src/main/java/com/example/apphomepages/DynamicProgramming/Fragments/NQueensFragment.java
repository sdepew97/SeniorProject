package com.example.apphomepages.DynamicProgramming.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.apphomepages.DynamicProgramming.Algorithms.DynamicProgrammingAlgorithms;
import com.example.apphomepages.DynamicProgramming.Animations.DynamicProgrammingAnimations;
import com.example.apphomepages.DynamicProgramming.DialogueFragments.NQueensDialogueFragment;
import com.example.apphomepages.DynamicProgramming.Drawable.NQueensDrawable;
import com.example.apphomepages.DynamicProgramming.HelperMethods.DynamicProgrammingHelperMethods;
import com.example.apphomepages.General.DataTypes.NQueensReturnType;
import com.example.apphomepages.R;

import java.util.ArrayList;
import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NQueensFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NQueensFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NQueensFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;

    //Class variables
    private int N = 4; //Default board size is 4x4
    private int[] possibleBoardSizes = {4, 5, 6, 7, 8};
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable = new AnimationDrawable();

    public NQueensFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NQueensFragment.
     */
    public static NQueensFragment newInstance()
    {
        NQueensFragment fragment = new NQueensFragment();
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
        final View viewGlobal = inflater.inflate(R.layout.fragment_nqueens, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button startButton = viewGlobal.findViewById(R.id.startButtonNQueens);
        Button stopButton = viewGlobal.findViewById(R.id.stopButtonNQueens);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButtonNQueens);
        Button proofButton = viewGlobal.findViewById(R.id.proofButtonNQueens);
        Button instructionsButton = viewGlobal.findViewById(R.id.instructionsButtonNQueens);
        Button generateButton = viewGlobal.findViewById(R.id.generateButtonNQueens);
        final Spinner spinnerSelect = viewGlobal.findViewById(R.id.spinnerSelectNQueens);

        //Get random initial index for the board size selection
        final Random r = new Random();

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Set up variables for the method
                NQueensDrawable[] stopMotionAnimation;

                //populate the location spinner according to graph type selection a.k.a get a value to seek in the graph during the execution of the algorithm
                int boardSizeIndex = r.nextInt(possibleBoardSizes.length);  //"index" into the array of board sizes
                N = possibleBoardSizes[boardSizeIndex];

                //Create a board
                boolean[][] board = new boolean[N][N];

                for (int i = 0; i < N; i++)
                {
                    for (int j = 0; j < N; j++)
                    {
                        board[i][j] = false;
                    }
                }

                DynamicProgrammingHelperMethods.populateSpinner(possibleBoardSizes, viewGlobal, spinnerSelect, boardSizeIndex);

                ArrayList<NQueensReturnType> frames = DynamicProgrammingAlgorithms.solveNQ(board);

                stopMotionAnimation = new NQueensDrawable[frames.size()];

                animationDrawable = new AnimationDrawable();
                image = viewGlobal.findViewById(R.id.imageViewNQueens);

                DynamicProgrammingAnimations.generateNQueens(frames, stopMotionAnimation, image, animationDrawable);
            }
        });

        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Set up variables for the method
                NQueensDrawable[] stopMotionAnimation;

                //Set the proper node value based on the item selected, which is the node's value
                int boardSizeIndex = spinnerSelect.getSelectedItemPosition();
                N = possibleBoardSizes[boardSizeIndex];

                //Create a board
                boolean[][] board = new boolean[N][N];

                for (int i = 0; i < N; i++)
                {
                    for (int j = 0; j < N; j++)
                    {
                        board[i][j] = false;
                    }
                }

                ArrayList<NQueensReturnType> frames = DynamicProgrammingAlgorithms.solveNQ(board);

                stopMotionAnimation = new NQueensDrawable[frames.size()];

                animationDrawable = new AnimationDrawable();
                image = viewGlobal.findViewById(R.id.imageViewNQueens);

                DynamicProgrammingAnimations.generateNQueens(frames, stopMotionAnimation, image, animationDrawable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                //Do nothing
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
                NQueensDialogueFragment dialogFragment = new NQueensDialogueFragment();
                dialogFragment.show(fm, "Instructions Fragment");
            }
        });

        return viewGlobal;
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
