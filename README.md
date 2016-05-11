# Walkthrough
A simple library to build a simple walkthrough activiy.

## Target platforms
API level 14 or later

## Latest version
Version 1.0.3 (May 11,2016)

## Getting started
This library is published on jCenter. Just add these lines to `build.gradle`.

```
dependencies {
    compile 'com.abanoub.androidlib:walkthrough:1.0.3:release@aar'
}
```

## Usage
1- Just extend **WalkthroughActivity**

    public class MainActivity extends WalkthroughActivity{
    
    }

2- Create new object from **WalkthroughItem** which represent a single page and add it to the activity.

    WalkthroughItem page = new WalkthroughItem(drawableId, title, subTitle);
    addPage(page);
You can use this functions to  customize your page :

    page.setBackgroundColorID(R.color.colorName);
    page.setTitleColorID(R.color.colorName);
    page.setSubTitleColorID(R.color.colorName);
    page.setSkipColorID(R.color.colorName);

3- You can use this function to customize you activity :

a- The type of progress, dots or horizontal bar by default it's dots :
**DOTS_TYPE** or **BAR_TYPE** 
	
    setProgressType(progressType);
and if you want to hide the progress just call 

    hideProgress();

b- The color of progress :

    setProgressBarColor(R.color.colorName);
c- Transition : you can use any of this built in transitions 
**ACCORDION_TRANSFORMER**,
**BACK_TO_FORE_TRANSFORMER**,
**FORE_TO_BACK_TRANSFORMER**,
**DEPTH_TRANSFORMER**,
**SCALE_IN_OUT_TRANSFORMER**,
**STACK_TRANSFORMER**,
**ZOOM_OUT_SLIDE_TRANSFORMER**,
**ZOOM_OUT_TRANSFORMER**,

    setTransitionType(transition);
and if you want the default transition for the viewPager don't call this function.

d- You can hide the skip button using 

    hideSkipButton();
e- To decide what happen when this Walkthrough finish just override onFinish() function.

    @Override
    public void onFinish() {
        enter code here
    }
    
