package com.roy.Expenses_Management_System.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
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
import com.roy.Expenses_Management_System.Adapters.OwnExpensesAdapter;
import com.roy.Expenses_Management_System.Models.AddExpensesModel;
import com.roy.Expenses_Management_System.R;

public class OwnExpensesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OwnExpensesAdapter ownExpensesAdapter;
    Dialog mEpicDialog;
    TextView mEpic_own_txt;
    Button mEpic_own_btn;
    ImageView mEpic_own_icon;
   private FirebaseAuth mAuth;
   private FirebaseUser mUser;
   private FirebaseDatabase mDatabase;
   private DatabaseReference mReference;
   private String mCurrentUserID;
   public static TextView own_total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_expenses);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mCurrentUserID = mUser.getUid();
        mDatabase = FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        mEpicDialog = new Dialog(this);
        recyclerView = findViewById(R.id.own_expenses_rv);
        own_total_price = findViewById(R.id.rv_own_total_price);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getOwnData();

    }

    private void getOwnData() {
        Query query = mReference.child("Expenses").orderByChild("user_ID").equalTo(mCurrentUserID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()) {

                    Toast.makeText(OwnExpensesActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
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

        ownExpensesAdapter = new OwnExpensesAdapter(options,getApplicationContext());
        recyclerView.setAdapter(ownExpensesAdapter);
    }

    private void showEpicDialog() {
        mEpicDialog.setContentView(R.layout.epic_dailog_datanotfound);
        mEpic_own_icon = (ImageView) mEpicDialog.findViewById(R.id.epic_icon);
        mEpic_own_txt = (TextView) mEpicDialog.findViewById(R.id.epic_txt);
        mEpic_own_btn = (Button) mEpicDialog.findViewById(R.id.epic_btn);

        mEpic_own_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEpicDialog.dismiss();
                Intent intent  = new Intent(OwnExpensesActivity.this,Add_expensesActivity.class);
                startActivity(intent);
            }
        });

        mEpicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mEpicDialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        OwnExpensesActivity.own_total_price.setText(String.valueOf(OwnExpensesAdapter.own_total));
    }

    @Override
    protected void onPause() {
        super.onPause();
        OwnExpensesAdapter.own_total =0;
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