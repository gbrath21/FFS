package com.example.ffs.fragments;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ffs.Issue;
import com.example.ffs.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ReportIssueFragment extends Fragment {
    //variables
    EditText issueName, Location, SoldierId;
    TextView reportTxt;
    Button reportBtn, selectBtn, uploadBtn, cancelBtn;
    ImageView imageView;
    String uriLink;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 2;
    //firebase
    private StorageReference reference = FirebaseStorage.getInstance().getReference();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_report_issue, container, false);
        issueName = rootView.findViewById(R.id.NameOfIssueET);
        Location = rootView.findViewById(R.id.LocationET);
        reportBtn = rootView.findViewById(R.id.ReportIssueBtn);
        SoldierId = rootView.findViewById(R.id.SoldierIDET);
        reportTxt = rootView.findViewById(R.id.ReportIssueTxt);
        selectBtn = rootView.findViewById(R.id.Select_button);
        uploadBtn = rootView.findViewById(R.id.upload_button);
        imageView = rootView.findViewById(R.id.imageView);
        cancelBtn = rootView.findViewById(R.id.CancelBtn);
        //report issue knappen sættes til at man ikke kan trykke på den og farven til grå
        reportBtn.setEnabled(false);
        reportBtn.setBackgroundColor(Color.parseColor("#D3D3D3"));

        //reseter siden
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                issueName.setText("");
                Location.setText("");
                SoldierId.setText("");
                imageView.setImageResource(R.drawable.baseline_image_24);
                reportBtn.setEnabled(false);
                reportBtn.setBackgroundColor(Color.parseColor("#D3D3D3"));
            }
        });
        reportBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override public void onClick(View v)
                    {
                        //henter værdiene fra alle felter
                        String isName = issueName.getText().toString();
                        String location = Location.getText().toString();
                        String soldierId = SoldierId.getText().toString();
                        //hvis ikke at alle felter er udfyldt får man en error message
                        //hvis alle felter er udfyldte sendes det til firebase
                        if(!isName.equals("") && !location.equals("") && !soldierId.equals("")) {
                            // uriLink får sin værdi når uploadImage functionen er successful
                            String url = uriLink;

                            //kalder en ny instance af issues classen hvor værdier fra felterne bliver sat ind
                            Issue issueClass = new Issue(isName, location, soldierId,url);
                            //sætter det ind i firebase og for at gøre at navnene på alle issuses er forskellige
                            //giver vi den navn udfra det tidspunkt der bliver tryket på knappen + soldier id'et
                            //til sidst sættes værdierne af issuet til at være den issuesclass der blev lavet oven over.
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
                            myRef.child("issues").child(sdf.format(Calendar.getInstance().getTime())+" "+sdf1.format(Calendar.getInstance().getTime())+" Soldier ID:"+ soldierId).setValue(issueClass);

                            //Reseter Report issue siden når et issue er reported
                            issueName.setText("");
                            Location.setText("");
                            SoldierId.setText("");
                            imageView.setImageResource(R.drawable.baseline_image_24);
                            reportBtn.setEnabled(false);
                            reportBtn.setBackgroundColor(Color.parseColor("#D3D3D3"));
                            //lille besked i bunden der fortæller når issue er reported
                            Toast.makeText(getContext(), "Issue reported", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            //lille besked i bunden der beder dig om at udfylde alle felter hvis det ikke er gjort
                            Toast.makeText(getContext(), "Please insert values in all fields", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        // hvis der trykkes på imageView kører functionen SelectImage
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();
            }
        });
        // hvis der trykkes på Select knappen kører functionen SelectImage
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SelectImage();
            }
        });
        // hvis der trykkes på Upload knappen, og filePath ikke er nul kører functionen uploadImage
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filePath != null){
                    uploadImage(filePath);
                }
                else{
                    Toast.makeText(getContext(), "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
    private void SelectImage()
    {
        // Bruger intet fuctionen til at bruge android telefonens apps
        Intent intent = new Intent();
        //specificerer at datatypen vi vil have retuneret er et billede
        intent.setType("image/*");
        //specificerer at vi vil hente data til appen
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starter activiteten (fotoappen), og vi kan så vælge et billede
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {
            // Get the Uri of data
            filePath = data.getData();
            try {
                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    // UploadImage method
    private void uploadImage(Uri uri)
    {
        // Code for showing progressDialog while uploading
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Uploading picture...");
        progressDialog.show();

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." );
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //
                        reportBtn.setBackgroundColor(Color.parseColor("#3f5b58"));
                        reportBtn.setEnabled(true);
                        uriLink = uri.toString();
                        progressDialog.hide();
                        Toast.makeText(getContext(), "Picture uploaded", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {
                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress = (100.0 * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage("Uploaded " + (int)progress + "%");
                                }
                            });
                    }
}