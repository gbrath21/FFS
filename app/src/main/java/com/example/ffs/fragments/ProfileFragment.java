package com.example.ffs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.ffs.R;


public class ProfileFragment extends Fragment {
    ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        imageView = (ImageView) rootView.findViewById(R.id.iv_sp);
        imageView.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.baseline_person_outline_24));

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}