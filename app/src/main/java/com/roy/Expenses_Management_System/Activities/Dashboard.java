package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.roy.Expenses_Management_System.R;

public class Dashboard extends AppCompatActivity {

    private View addExpenses;
    private View allExpense;
    private View profile;
    private View news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        addExpenses = (LinearLayout) findViewById(R.id.AddExpenses);
        allExpense = (LinearLayout) findViewById(R.id.allExpense);
        profile = (LinearLayout) findViewById(R.id.profile);
        news = (LinearLayout) findViewById(R.id.News);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Profile.class);
                startActivity(intent);
            }
        });

        addExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Add_expenses.class);
                startActivity(intent);
            }
        });
        allExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Expenses.class);
                startActivity(intent);
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, News.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
}
