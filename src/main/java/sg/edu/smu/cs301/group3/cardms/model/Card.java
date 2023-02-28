package sg.edu.smu.cs301.group3.cardms.model;

public class Card {
    private String name;
    private RewardType rewardType;

    public Card(String name, RewardType rewardType) {
        this.name = name;
        this.rewardType = rewardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RewardType getRewardType() {
        return rewardType;
    }

    public void setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
    }

}
