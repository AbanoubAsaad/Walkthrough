package com.abanoub.walkthrough_demo;

import android.os.Bundle;
import android.util.Log;

import com.abanoub.walkthrough.WalkthroughActivity;
import com.abanoub.walkthrough.WalkthroughItem;

public class MainActivity extends WalkthroughActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        WalkthroughItem page1 = new WalkthroughItem(R.drawable.facebook_final, getString(R.string.walkthrough_1_title), getString(R.string.walkthrough_1_details));
        page1.setTitleColorID(android.R.color.holo_blue_dark)
                .setSubTitleColorID(android.R.color.holo_blue_bright)
                .setSkipColorID(R.color.blue);

        WalkthroughItem page2 = new WalkthroughItem(R.drawable.youtube_final, getString(R.string.walkthrough_2_title), getString(R.string.walkthrough_2_details));
        page2.setBackgroundColorID(R.color.navy)
                .setTitleColorID(android.R.color.holo_red_light)
                .setSubTitleColorID(android.R.color.holo_red_dark)
                .setSkipColorID(android.R.color.white);

        WalkthroughItem page3 = new WalkthroughItem(R.drawable.twitter_final, getString(R.string.walkthrough_3_title), getString(R.string.walkthrough_3_details));
        page3.setBackgroundColorID(R.color.calypso)
                .setTitleColorID(android.R.color.holo_blue_light)
                .setSubTitleColorID(R.color.calypso);

        addPage(page1);
        addPage(page2);
        addPage(page3);

        setProgressType(DOTS_TYPE);
        setTransitionType(STACK_TRANSFORMER);
        setNextButtonColor(R.color.navy);
        setProgressBarColor(R.color.breaker_bay);
    }

    @Override
    public void onFinish() {
        finish();
    }
}
