package com.roy.Expenses_Management_System.Activities;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

        import com.google.firebase.auth.FirebaseAuth;
        import com.roy.Expenses_Management_System.R;
        import com.roy.Expenses_Management_System.sesion.SharedPrefManager;
//import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button clickMeButton;
    private Button logInButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickMeButton = (Button) findViewById(R.id.createGroupBtn);
        logInButton = findViewById(R.id.loginBtn);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent loginIntent = new Intent(MainActivity.this, DashboardActivity.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(loginIntent);
                }
            }
        };

//        if(!(SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn())){
//            Intent intent = new Intent(getApplicationContext(),DashboardActivity.class);
//            startActivity(intent);
//        }



//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                if(firebaseAuth.getCurrentUser() != null)
//                {
//                    Intent loginIntent = new Intent(MainActivity.this, DashboardActivity.class);
//                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(loginIntent);
//                }
//            }
//        };

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);

        clickMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Create_GroupActivity.class);
                startActivity(intent);
            }
        });
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login_pageActivity.class);
                startActivity(intent);
            }
        });
    }


}
