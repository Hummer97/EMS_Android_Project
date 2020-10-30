package com.roy.Expenses_Management_System.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roy.Expenses_Management_System.R;
import com.roy.Expenses_Management_System.sesion.SharedPrefManager;

public class ProfileActivity extends AppCompatActivity {
    private TextView mProfile_UserName,mProfile_UserMobileNo,mProfile_User_GroupName,mUserMail,mUserID;
    private ImageView mProfilePic;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private String mCurrentUserID,mCurrentMailID;
    private String group_key,current_user_group_name;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mProfile_UserName = findViewById(R.id.profile_userName);
        mProfile_User_GroupName = findViewById(R.id.profile_groupName);
        mProfile_UserMobileNo = findViewById(R.id.profile_userMobileNo);
        mUserMail = findViewById(R.id.profile_userMail);
        mProfilePic = findViewById(R.id.profile_userPic);
        mProgressDialog = new ProgressDialog(ProfileActivity.this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mReference = FirebaseDatabase.getInstance().getReference("Users");
        mCurrentUserID = mUser.getUid(); // fetch the current User ID
        mCurrentMailID = mUser.getEmail();
        mProgressDialog.setMessage("Loading Please wait..");
        mProgressDialog.show();
        mReference.child(mCurrentUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name = dataSnapshot.child("user_name").getValue().toString();
                        String mobileNo = dataSnapshot.child("mobile_No").getValue().toString();
                        group_key = dataSnapshot.child("group_ID").getValue().toString();
                        getGroupName(group_key);


                        mProfile_UserName.setText(name);
                        mUserMail.setText(mCurrentMailID);
                        mProfile_UserMobileNo.setText(mobileNo);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ProfileActivity.this, "Data Base erroe"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    // Fetch the group name
    private void getGroupName(String group_key) {

            mReference = FirebaseDatabase.getInstance().getReference("Groups");
            mReference.child(group_key).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mProgressDialog.dismiss();
                    current_user_group_name = dataSnapshot.child("group_Name").getValue().toString();
                    mProfile_User_GroupName.setText(current_user_group_name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
    }
}
