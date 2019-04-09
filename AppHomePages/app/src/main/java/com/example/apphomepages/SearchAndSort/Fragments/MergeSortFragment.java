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

import com.example.apphomepages.General.DataTypes.MergeSortReturnType;
import com.example.apphomepages.General.HelperMethods.HelperMethods;
import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.Algorithms.SortingAlgorithms;
import com.example.apphomepages.SearchAndSort.Animations.SortAnimations;
import com.example.apphomepages.SearchAndSort.DialogueFragments.MergeSortDialogueFragment;
import com.example.apphomepages.SearchAndSort.Drawable.ArrayMergeSortDrawable;
import com.example.apphomepages.SearchAndSort.HelperMethods.SortHelperMethods;

import java.util.ArrayList;
import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


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
     * @return A new instance of fragment MergeSortFragment.
     */
    public static MergeSortFragment newInstance()
    {
        MergeSortFragment fragment = new MergeSortFragment();
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
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View viewGlobal = inflater.inflate(R.layout.fragment_merge_sort, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = viewGlobal.findViewById(R.id.generateButtonMerge);
        Button startButton = viewGlobal.findViewById(R.id.startButtonMerge);
        Button stopButton = viewGlobal.findViewById(R.id.stopButtonMerge);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButtonMerge);
        Button proofButton = viewGlobal.findViewById(R.id.proofButtonMerge);
        Button instructionsButton = viewGlobal.findViewById(R.id.instructionsButtonMerge);
        final Spinner spinner = viewGlobal.findViewById(R.id.spinnerMerge);
        final Random r = new Random();

        //Create an ArrayList of integers as the possible initial values
        final ArrayList<Integer> lengths = new ArrayList<>();
        lengths.add(1); //Trivial Case
        lengths.add(5); //Odd Case
        lengths.add(6); //Even Case
        lengths.add(8); //Power of 2 Case

        //Set Up
        populateScreen(r, lengths, viewGlobal, spinner);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                populateScreen(r, lengths, viewGlobal, spinner);
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

                //Set up variables for the method
                ArrayMergeSortDrawable[] stopMotionAnimation;
                int numElements = lengths.get(index); //we want one of the length options in the dropdown spinner
                animationDrawable = new AnimationDrawable();

                //Get random numbers
                numbers = HelperMethods.generateRandomArray(r, numElements);

                //Run algorithm
                ArrayList<MergeSortReturnType> iterations = SortingAlgorithms.mergeSort(numbers, 0, numbers.size() - 1);

                //Convert each iteration to an ArrayList<ArrayList<Integer>> to represent the sections of numbers
                ArrayList<ArrayList<ArrayList<String>>> iterationPartitions = new ArrayList<>();

                ArrayList<ArrayList<String>> list = new ArrayList<>();
                list.add(SortHelperMethods.copyArrayString(iterations.get(0).getNumbers(), 0, iterations.get(0).getNumbers().size() - 1));
                iterationPartitions.add(list);

                int i = 1;
                int k = 0;
                while (i < iterations.size())
                {
                    ArrayList<String> currentPart;
                    if (!iterations.get(i).isMerging())
                    {
                        currentPart = SortHelperMethods.copyArrayString(iterations.get(i).getNumbers(), iterations.get(i).getLeft(), iterations.get(i).getRight());
                        k = 0; //restart the j-value after done merging
                    } else //we are in the case that we are merging...what should we do here?
                    {
                        //only copy j number elements for the array and then leave the remainder as blanks so that the merging of the arrays is clear
                        currentPart = SortHelperMethods.copyArrayString(iterations.get(i).getNumbers(), iterations.get(i).getLeft(), iterations.get(i).getLeft() + k);
                        for (int l = k; l < (iterations.get(i).getRight() - iterations.get(i).getLeft()); l++)
                        {
                            currentPart.add(" ");
                        }

                        k++; //increment the number of elements of the array copied
                    }

                    //Put the new piece in its proper place...
                    ArrayList<ArrayList<String>> lists = new ArrayList<>();

                    ArrayList<ArrayList<String>> otherPartLeft = new ArrayList<>();
                    ArrayList<ArrayList<String>> otherPartRight = new ArrayList<>();

                    //Add other left pieces, if they exist unless it is the other half
                    if (0 < iterations.get(i).getLeft())
                    {
                        for (int j = i - 1; j >= 0; j--)
                        {
                            if ((iterations.get(j).getRight() + 1 == iterations.get(i).getLeft()))
                            {
                                otherPartLeft = iterationPartitions.get(j);
                                break; //we want the first instance of this!
                            }
                        }
                    }

                    //Add other right pieces if they exist
                    if (iterations.get(i).getRight() < iterations.get(i).getNumbers().size() - 1)
                    {
                        for (int j = i - 1; j >= 0; j--)
                        {
                            if (iterations.get(i).getRight() + 1 == iterations.get(j).getLeft())
                            {
                                otherPartRight = iterationPartitions.get(j);
                                break; //we want the first instance of this!
                            }
                        }

                    }

                    //Combine all pieces together
                    if (otherPartLeft.size() > 0)
                        lists.addAll(otherPartLeft);

                    lists.add(currentPart);

                    if (otherPartRight.size() > 0)
                        lists.addAll(otherPartRight);


                    iterationPartitions.add(lists);
                    i++;
                }

                stopMotionAnimation = new ArrayMergeSortDrawable[iterations.size()];

                image = viewGlobal.findViewById(R.id.imageViewMerge);

                SortAnimations.generateMergeSort(iterationPartitions, stopMotionAnimation, image, animationDrawable);
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

        instructionsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getFragmentManager();
                MergeSortDialogueFragment dialogFragment = new MergeSortDialogueFragment();
                dialogFragment.show(fm, "Instructions Fragment");
            }
        });

        return viewGlobal;
    }

    private void populateScreen(Random r, ArrayList<Integer> lengths, View viewGlobal, Spinner spinner)
    {
        //Set up variables for the method
        index = r.nextInt(lengths.size()); //Get random initial index
        animationDrawable = new AnimationDrawable();

        //set spinner selection (length of array) randomly with initial length
        SortHelperMethods.populateSpinner(lengths, viewGlobal, spinner, index);
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
