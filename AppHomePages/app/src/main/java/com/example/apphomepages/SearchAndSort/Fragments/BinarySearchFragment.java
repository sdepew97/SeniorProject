package com.example.apphomepages.SearchAndSort.Fragments;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
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
import android.widget.SpinnerAdapter;

import com.example.apphomepages.General.HelperMethods.HelperMethods;
import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.Algorithms.SearchingAlgorithms;
import com.example.apphomepages.SearchAndSort.Animations.SearchAnimations;
import com.example.apphomepages.SearchAndSort.DialogueFragments.BinarySearchDialogueFragment;
import com.example.apphomepages.SearchAndSort.Drawable.ArraySearchDrawable;
import com.example.apphomepages.SearchAndSort.HelperMethods.SearchHelperMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BinarySearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BinarySearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BinarySearchFragment extends Fragment implements SpinnerAdapter
{
    //Global variables
    private ArrayList<Integer> numbers = null;
    private Integer soughtAfter = -1;
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable = new AnimationDrawable();
    private BinarySearchFragment.OnFragmentInteractionListener mListener = null;

    public BinarySearchFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BinarySearchFragment.
     */
    public static BinarySearchFragment newInstance()
    {
        BinarySearchFragment fragment = new BinarySearchFragment();
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
        //Set up variables
        final int bound = 10; //the max number of elements in the array
        final Random r = new Random();

        // Inflate the layout for this fragment
        final View viewGlobal = inflater.inflate(R.layout.fragment_binary_search, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = viewGlobal.findViewById(R.id.generateButtonBinary);
        Button startButton = viewGlobal.findViewById(R.id.startButtonBinary);
        Button stopButton = viewGlobal.findViewById(R.id.stopButtonBinary);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButtonBinary);
        Button proofButton = viewGlobal.findViewById(R.id.proofButtonBinary);
        Button instructionsButton = viewGlobal.findViewById(R.id.instructionsButtonBinary);
        final Spinner spinner = viewGlobal.findViewById(R.id.spinnerBinary);

        //Set Up
        populateScreen(r, bound, viewGlobal, spinner);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                populateScreen(r, bound, viewGlobal, spinner);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Set up variables for the method
                ArraySearchDrawable[] stopMotionAnimation;
                if ((spinner.getSelectedItem()).equals("Not Found"))
                {
                    soughtAfter = null;
                } else
                {
                    soughtAfter = Integer.valueOf((String) spinner.getSelectedItem());
                }

                ArrayList<Integer> squaresToHighlight = SearchingAlgorithms.binarySearchWithLocations(numbers, soughtAfter);
                int locationInArray;

                if (soughtAfter != null)
                {
                    locationInArray = squaresToHighlight.get(squaresToHighlight.size() - 1); //the last location is the place that we are trying to find
                } else
                {
                    locationInArray = -1;
                }

                stopMotionAnimation = new ArraySearchDrawable[squaresToHighlight.size() + 1];

                animationDrawable = new AnimationDrawable();
                image = viewGlobal.findViewById(R.id.imageViewBinary);
                SearchAnimations.generateBinarySearch(locationInArray, squaresToHighlight, numbers, stopMotionAnimation, image, animationDrawable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                //do nothing
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
                Uri uri = Uri.parse("http://www.csd.uwo.ca/courses/CS2210a/slides/correctness.pdf");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));

            }
        });

        instructionsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                BinarySearchDialogueFragment dialogFragment = new BinarySearchDialogueFragment();
                dialogFragment.show(fm, "Instructions Fragment");
            }
        });

        return viewGlobal;
    }

    private void populateScreen(Random r, int bound, View viewGlobal, Spinner spinner)
    {
        //Set up variables for the method
        ArraySearchDrawable[] stopMotionAnimation;
        int numElements = r.nextInt(bound) + 1; //want a value between 1 and 10, so 1-10 elements in the array
        animationDrawable = new AnimationDrawable();

        //Get random numbers
        numbers = HelperMethods.generateRandomArray(r, numElements);

        //Used https://beginnersbook.com/2013/12/how-to-sort-arraylist-in-java/ as a resource
        //Binary search requires a sorted array
        Collections.sort(numbers);

        //Run algorithm
        boolean targetFound = r.nextBoolean();
        soughtAfter = targetFound ? numbers.get(r.nextInt(numbers.size())) : -1;
        ArrayList<Integer> squaresToHighlight = SearchingAlgorithms.binarySearchWithLocations(numbers, soughtAfter);
        int locationInArray;

        if (soughtAfter != -1)
        {
            locationInArray = squaresToHighlight.get(squaresToHighlight.size() - 1); //the last location is the place that we are trying to find
        } else
        {
            locationInArray = -1;
        }

        //Populate the spinner and set initial random selection
        SearchHelperMethods.populateSpinner(numbers, viewGlobal, spinner, locationInArray);

        stopMotionAnimation = new ArraySearchDrawable[squaresToHighlight.size() + 1];

        image = viewGlobal.findViewById(R.id.imageViewBinary);
        SearchAnimations.generateBinarySearch(locationInArray, squaresToHighlight, numbers, stopMotionAnimation, image, animationDrawable);
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer)
    {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer)
    {

    }

    @Override
    public int getCount()
    {
        return 0;
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return null;
    }

    @Override
    public int getItemViewType(int position)
    {
        return 0;
    }

    @Override
    public int getViewTypeCount()
    {
        return 0;
    }

    @Override
    public boolean isEmpty()
    {
        return false;
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
