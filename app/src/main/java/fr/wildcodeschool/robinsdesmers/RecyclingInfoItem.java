package fr.wildcodeschool.robinsdesmers;

public class RecyclingInfoItem {

    private int logoRecycling;
    private String titleRecyclingInfo;
    private String descriptionRecyclingInfo;

    public RecyclingInfoItem(int logoRecycling, String titleRecyclingInfo, String descriptionRecyclingInfo) {
        this.logoRecycling = logoRecycling;
        this.titleRecyclingInfo = titleRecyclingInfo;
        this.descriptionRecyclingInfo = descriptionRecyclingInfo;
    }

    public int getLogoRecycling() {
        return logoRecycling;
    }

    public void setLogoRecycling(int logoRecycling) {
        this.logoRecycling = logoRecycling;
    }

    public String getTitleRecyclingInfo() {
        return titleRecyclingInfo;
    }

    public void setTitleRecyclingInfo(String titleRecyclingInfo) {
        this.titleRecyclingInfo = titleRecyclingInfo;
    }

    public String getDescriptionRecyclingInfo() {
        return descriptionRecyclingInfo;
    }

    public void setDescriptionRecyclingInfo(String descriptionRecyclingInfo) {
        this.descriptionRecyclingInfo = descriptionRecyclingInfo;
    }
}
