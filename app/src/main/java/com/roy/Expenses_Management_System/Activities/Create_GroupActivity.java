package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roy.Expenses_Management_System.Models.CreateGroup;
import com.roy.Expenses_Management_System.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Create_GroupActivity extends AppCompatActivity{
    CircleImageView rg_img;
    static int PReqCode=1;
    static int REQUESCODE=1;
    Uri PickedImgUri;

    private EditText rg_group_name,rg_group_size;
    private ProgressBar loadProgress;
    private Button regBtn;
    private FirebaseDatabase mDatabase;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__group);
        rg_group_name = findViewById(R.id.rg_group_name);
        rg_group_size = findViewById(R.id.rg_group_size);
        loadProgress = findViewById(R.id.rg_progressBar);
        regBtn = findViewById(R.id.rg_group_create_btn);
        loadProgress.setVisibility(View.INVISIBLE);

        mDatabase = FirebaseDatabase.getInstance();

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regBtn.setVisibility(View.INVISIBLE);
                loadProgress.setVisibility(View.VISIBLE);

                final String group_name = rg_group_name.getText().toString().trim();
                final String group_size = rg_group_size.getText().toString().trim();
                //final int size_group = Integer.parseInt(group_size);    We use it later

                if(group_name.isEmpty() || group_size.isEmpty())
                {
                    //Something goes to wrong: All field must be filled
                    //We need to display an error message
                    showMessage("Please verify all fields");
                    regBtn.setVisibility(View.VISIBLE);
                    loadProgress.setVisibility(View.INVISIBLE);

                }
                else
                {
                    // Everything is ok and all fields are filled now, we can start creating Group
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
                    String strdate = mdformat.format(calendar.getTime());

                    CreateGroup createGroup = new CreateGroup(group_name,group_size,strdate);

                    addGroup(createGroup);

                }
            }
        });

    }

    private void addGroup(CreateGroup createGroup) {
        final String groupSize = rg_group_size.getText().toString();
        final String groupName = rg_group_name.getText().toString();
        reference = mDatabase.getReference("Groups").push();
        //Get Group unique id and update group key
        final String key = reference.getKey();
        createGroup.setGroup_key(key);

        //Add Create Group date to firebase
        reference.setValue(createGroup).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Intent i = new Intent(Create_GroupActivity.this, Registration_FormActivity.class);
                i.putExtra("Group_key",key).putExtra("Group_size",groupSize).putExtra("Group_Name",groupName);
                startActivity(i);
                Toast.makeText(Create_GroupActivity.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
                loadProgress.setVisibility(View.GONE);
                regBtn.setVisibility(View.VISIBLE);
                rg_group_name.setText(null);
                rg_group_size.setText(null);
            }
        });
    }


    //Simple method to show the Toast message
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }
}
