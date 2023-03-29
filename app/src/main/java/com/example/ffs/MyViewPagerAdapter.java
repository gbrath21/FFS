package com.example.ffs;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ffs.fragments.ProfileFragment;
import com.example.ffs.fragments.ReportIssueFragment;
import com.example.ffs.fragments.ViewIssuesFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new ViewIssuesFragment();
            case 2:
                return new ProfileFragment();
            default:
                return new ReportIssueFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
