
package com.example.jharwalshoes.myshoes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserReward {

    @SerializedName("offer")
    @Expose
    private Integer offer;
    @SerializedName("offer_name")
    @Expose
    private String offerName;
    @SerializedName("rewards_image")
    @Expose
    private String rewardsImage;

    public Integer getOffer() {
        return offer;
    }

    public void setOffer(Integer offer) {
        this.offer = offer;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public String getRewardsImage() {
        return rewardsImage;
    }

    public void setRewardsImage(String rewardsImage) {
        this.rewardsImage = rewardsImage;
    }

    @Override
    public String toString() {
        return "UserReward{" +
                "offer=" + offer +
                ", offerName='" + offerName + '\'' +
                ", rewardsImage='" + rewardsImage + '\'' +
                '}';
    }
}
