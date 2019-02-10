package com.example.apphomepages.Fragments;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.apphomepages.Algorithms.SearchingAlgorithms;
import com.example.apphomepages.Animations.SearchAnimations;
import com.example.apphomepages.Drawable.ArraySearchDrawable;
import com.example.apphomepages.General.HelperMethods;
import com.example.apphomepages.General.SearchHelperMethods;
import com.example.apphomepages.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BinarySearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BinarySearchFragment newInstance(String param1, String param2)
    {
        BinarySearchFragment fragment = new BinarySearchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Set up variables
        final int bound = 10; //the max number of elements in the array
        final Random r = new Random();

        // Inflate the layout for this fragment
        final View viewGlobal = inflater.inflate(R.layout.fragment_linear_search, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = viewGlobal.findViewById(R.id.generateButton);
        Button startButton = viewGlobal.findViewById(R.id.startButton);
        Button stopButton = viewGlobal.findViewById(R.id.stopButton);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButton);
        final Spinner spinner = viewGlobal.findViewById(R.id.spinner);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Set up variables for the method
                ArraySearchDrawable[] stopMotionAnimation;
                int numElements = r.nextInt(bound) + 1; //want a value between 1 and 10, so 1-10 elements in the array
                animationDrawable = new AnimationDrawable();

                //Get random numbers
                numbers = HelperMethods.generateRandomArray(r, numElements, bound);

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

                image = viewGlobal.findViewById(R.id.imageView);
                SearchAnimations.generateBinarySearch(locationInArray, squaresToHighlight, numbers, stopMotionAnimation, image, animationDrawable);
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
                image = viewGlobal.findViewById(R.id.imageView);
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

        return viewGlobal;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
