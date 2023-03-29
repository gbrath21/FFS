package com.example.ffs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ffs.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;


public class ReportIssueFragment extends Fragment {
    public EditText issueName;
    EditText Location;
    EditText SoldierId;
    Button reportIssue;
    public TextView reportTxt;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_issue, container, false);
        issueName = (EditText) rootView.findViewById(R.id.NameOfIssueET);
        Location = (EditText) rootView.findViewById(R.id.LocationET);
        reportIssue = (Button) rootView.findViewById(R.id.ReportIssueBtn);
        SoldierId = (EditText) rootView.findViewById(R.id.SoldierIDET);
        reportTxt= (TextView) rootView.findViewById(R.id.ReportIssueTxt);
        reportIssue.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v)
                    {

                        //get all the values
                        String isName = issueName.getText().toString();
                        String location = Location.getText().toString();
                        String soldierId = SoldierId.getText().toString();

                        Issues issuesClass = new Issues(isName,location,soldierId);

                        myRef.child(Calendar.getInstance().getTime().toString()+" - Soldier Id:"+soldierId).setValue(issuesClass);
                        issueName.setText("");
                        Location.setText("");
                        SoldierId.setText("");

                    }
                });
        // Inflate the layout for this fragment
        return rootView;
    }


}