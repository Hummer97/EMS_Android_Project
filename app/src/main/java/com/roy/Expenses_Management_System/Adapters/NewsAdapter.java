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

import com.roy.Expenses_Management_System.Models.NewsItem;
import com.roy.Expenses_Management_System.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    Context mContext;
    List<NewsItem> mData;

    public NewsAdapter(Context mContext, List<NewsItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.newsitem,parent,false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        //Set Animation
        holder.nwimg_user.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.nwcontainer.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        //Bind Data
        holder.tv_nwtitle.setText(mData.get(position).getTitle());
        holder.tv_newscontent.setText(mData.get(position).getContext());
        holder.tv_nwdate.setText(mData.get(position).getDate());
        holder.nwimg_user.setImageResource(mData.get(position).getUserPhoto());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView tv_nwtitle,tv_newscontent,tv_nwdate;
        ImageView nwimg_user;
        RelativeLayout nwcontainer;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            nwcontainer = itemView.findViewById(R.id.newscontainer);
            tv_nwtitle = itemView.findViewById(R.id.tv_newstitle);
            tv_newscontent = itemView.findViewById(R.id.tv_newsdescription);
            tv_nwdate = itemView.findViewById(R.id.tv_newsdate);
            nwimg_user = itemView.findViewById(R.id.nwimg_user);
        }




    }

}
