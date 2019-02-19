package com.example.apphomepages.SearchAndSort.Fragments;

import android.content.Context;
import android.content.Intent;
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

import com.example.apphomepages.General.Helpers.HelperMethods;
import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.Drawable.ArraySearchDrawable;
import com.example.apphomepages.SearchAndSort.HelperMethods.SortHelperMethods;

import java.util.ArrayList;
import java.util.Random;

import static com.example.apphomepages.SearchAndSort.Algorithms.SortingAlgorithms.copyArray;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MergeSortFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MergeSortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MergeSortFragment extends Fragment implements SpinnerAdapter
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //Global variables
    private ArrayList<Integer> numbers = null;
    private int index = 0;
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable = new AnimationDrawable();
    private MergeSortFragment.OnFragmentInteractionListener mListener = null;

    public MergeSortFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MergeSortFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MergeSortFragment newInstance(String param1, String param2)
    {
        MergeSortFragment fragment = new MergeSortFragment();
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
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_merge_sort, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = view.findViewById(R.id.generateButton);
        Button startButton = view.findViewById(R.id.startButton);
        Button stopButton = view.findViewById(R.id.stopButton);
        Button rewindButton = view.findViewById(R.id.rewindButton);
        Button proofButton = view.findViewById(R.id.proofButton);
        final Spinner spinner = view.findViewById(R.id.spinner);

        //Create an ArrayList of integers as the possible initial values
        ArrayList<Integer> lengths = new ArrayList<>();
        lengths.add(1); //Trivial Case
        lengths.add(5); //Odd Case
        lengths.add(6); //Even Case

        //Get random initial index
        final Random r = new Random();
        index = r.nextInt(lengths.size());

        //set spinner selection (length of array) randomly with initial length
        SortHelperMethods.populateSpinner(lengths, view, spinner, index);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Set up variables for the method
                ArraySearchDrawable[] stopMotionAnimation;
                int numElements = index; //we want one of the length options in the dropdown spinner
                animationDrawable = new AnimationDrawable();

                //Get random numbers
                numbers = HelperMethods.generateRandomArray(r, numElements);

                //Run algorithm
                ArrayList<Integer> originalNumbers = copyArray(numbers);
                //TODO: finish body

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                index = spinner.getSelectedItemPosition();

                //check that the user has something selected and, if not, make sure a default size of 1 is selected
                if (index == -1)
                {
                    index = 0;
                }
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
                Uri uri = Uri.parse("https://www.cs.mcgill.ca/~dprecup/courses/IntroCS/Lectures/comp250-lecture16.pdf");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
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
