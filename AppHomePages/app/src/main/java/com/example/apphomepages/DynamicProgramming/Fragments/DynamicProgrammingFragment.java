package com.example.apphomepages.DynamicProgramming.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apphomepages.DynamicProgramming.Activities.DynamicProgrammingActivity;
import com.example.apphomepages.R;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DynamicProgrammingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DynamicProgrammingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DynamicProgrammingFragment extends Fragment
{
    private OnFragmentInteractionListener mListener;

    public DynamicProgrammingFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DynamicProgrammingFragment.
     */
    public static DynamicProgrammingFragment newInstance()
    {
        DynamicProgrammingFragment fragment = new DynamicProgrammingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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
        View view = inflater.inflate(R.layout.fragment_dynamic_programming, container, false);

        //Set up the buttons on the fragment
        Button graphButton = view.findViewById(R.id.dynamicProgrammingAlgorithms);

        graphButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //used https://stackoverflow.com/questions/28961842/how-to-move-from-a-one-fragment-to-an-activity to figure out the transition between a fragment and an activity
                Intent i = new Intent(getActivity(), DynamicProgrammingActivity.class);
                startActivity(i);
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
