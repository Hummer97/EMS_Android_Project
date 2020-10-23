package com.roy.Expenses_Management_System.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.roy.Expenses_Management_System.Models.AddExpensesModel;
import com.roy.Expenses_Management_System.Models.ItemExpenses;
import com.roy.Expenses_Management_System.R;

import java.util.List;

public class ExpensesAdapter extends FirebaseRecyclerAdapter<AddExpensesModel,ExpensesAdapter.ExpensesViewHolder> {

    public ExpensesAdapter(@NonNull FirebaseRecyclerOptions<AddExpensesModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position, @NonNull AddExpensesModel model) {

        holder.tv_title.setText(model.getUser_Name());
        holder.tv_content.setText(model.getExpense_details());
        holder.tv_date.setText(model.getAdded_date());
        holder.tv_price.setText(model.getExpense_price()+"Rs");
    }

    @NonNull
    @Override
    public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_allexpenses,parent,false);
        return new ExpensesViewHolder(layout);

    }
    public class ExpensesViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title,tv_content,tv_date,tv_price;
        ImageView img_user;
        RelativeLayout container;

        public ExpensesViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tv_title = itemView.findViewById(R.id.allExp_title);
            tv_content = itemView.findViewById(R.id.allExp_description);
            tv_date = itemView.findViewById(R.id.allExp_date);
            tv_price = itemView.findViewById(R.id.allExp_price);
            img_user = itemView.findViewById(R.id.allExp_UserImg);

        }
    }

}
