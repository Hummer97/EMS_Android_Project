package com.roy.Expenses_Management_System.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.roy.Expenses_Management_System.Adapters.ExpensesAdapter;
import com.roy.Expenses_Management_System.Models.AddExpensesModel;
import com.roy.Expenses_Management_System.Models.ItemExpenses;
import com.roy.Expenses_Management_System.R;

import java.util.ArrayList;
import java.util.List;

public class Expenses extends AppCompatActivity{


    RecyclerView recyclerView;
    ExpensesAdapter expensesAdapter;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private DatabaseReference mGroupIDReference;
    private String mCurrentUserID,mCurrentGroupID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        mReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mCurrentUserID = mUser.getUid();

        //ini view
        recyclerView = findViewById(R.id.expenses_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


         mGroupIDReference = mReference.child(mCurrentUserID).child("group_ID");
         mGroupIDReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 String group_id = dataSnapshot.getValue().toString().trim();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
        });



        Query query = FirebaseDatabase.getInstance().getReference().child("Expenses").orderByChild("user_ID").equalTo(mCurrentUserID);
        FirebaseRecyclerOptions<AddExpensesModel> options =
                new FirebaseRecyclerOptions.Builder<AddExpensesModel>()
                        .setQuery(query, AddExpensesModel.class)
                        .build();

        expensesAdapter = new ExpensesAdapter(options);
        recyclerView.setAdapter(expensesAdapter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        expensesAdapter.startListening();
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        expensesAdapter.stopListening();
    }
}
