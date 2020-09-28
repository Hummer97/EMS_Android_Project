package com.roy.Expenses_Management_System.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.roy.Expenses_Management_System.R;

import java.time.Instant;

public class Dashboard extends AppCompatActivity {

    private View addExpenses;
    private View allExpense;
    private View profile;
    private View news;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        addExpenses = (LinearLayout) findViewById(R.id.AddExpenses);
        allExpense = (LinearLayout) findViewById(R.id.allExpense);
        profile = (LinearLayout) findViewById(R.id.profile);
        news = (LinearLayout) findViewById(R.id.News);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null)
                {
                    Intent loginIntent = new Intent(Dashboard.this, Login_page.class);
                     loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                     startActivity(loginIntent);
                }
            }
        };
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
       @Override
       protected void onStart() {
           super.onStart();

           mAuth.addAuthStateListener(mAuthListener);

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
