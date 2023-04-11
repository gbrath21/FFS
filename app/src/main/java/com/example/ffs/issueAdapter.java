package com.example.ffs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View(a form of a listview)
public class issueAdapter extends FirebaseRecyclerAdapter<
        issue, issueAdapter.issuesViewholder> {

    public issueAdapter(
            @NonNull FirebaseRecyclerOptions<issue> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "issue.xml") with data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull issuesViewholder holder,
                     int position, @NonNull issue model)
    {

        // Add issuename from model class (here
        // "issue.class")to appropriate view in Card
        // view (here "issue.xml")
        holder.issuename.setText(model.getIssuename());

        // Add lastname from model class (here
        // "issue.class")to appropriate view in Card
        // view (here "issue.xml")
        holder.location.setText(model.getLocation());

        // Add age from model class (here
        // "issue.class")to appropriate view in Card
        // view (here "issue.xml")
        holder.soldierid.setText(model.getSoldierid());
    }

    // Function to tell the class about the Card view (here
    // "issue.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public issuesViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.issue, parent, false);
        return new issuesViewholder(view);
    }

    // Sub Class to create references of the views in Card
    // view (here "issue.xml")
    class issuesViewholder
            extends RecyclerView.ViewHolder {
        TextView issuename, location, soldierid;
        public issuesViewholder(@NonNull View itemView)
        {
            super(itemView);

            issuename
                    = itemView.findViewById(R.id.issuename);
            location = itemView.findViewById(R.id.location);
            soldierid = itemView.findViewById(R.id.soldierid);
        }
    }
}
