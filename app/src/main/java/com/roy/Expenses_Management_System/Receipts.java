package com.roy.Expenses_Management_System;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Receipts extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseDatabase mDatabase;
    TextView rpt_txt_name,rpt_txt_mail,rpt_txt_all_expenses,rpt_txt_own_expenses,rpt_txt_grand_total;
    private String mCurrentUser, mMail,group_key;
    private DatabaseReference mReference;
    private int mPrice = 0,mAllPrice =0,group_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts);
        rpt_txt_name = findViewById(R.id.rpt_txt_name);
        rpt_txt_mail = findViewById(R.id.rpt_txt_mail);
        rpt_txt_all_expenses = findViewById(R.id.rpt_txt_all_total);
        rpt_txt_own_expenses = findViewById(R.id.rpt_txt_own_total);
        rpt_txt_grand_total = findViewById(R.id.rpt_txt_grand_value);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mCurrentUser = mUser.getUid();

        mMail = mUser.getEmail();
        mDatabase= FirebaseDatabase.getInstance();
        mReference = mDatabase.getReference();
        rpt_txt_mail.setText(mMail);


        getOwnExpenses();
        getUserDetails();

    }

    private void getGrandValue() {

    }

    private void getOwnExpenses() {
        Query query = mReference.child("Expenses").orderByChild("user_ID").equalTo(mCurrentUser);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    String price = ds.child("expense_price").getValue().toString();
                    mPrice = mPrice + Integer.parseInt(price);
                    Log.d("Receipts","Iner Price : "+mPrice);
                }
                rpt_txt_own_expenses.setText(""+mPrice);
                Log.d("Receipts","Outer Price : "+mPrice);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getUserDetails() {
        mDatabase.getReference().child("Users").child(mCurrentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("user_name").getValue().toString();
                group_key = snapshot.child("group_ID").getValue().toString();

                rpt_txt_name.setText(name);
                getGroupSize(group_key);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getGroupSize(final String group_key) {
                mReference.child("Groups").child(group_key).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String get_group_size = snapshot.child("group_size").getValue().toString();
                        group_size = Integer.parseInt(get_group_size);
                        getAllExpenses(group_key);
                        Log.d("Receipts","Check Data: "+group_size);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

    private void getAllExpenses(String group_key) {
        Query query = FirebaseDatabase.getInstance().getReference().child("Expenses").orderByChild("group_ID").equalTo(group_key);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren())
                {
                    String allprice = ds.child("expense_price").getValue().toString();
                    mAllPrice = mAllPrice + Integer.parseInt(allprice);

                }
                rpt_txt_all_expenses.setText(""+mAllPrice);
                Log.d("Receipts","Price : "+mAllPrice);
                try{
                    String total = String.valueOf((mAllPrice/group_size)-mPrice);
                    rpt_txt_grand_total.setText(total);
                    Log.d("Receipts","Check Total Value: "+total);
                }catch (ArithmeticException e)
                {
                    Log.d("Receipts","Check Total Value Exception: "+e);
                    Toast.makeText(getApplicationContext(),"Somethings went wrong",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}