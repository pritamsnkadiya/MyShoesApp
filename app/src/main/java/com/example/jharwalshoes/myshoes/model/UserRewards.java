
package com.example.jharwalshoes.myshoes.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRewards {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userRewards")
    @Expose
    private List<UserReward> userRewards = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UserReward> getUserRewards() {
        return userRewards;
    }

    public void setUserRewards(List<UserReward> userRewards) {
        this.userRewards = userRewards;
    }

    @Override
    public String toString() {
        return "UserRewards{" +
                "status='" + status + '\'' +
                ", userRewards=" + userRewards +
                '}';
    }
}
