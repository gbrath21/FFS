package com.example.ffs.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
                        //hvis ikke at alle felter er udfyldt får man en error message
                        //hvis alle felter er udfyldte sendes det til firebase
                        if(!issueName.getText().toString().equals("") && !Location.getText().toString().equals("") && !SoldierId.getText().toString().equals("")) {
                            //henter værdiene fra alle felter
                            String isName = issueName.getText().toString();
                            String location = Location.getText().toString();
                            String soldierId = SoldierId.getText().toString();
                            //kalder en ny instance af issues classen hvor værdier fra felterne bliver sat ind 
                            Issues issuesClass = new Issues(isName, location, soldierId);
                            //sætter det ind i firebase og for at gøre at navnene på alle issuses er forskellige 
                            //giver vi den navn udfra det tidspunkt der bliver tryket på knappen + soldier id'et
                            //til sidst sættes værdierne af issuet til at være den issuesclass der blev lavet oven over.
                            myRef.child(Calendar.getInstance().getTime().toString() + " - Soldier Id:" + soldierId).setValue(issuesClass);
                            
                            //cleare tekstfelterne når det er sat ind i firebase
                            issueName.setText("");
                            Location.setText("");
                            SoldierId.setText("");
                        }
                        else {
                            Toast error = Toast.makeText(getContext(), "Please insert values", Toast.LENGTH_SHORT);
                            error.show();
                        }
                    }
                });
        // Inflate the layout for this fragment
        return rootView;
    }


}
