package com.example.apphomepages.Activities;

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

import com.example.apphomepages.FragmentAdapters.FragmentAdapterSearchingPage;
import com.example.apphomepages.Fragments.BinarySearchFragment;
import com.example.apphomepages.Fragments.LinearSearchFragment;
import com.example.apphomepages.R;

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
