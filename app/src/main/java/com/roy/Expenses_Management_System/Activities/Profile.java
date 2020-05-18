package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.roy.Expenses_Management_System.R;

public class Profile extends AppCompatActivity {
    private View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //back = (ImageButton) findViewById(R.id.back_key);

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Profile.this, Dashboard.class);
//                startActivity(intent);
//            }
//        });


    }


}
