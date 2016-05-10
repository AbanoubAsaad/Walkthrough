package com.abanoub.walkthrough;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class WalkthroughPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;

    ArrayList<WalkthroughItem> mItems;

    public WalkthroughPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (mItems != null)
            return mItems.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.walkthrough_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        TextView title = (TextView) itemView.findViewById(R.id.walkthrough_title);
        TextView details = (TextView) itemView.findViewById(R.id.walkthrough_details);

        WalkthroughItem currentWalkthroughItem = mItems.get(position);

        imageView.setImageResource(currentWalkthroughItem.getImageID());
        itemView.setBackgroundColor(mContext.getResources().getColor(currentWalkthroughItem.getBackgroundColorID()));
        title.setText(currentWalkthroughItem.getTitle());
        title.setTextColor(mContext.getResources().getColor(currentWalkthroughItem.getTitleColorID()));
        details.setText(currentWalkthroughItem.getSubTitle());
        details.setTextColor(mContext.getResources().getColor(currentWalkthroughItem.getSubTitleColorID()));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    public void addItem(WalkthroughItem walkthroughItem) {
        if (mItems == null)
            mItems = new ArrayList<WalkthroughItem>();

        mItems.add(walkthroughItem);
    }

    public WalkthroughItem getItemAtPosition(int position) {
        return mItems.get(position);
    }
}
