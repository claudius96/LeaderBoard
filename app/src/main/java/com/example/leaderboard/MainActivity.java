package com.example.leaderboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TabItem mTabLeaderboard, mTabSkill;
    private PagerAdapter mPagerAdapter;
    private Button mSubmitButton;
    private Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("LEADERBOARD");
        setSupportActionBar(toolbar);


        mIntent = new Intent(MainActivity.this, Submission.class);
        mSubmitButton = (Button)findViewById(R.id.ConstraintLayout);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(mIntent);
            }
        });
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mTabLeaderboard = (TabItem) findViewById(R.id.leaderboard);
        mTabSkill = (TabItem) findViewById(R.id.skill);
        mViewPager = findViewById(R.id.viewpager);


        mPagerAdapter = new PagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());


                if(tab.getPosition() == 0){
                    mPagerAdapter.notifyDataSetChanged();
                }else if(tab.getPosition() == 1){
                    mPagerAdapter.notifyDataSetChanged();
                }





            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
    }
}