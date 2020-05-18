package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.roy.Expenses_Management_System.Adapters.NewsAdapter;
import com.roy.Expenses_Management_System.Models.NewsItem;
import com.roy.Expenses_Management_System.R;

import java.util.ArrayList;
import java.util.List;

public class News extends AppCompatActivity {

    RecyclerView NewsRecyclerView;
    com.roy.Expenses_Management_System.Adapters.NewsAdapter NewsAdapter;
    List<NewsItem> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //ini View
        NewsRecyclerView = findViewById(R.id.news_rv);
        mData = new ArrayList<>();
        //fill the row data here

        mData.add(new NewsItem("Avinash Roy","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new NewsItem("Vishwjeet Roy","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new NewsItem("Ramesh","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new NewsItem("Abhishek","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new NewsItem("Abhinav","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new NewsItem("Ravinder","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new NewsItem("Brijmohan","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new NewsItem("Chandu","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));
        mData.add(new NewsItem("brijesh","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.","13 june 2020",R.drawable.avatar));

        //adepter ini and setup
        NewsAdapter = new NewsAdapter(this,mData);
        NewsRecyclerView.setAdapter(NewsAdapter);
        NewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}
