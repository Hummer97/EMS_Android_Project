package com.roy.Expenses_Management_System.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.roy.Expenses_Management_System.Activities.ExpensesActivity;
import com.roy.Expenses_Management_System.Activities.OwnExpensesActivity;
import com.roy.Expenses_Management_System.Models.AddExpensesModel;
import com.roy.Expenses_Management_System.R;

import java.util.Collections;

public class OwnExpensesAdapter extends FirebaseRecyclerAdapter<AddExpensesModel,OwnExpensesAdapter.OwnExpensesViewHolder> {
    private Context context;
    public static int own_total =0;

    public OwnExpensesAdapter(@NonNull FirebaseRecyclerOptions<AddExpensesModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull OwnExpensesViewHolder holder, int position, @NonNull AddExpensesModel model) {
        holder.tv_title.setText(model.getUser_Name());
        holder.tv_content.setText(model.getExpense_details());
        holder.tv_date.setText(model.getAdded_date());
        holder.tv_price.setText(model.getExpense_price()+"Rs");

        for(int i = 0; i< Collections.singletonList(model).size(); i++){
            own_total +=  Integer.parseInt(Collections.singletonList(model).get(i).getExpense_price());
        }

        OwnExpensesActivity.own_total_price.setText(String.valueOf(own_total)+" Rs");
        Log.d("Expenses","Price: "+own_total);

    }

    @NonNull
    @Override
    public OwnExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_own_expenses,parent,false);
        return new OwnExpensesViewHolder(layout);
    }

    public class OwnExpensesViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title,tv_content,tv_date,tv_price;
        ImageView img_user;
        RelativeLayout container;
        public OwnExpensesViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.own_container);
            tv_title = itemView.findViewById(R.id.ownExp_title);
            tv_content = itemView.findViewById(R.id.ownExp_description);
            tv_date = itemView.findViewById(R.id.ownExp_date);
            tv_price = itemView.findViewById(R.id.ownExp_price);
            img_user = itemView.findViewById(R.id.ownExp_UserImg);
        }
    }
}
