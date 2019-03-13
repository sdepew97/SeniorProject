package com.example.apphomepages.SearchAndSort.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.apphomepages.General.DataTypes.Tuple;
import com.example.apphomepages.General.Helpers.HelperMethods;
import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.Algorithms.SortingAlgorithms;
import com.example.apphomepages.SearchAndSort.Animations.SortAnimations;
import com.example.apphomepages.SearchAndSort.DialogFragments.QuickSortDialogFragment;
import com.example.apphomepages.SearchAndSort.Drawable.ArrayQuicksortDrawable;

import java.util.ArrayList;
import java.util.Random;

import static com.example.apphomepages.SearchAndSort.Algorithms.SortingAlgorithms.copyArray;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuicksortFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuicksortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuicksortFragment extends Fragment
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
    private int index = 0;
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable = new AnimationDrawable();
    private QuicksortFragment.OnFragmentInteractionListener mListener;

    public QuicksortFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuicksortFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuicksortFragment newInstance(String param1, String param2)
    {
        QuicksortFragment fragment = new QuicksortFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        final View view = inflater.inflate(R.layout.fragment_quicksort, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = view.findViewById(R.id.generateButton);
        Button startButton = view.findViewById(R.id.startButton);
        Button stopButton = view.findViewById(R.id.stopButton);
        Button rewindButton = view.findViewById(R.id.rewindButton);
        Button proofButton = view.findViewById(R.id.proofButton);
        Button instructionsButton = view.findViewById(R.id.instructionsButton);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Set up variables for the method
                ArrayQuicksortDrawable[] stopMotionAnimation;
                int numElements = r.nextInt(bound) + 1; //want a value between 1 and 10, so 1-10 elements in the array
                animationDrawable = new AnimationDrawable();

                //Get random numbers
                numbers = HelperMethods.generateRandomArray(r, numElements);

                //Run algorithm
                ArrayList<Integer> originalNumbers = copyArray(numbers);
                ArrayList<Tuple> iterations = SortingAlgorithms.quicksort(numbers, 0, numbers.size() - 1);

                stopMotionAnimation = new ArrayQuicksortDrawable[iterations.size() + 2];

                image = view.findViewById(R.id.imageView);
                SortAnimations.generateQuicksort(originalNumbers, iterations, stopMotionAnimation, image, animationDrawable);
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
                Uri uri = Uri.parse("https://www.cs.mcgill.ca/~dprecup/courses/IntroCS/Lectures/comp250-lecture17.pdf");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        instructionsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                QuickSortDialogFragment dialogFragment = new QuickSortDialogFragment();
                dialogFragment.show(fm, "Instructions Fragment");
            }
        });

        return view;
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
