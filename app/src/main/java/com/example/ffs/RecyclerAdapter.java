package com.example.ffs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private Context mContext;
    //liste som gemmer værdierne fra classen Issue, som bruges til at indsætte værdierne på felterne
    //i issue_item.xml filen, som er det layout der bliver sat ind for hver instance der er i firebase
    private ArrayList<Issue> issueArrayList;

    //Constructoren som bruges når værdierne skal vises i ViewIssuesFragment
    public RecyclerAdapter(Context mContext, ArrayList<Issue> issueArrayList) {
        this.mContext = mContext;
        this.issueArrayList = issueArrayList;
    }

    //Den function som binder ViewHolderen med issue_item layoutet
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_item, parent,false);

        return new ViewHolder(view);
    }

    //functionen som binder de specifikke værdier i array listen med tingene i issue_item layoutet
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TextViews
        holder.issuename.setText(issueArrayList.get(position).getIssuename());
        holder.location.setText(issueArrayList.get(position).getLocation());
        holder.soldierid.setText(issueArrayList.get(position).getSoldierid());
        //ImageView
        Glide.with(mContext).load(issueArrayList.get(position).getImageurl()).into(holder.imageView);
    }

    //Sørge for at alle værdier i listen bliver vist... Hvis man sætter den til return 3 vil den kun
    //vise 3 bokse i View Issues
    @Override
    public int getItemCount() {
        return issueArrayList.size();
    }

    //her bindes alle variablerne til issue_item
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView issuename, location, soldierid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.imageViewRV);
            issuename = itemView.findViewById(R.id.issuename);
            location = itemView.findViewById(R.id.location);
            soldierid = itemView.findViewById(R.id.soldierid);
        }
    }

}
