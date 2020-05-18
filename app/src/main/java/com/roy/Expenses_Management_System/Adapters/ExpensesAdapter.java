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

import com.roy.Expenses_Management_System.Models.ItemExpenses;
import com.roy.Expenses_Management_System.R;

import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesViewHolder> {


    Context mContext;
    List<ItemExpenses> mData;

    public ExpensesAdapter(Context mContext, List<ItemExpenses> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ExpensesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_allexpenses,parent,false);
        return new ExpensesViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpensesViewHolder holder, int position) {
            //Bind data hare


        //We can apply animation here
        holder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        holder.tv_title.setText(mData.get(position).getTitle());
        holder.tv_content.setText(mData.get(position).getContent());
        holder.tv_date.setText(mData.get(position).getDate());
        holder.img_user.setImageResource(mData.get(position).getUserPhoto());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ExpensesViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title,tv_content,tv_date;
        ImageView img_user;
        RelativeLayout container;

        public ExpensesViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_description);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_user = itemView.findViewById(R.id.nwimg_user);

        }
    }

}
