package com.example.dsm2017.scoreboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by geni on 2017. 9. 21..
 */

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    private ArrayList<RankValueObject> mRVO;
    private Context mContext;
    public RankAdapter(ArrayList<RankValueObject> RVO, Context context){
        this.mContext = context;
        this.mRVO = RVO;
    }

    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_board, parent, false);
        return new RankAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RankAdapter.ViewHolder holder, int position) {
        holder.name.setText(mRVO.get(position).getName());
        holder.affiliation.setText(mRVO.get(position).getAffiliation());
        holder.score.setText(mRVO.get(position).getScore()+"");
        Glide.with(mContext).load("http://52.79.134.200:5590/user-image/" + mRVO.get(position).getPhone()).centerCrop().into(holder.image);
        switch (position){
            case 0:
                holder.edge.setBackgroundResource(R.drawable.rank_first);
                break;
            case 1:
                holder.edge.setBackgroundResource(R.drawable.rank_second);
                break;
            case 2:
                holder.edge.setBackgroundResource(R.drawable.rank_third);
                break;
            default:
                holder.edge.setBackgroundResource(R.drawable.rank);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, affiliation, score;
        private ImageView image;
        private LinearLayout edge;

        public ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.name);
            affiliation = view.findViewById(R.id.temper);
            score = view.findViewById(R.id.score);
            image = view.findViewById(R.id.image);
            edge = view.findViewById(R.id.edge);
        }
    }
}
