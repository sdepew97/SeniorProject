package com.example.apphomepages.Graph.DialogFragments;

import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apphomepages.R;

//Used https://stacktips.com/tutorials/android/android-dialog-fragment-example as a resource to create this code
public class DFSDialogFragment extends DialogFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_dfs_instr_dialog, container, false);
        getDialog().setTitle("Instructions");

        Button dismiss = rootView.findViewById(R.id.close_instructions_dfs);
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
