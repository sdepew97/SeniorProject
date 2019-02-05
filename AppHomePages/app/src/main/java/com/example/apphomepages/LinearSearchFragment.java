package com.example.apphomepages;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LinearSearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LinearSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LinearSearchFragment extends Fragment implements SpinnerAdapter {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int currentCount = 0;
    private int bound = 10; //the max number of elements in the array
    private int[] numbers = null;
    private int soughtAfter = -1;
    private Random r = new Random();
    private int durration = 800;
    private ImageView image;
    private ArraySearchDrawable[] stopMotionAnimation = null;
    //The animation that will be played when the "next frame" button is clicked
    private AnimationDrawable animationDrawable = new AnimationDrawable();
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
        final View viewGlobal = inflater.inflate(R.layout.fragment_linear_search, container, false);

        //Set up the buttons and clickable elements on the fragment
        Button generateButton = viewGlobal.findViewById(R.id.generateButton);
        Button startButton = viewGlobal.findViewById(R.id.startButton);
        Button stopButton = viewGlobal.findViewById(R.id.stopButton);
        Button rewindButton = viewGlobal.findViewById(R.id.rewindButton);
        final Spinner spinner = viewGlobal.findViewById(R.id.spinner);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable = new AnimationDrawable();
                currentCount = 0; //make sure we are starting at 0 in the array

                int numElements = r.nextInt(bound) + 1; //want a value between 1 and 10, so 1-10 elements in the array
                numbers = new int[numElements]; //random numbers

                for (int j = 0; j < numElements; j++) {
                    int randomInt = r.nextInt(bound * 10);
                    if (r.nextBoolean()) {
                        numbers[j] = (-randomInt);
                    } else {
                        numbers[j] = randomInt;
                    }
                }

                //populate the spinner used https://stackoverflow.com/questions/20244419/android-spinner-populating-using-arrayliststring as a resource as well as https://stackoverflow.com/questions/10582283/how-to-populate-spinner-from-a-array-string
                ArrayList<String> numbersStrings = new ArrayList<>();

                for (int k = 0; k < numElements; k++) {
                    numbersStrings.add(Integer.toString(numbers[k]));
                }

                ArrayAdapter adapter = new ArrayAdapter(viewGlobal.getContext(), android.R.layout.simple_spinner_item, numbersStrings);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                boolean targetFound = r.nextBoolean();
                soughtAfter = targetFound ? numbers[r.nextInt(numbers.length)] : -1;
                int locationInArray = SearchingAlgorithms.linearSearch(numbers, soughtAfter);

                if (locationInArray != -1) {
                    spinner.setSelection(locationInArray); //choose the element in the array to select automatically from the dropdown menu (a.k.a. spinner menu)
                } else {
                    //TODO: setting when not found!
                }

                int square = -1;

                //depending if the element is found or not...
                if (locationInArray == -1) {
                    stopMotionAnimation = new ArraySearchDrawable[numbers.length + 1];
                } else {
                    stopMotionAnimation = new ArraySearchDrawable[locationInArray + 1 + 1];
                }

                //https://www.rapidtables.com/web/color/RGB_Color.html used to get the colors
                Color main = new Color(102, 102, 255);
                Color secondary = new Color(153, 255, 255);
                Color found = new Color(255, 102, 178);

                //setup main frame
                stopMotionAnimation[0] = new ArraySearchDrawable(main, secondary, found, square, false, numbers);
                square++;

                for (int i = 1; i < stopMotionAnimation.length; i++) {
                    stopMotionAnimation[i] = locationInArray == (i - 1) ? new ArraySearchDrawable(main, secondary, found, square, true, numbers) : new ArraySearchDrawable(main, secondary, found, square, false, numbers);
                    square++;
                }

                image = viewGlobal.findViewById(R.id.imageView);

                for (Drawable d : stopMotionAnimation) {
                    animationDrawable.addFrame(d, durration);
                }

                animationDrawable.setOneShot(false);
                image.setBackgroundDrawable(animationDrawable);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                soughtAfter = Integer.valueOf((String) spinner.getSelectedItem());
                currentCount = 0;
                int locationInArray = SearchingAlgorithms.linearSearch(numbers, soughtAfter);
                int square = -1;

                //depending if the element is found or not...
                if (locationInArray == -1) {
                    stopMotionAnimation = new ArraySearchDrawable[numbers.length + 1];
                } else {
                    stopMotionAnimation = new ArraySearchDrawable[locationInArray + 1 + 1];
                }

                //https://www.rapidtables.com/web/color/RGB_Color.html used to get the colors
                Color main = new Color(102, 102, 255);
                Color secondary = new Color(153, 255, 255);
                Color found = new Color(255, 102, 178);

                //setup main frame
                stopMotionAnimation[0] = new ArraySearchDrawable(main, secondary, found, square, false, numbers);
                square++;

                for (int i = 1; i < stopMotionAnimation.length; i++) {
                    stopMotionAnimation[i] = locationInArray == (i - 1) ? new ArraySearchDrawable(main, secondary, found, square, true, numbers) : new ArraySearchDrawable(main, secondary, found, square, false, numbers);
                    square++;
                }

                animationDrawable = new AnimationDrawable(); //wipe the drawable and put in new frames
                for (Drawable d : stopMotionAnimation) {
                    animationDrawable.addFrame(d, durration);
                }

                animationDrawable.setOneShot(false);
                image.setBackgroundDrawable(animationDrawable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //do nothing
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.start();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.stop();
            }
        });

        rewindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationDrawable.setVisible(true, true);
                animationDrawable.stop();
            }
        });

        return viewGlobal;
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

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
