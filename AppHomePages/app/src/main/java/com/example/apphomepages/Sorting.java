package com.example.apphomepages;

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

import com.example.apphomepages.FragmentAdapters.FragmentAdapterSortingPage;
import com.example.apphomepages.Fragments.BubbleSortFragment;
import com.example.apphomepages.Fragments.InsertionSortFragment;
import com.example.apphomepages.Fragments.MergeSortFragment;
import com.example.apphomepages.Fragments.QuicksortFragment;
import com.example.apphomepages.Fragments.SelectionSortFragment;

public class Sorting extends AppCompatActivity implements BubbleSortFragment.OnFragmentInteractionListener, InsertionSortFragment.OnFragmentInteractionListener, SelectionSortFragment.OnFragmentInteractionListener, MergeSortFragment.OnFragmentInteractionListener, QuicksortFragment.OnFragmentInteractionListener
{
    //LOG messages
    private static String TAG = "Sorting";

    //These are the various portions of the main page for the application
    private ViewPager viewPager;
    private FragmentAdapterSortingPage fragmentAdapterSortingPage;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        initViewPagerSorting();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initViewPagerSorting()
    {
        viewPager = findViewById(R.id.viewPager);
        fragmentAdapterSortingPage = new FragmentAdapterSortingPage(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapterSortingPage); //linking the view manager and fragment adapter together

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

