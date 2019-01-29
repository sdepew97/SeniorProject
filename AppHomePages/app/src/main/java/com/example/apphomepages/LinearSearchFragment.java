package com.example.apphomepages;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LinearSearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LinearSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LinearSearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int currentCount = 0;
    private LinearSearchDrawable[] stopMotionAnimation = null;
    private OnFragmentInteractionListener mListener = null;

    public LinearSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LinearSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LinearSearchFragment newInstance(String param1, String param2) {
        LinearSearchFragment fragment = new LinearSearchFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_linear_search, container, false);

        //Set up the buttons on the fragment
        Button nextFrameButton = view.findViewById(R.id.frameButton);
        Button generateButton = view.findViewById(R.id.generateButton);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the numbers input by the user
                EditText numbersEditText = view.findViewById(R.id.editTextArray);
                EditText targetEditText = view.findViewById(R.id.editTextNumber);

                if (numbersEditText != null && numbersEditText.getText().toString() != "") {
                    currentCount = 0;

                    String[] numbers = numbersEditText.getText().toString().split(",");
                    int numbersLength = numbers.length;

                    if (numbersLength > 0 && numbers[0] != "") {

                        for (int i = 0; i < numbersLength; i++) {
                            numbers[i] = numbers[i].trim();
                        }

                        //TODO: update with user's sought after value!
                        int locationInArray = SearchingAlgorithms.linearSearch(numbers, targetEditText.getText().toString().trim());
                        int square = -1;

                        numbersEditText.setText(""); //reset the text box with an empty string
                        targetEditText.setText(""); //reset the text box with an empty string

                        stopMotionAnimation = new LinearSearchDrawable[numbersLength + 1];

                        //TODO: get this info from the user and parse into array of "animation frames"
                        Color main = new Color(255, 0, 255);
                        Color secondary = new Color(255, 255, 0);
                        Color found = new Color(255, 0, 0);


                        //setup main frame git
                        stopMotionAnimation[0] = new LinearSearchDrawable(main, secondary, found, square, false, numbers);
                        square++;

                        for (int i = 1; i < stopMotionAnimation.length; i++) {
                            stopMotionAnimation[i] = locationInArray == (i - 1) ? new LinearSearchDrawable(main, secondary, found, square, true, numbers) : new LinearSearchDrawable(main, secondary, found, square, false, numbers);
                            square++;
                        }

                        ImageView image = view.findViewById(R.id.imageView);
                        image.setImageDrawable(stopMotionAnimation[currentCount]);
                        currentCount = 1;

                    }
                }
            }
        });

        nextFrameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stopMotionAnimation != null) {
                    if (currentCount >= stopMotionAnimation.length) {
                        currentCount = 0;
                    }

                    ImageView image = view.findViewById(R.id.imageView);
                    image.setImageDrawable(stopMotionAnimation[currentCount]);
                    currentCount++;
                }
            }
        });

        return view;
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
