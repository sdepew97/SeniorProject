package com.example.apphomepages.Graph.Activities;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

public class TopologicalOrderingActivity extends AppCompatActivity
{
    //Class variables
    private int index = 0; //type of graph being searched
    private List<Integer> numbers = new ArrayList<>(); //numbers on nodes searched
    private Integer soughtAfter = -1; //actual node value being sought
    private ImageView image = null;

    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topological_ordering);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = findViewById(R.id.generateButton);
        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button rewindButton = findViewById(R.id.rewindButton);
        Button proofButton = findViewById(R.id.proofButton);

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
                } catch (RuntimeException r) {
                    Log.e("Topological Ordering", String.valueOf(r));
                }

                stopMotionAnimation = new TopologicalOrderingDrawable[nodesToHighlight.size() + 2];

                animationDrawable = new AnimationDrawable();
                image = getWindow().getDecorView().getRootView().findViewById(R.id.imageView);

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
}
