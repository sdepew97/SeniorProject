package com.example.apphomepages.Graph.Fragments;

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

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.Helpers.HelperMethods;
import com.example.apphomepages.Graph.Algorithms.GraphAlgorithms;
import com.example.apphomepages.Graph.Animations.GraphAnimations;
import com.example.apphomepages.Graph.DialogFragments.BFSDialogFragment;
import com.example.apphomepages.Graph.Drawable.GraphSearchDrawable;
import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.HelperMethods.SearchHelperMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BFSFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BFSFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BFSFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;

    //Class variables
    private List<Integer> numbers = new ArrayList<>(); //numbers on nodes searched
    Graph<Integer> graph = null; //the current graph
    private Node<Integer> soughtAfter = null; //actual node being sought
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable = new AnimationDrawable();

    public BFSFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BFSFragment.
     */
    public static BFSFragment newInstance()
    {
        BFSFragment fragment = new BFSFragment();
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
        final View viewGlobal = inflater.inflate(R.layout.fragment_bf, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button startButton = viewGlobal.findViewById(R.id.startButtonBFS);
        Button stopButton = viewGlobal.findViewById(R.id.stopButtonBFS);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButtonBFS);
        Button proofButton = viewGlobal.findViewById(R.id.proofButtonBFS);
        Button instructionsButton = viewGlobal.findViewById(R.id.instructionsButtonBFS);
        Button generateButton = viewGlobal.findViewById(R.id.generateButtonBFS);
        final Spinner spinnerSelect = viewGlobal.findViewById(R.id.spinnerSelectBFS);

        //Get random initial index for spinner selections
        final Random r = new Random();

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Set up variables for the method
                GraphSearchDrawable[] stopMotionAnimation;
                numbers = HelperMethods.generateRandomArray(new Random(), r.nextInt(10) + 1);
                graph = HelperMethods.generateGraph(r.nextBoolean(), numbers);

                //populate the location spinner according to graph type selection a.k.a get a value to seek in the graph during the execution of the algorithm
                boolean targetFound = r.nextBoolean();
                int soughtAfterIndex = targetFound ? r.nextInt(numbers.size()) : -1; //"index" into the array of spinner elements

                //Run the algorithm to generate the animation frames
                if (soughtAfterIndex != -1)
                {
                    soughtAfter = graph.getGraphElements().get(soughtAfterIndex);

                    //populate the node selection list
                    //ArrayList<Integer> sortedNumbers = HelperMethods.copyList(numbers);
                    //Collections.sort(sortedNumbers);
                    SearchHelperMethods.populateSpinner(numbers, viewGlobal, spinnerSelect, numbers.indexOf(soughtAfter.getNodeValue()));

                } else
                {
                    soughtAfter = null; //the node was not found
                    SearchHelperMethods.populateSpinner(numbers, viewGlobal, spinnerSelect, -1); //-1 means the element was not found
                }

                ArrayList<Node<Integer>> nodesToHighlight = GraphAlgorithms.breadthFirstSearch(graph, soughtAfter, !targetFound);

                stopMotionAnimation = new GraphSearchDrawable[nodesToHighlight.size() + 2];

                animationDrawable = new AnimationDrawable();
                image = viewGlobal.findViewById(R.id.imageViewBFS);

                GraphAnimations.generateGraphSearch(targetFound, nodesToHighlight, graph, stopMotionAnimation, image, animationDrawable);
            }
        });

        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Set up variables for the method
                GraphSearchDrawable[] stopMotionAnimation;

                //Set the proper node value based on the item selected, which is the node's value
                Object itemSelected = spinnerSelect.getSelectedItem();
                ArrayList<Node<Integer>> nodes = graph.getGraphElements();

                //Run the algorithm to generate the animation frames
                boolean targetFound = false;

                if (!itemSelected.toString().equals("Not Found"))
                {
                    targetFound = true;
                    for (Node<Integer> n : nodes)
                    {
                        if (n.getNodeValue().toString().equals(itemSelected.toString()))
                        {
                            soughtAfter = n;
                            break;
                        }
                    }
                } else
                {
                    soughtAfter = null;
                }

                ArrayList<Node<Integer>> nodesToHighlight = GraphAlgorithms.breadthFirstSearch(graph, soughtAfter, !targetFound);

                stopMotionAnimation = new GraphSearchDrawable[nodesToHighlight.size() + 2];

                animationDrawable = new AnimationDrawable();
                image = viewGlobal.findViewById(R.id.imageViewBFS);

                GraphAnimations.generateGraphSearch(targetFound, nodesToHighlight, graph, stopMotionAnimation, image, animationDrawable);
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
                BFSDialogFragment dialogFragment = new BFSDialogFragment();
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
