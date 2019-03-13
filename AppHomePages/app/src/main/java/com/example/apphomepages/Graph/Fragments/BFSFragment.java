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
import com.example.apphomepages.Graph.Algorithms.GraphAlgorithms;
import com.example.apphomepages.Graph.Animations.GraphAnimations;
import com.example.apphomepages.Graph.DialogFragments.BFSDialogFragment;
import com.example.apphomepages.Graph.Drawable.GraphSearchDrawable;
import com.example.apphomepages.Graph.HelperMethods.GraphHelperMethods;
import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.HelperMethods.SearchHelperMethods;

import java.util.ArrayList;
import java.util.Arrays;
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
    private int index = 0; //type of graph being searched
    private List<Integer> numbers = new ArrayList<>(); //numbers on nodes searched
    private Integer soughtAfter = -1; //actual node value being sought
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
    // TODO: Rename and change types and number of parameters
    public static BFSFragment newInstance()
    {
        BFSFragment fragment = new BFSFragment();
        Bundle args = new Bundle();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        final View viewGlobal = inflater.inflate(R.layout.fragment_bf, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button startButton = viewGlobal.findViewById(R.id.startButton);
        Button stopButton = viewGlobal.findViewById(R.id.stopButton);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButton);
        Button proofButton = viewGlobal.findViewById(R.id.proofButton);
        Button instructionsButton = viewGlobal.findViewById(R.id.instructionsButton);
        final Spinner spinner = viewGlobal.findViewById(R.id.spinner);
        final Spinner spinnerSelect = viewGlobal.findViewById(R.id.spinnerSelect);

        //Create an ArrayList of integers as the possible initial values
        //Used this resource to figure out the types of binary trees https://www.geeksforgeeks.org/binary-tree-set-3-types-of-binary-tree/
        //Used this resource to read about depth-first search https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/
        final ArrayList<String> treeTypes = new ArrayList<>();
        treeTypes.add("Tree");
        treeTypes.add("Graph");
        treeTypes.add("Graph with Cycles");

        //Construct the graphs to be used to search with depth-first search
        final ArrayList<List<Integer>> valueLists = new ArrayList<>();
        Integer treeValues[] = {18, 15, 30, 40, 50, 100, 70};
        Integer graphValues[] = {1, 2, 7, 8, 3, 6, 9, 12, 4, 5, 10, 11};
        Integer graphWithCycleValues[] = {12, 15, 17, 22, 21, 32, 16};
        valueLists.add(Arrays.asList(treeValues));
        valueLists.add(Arrays.asList(graphValues));
        valueLists.add(Arrays.asList(graphWithCycleValues));

        final ArrayList<Graph<Integer>> graphList = new ArrayList<>();
        graphList.add(createTreeGraph(treeValues));
        graphList.add(createGraphGraph(graphValues));
        graphList.add(createGraphWithCyclesGraph(graphWithCycleValues));

        //Get random initial index for spinner selections
        final Random r = new Random();
        index = r.nextInt(treeTypes.size());
        numbers = valueLists.get(index);

        //set spinner selection (length of array) randomly with initial length
        //used https://stackoverflow.com/questions/4486034/get-root-view-from-current-activity to figure out the view
        GraphHelperMethods.populateSpinner(treeTypes, viewGlobal, spinner, index);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Get the value of the item selected on the spinner to know the array to populate with
                index = spinner.getSelectedItemPosition();
                numbers = valueLists.get(index);

                //Set up variables for the method
                GraphSearchDrawable[] stopMotionAnimation;

                //populate the location spinner according to graph type selection a.k.a get a value to seek in the graph during the execution of the algorithm
                boolean targetFound = r.nextBoolean();
                soughtAfter = targetFound ? r.nextInt(numbers.size()) : -1; //"index" into the array of spinner elements

                //populate the node selection list
                SearchHelperMethods.populateSpinner(numbers, viewGlobal, spinnerSelect, soughtAfter);

                //Run the algorithm to generate the animation frames
                int valueInGraphSought = -1;
                if (soughtAfter != -1)
                {
                    valueInGraphSought = numbers.get(soughtAfter);
                }

                ArrayList<Integer> nodesToHighlight = GraphAlgorithms.breadthFirstSearch(graphList.get(index), valueInGraphSought, !targetFound);

                stopMotionAnimation = new GraphSearchDrawable[nodesToHighlight.size() + 2];

                animationDrawable = new AnimationDrawable();
                image = viewGlobal.findViewById(R.id.imageView);

                GraphAnimations.generateGraphSearch(targetFound, nodesToHighlight, graphList.get(index), stopMotionAnimation, image, animationDrawable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                //Do nothing
            }
        });

        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                //Set up variables for the method
                GraphSearchDrawable[] stopMotionAnimation;

                soughtAfter = (spinnerSelect.getSelectedItemPosition()) - 1;

                //Run the algorithm to generate the animation frames
                int valueInGraphSought = -1;
                boolean targetFound = false;

                if (soughtAfter != -1)
                {
                    targetFound = true;
                    valueInGraphSought = numbers.get(soughtAfter);
                }

                ArrayList<Integer> nodesToHighlight = GraphAlgorithms.breadthFirstSearch(graphList.get(index), valueInGraphSought, !targetFound);

                stopMotionAnimation = new GraphSearchDrawable[nodesToHighlight.size() + 2];

                animationDrawable = new AnimationDrawable();
                image = viewGlobal.findViewById(R.id.imageView);

                GraphAnimations.generateGraphSearch(targetFound, nodesToHighlight, graphList.get(index), stopMotionAnimation, image, animationDrawable);
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

    private Graph<Integer> createTreeGraph(Integer[] nodeValues)
    {
        //construct nodes with no connections
        ArrayList<Node<Integer>> nodes = new ArrayList<>();

        for (Integer i : nodeValues)
        {
            nodes.add(new Node<>(i));
        }

        //put in all desired connections
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        nodes.get(1).addAdjacentNode(nodes.get(3));
        nodes.get(3).addAdjacentNode(nodes.get(1));

        nodes.get(1).addAdjacentNode(nodes.get(4));
        nodes.get(4).addAdjacentNode(nodes.get(1));

        nodes.get(2).addAdjacentNode(nodes.get(5));
        nodes.get(5).addAdjacentNode(nodes.get(2));

        nodes.get(2).addAdjacentNode(nodes.get(6));
        nodes.get(6).addAdjacentNode(nodes.get(2));

        //return the graph
        return new Graph<>(nodes);
    }

    private Graph<Integer> createGraphGraph(Integer[] nodeValues)
    {
        //construct nodes with no connections
        ArrayList<Node<Integer>> nodes = new ArrayList<>();

        for (Integer i : nodeValues)
        {
            nodes.add(new Node<>(i));
        }

        //put in all desired connections
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(3));
        nodes.get(3).addAdjacentNode(nodes.get(0));

        nodes.get(1).addAdjacentNode(nodes.get(4));
        nodes.get(4).addAdjacentNode(nodes.get(1));

        nodes.get(1).addAdjacentNode(nodes.get(5));
        nodes.get(5).addAdjacentNode(nodes.get(1));

        nodes.get(3).addAdjacentNode(nodes.get(6));
        nodes.get(6).addAdjacentNode(nodes.get(3));

        nodes.get(3).addAdjacentNode(nodes.get(7));
        nodes.get(7).addAdjacentNode(nodes.get(3));

        nodes.get(4).addAdjacentNode(nodes.get(8));
        nodes.get(8).addAdjacentNode(nodes.get(4));

        nodes.get(4).addAdjacentNode(nodes.get(9));
        nodes.get(9).addAdjacentNode(nodes.get(4));

        nodes.get(6).addAdjacentNode(nodes.get(10));
        nodes.get(10).addAdjacentNode(nodes.get(6));

        nodes.get(6).addAdjacentNode(nodes.get(11));
        nodes.get(11).addAdjacentNode(nodes.get(6));

        //return the graph
        return new Graph<>(nodes);
    }

    private Graph<Integer> createGraphWithCyclesGraph(Integer[] nodeValues)
    {
        //construct nodes with no connections
        ArrayList<Node<Integer>> nodes = new ArrayList<>();

        for (Integer i : nodeValues)
        {
            nodes.add(new Node<>(i));
        }

        //put in all desired connections
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        nodes.get(1).addAdjacentNode(nodes.get(3));
        nodes.get(3).addAdjacentNode(nodes.get(1));

        nodes.get(2).addAdjacentNode(nodes.get(3));
        nodes.get(3).addAdjacentNode(nodes.get(2));

        nodes.get(2).addAdjacentNode(nodes.get(4));
        nodes.get(4).addAdjacentNode(nodes.get(2));

        nodes.get(2).addAdjacentNode(nodes.get(5));
        nodes.get(5).addAdjacentNode(nodes.get(2));

        nodes.get(4).addAdjacentNode(nodes.get(6));
        nodes.get(6).addAdjacentNode(nodes.get(4));

        //return the graph
        return new Graph<>(nodes);
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
