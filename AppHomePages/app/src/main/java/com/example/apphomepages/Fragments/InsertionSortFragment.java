package com.example.apphomepages.Fragments;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.apphomepages.Algorithms.SortingAlgorithms;
import com.example.apphomepages.Animations.SortAnimations;
import com.example.apphomepages.Datatypes.Tuple;
import com.example.apphomepages.Drawable.ArraySortDrawable;
import com.example.apphomepages.R;

import java.util.ArrayList;
import java.util.Random;

import static com.example.apphomepages.Algorithms.SortingAlgorithms.copyArray;


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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertionSortFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertionSortFragment newInstance(String param1, String param2)
    {
        InsertionSortFragment fragment = new InsertionSortFragment();
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
    }

    /*
     * The visualization, below, is inspired by the visualization of insertion sort found on Wikipedia (https://en.wikipedia.org/wiki/Insertion_sort) in the "Algorithm" section
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
        Button generateButton = viewGlobal.findViewById(R.id.generateButton);
        Button startButton = viewGlobal.findViewById(R.id.startButton);
        Button stopButton = viewGlobal.findViewById(R.id.stopButton);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButton);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Set up variables for the method
                ArraySortDrawable[] stopMotionAnimation;
                //TODO: remove this comment
                // int numElements = r.nextInt(bound) + 1; //want a value between 1 and 10, so 1-10 elements in the array
                animationDrawable = new AnimationDrawable();

                //Get random numbers
                //TODO: remove this comment
                // numbers = HelperMethods.generateRandomArray(r, numElements, bound);

                //int numElements = r.nextInt(bound) + 1; //want a value between 1 and 10, so 1-10 elements in the array
                int numElements = 8;
                numbers = new ArrayList<Integer>(numElements); //random numbers
                /*
                for (int j = 0; j < numElements; j++) {
                    int randomInt = r.nextInt(bound * 10);
                    if (r.nextBoolean()) {
                        numbers.add(j, (-randomInt));
                    } else {
                        numbers.add(j, randomInt);
                    }
                }
                */

                numbers.add(6);
                numbers.add(5);
                numbers.add(3);
                numbers.add(1);
                numbers.add(8);
                numbers.add(7);
                numbers.add(2);
                numbers.add(4);

                //Run algorithm
                ArrayList<Integer> originalNumbers = copyArray(numbers);
                ArrayList<Tuple> iterations = SortingAlgorithms.insertionSort(numbers);
                ArrayList<Integer> squaresToHighlight = new ArrayList<>();
                squaresToHighlight.add(-1);

                stopMotionAnimation = new ArraySortDrawable[iterations.size() + 1 + 1];

                image = viewGlobal.findViewById(R.id.imageView);

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
