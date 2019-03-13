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
import com.example.apphomepages.SearchAndSort.FragmentAdapters.FragmentAdapterSearchingPage;
import com.example.apphomepages.SearchAndSort.Fragments.BinarySearchFragment;
import com.example.apphomepages.SearchAndSort.Fragments.LinearSearchFragment;

public class SearchingActivity extends AppCompatActivity implements LinearSearchFragment.OnFragmentInteractionListener, BinarySearchFragment.OnFragmentInteractionListener
{
    //LOG messages
    private static String TAG = "SearchingActivity";

    //These are the various portions of the main page for the application
    private ViewPager viewPager;
    private FragmentAdapterSearchingPage fragmentAdapterSearchingPage;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        initViewPagerSearching();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initViewPagerSearching()
    {
        viewPager = findViewById(R.id.viewPager);
        fragmentAdapterSearchingPage = new FragmentAdapterSearchingPage(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapterSearchingPage); //linking the view manager and fragment adapter together

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
