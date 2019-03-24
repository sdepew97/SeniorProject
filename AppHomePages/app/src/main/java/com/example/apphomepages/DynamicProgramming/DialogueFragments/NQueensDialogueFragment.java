package com.example.apphomepages.DynamicProgramming.DialogueFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.apphomepages.R;

import androidx.fragment.app.DialogFragment;

public class NQueensDialogueFragment extends DialogFragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_nqueens_instr_dialog, container, false);
        getDialog().setTitle("Instructions");

        Button dismiss = rootView.findViewById(R.id.close_instructions_nqueens);
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
