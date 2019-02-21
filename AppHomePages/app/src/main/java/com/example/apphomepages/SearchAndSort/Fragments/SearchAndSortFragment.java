package com.example.apphomepages.SearchAndSort.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.Activities.SearchingActivity;
import com.example.apphomepages.SearchAndSort.Activities.SortingActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchAndSortFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchAndSortFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchAndSortFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //LOG messages
    private static String TAG = "SearchAndSortFragment";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchAndSortFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchAndSortFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchAndSortFragment newInstance(String param1, String param2)
    {
        SearchAndSortFragment fragment = new SearchAndSortFragment();
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
        View view = inflater.inflate(R.layout.fragment_search_and_sort, container, false);

        //Set up the buttons on the fragment
        Button searchButton = view.findViewById(R.id.searchingButton);
        Button sortButton = view.findViewById(R.id.sortingButton);

        searchButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e(TAG, "Search Button Clicked Main Screen Fragment");

                //used https://stackoverflow.com/questions/28961842/how-to-move-from-a-one-fragment-to-an-activity to figure out the transition between a fragment and an activity
                Intent i = new Intent(getActivity(), SearchingActivity.class);
                startActivity(i);
            }
        });

        sortButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e(TAG, "Sort Button Clicked Main Screen Fragment");

                //used https://stackoverflow.com/questions/28961842/how-to-move-from-a-one-fragment-to-an-activity to figure out the transition between a fragment and an activity
                Intent i = new Intent(getActivity(), SortingActivity.class);
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
