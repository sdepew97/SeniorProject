package com.example.apphomepages.DynamicProgramming.Activities;

import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import com.example.apphomepages.DynamicProgramming.FragmentAdapters.FragmentAdapterDPPage;
import com.example.apphomepages.DynamicProgramming.Fragments.LevenshteinFragment;
import com.example.apphomepages.R;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;

public class DynamicProgrammingActivity extends AppCompatActivity implements LevenshteinFragment.OnFragmentInteractionListener
{
    //These are the various portions of the main page for the application
    private ViewPager viewPager;
    private FragmentAdapterDPPage fragmentAdapterDPPage;
    private TabLayout tabLayout;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_programming);

        initViewPagerSearching();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void initViewPagerSearching()
    {
        viewPager = findViewById(R.id.viewPager);
        fragmentAdapterDPPage = new FragmentAdapterDPPage(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapterDPPage); //linking the view manager and fragment adapter together

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

        int tabSelectedColor = ResourcesCompat.getColor(getResources(), R.color.colorPrimaryDark, null);
        int tabNotSelectedColor = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        tabLayout.setTabTextColors(tabSelectedColor, tabNotSelectedColor);

        int tabColors = ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null);
        tabLayout.setBackground(new ColorDrawable(tabColors));
    }
}
