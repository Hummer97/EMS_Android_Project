package com.roy.Expenses_Management_System.Activities;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.roy.Expenses_Management_System.R;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button clickMeButton = (Button) findViewById(R.id.createGroupBtn);
        Button logInButton = findViewById(R.id.loginBtn);
        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Create_Group.class);
                startActivity(intent);
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Login_page.class);
                startActivity(intent);
            }
        });

    }

//    @Override
//    public void onClick(View view) {
//        //Toast.makeText(this,"Button is working",Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(MainActivity.this,Create_Group.class);
//        startActivity(intent);
//    }


}
