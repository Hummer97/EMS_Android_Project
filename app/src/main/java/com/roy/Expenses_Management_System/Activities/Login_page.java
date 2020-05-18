package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.roy.Expenses_Management_System.R;

public class Login_page extends AppCompatActivity {
    private View login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_btn=(Button)findViewById(R.id.lg_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_page.this,Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
