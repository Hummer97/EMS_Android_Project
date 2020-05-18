package com.roy.Expenses_Management_System.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.roy.Expenses_Management_System.R;

public class Add_expenses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        final EditText Item = (EditText) findViewById(R.id.itemDis);
        final EditText Price = (EditText) findViewById(R.id.itemPrice);
        Button ShowData = (Button) findViewById(R.id.showData);
//        boolean d=true;
//        if(d == true)
//        {
//            ShowData.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(this,"Data Submitted Successfully",Toast.LENGTH_LONG).show();
//                }
//            });
//        }

//        ShowData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String Itemdata = Item.getText().toString();
//                String ItemPrice = Price.getText().toString();
//                ShowItem.setText(Itemdata);
//                ShowPrice.setText(ItemPrice);
//            }
//        });
    }
}
