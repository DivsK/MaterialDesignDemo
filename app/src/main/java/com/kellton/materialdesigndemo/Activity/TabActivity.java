package com.kellton.materialdesigndemo.Activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.kellton.materialdesigndemo.Adapters.ViewPagerAdapter;
import com.kellton.materialdesigndemo.Fragment.FirstFragment;
import com.kellton.materialdesigndemo.Fragment.SecondFragment;
import com.kellton.materialdesigndemo.Fragment.ThirdFragment;
import com.kellton.materialdesigndemo.R;
/**
 * @author Divya Khanduri
 */


public class TabActivity extends AppCompatActivity {
    private ViewPagerAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        CollapsingToolbarLayout mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        mCollapsingToolbarLayout.setTitle("Title");
        mCollapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent,getTheme()));

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        FragmentManager fragmentManager=getSupportFragmentManager();
        viewPagerAdapter=new ViewPagerAdapter(fragmentManager,this);
        viewPagerAdapter.addFragments(FirstFragment.newInstance(1),"Tab 1");
        viewPagerAdapter.addFragments(SecondFragment.newInstance(2),"Tab 2");
        viewPagerAdapter.addFragments(ThirdFragment.newInstance(3),"Tab 3");
        viewPager.setAdapter(viewPagerAdapter);

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

    }
}
