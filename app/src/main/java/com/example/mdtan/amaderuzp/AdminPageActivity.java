package com.example.mdtan.amaderuzp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AdminPageActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page_layout);

        tabLayout=(TabLayout)findViewById(R.id.tab_xml);
        viewPager=(ViewPager)findViewById(R.id.viewpager_xml);
        viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new Complain_view_fragment(),"Complains");
        viewPagerAdapter.addFragment(new Application_view_fragment(),"Applications");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        ActionBar actionBar=getSupportActionBar();
        /*actionBar.setElevation(0);*/

    }
}
