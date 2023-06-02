package com.example.ffs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ffs.Issue;
import com.example.ffs.R;
import com.example.ffs.RecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ViewIssuesFragment extends Fragment {
    private RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter; // Create Object of the Adapter class
    DatabaseReference myRef; // Create object of the
    // Firebase Realtime Database
    private ArrayList<Issue> issueArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_view_issues, container, false);
        // Create a instance of the database and get
        // its reference

        recyclerView = rootView.findViewById(R.id.recycler1);
        // To display the Recycler view linearly
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        myRef = FirebaseDatabase.getInstance().getReference();

        issueArrayList = new ArrayList<>();

        ClearAll();

        GetDataFromFirebase();

        return rootView;
    }


    private void GetDataFromFirebase() {
        Query query = myRef.child("issues");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Issue issues = new Issue();
                    issues.setIssuename(snapshot.child("issuename").getValue().toString());
                    issues.setLocation(snapshot.child("location").getValue().toString());
                    issues.setSoldierid(snapshot.child("soldierid").getValue().toString());
                    issues.setImageurl(snapshot.child("imageurl").getValue().toString());
                    issueArrayList.add(issues);
                }
                recyclerAdapter = new RecyclerAdapter(getContext(), issueArrayList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void ClearAll(){
        if (issueArrayList != null){
            issueArrayList.clear();
            if(recyclerAdapter != null){
                recyclerAdapter.notifyDataSetChanged();
            }
        }
        issueArrayList = new ArrayList<>();
    }
}

