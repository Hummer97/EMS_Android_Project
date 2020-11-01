package com.roy.Expenses_Management_System.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class ExpensesActivity extends AppCompatActivity{


    RecyclerView recyclerView;
    ExpensesAdapter expensesAdapter;
    Dialog epicDialog;
    TextView mEpic_txt;
    Button mEpic_btn;
    ImageView mEpic_icon;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mReference;
    private DatabaseReference mGroupIDReference;
    private String mCurrentUserID,mCurrentGroupID;
    public  static  TextView total_price;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        total_price = findViewById(R.id.rv_total_price);
        mReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mCurrentUserID = mUser.getUid();
        epicDialog = new Dialog(this);
        recyclerView = findViewById(R.id.expenses_rv);
        mSwipeRefreshLayout = findViewById(R.id.exp_refresh);

        //ini view




        final Intent intent = getIntent();
        mCurrentGroupID = intent.getExtras().getString("groupID");
        Log.d("Dashboard","Current Group ID : "+mCurrentGroupID);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

        getdata();


    }

    @Override
    protected void onResume() {
        super.onResume();
        ExpensesActivity.total_price.setText(String.valueOf(ExpensesAdapter.g_total));
    }

    @Override
    protected void onPause() {
        super.onPause();
        ExpensesAdapter.g_total =0;
    }

    private void getdata(){
        Query query = FirebaseDatabase.getInstance().getReference().child("Expenses").orderByChild("group_ID").equalTo(mCurrentGroupID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    Toast.makeText(ExpensesActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
                }
                else {
                    showEpicDialog();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });


        FirebaseRecyclerOptions<AddExpensesModel> options =
                new FirebaseRecyclerOptions.Builder<AddExpensesModel>()
                        .setQuery(query, AddExpensesModel.class)
                        .build();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        expensesAdapter = new ExpensesAdapter(options,getApplicationContext());
        recyclerView.setAdapter(expensesAdapter);
    }

    private void showEpicDialog() {
        epicDialog.setContentView(R.layout.epic_dailog_datanotfound);
        mEpic_icon = (ImageView) epicDialog.findViewById(R.id.epic_icon);
        mEpic_txt = (TextView) epicDialog.findViewById(R.id.epic_txt);
        mEpic_btn = (Button) epicDialog.findViewById(R.id.epic_btn);

        mEpic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                epicDialog.dismiss();
                Intent intent  = new Intent(ExpensesActivity.this,Add_expensesActivity.class);
                startActivity(intent);
            }
        });

        epicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicDialog.show();
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
