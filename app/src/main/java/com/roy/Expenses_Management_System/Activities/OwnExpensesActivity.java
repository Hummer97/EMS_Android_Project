package com.roy.Expenses_Management_System.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.roy.Expenses_Management_System.Adapters.OwnExpensesAdapter;
import com.roy.Expenses_Management_System.Models.AddExpensesModel;
import com.roy.Expenses_Management_System.R;

public class OwnExpensesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OwnExpensesAdapter ownExpensesAdapter;
   private FirebaseAuth mAuth;
   private FirebaseUser mUser;
   private FirebaseDatabase mDatabase;
   private DatabaseReference mReference;
   private String mCurrentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_expenses);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mCurrentUserID = mUser.getUid();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();

        recyclerView = findViewById(R.id.own_expenses_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Query query = mReference.child("Expenses").orderByChild("user_ID").equalTo(mCurrentUserID);
        FirebaseRecyclerOptions<AddExpensesModel> options =
                new FirebaseRecyclerOptions.Builder<AddExpensesModel>()
                        .setQuery(query, AddExpensesModel.class)
                        .build();

        ownExpensesAdapter = new OwnExpensesAdapter(options);
        recyclerView.setAdapter(ownExpensesAdapter);
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        ownExpensesAdapter.startListening();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        ownExpensesAdapter.stopListening();
    }
}