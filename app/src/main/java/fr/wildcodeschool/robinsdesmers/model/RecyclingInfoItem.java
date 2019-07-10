package fr.wildcodeschool.robinsdesmers.model;

public class RecyclingInfoItem {

    private int logoRecycling;
    private String titleRecyclingInfo;
    private String descriptionRecyclingInfo;

    public RecyclingInfoItem(int logo, String titleRecyclingInfo, String descriptionRecyclingInfo) {
        this.logoRecycling = logo;
        this.titleRecyclingInfo = titleRecyclingInfo;
        this.descriptionRecyclingInfo = descriptionRecyclingInfo;
    }

    public int getLogoRecycling() {
        return logoRecycling;
    }

    public void setLogoRecycling(int logo) {
        this.logoRecycling = logo;
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
