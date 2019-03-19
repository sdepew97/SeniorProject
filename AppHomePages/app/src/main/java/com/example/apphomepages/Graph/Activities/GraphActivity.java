package com.example.apphomepages.Graph.Activities;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.apphomepages.Graph.FragmentAdapters.FragmentAdapterGraphPage;
import com.example.apphomepages.Graph.Fragments.BFSFragment;
import com.example.apphomepages.Graph.Fragments.DFSFragment;
import com.example.apphomepages.Graph.Fragments.TopologicalFragment;
import com.example.apphomepages.R;

public class GraphActivity extends AppCompatActivity implements DFSFragment.OnFragmentInteractionListener, BFSFragment.OnFragmentInteractionListener, TopologicalFragment.OnFragmentInteractionListener
{

    //LOG messages
    private static String TAG = "GraphActivity";

    //These are the various portions of the main page for the application
    private ViewPager viewPager;
    private FragmentAdapterGraphPage fragmentAdapterGraphPage;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        initViewPagerSearching();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initViewPagerSearching()
    {
        viewPager = findViewById(R.id.viewPager);
        fragmentAdapterGraphPage = new FragmentAdapterGraphPage(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapterGraphPage); //linking the view manager and fragment adapter together

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        int tabSelectedColor = ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null);
        int tabNotSelectedColor = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        tabLayout.setTabTextColors(tabSelectedColor, tabNotSelectedColor);

        int tabColors = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        tabLayout.setBackground(new ColorDrawable(tabColors));
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {
        Log.e(TAG, "Fragment interaction listener");
    }
}
