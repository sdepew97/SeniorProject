package com.example.apphomepages.SearchAndSort.DialogFragments;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apphomepages.R;

//Used https://stacktips.com/tutorials/android/android-dialog-fragment-example as a resource to create this code
public class MergeSortDialogFragment extends DialogFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_merge_sort_instr_dialog, container, false);
        getDialog().setTitle("Instructions");

        Button dismiss = rootView.findViewById(R.id.close_instructions_merge);
        dismiss.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });

        return rootView;
    }
}