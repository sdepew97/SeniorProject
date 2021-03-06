package com.example.apphomepages;

import com.example.apphomepages.DynamicProgramming.FragmentAdapters.FragmentAdapterDPPage;
import com.example.apphomepages.DynamicProgramming.Fragments.LevenshteinFragment;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import androidx.fragment.app.FragmentManager;

import static org.hamcrest.CoreMatchers.instanceOf;

public class FragmentAdapterDPPageTest
{

    @Test
    public void getCount()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterDPPage fragmentAdapterDPPage = new FragmentAdapterDPPage(fm);

        Assert.assertEquals(1, fragmentAdapterDPPage.getCount());
    }

    @Test
    public void getPageTitle()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterDPPage fragmentAdapterDPPage = new FragmentAdapterDPPage(fm);

        Assert.assertEquals("Levenshtein Distance Algorithm", fragmentAdapterDPPage.getPageTitle(0));
        Assert.assertEquals("Levenshtein Distance Algorithm", fragmentAdapterDPPage.getPageTitle(22));
    }

    @Test
    public void getItem()
    {
        FragmentManager fm = Mockito.mock(FragmentManager.class);
        FragmentAdapterDPPage fragmentAdapterDPPage = new FragmentAdapterDPPage(fm);

        Assert.assertThat(fragmentAdapterDPPage.getItem(0), instanceOf(LevenshteinFragment.class));
        Assert.assertThat(fragmentAdapterDPPage.getItem(22), instanceOf(LevenshteinFragment.class));
    }
}