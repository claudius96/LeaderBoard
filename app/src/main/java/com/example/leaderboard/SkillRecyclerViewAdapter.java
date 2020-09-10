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

public class SkillRecyclerViewAdapter extends RecyclerView.Adapter<SkillRecyclerViewAdapter.ViewHolder> {
    private Context mContext;
    private List<SkillData> mSkillDataList;
    private LayoutInflater mLayoutInflater;
    public SkillRecyclerViewAdapter(Context context,List<SkillData> skillDataList) {
        this.mContext = context;
        this.mSkillDataList = skillDataList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String score_country = mSkillDataList.get(position).getHours() + " learning hours, " +
                mSkillDataList.get(position).getCountry();
        holder.mName_person.setText(mSkillDataList.get(position).getName());
        holder.mScoreCountry.setText(score_country);
        Picasso.get()
                .load(mSkillDataList.get(position).getBadgeUrl())
                .into(holder.mBadgeUrl);

    }

    @Override
    public int getItemCount() {
        return mSkillDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mName_person;
        TextView mScoreCountry;
        ImageView mBadgeUrl;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mBadgeUrl = itemView.findViewById(R.id.badgeUrl);
            mScoreCountry = itemView.findViewById(R.id.score_country);
            mName_person = itemView.findViewById(R.id.name_person);


        }
    }
}
