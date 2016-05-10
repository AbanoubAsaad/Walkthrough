package com.abanoub.walkthrough;

public class WalkthroughItem {

    private int mImageID;
    private String mTitle;
    private String mSubTitle;

    private int mBackgroundColorID = android.R.color.white;
    private int mTitleColorID = R.color.blue;
    private int mSubTitleColorID = android.R.color.darker_gray;
    private int mSkipColorID = android.R.color.black;

    public WalkthroughItem(int id, String title, String subTitle) {
        mImageID = id;
        mTitle = title;
        mSubTitle = subTitle;
    }

    public String getTitle() {
        return mTitle;
    }

    public WalkthroughItem setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public WalkthroughItem setSubTitle(String subTitle) {
        this.mSubTitle = subTitle;
        return this;
    }

    public int getImageID() {
        return mImageID;
    }

    public WalkthroughItem setImageID(int imageID) {
        this.mImageID = imageID;
        return this;
    }

    public int getBackgroundColorID() {
        return mBackgroundColorID;
    }

    public WalkthroughItem setBackgroundColorID(int backgroundColorID) {
        this.mBackgroundColorID = backgroundColorID;
        return this;
    }

    public int getTitleColorID() {
        return mTitleColorID;
    }

    public WalkthroughItem setTitleColorID(int titleColorID) {
        this.mTitleColorID = titleColorID;
        return this;
    }

    public int getSubTitleColorID() {
        return mSubTitleColorID;
    }

    public WalkthroughItem setSubTitleColorID(int subTitleColorID) {
        this.mSubTitleColorID = subTitleColorID;
        return this;
    }

    public int getSkipColorID() {
        return mSkipColorID;
    }

    public void setSkipColorID(int skipColorID) {
        this.mSkipColorID = skipColorID;
    }
}
