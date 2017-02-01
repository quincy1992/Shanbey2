package com.quincy.shanbay.view;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by quincy on 1/16/17.
 */
public class ScrollViewPager extends ViewPager {

    private final String TAG = "ScrollViewPager";

    private boolean canScroll = true;

    public ScrollViewPager(Context context){
        super(context);
    }

    public ScrollViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public ScrollViewPager(Context context, boolean canScroll){
        super(context);
        this.canScroll = canScroll;
    }

    public void setCanScroll(boolean canScroll){
        this.canScroll = canScroll;
    }

//    @Override
//    public void scrollTo(int x, int y){
//        if(canScroll){
//            super.scrollTo(x, y);
//        }
//    }


//    public boolean onIntercepttouchevent(MotionEvent event){
//        switch (event.getAction()){
//            case MotionEvent.ACTION_MOVE:
//                Log.d(TAG, "ACTION_MOVE");
//                if(canScroll == false) {
//                    return false;
//                }
//                break;
//
//        }
//        return super.onTouchEvent(event);
//    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
        Log.d(TAG, "setCurrentItem item : " + item);
    }
}
