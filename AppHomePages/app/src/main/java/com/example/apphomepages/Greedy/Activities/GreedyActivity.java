package com.example.apphomepages.Greedy.Activities;

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

import com.example.apphomepages.Greedy.FragmentAdapters.FragmentAdapterGreedyPage;
import com.example.apphomepages.Greedy.Fragments.DijkstrasFragment;
import com.example.apphomepages.Greedy.Fragments.IntervalSchedulingFragment;
import com.example.apphomepages.R;

public class GreedyActivity extends AppCompatActivity implements IntervalSchedulingFragment.OnFragmentInteractionListener, DijkstrasFragment.OnFragmentInteractionListener
{
    //LOG messages
    private static String TAG = "GreedyActivity";

    //These are the various portions of the main page for the application
    private ViewPager viewPager;
    private FragmentAdapterGreedyPage fragmentAdapterGreedyPage;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greedy);

        initViewPagerSearching();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initViewPagerSearching()
    {
        viewPager = findViewById(R.id.viewPager);
        fragmentAdapterGreedyPage = new FragmentAdapterGreedyPage(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapterGreedyPage); //linking the view manager and fragment adapter together

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
