package com.example.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class LeaderBoardRecyclerViewAdapter extends RecyclerView.Adapter<LeaderBoardRecyclerViewAdapter.AppViewHolder> {
    public Context mContext;
    LayoutInflater mInflater;
    List<LeaderBoardData> mLeaderBoardDataList;

    public LeaderBoardRecyclerViewAdapter(Context context, List<LeaderBoardData> leaderBoardDataList) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        this.mLeaderBoardDataList = leaderBoardDataList;
    }

    @NonNull
    @Override
    public AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item_list, parent, false);

        return new AppViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolder holder, int position) {
        String score_country = mLeaderBoardDataList.get(position).getScore() + " learning hours, " +
                mLeaderBoardDataList.get(position).getCountry();
        holder.mName_person.setText(mLeaderBoardDataList.get(position).getName());
        holder.mScoreCountry.setText(score_country);
        Picasso.get()
                .load(mLeaderBoardDataList.get(position).getBadgeUrl())
                .into(holder.mBadgeUrl);


    }

    @Override
    public int getItemCount() {
        return mLeaderBoardDataList.size();
    }

    public class AppViewHolder extends RecyclerView.ViewHolder{
        TextView mName_person;
        TextView mScoreCountry;
        ImageView mBadgeUrl;


        public AppViewHolder(@NonNull View itemView) {
            super(itemView);
            mBadgeUrl = itemView.findViewById(R.id.badgeUrl);
            mScoreCountry = itemView.findViewById(R.id.score_country);
            mName_person = itemView.findViewById(R.id.name_person);

        }


    }
}
