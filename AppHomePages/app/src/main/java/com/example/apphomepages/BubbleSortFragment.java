package com.example.apphomepages;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static com.example.apphomepages.SortingAlgorithms.copyArray;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BubbleSortFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BubbleSortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BubbleSortFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int currentCount = 0;
    private int bound = 10; //the max number of elements in the array
    private ArrayList<Integer> numbers = null;
    private Random r = new Random();
    private int durration = 1000;
    private ImageView image;
    private ArraySortDrawable[] stopMotionAnimation = null;
    private AnimationDrawable animationDrawable = new AnimationDrawable();
    private BubbleSortFragment.OnFragmentInteractionListener mListener = null;

    public BubbleSortFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BubbleSortFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BubbleSortFragment newInstance(String param1, String param2) {
        BubbleSortFragment fragment = new BubbleSortFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /*
     * The visualization, below, is inspired by the visualization of bubble sort found on Wikipedia (https://en.wikipedia.org/wiki/Bubble_sort) in the "Example" section
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View viewGlobal = inflater.inflate(R.layout.fragment_bubble_sort, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = viewGlobal.findViewById(R.id.generateButton);
        Button startButton = viewGlobal.findViewById(R.id.startButton);
        Button stopButton = viewGlobal.findViewById(R.id.stopButton);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable = new AnimationDrawable();
                currentCount = 0; //make sure we are starting at 0 in the array

                int numElements = r.nextInt(bound) + 1; //want a value between 1 and 10, so 1-10 elements in the array
                numbers = new ArrayList<Integer>(numElements); //random numbers

                for (int j = 0; j < numElements; j++) {
                    int randomInt = r.nextInt(bound * 10);
                    if (r.nextBoolean()) {
                        numbers.add(j, (-randomInt));
                    } else {
                        numbers.add(j, randomInt);
                    }
                }

                ArrayList<Integer> originalNumbers = copyArray(numbers);
                ArrayList<Tuple> iterations = SortingAlgorithms.bubbleSort(numbers);
                ArrayList<Integer> squaresToHighlight = new ArrayList<>();
                squaresToHighlight.add(-1);

                stopMotionAnimation = new ArraySortDrawable[iterations.size()];

                //setup main frame
                stopMotionAnimation[0] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), squaresToHighlight, -1, originalNumbers);

                for (int i = 1; i <= stopMotionAnimation.length-2; i++) {
                    stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), iterations.get(i-1).constructPair(), -1, iterations.get(i - 1).getList());
                }

                stopMotionAnimation[stopMotionAnimation.length-1] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), squaresToHighlight, iterations.size()-1, iterations.get(stopMotionAnimation.length - 2).getList());

                image = viewGlobal.findViewById(R.id.imageView);

                for (Drawable d : stopMotionAnimation) {
                    animationDrawable.addFrame(d, durration);
                }

                animationDrawable.setOneShot(false);
                image.setBackgroundDrawable(animationDrawable);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
            }
        });

        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.setVisible(true, true);
                animationDrawable.stop();
            }
        });

        return viewGlobal;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
