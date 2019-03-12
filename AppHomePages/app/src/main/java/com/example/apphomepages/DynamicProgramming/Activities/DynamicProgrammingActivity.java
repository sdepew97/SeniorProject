package com.example.apphomepages.DynamicProgramming.Activities;

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

import com.example.apphomepages.DynamicProgramming.FragmentAdapters.FragmentAdapterDPPage;
import com.example.apphomepages.DynamicProgramming.Fragments.MinEditFragment;
import com.example.apphomepages.DynamicProgramming.Fragments.NQueensFragment;
import com.example.apphomepages.R;

public class DynamicProgrammingActivity extends AppCompatActivity implements NQueensFragment.OnFragmentInteractionListener, MinEditFragment.OnFragmentInteractionListener
{
    //LOG messages
    private static String TAG = "DynamicProgrammingActivity";

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

    @Override
    public void onFragmentInteraction(Uri uri)
    {
        Log.e(TAG, "Fragment interaction listener");
    }

}
