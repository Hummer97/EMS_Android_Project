package com.roy.Expenses_Management_System.Activities;




import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roy.Expenses_Management_System.Models.AddExpensesModel;
import com.roy.Expenses_Management_System.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Add_expensesActivity extends AppCompatActivity implements ValueEventListener {
    private EditText mAdd_exp_details,mAdd_exp_price,mAdd_exp_date;
    private TextView mShow_Group_Name,mShow_UserName;
    private Button mAdd_exp_btn;
    private DatePickerDialog mDatePickerDialog;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase= FirebaseDatabase.getInstance();
    private DatabaseReference mReference = mDatabase.getReference();
    private DatabaseReference mGroupIDReference;
    private DatabaseReference mUsersReference,mGroupNameReference;
    private String mCurrentUserID,mCurrentGroupID,mUserName;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        mAdd_exp_date = findViewById(R.id.add_expense_date);
        mAdd_exp_btn = findViewById(R.id.add_expense_btn);
        mAdd_exp_details = findViewById(R.id.add_expense_details);
        mAdd_exp_price = findViewById(R.id.add_expense_price);
        mShow_Group_Name = findViewById(R.id.show_GroupName);
        mShow_UserName = findViewById(R.id.show_UserName);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        mProgressDialog = new ProgressDialog(Add_expensesActivity.this);




        setDateTimeField();
        mAdd_exp_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDatePickerDialog.show();
                return false;
            }
        });
        // get user_key and group_key
        mCurrentUserID = mUser.getUid();
        mGroupIDReference = mReference.child("Users").child(mCurrentUserID).child("group_ID");
        mUsersReference = mReference.child("Users").child(mCurrentUserID).child("user_name");
        mGroupNameReference = mReference.child("Users").child(mCurrentUserID).child("group_Name");

    }

    private void addExpenseData(AddExpensesModel addExpensesModel) {
        mReference = mDatabase.getReference("Expenses").push();
        //Get Expense unique ID and update expense_key;
        final String key = mReference.getKey();
        addExpensesModel.setExpenses_ID(key);

        mReference.setValue(addExpensesModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                mProgressDialog.dismiss();
                Intent intent = new Intent(Add_expensesActivity.this, DashboardActivity.class);
                startActivity(intent);
                //showMessage("Successfully Inserted");
            }
        });
    }

    //Simple method to show the Toast message
    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();

    }

    // Date Picker
    private void setDateTimeField() {

        Calendar newCalendar = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
                final Date startDate = newDate.getTime();

                String fdate = sd.format(startDate);

                mAdd_exp_date.setText(fdate);

            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mDatePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.getValue(String.class)!=null)
            {
                String key = dataSnapshot.getKey();
                if (key.equals("group_ID"))
                {
                    mCurrentGroupID = dataSnapshot.getValue(String.class);
                }if (key.equals("user_name"))
                {
                    //String UserName = dataSnapshot.getValue(String.class);
                    mUserName = dataSnapshot.getValue(String.class);
                    mShow_UserName.setText(mUserName);


                }
                if (key.equals("group_Name"))
                {
                    String groupName = dataSnapshot.getValue(String.class);
                    mShow_Group_Name.setText(groupName);
                }
            }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        mGroupIDReference.addValueEventListener(this);
        mGroupNameReference.addValueEventListener(this);
        mUsersReference.addValueEventListener(this);

        mAdd_exp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String expense_detail = mAdd_exp_details.getText().toString().trim();
                final String expense_price = mAdd_exp_price.getText().toString().trim();
                final String expense_date = mAdd_exp_date.getText().toString().trim();
                if(!TextUtils.isEmpty(expense_detail) && !TextUtils.isEmpty(expense_price) && !TextUtils.isEmpty(expense_date))
                {
                    mProgressDialog.setMessage("Please Wait...");
                    mProgressDialog.show();
                    AddExpensesModel addExpensesModel = new AddExpensesModel(mCurrentGroupID, mUserName,mCurrentUserID, expense_detail, expense_price, expense_date);
                    addExpenseData(addExpensesModel);
                }
                else
                {
                    showMessage("Please Fill the all field");
                }
            }


        });

    }
}


