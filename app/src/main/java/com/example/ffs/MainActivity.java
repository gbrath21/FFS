package com.example.ffs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Placeholder;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ffs.fragments.ReportIssueFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;
    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference myRef = database.getReference("issue/1/location");
    EditText issueName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tablayout er den øverste del af skærmen hvor man kan trykke på de forskellige tabs
        tabLayout = findViewById(R.id.tab_layout);
        //viewpager er det som er under tabsene som viser de forskellige fragments
        viewPager2 = findViewById(R.id.view_pager);
        //her bliver der lavet en ny instance af viewpager adapteren som er den java class der
        //gør at de rigtige fragments bliver vist på de rigtige tabs
        myViewPagerAdapter = new MyViewPagerAdapter(this);
        //Her vælges adapter classen til at være den adapter som viewpageren skal bruge
        viewPager2.setAdapter(myViewPagerAdapter);
        //den her funktion 'lytter' efter hvilken tab man står på så den kan vise det rigtige fragment
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //den her funktion er lidtt ligesom den over men den opfanger hvis man swiper, således
        //at tabhighlighteren følger med når der swipes mellem siderne/fragmentsene
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });

    }




}
