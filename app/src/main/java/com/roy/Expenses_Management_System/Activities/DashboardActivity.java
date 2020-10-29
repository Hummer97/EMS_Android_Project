package com.roy.Expenses_Management_System.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roy.Expenses_Management_System.R;
import com.roy.Expenses_Management_System.sesion.SharedPrefManager;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private View addExpenses;
    private View allExpense, ownExpense;
    private View profile;
    private View news;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    private DatabaseReference mReference;
    private DatabaseReference mGroupIDReference;
    private FirebaseUser mUser;
    private String mCurrentGroupID,mCurrentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        addExpenses = (LinearLayout) findViewById(R.id.AddExpenses);
        allExpense = (LinearLayout) findViewById(R.id.allExpense);
        ownExpense = (LinearLayout) findViewById(R.id.OwnExpenses);
        profile = (LinearLayout) findViewById(R.id.profile);
        news = (LinearLayout) findViewById(R.id.News);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mToolbar = findViewById(R.id.dashboard_toolbar);


        mReference = FirebaseDatabase.getInstance().getReference("Users");

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mCurrentUserID = mUser.getUid();

        if(SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn()){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }

    //    @Override
//    protected void onStop() {
//        super.onStop();
//    }
//
    @Override
    protected void onStart() {
        super.onStart();

       // mAuth.addAuthStateListener(mAuthListener);


        /*-------------------------------------- Tool Bar ------------------------------------------*/

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        /*-------------------------------------Navigation Drawer Menu-------------------------------------------*/
        mNavigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        addExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, Add_expensesActivity.class);
                startActivity(intent);
            }
        });
        allExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserGroupID();
            }
        });
        ownExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, OwnExpensesActivity.class);
                startActivity(intent);
                //getUserGroupIDForOwnExpenses();
            }
        });
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getUserGroupID() {
        Log.d(getLocalClassName(),"mCurrentGroupID : "+mCurrentGroupID);
        mGroupIDReference = mReference.child(mCurrentUserID).child("group_ID");
        mGroupIDReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mCurrentGroupID = dataSnapshot.getValue().toString().trim();
                Log.d(getLocalClassName(),"mCurrentGroupID : "+mCurrentGroupID);
                Intent intent = new Intent(DashboardActivity.this, ExpensesActivity.class);
                intent.putExtra("groupID",mCurrentGroupID);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId())
        {
            case R.id.nav_home:
                break;
            case R.id.nav_add_expenses:
                Intent add_expenses_intent = new Intent(DashboardActivity.this, Add_expensesActivity.class);
                startActivity(add_expenses_intent);
                break;
            case R.id.nav_all_expenses:
                Intent all_expenses_intent = new Intent(DashboardActivity.this, ExpensesActivity.class);
                startActivity(all_expenses_intent);
                break;
            case R.id.nav_own_expenses:
                break;
            case R.id.nav_notification:
                Intent notification_intent = new Intent(DashboardActivity.this, NewsActivity.class);
                startActivity(notification_intent);
                break;
            case R.id.nav_profile:
                Intent profile_intent = new Intent(DashboardActivity.this, ProfileActivity.class);
                startActivity(profile_intent);
                break;
            case R.id.nav_logOnt:
                logOut();
                break;
            case R.id.nav_setting:
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logOut() {
        SharedPrefManager.getInstance(getApplicationContext()).logout();
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
