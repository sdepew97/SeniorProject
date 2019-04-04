package com.example.apphomepages.SearchAndSort.Fragments;

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

import com.example.apphomepages.General.DataTypes.SortReturnType;
import com.example.apphomepages.General.HelperMethods.HelperMethods;
import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.Algorithms.SortingAlgorithms;
import com.example.apphomepages.SearchAndSort.Animations.SortAnimations;
import com.example.apphomepages.SearchAndSort.DialogueFragments.InsertionSortDialogueFragment;
import com.example.apphomepages.SearchAndSort.Drawable.ArraySortDrawable;

import java.util.ArrayList;
import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import static com.example.apphomepages.SearchAndSort.Algorithms.SortingAlgorithms.copyArray;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InsertionSortFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InsertionSortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertionSortFragment extends Fragment
{
    //Global variables
    private ArrayList<Integer> numbers = null;
    private ImageView image = null;
    private AnimationDrawable animationDrawable = new AnimationDrawable();
    private InsertionSortFragment.OnFragmentInteractionListener mListener = null;

    public InsertionSortFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertionSortFragment.
     */
    public static InsertionSortFragment newInstance()
    {
        InsertionSortFragment fragment = new InsertionSortFragment();
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

    /*
     * The visualization, below, is inspired by the visualization of insertion mergeSort found on Wikipedia (https://en.wikipedia.org/wiki/Insertion_sort) in the "Algorithm" section
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Set up variables
        final int bound = 10; //the max number of elements in the array
        final Random r = new Random();

        // Inflate the layout for this fragment
        final View viewGlobal = inflater.inflate(R.layout.fragment_insertion_sort, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = viewGlobal.findViewById(R.id.generateButtonInsertion);
        Button startButton = viewGlobal.findViewById(R.id.startButtonInsertion);
        Button stopButton = viewGlobal.findViewById(R.id.stopButtonInsertion);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButtonInsertion);
        Button proofButton = viewGlobal.findViewById(R.id.proofButtonInsertion);
        Button instructionsButton = viewGlobal.findViewById(R.id.instructionsButtonInsertion);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Set up variables for the method
                ArraySortDrawable[] stopMotionAnimation;
                int numElements = r.nextInt(bound) + 1; //want a value between 1 and 10, so 1-10 elements in the array
                animationDrawable = new AnimationDrawable();

                //Get random numbers
                numbers = HelperMethods.generateRandomArray(r, numElements);

                //Run algorithm
                ArrayList<Integer> originalNumbers = copyArray(numbers);
                ArrayList<SortReturnType> iterations = SortingAlgorithms.insertionSort(numbers);
                ArrayList<Integer> squaresToHighlight = new ArrayList<>();
                squaresToHighlight.add(-1);

                stopMotionAnimation = new ArraySortDrawable[iterations.size() + 1 + 1];

                image = viewGlobal.findViewById(R.id.imageViewInsertion);

                SortAnimations.generateInsertionSort(originalNumbers, squaresToHighlight, iterations, numbers, stopMotionAnimation, image, animationDrawable);
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
                Uri uri = Uri.parse("http://courses.ece.ubc.ca/320/notes/InsertionSort.pdf");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        instructionsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                InsertionSortDialogueFragment dialogFragment = new InsertionSortDialogueFragment();
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
