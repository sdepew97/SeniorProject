package com.example.apphomepages.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.apphomepages.General.GraphHelperMethods;
import com.example.apphomepages.R;

import java.util.ArrayList;
import java.util.Random;

public class BreadthFirstSearchActivity extends AppCompatActivity
{
    //Global variables
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breadth_first_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = findViewById(R.id.generateButton);
        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);
        Button rewindButton = findViewById(R.id.rewindButton);
        Button proofButton = findViewById(R.id.proofButton);
        final Spinner spinner = findViewById(R.id.spinner);

        //Create an ArrayList of integers as the possible initial values
        //Used this resource to figure out the types of binary trees https://www.geeksforgeeks.org/binary-tree-set-3-types-of-binary-tree/
        ArrayList<String> treeTypes = new ArrayList<>();
        treeTypes.add("Full");
        treeTypes.add("Complete");
        treeTypes.add("Perfect");

        //Get random initial index
        final Random r = new Random();
        index = r.nextInt(treeTypes.size());

        //set spinner selection (length of array) randomly with initial length
        //used https://stackoverflow.com/questions/4486034/get-root-view-from-current-activity to figure out the view
        GraphHelperMethods.populateSpinner(treeTypes, getWindow().getDecorView().getRootView(), spinner, index);

        generateButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        startButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        stopButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        rewindButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        proofButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Open a webpage! (resource used to figure out code at https://stackoverflow.com/questions/2201917/how-can-i-open-a-url-in-androids-web-browser-from-my-application)
                Uri uri = Uri.parse("https://www.cs.mcgill.ca/~pnguyen/251F09/BFScorrect.pdf");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

    }

}
