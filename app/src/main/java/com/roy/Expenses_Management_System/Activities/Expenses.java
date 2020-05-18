package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.roy.Expenses_Management_System.Adapters.ExpensesAdapter;
import com.roy.Expenses_Management_System.Models.ItemExpenses;
import com.roy.Expenses_Management_System.R;

import java.util.ArrayList;
import java.util.List;

public class Expenses extends AppCompatActivity {


    RecyclerView ExpensesRecyclerView;
    ExpensesAdapter expensesAdapter;
    List<ItemExpenses> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

        //ini view
        ExpensesRecyclerView = findViewById(R.id.expenses_rv);
        mData = new ArrayList<>();

        //fill the row data here

        mData.add(new ItemExpenses("Avinash Roy","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new ItemExpenses("Vishwjeet Roy","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new ItemExpenses("Ramesh","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new ItemExpenses("Abhishek","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new ItemExpenses("Abhinav","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new ItemExpenses("Ravinder","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new ItemExpenses("Brijmohan","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new ItemExpenses("Chandu","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new ItemExpenses("brijesh","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));

        //adepter ini and setup
        expensesAdapter = new ExpensesAdapter(this,mData);
        ExpensesRecyclerView.setAdapter(expensesAdapter);

        ExpensesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
