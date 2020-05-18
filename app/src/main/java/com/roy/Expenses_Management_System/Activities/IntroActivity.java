package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.roy.Expenses_Management_System.Adapters.IntroPagerAdapter;
import com.roy.Expenses_Management_System.Models.IntroScreenItem;
import com.roy.Expenses_Management_System.R;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenpager;
    IntroPagerAdapter introPagerAdapter;
    TabLayout tabIndicator;
    Button  btnNext;
    int position = 0;
    Button btn_get_started;
    Animation btnAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Make the activity on full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //when this activity is about to launch we need to check if its opened before or note
        if(restorePrefData())
        {
            Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(mainActivity);
            finish();
        }


        setContentView(R.layout.activity_intro);


        //ini View
        btn_get_started = findViewById(R.id.btn_get_started);
        btnNext = findViewById(R.id.btn_next);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.btn_animation);

        //Fill Data
        final List<IntroScreenItem> mData =new ArrayList<>();
        mData.add(new IntroScreenItem("Add Expenses ","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit opti",R.drawable.addexpenses));
        mData.add(new IntroScreenItem("Details","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.",R.drawable.detailsexpenses));
        mData.add(new IntroScreenItem("Online Invoice","Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequatur quaerat sit veritatis facilis ad neque? Neque ratione minus excepturi officiis a officia libero eveniet. Officia commodi accusantium nulla suscipit optio.",R.drawable.reciepts));

        //set Viewpager
        screenpager = findViewById(R.id.screen_viewpaer);
        introPagerAdapter = new IntroPagerAdapter(this,mData);
        screenpager.setAdapter(introPagerAdapter);

        //Set up TabLayout with ViewPager
        tabIndicator.setupWithViewPager(screenpager);

        //For next button set OnClickListener
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                position=screenpager.getCurrentItem();
                if(position<mData.size())
                {
                    position++;
                    screenpager.setCurrentItem(position);
                }

                if(position==mData.size()-1){
                    //TODO: Show the get started button and hide the next and indicator btn
                    LoadedLastScreen();
                }
            }
        });

        //getStarted button clickListener
        btn_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                //also we need to save a boolean value to storage next time when the user
                //run the app we could know that user is already checked the intro
                //i'm going to use shared preference to that process
                    savePrefsData();
            }
        });



        //Tablayout add change listener
        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == mData.size()-1){
                    LoadedLastScreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isActivityOpenedBefore = pref.getBoolean("isIntroOpened",false);
        return isActivityOpenedBefore;
    }

    private void savePrefsData() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isIntroOpened",true);
        editor.commit();
    }


    //Show the get started button and hide the next and indicator btn
    private void LoadedLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btn_get_started.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);

        //TODO: Add the Aiation to the getstarted btn
        //Setup button Animation
        btn_get_started.setAnimation(btnAnim);

    }
}
