package com.example.shower.artist_shower;

/**
 * Created by dsm2016 on 2017-09-22.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WaitAdapter extends RecyclerView.Adapter<WaitAdapter.ViewHolder> {
    private ArrayList<WaitItem> mDataset;

    public WaitAdapter(ArrayList<WaitItem> myDataset) {
        this.mDataset = myDataset;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView school;
        public TextView score;

        public ViewHolder(View view) {
            super(view);
            school = (TextView) view.findViewById(R.id.school);
            name = (TextView) view.findViewById(R.id.name);
            score = (TextView) view.findViewById(R.id.score);
        }
    }

    public void setData(WaitItem[] datas){
        ArrayList<WaitItem> arrayListDatas = new ArrayList<>();
        for(WaitItem data : datas){
            arrayListDatas.add(data);
        }
        mDataset = arrayListDatas;
    }


    @Override
    public WaitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.wait_recycler, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(WaitAdapter.ViewHolder holder, int position) {
        holder.name.setText(mDataset.get(position).getName());
        holder.school.setText(mDataset.get(position).getSchool());
        holder.score.setText(mDataset.get(position).getScore());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}