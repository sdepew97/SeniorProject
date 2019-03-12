package com.example.apphomepages.Graph.Fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.Helpers.HelperMethods;
import com.example.apphomepages.Graph.Algorithms.GraphAlgorithms;
import com.example.apphomepages.Graph.Animations.GraphAnimations;
import com.example.apphomepages.Graph.Drawable.TopologicalOrderingDrawable;
import com.example.apphomepages.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopologicalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopologicalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopologicalFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Class variables
    private int index = 0; //type of graph being searched
    private List<Integer> numbers = new ArrayList<>(); //numbers on nodes searched
    private Integer soughtAfter = -1; //actual node value being sought
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable;

    public TopologicalFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopologicalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopologicalFragment newInstance(String param1, String param2)
    {
        TopologicalFragment fragment = new TopologicalFragment();
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
        // Inflate the layout for this fragment
        final View viewGlobal = inflater.inflate(R.layout.fragment_topological, container, false);

        {
            //Set up the buttons and clickable elements on the fragment
            Button generateButton = viewGlobal.findViewById(R.id.generateButton);
            Button startButton = viewGlobal.findViewById(R.id.startButton);
            Button stopButton = viewGlobal.findViewById(R.id.stopButton);
            Button rewindButton = viewGlobal.findViewById(R.id.rewindButton);
            Button proofButton = viewGlobal.findViewById(R.id.proofButton);

            //Construct the names of the graph's nodes
            String[] nodeValues = {"Algebra 1", "Geometry", "Algebra 2", "Trigonometry", "Statistics", "Probability", "Pre-Calculus", "Calculus I", "AP Calculus"};
            ArrayList<String> nodeValuesList = HelperMethods.convertFromArray(nodeValues);
            final Graph<String> g = createDAG(nodeValuesList);

            generateButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //Set up variables for the method
                    TopologicalOrderingDrawable[] stopMotionAnimation;
                    ArrayList<String> nodesToHighlight = new ArrayList<>();

                    try
                    {
                        nodesToHighlight = GraphAlgorithms.topologicalOrdering(g);
                    } catch (RuntimeException r)
                    {
                        Log.e("Topological Ordering", String.valueOf(r));
                    }

                    stopMotionAnimation = new TopologicalOrderingDrawable[nodesToHighlight.size() + 2];

                    animationDrawable = new AnimationDrawable();
                    image = viewGlobal.findViewById(R.id.imageView);

                    GraphAnimations.generateTopologicalGraphOrdering(nodesToHighlight, g, stopMotionAnimation, image, animationDrawable);
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
                    Uri uri = Uri.parse("http://www.columbia.edu/~cs2035/courses/csor4231.F15/ts.pdf"); //TODO: find proof that doesn't require downloading anything!
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            });
        }

        return viewGlobal;
    }

    private Graph<String> createDAG(ArrayList<String> nodeValuesList)
    {
        //construct nodes with no connections
        ArrayList<Node<String>> nodes = new ArrayList<>();

        for (String s : nodeValuesList)
        {
            nodes.add(new Node<>(s));
        }

        //put in all desired connections to create the graph; note that all connections must be acyclic! (because of topological ordering..
        nodes.get(0).addAdjacentNode(nodes.get(1)); //Algebra 1 --> Geometry
        nodes.get(0).addAdjacentNode(nodes.get(2)); //Algebra 1 --> Algebra 2

        nodes.get(1).addAdjacentNode(nodes.get(2)); //Geometry --> Algebra 2

        //nodes.get(2).addAdjacentNode(nodes.get(1)); //Algebra 2 --> Geometry
        nodes.get(2).addAdjacentNode(nodes.get(3)); //Algebra 2 --> Trigonometry

        nodes.get(3).addAdjacentNode(nodes.get(6)); //Trigonometry --> Pre-Calculus

        nodes.get(4).addAdjacentNode(nodes.get(6)); //Statistics --> Pre-Calculus

        nodes.get(5).addAdjacentNode(nodes.get(6)); //Probability --> Pre-Calculus

        nodes.get(6).addAdjacentNode(nodes.get(7)); //Pre-Calculus --> Calculus I
        nodes.get(6).addAdjacentNode(nodes.get(8)); //Pre-Calculus --> AP Calculus

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
