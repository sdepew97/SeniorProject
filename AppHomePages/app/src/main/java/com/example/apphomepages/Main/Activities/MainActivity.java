package com.example.apphomepages.Main.Activities;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.apphomepages.DynamicProgramming.Fragments.DynamicProgrammingFragment;
import com.example.apphomepages.Graph.Fragments.GraphFragment;
import com.example.apphomepages.Greedy.Fragments.GreedyFragment;
import com.example.apphomepages.Main.FragmentAdapters.FragmentAdapterMainPage;
import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.Fragments.SearchAndSortFragment;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements SearchAndSortFragment.OnFragmentInteractionListener, GraphFragment.OnFragmentInteractionListener, GreedyFragment.OnFragmentInteractionListener, DynamicProgrammingFragment.OnFragmentInteractionListener
{

    //LOG messages
    private static String TAG = "MainActivity";

    //These are the various portions of the main page for the application
    private ViewPager viewPager;
    private FragmentAdapterMainPage fragmentAdapterMainPage;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewPagerMainPage(); //This is the main swiping page from the UI/UX diagram
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initViewPagerMainPage()
    {
        viewPager = findViewById(R.id.viewPager);
        fragmentAdapterMainPage = new FragmentAdapterMainPage(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapterMainPage); //linking the view manager and fragment adapter together

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
