package com.example.apphomepages.SearchAndSort.Activities;

import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import com.google.android.material.tabs.TabLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.example.apphomepages.R;
import com.example.apphomepages.SearchAndSort.FragmentAdapters.FragmentAdapterSortingPage;
import com.example.apphomepages.SearchAndSort.Fragments.BubbleSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.InsertionSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.MergeSortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.QuicksortFragment;
import com.example.apphomepages.SearchAndSort.Fragments.SelectionSortFragment;

public class SortingActivity extends AppCompatActivity implements BubbleSortFragment.OnFragmentInteractionListener, InsertionSortFragment.OnFragmentInteractionListener, SelectionSortFragment.OnFragmentInteractionListener, MergeSortFragment.OnFragmentInteractionListener, QuicksortFragment.OnFragmentInteractionListener
{
    //LOG messages
    private static String TAG = "SortingActivity";

    //These are the various portions of the main page for the application
    private ViewPager viewPager;
    private FragmentAdapterSortingPage fragmentAdapterSortingPage;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);

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

