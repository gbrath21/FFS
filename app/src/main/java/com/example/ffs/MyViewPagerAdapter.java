package com.example.ffs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ffs.fragments.ReportIssueFragment;
import com.example.ffs.fragments.ViewIssuesFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    //den her funktion er den som bruges i mainactivity og er den der retunerer hvilken tab
    //man står på og som man kan se så er defaulten ved opstart sat til at være report issue
    //som er den første side hvis man så trykker eller swiper til en af de andre tabs er det så
    //en anden int der bliver retuneret og dermed vises et af de andre fragments.
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            default:
                return new ReportIssueFragment();
            case 1:
                return new ViewIssuesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
