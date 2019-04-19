package com.example.jharwalshoes.myshoes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jharwalshoes.myshoes.R;
import com.example.jharwalshoes.myshoes.model.UserReward;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowUserRewardsAdapter extends RecyclerView.Adapter<ShowUserRewardsAdapter.ViewHolder> {

    private Context context;
    private View view;
    private ViewHolder viewHolder;
    private List<UserReward> userRewardList;
    private FragmentActivity activity;

    public ShowUserRewardsAdapter(Context context, FragmentActivity activity, List<UserReward> userRewardList) {
        this.context = context;
        this.userRewardList = userRewardList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.user_rewards_layout, viewGroup, false);

        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Glide.with(context).load(userRewardList.get(i).getRewardsImage()).into(viewHolder.offer_image);
        viewHolder.tv_offer_name.setText(userRewardList.get(i).getOfferName());
        viewHolder.offer.setText(String.valueOf(userRewardList.get(i).getOffer()) + "%");
    }

    @Override
    public int getItemCount() {
        return userRewardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CircleImageView offer_image;
        private TextView tv_offer_name, offer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            findid();
        }

        private void findid() {
            offer_image = view.findViewById(R.id.offer_image);
            tv_offer_name = view.findViewById(R.id.tv_offer_name);
            offer = view.findViewById(R.id.offer);
        }
    }
}
