package com.abanoub.walkthrough;

import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.abanoub.walkthrough.transformation.AccordionTransformer;
import com.abanoub.walkthrough.transformation.BackgroundToForegroundTransformer;
import com.abanoub.walkthrough.transformation.DepthPageTransformer;
import com.abanoub.walkthrough.transformation.ForegroundToBackgroundTransformer;
import com.abanoub.walkthrough.transformation.ScaleInOutTransformer;
import com.abanoub.walkthrough.transformation.StackTransformer;
import com.abanoub.walkthrough.transformation.ZoomOutSlideTransformer;
import com.abanoub.walkthrough.transformation.ZoomOutTranformer;


public class WalkthroughActivity extends AppCompatActivity {

    public static final int BAR_TYPE = 0;
    public static final int DOTS_TYPE = 1;

    public static final int ACCORDION_TRANSFORMER = 1;
    public static final int BACK_TO_FORE_TRANSFORMER = 2;
    public static final int FORE_TO_BACK_TRANSFORMER = 3;
    public static final int DEPTH_TRANSFORMER = 4;
    public static final int SCALE_IN_OUT_TRANSFORMER = 5;
    public static final int STACK_TRANSFORMER = 6;
    public static final int ZOOM_OUT_SLIDE_TRANSFORMER = 7;
    public static final int ZOOM_OUT_TRANSFORMER = 8;


    Boolean mShowProgress = true;
    int mProgressType = DOTS_TYPE;

    TextView mNextBtn;
    TextView mSkipBtn;
    ProgressBar mProgressBar;
    LinearLayout pager_indicator;
    WalkthroughPagerAdapter mAdapter;
    ViewPager mViewPager;
    int dotsCount;
    ImageView[] dots;
    int mCurrentPosition;

    Drawable mSelectedDot;
    Drawable mNormalDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walkthrough);

        mSelectedDot = getResources().getDrawable(R.drawable.selecteditem_dot);
        mNormalDot = getResources().getDrawable(R.drawable.nonselecteditem_dot);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new WalkthroughPagerAdapter(this);
        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                mProgressBar.setProgress((mCurrentPosition + 1) * (100 / mAdapter.getCount()));
                if (position < mAdapter.getCount() - 1) {
                    mNextBtn.setText(getString(R.string.next));
                    mSkipBtn.setVisibility(View.VISIBLE);
                } else {
                    mNextBtn.setText(getString(R.string.enjoy));
                    mSkipBtn.setVisibility(View.GONE);
                }
                for (int i = 0; i < dotsCount; i++) {
                    dots[i].setImageDrawable(mNormalDot);
                }
                dots[position].setImageDrawable(mSelectedDot);

                mSkipBtn.setTextColor(getResources().getColor(mAdapter.getItemAtPosition(position).getSkipColorID()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mProgressBar = (ProgressBar) findViewById(R.id.walkthrough_progressbar);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);

        mNextBtn = (TextView) findViewById(R.id.walkthrough_next);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentPosition < mAdapter.getCount() - 1) {
                    mViewPager.setCurrentItem(mCurrentPosition + 1);
                } else {
                    onFinish();
                }
            }
        });

        mSkipBtn = (TextView) findViewById(R.id.walkthrough_skip);
        mSkipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinish();
            }
        });
    }

    public void updateProgress() {
        mProgressBar.setProgress(100 / mAdapter.getCount());
        dotsCount = mAdapter.getCount();
        pager_indicator.removeAllViews();
        dots = new ImageView[dotsCount];
        for (int i = 0; i < dotsCount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(mNormalDot);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(4, 0, 4, 0);
            pager_indicator.addView(dots[i], params);
        }
        dots[0].setImageDrawable(mSelectedDot);
        if (dotsCount == 1) {
            mNextBtn.setText(getString(R.string.enjoy));
            mSkipBtn.setVisibility(View.GONE);
        } else {
            mNextBtn.setText(getString(R.string.next));
            mSkipBtn.setVisibility(View.VISIBLE);
        }
        mSkipBtn.setTextColor(getResources().getColor(mAdapter.getItemAtPosition(0).getSkipColorID()));
    }

    public void setProgressType(int progressType) {
        mProgressType = progressType;
        if (mProgressType == BAR_TYPE) {
            mProgressBar.setVisibility(View.VISIBLE);
            pager_indicator.setVisibility(View.GONE);
        } else if (mProgressType == DOTS_TYPE) {
            mProgressBar.setVisibility(View.GONE);
            pager_indicator.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgress() {
        mShowProgress = false;
        mProgressBar.setVisibility(View.GONE);
        pager_indicator.setVisibility(View.GONE);
    }

    public void setTransitionType(int transitionType) {
        switch (transitionType) {
            case ACCORDION_TRANSFORMER:
                mViewPager.setPageTransformer(true, new AccordionTransformer());
                break;
            case BACK_TO_FORE_TRANSFORMER:
                mViewPager.setPageTransformer(true, new BackgroundToForegroundTransformer());
                break;
            case FORE_TO_BACK_TRANSFORMER:
                mViewPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
                break;
            case DEPTH_TRANSFORMER:
                mViewPager.setPageTransformer(true, new DepthPageTransformer());
                break;
            case SCALE_IN_OUT_TRANSFORMER:
                mViewPager.setPageTransformer(true, new ScaleInOutTransformer());
                break;
            case STACK_TRANSFORMER:
                mViewPager.setPageTransformer(true, new StackTransformer());
                break;
            case ZOOM_OUT_SLIDE_TRANSFORMER:
                mViewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
                break;
            case ZOOM_OUT_TRANSFORMER:
                mViewPager.setPageTransformer(true, new ZoomOutTranformer());
                break;
        }
    }

    public void addPage(WalkthroughItem walkthroughItem) {
        mAdapter.addItem(walkthroughItem);
        mAdapter.notifyDataSetChanged();
        updateProgress();
    }

    public void hideSkipButton() {
        mSkipBtn.setVisibility(View.GONE);
    }

    public void setProgressBarColor(int color) {
        Drawable drawable = mProgressBar.getProgressDrawable();
        drawable.setColorFilter(new LightingColorFilter(0x00000000, getResources().getColor(color)));
        mSelectedDot.setColorFilter(new LightingColorFilter(0x00000000, getResources().getColor(color)));
        mNormalDot.setColorFilter(new LightingColorFilter(0xFFFFFFFF, getResources().getColor(color)));
        updateProgress();
    }

    public void setNextButtonColor(int color) {
        mNextBtn.setTextColor(getResources().getColor(color));
    }


    public void onFinish() {
        finish();
    }

}
